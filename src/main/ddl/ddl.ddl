DROP DATABASE IF EXISTS reservationsystem;

CREATE DATABASE IF NOT EXISTS reservationsystem;

USE reservationsystem;

CREATE TABLE reservation (
                             `id` INT AUTO_INCREMENT PRIMARY KEY,
                             `name` VARCHAR(255) NOT NULL,
                             `email` VARCHAR(255) NOT NULL,
                             `company_name` VARCHAR(255),
                             `phone_number` int NOT NULL,
                             `number_of_seats` int NOT NULL,
                             `reservation_date` date NOT NULL,
                             `time` time NOT NULL,
                             `duration_in_hours` int NOT NULL,
                             `celebration` ENUM ('false', 'true') DEFAULT 'false',
                             `birthday` ENUM ('false', 'true') DEFAULT 'false',
                             `comments` VARCHAR(255),
                             `url` VARCHAR(255) NOT NULL
);

CREATE TABLE cafe_table (
                            `id` INT AUTO_INCREMENT PRIMARY KEY,
                            `table_number` int UNIQUE NOT NULL,
                            `seat_capacity` int NOT NULL
);

CREATE TABLE beverage (
                          `id` INT AUTO_INCREMENT PRIMARY KEY,
                          `name` VARCHAR(255) NOT NULL,
                          `price` int NOT NULL
);


CREATE TABLE cafe_table_reservation (
                                        `id` INT AUTO_INCREMENT PRIMARY KEY,
                                        `reservation_id` INT,
                                        `cafe_table_id` INT,
                                        FOREIGN KEY (`reservation_id`) REFERENCES `reservation`(`id`),
                                        FOREIGN KEY (`cafe_table_id`) REFERENCES `cafe_table`(`id`)
                                            ON DELETE CASCADE
);

CREATE TABLE beverage_reservation (
                                      `id` INT AUTO_INCREMENT PRIMARY KEY,
                                      `reservation_id` INT,
                                      `beverage_id` INT,
                                      `quantity` int NOT NULL,
                                      FOREIGN KEY (`reservation_id`) REFERENCES `reservation`(`id`),
                                      FOREIGN KEY (`beverage_id`) REFERENCES `beverage`(`id`)
                                          ON DELETE CASCADE
);

CREATE TABLE admin (
                       `user_name` VARCHAR(255) PRIMARY KEY,
                       `password` VARCHAR(255) NOT NULL,
                       `url` VARCHAR(255) UNIQUE
);

INSERT INTO cafe_table (table_number, seat_capacity) VALUES
                                                         (50, 4),
                                                         (52, 6),
                                                         (56, 8),
                                                         (60, 4);

INSERT INTO beverage (name, price) VALUES
                                       ('Syv Ekspressen Pilsner (Flaske)', 47),
                                       ('Syv Ekspressen Classic (Flaske)', 47),
                                       ('Grøn Tuborg (Flaske)', 44),
                                       ('Carlsberg (Flaske)', 44);

INSERT INTO admin (user_name, password, url) VALUES
    ('admin', 'root', '6a4e780d-34cf-408c-844f-035bce09b427');


