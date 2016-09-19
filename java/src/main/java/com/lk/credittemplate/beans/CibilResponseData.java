package com.lk.credittemplate.beans;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CibilResponseData {

	private List<CibilResponeBean> cibilDataList;
	
	private ApplicantBean applicantDetails;
	
	private CibilSummaryBean cibilSummaryBean; 

	private EnquiryDataBean enquiryData;
	
	public CibilSummaryBean getCibilSummaryBean() {
		return cibilSummaryBean;
	}

	public void setCibilSummaryBean(CibilSummaryBean cibilSummaryBean) {
		this.cibilSummaryBean = cibilSummaryBean;
	}

	public EnquiryDataBean getEnquiryData() {
		return enquiryData;
	}

	public void setEnquiryData(EnquiryDataBean enquiryData) {
		this.enquiryData = enquiryData;
	}

	public CibilSummaryBean getLoanSummaryBean() {
		return cibilSummaryBean;
	}

	public void setLoanSummaryBean(CibilSummaryBean cibilSummaryBean) {
		this.cibilSummaryBean = cibilSummaryBean;
	}

	public ApplicantBean getApplicantDetails() {
		return applicantDetails;
	}

	public void setApplicantDetails(ApplicantBean applicantDetails) {
		this.applicantDetails = applicantDetails;
	}

	public List<CibilResponeBean> getCibilDataList() {
		return cibilDataList;
	}

	public void setCibilDataList(List<CibilResponeBean> cibilDataList) {
		this.cibilDataList = cibilDataList;
	}
	
}
