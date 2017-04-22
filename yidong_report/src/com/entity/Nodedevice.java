package com.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id; 
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

/**
 * Nodedevice entity. @author MyEclipse Persistence Tools
 */
@Entity 
public class Nodedevice implements java.io.Serializable {
 
	private static final long serialVersionUID = 8876551359843159965L;
	// Fields

	private Integer id;
	private Integer deviceid; 
	private Node node;
	private String activestatus;
	private String status;
	private String devicetype;
	private Date createtime;
	private Date requesttime; 

	// Constructors

	/** default constructor */
	public Nodedevice() {
	}

	/** minimal constructor */
	public Nodedevice(String activestatus, String status, String devicetype,
			Date createtime) {
		this.activestatus = activestatus;
		this.status = status;
		this.devicetype = devicetype;
		this.createtime = createtime;
	}

	/** full constructor */ 
	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public Nodedevice(Integer id, Integer deviceid, Node node,
			String activestatus, String status, String devicetype,
			Date createtime, Date requesttime) {
		super();
		this.id = id;
		this.deviceid = deviceid;
		this.node = node;
		this.activestatus = activestatus;
		this.status = status;
		this.devicetype = devicetype;
		this.createtime = createtime;
		this.requesttime = requesttime;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "DEVICEID")
	public Integer getDeviceid() {
		return this.deviceid;
	}

	public void setDeviceid(Integer deviceid) {
		this.deviceid = deviceid;
	}
 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	@ForeignKey(name = "NODE")
	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	@Column(name = "ACTIVESTATUS", nullable = false, length = 5)
	public String getActivestatus() {
		return this.activestatus;
	}

	public void setActivestatus(String activestatus) {
		this.activestatus = activestatus;
	}

	@Column(name = "STATUS", nullable = false, length = 5)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "DEVICETYPE", nullable = false, length = 5)
	public String getDevicetype() {
		return this.devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATETIME", nullable = false, length = 0)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REQUESTTIME", length = 0)
	public Date getRequesttime() {
		return this.requesttime;
	}

	public void setRequesttime(Date requesttime) {
		this.requesttime = requesttime;
	}

 
}