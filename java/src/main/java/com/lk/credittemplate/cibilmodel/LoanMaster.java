package com.lk.credittemplate.cibilmodel;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "LK_LOAN_MASTER")

@NamedQueries({

	@NamedQuery(name = "getApplicationByAppId", query = "SELECT l FROM LoanMaster AS l WHERE l.applicationId = :appId"),
	@NamedQuery(name = "getCountByStatusId", query = "SELECT count(*) FROM LoanMaster AS l WHERE l.statusId =:statusid"),
	@NamedQuery(name = "getDataByStatusId", query = "SELECT l FROM LoanMaster AS l WHERE l.statusId =:statusid order by l.applicationId"),	
	@NamedQuery(name = "getCountBySubStatusId", query = "SELECT count(*) FROM LoanMaster AS l WHERE l.subStatus =:statusid"),
	@NamedQuery(name = "getCountByStatusIdAndAssignee", query = "SELECT count(*) FROM LoanMaster AS l WHERE l.statusId =:statusid"
			+ " AND l.assignedTo =:name"),
	@NamedQuery(name = "getCountBySubStatusAndAssignee", query = "SELECT count(*) FROM LoanMaster AS l WHERE l.subStatus =:statusid"
			+ " AND l.assignedTo =:name"),
	@NamedQuery(name = "getDataByStatusIdAndAssignee", query = "SELECT l FROM LoanMaster AS l WHERE l.statusId =:statusid"
			+ " AND l.assignedTo =:name order by l.applicationId"),
	@NamedQuery(name = "getDataBySubStatus", query = "SELECT l FROM LoanMaster AS l WHERE l.subStatus =:statusid"
			+ " order by l.applicationId"),
	@NamedQuery(name = "getAppIdById", query = "SELECT l.loanMastertId FROM LoanMaster AS l WHERE l.applicationId =:applicationId"
			+ " order by l.applicationId")
	
	
})

public class LoanMaster {

	@Id
	@Column(name = "L_GUID")
	private String loanMastertId;

	@Column(name = "L_USER_ID")
	private String userId;

	@Column(name = "L_REGISTERAS_ID")
	private String registerId;

	@Column(name = "L_STATUS_ID")
	private String statusId;

	@Column(name = "L_REQUEST_STATUS")
	private boolean requestStatus;

	@Column(name = "L_APPLICATION_ID", length = 50)
	private String applicationId;

	@Column(name = "L_COMPANY_NAME")
	private String companyName;

	@Column(name = "L_CONTACT_NO")
	private String contactNo;

	@Column(name = "L_CLS_APPLICATION_ID")
	private String clsApplicationId;

	@Column(name = "L_GENDER")
	private String gender;

	@Column(name = "L_ENQUIRY_AMOUNT")
	private String enquiryAmount;

	@Column(name = "L_NUMBER_OF_EMPLOYEES")
	private String noOfEmployees;

	@Column(name = "L_COMPANY_PAN")
	private String panNumber;

	@Column(name = "L_TIN")
	private String tin;

	@Column(name = "L_EXEMPTED_FROM_VAT")
	private Integer exemptedVat;

	@Column(name = "L_SERVICE_TAX", length = 225)
	private String serviceTax;

	@Column(name = "L_SELL_SERVICES")
	private Integer serviceSell;

	@Column(name = "L_SELL_OFFLINE")
	private Date sellOffline;

	@Column(name = "L_SELLING_OFFLINE")
	private Integer offlineSelling;

	@Column(name = "L_SELL_ONLINE")
	private Date sellOnline;

	@Column(name = "L_SELLING_ONLINE")
	private Integer onlineSelling;

	@Column(name = "L_TURN_OVER", length = 255)
	private String turnOver;

	@Column(name = "L_BUSINESS_ADDRESS", length = 255)
	private String businessAddress;

	@Column(name = "L_PINCODE", length = 255)
	private String pincode;

