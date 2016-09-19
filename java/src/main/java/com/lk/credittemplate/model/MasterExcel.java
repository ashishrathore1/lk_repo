package com.lk.credittemplate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CT_MasterTable")
public class MasterExcel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "appid")
	private String appId;

	@Column(name = "vid")
	private String versionId;
	
	//@Column(name="flag",nullable=false)
	//private boolean flag; 
	
	@Column(name="save_flag",nullable=false)
	private boolean saveflag;

	@Column(name ="createdDate")
	private Date created;

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	public boolean isSaveflag() {
		return saveflag;
	}

	public void setSaveflag(boolean saveflag) {
		this.saveflag = saveflag;
	}

//	public boolean isFlag() {
//		return flag;
//	}

//	public void setFlag(boolean flag) {
//		this.flag = flag;
//	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public static EntityManager entityManager() {
		// TODO Auto-generated method stub
		return null;
	}

}
