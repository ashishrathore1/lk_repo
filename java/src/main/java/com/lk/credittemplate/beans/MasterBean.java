/**
 * 
 */
package com.lk.credittemplate.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author pooja
 * @param <T>
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MasterBean {

	
	private PersonalDetailsBean personalDetailsBean;
	
	private PersonalCompanyBean personalCompanyBean;
	
	private PersonalKYCBean personalKYCBean;
	
	private List<PDSocialPresenceBean> pdSocialPresenceBean;
	
   private List<PersonalDetailsApplicantBean> personalDetailsApplicantBean;
   
   private PersonalDetailsBusinessBean personalDetailsBusinessBean;
   
   private List<PersonalDetailsApplicantInfoBean> personalDetailsApplicantInfoBean;
   
   private PersonalDetailsCalculationBean personalDetailsCalculationBean;
   
   private List<PersonalDetailsMarketBean> personalDetailsMarketBean;
   
	
	
	
	public List<PersonalDetailsMarketBean> getPersonalDetailsMarketBean() {
	return personalDetailsMarketBean;
}

public void setPersonalDetailsMarketBean(List<PersonalDetailsMarketBean> list) {
	this.personalDetailsMarketBean = list;
}

	public PersonalDetailsCalculationBean getPersonalDetailsCalculationBean() {
	return personalDetailsCalculationBean;
}

public void setPersonalDetailsCalculationBean(PersonalDetailsCalculationBean personalDetailsCalculationBean) {
	this.personalDetailsCalculationBean = personalDetailsCalculationBean;
}

	public List<PersonalDetailsApplicantInfoBean> getPersonalDetailsApplicantInfoBean() {
	return personalDetailsApplicantInfoBean;
}

public void setPersonalDetailsApplicantInfoBean(List<PersonalDetailsApplicantInfoBean> list) {
	this.personalDetailsApplicantInfoBean = list;
}

	public PersonalDetailsBusinessBean getPersonalDetailsBusinessBean() {
	return personalDetailsBusinessBean;
}

public void setPersonalDetailsBusinessBean(PersonalDetailsBusinessBean personalDetailsBusinessBean) {
	this.personalDetailsBusinessBean = personalDetailsBusinessBean;
}

	public List<PDSocialPresenceBean> getPdSocialPresenceBean() {
		return pdSocialPresenceBean;
		
		
	}

	public List<PersonalDetailsApplicantBean> getPersonalDetailsApplicantBean() {
		return personalDetailsApplicantBean;
	}

	public void setPersonalDetailsApplicantBean(List<PersonalDetailsApplicantBean> list) {
		this.personalDetailsApplicantBean = list;
	}

	public void setPdSocialPresenceBean(List<PDSocialPresenceBean> list) {
		this.pdSocialPresenceBean = list;
	}

	private String loanApplicationId;

	public PersonalKYCBean getPersonalKYCBean() {
		return personalKYCBean;
	}

	public void setPersonalKYCBean(PersonalKYCBean personalKYCBean) {
		this.personalKYCBean = personalKYCBean;
	}

	public PersonalDetailsBean getPersonalDetailsBean() {
		return personalDetailsBean;
	}

	public void setPersonalDetailsBean(PersonalDetailsBean personalDetailsBean) {
		this.personalDetailsBean = personalDetailsBean;
	}

	public PersonalCompanyBean getPersonalCompanyBean() {
		return personalCompanyBean;
	}

	public void setPersonalCompanyBean(PersonalCompanyBean personalCompanyBean) {
		this.personalCompanyBean = personalCompanyBean;
	}

	public String getLoanApplicationId() {
		return loanApplicationId;
	}

	public void setLoanApplicationId(String loanApplicationId) {
		this.loanApplicationId = loanApplicationId;
	}


}
