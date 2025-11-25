DROP TABLE IF EXISTS furniture_colors;

ALTER TABLE furnitures
ADD COLUMN color_id BIGINT;

ALTER TABLE furnitures
ADD CONSTRAINT fk_furnitures_color
FOREIGN KEY (color_id)
REFERENCES colors(id_color)
ON DELETE SET NULL;
