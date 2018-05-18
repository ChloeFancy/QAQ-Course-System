
--Table structure for administrator 管理员表
-- ----------------------------------
create table administrator(
id int auto_increment primary key,
aid varchar(30),
aPassword varchar(40) not null,
aName varchar(8)
);
insert into administrator values('01','123456','liyixuan');
insert into administrator values('02','123456','上海大学');
select * from administrator;

--Table structure for Department 院系关系表
-- -------------------------------

create table department(
id int auto_increment primary key,
did char(2),--系编号
dName char(20) unique not null,--系名称
dOfficePlace char(40),--办公地点
dTelephone char(11)--办公电话
);
insert into department values('01','计算机学院','东区三号楼','65347567');
insert into department values('02','通信学院','东区二号楼','65341234');
insert into department values('03','材料学院','东区四号楼','65347890');
select * from department;

-- Tabel structure for Professional 专业关系表
-- ----------------------------------
create table professional(
id int auto_increment primary key,
pid varchar(7),
pName char(20) unique not null,
did char(2),
constraint fk_professional_department foreign key (did) references department(did)
on delete cascade on update cascade
);
insert into professional values('011','软件','01');
insert into professional values('021','自动化','02');
insert into professional values('022','电子','02');
insert into professional values('031','环境','03');
insert into professional values('032','冶金','03');
select * from professional;

--Table structure for banji 班级关系表
-- --------------------------
create table banji(
id int auto_increment primary key,
cName varchar(30),
cNumber int,
pid varchar(7),
constraint fk_class_professional foreign key (pid) references professional(pid)
on delete cascade on update cascade
);
insert into banji values('1','30','011');
insert into banji values('2','30','011');
insert into banji values('3','30','011');
insert into banji values('4','30','011');
insert into banji values('5','30','021');
insert into banji values('6','30','022');
insert into banji values('7','30','031');
insert into banji values('8','30','032');
select * from banji;


create table student(
id int auto_increment primary key,
sid char(10) primary key,
sPassword varchar(20) not null,
sName varchar(10) not null,
sClassName varchar(30) not null,
sSex varchar(2) default'男' check (sSex in('男','女')),
sBrithday date default null,
pid varchar(7),
constraint fk_student_class foreign key (sClassName) references banji(cName)
on delete cascade on update cascade
);
insert into student values('15120111','123','李明','1','男','1993-03-06','011');
insert into student values('15120112','123','刘晓明','2','男','1992-12-08','011');
insert into student values('15120113','123','张颖','3','女','1993-01-05','011');
insert into student values('15120114','123','刘晶晶','4','女','1994-11-06','011');
insert into student values('15120115','123','刘成刚','5','男','1991-06-07','021');
insert into student values('15120116','123','李二丽','6','女','1993-05-04','022');
insert into student values('15120117','123','张晓峰','7','男','1992-08-16','031');
select * from student;

create table course(
cid varchar(10) primary key,
cName varchar(60) unique,
cCredit int default null,
cTotalHours int
);
insert into course values('08305001','离散数学','4','40');
insert into course values('08305002','数据库原理','4','50');
insert into course values('08305003','数据结构','4','50');
insert into course values('08305004','系统结构','6','60');
insert into course values('08301001','分子物理学','4','40');
insert into course values('08302001','通信学','3','30');
select * from course;

create table score(
academic_year varchar(20) default null,
semester varchar(10) default null,
sid char(10),
cid varchar(10),
usualResults int default null,
examResults int default null,
totalResults int default null,
constraint pk_score primary key(sid,cid),
constraint fk_t_score_stu foreign key(sid)references student (sid),
constraint fk_t_score_cource foreign key(cid) references course (cid)
);
insert into score values('1','1','15120111','08305001','90','80','90');
insert into score values('2','2','15120112','08305002','80','76','87');
insert into score values('2','3','15120113','08305001','60','70','65');
insert into score values('3','1','15120115','08305002','80','90','87');
insert into score values('2','2','15120111','08305002','78','98','89');
insert into score values('3','3','15120114','08305003','90','89','90');
insert into score values('2','1','15120117','08305004','89','78','88');
insert into score values('3','2','15120116','08305001','90','89','90');
insert into score values('1','3','15120111','08305003','78','89','89');
select * from score;


