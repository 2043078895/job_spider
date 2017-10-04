/**
* Filename : 	  StudentDao.java
* Author :   	  yuzhe
* EMail:     	  flowerowl@qq.com
* Site:      	  http://lazynight.me
* Creation time : обнГ5:24:36 - 2013-4-29
* Description :
*/
package me.lazynight.stusys.dao;
import me.lazynight.stusys.entity.Student;
import java.util.List;

public interface StudentDao {
	
	void insert(Student student) throws DaoException;
	
	void delete(int stuid) throws DaoException;
	
	List<Student> getStudentList() throws DaoException;
}
