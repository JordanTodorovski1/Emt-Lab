CREATE TABLE users
(
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    name VARCHAR(100),
    surname VARCHAR(100),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE reservation
(
    id BIGSERIAL PRIMARY KEY,
    date_from DATE,
    date_to DATE,
    accommodation_id BIGINT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (accommodation_id) REFERENCES accommodations (id)
);

CREATE TABLE user_reservation
(
    reservation_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    PRIMARY KEY (reservation_id, user_id),
    FOREIGN KEY (reservation_id) REFERENCES reservation (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);