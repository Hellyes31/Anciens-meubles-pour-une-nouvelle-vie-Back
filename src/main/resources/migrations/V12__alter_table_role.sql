-- Supprimer les rôles inutiles
DELETE FROM roles WHERE role_name IN ('vendeur', 'acheteur');

-- Mettre à jour le rôle admin
UPDATE roles
SET role_name = 'ROLE_ADMIN'
WHERE LOWER(role_name) = 'admin';

-- Ajouter un rôle utilisateur générique
INSERT INTO roles (role_name)
SELECT 'ROLE_USER'
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE role_name = 'ROLE_USER');