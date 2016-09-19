/**
 * 
 */
package com.lk.credittemplate.beans.response;

/**
 * @author Prashanth
 *
 */
public class ResponseIDSegment {
	private String iDName="";
	private String iDValue="";
	private String issueDate="";
	private String expirationDate="";
	private String enrichedThroughEnquiry="";
	
	/**
	 * @return the iDName
	 */
	public String getiDName() {
		return iDName;
	}
	/**
	 * @param iDName the iDName to set
	 */
	public void setiDName(String iDName) {
		this.iDName = iDName;
	}
	/**
	 * @return the iDValue
	 */
	public String getiDValue() {
		return iDValue;
	}
	/**
	 * @param iDValue the iDValue to set
	 */
	public void setiDValue(String iDValue) {
		this.iDValue = iDValue;
	}
	/**
	 * @return the issueDate
	 */
	public String getIssueDate() {
		return issueDate;
	}
	/**
	 * @param issueDate the issueDate to set
	 */
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	/**
	 * @return the expirationDate
	 */
	public String getExpirationDate() {
		return expirationDate;
	}
	/**
	 * @param expirationDate the expirationDate to set
	 */
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	/**
	 * @return the enrichedThroughEnquiry
	 */
	public String getEnrichedThroughEnquiry() {
		return enrichedThroughEnquiry;
	}
	/**
	 * @param enrichedThroughEnquiry the enrichedThroughEnquiry to set
	 */
	public void setEnrichedThroughEnquiry(String enrichedThroughEnquiry) {
		this.enrichedThroughEnquiry = enrichedThroughEnquiry;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResponseInnerIDSegmentBean [iDName=" + iDName + ", iDValue="
				+ iDValue + ", issueDate=" + issueDate + ", expirationDate="
				+ expirationDate + ", enrichedThroughEnquiry="
				+ enrichedThroughEnquiry + "]";
	}
	
}
