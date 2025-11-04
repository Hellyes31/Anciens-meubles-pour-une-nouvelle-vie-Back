ALTER TABLE users
ADD COLUMN city VARCHAR(255);

INSERT INTO users (firstname, lastname, mail, password, role)
VALUES ('Elies', 'Auguet', 'elies.test@test.fr', 'test1234', 'Admin');