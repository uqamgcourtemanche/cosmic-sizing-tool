# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table data_group (
  id                        integer primary key AUTOINCREMENT,
  name                      varchar(255) not null,
  movement                  varchar(255) not null,
  comment                   varchar(255) not null,
  process                   integer not null,
  entry                     integer not null,
  exit                      integer not null,
  read                      integer not null,
  write                     integer not null)
;

create table layer (
  id                        integer primary key AUTOINCREMENT,
  name                      varchar(255) not null,
  system                    integer not null)
;

create table process (
  id                        integer primary key AUTOINCREMENT,
  name                      varchar(255) not null,
  layer                     integer not null,
  f_add                     integer not null,
  f_modify                  integer not null,
  f_delete                  integer not null,
  f_unknown                 integer not null)
;

create table project (
  id                        integer primary key AUTOINCREMENT,
  time_stamp                varchar(255) not null,
  name                      varchar(255) not null,
  project_type              integer not null)
;

create table system (
  id                        integer primary key AUTOINCREMENT,
  name                      varchar(255) not null,
  project                   integer not null,
  f_add                     integer not null,
  f_modify                  integer not null,
  f_delete                  integer not null,
  f_unknown                 integer not null)
;

create table team_member (
  id                        integer primary key AUTOINCREMENT,
  name                      varchar(255) not null,
  email                     varchar(255) not null,
  password                  varchar(255) not null)
;




# --- !Downs

PRAGMA foreign_keys = OFF;

drop table data_group;

drop table layer;

drop table process;

drop table project;

drop table system;

drop table team_member;

PRAGMA foreign_keys = ON;

