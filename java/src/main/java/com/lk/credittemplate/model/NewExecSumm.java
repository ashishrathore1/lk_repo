package com.lk.credittemplate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "CT_ExecSumm")
@NamedQueries(
{
@NamedQuery(name = "getDataByAppIdAndTable",
query = "SELECT cr FROM NewExecSumm AS cr WHERE cr.masterExcel=:mid AND cr.tableName LIKE :table"),
@NamedQuery(name = "getDataByAppIdAndKey",
query = "SELECT cr FROM NewExecSumm AS cr WHERE cr.masterExcel=:mid AND cr.key LIKE :key")
})
public class NewExecSumm {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@Column(name = "keyname")
	private String key;
	
	@Column(name = "val1")
	private String val1;
	
	@Column(name = "val2")
	private String val2;
	
	@Column(name = "tablename")
	private String tableName;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = MasterExcel.class)
	@JoinColumn(name = "masterid")
	private MasterExcel masterExcel;
	
	@Column(name ="createdDate")
	private Date created;

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}



	public MasterExcel getMasterExcel() {
		return masterExcel;
	}
	public void setMasterExcel(MasterExcel masterExcel) {
		this.masterExcel = masterExcel;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getVal1() {
		return val1;
	}
	public void setVal1(String val1) {
		this.val1 = val1;
	}
	public String getVal2() {
		return val2;
	}
	public void setVal2(String val2) {
		this.val2 = val2;
	}
	
	
}
