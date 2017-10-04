/**
* Filename : 	  TransactionManager.java
* Author :   	  yuzhe
* EMail:     	  flowerowl@qq.com
* Site:      	  http://lazynight.me
* Creation time : 下午11:11:57 - 2013-4-29
* Description :
*/
package me.lazynight.stusys.common;

import java.sql.Connection;
import java.sql.SQLException;
import me.lazynight.stusys.dao.DaoException;

/*
 * 事物管理器
 * 
 * */

public class TransactionManager {
	private Connection conn;
	
	protected TransactionManager(Connection conn){
		this.conn = conn;
	}
	
	/*
	 * 开启事务
	 * 
	 * */
	public void beginTransaction() throws DaoException{
		try{
			if(null != conn && !conn.isClosed()){
				conn.setAutoCommit(false);
				//把事务提交方式改为手工提交
			}
		}catch (SQLException e) {
			throw new DaoException("开始事务时出现异常",e);
		}
	}

	/*
	 * 提交事务并关闭连接
	 * 
	 * */
	public void commitAndClose() throws DaoException{
		try{
			conn.commit();
		}catch (SQLException e) {
			throw new DaoException("开启事务时出现异常",e);
		}finally{
			ConnectionFactory.close(conn);
		}
	}
	/*
	 * 回滚并关闭事务
	 * 
	 * */
	public void rollbackAndClose() throws DaoException{
		try{
			conn.rollback();
		}catch (SQLException e) {
			throw new DaoException("回滚事务时出现异常",e);
		}finally{
			ConnectionFactory.close(conn);
		}
	}
}
