/**
 * 
 */
package com.lk.credittemplate.beans.response;

/**
 * 
 * @author prashanth
 *
 */
public class ResponseTelephoneSegmaent {
	
	private String telephoneNumber="";
	private String telephoneExtension="";
	private String telephoneType="";
	private String enrichedThroughEnquiry="";
	/**
	 * @return the telephoneNumber
	 */
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	/**
	 * @param telephoneNumber the telephoneNumber to set
	 */
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	/**
	 * @return the telephoneExtension
	 */
	public String getTelephoneExtension() {
		return telephoneExtension;
	}
	/**
	 * @param telephoneExtension the telephoneExtension to set
	 */
	public void setTelephoneExtension(String telephoneExtension) {
		this.telephoneExtension = telephoneExtension;
	}
	/**
	 * @return the telephoneType
	 */
	public String getTelephoneType() {
		return telephoneType;
	}
	/**
	 * @param telephoneType the telephoneType to set
	 */
	public void setTelephoneType(String telephoneType) {
		this.telephoneType = telephoneType;
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
	
}
