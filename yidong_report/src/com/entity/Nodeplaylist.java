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
import org.springframework.format.annotation.DateTimeFormat;
 
@Entity
public class Nodeplaylist implements java.io.Serializable {
 
	private static final long serialVersionUID = -227219409025337864L;

	// Fields

	private Integer id;
	private Nodelist nodelist; 
	private String filename;
	private String artist;
	private String stbNum;
	private Date createtime;  

	// Constructors

	/** default constructor */
	public Nodeplaylist() {
	}
  
 
	public Nodeplaylist(Integer id, Nodelist nodelist, String filename,
			String artist, String stbNum, Date createtime) {
		super();
		this.id = id;
		this.nodelist = nodelist;
		this.filename = filename;
		this.artist = artist;
		this.stbNum = stbNum;
		this.createtime = createtime;
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
	@ForeignKey(name = "NODELIST")
	public Nodelist getNodelist() {
		return nodelist;
	}
 
	public void setNodelist(Nodelist nodelist) {
		this.nodelist = nodelist;
	}
 
	@Column(name = "FILENAME",nullable = false,length = 200)
	public String getFilename() {
		return filename;
	}

	
	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "ARTIST",nullable = false,length = 100)
	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	@Column(name = "STBNUM",nullable = false,length = 50)
	public String getStbNum() {
		return stbNum;
	}

	public void setStbNum(String stbNum) {
		this.stbNum = stbNum;
	}
   
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATETIME", length = 0)
	public Date getCreatetime() {
		return this.createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	 
 

}