/**
* Filename : 	  UserDaoImpl.java
* Author :   	  yuzhe
* EMail:     	  flowerowl@qq.com
* Site:      	  http://lazynight.me
* Creation time : 上午8:19:21 - 2013-4-30
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
import me.lazynight.stusys.entity.User;

public class UserDaoImpl implements UserDao{
	private QueryRunner qr = new QueryRunner(true);
	
	//查询指定用户
	public List<User> getSelectUser(String username) throws DaoException{
		Connection conn = ConnectionFactory.getConnection();
		List<User> list = new ArrayList<User>();
		System.out.println("UserDao-->"+conn);
		String sql = "SELECT * FROM Users WHERE UserName ='"+username+"'";
		try{
			list = qr.query(sql, conn, new BeanListHandler<User>(User.class));
		}catch (SQLException e) {
			throw new DaoException("查询指定username的用户时出现异常");
		}
		return list;
	}
	//验证用户登陆
	public List<User> select(String username,String password) throws DaoException{
		
		Connection conn = ConnectionFactory.getConnection();
		List<User> list = new ArrayList<User>();
		System.out.println("UserDao-->"+conn);
		String sql = "SELECT * FROM Users WHERE UserName ='"+username+"' AND PassWord='"+password+"'";

		try{
			list = qr.query(conn, sql, new BeanListHandler<User>(User.class));
		}catch (SQLException e) {
			throw new DaoException("查询指定Id的用户时出现异常",e);
		}
		return list;
	}
	
	//删除用户
	public void delete(String username) throws DaoException{
		Connection conn = ConnectionFactory.getConnection();
		System.out.println("UserDao:"+conn);
		String sql = "DELETE FROM Users WHREE UserName =?";
		try{
			qr.update(conn, sql, username);
		}catch (SQLException e) {
			throw new DaoException("删除指定username的用户时出现异常",e);
		}
	}
	//判断是否插入新用户
	public boolean insert(User user) throws DaoException{
		Connection conn = ConnectionFactory.getConnection();
		boolean isInserted = false;
		String sql = "INSERT INTO Users(UserName,PassWord,PriNo) VALUES(?,?,?)";
		Object[] params = {user.getUsername(),user.getPassword(),user.getPrino()};
		try{
			qr.update(conn, sql, params);
			isInserted = true;
		}catch (SQLException e) {
			throw new DaoException("新增用户时出现异常",e);
		}
		return isInserted;
	}
	
	//获取所有用户
	public List<User> getUserList() throws DaoException{
		List<User> list = new ArrayList<User>();
		
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "SELECT * FROM Users";
		
		try{
			list = qr.query(conn, sql, new BeanListHandler<User>(User.class));
		}catch (SQLException e) {
			throw new DaoException("获取用户列表时出现异常",e);
		}
		return list;
	}
}
