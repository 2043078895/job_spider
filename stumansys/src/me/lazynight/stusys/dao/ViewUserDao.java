/**
* Filename : 	  ViewUserDao.java
* Author :   	  yuzhe
* EMail:     	  flowerowl@qq.com
* Site:      	  http://lazynight.me
* Creation time : 下午11:03:04 - 2013-5-1
* Description :
*/
package me.lazynight.stusys.dao;
import java.util.List;

import me.lazynight.stusys.entity.ViewUser;

public interface ViewUserDao {
	/*
	 * 获取用户视图内容 username , password, priname
	 * 
	 * */
	List<ViewUser> getViewUserList() throws DaoException;
	List<ViewUser> getSelectViewUser(String username) throws DaoException;
}
