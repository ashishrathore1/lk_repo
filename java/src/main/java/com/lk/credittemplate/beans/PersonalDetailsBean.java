package com.lk.credittemplate.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonalDetailsBean {

	   @JsonProperty("adminId")
	    private String adminId="";
	    @JsonProperty("clsAppID")
	    private String clsAppID="";
	    @JsonProperty("clContractId")
	    private String clContractId="";
	    @JsonProperty("product")
	    private String product="";
	    @JsonProperty("cycle")
	    private String cycle="";
	    @JsonProperty("type")
	    private String type="";
	    @JsonProperty("caseType")
	    private String caseType="";
	    @JsonProperty("appCreatedDate")
	    private String appCreatedDate="";
	    @JsonProperty("dateAssignToAnalyst")
	    private String dateAssignToAnalyst="";
	    @JsonProperty("dateOfAnalysis")
	    private String dateOfAnalysis="";
	    @JsonProperty("analystName")
	    private String analystName="";
	    @JsonProperty("leadSource")
	    private String leadSource="";
	    @JsonProperty("others")
	    private String others="";
	    @JsonProperty("loanPurpose")
	    private String loanPurpose="";
	    @JsonProperty("loanEligible")
	    private String loanEligible="";
	    @JsonProperty("loanApplied")
	    private String loanApplied="";
		public String getAdminId() {
			return adminId;
		}
		public void setAdminId(String adminId) {
			this.adminId = adminId;
		}
		public String getClContractId() {
			return clContractId;
		}
		public void setClContractId(String clContractId) {
			this.clContractId = clContractId;
		}
		public String getProduct() {
			return product;
		}
		public void setProduct(String product) {
			this.product = product;
		}
		public String getCycle() {
			return cycle;
		}
		public void setCycle(String cycle) {
			this.cycle = cycle;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getClsAppID() {
			return clsAppID;
		}
		public void setClsAppID(String clsAppID) {
			this.clsAppID = clsAppID;
		}
		public String getCaseType() {
			return caseType;
		}
		public void setCaseType(String caseType) {
			this.caseType = caseType;
		}
		public String getAppCreatedDate() {
			return appCreatedDate;
		}
		public void setAppCreatedDate(String appCreatedDate) {
			this.appCreatedDate = appCreatedDate;
		}
		public String getDateAssignToAnalyst() {
			return dateAssignToAnalyst;
		}
		public void setDateAssignToAnalyst(String dateAssignToAnalyst) {
			this.dateAssignToAnalyst = dateAssignToAnalyst;
		}
		public String getDateOfAnalysis() {
			return dateOfAnalysis;
		}
		public void setDateOfAnalysis(String dateOfAnalysis) {
			this.dateOfAnalysis = dateOfAnalysis;
		}
		public String getAnalystName() {
			return analystName;
		}
		public void setAnalystName(String analystName) {
			this.analystName = analystName;
		}
		public String getLeadSource() {
			return leadSource;
		}
		public void setLeadSource(String leadSource) {
			this.leadSource = leadSource;
		}
		public String getOthers() {
			return others;
		}
		public void setOthers(String others) {
			this.others = others;
		}
		public String getLoanPurpose() {
			return loanPurpose;
		}
		public void setLoanPurpose(String loanPurpose) {
			this.loanPurpose = loanPurpose;
		}
		public String getLoanEligible() {
			return loanEligible;
		}
		public void setLoanEligible(String loanEligible) {
			this.loanEligible = loanEligible;
		}
		public String getLoanApplied() {
			return loanApplied;
		}
		public void setLoanApplied(String loanApplied) {
			this.loanApplied = loanApplied;
		}

	
	
	
}
