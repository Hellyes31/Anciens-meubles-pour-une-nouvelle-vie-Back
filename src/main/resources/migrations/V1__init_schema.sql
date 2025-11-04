CREATE TABLE users (
    id_user BIGSERIAL PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    mail VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL
);

CREATE TABLE status (
    id_status BIGSERIAL PRIMARY KEY,
    status VARCHAR(100) NOT NULL
);

CREATE TABLE types (
    id_type BIGSERIAL PRIMARY KEY,
    type VARCHAR(100) NOT NULL
);

CREATE TABLE furnitures (
    id_furniture BIGSERIAL PRIMARY KEY,
    type_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    price FLOAT NOT NULL,
    status_id BIGINT NOT NULL,
    seller_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,

    CONSTRAINT fk_furnitures_type
        FOREIGN KEY (type_id) REFERENCES types(id_type)
        ON UPDATE CASCADE ON DELETE RESTRICT,

    CONSTRAINT fk_furnitures_status
        FOREIGN KEY (status_id) REFERENCES status(id_status)
        ON UPDATE CASCADE ON DELETE RESTRICT,

    CONSTRAINT fk_furnitures_seller
        FOREIGN KEY (seller_id) REFERENCES users(id_user)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE transactions (
    id_transaction BIGSERIAL PRIMARY KEY,
    id_furniture BIGINT NOT NULL,
    id_buyer BIGINT NOT NULL,
    sold_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    sold_price FLOAT NOT NULL,

    CONSTRAINT fk_transactions_furniture
        FOREIGN KEY (id_furniture) REFERENCES furnitures(id_furniture)
        ON UPDATE CASCADE ON DELETE RESTRICT,

    CONSTRAINT fk_transactions_buyer
        FOREIGN KEY (id_buyer) REFERENCES users(id_user)
        ON UPDATE CASCADE ON DELETE RESTRICT
);
