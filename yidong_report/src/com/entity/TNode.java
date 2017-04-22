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
 * TNode entity. @author MyEclipse Persistence Tools
 */
@Entity
public class TNode implements java.io.Serializable {

	// Fields

	private Integer id;
	private String market;
	private String city;
	private String code;
	private String status;
	private String note;
	private Date createtime;
	private Date motifytime;
	private String name;
	private String errornote;
	private Integer nodeid;

	// Constructors

	/** default constructor */
	public TNode() {
	}

	/** full constructor */
	public TNode(String market, String city, String code, String status,
			String note, Date createtime, Date motifytime, String name,
			String errornote, Integer nodeid) {
		this.market = market;
		this.city = city;
		this.code = code;
		this.status = status;
		this.note = note;
		this.createtime = createtime;
		this.motifytime = motifytime;
		this.name = name;
		this.errornote = errornote;
		this.nodeid = nodeid;
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

	@Column(name = "MARKET", length = 20)
	public String getMarket() {
		return this.market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	@Column(name = "CITY", length = 20)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "CODE", length = 20)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "NOTE", length = 500)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
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
	@Column(name = "MOTIFYTIME", length = 0)
	public Date getMotifytime() {
		return this.motifytime;
	}

	public void setMotifytime(Date motifytime) {
		this.motifytime = motifytime;
	}

	@Column(name = "NAME", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ERRORNOTE", length = 500)
	public String getErrornote() {
		return this.errornote;
	}

	public void setErrornote(String errornote) {
		this.errornote = errornote;
	}

	@Column(name = "NODEID")
	public Integer getNodeid() {
		return this.nodeid;
	}

	public void setNodeid(Integer nodeid) {
		this.nodeid = nodeid;
	}

}