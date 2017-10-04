/**
* Filename : 	  CourseDao.java
* Author :   	  yuzhe
* EMail:     	  flowerowl@qq.com
* Site:      	  http://lazynight.me
* Creation time : обнГ5:43:55 - 2013-4-29
* Description :
*/
package me.lazynight.stusys.dao;
import java.util.List;
import me.lazynight.stusys.entity.Course;

public interface CourseDao {
	
	void insert(Course course) throws DaoException;
	
	void delete(int cid) throws DaoException;

	List<Course> getCourseList() throws DaoException;
}
