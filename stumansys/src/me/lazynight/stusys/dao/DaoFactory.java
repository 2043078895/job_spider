/**
* Filename : 	  DaoFactory.java
* Author :   	  yuzhe
* EMail:     	  flowerowl@qq.com
* Site:      	  http://lazynight.me
* Creation time : ÏÂÎç1:41:36 - 2013-4-30
* Description :
*/
package me.lazynight.stusys.dao;

import java.io.IOException;
import java.util.Properties;

public class DaoFactory {
	private static Properties prop = new Properties();
	
	static{
		try{
			prop.load(Thread.currentThread()
					.getContextClassLoader()
					.getResourceAsStream("dao.properties"));
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static <T> T getInstance(String daoName,Class<T> interfaceType){
		T obj = null;
		try{
			obj = interfaceType.cast(Class.forName(prop.getProperty(daoName)).newInstance());
		}catch (InstantiationException e) {
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
