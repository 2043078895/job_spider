/**
 * Filename : 	  UserDao.java
 * Author :   	  yuzhe
 * EMail:     	  flowerowl@qq.com
 * Site:      	  http://lazynight.me
 * Creation time : ����5:07:54 - 2013-4-29
 * Description :
 */
package me.lazynight.stusys.dao;

import java.util.List;
import me.lazynight.stusys.entity.User;


public interface UserDao {
	
	boolean insert (User user) throws DaoException;
	
	void delete(String username) throws DaoException;
	
	//��½��֤
	List<User> select(String username, String password) throws DaoException;
	
	//��ȡȫ���û�
	List<User> getUserList() throws DaoException;
	
	//��ѯ�û�
	List<User> getSelectUser(String username) throws DaoException;
}