	@Column(name = "L_ADDRESS", length = 255)
	private String address;

	@Column(name = "L_CITY", length = 13)
	private String city;

	@Column(name = "L_STATE")
	private String state;

	@Column(name = "L_WAREHOUSE_PREMISES")
	private Integer warehousePremises;

	@Column(name = "L_WH_ADDRESS")
	private String whAddress;

	@Column(name = "L_WAREHOUSE_PINCODE")
	private String warehousePincode;

	@Column(name = "L_WAREHOUSE_ADDRESS")
	private String warehouseAddress;

	@Column(name = "L_WAREHOUSE_CITY")
	private String warehouseCity;

	@Column(name = "L_WAREHOUSE_STATE")
	private String warehouseState;

	@Column(name = "L_OVER_DRAFT")
	private Integer overDraft;

	@Column(name = "L_CREDIT_CARD")
	private String creditCard;

	@Column(name = "L_EMAIL")
	private String email;

	@Column(name = "L_CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Column(name = "L_UPDATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;

	@Column(name = "L_BUSINESSRUN_BY")
	private String businessRunBy;

	@Column(name = "L_NO_PARTNERS")
	private String noPartners;

	@Column(name = "L_NO_DIRECTORS")
	private String noDirectors;

	@Column(name = "L_OPERATED_BY")
	private String operatedBy;

	@Column(name = "L_BANK_OTHERS")
	private String bankOthers;

	@Column(name = "L_PRODUCT_OTHERS")
	private String productOthers;

	@Column(name = "L_SELLINGON_OTHERS")
	private String sellingonOthers;

	@Column(name = "L_KNOW_ABOUT_US")
	private String knowAboutUs;

	@Column(name = "L_KNOW_ABOUT_US_OTHERS")
	private String knowAboutUsOthers;

	@Column(name = "L_PURPOSE_OF_LOAN")
	private String purposeOfLoan;

	@Column(name = "L_PURPOSE_OF_LOAN_OTHERS")
	private String purposeOfLoanOthers;

	@Column(name = "L_LEAD_NAME")
	private String leadName;

	@Column(name = "L_ASSIGNED_TO")
	private String assignedTo;

	@Column(name = "L_REFERENCEID")
	private String loanReferenceId;

	@Column(name = "L_LOANTYPE")
	private String loanType;

	@Column(name = "L_LEADSOURCE")
	private String leadSource;

	@Column(name = "L_LK_SCORE")
	private String lendingkartScore;

	@Column(name = "L_LAG_STATUS")
	private String lagStatus;

	@Column(name = "L_SUB_STATUS_ID")
	private String subStatus;

	@Column(name = "L_DS_FLAG")
	private short dsFlag;
	
	@Column(name = "L_REJECTION_REASON")
	private String rejectionReason;
	
	@Column(name = "L_TEN_LOAN_AMOUNT")
	private Double tenloanAmnt;
	
	@Column(name = "L_STATUS_NOTIFICATION_FLAG")
	private boolean statusNotificationFlag;

	public boolean isStatusNotificationFlag() {
		return statusNotificationFlag;
	}

	public void setStatusNotificationFlag(boolean statusNotificationFlag) {
		this.statusNotificationFlag = statusNotificationFlag;
	}
	
	public Double getTenloanAmnt() {
		return tenloanAmnt;
	}

	public void setTenloanAmnt(Double tenloanAmnt) {
		this.tenloanAmnt = tenloanAmnt;
	}

	public LoanMaster() {
		if (StringUtils.isEmpty(this.loanMastertId)) {
			this.loanMastertId = UUID.randomUUID().toString();
		}

	}

	public String getRejectionReason() {
		return rejectionReason;
	}


	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}


	public String getLoanMastertId() {
		return loanMastertId;
	}

	/**
	 * @param loanMastertId
	 *            the loanMastertId to set
	 */
	public void setLoanMastertId(String loanMastertId) {
		this.loanMastertId = loanMastertId;
	}

