/**
 * Filename : 	  UserDao.java
 * Author :   	  yuzhe
 * EMail:     	  flowerowl@qq.com
 * Site:      	  http://lazynight.me
 * Creation time : 下午5:07:54 - 2013-4-29
 * Description :
 */
package me.lazynight.stusys.dao;

import java.util.List;
import me.lazynight.stusys.entity.User;


public interface UserDao {
	
	boolean insert (User user) throws DaoException;
	
	void delete(String username) throws DaoException;
	
	//登陆验证
	List<User> select(String username, String password) throws DaoException;
	
	//获取全部用户
	List<User> getUserList() throws DaoException;
	
	//查询用户
	List<User> getSelectUser(String username) throws DaoException;
}
