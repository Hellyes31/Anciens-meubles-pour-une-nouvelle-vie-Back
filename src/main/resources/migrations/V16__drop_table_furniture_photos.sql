DROP TABLE IF EXISTS furniture_photos;

ALTER TABLE photos
ADD COLUMN furniture_id BIGINT;

ALTER TABLE photos
ADD CONSTRAINT fk_photos_furniture
FOREIGN KEY (furniture_id) REFERENCES furnitures(id_furniture)
ON DELETE SET NULL;
