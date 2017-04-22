package com.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * List entity. @author MyEclipse Persistence Tools
 */
@Entity 
public class List implements java.io.Serializable {
 
	private static final long serialVersionUID = -7160026430405683182L;
	
	// Fields

	private Integer id;
	private String status;
	private String listtype;
	private String title;
	private String type;
	private String des;
	private Date starttime;
	private String flag;
	private Date creattime;
	private Date modtime;
	private String operator;
	private Integer pri;
	private String bturl;
	private String listurl;
	private String remark1;
	private String remark2;
	private Date endTime;

	// Constructors

	/** default constructor */
	public List() {
	}

	/** minimal constructor */
	public List(String status, String listtype, String title, String type,
			String flag, String operator) {
		this.status = status;
		this.listtype = listtype;
		this.title = title;
		this.type = type;
		this.flag = flag;
		this.operator = operator;
	}

	/** full constructor */
	public List(String status, String listtype, String title, String type,
			String des, Date starttime, String flag, Date creattime,
			Date modtime, String operator, Integer pri, String bturl,
			String listurl, String remark1, String remark2, Date endTime) {
		this.status = status;
		this.listtype = listtype;
		this.title = title;
		this.type = type;
		this.des = des;
		this.starttime = starttime;
		this.flag = flag;
		this.creattime = creattime;
		this.modtime = modtime;
		this.operator = operator;
		this.pri = pri;
		this.bturl = bturl;
		this.listurl = listurl;
		this.remark1 = remark1;
		this.remark2 = remark2;
		this.endTime = endTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "LISTTYPE", nullable = false, length = 50)
	public String getListtype() {
		return this.listtype;
	}

	public void setListtype(String listtype) {
		this.listtype = listtype;
	}

	@Column(name = "TITLE", nullable = false, length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "TYPE", nullable = false, length = 50)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "DES", length = 400)
	public String getDes() {
		return this.des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "STARTTIME", length = 0)
	public Date getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	@Column(name = "FLAG", nullable = false, length = 50)
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATTIME", length = 0)
	public Date getCreattime() {
		return this.creattime;
	}

	public void setCreattime(Date creattime) {
		this.creattime = creattime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "MODTIME", length = 0)
	public Date getModtime() {
		return this.modtime;
	}

	public void setModtime(Date modtime) {
		this.modtime = modtime;
	}

	@Column(name = "OPERATOR", nullable = false, length = 50)
	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "PRI")
	public Integer getPri() {
		return this.pri;
	}

	public void setPri(Integer pri) {
		this.pri = pri;
	}

	@Column(name = "BTURL", length = 200)
	public String getBturl() {
		return this.bturl;
	}

	public void setBturl(String bturl) {
		this.bturl = bturl;
	}

	@Column(name = "LISTURL", length = 200)
	public String getListurl() {
		return this.listurl;
	}

	public void setListurl(String listurl) {
		this.listurl = listurl;
	}

	@Column(name = "REMARK1", length = 50)
	public String getRemark1() {
		return this.remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	@Column(name = "REMARK2", length = 50)
	public String getRemark2() {
		return this.remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "END_TIME", length = 0)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}