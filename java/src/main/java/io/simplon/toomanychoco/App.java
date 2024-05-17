package io.simplon.toomanychoco;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import io.simplon.toomanychoco.db.DbConnector;
import io.simplon.toomanychoco.db.DbMigrator;
import io.simplon.toomanychoco.exception.EventNotFoundException;
import io.simplon.toomanychoco.exception.UserNotFoundException;
import io.simplon.toomanychoco.model.Event;
import io.simplon.toomanychoco.model.User;
import io.simplon.toomanychoco.repository.EventRespository;
import io.simplon.toomanychoco.repository.UserRepository;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.concurrent.Executors;

public class App {
	private static final App instance = new App();

	public static App getInstance() {
		return instance;
	}

	private final UserRepository userRepository = UserRepository.getInstance();
	private final EventRespository eventRespository = EventRespository.getInstance();


	private App() {
	}

	public void init() throws SQLException, IOException {
			Connection connector = DbConnector.getConnection();
			DbMigrator migrator = new DbMigrator(connector);

			migrator.migrateDatabase();
	}

	public void start(int numberOfThreads) throws IOException {
		// Create HTTP server on port 8080
		HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

		// Create a basic handler for index route ('/')
		server.createContext("/", (request -> {
			String response = "Hello, world!";
			request.sendResponseHeaders(200, response.getBytes().length);
			request.getResponseBody().write(response.getBytes());
			request.getResponseBody().close();
		}));

		// Create a new handler with Database access ('/hello/:username')
		server.createContext("/hello", (request -> {
			try {
				String username = request.getRequestURI().getPath().split("/")[2];

				User user = userRepository
						.findByUsername(username)
						.orElseThrow(
								() -> new UserNotFoundException(
										String.format("User '%s' didn't exist in database.", username)));

				String response = String.format("Hello, %s", user.getFirstName());

				request.sendResponseHeaders(200, response.getBytes().length);
				request.getResponseBody().write(response.getBytes());
				request.getResponseBody().close();
			} catch (IndexOutOfBoundsException error) {
				String response = "Username parameter is missing. Example : `/hello/bob` will return `Hello, Bob!`.";
				request.sendResponseHeaders(400, response.getBytes().length);
				request.getResponseBody().write(response.getBytes());
				request.getResponseBody().close();
			} catch (UserNotFoundException error) {
				String response = error.getMessage();
				request.sendResponseHeaders(404, response.getBytes().length);
				request.getResponseBody().write(response.getBytes());
				request.getResponseBody().close();
			}
		}));

		//Handler endpoint POST createUser ('/create/user')
		server.createContext("/create/user", request ->{

			//Lire le corps de la requete sous forme de String
			String body = new String(request.getRequestBody().readAllBytes());
			//Parser la String en objet JSON
			ObjectMapper objectMapper = new ObjectMapper();
//			User user = new User();
			try {
				User user = objectMapper.readValue(body, User.class);
				//Traitement du user
				userRepository.createUser(user);
				//Réponse au client
				request.sendResponseHeaders(201, 0);
				request.getResponseBody().close();
			} catch(Exception e){
				e.printStackTrace();
			}
		});
		server.createContext("/event", (request -> {
			try {
				// on recherche le contenu dans l'url après /event/ XXXXXX
				String date = request.getRequestURI().getPath().split("/")[2];

				Event event = eventRespository
						.findByEventDate(date)
						.orElseThrow(
								() -> new EventNotFoundException(
										String.format("Event '%s' didn't exist in database.", date)));
				ObjectMapper objectMapper = new ObjectMapper();
				String response = objectMapper.writeValueAsString(event);

				request.sendResponseHeaders(200, response.getBytes().length);
				request.getResponseBody().write(response.getBytes());
				request.getResponseBody().close();
			} catch (IndexOutOfBoundsException error) {
				String response = "Event parameter is missing. Example : `/event/2024-05-16` will return  event_date | event_id | first_name | pastry_name.";
				request.sendResponseHeaders(400, response.getBytes().length);
				request.getResponseBody().write(response.getBytes());
				request.getResponseBody().close();
			} catch (EventNotFoundException error) {
				String response = error.getMessage();
				request.sendResponseHeaders(404, response.getBytes().length);
				request.getResponseBody().write(response.getBytes());
				request.getResponseBody().close();
			}
		}));

		// Start the server in a new thread
		server.setExecutor(Executors.newFixedThreadPool(numberOfThreads)); // Use a thread pool
		server.start();

		System.out.println("Server started on port 8080");
	}

	public static void main(String[] args) throws SQLException, IOException {
		App app = App.getInstance();
		app.init();
		app.start(10);
	}

}
