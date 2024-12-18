CREATE TABLE room (
                      id SERIAL PRIMARY KEY,
                      capacity INT NOT NULL,
                      room_class VARCHAR(50) NOT NULL,
                      is_available BOOLEAN NOT NULL,
                      price_per_night NUMERIC(10, 2) NOT NULL
);

CREATE TABLE manager (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         username VARCHAR(100) NOT NULL UNIQUE,
                         password VARCHAR(255) NOT NULL
);

CREATE TABLE orders (
                        id SERIAL PRIMARY KEY,
                        client_id INT NOT NULL,
                        room_id INT NOT NULL,
                        start_date DATE NOT NULL,
                        end_date DATE NOT NULL,
                        total_price NUMERIC(10, 2) NOT NULL,
                        status VARCHAR(50) NOT NULL,
                        FOREIGN KEY (client_id) REFERENCES client(id),
                        FOREIGN KEY (room_id) REFERENCES room(id)
);
