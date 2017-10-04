/**
* Filename : 	  TransactionManager.java
* Author :   	  yuzhe
* EMail:     	  flowerowl@qq.com
* Site:      	  http://lazynight.me
* Creation time : ����11:11:57 - 2013-4-29
* Description :
*/
package me.lazynight.stusys.common;

import java.sql.Connection;
import java.sql.SQLException;
import me.lazynight.stusys.dao.DaoException;

/*
 * ���������
 * 
 * */

public class TransactionManager {
	private Connection conn;
	
	protected TransactionManager(Connection conn){
		this.conn = conn;
	}
	
	/*
	 * ��������
	 * 
	 * */
	public void beginTransaction() throws DaoException{
		try{
			if(null != conn && !conn.isClosed()){
				conn.setAutoCommit(false);
				//�������ύ��ʽ��Ϊ�ֹ��ύ
			}
		}catch (SQLException e) {
			throw new DaoException("��ʼ����ʱ�����쳣",e);
		}
	}

	/*
	 * �ύ���񲢹ر�����
	 * 
	 * */
	public void commitAndClose() throws DaoException{
		try{
			conn.commit();
		}catch (SQLException e) {
			throw new DaoException("��������ʱ�����쳣",e);
		}finally{
			ConnectionFactory.close(conn);
		}
	}
	/*
	 * �ع����ر�����
	 * 
	 * */
	public void rollbackAndClose() throws DaoException{
		try{
			conn.rollback();
		}catch (SQLException e) {
			throw new DaoException("�ع�����ʱ�����쳣",e);
		}finally{
			ConnectionFactory.close(conn);
		}
	}
}
