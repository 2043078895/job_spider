/**
* Filename : 	  ViewUser.java
* Author :   	  yuzhe
* EMail:     	  flowerowl@qq.com
* Site:      	  http://lazynight.me
* Creation time : ÏÂÎç10:58:17 - 2013-5-1
* Description :
*/
package me.lazynight.stusys.entity;

import java.io.Serializable;

public class ViewUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2299667521141000254L;
	private String username;
	private String password;
	private String priname;
	
	
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
	 * @return the priname
	 */
	public String getPriname() {
		return priname;
	}
	/**
	 * @param priname the priname to set
	 */
	public void setPriname(String priname) {
		this.priname = priname;
	}
	
	@Override
	public String toString(){
		return "viewuser--> [username=" + username + ", password=" + password + ", priname="
				+ priname + "]";
	}
	
}
