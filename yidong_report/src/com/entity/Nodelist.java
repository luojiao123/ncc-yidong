package com.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

/**
 * Nodelist entity. @author MyEclipse Persistence Tools
 */
@Entity
public class Nodelist implements java.io.Serializable {
  
	private static final long serialVersionUID = -5517841609802079300L;
	
	// Fields

	private Integer id;
	private Node node;
	private List list; 
	private String flag;
	private String status;
	private Date createtime;
	private Date receivetime;
	private Date modtime;

	// Constructors

	/** default constructor */
	public Nodelist() {
	}

	/** minimal constructor */
	public Nodelist(String flag, String status) {
		this.flag = flag;
		this.status = status;
	}

	/** full constructor */ 
	public Nodelist(Integer id, Node node, List list, String flag,
			String status, Date createtime, Date receivetime, Date modtime) {
		super();
		this.id = id;
		this.node = node;
		this.list = list;
		this.flag = flag;
		this.status = status;
		this.createtime = createtime;
		this.receivetime = receivetime;
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
 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	@ForeignKey(name = "NODE")
	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	@ForeignKey(name = "LIST")
	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	@Column(name = "FLAG", nullable = false, length = 5)
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "STATUS", nullable = false, length = 5)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATETIME", length = 0)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RECEIVETIME", length = 0)
	public Date getReceivetime() {
		return this.receivetime;
	}

	public void setReceivetime(Date receivetime) {
		this.receivetime = receivetime;
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