package com.lk.credittemplate.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;


@Entity
@Table(name="LK_USER_MASTER")
@NamedQueries(
		{
			@NamedQuery(name = "getUserUsingEmail",
					query = "SELECT u FROM UserMaster u WHERE u.email =:email"),
		
			@NamedQuery(name = "getUsersByRole",
					query = "SELECT u FROM UserMaster u join u.roleMaster RoleMaster WHERE RoleMaster.name =:rName")
		}
	)
public class UserMaster 
{
	
	@Id
	@Column(name="U_GUID")
	private String userId;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = RoleMaster.class)
	@JoinColumn(name = "U_ROLE_ID")
	private RoleMaster roleMaster;
	
	
	@Column(name="U_NAME")
	private String name;
	
	@Column(name="U_EMAIL")
	private String email;

	@Column(name="U_CONTACT_NUMBER")
	private String contactNo;
	
	@Column(name="U_PASSWORD")
	private String password;
	
	@Column(name="U_STATUS")
	private String userStatus;
	
	@Column(name = "U_LAST_LOGIN_DATE")
	private Date lastLoginDate;
	
	@Column(name = "U_CREATED_DATE")
	private Date created;
	
	@Column(name = "U_UPDATED_DATE")
	private Date modified;
	
	@Column(name="U_OPERATED_BY")
	private String operatedBy;
	
	@Column(name = "U_OTP")
	private String otp;
	
	
	public UserMaster() {
		if(StringUtils.isEmpty(this.userId)){
			this.userId = UUID.randomUUID().toString();
		}
		
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the contactNo
	 */
	public String getContactNo() {
		return contactNo;
	}

	/**
	 * @param contactNo the contactNo to set
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}



	/**
	 * @return the lastLoginDate
	 */
	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	/**
	 * @param lastLoginDate the lastLoginDate to set
	 */
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the modified
	 */
	public Date getModified() {
		return modified;
	}

	/**
	 * @param modified the modified to set
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * @return the operatedBy
	 */
	public String getOperatedBy() {
		return operatedBy;
	}

	/**
	 * @param operatedBy the operatedBy to set
	 */
	public void setOperatedBy(String operatedBy) {
		this.operatedBy = operatedBy;
	}

	/**
	 * @return the loanMastersSet
	 */
	
	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	/**
	 * @return the otp
	 */
	public String getOtp() {
		return otp;
	}

	/**
	 * @param otp the otp to set
	 */
	public void setOtp(String otp) {
		this.otp = otp;
	}

	/**
	 * @return the roleMaster
	 */
	public RoleMaster getRoleMaster() {
		return roleMaster;
	}

	/**
	 * @param roleMaster the roleMaster to set
	 */
	public void setRoleMaster(RoleMaster roleMaster) {
		this.roleMaster = roleMaster;
	}
	
}
