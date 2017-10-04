/*
Date:2013/4/26
Author:YUZHE
*/

--�û���ͼ
CREATE OR REPLACE VIEW view_users
	(username,password,priname)
AS SELECT u.UserName, u.PassWord, p.PriName
FROM Users u, Pri p
WHERE u.PriNo = p.PriNo;

/*
USERNAME         PASSWORD         PRONAME
---------------- ---------------- --------------------
10000000         12345            ѧ��
10000001         12345            ��ʦ
10000002         12345            ����Ա

*/

--�����ͼ
CREATE OR REPLACE VIEW view_tc
	(teaname,cname)
AS SELECT t.TeaName, c.CName
FROM TCourse tc, Course c, Teachers t
WHERE tc.TeaId = t.TeaId AND c.CId = tc.CId;

/*
TEANAME				CNAME
--------------------------------------------------------------------
������				�߼����ݿ�
������				����ԭ��
������				ģʽʶ��
����				�����ھ���
���				�˹�����
������				ͼ����
*/

--�ɼ�¼����ͼ
--��Ҫ���ݵ�ǰ��ʦ�ŷ���ѧ���ɼ���Ĭ�Ϸ�������
CREATE OR REPLACE VIEW view_gi
	(cname,sid,sname,scgrade,tid)
AS SELECT c.Cname, s.StuId, s.StuName, sc.ScGrade, tc.TeaId
FROM Students s, Course c, SCourse sc, TCourse tc
WHERE c.CId = sc.CId AND tc.CId = c.CId AND sc.StuId = s.StuId
ORDER BY c.CName;
/*

CNAME
----------------------------------------------------------------------------

SID              SNAME                SCGRAD TID
---------------- -------------------- ------ --------------
�˹�����
20100003         ����                 80     2010005

�˹�����
20100001         ����                 91     2010005

ͼ����
20100003         ����                 56     2010006


CNAME
----------------------------------------------------------------------------

SID              SNAME                SCGRAD TID
---------------- -------------------- ------ --------------
ͼ����
20100001         ����                 92     2010006

�����ھ���
20100001         ����                 71     2010004

�����ھ���
20100002         Ƚ���               67     2010004


CNAME
----------------------------------------------------------------------------

SID              SNAME                SCGRAD TID
---------------- -------------------- ------ --------------
ģʽʶ��
20100003         ����                 60     2010002

ģʽʶ��
20100001         ����                 70     2010002

ģʽʶ��
20100002         Ƚ���               91     2010002


CNAME
----------------------------------------------------------------------------

SID              SNAME                SCGRAD TID
---------------- -------------------- ------ --------------
����ԭ��
20100003         ����                 78     2010001

����ԭ��
20100002         Ƚ���               97     2010001

�߼����ݿ�
20100002         Ƚ���               91     2010001
*/

--�ɼ�ͳ����ͼ
CREATE OR REPLACE VIEW view_count
	(sid,sname,savg)
AS SELECT s.StuId, s.StuName, avg(sc.ScGrade)
FROM Students s, SCourse sc
WHERE s.StuId = sc.StuId
GROUP BY s.StuId, s.StuName ORDER BY s.StuId ASC;

--ѧ���ɼ���ͼ
--��Ҫ���ݵ�ǰѧ���ŷ���ѧ���ɼ���Ĭ�Ϸ�������
CREATE OR REPLACE VIEW view_grade
	(cid, cname, scgrade, sid)
AS SELECT c.CId, c.CName, sc.ScGrade, s.StuId
FROM Course c, SCourse sc, Students s
WHERE c.CId = sc.CId AND s.StuId = sc.StuId;

/*
CID
--------
CNAME
-----------------------

SCGRAD SID
------ ----------------
1002
ģʽʶ��
70     20100001

1003
�����ھ���
71     20100001

CID
--------
CNAME
-----------------------

SCGRAD SID
------ ----------------

1004
�˹�����
91     20100001

1005
ͼ����

CID
--------
CNAME
-----------------------

SCGRAD SID
------ ----------------
92     20100001

1001
����ԭ��
97     20100002

1000

CID
--------
CNAME
-----------------------

SCGRAD SID
------ ----------------
�߼����ݿ�
91     20100002

1002
ģʽʶ��
91     20100002


CID
--------
CNAME
-----------------------

SCGRAD SID
------ ----------------
1003
�����ھ���
67     20100002

1002
ģʽʶ��
60     20100003

CID
--------
CNAME
-----------------------

SCGRAD SID
------ ----------------

1001
����ԭ��
78     20100003

1004
�˹�����

CID
--------
CNAME
-----------------------

SCGRAD SID
------ ----------------
80     20100003

1005
ͼ����
56     20100003


*/



