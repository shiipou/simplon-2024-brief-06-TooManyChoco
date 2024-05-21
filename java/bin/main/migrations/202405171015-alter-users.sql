ALTER TABLE users
ADD email VARCHAR(255) UNIQUE,
ADD password VARCHAR(255) ;

INSERT INTO users (username, first_name, email, password) VALUES ('bob', 'bobo', 'bobo@maif.fr', 'bobo123');