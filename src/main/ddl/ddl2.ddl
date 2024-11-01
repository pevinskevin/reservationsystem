-- we don't know how to generate root <with-no-name> (class Root) :(

create table admin
(
    user_name varchar(255) not null
        primary key,
    password  varchar(255) not null,
    url       varchar(255) null,
    constraint url
        unique (url)
)
    engine = InnoDB;

create table beverage
(
    id    int auto_increment
        primary key,
    name  varchar(255) not null,
    price int          not null
)
    engine = InnoDB;

create table cafe_table
(
    id            int auto_increment
        primary key,
    table_number  int not null,
    seat_capacity int not null,
    constraint table_number
        unique (table_number)
)
    engine = InnoDB;

create table reservation
(
    id                int auto_increment
        primary key,
    name              varchar(255)                           not null,
    email             varchar(255)                           not null,
    company_name      varchar(255)                           null,
    phone_number      int                                    not null,
    number_of_seats   int                                    not null,
    reservation_date  date                                   not null,
    time              time                                   not null,
    duration_in_hours int                                    not null,
    celebration       enum ('false', 'true') default 'false' null,
    birthday          enum ('false', 'true') default 'false' null,
    comments          varchar(255)                           null,
    url               varchar(255)                           not null
)
    engine = InnoDB;

create table beverage_reservation
(
    id             int auto_increment
        primary key,
    reservation_id int null,
    beverage_id    int null,
    quantity       int not null,
    constraint beverage_reservation_ibfk_1
        foreign key (reservation_id) references reservation (id),
    constraint beverage_reservation_ibfk_2
        foreign key (beverage_id) references beverage (id)
            on delete cascade
)
    engine = InnoDB;

create index beverage_id
    on beverage_reservation (beverage_id);

create index reservation_id
    on beverage_reservation (reservation_id);

create table cafe_table_reservation
(
    id             int auto_increment
        primary key,
    reservation_id int null,
    cafe_table_id  int null,
    constraint cafe_table_reservation_ibfk_1
        foreign key (reservation_id) references reservation (id),
    constraint cafe_table_reservation_ibfk_2
        foreign key (cafe_table_id) references cafe_table (id)
            on delete cascade
)
    engine = InnoDB;

create index cafe_table_id
    on cafe_table_reservation (cafe_table_id);

create index reservation_id
    on cafe_table_reservation (reservation_id);

