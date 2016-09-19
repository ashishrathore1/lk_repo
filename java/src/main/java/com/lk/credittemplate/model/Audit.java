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

import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "CT_Audit")
@NamedQuery(name = "findSubmitByLoanId", query = "SELECT s FROM Audit s WHERE s.appId=:appId ORDER BY s.uploadTime desc")


public class Audit {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Column(name = "uploadtime")
	private Date uploadTime;

	@Column(name = "submitflag")
	private boolean submitFlag;

	@Column(name = "loanid")
	private String appId;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = UserMaster.class)
	@JoinColumn(name = "uploadedby")
	private UserMaster userMaster;


	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public UserMaster getUserMaster() {
		return userMaster;
	}

	public void setUserMaster(UserMaster userMaster) {
		this.userMaster = userMaster;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public boolean isSubmitFlag() {
		return submitFlag;
	}

	public void setSubmitFlag(boolean submitFlag) {
		this.submitFlag = submitFlag;
	}

}
