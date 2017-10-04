/*
Date:2013/4/26
Author:YUZHE
*/

--用户视图
CREATE OR REPLACE VIEW view_users
	(username,password,priname)
AS SELECT u.UserName, u.PassWord, p.PriName
FROM Users u, Pri p
WHERE u.PriNo = p.PriNo;

/*
USERNAME         PASSWORD         PRONAME
---------------- ---------------- --------------------
10000000         12345            学生
10000001         12345            教师
10000002         12345            管理员

*/

--配课视图
CREATE OR REPLACE VIEW view_tc
	(teaname,cname)
AS SELECT t.TeaName, c.CName
FROM TCourse tc, Course c, Teachers t
WHERE tc.TeaId = t.TeaId AND c.CId = tc.CId;

/*
TEANAME				CNAME
--------------------------------------------------------------------
梁永先				高级数据库
梁永先				编译原理
刘红日				模式识别
刘杰				数据挖掘导论
徐辉				人工智能
佘欣媛				图像处理
*/

--成绩录入视图
--需要根据当前教师号返回学生成绩，默认返回所有
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
人工智能
20100003         刘超                 80     2010005

人工智能
20100001         于哲                 91     2010005

图像处理
20100003         刘超                 56     2010006


CNAME
----------------------------------------------------------------------------

SID              SNAME                SCGRAD TID
---------------- -------------------- ------ --------------
图像处理
20100001         于哲                 92     2010006

数据挖掘导论
20100001         于哲                 71     2010004

数据挖掘导论
20100002         冉令箭               67     2010004


CNAME
----------------------------------------------------------------------------

SID              SNAME                SCGRAD TID
---------------- -------------------- ------ --------------
模式识别
20100003         刘超                 60     2010002

模式识别
20100001         于哲                 70     2010002

模式识别
20100002         冉令箭               91     2010002


CNAME
----------------------------------------------------------------------------

SID              SNAME                SCGRAD TID
---------------- -------------------- ------ --------------
编译原理
20100003         刘超                 78     2010001

编译原理
20100002         冉令箭               97     2010001

高级数据库
20100002         冉令箭               91     2010001
*/

--成绩统计视图
CREATE OR REPLACE VIEW view_count
	(sid,sname,savg)
AS SELECT s.StuId, s.StuName, avg(sc.ScGrade)
FROM Students s, SCourse sc
WHERE s.StuId = sc.StuId
GROUP BY s.StuId, s.StuName ORDER BY s.StuId ASC;

--学生成绩视图
--需要根据当前学生号返回学生成绩，默认返回所有
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
模式识别
70     20100001

1003
数据挖掘导论
71     20100001

CID
--------
CNAME
-----------------------

SCGRAD SID
------ ----------------

1004
人工智能
91     20100001

1005
图像处理

CID
--------
CNAME
-----------------------

SCGRAD SID
------ ----------------
92     20100001

1001
编译原理
97     20100002

1000

CID
--------
CNAME
-----------------------

SCGRAD SID
------ ----------------
高级数据库
91     20100002

1002
模式识别
91     20100002


CID
--------
CNAME
-----------------------

SCGRAD SID
------ ----------------
1003
数据挖掘导论
67     20100002

1002
模式识别
60     20100003

CID
--------
CNAME
-----------------------

SCGRAD SID
------ ----------------

1001
编译原理
78     20100003

1004
人工智能

CID
--------
CNAME
-----------------------

SCGRAD SID
------ ----------------
80     20100003

1005
图像处理
56     20100003


*/



