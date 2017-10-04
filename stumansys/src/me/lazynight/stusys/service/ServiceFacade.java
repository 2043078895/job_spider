/**
* Filename : 	  ServiceFacade.java
* Author :   	  yuzhe
* EMail:     	  flowerowl@qq.com
* Site:      	  http://lazynight.me
* Creation time : 下午1:38:44 - 2013-4-30
* Description :
*/
package me.lazynight.stusys.service;

import java.util.List;
import me.lazynight.stusys.common.ConnectionFactory;
import me.lazynight.stusys.common.TransactionManager;
import me.lazynight.stusys.dao.*;
import me.lazynight.stusys.entity.*;

/*
 * 业务层门面 --> 添加事务控制
 * 
 * JDBC事务默认自动提交
 * 
 * */
public class ServiceFacade {
	private StudentDao studentDao = DaoFactory.getInstance("studentDao", StudentDao.class);
	private TeacherDao teacherDao = DaoFactory.getInstance("teacherDao", TeacherDao.class);
	private UserDao userDao = DaoFactory.getInstance("userDao", UserDao.class);
	private CourseDao courseDao = DaoFactory.getInstance("courseDao", CourseDao.class);
	private ViewUserDao viewuserDao = DaoFactory.getInstance("viewuserDao", ViewUserDao.class);

	/*
	 * 查询用户
	 * 
	 * */
	public List<User> selectUser(String username, String password){
		List<User> list = null;
		TransactionManager tx = ConnectionFactory.getTransactionManager();
		try{
			tx.beginTransaction();
			list = userDao.select(username,password);
			tx.commitAndClose();
		}catch (DaoException e) {
			tx.rollbackAndClose();
		}
		return list;
	}
	
	/*
	 * 新增用户
	 * @param user
	 * */
	public boolean insertUser(User user){
		TransactionManager tx = ConnectionFactory.getTransactionManager();
		boolean isInserted = false;
		try{
			tx.beginTransaction();
			userDao.insert(user);
			tx.commitAndClose();
			isInserted = true;
		}catch (DaoException e) {
			tx.rollbackAndClose();
		}
		return isInserted;
	}
	/*
	 * 新增学生
	 * @param student
	 * */
	public void insertStudent(Student student){
		TransactionManager tx = ConnectionFactory.getTransactionManager();
		try{
			tx.beginTransaction();
			studentDao.insert(student);
			tx.commitAndClose();
		}catch (DaoException e) {
			tx.rollbackAndClose();
		}
	}
	/*
	 * 新增教师
	 * @param teacher
	 * */
	public void insertTeacher(Teacher teacher){
		TransactionManager tx = ConnectionFactory.getTransactionManager();
		try{
			tx.beginTransaction();
			teacherDao.insert(teacher);
			tx.commitAndClose();
		}catch (DaoException e) {
			tx.rollbackAndClose();
		}
	}
	/*
	 * 新增课程
	 * @param course
	 * */
	public void insertCourse(Course course){
		TransactionManager tx = ConnectionFactory.getTransactionManager();
		try{
			tx.beginTransaction();
			courseDao.insert(course);
			tx.commitAndClose();
		}catch (DaoException e) {
			tx.rollbackAndClose();
		}
	}
	
	
	/*
	 * 删除指定username的用户
	 * @param username
	 * */
	public void deleteUser(String username){
		TransactionManager tx = ConnectionFactory.getTransactionManager();
		try{
			tx.beginTransaction();
			userDao.delete(username);
			tx.commitAndClose();
		}catch (DaoException e) {
			tx.rollbackAndClose();
		}
	}
	/*
	 * 删除指定Stuid的学生
	 * @param stuid
	 * */
	public void deleteStudent(int stuid){
		TransactionManager tx = ConnectionFactory.getTransactionManager();
		try{
			tx.beginTransaction();
			studentDao.delete(stuid);
			tx.commitAndClose();
		}catch (DaoException e) {
			tx.rollbackAndClose();
		}
	}
	/*
	 * 删除指定Teaid的教师
	 * @param teaid
	 * */
	public void deleteTeacher(int teaid){
		TransactionManager tx = ConnectionFactory.getTransactionManager();
		try{
			tx.beginTransaction();
			teacherDao.delete(teaid);
			tx.commitAndClose();
		}catch (DaoException e) {
			tx.rollbackAndClose();
		}
	}
	/*
	 * 删除指定CId的课程
	 * @param cid
	 * */
	public void deleteCourse(int cid){
		TransactionManager tx = ConnectionFactory.getTransactionManager();
		try{
			tx.beginTransaction();
			courseDao.delete(cid);
			tx.commitAndClose();
		}catch (DaoException e) {
			tx.rollbackAndClose();
		}
	}
	
	
	
