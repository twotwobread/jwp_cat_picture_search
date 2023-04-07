create table if not exists cat_picture_seq
(
    next_val bigint
) engine = InnoDB;

insert into cat_picture_seq
values (1);

create table if not exists cat_picture
(
    id          bigint  not null,
    height      integer not null,
    image_id    varchar(255),
    name        varchar(255),
    origin      varchar(255),
    temperament varchar(255),
    url         varchar(255),
    width       integer not null,
    primary key (id)
) engine = InnoDB;

alter table cat_picture
    add constraint UK_63972j03gctffxvn55v1cwqh7 unique (image_id);