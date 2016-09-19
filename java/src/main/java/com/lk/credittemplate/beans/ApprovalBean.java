package com.lk.credittemplate.beans;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApprovalBean {

	private String appId;
	private String status;
	private String remarks;
	private String userId;
	private String applicationId;
	private String assignedId;
	private List<String> rejectionReasons;
	
	
	public List<String> getRejectionReasons() {
		return rejectionReasons;
	}

	public void setRejectionReasons(List<String> rejectionReasons) {
		this.rejectionReasons = rejectionReasons;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAssignedId() {
		return assignedId;
	}

	public void setAssignedId(String assignedId) {
		this.assignedId = assignedId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
