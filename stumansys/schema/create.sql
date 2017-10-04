/*
Date:2013/4/24
Author:YUZHE
*/

--权限表
CREATE TABLE Pri(
	PriNo Char(1) NOT NULL PRIMARY KEY,
	PriName VarChar2(10) NOT NULL
);
--用户表
CREATE TABLE Users(
	UserName Char(8) PRIMARY KEY,
	PassWord VarChar2(50) NOT NULL,
	PriNo Char(1) NOT NULL,
	FOREIGN KEY(PriNo) REFERENCES Pri(PriNo)
);
--学生表
CREATE TABLE Students(
	StuId Char(8) CHECK(regexp_like(StuId,'^[0-9]{8}$')) PRIMARY KEY,
	StuName VarChar2(10) NOT NULL,
	StuSex Char(3) CHECK (StuSex in('男','女')) NOT NULL,
	StuYear Date NOT NULL,
	StuIDCard Char(18) CHECK(regexp_like(StuIDCard,'^[0-9]{18}$')) NOT NULL,
	StuAddress VarChar2(50),
	StuTel VarChar(11),
	StuOther VarChar2(100)
);
--教师表
CREATE TABLE Teachers(
	TeaId Char(7) CHECK(regexp_like(TeaId,'^[0-9]{7}$')) PRIMARY KEY,
	TeaName VarChar2(10) NOT NULL,
	TeaSex Char(3) CHECK (TeaSex in('男','女')) NOT NULL,
	TeaYear Date NOT NULL,
	TeaIDCard Char(18) CHECK(regexp_like(TeaIDCard,'^[0-9]{18}$')) NOT NULL,
	TeaAddress VarChar2(50),
	TeaTel VarChar(11),
	TeaOther VarChar2(100)
);
--课程表
CREATE TABLE Course(
	CId Char(4) CHECK(regexp_like(CId,'^[0-9]{4}$')) PRIMARY KEY,
	CName VarChar2(40) NOT NULL,
	CTime Char(3) CHECK(regexp_like(CTime,'^[0-9]{2,3}')) NOT NULL
);
--配课表
CREATE TABLE TCourse(
	TeaId Char(7),
	CId Char(4),
	FOREIGN KEY(TeaId) REFERENCES Teachers(TeaId),
	FOREIGN KEY(CId) REFERENCES Course(CId),
	CONSTRAINT PK_TCId PRIMARY KEY(TeaId,CId)
);
--选课表
CREATE TABLE SCourse(
	StuId Char(8), 
	CId Char(4),
	ScGrade VarChar(3) CHECK(regexp_like(ScGrade,'^[0-9]{2,3}$')) NOT NULL,
	FOREIGN KEY(StuId) REFERENCES Students(StuId),
	FOREIGN KEY(CId) REFERENCES Course(CId),
	CONSTRAINT PK_SCId PRIMARY KEY(StuId,CId)
);

--插入权限表
insert into Pri values(1,'学生');
insert into Pri values(2,'教师');
insert into Pri values(3,'管理员');

--插入用户表
insert into Users values(10000000,'12345',1);
insert into Users values(10000001,'12345',2);
insert into Users values(10000002,'12345',3);

--插入学生表
insert into Students values(20100001 , '于哲' , '男' , to_date('1991-01-10 12:39:49
','YYYY-MM-DD HH:MI:SS') , 371521199108111229 , '北京市' , 10766220212 , '暂无');
insert into Students values(20100002 , '冉令箭' , '男' , to_date('1991-08-10 12:39:49
','YYYY-MM-DD HH:MI:SS') , 371521199108111229 , '北京市' , 12322205921 , '暂无');
insert into Students values(20100003 , '刘超' , '男' , to_date('1991-11-10 12:39:49
','YYYY-MM-DD HH:MI:SS') , 371521199108111229 , '北京市' , 19102010201 , '暂无');

--插入教师表
insert into Teachers values(2010001 , '梁永先' , '男' , to_date('1968-01-21 12:39:49
','YYYY-MM-DD HH:MI:SS') , 371521196801211139 , '北京市' , 18700000000 , '软件系主任');
insert into Teachers values(2010002 , '刘红日' , '男' , to_date('1981-01-11 12:39:49
','YYYY-MM-DD HH:MI:SS') , 371521198101211111 , '北京市' , 18700012200 , '老师');
insert into Teachers values(2010003 , '王敏' , '女' , to_date('1982-01-01 12:39:49
','YYYY-MM-DD HH:MI:SS') , 371521196821211112 , '北京市' , 18700444400 , '老师');
insert into Teachers values(2010004 , '刘杰' , '男' , to_date('1982-01-01 12:39:49
','YYYY-MM-DD HH:MI:SS') , 371521196821212311 , '北京市' , 18711111111 , '老师');
insert into Teachers values(2010005 , '徐辉' , '男' , to_date('1982-01-01 12:39:49
','YYYY-MM-DD HH:MI:SS') , 371521196812311139 , '北京市' , 18702222222 , '老师');
insert into Teachers values(2010006 , '佘欣媛' , '女' , to_date('1982-01-01 12:39:49
','YYYY-MM-DD HH:MI:SS') , 371521196121231239 , '北京市' , 18703454564 , '老师');

--插入课程表
insert into Course values(1000,'高级数据库',45);
insert into Course values(1001,'编译原理',60);
insert into Course values(1002,'模式识别',60);
insert into Course values(1003,'数据挖掘导论',55);
insert into Course values(1004,'人工智能',60);
insert into Course values(1005,'图像处理',50);

--插入配课表
insert into TCourse values(2010001,1000);
insert into TCourse values(2010001,1001);
insert into TCourse values(2010002,1002);
insert into TCourse values(2010004,1003);
insert into TCourse values(2010005,1004);
insert into TCourse values(2010006,1005);

--插入选课表
insert into SCourse values(20100001,1002,70);
insert into SCourse values(20100001,1003,71);
insert into SCourse values(20100001,1004,91);
insert into SCourse values(20100001,1005,92);
insert into SCourse values(20100002,1001,97);
insert into SCourse values(20100002,1000,91);
insert into SCourse values(20100002,1002,91);
insert into SCourse values(20100002,1003,67);
insert into SCourse values(20100003,1002,60);
insert into SCourse values(20100003,1001,78);
insert into SCourse values(20100003,1004,80);
insert into SCourse values(20100003,1005,56);
