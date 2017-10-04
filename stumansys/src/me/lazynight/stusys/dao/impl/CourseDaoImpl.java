/**
* Filename : 	  CourseDaoImpl.java
* Author :   	  yuzhe
* EMail:     	  flowerowl@qq.com
* Site:      	  http://lazynight.me
* Creation time : 上午8:19:01 - 2013-4-30
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
import me.lazynight.stusys.dao.CourseDao;
import me.lazynight.stusys.entity.Course;

public class CourseDaoImpl implements CourseDao{
	private QueryRunner qr = new QueryRunner();
	
	public void delete(int stuid) throws DaoException{
		Connection conn = ConnectionFactory.getConnection();
		System.out.println("CourseDao:"+conn);
		String sql = "DELETE FROM Course WHREE CId = ?";
		try{
			qr.update(conn, sql, stuid);
		}catch (SQLException e) {
			throw new DaoException("删除指定Id的课程时出现异常",e);
		}
	}
	
	public void insert(Course course) throws DaoException{
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "INSERT INTO Courses(CId,CName,CTime) VALUES(?,?,?)";
		Object[] params = {course.getCid(),course.getName(),course.getTime()};
		try{
			qr.update(conn, sql, params);
		}catch (SQLException e) {
			throw new DaoException("新增课程时出现异常",e);
		}
	}
	
	public List<Course> getCourseList() throws DaoException{
		List<Course> list = new ArrayList<Course>();
		
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "SELECT * FROM Courses";
		
		try{
			list = qr.query(conn, sql, new BeanListHandler<Course>(Course.class));
		}catch (SQLException e) {
			throw new DaoException("获取课程列表时出现异常",e);
		}
		return list;
	}
	
}
