package com.lk.credittemplate.beans;


import java.util.Date;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@Configuration
public class PDSocialPresenceBean {
	
	
	
    
    @JsonProperty("values")
	private String values="";
    
	@JsonProperty("remarks")
	private String remarks="";
	
	@JsonProperty("tabName")
	private String tabName="";
	public String getTabName() {
		return tabName;
	}
	public void setTabName(String tabName) {
		this.tabName = tabName;
	}
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setSocialTab(String socialTab) {
		// TODO Auto-generated method stub
		
	}



}
