package com.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Node entity. @author MyEclipse Persistence Tools
 */
@Entity
public class Node implements java.io.Serializable {
	
	private static final long serialVersionUID = -6702065565384823473L;
	// Fields
	public Integer id;
	public String name;
	private String dis;
	private String brand;
	private Integer parentid;
	private String statid;
	private String nodetype;
	private String globalcode;
	private String status;
	private Integer operator;
	private Date createtime;
	private Date modtime;
	public String code;
	private String tradezonenameRsc;
	private Integer scale;
	private String phone;
	private String zipcode;
	private String address;
	private String mailads;
	
	private Set<Nodedevice> nodedeviceSet = new HashSet<Nodedevice>(); 

	// Constructors

	/** default constructor */
	public Node() {
	}

	/** full constructor */
	public Node(String name, String dis, String brand, Integer parentid,
			String statid, String nodetype, String globalcode, String status,
			Integer operator, Date createtime, Date modtime, String code,
			String tradezonenameRsc, Integer scale, String phone,
			String zipcode, String address, String mailads) {
		this.name = name;
		this.dis = dis;
		this.brand = brand;
		this.parentid = parentid;
		this.statid = statid;
		this.nodetype = nodetype;
		this.globalcode = globalcode;
		this.status = status;
		this.operator = operator;
		this.createtime = createtime;
		this.modtime = modtime;
		this.code = code;
		this.tradezonenameRsc = tradezonenameRsc;
		this.scale = scale;
		this.phone = phone;
		this.zipcode = zipcode;
		this.address = address;
		this.mailads = mailads;
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

	@Column(name = "NAME", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DIS", length = 200)
	public String getDis() {
		return this.dis;
	}

	public void setDis(String dis) {
		this.dis = dis;
	}

	@Column(name = "BRAND", length = 20)
	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name = "PARENTID")
	public Integer getParentid() {
		return this.parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	@Column(name = "STATID", length = 200)
	public String getStatid() {
		return this.statid;
	}

	public void setStatid(String statid) {
		this.statid = statid;
	}

	@Column(name = "NODETYPE", length = 5)
	public String getNodetype() {
		return this.nodetype;
	}

	public void setNodetype(String nodetype) {
		this.nodetype = nodetype;
	}

	@Column(name = "GLOBALCODE", length = 50)
	public String getGlobalcode() {
		return this.globalcode;
	}

	public void setGlobalcode(String globalcode) {
		this.globalcode = globalcode;
	}

	@Column(name = "STATUS", length = 5)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "OPERATOR")
	public Integer getOperator() {
		return this.operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
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
	@Column(name = "MODTIME", length = 0)
	public Date getModtime() {
		return this.modtime;
	}

	public void setModtime(Date modtime) {
		this.modtime = modtime;
	}

	@Column(name = "CODE", length = 20)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "TRADEZONENAME_RSC", length = 200)
	public String getTradezonenameRsc() {
		return this.tradezonenameRsc;
	}

	public void setTradezonenameRsc(String tradezonenameRsc) {
		this.tradezonenameRsc = tradezonenameRsc;
	}

	@Column(name = "SCALE")
	public Integer getScale() {
		return this.scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	@Column(name = "PHONE", length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "ZIPCODE", length = 10)
	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Column(name = "ADDRESS", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "MAILADS", length = 50)
	public String getMailads() {
		return this.mailads;
	}

	public void setMailads(String mailads) {
		this.mailads = mailads;
	}
	@OneToMany(mappedBy="node")  
	//@Column(nullable=true) 
	public Set<Nodedevice> getNodedeviceSet() {
		return nodedeviceSet;
	}

	public void setNodedeviceSet(Set<Nodedevice> nodedeviceSet) {
		this.nodedeviceSet = nodedeviceSet;
	}

}