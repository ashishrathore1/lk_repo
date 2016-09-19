package com.lk.credittemplate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CT_ExcelTableCoord")
public class ExcelTableCoord {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "vid")
	private String vid;

	@Column(name = "tablename")
	private String tableName;

	@Column(name = "rowno")
	private String xcord;

	@Column(name = "colno")
	private String ycord;

	@Column(name = "description")
	private String desc;
	
	@Column(name ="createdDate")
	private Date created;

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getXcord() {
		return xcord;
	}

	public void setXcord(String xcord) {
		this.xcord = xcord;
	}

	public String getYcord() {
		return ycord;
	}

	public void setYcord(String ycord) {
		this.ycord = ycord;
	}

}
