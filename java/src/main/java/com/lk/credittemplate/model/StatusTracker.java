package com.lk.credittemplate.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "LK_STATUS_TRACKER")

@NamedQueries({

		@NamedQuery(name = "findStatusByStatusId", query = "SELECT s FROM StatusTracker s WHERE s.flag=:flag"),
		@NamedQuery(name = "findAllStatus", query = "SELECT s FROM StatusTracker s WHERE s.applicationId IN(:applicationId)"),
		@NamedQuery(name = "getStatusTrackerByAppId", query = "SELECT s FROM StatusTracker s WHERE s.applicationId =:applicationId")
		//@NamedQuery(name="updateStatusTrackerById", query="UPDATE  statusTracker s  WHERE s.applicationId=:applicationId")

})
public class StatusTracker implements Serializable {

	private static final long serialVersionUID = 2805838946323485598L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long statusTrackerID;

	@Column(name = "USER_ID")
	private String userID;

	@Column(name = "APPLICATION_ID", length = 50)
	private String applicationId;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	@Column(name = "IPADDRESS")
	private String ipAddress;

	@Column(name = "STATUS_CHANGE_DATE")
	private Date statusChangeDate;

	@Column(name = "FLAG")
	private Boolean flag;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "SUBSTATUS")
	private String subStatus;

	/**
	 * @return the statusTrackerID
	 */
	public long getStatusTrackerID() {
		return statusTrackerID;
	}

	/**
	 * @param statusTrackerID
	 *            the statusTrackerID to set
	 */
	public void setStatusTrackerID(long statusTrackerID) {
		this.statusTrackerID = statusTrackerID;
	}

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param userID
	 *            the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param string
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate
	 *            the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress
	 *            the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the statusChangeDate
	 */
	public Date getStatusChangeDate() {
		return statusChangeDate;
	}

	/**
	 * @param statusChangeDate
	 *            the statusChangeDate to set
	 */
	public void setStatusChangeDate(Date statusChangeDate) {
		this.statusChangeDate = statusChangeDate;
	}

	/**
	 * @return the flag
	 */
	public Boolean getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the subStatus
	 */
	public String getSubStatus() {
		return subStatus;
	}

	/**
	 * @param subStatus
	 *            the subStatus to set
	 */
	public void setSubStatus(String subStatus) {
		this.subStatus = subStatus;
	}

	/**
	 * @return the applicationId
	 */
	public String getApplicationId() {
		return applicationId;
	}

	/**
	 * @param applicationId
	 *            the applicationId to set
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	
}
