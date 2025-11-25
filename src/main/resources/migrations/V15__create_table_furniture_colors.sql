CREATE TABLE furniture_colors (
    furniture_id BIGINT NOT NULL,
    color_id BIGINT NOT NULL,
    PRIMARY KEY(furniture_id, color_id),
    FOREIGN KEY(furniture_id) REFERENCES furnitures(id_furniture) ON DELETE CASCADE,
    FOREIGN KEY(color_id) REFERENCES colors(id_color) ON DELETE CASCADE
);
CREATE TABLE furniture_photos (
    furniture_id BIGINT NOT NULL,
    photo_id BIGINT NOT NULL,
    PRIMARY KEY(furniture_id, photo_id),
    FOREIGN KEY(furniture_id) REFERENCES furnitures(id_furniture) ON DELETE CASCADE,
    FOREIGN KEY(photo_id) REFERENCES photos(id_photo) ON DELETE CASCADE
);
