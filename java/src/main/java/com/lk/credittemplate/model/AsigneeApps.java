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

@Entity
@Table(name = "CT_TrackApplications")
public class AsigneeApps {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Column(name = "status")
	private String status;

	@Column(name = "loanid")
	private String loanId;

	@Column(name = "type")
	private String type;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = UserMaster.class)
	@JoinColumn(name = "assigneeId")
	private UserMaster userMaster;
	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UserMaster getUserMaster() {
		return userMaster;
	}

	public void setUserMaster(UserMaster userMaster) {
		this.userMaster = userMaster;
	}

}
