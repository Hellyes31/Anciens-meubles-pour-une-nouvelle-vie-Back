ALTER TABLE users
ADD COLUMN username VARCHAR(50);

ALTER TABLE users
ADD CONSTRAINT username_format CHECK (username ~ '^[a-zA-Z0-9._-]{3,20}$');