create table myscore(
sid char(10) not null,
openTerm varchar(14) not null,
cid varchar(10) not null,
tid varchar(8) not null,
usualResults int default null,
examResults int default null,
totalResults int default null,
constraint pk_my_score primary key(sid,cid,openTerm,tid),
constraint fk_my_score_stu foreign key(sid)references student (sid),
constraint fk_my_score_cource foreign key(tid,cid,openTerm) references teaching(tid,cid,openTerm)
);

insert into myscore values('15120111', '2017-2018 冬季','08305001','0102', '90','80','90');
insert into myscore values('15120112', '2017-2018 冬季','08305002','0101', '80','76','87');
insert into myscore values('15120113', '2017-2018 冬季','08305001','0102', '60','70','65');
insert into myscore values('15120115', '2017-2018 冬季','08305002','0101', '80','90','87');
insert into myscore values('15120111', '2017-2018 冬季','08305002','0101', '78','98','89');
insert into myscore values('15120114', '2017-2018 冬季','08305003','0102', '90','89','90');
insert into myscore values('15120117', '2018-2019 秋季','08305004','0101', '89','78','88');
insert into myscore values('15120116', '2017-2018 冬季','08305001','0102', '90','89','90');
insert into myscore values('15120111', '2018-2019 秋季','08305003','0102', '78','89','89');


create table teacher(
tid varchar(8) primary key,
tPassword varchar(20) not null,
tName varchar(10) not null,
tSex varchar(2),
tEducation varchar(10) check (tEducation in ('本科','硕士研究生','博士研究生','专科')),
tTitle varchar(10) default null,
did char(2),
constraint fk_teacher_departmenr foreign key(did) references department(did)
);
insert into teacher values('0101','123','陈迪茂','男','本科','副教授','01');
insert into teacher values('0102','123','马小红','女','博士研究生','讲师','01');
insert into teacher values('0201','123','张心颖','女','硕士研究生','教授','02');
insert into teacher values('0103','123','吴宝钢','男','硕士研究生','讲师','01');
select * from teacher;


create table teaching(
openTerm varchar(14),
cid varchar(10),
tid varchar(8),
ttime varchar(12),
constraint pk_teaching primary key(tid,cid,openTerm),
constraint fk_teaching_teacher foreign key(tid) references teacher(tid),
constraint fk_teachign_course foreign key(cid) references course(cid)
);
insert into teaching values('2012-2013 秋季','08305001','0103','星期三 5-8');
insert into teaching values('2012-2013 冬季','08305002','0101','星期三 1-4');
insert into teaching values('2012-2013 冬季','08305002','0102','星期三 1-4');
insert into teaching values('2012-2013 冬季','08305002','0103','星期三 1-4');
insert into teaching values('2012-2013 冬季','08305003','0102','星期五 5-8');
insert into teaching values('2013-2014 秋季','08305004','0101','星期二 1-4');
insert into teaching values('2013-2014 秋季','08305001','0102','星期一 5-8');
insert into teaching values('2013-2014 冬季','08302001','0201','星期一 5-8');
select * from teaching;



DELIMITER $
CREATE TRIGGER modifyGrade AFTER INSERT ON Myscore
  FOR EACH ROW 
  BEGIN
    UPDATE Myscore SET totalResults = (usualResults+examResults)/2;
end$
DELIMITER ;


DROP PROCEDURE IF EXISTS `increaseExamGrades`;
DELIMITER ;;  
CREATE PROCEDURE `databaseExp`.`increaseExamGrades`(in n float)
BEGIN
    update Myscore set examResults = examResults * (1+n);
END
;;
DELIMITER ;


select * from mysql.proc where db = 'databaseExp' and `type` = 'PROCEDURE'
