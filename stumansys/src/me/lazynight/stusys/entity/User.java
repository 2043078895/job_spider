package me.lazynight.stusys.entity;

import java.io.Serializable;

import me.lazynight.stusys.service.ServiceFacade;

/**
 * Filename : 	  users.java
 * Author :   	  yuzhe
 * EMail:     	  flowerowl@qq.com
 * Site:      	  http://lazynight.me
 * Creation time : ÏÂÎç3:21:59 - 2013-4-29
 * Description :
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 593223264132150530L;
	private String username;
	private String password;
	private int prino;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the prino
	 */
	public int getPrino() {
		return prino;
	}
	/**
	 * @param prino the prino to set
	 */
	public void setPrino(int prino) {
		this.prino = prino;
	}
	
	@Override
	public String toString(){
		return "user--> [username=" + username + ", password=" + password + ", prino="
				+ prino + "]";
	}

	
}
