# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table course (
  id                        bigint not null,
  code                      varchar(255),
  description               varchar(255),
  constraint pk_course primary key (id))
;

create table discipline (
  id                        bigint not null,
  course_id                 bigint,
  code                      varchar(255),
  course_year               integer,
  year                      integer,
  constraint pk_discipline primary key (id))
;

create table shift (
  id                        bigint not null,
  discipline_id             bigint,
  code                      varchar(255),
  description               varchar(255),
  constraint pk_shift primary key (id))
;

create table student (
  id                        bigint not null,
  email                     varchar(255) not null,
  encrypted_password        varchar(255),
  date_sign_up              timestamp,
  code                      varchar(255),
  name                      varchar(255),
  birthdate                 timestamp,
  contact                   varchar(255),
  constraint uq_student_email unique (email),
  constraint pk_student primary key (id))
;

create table teacher (
  id                        bigint not null,
  email                     varchar(255) not null,
  encrypted_password        varchar(255),
  date_sign_up              timestamp,
  code                      varchar(255),
  name                      varchar(255),
  birthdate                 timestamp,
  contact                   varchar(255),
  constraint uq_teacher_email unique (email),
  constraint pk_teacher primary key (id))
;

create table user (
  id                        bigint not null,
  email                     varchar(255) not null,
  encrypted_password        varchar(255),
  date_sign_up              timestamp,
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id))
;


create table shift_student (
  shift_id                       bigint not null,
  student_id                     bigint not null,
  constraint pk_shift_student primary key (shift_id, student_id))
;

create table student_shift (
  student_id                     bigint not null,
  shift_id                       bigint not null,
  constraint pk_student_shift primary key (student_id, shift_id))
;
create sequence course_seq;

create sequence discipline_seq;

create sequence shift_seq;

create sequence student_seq;

create sequence teacher_seq;

create sequence user_seq;

alter table discipline add constraint fk_discipline_course_1 foreign key (course_id) references course (id) on delete restrict on update restrict;
create index ix_discipline_course_1 on discipline (course_id);
alter table shift add constraint fk_shift_discipline_2 foreign key (discipline_id) references discipline (id) on delete restrict on update restrict;
create index ix_shift_discipline_2 on shift (discipline_id);



alter table shift_student add constraint fk_shift_student_shift_01 foreign key (shift_id) references shift (id) on delete restrict on update restrict;

alter table shift_student add constraint fk_shift_student_student_02 foreign key (student_id) references student (id) on delete restrict on update restrict;

alter table student_shift add constraint fk_student_shift_student_01 foreign key (student_id) references student (id) on delete restrict on update restrict;

alter table student_shift add constraint fk_student_shift_shift_02 foreign key (shift_id) references shift (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists course;

drop table if exists discipline;

drop table if exists shift;

drop table if exists shift_student;

drop table if exists student;

drop table if exists student_shift;

drop table if exists teacher;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists course_seq;

drop sequence if exists discipline_seq;

drop sequence if exists shift_seq;

drop sequence if exists student_seq;

drop sequence if exists teacher_seq;

drop sequence if exists user_seq;

