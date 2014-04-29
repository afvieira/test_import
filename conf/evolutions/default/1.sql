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

create table GroupTable (
  id                        bigint not null,
  discipline_id             bigint,
  code                      varchar(255),
  description               varchar(255),
  limit_number              integer,
  password_registration     varchar(255),
  closed                    boolean,
  constraint pk_GroupTable primary key (id))
;

create table group_milestone (
  id                        bigint not null,
  group_id                  bigint,
  milestone_id              bigint,
  avaliation                integer,
  path                      varchar(255),
  private_comment           varchar(255),
  public_comment_teacher    varchar(255),
  public_comment_student    varchar(255),
  last_update               timestamp,
  closed                    boolean,
  constraint pk_group_milestone primary key (id))
;

create table group_project (
  id                        bigint not null,
  group_id                  bigint,
  project_id                bigint,
  path                      varchar(255),
  state                     integer,
  constraint ck_group_project_state check (state in (0,1,2)),
  constraint pk_group_project primary key (id))
;

create table milestone (
  id                        bigint not null,
  project_id                bigint,
  code                      varchar(255),
  description               varchar(255),
  number                    integer,
  start_date                timestamp,
  end_date                  timestamp,
  path                      varchar(255),
  percentage                integer,
  constraint pk_milestone primary key (id))
;

create table project (
  id                        bigint not null,
  discipline_id             bigint,
  code                      varchar(255),
  title                     varchar(255),
  description               varchar(255),
  start_date                timestamp,
  end_date                  timestamp,
  creation_date             timestamp,
  constraint pk_project primary key (id))
;

create table shift (
  id                        bigint not null,
  discipline_id             bigint,
  code                      varchar(255),
  description               varchar(255),
  constraint pk_shift primary key (id))
;

create table student_milestone (
  id                        bigint not null,
  student_id                bigint,
  milestone_id              bigint,
  avaliation                integer,
  path                      varchar(255),
  private_comment           varchar(255),
  public_comment_teacher    varchar(255),
  public_comment_student    varchar(255),
  last_update               timestamp,
  closed                    boolean,
  constraint pk_student_milestone primary key (id))
;

create table student_project (
  id                        bigint not null,
  student_id                bigint,
  project_id                bigint,
  avaliation                integer,
  constraint pk_student_project primary key (id))
;

create table user (
  id                        bigint not null,
  code                      varchar(255),
  name                      varchar(255),
  birthdate                 timestamp,
  contact                   varchar(255),
  email                     varchar(255) not null,
  encrypted_password        varchar(255),
  date_sign_up              timestamp,
  user_type                 varchar(255),
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id))
;


create table user_discipline (
  discipline_id                  bigint not null,
  user_id                        bigint not null,
  constraint pk_user_discipline primary key (discipline_id, user_id))
;

create table user_GroupTable (
  user_id                        bigint not null,
  GroupTable_id                  bigint not null,
  constraint pk_user_GroupTable primary key (user_id, GroupTable_id))
;

create table user_shift (
  user_id                        bigint not null,
  shift_id                       bigint not null,
  constraint pk_user_shift primary key (user_id, shift_id))
;
create sequence course_seq;

create sequence discipline_seq;

create sequence GroupTable_seq;

create sequence group_milestone_seq;

create sequence group_project_seq;

create sequence milestone_seq;

create sequence project_seq;

create sequence shift_seq;

create sequence student_milestone_seq;

create sequence student_project_seq;

create sequence user_seq;

alter table discipline add constraint fk_discipline_course_1 foreign key (course_id) references course (id) on delete restrict on update restrict;
create index ix_discipline_course_1 on discipline (course_id);
alter table GroupTable add constraint fk_GroupTable_discipline_2 foreign key (discipline_id) references discipline (id) on delete restrict on update restrict;
create index ix_GroupTable_discipline_2 on GroupTable (discipline_id);
alter table group_milestone add constraint fk_group_milestone_group_3 foreign key (group_id) references GroupTable (id) on delete restrict on update restrict;
create index ix_group_milestone_group_3 on group_milestone (group_id);
alter table group_milestone add constraint fk_group_milestone_milestone_4 foreign key (milestone_id) references milestone (id) on delete restrict on update restrict;
create index ix_group_milestone_milestone_4 on group_milestone (milestone_id);
alter table group_project add constraint fk_group_project_group_5 foreign key (group_id) references GroupTable (id) on delete restrict on update restrict;
create index ix_group_project_group_5 on group_project (group_id);
alter table group_project add constraint fk_group_project_project_6 foreign key (project_id) references project (id) on delete restrict on update restrict;
create index ix_group_project_project_6 on group_project (project_id);
alter table milestone add constraint fk_milestone_project_7 foreign key (project_id) references project (id) on delete restrict on update restrict;
create index ix_milestone_project_7 on milestone (project_id);
alter table project add constraint fk_project_discipline_8 foreign key (discipline_id) references discipline (id) on delete restrict on update restrict;
create index ix_project_discipline_8 on project (discipline_id);
alter table shift add constraint fk_shift_discipline_9 foreign key (discipline_id) references discipline (id) on delete restrict on update restrict;
create index ix_shift_discipline_9 on shift (discipline_id);
alter table student_milestone add constraint fk_student_milestone_student_10 foreign key (student_id) references user (id) on delete restrict on update restrict;
create index ix_student_milestone_student_10 on student_milestone (student_id);
alter table student_milestone add constraint fk_student_milestone_mileston_11 foreign key (milestone_id) references milestone (id) on delete restrict on update restrict;
create index ix_student_milestone_mileston_11 on student_milestone (milestone_id);
alter table student_project add constraint fk_student_project_student_12 foreign key (student_id) references user (id) on delete restrict on update restrict;
create index ix_student_project_student_12 on student_project (student_id);
alter table student_project add constraint fk_student_project_project_13 foreign key (project_id) references project (id) on delete restrict on update restrict;
create index ix_student_project_project_13 on student_project (project_id);



alter table user_discipline add constraint fk_user_discipline_discipline_01 foreign key (discipline_id) references discipline (id) on delete restrict on update restrict;

alter table user_discipline add constraint fk_user_discipline_user_02 foreign key (user_id) references user (id) on delete restrict on update restrict;

alter table user_GroupTable add constraint fk_user_GroupTable_user_01 foreign key (user_id) references user (id) on delete restrict on update restrict;

alter table user_GroupTable add constraint fk_user_GroupTable_GroupTable_02 foreign key (GroupTable_id) references GroupTable (id) on delete restrict on update restrict;

alter table user_shift add constraint fk_user_shift_user_01 foreign key (user_id) references user (id) on delete restrict on update restrict;

alter table user_shift add constraint fk_user_shift_shift_02 foreign key (shift_id) references shift (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists course;

drop table if exists discipline;

drop table if exists user_discipline;

drop table if exists GroupTable;

drop table if exists group_milestone;

drop table if exists group_project;

drop table if exists milestone;

drop table if exists project;

drop table if exists shift;

drop table if exists student_milestone;

drop table if exists student_project;

drop table if exists user;

drop table if exists user_GroupTable;

drop table if exists user_shift;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists course_seq;

drop sequence if exists discipline_seq;

drop sequence if exists GroupTable_seq;

drop sequence if exists group_milestone_seq;

drop sequence if exists group_project_seq;

drop sequence if exists milestone_seq;

drop sequence if exists project_seq;

drop sequence if exists shift_seq;

drop sequence if exists student_milestone_seq;

drop sequence if exists student_project_seq;

drop sequence if exists user_seq;

