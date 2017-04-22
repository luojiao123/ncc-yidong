package com.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Stb implements Serializable {

	/** $field: serialVersionUID @TODO: -by fjt*/
	private static final long serialVersionUID = 5243935622427994101L;
	
	private Integer id;
	private String stbNum;
	private String stbMac;
	private String standard;
	private String devicetype;
	private String des;
	private String status;
	private Date createtime;
	private Date modtime;
	private String ph;
	
	
	
	
	
	public Stb() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Stb(Integer id, String stbNum, String stbMac, String standard,
			String devicetype, String des, String status, Date createtime,
			Date modtime, String ph) {
		super();
		this.id = id;
		this.stbNum = stbNum;
		this.stbMac = stbMac;
		this.standard = standard;
		this.devicetype = devicetype;
		this.des = des;
		this.status = status;
		this.createtime = createtime;
		this.modtime = modtime;
		this.ph = ph;
	}
	
	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "STB_NUM",nullable = false, length = 50)
	public String getStbNum() {
		return stbNum;
	}
	public void setStbNum(String stbNum) {
		this.stbNum = stbNum;
	}
	
	@Column(name = "STB_MAC", nullable = false, length = 50)
	public String getStbMac() {
		return stbMac;
	}
	public void setStbMac(String stbMac) {
		this.stbMac = stbMac;
	}
	
	@Column(name = "STANDARD", nullable = false, length = 5)
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	
	@Column(name = "DEVICETYPE" , nullable = false, length = 5)
	public String getDevicetype() {
		return devicetype;
	}
	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}
	
	@Column(name = "DES", nullable = false, length = 200)
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
	@Column(name = "STATUS",nullable = false, length = 5)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATETIME", nullable = false, length = 0 )
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "MODTIME", length = 0)
	public Date getModtime() {
		return modtime;
	}
	public void setModtime(Date modtime) {
		this.modtime = modtime;
	}
	
	@Column(name = "PH", nullable = false, length = 100)
	public String getPh() {
		return ph;
	}
	public void setPh(String ph) {
		this.ph = ph;
	}
	
	
	

}
