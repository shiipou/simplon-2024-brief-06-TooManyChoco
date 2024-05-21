CREATE TABLE IF NOT EXISTS event (

event_id SERIAL PRIMARY KEY,

event_date DATE,

creator VARCHAR REFERENCES users(username)

);
INSERT INTO users (username, first_name) VALUES ('Omar1H1', 'Omar');
INSERT INTO users (username, first_name) VALUES ('Sfaucher', 'Sandrine');

INSERT INTO event (event_date, creator) VALUES ('2024-05-16', 'Omar1H1');
INSERT INTO event (event_date, creator) VALUES ('2024-05-13', 'Sfaucher');

CREATE TABLE IF NOT EXISTS pastry (

pastry_id SERIAL PRIMARY KEY,

pastry_name VARCHAR(255) NOT NULL

);

INSERT INTO pastry (pastry_name) VALUES ('croissants'), ('pains aux chocolats'),('cookies'), ('chocolatines'), ('pains aux raisins');

CREATE TABLE IF NOT EXISTS event_pastry (

event_id INT REFERENCES event(event_id),

pastry_id INT REFERENCES pastry(pastry_id)

);

INSERT INTO event_pastry (event_id, pastry_id) VALUES (1, 1);