	//从用户表查询特定用户
	public List<User> getSelecUsers(String username){
		List<User> list = null;
		TransactionManager tx = ConnectionFactory.getTransactionManager();
		try{
			tx.beginTransaction();
			list = userDao.getSelectUser(username);
			tx.commitAndClose();
		}catch (DaoException e) {
			tx.rollbackAndClose();
		}
		return list;
	}
	//从用户视图查询特定用户
	public List<ViewUser> getSelectViewUser(String username){
		List<ViewUser> list = null;
		TransactionManager tx = ConnectionFactory.getTransactionManager();
		try{
			tx.beginTransaction();
			list = viewuserDao.getSelectViewUser(username);
			tx.commitAndClose();
		}catch (DaoException e) {
			tx.rollbackAndClose();
		}
		return list;
	}
	/*
	 * 获取用户视图列表
	 * 
	 * */
	public List<ViewUser> getViewUserList(){
		List<ViewUser> list = null;
		TransactionManager tx = ConnectionFactory.getTransactionManager();
		try{
			tx.beginTransaction();
			list = viewuserDao.getViewUserList();
			tx.commitAndClose();
		}catch (DaoException e) {
			tx.rollbackAndClose();
		}
		return list;
	}
	/*
	 * 获取所有用户的列表
	 * @return 
	 * */
	public List<User> getUserList(){
		List<User> list = null;
		TransactionManager tx = ConnectionFactory.getTransactionManager();
		try{
			tx.beginTransaction();
			list = userDao.getUserList();
			tx.commitAndClose();
		}catch (DaoException e) {
			e.printStackTrace();
			tx.rollbackAndClose();
		}
		return list;
	}
	/*
	 * 获取所有学生的列表
	 * @return
	 * */
	public List<Student> getStudentList(){
		List<Student> list = null;
		TransactionManager tx = ConnectionFactory.getTransactionManager();
		try{
			tx.beginTransaction();
			list = studentDao.getStudentList();
			tx.commitAndClose();
		}catch (DaoException e) {
			e.printStackTrace();
			tx.rollbackAndClose();
		}
		return list;
	}
	/*
	 * 获取所有教师的列表
	 * @return
	 * */
	public List<Teacher> getTeacherList(){
		List<Teacher> list = null;
		TransactionManager tx = ConnectionFactory.getTransactionManager();
		try{
			tx.beginTransaction();
			list = teacherDao.getTeacherList();
			tx.commitAndClose();
		}catch (DaoException e) {
			e.printStackTrace();
			tx.rollbackAndClose();
		}
		return list;
	}
	/*
	 * 获取所有课程的列表
	 * @return
	 * */
	public List<Course> getCourseList(){
		List<Course> list = null;
		TransactionManager tx = ConnectionFactory.getTransactionManager();
		try{
			tx.beginTransaction();
			list = courseDao.getCourseList();
			tx.commitAndClose();
		}catch (DaoException e) {
			e.printStackTrace();
			tx.rollbackAndClose();
		}
		return list;
	}
}
