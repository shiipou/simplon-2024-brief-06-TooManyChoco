package io.simplon.toomanychoco;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Executors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import io.simplon.toomanychoco.db.DbConnector;
import io.simplon.toomanychoco.db.DbMigrator;
import io.simplon.toomanychoco.exception.EventNotFoundException;
import io.simplon.toomanychoco.exception.UserNotFoundException;
import io.simplon.toomanychoco.model.Event;
import io.simplon.toomanychoco.model.Pastry;
import io.simplon.toomanychoco.model.User;
import io.simplon.toomanychoco.repository.EventRepository;
import io.simplon.toomanychoco.repository.EventRespository;
import io.simplon.toomanychoco.repository.PastryRepository;
import io.simplon.toomanychoco.repository.UserRepository;

public class App {

    private static final App instance = new App();

    public static App getInstance() {
        return instance;
    }

    private final UserRepository userRepository = UserRepository.getInstance();
    private final EventRespository eventRespository = EventRespository.getInstance();
    private final PastryRepository pastryRepository = PastryRepository.getInstance();
    private final EventRepository eventRepository = EventRepository.getInstance();

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
        server.createContext("/", withCORS(request -> {
            String response = "Hello, world!";
            request.sendResponseHeaders(200, response.getBytes().length);
            request.getResponseBody().write(response.getBytes());
            request.getResponseBody().close();
        }));

        // Create a new handler with Database access ('/hello/:username')
        server.createContext("/hello", withCORS(request -> {
            try {
                String username = request.getRequestURI().getPath().split("/")[2];

                User user = userRepository
                        .findByUsername(username)
                        .orElseThrow(
                                () -> new UserNotFoundException(
                                        String.format("User '%s' didn't exist in database.", username)));

                String response = String.format("Hello, %s", user.getFirstname());

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

        // Handler endpoint POST createUser ('/create/user')
        server.createContext("/create/user", withCORS(request -> {
            if (request.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                request.sendResponseHeaders(204, -1);
                return;
            }

            // Lire le corps de la requete sous forme de String
            String body = new String(request.getRequestBody().readAllBytes());
            // Parser la String en objet JSON
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                User user = objectMapper.readValue(body, User.class);
                // Traitement du user
                userRepository.createUser(user);
                // Réponse au client
                request.sendResponseHeaders(201, 0);
                request.getResponseBody().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

        // Create a new handler with Database access ('/pastries')
        server.createContext("/pastries", withCORS(request -> {
            if (request.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                request.sendResponseHeaders(204, -1);
                return;
            }

            List<Pastry> pastries = pastryRepository.findAll();

            ObjectMapper objectMapper = new ObjectMapper();
            String response = objectMapper.writeValueAsString(pastries);

            request.sendResponseHeaders(200, response.getBytes().length);
            request.getResponseBody().write(response.getBytes());
            request.getResponseBody().close();
        }));

        // Create a new handler with Database access ('/events')
        server.createContext("/events", withCORS(request -> {
            ObjectMapper objectMapper = new ObjectMapper();
            String method = request.getRequestMethod();

            /*
             * if (method.equalsIgnoreCase("OPTIONS")) {
             * request.sendResponseHeaders(204, -1);
             * return;
             * }
             */
            if (method.equalsIgnoreCase("GET")) {
                /*
                 * request.sendResponseHeaders(204, -1);
                 * return;
                 */
                List<Event> events = eventRepository.findAll();
                String response = objectMapper.writeValueAsString(events);

                request.sendResponseHeaders(200, response.getBytes().length);
                request.getResponseBody().write(response.getBytes());
                request.getResponseBody().close();
            }

            if (method.equalsIgnoreCase("POST")) {
                try {
                    String body = new String(request.getRequestBody().readAllBytes());
                    System.out.println("body : " + body);
                    Event event = objectMapper.readValue(body, Event.class);

                    // Save the event to the database
                    eventRepository.save(event, event.getPastries());

                    request.sendResponseHeaders(201, 0);
                    request.getResponseBody().close();
                } catch (Exception e) {
                    e.printStackTrace();
                    String response = "Failed to create event";
                    request.sendResponseHeaders(500, response.getBytes().length);
                    request.getResponseBody().write(response.getBytes());
                    request.getResponseBody().close();
                }
            } else {
                request.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        }));

        // Vérifie qu'un user existe en base si oui cette route renvoie les informations
        // d'user
        server.createContext("/login", withCORS(request -> {
            String method = request.getRequestMethod();
            if (method.equalsIgnoreCase("OPTIONS")) {
                request.sendResponseHeaders(204, -1);
                return;
            }

            if (method.equalsIgnoreCase("POST")) {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();

                    String userInfoJson = new String(request.getRequestBody().readAllBytes());
                    System.out.println(userInfoJson);

                    User userInfo = objectMapper.readValue(userInfoJson, User.class);
                    System.out.println(userInfo);

                    User user = userRepository
                            .findIsUserExist(userInfo)
                            .orElseThrow(
                                    () -> new UserNotFoundException(
                                            String.format("User '%s' didn't exist in database.", userInfo.getEmail())));

                    String response = objectMapper.writeValueAsString(user);

                    request.sendResponseHeaders(200, response.getBytes().length);
                    request.getResponseBody().write(response.getBytes());
                    request.getResponseBody().close();
                } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
                    e.printStackTrace();
                } catch (UserNotFoundException error) {
                    String response = "User not found. ";
                    request.sendResponseHeaders(400, response.getBytes().length);
                    request.getResponseBody().write(response.getBytes());
                    request.getResponseBody().close();
                }
            }
        }));

        server.createContext("/event", withCORS(request -> {
            try {
                // Extract the content from the URL after "/event/"
                String date = request.getRequestURI().getPath().split("/")[2];

                Event event = eventRespository
                        .findByEventDate(date)
                        .orElseThrow(() -> new EventNotFoundException(
                                String.format("Event '%s' didn't exist in the database.", date)));

                // Serialize the event object into a JSON string
                ObjectMapper objectMapper = new ObjectMapper();
                String response = objectMapper.writeValueAsString(event);

                // Send the HTTP response
                request.sendResponseHeaders(200, response.getBytes().length);
                request.getResponseBody().write(response.getBytes());
                request.getResponseBody().close();
            } catch (IndexOutOfBoundsException error) {
                String response = "Event parameter is missing. Example: `/event/2024-05-16` will return event_date | event_id | first_name | pastry_name.";
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

    private static HttpHandler withCORS(HttpHandler handler) {
        return exchange -> {
            Headers headers = exchange.getResponseHeaders();
            headers.add("Access-Control-Allow-Origin", "*");
            headers.add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            headers.add("Access-Control-Allow-Headers", "Content-Type");

            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            handler.handle(exchange);
        };
    }

    public static void main(String[] args) throws SQLException, IOException {
        App app = App.getInstance();
        app.init();
        app.start(10);
    }

}
