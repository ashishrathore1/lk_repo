package com.lk.credittemplate.beans.CAD;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestDataBean {

	private String statusId;
	private int pageCount;
	private String assignee;
	private String type;
	private String searchkey;
	private String filterCAName;
	private String filterLeadSource;
	
	public String getFilterLeadSource() {
		return filterLeadSource;
	}
	public void setFilterLeadSource(String filterLeadSource) {
		this.filterLeadSource = filterLeadSource;
	}
	public String getFilterCAName() {
		return filterCAName;
	}
	public void setFilterCAName(String filterCAName) {
		this.filterCAName = filterCAName;
	}
	
	public String getSearchkey() {
		return searchkey;
	}
	public void setSearchkey(String searchkey) {
		this.searchkey = searchkey;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	
	
}
