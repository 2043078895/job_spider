/*
Date:2013/4/27
Author:YUZHE
*/

--��ȡѧ�ź���
CREATE OR REPLACE FUNCTION getStudentNo RETURN Char IS 
stuno Char(8);
BEGIN
SELECT Extract(year from sysdate)||seq_stuno.nextval INTO stuno FROM dual;
RETURN stuno;
END getStudentNo;

/*
S
----------------------------------------------------------------
20131012
*/

--��ȡ��ʦ�ź���
CREATE OR REPLACE FUNCTION getTeacherNo RETURN Char IS 
teano Char(7);
BEGIN
SELECT Extract(year from sysdate)||seq_teano.nextval INTO teano FROM dual;
RETURN teano;
END getTeacherNo;

/*
TEANO
----------------------------------------------------------------
2013100
*/

--��ȡ�γ̺ź���
CREATE OR REPLACE FUNCTION getCourseNo RETURN Char IS 
cno Char(7);
BEGIN
SELECT seq_cno.nextval INTO cno FROM dual;
RETURN cno;
END getCourseNo;

/*
CNO
----------------------------------------------------------------
1000
*/