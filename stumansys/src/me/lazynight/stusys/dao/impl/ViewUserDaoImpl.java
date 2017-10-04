/**
* Filename : 	  ViewUserDaoImpl.java
* Author :   	  yuzhe
* EMail:     	  flowerowl@qq.com
* Site:      	  http://lazynight.me
* Creation time : 下午11:04:59 - 2013-5-1
* Description :
*/
package me.lazynight.stusys.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import me.lazynight.stusys.common.ConnectionFactory;
import me.lazynight.stusys.dao.DaoException;
import me.lazynight.stusys.dao.UserDao;
import me.lazynight.stusys.dao.ViewUserDao;
import me.lazynight.stusys.entity.ViewUser;

public class ViewUserDaoImpl implements ViewUserDao{
	private QueryRunner qr = new QueryRunner();
	
	public List<ViewUser> getViewUserList() throws DaoException{
		Connection conn = ConnectionFactory.getConnection();
		List<ViewUser> list = new ArrayList<ViewUser>();
		System.out.println("ViewUserDao-->"+conn);
		
		String sql = "SELECT username, password, priname FROM view_users";
		
		try{
			list = qr.query(conn, sql, new BeanListHandler<ViewUser>(ViewUser.class));
			
		}catch (SQLException e) {
			throw new DaoException("获取用户列表时出现异常",e);
		}
		return list;
	}
	
	//获取指定用户，查询时用
	public List<ViewUser> getSelectViewUser(String username) throws DaoException{
		Connection conn = ConnectionFactory.getConnection();
		List<ViewUser> list = new ArrayList<ViewUser>();
		System.out.println("ViewUserDao-->"+conn);
		String sql="SELECT * FROM view_users WHERE username ='"+username+"'";
		try{
			list = qr.query(conn,sql,new BeanListHandler<ViewUser>(ViewUser.class));
		}catch (SQLException e) {
			throw new DaoException("获取指定用户时出现异常",e);
		}
		return list ;
	}
}
