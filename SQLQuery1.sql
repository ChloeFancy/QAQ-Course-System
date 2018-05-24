
--Table structure for administrator ����Ա��
-- ----------------------------------
create table administrator(
id int auto_increment primary key,
aid varchar(30),
aPassword varchar(40) not null,
aName varchar(8)
);
insert into administrator values('01','123456','liyixuan');
insert into administrator values('02','123456','�Ϻ���ѧ');
select * from administrator;

--Table structure for Department Ժϵ��ϵ��
-- -------------------------------

create table department(
did char(2) primary key,--ϵ���
dName char(20) unique not null,--ϵ����
dOfficePlace char(40),--�칫�ص�
dTelephone char(11)--�칫�绰
);
insert into department values('01','�����ѧԺ','��������¥','65347567');
insert into department values('02','ͨ��ѧԺ','��������¥','65341234');
insert into department values('03','����ѧԺ','�����ĺ�¥','65347890');
select * from department;

-- Tabel structure for Professional רҵ��ϵ��
-- ----------------------------------
create table professional(
pid varchar(7) primary key,
pName char(20) unique not null,
did char(2),
constraint fk_professional_department foreign key (did) references department(did)
on delete cascade on update cascade
);
insert into professional values('011','���','01');
insert into professional values('021','�Զ���','02');
insert into professional values('022','����','02');
insert into professional values('031','����','03');
insert into professional values('032','ұ��','03');
select * from professional;

--Table structure for banji �༶��ϵ��
-- --------------------------
create table banji(
cName varchar(30) primary key,
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
sid char(10) primary key,
sPassword varchar(20) not null,
sName varchar(10) not null,
sClassName varchar(30) not null,
sSex varchar(2) default'��' check (sSex in('��','Ů')),
sBrithday date default null,
pid varchar(7),
constraint fk_student_class foreign key (sClassName) references banji(cName)
on delete cascade on update cascade
);
insert into student values('15120111','123','����','1','��','1993-03-06','011');
insert into student values('15120112','123','������','2','��','1992-12-08','011');
insert into student values('15120113','123','��ӱ','3','Ů','1993-01-05','011');
insert into student values('15120114','123','������','4','Ů','1994-11-06','011');
insert into student values('15120115','123','���ɸ�','5','��','1991-06-07','021');
insert into student values('15120116','123','�����','6','Ů','1993-05-04','022');
insert into student values('15120117','123','������','7','��','1992-08-16','031');
select * from student;

create table course(
cid varchar(10) primary key,
cName varchar(60) unique,
cCredit int default null,
cTotalHours int
);
insert into course values('08305001','��ɢ��ѧ','4','40');
insert into course values('08305002','���ݿ�ԭ��','4','50');
insert into course values('08305003','���ݽṹ','4','50');
insert into course values('08305004','ϵͳ�ṹ','6','60');
insert into course values('08301001','��������ѧ','4','40');
insert into course values('08302001','ͨ��ѧ','3','30');
select * from course;


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

insert into myscore values('15120111', '2018-2019 ����','08305001','0102', '90','80','90');
insert into myscore values('15120112', '2018-2019 ����','08305002','0101', '80','76','87');
insert into myscore values('15120113', '2018-2019 ����','08305001','0102', '60','70','65');
insert into myscore values('15120115', '2018-2019 ����','08305002','0101', '80','90','87');
insert into myscore values('15120111', '2018-2019 ����','08305002','0101', '78','98','89');
insert into myscore values('15120114', '2018-2019 ����','08305003','0102', '90','89','90');
insert into myscore values('15120117', '2018-2019 �＾','08305004','0101', '89','78','88');
insert into myscore values('15120116', '2018-2019 ����','08305001','0102', '90','89','90');
insert into myscore values('15120111', '2018-2019 �＾','08305003','0102', '78','89','89');

create table teacher(
tid varchar(8) primary key,
tPassword varchar(20) not null,
tName varchar(10) not null,
tSex varchar(2),
tEducation varchar(10) check (tEducation in ('����','˶ʿ�о���','��ʿ�о���','ר��')),
tTitle varchar(10) default null,
did char(2),
constraint fk_teacher_departmenr foreign key(did) references department(did)
);
insert into teacher values('0101','123','�µ�ï','��','����','������','01');
insert into teacher values('0102','123','��С��','Ů','��ʿ�о���','��ʦ','01');
insert into teacher values('0201','123','����ӱ','Ů','˶ʿ�о���','����','02');
insert into teacher values('0103','123','�ⱦ��','��','˶ʿ�о���','��ʦ','01');






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
insert into teaching values('2012-2013 �＾','08305001','0103','������ 5-8');
insert into teaching values('2012-2013 ����','08305002','0101','������ 1-4');
insert into teaching values('2012-2013 ����','08305002','0102','������ 1-4');
insert into teaching values('2012-2013 ����','08305002','0103','������ 1-4');
insert into teaching values('2012-2013 ����','08305003','0102','������ 5-8');
insert into teaching values('2013-2014 �＾','08305004','0101','���ڶ� 1-4');
insert into teaching values('2013-2014 �＾','08305001','0102','����һ 5-8');
insert into teaching values('2013-2014 ����','08302001','0201','����һ 5-8');
select * from teaching;


//���Ƽ����µ����룬���ܼ���

DELIMITER $
CREATE TRIGGER encodeTPassword before INSERT ON teacher
  FOR EACH ROW 
  BEGIN
    SET NEW.tPassword = md5(New.tPassword);
end$
DELIMITER ;

DELIMITER $
CREATE TRIGGER encodeSPassword before INSERT ON student
  FOR EACH ROW 
  BEGIN
    SET NEW.sPassword = md5(New.sPassword);
end$
DELIMITER ;

DELIMITER $
CREATE TRIGGER encodeAPassword before INSERT ON administrator
  FOR EACH ROW 
  BEGIN
    SET NEW.aPassword = md5(New.aPassword);
end$
DELIMITER ;



//�޸��µ����룬���ܼ���

DELIMITER $
CREATE TRIGGER encodeTPasswordWhenUpdate before update ON teacher
  FOR EACH ROW 
  BEGIN
    SET NEW.tPassword = md5(New.tPassword);
end$
DELIMITER ;

DELIMITER $
CREATE TRIGGER encodeSPasswordWhenUpdate before update ON student
  FOR EACH ROW 
  BEGIN
    SET NEW.sPassword = md5(New.sPassword);
end$
DELIMITER ;

DELIMITER $
CREATE TRIGGER encodeAPasswordWhenUpdate before update ON administrator
  FOR EACH ROW 
  BEGIN
    SET NEW.aPassword = md5(New.aPassword);
end$
DELIMITER ;


//others

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
