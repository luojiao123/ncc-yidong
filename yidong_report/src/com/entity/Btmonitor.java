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
 * Btmonitor entity. @author MyEclipse Persistence Tools
 */
@Entity 
public class Btmonitor implements java.io.Serializable {
 
	/** $field: serialVersionUID @TODO: -by fjt*/
	private static final long serialVersionUID = 630330063252688196L;
	
	// Fields 
	private Integer id;
	private String flag;
	private String content;
	private String status;
	private Date createtime; 
	private Nodelist nodelist;
	private Date modtime;

	// Constructors

	/** default constructor */
	public Btmonitor() {
	}

	/** minimal constructor */
	public Btmonitor(String flag, String content, String status, Date createtime) {
		this.flag = flag;
		this.content = content;
		this.status = status;
		this.createtime = createtime;
	}

	/** full constructor */
	public Btmonitor(Integer id, String flag, String content, String status,
			Date createtime, Nodelist nodelist, Date modtime) {
		super();
		this.id = id;
		this.flag = flag;
		this.content = content;
		this.status = status;
		this.createtime = createtime;
		this.nodelist = nodelist;
		this.modtime = modtime;
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

	@Column(name = "FLAG", nullable = false, length = 5)
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "CONTENT", nullable = false, length = 80)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "STATUS", nullable = false, length = 5)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATETIME", nullable = false, length = 0)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
  
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	@ForeignKey(name = "NODELIST")
	public Nodelist getNodelist() {
		return nodelist;
	}

	public void setNodelist(Nodelist nodelist) {
		this.nodelist = nodelist;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "MODTIME", length = 0)
	public Date getModtime() {
		return this.modtime;
	}

	public void setModtime(Date modtime) {
		this.modtime = modtime;
	}

}