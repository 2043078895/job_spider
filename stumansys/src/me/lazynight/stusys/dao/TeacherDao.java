/**
* Filename : 	  TeacherDao.java
* Author :   	  yuzhe
* EMail:     	  flowerowl@qq.com
* Site:      	  http://lazynight.me
* Creation time : обнГ5:40:49 - 2013-4-29
* Description :
*/
package me.lazynight.stusys.dao;
import java.util.List;
import me.lazynight.stusys.entity.Teacher;

public interface TeacherDao {
	
	void insert (Teacher teacher) throws DaoException;
	
	void delete (int teaid) throws DaoException;
	
	List<Teacher> getTeacherList() throws DaoException;
}
