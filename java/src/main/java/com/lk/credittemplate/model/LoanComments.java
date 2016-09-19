package com.lk.credittemplate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "LOAN_COMMENTS")
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQueries(
{
@NamedQuery(name = "showCatComments",
query = "SELECT c FROM LoanComments c where c.appId =:appId order by c.createdDate"),
@NamedQuery(name = "showCibilComments",
query = "SELECT c FROM LoanCommentsCibil c where c.appId =:appId and c.directorMaster = :dirId order by c.createdDate")
})
public abstract class LoanComments {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Column(name = "loanid")
	private String appId;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
	
	@Column(name = "TYPE")
	private String type;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = UserMaster.class)
	@JoinColumn(name = "userId")
	private UserMaster userMaster;
	
	protected LoanComments(String type) {
		this.type = type;
	}
	
	public LoanComments(){
		this("STD");
	}
	
	
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public UserMaster getUserMaster() {
		return userMaster;
	}

	public void setUserMaster(UserMaster userMaster) {
		this.userMaster = userMaster;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
