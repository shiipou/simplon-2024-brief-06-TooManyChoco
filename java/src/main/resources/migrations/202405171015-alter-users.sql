ALTER TABLE users
ADD email VARCHAR(255) NOT NULL UNIQUE
ADD password VARCHAR(255) NOT NULL;

INSERT INTO users (username, firstname, email, password) VALUES ('bob', 'bobo', 'bobo@maif.fr', 'bobo123');
