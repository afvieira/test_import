# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table curso (
  id                        bigint not null,
  code                      varchar(255),
  description               varchar(255),
  constraint pk_curso primary key (id))
;

create table discipline (
  id                        bigint not null,
  course_id                 bigint,
  code                      varchar(255),
  course_year               integer,
  year                      integer,
  constraint pk_discipline primary key (id))
;

create table user (
  id                        bigint not null,
  email                     varchar(255) not null,
  nome                      varchar(255),
  encrypted_password        varchar(255),
  date_sign_up              timestamp,
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id))
;

create sequence curso_seq;

create sequence discipline_seq;

create sequence user_seq;

alter table discipline add constraint fk_discipline_course_1 foreign key (course_id) references curso (id) on delete restrict on update restrict;
create index ix_discipline_course_1 on discipline (course_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists curso;

drop table if exists discipline;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists curso_seq;

drop sequence if exists discipline_seq;

drop sequence if exists user_seq;

