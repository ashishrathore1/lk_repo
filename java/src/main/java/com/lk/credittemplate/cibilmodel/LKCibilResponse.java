/**
 * 
 */
package com.lk.credittemplate.cibilmodel;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * 
 * @author prashanth
 *
 */
@Entity
@Table(name="LK_CIBIL_RESPONSE")
@NamedQueries(
		{
			@NamedQuery(name = "getLKCibilResponse",
				query = "SELECT cr FROM LKCibilResponse AS cr WHERE cr.applicationID=:applicationID AND cr.directorId =:directorId ORDER BY cr.createdTime DESC "),
			@NamedQuery(name = "getLKCibilResponseByAppId",
				query = "SELECT cr FROM LKCibilResponse AS cr WHERE cr.applicationID=:applicationID ORDER BY cr.createdTime DESC"),
			@NamedQuery(name = "getLKCibilResponseDirectorsByAppId",
			query = "SELECT distinct(cr.directorId) FROM LKCibilResponse AS cr WHERE cr.applicationID=:applicationID AND cr.directorId IS NOT NULL")
		})
public class LKCibilResponse  {

	
	@Id
	@Column(name="RESPONSE_ID")
	private String responseId;
	
	@Column(name="APPLICATION_ID")
	private String applicationID;
	
	@Column(name="DIRECTOR_ID")
	private String directorId;
	
	@Column(name="MEMBER_REF_ID")
	private String memberRefId;
	
	@Column(name="RESPONSE_BY")
	private String responseBy;
	
	@Column(name="RESPONSE_JSON_DATA")
	private String responseJsonData;
	
	@Column(name="RESPONSE_DATA")
	private String responseData;
	
	@Column(name="CREATED_TIME")
	private Date createdTime;
	
	@Column(name="UPDATED_TIME")
	private Date updatedTime;
	
	@Column(name="RESPONSE_STATUS")
	private Boolean responseStatus;
	
	public LKCibilResponse() {
		if(StringUtils.isEmpty(this.responseId)){
			this.responseId = UUID.randomUUID().toString();
		}
		
	}

	/**
	 * @return the responseId
	 */
	public String getResponseId() {
		return responseId;
	}

	/**
	 * @param responseId the responseId to set
	 */
	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}

	/**
	 * @return the applicationID
	 */
	public String getApplicationID() {
		return applicationID;
	}

	/**
	 * @param applicationID the applicationID to set
	 */
	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}

	/**
	 * @return the memberRefId
	 */
	public String getMemberRefId() {
		return memberRefId;
	}

	/**
	 * @param memberRefId the memberRefId to set
	 */
	public void setMemberRefId(String memberRefId) {
		this.memberRefId = memberRefId;
	}

	/**
	 * @return the responseBy
	 */
	public String getResponseBy() {
		return responseBy;
	}

	/**
	 * @param responseBy the responseBy to set
	 */
	public void setResponseBy(String responseBy) {
		this.responseBy = responseBy;
	}

	/**
	 * @return the responseJsonData
	 */
	public String getResponseJsonData() {
		return responseJsonData;
	}

	/**
	 * @param responseJsonData the responseJsonData to set
	 */
	public void setResponseJsonData(String responseJsonData) {
		this.responseJsonData = responseJsonData;
	}

	/**
	 * @return the responseData
	 */
	public String getResponseData() {
		return responseData;
	}

	/**
	 * @param responseData the responseData to set
	 */
	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}

	/**
	 * @return the createdTime
	 */
	public Date getCreatedTime() {
		return createdTime;
	}

	/**
	 * @param createdTime the createdTime to set
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * @return the updatedTime
	 */
	public Date getUpdatedTime() {
		return updatedTime;
	}

	/**
	 * @param updatedTime the updatedTime to set
	 */
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	/**
	 * @return the responseStatus
	 */
	public Boolean getResponseStatus() {
		return responseStatus;
	}

	/**
	 * @param responseStatus the responseStatus to set
	 */
	public void setResponseStatus(Boolean responseStatus) {
		this.responseStatus = responseStatus;
	}

	/**
	 * @return the directorId
	 */
	public String getDirectorId() {
		return directorId;
	}

	/**
	 * @param directorId the directorId to set
	 */
	public void setDirectorId(String directorId) {
		this.directorId = directorId;
	}
	
	

}
