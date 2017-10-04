package me.lazynight.stusys.entity;

import java.io.Serializable;

/**
 * Filename : 	  scourse.java
 * Author :   	  yuzhe
 * EMail:     	  flowerowl@qq.com
 * Site:      	  http://lazynight.me
 * Creation time : ÏÂÎç4:25:14 - 2013-4-29
 * Description :
 */
public class Scourse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4752397363056036625L;
	private int stuid;
	private int cid;
	private int grade;
	
	/**
	 * @return the stuid
	 */
	public int getStuid() {
		return stuid;
	}
	/**
	 * @param stuid the stuid to set
	 */
	public void setStuid(int stuid) {
		this.stuid = stuid;
	}
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
	 * @return the grade
	 */
	public int getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
}
