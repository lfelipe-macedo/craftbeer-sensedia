CREATE DATABASE craftbeer;
USE craftbeer;
drop table if exists beers;
drop table if exists hibernate_sequence;
create table beers (id bigint not null, alcohol_content varchar(255) not null,
category varchar(255) not null, created_at date,
ingredients varchar(255) not null, name varchar(255) not null,
price decimal(19,2) not null, updated_at date, primary key (id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );