package com.lk.credittemplate.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * @author Pooja Desai
 *
 */
@Entity
@Table(name = "LK_PD_COMPANY_DETAILS_copy")

@NamedQueries({

		//@NamedQuery(name = "findStatusByStatusId", query = "SELECT s FROM StatusTracker s WHERE s.flag=:flag"),
		//@NamedQuery(name = "findAllStatus", query = "SELECT s FROM StatusTracker s WHERE s.applicationId IN(:applicationId)"),
		//@NamedQuery(name = "getStatusTrackerByAppId", query = "SELECT s FROM StatusTracker s WHERE s.applicationId =:applicationId")
		//@NamedQuery(name="updateStatusTrackerById", query="UPDATE  statusTracker s  WHERE s.applicationId=:applicationId")
	     // @NamedQuery(name = "findCompanyApplication", query = "SELECT l FROM PersonalDetailsCompany l WHERE l.appID=:appID"),
	      @NamedQuery(name = "getCompanyDetailsById", query = "SELECT l FROM PersonalDetailsCompany l WHERE l.appID=:appID")

})
public class PersonalDetailsCompany implements Serializable {

	private static final long serialVersionUID = 2805838946323485598L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long companyID;

	@Column(name = "LK_APP_ID")
	private String appID;


	@Column(name = "LK_COMPANY")
	private String companyName;

	@Column(name = "LK_CLIENT_NAME")
	private String clientName;

	@Column(name = "LK_CONTACT_PERSON_1")
	private String contactPerson1;

	@Column(name = "LK_CONTACT_DETAILS_1")
	private String contactDetails;

	@Column(name = "LK_EMAIL_ADDRESS_1")
	private String emailAdress1;

	@Column(name = "LK_RELATIONSHIP")
	private String relationship;

	@Column(name = "LK_CONTACT_PERSON_2")
	private String contactPerson2;
	
	@Column(name = "LK_CONTACT_DETAILS_2")
	private String contactDetails2;
	
	@Column(name = "LK_EMAIL_ADDRESS_2")
	private String emailAddress2;
	
	@Column(name = "LK_RELATIONSHIP_2")
	private String relationship2;
	
	@Column(name = "LK_CONSTITUTION")
	private String constitution;
	
	@Column(name = "LK_BUSINESS")
	private String natureOfBusiness;
	
	@Column(name = "LK_CIBIL_SCORE")
	private int cibilScore;
	
	@Column(name = "LK_ONLINE_START_DATE")
	private Date onlineStartDate;
	
	@Column(name = "LK_OFFLINE_START_DATE")
	private Date offlineStartDate;
	
	@Column(name = "LK_INCORPORATION_DATE")
	private Date incorporationDate;
	
	@Column(name = "LK_OFFLINE_VINTAGE")
	private int offlineVintage;
	
	@Column(name = "LK_ONLINE_VINTAGE")
	private int onlineVintage;
	
	@Column(name = "LK_INDUSTRY_SELECTED")
	private String industrySelected;
	
	@Column(name = "LK_NO_PRODUCT_CATEGORIES")
	private int NoOfProductCategories;
	
	@Column(name = "LK_PRIMARY_CATEGORY")
	private String primaryCategory;
	
	@Column(name = "LK_SECONDARY_CATEGORY")
	private String secondaryCategory;
	
	@Column(name = "LK_OTHER_CATEGORY")
	private String otherCategory;
	
	@Column(name = "LK_NO_BANK_ACCOUNT")
	private String noOfBankAccounts;
	
	@Column(name = "LK_PRIMARY_BANK")
	private String primaryBank;
	
	@Column(name = "LK_SECONDARY_BANK")
	private String secondaryBank;
	
	@Column(name = "LK_OTHER_BANKS")
	private String otherBanks;
	
	@Column(name = "LK_TOTAL_BANK_STATMT")
	private String totalBankStatements;
	
	@Column(name = "LK_TOTAL_MONTHS_REVENUE")
	private String totalMonthsRevenue;
	
	public long getCompanyID() {
		return companyID;
	}

	public void setCompanyID(long companyID) {
		this.companyID = companyID;
	}

	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}


	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getContactPerson1() {
		return contactPerson1;
	}

	public void setContactPerson1(String contactPerson1) {
		this.contactPerson1 = contactPerson1;
	}

	public String getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(String double1) {
		this.contactDetails = double1;
	}

	public String getEmailAdress1() {
		return emailAdress1;
	}

	public void setEmailAdress1(String emailAdress1) {
		this.emailAdress1 = emailAdress1;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getContactPerson2() {
		return contactPerson2;
	}

	public void setContactPerson2(String contactPerson2) {
		this.contactPerson2 = contactPerson2;
	}

	public String getContactDetails2() {
		return contactDetails2;
	}

	public void setContactDetails2(String string) {
		this.contactDetails2 = string;
	}

	public String getEmailAddress2() {
		return emailAddress2;
	}

	public void setEmailAddress2(String emailAddress2) {
		this.emailAddress2 = emailAddress2;
	}

	public String getRelationship2() {
		return relationship2;
	}

	public void setRelationship2(String relationship2) {
		this.relationship2 = relationship2;
	}

	public String getConstitution() {
		return constitution;
	}

	public void setConstitution(String constitution) {
		this.constitution = constitution;
	}


	public String getNatureOfBusiness() {
		return natureOfBusiness;
	}

	public void setNatureOfBusiness(String natureOfBusiness) {
		this.natureOfBusiness = natureOfBusiness;
	}

	public int getCibilScore() {
		return cibilScore;
	}

	public void setCibilScore(int cibilScore) {
		this.cibilScore = cibilScore;
	}

	public Date getOnlineStartDate() {
		return onlineStartDate;
	}

	public Date setOnlineStartDate(Date onlineStartDate) {
		return this.onlineStartDate = onlineStartDate;
	}

	public Date getOfflineStartDate() {
		return offlineStartDate;
	}

	public Date setOfflineStartDate(Date offlineStartDate) {
		return this.offlineStartDate = offlineStartDate;
	}

	public Date getIncorporationDate() {
		return incorporationDate;
	}

	public void setIncorporationDate(Date incorporationDate) {
		this.incorporationDate = incorporationDate;
	}

	public int getOfflineVintage() {
		return offlineVintage;
	}

	public void setOfflineVintage(int i) {
		this.offlineVintage = i;
	}

	public int getOnlineVintage() {
		return onlineVintage;
	}

	public void setOnlineVintage(int onlineVintage) {
		this.onlineVintage = onlineVintage;
	}

	public String getIndustrySelected() {
		return industrySelected;
	}

	public void setIndustrySelected(String string) {
		this.industrySelected = string;
	}

	public int getNoOfProductCategories() {
		return NoOfProductCategories;
	}

	public void setNoOfProductCategories(int noOfProductCategories) {
		NoOfProductCategories = noOfProductCategories;
	}

	public String getPrimaryCategory() {
		return primaryCategory;
	}

	public void setPrimaryCategory(String primaryCategory) {
		this.primaryCategory = primaryCategory;
	}

	public String getSecondaryCategory() {
		return secondaryCategory;
	}

	public void setSecondaryCategory(String secondaryCategory) {
		this.secondaryCategory = secondaryCategory;
	}

	public String getOtherCategory() {
		return otherCategory;
	}

	public void setOtherCategory(String otherCategory) {
		this.otherCategory = otherCategory;
	}

	public String getNoOfBankAccounts() {
		return noOfBankAccounts;
	}

	public void setNoOfBankAccounts(String noOfBankAccounts) {
		this.noOfBankAccounts = noOfBankAccounts;
	}

	public String getPrimaryBank() {
		return primaryBank;
	}

	public void setPrimaryBank(String primaryBank) {
		this.primaryBank = primaryBank;
	}

	public String getSecondaryBank() {
		return secondaryBank;
	}

	public void setSecondaryBank(String secondaryBank) {
		this.secondaryBank = secondaryBank;
	}

	public String getOtherBanks() {
		return otherBanks;
	}

	public void setOtherBanks(String otherBanks) {
		this.otherBanks = otherBanks;
	}

	public String getTotalBankStatements() {
		return totalBankStatements;
	}

	public void setTotalBankStatements(String i) {
		this.totalBankStatements = i;
	}

	public String getTotalMonthsRevenue() {
		return totalMonthsRevenue;
	}

	public void setTotalMonthsRevenue(String totalMonthsRevenue) {
		this.totalMonthsRevenue = totalMonthsRevenue;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

//	public String getNatureOfBusiness() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
	
	
	




	
}
