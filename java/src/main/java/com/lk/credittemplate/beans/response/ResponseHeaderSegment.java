/**
 * 
 */
package com.lk.credittemplate.beans.response;

/**
 * @author kapil
 *
 */
public class ResponseHeaderSegment {
	private String memberReferenceNumber="";
	private String enquiryMemberUserID="";
	private String subjectReturnCode="";
	private String enquiryControlNumber="";
	private String dateProcessed="";
	private String timeProcessed="";
	
	/**
	 * @return the memberReferenceNumber
	 */
	public String getMemberReferenceNumber() {
		return memberReferenceNumber;
	}
	/**
	 * @param memberReferenceNumber the memberReferenceNumber to set
	 */
	public void setMemberReferenceNumber(String memberReferenceNumber) {
		this.memberReferenceNumber = memberReferenceNumber;
	}
	/**
	 * @return the enquiryMemberUserID
	 */
	public String getEnquiryMemberUserID() {
		return enquiryMemberUserID;
	}
	/**
	 * @param enquiryMemberUserID the enquiryMemberUserID to set
	 */
	public void setEnquiryMemberUserID(String enquiryMemberUserID) {
		this.enquiryMemberUserID = enquiryMemberUserID;
	}
	/**
	 * @return the subjectReturnCode
	 */
	public String getSubjectReturnCode() {
		return subjectReturnCode;
	}
	/**
	 * @param subjectReturnCode the subjectReturnCode to set
	 */
	public void setSubjectReturnCode(String subjectReturnCode) {
		this.subjectReturnCode = subjectReturnCode;
	}
	/**
	 * @return the enquiryControlNumber
	 */
	public String getEnquiryControlNumber() {
		return enquiryControlNumber;
	}
	/**
	 * @param enquiryControlNumber the enquiryControlNumber to set
	 */
	public void setEnquiryControlNumber(String enquiryControlNumber) {
		this.enquiryControlNumber = enquiryControlNumber;
	}
	/**
	 * @return the dateProcessed
	 */
	public String getDateProcessed() {
		return dateProcessed;
	}
	/**
	 * @param dateProcessed the dateProcessed to set
	 */
	public void setDateProcessed(String dateProcessed) {
		this.dateProcessed = dateProcessed;
	}
	/**
	 * @return the timeProcessed
	 */
	public String getTimeProcessed() {
		return timeProcessed;
	}
	/**
	 * @param timeProcessed the timeProcessed to set
	 */
	public void setTimeProcessed(String timeProcessed) {
		this.timeProcessed = timeProcessed;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResponseHeaderSegment [memberReferenceNumber="
				+ memberReferenceNumber + ", enquiryMemberUserID="
				+ enquiryMemberUserID + ", subjectReturnCode="
				+ subjectReturnCode + ", enquiryControlNumber="
				+ enquiryControlNumber + ", dateProcessed=" + dateProcessed
				+ ", timeProcessed=" + timeProcessed + "]";
	}
}