	/**
	 * @return the noOfEmployees
	 */
	public String getNoOfEmployees() {
		return noOfEmployees;
	}

	/**
	 * @param noOfEmployees
	 *            the noOfEmployees to set
	 */
	public void setNoOfEmployees(String noOfEmployees) {
		this.noOfEmployees = noOfEmployees;
	}

	/**
	 * @return the panNumber
	 */
	public String getPanNumber() {
		return panNumber;
	}

	/**
	 * @param panNumber
	 *            the panNumber to set
	 */
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	/**
	 * @return the tin
	 */
	public String getTin() {
		return tin;
	}

	/**
	 * @param tin
	 *            the tin to set
	 */
	public void setTin(String tin) {
		this.tin = tin;
	}

	/**
	 * @return the exemptedVat
	 */
	public Integer getExemptedVat() {
		return exemptedVat;
	}

	/**
	 * @param exemptedVat
	 *            the exemptedVat to set
	 */
	public void setExemptedVat(Integer exemptedVat) {
		this.exemptedVat = exemptedVat;
	}

	/**
	 * @return the serviceTax
	 */
	public String getServiceTax() {
		return serviceTax;
	}

	/**
	 * @param serviceTax
	 *            the serviceTax to set
	 */
	public void setServiceTax(String serviceTax) {
		this.serviceTax = serviceTax;
	}

	/**
	 * @return the serviceSell
	 */
	public Integer getServiceSell() {
		return serviceSell;
	}

	/**
	 * @param serviceSell
	 *            the serviceSell to set
	 */
	public void setServiceSell(Integer serviceSell) {
		this.serviceSell = serviceSell;
	}

	/**
	 * @return the sellOffline
	 */
	public Date getSellOffline() {
		return sellOffline;
	}

	/**
	 * @param sellOffline
	 *            the sellOffline to set
	 */
	public void setSellOffline(Date sellOffline) {
		this.sellOffline = sellOffline;
	}

	/**
	 * @return the offlineSelling
	 */
	public Integer getOfflineSelling() {
		return offlineSelling;
	}

	/**
	 * @param offlineSelling
	 *            the offlineSelling to set
	 */
	public void setOfflineSelling(Integer offlineSelling) {
		this.offlineSelling = offlineSelling;
	}

	/**
	 * @return the sellOnline
	 */
	public Date getSellOnline() {
		return sellOnline;
	}

	/**
	 * @param sellOnline
	 *            the sellOnline to set
	 */
	public void setSellOnline(Date sellOnline) {
		this.sellOnline = sellOnline;
	}

	/**
	 * @return the onlineSelling
	 */
	public Integer getOnlineSelling() {
		return onlineSelling;
	}

	/**
	 * @param onlineSelling
	 *            the onlineSelling to set
	 */
	public void setOnlineSelling(Integer onlineSelling) {
		this.onlineSelling = onlineSelling;
	}

	/**
	 * @return the turnOver
	 */
	public String getTurnOver() {
		return turnOver;
	}

	/**
	 * @param turnOver
	 *            the turnOver to set
	 */
	public void setTurnOver(String turnOver) {
		this.turnOver = turnOver;
	}

	/**
	 * @return the businessAddress
	 */
	public String getBusinessAddress() {
		return businessAddress;
	}

	/**
	 * @param businessAddress
	 *            the businessAddress to set
	 */
	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	/**
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}

	/**
	 * @param pincode
	 *            the pincode to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the warehousePremises
	 */
	public Integer getWarehousePremises() {
		return warehousePremises;
	}

	/**
	 * @param warehousePremises
	 *            the warehousePremises to set
	 */
	public void setWarehousePremises(Integer warehousePremises) {
		this.warehousePremises = warehousePremises;
	}

	/**
	 * @return the whAddress
	 */
	public String getWhAddress() {
		return whAddress;
	}

