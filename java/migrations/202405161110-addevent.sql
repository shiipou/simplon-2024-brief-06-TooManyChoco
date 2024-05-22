-- Creation des tables -----------------

CREATE TABLE IF NOT EXISTS pastry (
    "pastry_id" SERIAL PRIMARY KEY,
    "pastry_name" VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS event (
    "event_id" SERIAL PRIMARY KEY,
    "event_date" DATE,
    "creator" VARCHAR(255) REFERENCES "users"("username") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS event_pastry (
    "event_id" INT REFERENCES "event"("event_id") ON DELETE CASCADE,
    "pastry_id" INT REFERENCES "pastry"("pastry_id") ON DELETE CASCADE,
    PRIMARY KEY ("event_id", "pastry_id")
);


-- Insertion dans les tables --------------

INSERT INTO pastry ("pastry_name") VALUES
('croissants'),
('pains aux chocolats'),
('pains aux raisins'),
('croissants aux amandes'),
('chouquettes');

INSERT INTO event ("event_date", "creator") VALUES
('2024-05-16', 'shiipou');

INSERT INTO event_pastry ( "event_id", "pastry_id") VALUES
(1, 1);
