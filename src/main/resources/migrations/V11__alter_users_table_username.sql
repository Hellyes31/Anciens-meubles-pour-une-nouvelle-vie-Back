UPDATE users
SET username = 'EliesA'
WHERE id_user = 1;

UPDATE users
SET username = 'MargotA'
WHERE id_user = 3;

ALTER TABLE users
ALTER COLUMN username SET NOT NULL;

ALTER TABLE users
ADD CONSTRAINT users_username_unique UNIQUE(username);
