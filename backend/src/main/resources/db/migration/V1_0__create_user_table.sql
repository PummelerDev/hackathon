create table if not exists users
(
    id        bigserial,
    firstname varchar(25),
    lastname  varchar(25),
    email     varchar(25),
    password  varchar(25)
    );

-- alter table users
--     owner to CURRENT_USER;