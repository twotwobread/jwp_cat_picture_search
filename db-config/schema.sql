CREATE DATABASE IF NOT EXISTS `${MYSQL_DATABASE}`;
USE `${MYSQL_DATABASE}`;

CREATE TABLE IF NOT EXISTS cat_picture
(
    id          bigint  not null,
    height      integer not null,
    image_id    varchar(255) unique,
    name        varchar(255),
    origin      varchar(255),
    temperament varchar(255),
    url         varchar(255),
    width       integer not null,
    primary key (id)
) engine = InnoDB;