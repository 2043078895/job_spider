/**
* Filename : 	  ConnectionFactory.java
* Author :   	  yuzhe
* EMail:     	  flowerowl@qq.com
* Site:      	  http://lazynight.me
* Creation time : ����5:48:21 - 2013-4-29
* Description :
*/
package me.lazynight.stusys.common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.DataSources;
import me.lazynight.stusys.dao.DaoException;

/*
 * ���ݿ����ӹ���
 * 
 * */
public class ConnectionFactory {
	private static Properties prop = new Properties();
	/*����Դ*/
	private static DataSource ds = null;
	
	//������connection �󶨵���ǰ�߳��ϵı���
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	static{
		try{
			prop.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("jdbc.properties"));
			
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.print("��classpath��û���ҵ�jdbc.properties�ļ�");
			
		}
		//ʹ��c3p0���ӳ�
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			DataSource unpooled = DataSources.unpooledDataSource(
					prop.getProperty("url"),
					prop.getProperty("user"),
					prop.getProperty("password"));
			ds = DataSources.pooledDataSource(unpooled);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * �������ݿ��Ĭ�����Ӳ�����ȡ���ݿ��Connection����
	 * 
	 * @return �ɹ�������Connection���󣬷��򷵻�null
	 * 
	 * */
	public static synchronized Connection getConnection(){
		//�ӵ�ǰ�߳�ȡ������ʵ��
		Connection conn = tl.get();
		
		if(null == conn){
			//�����ǰ�߳�û��Connection��ʵ��
			try{
				conn = ds.getConnection();
				tl.set(conn);
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	
	/*
	 * ��ȡ���������
	 * 
	 * */
	public static synchronized TransactionManager getTransactionManager(){
		return new TransactionManager(getConnection());
	}
	
	protected static void close(Connection conn) throws DaoException {
		if(conn != null){
			try{
				conn.close();
				tl.remove();//ж���̰߳�
			}catch (SQLException e) {
				throw new DaoException("�ر�����ʱ�����쳣",e);
			}
		}
		
	}
}













