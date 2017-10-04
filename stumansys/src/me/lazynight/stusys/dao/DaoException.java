package me.lazynight.stusys.dao;
/**
 * Filename : 	  DaoException.java
 * Author :   	  yuzhe
 * EMail:     	  flowerowl@qq.com
 * Site:      	  http://lazynight.me
 * Creation time : обнГ4:55:42 - 2013-4-29
 * Description :
 */
public class DaoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6882252893398677822L;
	
	public DaoException(){
		super();
	}
	
	public DaoException(String message, Throwable cause){
		super(message, cause);
	}
	
	public DaoException(String message){
		super(message);
	}
	
	public DaoException(Throwable cause){
		super(cause);
	}
	
}
