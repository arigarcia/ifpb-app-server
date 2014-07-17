create database db_app;

use db_app; -- for mysql

create table tb_user(
  email varchar(40) not null,
  name varchar(100) not null,
  passkey varchar(100) not null
);

alter table tb_user
add constraint primary key pk_user(email);

create table tb_route(
  id int not null,
  dat long not null,
  primary key pk_route(id) 
);

create table tb_route_coordinate(
  id int not null,
  longitude long not null,
  latitude long not null,
  foreign key fk_route_coordinate (id) references tb_route(id)
);