	/**
	 * @param whAddress
	 *            the whAddress to set
	 */
	public void setWhAddress(String whAddress) {
		this.whAddress = whAddress;
	}

	/**
	 * @return the warehousePincode
	 */
	public String getWarehousePincode() {
		return warehousePincode;
	}

	/**
	 * @param warehousePincode
	 *            the warehousePincode to set
	 */
	public void setWarehousePincode(String warehousePincode) {
		this.warehousePincode = warehousePincode;
	}

	/**
	 * @return the warehouseAddress
	 */
	public String getWarehouseAddress() {
		return warehouseAddress;
	}

	/**
	 * @param warehouseAddress
	 *            the warehouseAddress to set
	 */
	public void setWarehouseAddress(String warehouseAddress) {
		this.warehouseAddress = warehouseAddress;
	}

	/**
	 * @return the warehouseCity
	 */
	public String getWarehouseCity() {
		return warehouseCity;
	}

	/**
	 * @param warehouseCity
	 *            the warehouseCity to set
	 */
	public void setWarehouseCity(String warehouseCity) {
		this.warehouseCity = warehouseCity;
	}

	/**
	 * @return the warehouseState
	 */
	public String getWarehouseState() {
		return warehouseState;
	}

	/**
	 * @param warehouseState
	 *            the warehouseState to set
	 */
	public void setWarehouseState(String warehouseState) {
		this.warehouseState = warehouseState;
	}

	/**
	 * @return the overDraft
	 */
	public Integer getOverDraft() {
		return overDraft;
	}

	/**
	 * @param overDraft
	 *            the overDraft to set
	 */
	public void setOverDraft(Integer overDraft) {
		this.overDraft = overDraft;
	}

	/**
	 * @return the typeofbusiness
	 */
	/*
	 * public String getTypeofbusiness() { return typeofbusiness; }
	 * 
	 *//**
		 * @param typeofbusiness
		 *            the typeofbusiness to set
		 *//*
		 * public void setTypeofbusiness(String typeofbusiness) {
		 * this.typeofbusiness = typeofbusiness; }
		 */

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created
	 *            the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the modified
	 */
	public Date getModified() {
		return modified;
	}

	/**
	 * @param modified
	 *            the modified to set
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * @return the operatedBy
	 */
	public String getOperatedBy() {
		return operatedBy;
	}

	/**
	 * @param operatedBy
	 *            the operatedBy to set
	 */
	public void setOperatedBy(String operatedBy) {
		this.operatedBy = operatedBy;
	}

	/**
	 * @return the applicationId
	 */
	public String getApplicationId() {
		return applicationId;
	}

	/**
	 * @param applicationId
	 *            the applicationId to set
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName
	 *            the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the contactNo
	 */
	public String getContactNo() {
		return contactNo;
	}

	/**
	 * @param contactNo
	 *            the contactNo to set
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	/**
	 * @return the creditCard
	 */
	public String getCreditCard() {
		return creditCard;
	}

	/**
	 * @param creditCard
	 *            the creditCard to set
	 */
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	/**
	 * @return the businessRunBy
	 */
	public String getBusinessRunBy() {
		return businessRunBy;
	}

	/**
	 * @param businessRunBy
	 *            the businessRunBy to set
	 */
	public void setBusinessRunBy(String businessRunBy) {
		this.businessRunBy = businessRunBy;
	}

	/**
	 * @return the noPartners
	 */
	public String getNoPartners() {
		return noPartners;
	}

	/**
	 * @param noPartners
	 *            the noPartners to set
	 */
	public void setNoPartners(String noPartners) {
		this.noPartners = noPartners;
	}

	/**
	 * @return the noDirectors
	 */
	public String getNoDirectors() {
		return noDirectors;
	}

	/**
	 * @param noDirectors
	 *            the noDirectors to set
	 */
	public void setNoDirectors(String noDirectors) {
		this.noDirectors = noDirectors;
	}

