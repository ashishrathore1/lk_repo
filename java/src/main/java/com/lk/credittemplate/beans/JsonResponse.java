/**
 * 
 */
package com.lk.credittemplate.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.lk.credittemplate.beans.response.ResponseAccountSegment;
import com.lk.credittemplate.beans.response.ResponseAddressSegment;
import com.lk.credittemplate.beans.response.ResponseDisputeRemarksSegment;
import com.lk.credittemplate.beans.response.ResponseEmploymentSegmaent;
import com.lk.credittemplate.beans.response.ResponseEnquirySegment;
import com.lk.credittemplate.beans.response.ResponseHeaderSegment;
import com.lk.credittemplate.beans.response.ResponseIDSegment;
import com.lk.credittemplate.beans.response.ResponseNameSegmaent;
import com.lk.credittemplate.beans.response.ResponsePLScoreSegmaent;
import com.lk.credittemplate.beans.response.ResponseScoreSegmaent;
import com.lk.credittemplate.beans.response.ResponseTelephoneSegmaent;


@JsonInclude(Include.NON_DEFAULT) 
public class JsonResponse {
	
	private ResponseHeaderSegment headerSegment=new ResponseHeaderSegment();
	private ResponseNameSegmaent nameSegmaent=new ResponseNameSegmaent();
	private List<ResponseIDSegment> idSegments=new ArrayList<ResponseIDSegment>();
	private List<ResponseTelephoneSegmaent> telephoneSegmaents=new ArrayList<ResponseTelephoneSegmaent>();
	private List<Map<String,String>> emailSegmaents=new ArrayList<Map<String,String>>();
	private ResponseEmploymentSegmaent employmentSegmaent=new ResponseEmploymentSegmaent();
	private List<Map<String,String>> enqueryAccountNumberSegmaents=new ArrayList<Map<String,String>>();;
	private ResponseScoreSegmaent scoreSegmaent=new ResponseScoreSegmaent();
	private ResponsePLScoreSegmaent plScoreSegmaent=new ResponsePLScoreSegmaent();
	private List<ResponseAddressSegment> addressSegments=new ArrayList<ResponseAddressSegment>();;
	private List<ResponseAccountSegment> accountSegments=new ArrayList<ResponseAccountSegment>();;
	private List<ResponseEnquirySegment> enquirySegments=new ArrayList<ResponseEnquirySegment>();;
	private ResponseDisputeRemarksSegment responseDisputeRemarksSegments=new ResponseDisputeRemarksSegment();
//	private String endSegment;
	/**
	 * @return the headerSegment
	 */
	public ResponseHeaderSegment getHeaderSegment() {
		return headerSegment;
	}
	/**
	 * @param headerSegment the headerSegment to set
	 */
	public void setHeaderSegment(ResponseHeaderSegment headerSegment) {
		this.headerSegment = headerSegment;
	}
	/**
	 * @return the nameSegmaent
	 */
	public ResponseNameSegmaent getNameSegmaent() {
		return nameSegmaent;
	}
	/**
	 * @param nameSegmaent the nameSegmaent to set
	 */
	public void setNameSegmaent(ResponseNameSegmaent nameSegmaent) {
		this.nameSegmaent = nameSegmaent;
	}
	/**
	 * @return the idSegments
	 */
	public List<ResponseIDSegment> getIdSegments() {
		return idSegments;
	}
	/**
	 * @param idSegments the idSegments to set
	 */
	public void setIdSegments(List<ResponseIDSegment> idSegments) {
		this.idSegments = idSegments;
	}
	/**
	 * @return the telephoneSegmaents
	 */
	public List<ResponseTelephoneSegmaent> getTelephoneSegmaents() {
		return telephoneSegmaents;
	}
	/**
	 * @param telephoneSegmaents the telephoneSegmaents to set
	 */
	public void setTelephoneSegmaents(
			List<ResponseTelephoneSegmaent> telephoneSegmaents) {
		this.telephoneSegmaents = telephoneSegmaents;
	}
	/**
	 * @return the emailSegmaents
	 */
	public List<Map<String,String>> getEmailSegmaents() {
		return emailSegmaents;
	}
	/**
	 * @param emailSegmaents the emailSegmaents to set
	 */
	public void setEmailSegmaents(List<Map<String,String>> emailSegmaents) {
		this.emailSegmaents = emailSegmaents;
	}
	/**
	 * @return the employmentSegmaent
	 */
	public ResponseEmploymentSegmaent getEmploymentSegmaent() {
		return employmentSegmaent;
	}
	/**
	 * @param employmentSegmaent the employmentSegmaent to set
	 */
	public void setEmploymentSegmaent(ResponseEmploymentSegmaent employmentSegmaent) {
		this.employmentSegmaent = employmentSegmaent;
	}
	/**
	 * @return the enqueryAccountNumberSegmaents
	 */
	public List<Map<String,String>> getEnqueryAccountNumberSegmaents() {
		return enqueryAccountNumberSegmaents;
	}
	/**
	 * @param enqueryAccountNumberSegmaents the enqueryAccountNumberSegmaents to set
	 */
	public void setEnqueryAccountNumberSegmaents(
			List<Map<String,String>> enqueryAccountNumberSegmaents) {
		this.enqueryAccountNumberSegmaents = enqueryAccountNumberSegmaents;
	}
	/**
	 * @return the scoreSegmaent
	 */
	public ResponseScoreSegmaent getScoreSegmaent() {
		return scoreSegmaent;
	}
	/**
	 * @param scoreSegmaent the scoreSegmaent to set
	 */
	public void setScoreSegmaent(ResponseScoreSegmaent scoreSegmaent) {
		this.scoreSegmaent = scoreSegmaent;
	}
	/**
	 * @return the addressSegments
	 */
	public List<ResponseAddressSegment> getAddressSegments() {
		return addressSegments;
	}
	/**
	 * @param addressSegments the addressSegments to set
	 */
	public void setAddressSegments(List<ResponseAddressSegment> addressSegments) {
		this.addressSegments = addressSegments;
	}
	/**
	 * @return the accountSegments
	 */
	public List<ResponseAccountSegment> getAccountSegments() {
		return accountSegments;
	}
	/**
	 * @param accountSegments the accountSegments to set
	 */
	public void setAccountSegments(List<ResponseAccountSegment> accountSegments) {
		this.accountSegments = accountSegments;
	}
	/**
	 * @return the enquirySegments
	 */
	public List<ResponseEnquirySegment> getEnquirySegments() {
		return enquirySegments;
	}
	/**
	 * @param enquirySegments the enquirySegments to set
	 */
	public void setEnquirySegments(List<ResponseEnquirySegment> enquirySegments) {
		this.enquirySegments = enquirySegments;
	}

//	/**
//	 * @return the endSegment
//	 */
//	public String getEndSegment() {
//		return endSegment;
//	}
//	/**
//	 * @param endSegment the endSegment to set
//	 */
//	public void setEndSegment(String endSegment) {
//		this.endSegment = endSegment;
//	}
	/**
	 * @return the responseDisputeRemarksSegments
	 */
	public ResponseDisputeRemarksSegment getResponseDisputeRemarksSegments() {
		return responseDisputeRemarksSegments;
	}
	/**
	 * @param responseDisputeRemarksSegments the responseDisputeRemarksSegments to set
	 */
	public void setResponseDisputeRemarksSegments(
			ResponseDisputeRemarksSegment responseDisputeRemarksSegments) {
		this.responseDisputeRemarksSegments = responseDisputeRemarksSegments;
	}
	/**
	 * @return the plScoreSegmaent
	 */
	public ResponsePLScoreSegmaent getPlScoreSegmaent() {
		return plScoreSegmaent;
	}
	/**
	 * @param plScoreSegmaent the plScoreSegmaent to set
	 */
	public void setPlScoreSegmaent(ResponsePLScoreSegmaent plScoreSegmaent) {
		this.plScoreSegmaent = plScoreSegmaent;
	}
	
	
	
}
