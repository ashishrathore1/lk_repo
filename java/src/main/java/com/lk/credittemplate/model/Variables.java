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
@Table(name = "CT_Variables")
@NamedQueries({

		@NamedQuery(name = "getVariables", query = "SELECT vr from Variables vr WHERE vr.masterExcel=(SELECT mt from MasterExcel mt "
				+ "WHERE mt.appId=:appId AND mt.versionId=:version) AND vr.key IN (:keyNames)"),
		@NamedQuery(name = "getLoanAmount", query = "SELECT vr from Variables vr WHERE vr.masterExcel=(SELECT mt from MasterExcel mt "
				+ "WHERE mt.appId=:appId AND mt.versionId=:version) AND vr.key=:keyNames"),
		@NamedQuery(name = "getByKey", query = "SELECT vr from Variables vr WHERE vr.masterExcel=:mex AND vr.key=:key")
		

})

public class Variables {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = MasterExcel.class)
	@JoinColumn(name = "masterid")
	private MasterExcel masterExcel;

	@Column(name = "keyname")
	private String key;

	@Column(name = "value")
	private String val;
	
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