	public String getBankOthers() {
		return bankOthers;
	}

	public void setBankOthers(String bankOthers) {
		this.bankOthers = bankOthers;
	}

	public String getProductOthers() {
		return productOthers;
	}

	public void setProductOthers(String productOthers) {
		this.productOthers = productOthers;
	}

	/*
	 * public String getCommentOthers() { return commentOthers; }
	 * 
	 * public void setCommentOthers(String commentOthers) { this.commentOthers =
	 * commentOthers; }
	 */

	public String getSellingonOthers() {
		return sellingonOthers;
	}

	public void setSellingonOthers(String sellingonOthers) {
		this.sellingonOthers = sellingonOthers;
	}

	public String getKnowAboutUs() {
		return knowAboutUs;
	}

	public void setKnowAboutUs(String knowAboutUs) {
		this.knowAboutUs = knowAboutUs;
	}

	public String getPurposeOfLoan() {
		return purposeOfLoan;
	}

	public void setPurposeOfLoan(String purposeOfLoan) {
		this.purposeOfLoan = purposeOfLoan;
	}

	public String getPurposeOfLoanOthers() {
		return purposeOfLoanOthers;
	}

	public void setPurposeOfLoanOthers(String purposeOfLoanOthers) {
		this.purposeOfLoanOthers = purposeOfLoanOthers;
	}

	public String getLeadName() {
		return leadName;
	}

	public void setLeadName(String leadName) {
		this.leadName = leadName;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getKnowAboutUsOthers() {
		return knowAboutUsOthers;
	}

	public void setKnowAboutUsOthers(String knowAboutUsOthers) {
		this.knowAboutUsOthers = knowAboutUsOthers;
	}

	/**
	 * @return the clsApplicationId
	 */
	public String getClsApplicationId() {
		return clsApplicationId;
	}

	/**
	 * @param clsApplicationId
	 *            the clsApplicationId to set
	 */
	public void setClsApplicationId(String clsApplicationId) {
		this.clsApplicationId = clsApplicationId;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the enquiryAmount
	 */
	public String getEnquiryAmount() {
		return enquiryAmount;
	}

	/**
	 * @param enquiryAmount
	 *            the enquiryAmount to set
	 */
	public void setEnquiryAmount(String enquiryAmount) {
		this.enquiryAmount = enquiryAmount;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the registerId
	 */
	public String getRegisterId() {
		return registerId;
	}

	/**
	 * @param registerId
	 *            the registerId to set
	 */
	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}

	/**
	 * @return the statusId
	 */
	public String getStatusId() {
		return statusId;
	}

	/**
	 * @param statusId
	 *            the statusId to set
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	/**
	 * @return the requestStatus
	 */
	public boolean isRequestStatus() {
		return requestStatus;
	}

	/**
	 * @param requestStatus
	 *            the requestStatus to set
	 */
	public void setRequestStatus(boolean requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getLoanReferenceId() {
		return loanReferenceId;
	}

	public void setLoanReferenceId(String loanReferenceId) {
		this.loanReferenceId = loanReferenceId;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getLeadSource() {
		return leadSource;
	}

	public void setLeadSource(String leadSource) {
		this.leadSource = leadSource;
	}

	public String getLendingkartScore() {
		return lendingkartScore;
	}

	public void setLendingkartScore(String lendingkartScore) {
		this.lendingkartScore = lendingkartScore;
	}

	public String getLagStatus() {
		return lagStatus;
	}

	public void setLagStatus(String lagStatus) {
		this.lagStatus = lagStatus;

	}

	public String getSubStatus() {
		return subStatus;
	}

	public void setSubStatus(String subStatus) {
		this.subStatus = subStatus;
	}

	public short getDsFlag() {
		return dsFlag;
	}

	public void setDsFlag(short dsFlag) {
		this.dsFlag = dsFlag;
	}

}
