create table if not exists images
(
    id        bigserial,
    name varchar(50),
    image bytea
    );

alter table images
    owner to CURRENT_USER;
