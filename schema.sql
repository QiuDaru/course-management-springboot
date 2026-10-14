<<<<<<< HEAD
create table course (course_id varchar(255) not null, course_name varchar(255) not null, credit integer not null, primary key (course_id)) engine=InnoDB;
create table course_selection (course_id varchar(255) not null, student_id varchar(255) not null, create_time datetime(6) not null, grade integer, remark varchar(255), primary key (course_id, student_id)) engine=InnoDB;
create table student (student_id varchar(8) not null, email varchar(255) not null, name varchar(255) not null, primary key (student_id)) engine=InnoDB;
alter table course_selection add constraint FKl17bso072mkjf3o87itjugoed foreign key (course_id) references course (course_id);
alter table course_selection add constraint FKay7wfmdttc5guq09ddwy06jo6 foreign key (student_id) references student (student_id);
create table course (course_id varchar(255) not null, course_name varchar(255) not null, credit integer not null, primary key (course_id)) engine=InnoDB;
create table course_selection (course_id varchar(255) not null, student_id varchar(255) not null, create_time datetime(6) not null, grade integer, remark varchar(255), primary key (course_id, student_id)) engine=InnoDB;
create table student (student_id varchar(8) not null, email varchar(255) not null, name varchar(255) not null, primary key (student_id)) engine=InnoDB;
alter table course_selection add constraint FKl17bso072mkjf3o87itjugoed foreign key (course_id) references course (course_id);
alter table course_selection add constraint FKay7wfmdttc5guq09ddwy06jo6 foreign key (student_id) references student (student_id);
create table course (course_id varchar(255) not null, course_name varchar(255) not null, credit integer not null, primary key (course_id)) engine=InnoDB;
create table course_selection (course_id varchar(255) not null, student_id varchar(255) not null, create_time datetime(6) not null, grade integer, remark varchar(255), primary key (course_id, student_id)) engine=InnoDB;
create table student (student_id varchar(8) not null, email varchar(255) not null, name varchar(255) not null, primary key (student_id)) engine=InnoDB;
alter table course_selection add constraint FKl17bso072mkjf3o87itjugoed foreign key (course_id) references course (course_id);
alter table course_selection add constraint FKay7wfmdttc5guq09ddwy06jo6 foreign key (student_id) references student (student_id);
create table course (course_id varchar(255) not null, course_name varchar(255) not null, credit integer not null, primary key (course_id)) engine=InnoDB;
create table course_selection (course_id varchar(255) not null, student_id varchar(255) not null, create_time datetime(6) not null, grade integer, remark varchar(255), primary key (course_id, student_id)) engine=InnoDB;
create table student (student_id varchar(8) not null, email varchar(255) not null, name varchar(255) not null, primary key (student_id)) engine=InnoDB;
alter table course_selection add constraint FKl17bso072mkjf3o87itjugoed foreign key (course_id) references course (course_id);
alter table course_selection add constraint FKay7wfmdttc5guq09ddwy06jo6 foreign key (student_id) references student (student_id);
=======
create table Book (id varchar(255) not null, author varchar(255), title varchar(255), primary key (id)) engine=InnoDB;
create table Reader (id varchar(255) not null, email varchar(255), name varchar(255), primary key (id)) engine=InnoDB;
create table ReaderBook (bookId varchar(255) not null, readerId varchar(255) not null, borrowDate datetime(6), primary key (bookId, readerId)) engine=InnoDB;
alter table ReaderBook add constraint FK9tvo1xs8cb3r6ffw9m1sxfl35 foreign key (bookId) references Book (id);
alter table ReaderBook add constraint FKdoyeiwgcg820wqdng8aes50mp foreign key (readerId) references Reader (id);
create table Book (id varchar(255) not null, author varchar(255), title varchar(255), primary key (id)) engine=InnoDB;
create table Reader (id varchar(255) not null, email varchar(255), name varchar(255), primary key (id)) engine=InnoDB;
create table ReaderBook (bookId varchar(255) not null, readerId varchar(255) not null, borrowDate datetime(6), primary key (bookId, readerId)) engine=InnoDB;
alter table ReaderBook add constraint FK9tvo1xs8cb3r6ffw9m1sxfl35 foreign key (bookId) references Book (id);
alter table ReaderBook add constraint FKdoyeiwgcg820wqdng8aes50mp foreign key (readerId) references Reader (id);
>>>>>>> 816cc145ffa90a412a776ce5140ed08304a74382
