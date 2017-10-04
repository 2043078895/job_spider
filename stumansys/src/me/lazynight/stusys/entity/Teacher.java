package me.lazynight.stusys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Filename : 	  teacher.java
 * Author :   	  yuzhe
 * EMail:     	  flowerowl@qq.com
 * Site:      	  http://lazynight.me
 * Creation time : ÏÂÎç3:22:28 - 2013-4-29
 * Description :
 */
public class Teacher implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9172435608924310938L;
	private int id;
	private String name;
	private String sex;
	private Date year;
	private String idcard;
	private String address;
	private String tel;
	private String other;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return the year
	 */
	public Date getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(Date year) {
		this.year = year;
	}
	/**
	 * @return the idcard
	 */
	public String getIdcard() {
		return idcard;
	}
	/**
	 * @param idcard the idcard to set
	 */
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * @return the other
	 */
	public String getOther() {
		return other;
	}
	/**
	 * @param other the other to set
	 */
	public void setOther(String other) {
		this.other = other;
	}
	
	@Override
	public String toString(){
		return "teacher--> [id=" + id + ", name=" + name + ", sex="
				+ sex+", year="+ year+", idcard="+idcard+", address="+address+", tel="+tel+", other= "+other + "]";
	}
}
