-- Creation des tables -----------------

CREATE TABLE IF NOT EXISTS viennoiserie (
    "id" SERIAL PRIMARY KEY,
    "nom" VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS evenement (
    "id" SERIAL PRIMARY KEY,
    "dateEvent" DATE,
    "auteur" VARCHAR(255) REFERENCES "users"("username") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS viennoiserie_evenement (
    "evenement_id" INT REFERENCES "evenement"("id") ON DELETE CASCADE,
    "viennoiserie_id" INT REFERENCES "viennoiserie"("id") ON DELETE CASCADE
);


-- Insertion dans les tables --------------

INSERT INTO viennoiserie ("nom") VALUES
('croissant'),
('pain au chocolat'),
('pain aux raisins'),
('croissant aux amandes'),
('chouquette');

INSERT INTO evenement ("dateEvent", "auteur") VALUES
('2024-05-16', 'shiipou');

INSERT INTO viennoiserie_evenement ( "evenement_id", "viennoiserie_id") VALUES
(1, 1),
(1, 4);