CREATE TABLE roles (
    id_role BIGSERIAL PRIMARY KEY,
    role_name VARCHAR(100) NOT NULL UNIQUE
);

INSERT INTO roles (role_name) VALUES ('Admin'), ('Vendeur'), ('Acheteur');

ALTER TABLE users ADD COLUMN role_id BIGINT;

UPDATE users
SET role_id = r.id_role
FROM roles r
WHERE users.role = r.role_name;

UPDATE users SET role_id = (SELECT id_role FROM roles WHERE role_name = 'acheteur')
WHERE role_id IS NULL;

ALTER TABLE users ALTER COLUMN role_id SET NOT NULL;

ALTER TABLE users
ADD CONSTRAINT fk_users_roles
FOREIGN KEY (role_id) REFERENCES roles(id_role)
ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE users DROP COLUMN role;
