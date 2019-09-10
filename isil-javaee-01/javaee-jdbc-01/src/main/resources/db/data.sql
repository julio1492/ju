create table t_users
(
    id       serial primary key,
    login    varchar(20) not null,
    password varchar(20) not null
);

insert into t_users (id, login, password)
values (101, 'jose', '1234567');
insert into t_users (id, login, password)
values (102, 'user', 'user');
insert into t_users (id, login, password)
values (103, 'admin', 'admin');
