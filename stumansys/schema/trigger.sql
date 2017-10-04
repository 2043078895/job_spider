/*
Date:2013/4/28
Author:YUZHE
*/

--创建日志表
CREATE TABLE log_grade(
	logid NUMBER(10) NOT NULL,
	content VARCHAR2(100) NOT NULL,
	logtime DATE DEFAULT SYSDATE NOT NULL,
	userid NUMBER(10) NOT NULL,
	CONSTRAINT cst_log_grade PRIMARY KEY(logid)
);
--创建日志序列
CREATE SEQUENCE seq_log 
INCREMENT BY 1
START WITH 1
MAXVALUE 1.0E28
MINVALUE 1
NOCYCLE
CACHE 20
NOORDER;

--创建触发器
CREATE OR REPLACE TRIGGER tri_grade
BEFORE UPDATE OF ScGrade ON SCourse
FOR EACH ROW DECLARE
logtext log_grade.content%TYPE;
BEGIN
	IF :OLD.ScGrade <> :NEW.ScGrade THEN
		logtext:=CONCAT(logtext,'成绩:');
		logtext:=CONCAT(logtext,REPLACE(:OLD.ScGrade,' ',''));
		logtext:=CONCAT(logtext,'----->');
		logtext:=CONCAT(logtext,REPLACE(:NEW.ScGrade,' ',''));
		logtext:=CONCAT(logtext,';');
	END IF;
	IF LENGTHB(logtext) > 1 THEN
		INSERT INTO log_grade(logid, content, logtime, userid)
		VALUES(seq_log.nextval, logtext, sysdate, 1);
	END IF;
END;

/*

     LOGID			CONTENT				LOGTIME            USERID
-------------------------------------------------------------------
     21				成绩:90----->91;	27-4月 -13         1
*/

