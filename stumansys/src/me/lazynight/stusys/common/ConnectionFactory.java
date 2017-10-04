/**
* Filename : 	  ConnectionFactory.java
* Author :   	  yuzhe
* EMail:     	  flowerowl@qq.com
* Site:      	  http://lazynight.me
* Creation time : 下午5:48:21 - 2013-4-29
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
 * 数据库连接工厂
 * 
 * */
public class ConnectionFactory {
	private static Properties prop = new Properties();
	/*数据源*/
	private static DataSource ds = null;
	
	//用来把connection 绑定到当前线程上的变量
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	static{
		try{
			prop.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("jdbc.properties"));
			
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.print("在classpath下没有找到jdbc.properties文件");
			
		}
		//使用c3p0连接池
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
	 * 根据数据库的默认连接参数获取数据库的Connection对象
	 * 
	 * @return 成功，返回Connection对象，否则返回null
	 * 
	 * */
	public static synchronized Connection getConnection(){
		//从当前线程取出连接实例
		Connection conn = tl.get();
		
		if(null == conn){
			//如果当前线程没有Connection的实例
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
	 * 获取事务管理器
	 * 
	 * */
	public static synchronized TransactionManager getTransactionManager(){
		return new TransactionManager(getConnection());
	}
	
	protected static void close(Connection conn) throws DaoException {
		if(conn != null){
			try{
				conn.close();
				tl.remove();//卸载线程绑定
			}catch (SQLException e) {
				throw new DaoException("关闭连接时出现异常",e);
			}
		}
		
	}
}













