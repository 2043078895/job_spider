package me.lazynight.stusys.entity;

import java.io.Serializable;

/**
 * Filename : 	  course.java
 * Author :   	  yuzhe
 * EMail:     	  flowerowl@qq.com
 * Site:      	  http://lazynight.me
 * Creation time : ÏÂÎç3:29:39 - 2013-4-29
 * Description :
 */
public class Course implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3121436448145670591L;
	private int cid;
	private String name;
	private int time;
	
	/**
	 * @return the cid
	 */
	public int getCid() {
		return cid;
	}
	/**
	 * @param cid the cid to set
	 */
	public void setCid(int cid) {
		this.cid = cid;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}

}
