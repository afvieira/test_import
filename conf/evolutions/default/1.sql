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

create table general_group (
  id                        bigint not null,
  discipline_id             bigint,
  code                      varchar(255),
  description               varchar(255),
  limit_number              integer,
  password_registration     varchar(255),
  closed                    boolean,
  constraint pk_general_group primary key (id))
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
  created_by_id             bigint,
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

create table general_user (
  id                        bigint not null,
  code                      varchar(255),
  name                      varchar(255),
  birthdate                 timestamp,
  contact                   varchar(255),
  email                     varchar(255) not null,
  encrypted_password        varchar(255),
  date_sign_up              timestamp,
  user_type                 varchar(255),
  constraint uq_general_user_email unique (email),
  constraint pk_general_user primary key (id))
;


create table general_user_discipline (
  discipline_id                  bigint not null,
  general_user_id                bigint not null,
  constraint pk_general_user_discipline primary key (discipline_id, general_user_id))
;

create table general_user_general_group (
  general_group_id               bigint not null,
  general_user_id                bigint not null,
  constraint pk_general_user_general_group primary key (general_group_id, general_user_id))
;

create table general_user_shift (
  general_user_id                bigint not null,
  shift_id                       bigint not null,
  constraint pk_general_user_shift primary key (general_user_id, shift_id))
;
create sequence course_seq;

create sequence discipline_seq;

create sequence general_group_seq;

create sequence group_milestone_seq;

create sequence group_project_seq;

create sequence milestone_seq;

create sequence project_seq;

create sequence shift_seq;

create sequence student_milestone_seq;

create sequence student_project_seq;

create sequence general_user_seq;

alter table discipline add constraint fk_discipline_course_1 foreign key (course_id) references course (id);
create index ix_discipline_course_1 on discipline (course_id);
alter table general_group add constraint fk_general_group_discipline_2 foreign key (discipline_id) references discipline (id);
create index ix_general_group_discipline_2 on general_group (discipline_id);
alter table group_milestone add constraint fk_group_milestone_group_3 foreign key (group_id) references general_group (id);
create index ix_group_milestone_group_3 on group_milestone (group_id);
alter table group_milestone add constraint fk_group_milestone_milestone_4 foreign key (milestone_id) references milestone (id);
create index ix_group_milestone_milestone_4 on group_milestone (milestone_id);
alter table group_project add constraint fk_group_project_group_5 foreign key (group_id) references general_group (id);
create index ix_group_project_group_5 on group_project (group_id);
alter table group_project add constraint fk_group_project_project_6 foreign key (project_id) references project (id);
create index ix_group_project_project_6 on group_project (project_id);
alter table milestone add constraint fk_milestone_project_7 foreign key (project_id) references project (id);
create index ix_milestone_project_7 on milestone (project_id);
alter table project add constraint fk_project_discipline_8 foreign key (discipline_id) references discipline (id);
create index ix_project_discipline_8 on project (discipline_id);
alter table project add constraint fk_project_createdBy_9 foreign key (created_by_id) references general_user (id);
create index ix_project_createdBy_9 on project (created_by_id);
alter table shift add constraint fk_shift_discipline_10 foreign key (discipline_id) references discipline (id);
create index ix_shift_discipline_10 on shift (discipline_id);
alter table student_milestone add constraint fk_student_milestone_student_11 foreign key (student_id) references general_user (id);
create index ix_student_milestone_student_11 on student_milestone (student_id);
alter table student_milestone add constraint fk_student_milestone_mileston_12 foreign key (milestone_id) references milestone (id);
create index ix_student_milestone_mileston_12 on student_milestone (milestone_id);
alter table student_project add constraint fk_student_project_student_13 foreign key (student_id) references general_user (id);
create index ix_student_project_student_13 on student_project (student_id);
alter table student_project add constraint fk_student_project_project_14 foreign key (project_id) references project (id);
create index ix_student_project_project_14 on student_project (project_id);



alter table general_user_discipline add constraint fk_general_user_discipline_di_01 foreign key (discipline_id) references discipline (id);

alter table general_user_discipline add constraint fk_general_user_discipline_ge_02 foreign key (general_user_id) references general_user (id);

alter table general_user_general_group add constraint fk_general_user_general_group_01 foreign key (general_group_id) references general_group (id);

alter table general_user_general_group add constraint fk_general_user_general_group_02 foreign key (general_user_id) references general_user (id);

alter table general_user_shift add constraint fk_general_user_shift_general_01 foreign key (general_user_id) references general_user (id);

alter table general_user_shift add constraint fk_general_user_shift_shift_02 foreign key (shift_id) references shift (id);

# --- !Downs

drop table if exists course cascade;

drop table if exists discipline cascade;

drop table if exists general_user_discipline cascade;

drop table if exists general_group cascade;

drop table if exists general_user_general_group cascade;

drop table if exists group_milestone cascade;

drop table if exists group_project cascade;

drop table if exists milestone cascade;

drop table if exists project cascade;

drop table if exists shift cascade;

drop table if exists student_milestone cascade;

drop table if exists student_project cascade;

drop table if exists general_user cascade;

drop table if exists general_user_shift cascade;

drop sequence if exists course_seq;

drop sequence if exists discipline_seq;

drop sequence if exists general_group_seq;

drop sequence if exists group_milestone_seq;

drop sequence if exists group_project_seq;

drop sequence if exists milestone_seq;

drop sequence if exists project_seq;

drop sequence if exists shift_seq;

drop sequence if exists student_milestone_seq;

drop sequence if exists student_project_seq;

drop sequence if exists general_user_seq;

