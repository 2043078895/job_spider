/**
* Filename : 	  StudentDaoImpl.java
* Author :   	  yuzhe
* EMail:     	  flowerowl@qq.com
* Site:      	  http://lazynight.me
* Creation time : 上午12:03:39 - 2013-4-30
* Description :
*/
package me.lazynight.stusys.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import me.lazynight.stusys.common.ConnectionFactory;
import me.lazynight.stusys.dao.DaoException;
import me.lazynight.stusys.dao.StudentDao;
import me.lazynight.stusys.entity.Student;

public class StudentDaoImpl implements StudentDao{
	private QueryRunner qr = new QueryRunner();
	
	public void delete(int stuid) throws DaoException{
		Connection conn = ConnectionFactory.getConnection();
		System.out.println("StudentDao:"+conn);
		String sql = "DELETE FROM Student WHREE SId = ?";
		try{
			qr.update(conn, sql, stuid);
		}catch (SQLException e) {
			throw new DaoException("删除指定Id的学生时出现异常",e);
		}
	}
	
	public void insert(Student student) throws DaoException{
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "INSERT INTO Students(SId,StuName,StuSex,StuYear,StuIDCard" +
					",StuAddress,StuTel,StuOther) VALUES(?,?,?,?,?,?,?,?,?)";
		Object[] params = {student.getId(),student.getName(),student.getSex(),student.getYear(),student.getIdcard()
							,student.getAddress(),student.getTel(),student.getOther()};
		try{
			qr.update(conn, sql, params);
		}catch (SQLException e) {
			throw new DaoException("新增学生时出现异常",e);
		}
	}
	
	public List<Student> getStudentList() throws DaoException{
		List<Student> list = new ArrayList<Student>();
		
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "SELECT * FROM Students";
		
		try{
			list = qr.query(conn, sql, new BeanListHandler<Student>(Student.class));
		}catch (SQLException e) {
			throw new DaoException("获取学生列表时出现异常",e);
		}
		return list;
	}
	
}
