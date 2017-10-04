/**
* Filename : 	  TeacherDaoImpl.java
* Author :   	  yuzhe
* EMail:     	  flowerowl@qq.com
* Site:      	  http://lazynight.me
* Creation time : 上午8:18:51 - 2013-4-30
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
import me.lazynight.stusys.dao.TeacherDao;
import me.lazynight.stusys.entity.Teacher;

public class TeacherDaoImpl implements TeacherDao{
	private QueryRunner qr = new QueryRunner();
	
	public void delete(int teaid) throws DaoException{
		Connection conn = ConnectionFactory.getConnection();
		System.out.println("TeacherDao:"+conn);
		String sql = "DELETE FROM Teacher WHREE TeaId = ?";
		try{
			qr.update(conn, sql, teaid);
		}catch (SQLException e) {
			throw new DaoException("删除指定Id的老师时出现异常",e);
		}
	}
	
	public void insert(Teacher teacher) throws DaoException{
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "INSERT INTO Teachers(TeaId,TeaName,TeaSex,TeaYear,TeaIDCard" +
					",TeaAddress,TeaTel,TeaOther) VALUES(?,?,?,?,?,?,?,?)";
		Object[] params = {teacher.getId(),teacher.getName(),teacher.getSex(),
							teacher.getYear(),teacher.getIdcard(),
							teacher.getAddress(),teacher.getTel(),teacher.getOther()};
		try{
			qr.update(conn, sql, params);
		}catch (SQLException e) {
			throw new DaoException("新增老师时出现异常",e);
		}
	}
	
	public List<Teacher> getTeacherList() throws DaoException{
		List<Teacher> list = new ArrayList<Teacher>();
		
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "SELECT TeaId, TeaName, TeaSex, TeaYear, TeaIDCard, " +
				"TeaAddress, TeaTel, TeaOther FROM Teachers";
		
		try{
			list = qr.query(conn, sql, new BeanListHandler<Teacher>(Teacher.class));
			System.out.println(list.size());
		}catch (SQLException e) {
			throw new DaoException("获取老师列表时出现异常",e);
		}
		return list;
	}
	
}
