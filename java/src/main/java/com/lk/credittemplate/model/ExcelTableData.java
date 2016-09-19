package com.lk.credittemplate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "CT_ExcelTableData")

@NamedQueries(
		{
			@NamedQuery(name = "getByKeyAndTableName",
				query = "SELECT cr FROM ExcelTableData AS cr WHERE cr.masterExcel=:mex AND"
						+ " cr.tname=:tname AND cr.val=:key"),
		}
		
	)

public class ExcelTableData {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = MasterExcel.class)
	@JoinColumn(name = "masterid")
	private MasterExcel masterExcel;

	@Column(name = "value")
	private String val;

	@Column(name = "tablename")
	private String tname;
	
	@Column(name ="createdDate")
	private Date created;

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public MasterExcel getMasterExcel() {
		return masterExcel;
	}

	public void setMasterExcel(MasterExcel masterExcel) {
		this.masterExcel = masterExcel;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

}
