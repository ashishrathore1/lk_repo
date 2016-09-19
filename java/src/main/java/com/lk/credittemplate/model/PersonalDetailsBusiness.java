package com.lk.credittemplate.model;

import java.io.Serializable;
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
@Table(name = "LK_PD_BUSINESS_DETAILS")

@NamedQueries({

	      @NamedQuery(name = "getBusinessDetailsById", query = "SELECT l FROM PersonalDetailsBusiness l WHERE l.busAppId=:busAppId")

})
public class PersonalDetailsBusiness implements Serializable {

	private static final long serialVersionUID = 2805838946323485598L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long busId;

	@Column(name = "LK_APP_ID")
	private String busAppId;

	@Column(name = "LK_PROFIT_MARGIN")
	private int profitMargin;

	@Column(name = "LK_CASH_DISCOUNT")
	private double  cashDiscount;

	@Column(name = "LK_PRODUCT_REJECTION_RATE")
	private int prodRejectionRate;

	@Column(name = "LK_NUM_OF_EMPLOYEES")
	private String numOfEmployees;

	@Column(name = "LK_OWNED_CAPITAL")
	private double ownedCapital;

	@Column(name = "LK_ONLINE_BUSINESS")
	private double onlineBusiness;

	@Column(name = "LK_OFFLINE_BUSINESS")
	private double offlineBusiness;

	@Column(name = "LK_DEBTOR_DAYS")
	private int debitorDays;
	
	@Column(name = "LK_CURRENT_ACCOUNTS_RECEIVABLE")
	private double curAccReceivable;
	
	@Column(name = "LK_INVENTORY_DAYS")
	private int inventoryDays;
	
	@Column(name = "LK_CURRENT_INVENTORY_AMOUNT")
	private double currentInventoryAmount;
	
	@Column(name = "LK_CREDITOR_DAYS")
	private int creditorDays;
	
	@Column(name = "LK_CURRENT_ACC_PAYABLE")
	private double currAccPayable;
	
	@Column(name = "LK_CASH_CONVERSION_CYCLE_DAYS")
	private int cashConversionCycle;
	
	@Column(name = "LK_CASH_CONVERSION_CYCLE_LAKHS")
	private double cashConversionCycleLakhs;
	
	@Column(name = "LK_REGISTERED_FULFILMENT")
	private boolean registeredFulfilment;
	
	@Column(name = "LK_INVENTORY_FULFILMENT")
	private double inventoryFulfilment;
	
	@Column(name = "LK_OTHER_COMPANY")
	private boolean otherCompany;
	
	@Column(name = "LK_REFERENCE")
	private String reference;
	
	@Column(name = "LK_AWARDS_RECOGNITION")
	private boolean awardsRecognition;
	
	@Column(name = "LK_ENDORSEMENT_RECEIVED")
	private boolean endorsementReceived;
	
	@Column(name = "LK_ELIGIBLE")
	private boolean eligible;
	
	@Column(name = "LK_GOOGLE_SEARCH")
	private boolean googleSearch;
	
	@Column(name = "LK_RBI_DEFAULTER")
	private boolean rbiDefaulter;

	public long getBusId() {
		return busId;
	}

	public void setBusId(long busId) {
		this.busId = busId;
	}

	public String getBusAppId() {
		return busAppId;
	}

	public void setBusAppId(String busAppId) {
		this.busAppId = busAppId;
	}

	public int getProfitMargin() {
		return profitMargin;
	}

	public void setProfitMargin(int profitMargin) {
		this.profitMargin = profitMargin;
	}


	public double getCashDiscount() {
		return cashDiscount;
	}

	public void setCashDiscount(double cashDiscount) {
		this.cashDiscount = cashDiscount;
	}

	public int getProdRejectionRate() {
		return prodRejectionRate;
	}

	public void setProdRejectionRate(int prodRejectionRate) {
		this.prodRejectionRate = prodRejectionRate;
	}

	public String getNumOfEmployees() {
		return numOfEmployees;
	}

	public void setNumOfEmployees(String numOfEmployees) {
		this.numOfEmployees = numOfEmployees;
	}

	public double getOwnedCapital() {
		return ownedCapital;
	}

	public void setOwnedCapital(double ownedCapital) {
		this.ownedCapital = ownedCapital;
	}

	public double getOnlineBusiness() {
		return onlineBusiness;
	}

	public void setOnlineBusiness(double onlineBusiness) {
		this.onlineBusiness = onlineBusiness;
	}

	public double getOfflineBusiness() {
		return offlineBusiness;
	}

	public void setOfflineBusiness(double offlineBusiness) {
		this.offlineBusiness = offlineBusiness;
	}

	public int getDebitorDays() {
		return debitorDays;
	}

	public void setDebitorDays(int debitorDays) {
		this.debitorDays = debitorDays;
	}

	public double getCurAccReceivable() {
		return curAccReceivable;
	}

	public void setCurAccReceivable(double curAccReceivable) {
		this.curAccReceivable = curAccReceivable;
	}

	public int getInventoryDays() {
		return inventoryDays;
	}

	public void setInventoryDays(int inventoryDays) {
		this.inventoryDays = inventoryDays;
	}

	public double getCurrentInventoryAmount() {
		return currentInventoryAmount;
	}

	public void setCurrentInventoryAmount(double currentInventoryAmount) {
		this.currentInventoryAmount = currentInventoryAmount;
	}

	public int getCreditorDays() {
		return creditorDays;
	}

	public void setCreditorDays(int creditorDays) {
		this.creditorDays = creditorDays;
	}


	public double getCurrAccPayable() {
		return currAccPayable;
	}

	public void setCurrAccPayable(double currAccPayable) {
		this.currAccPayable = currAccPayable;
	}

	public int getCashConversionCycle() {
		return cashConversionCycle;
	}

	public void setCashConversionCycle(int cashConversionCycle) {
		this.cashConversionCycle = cashConversionCycle;
	}

	public double getCashConversionCycleLakhs() {
		return cashConversionCycleLakhs;
	}

	public void setCashConversionCycleLakhs(double cashConversionCycleLakhs) {
		this.cashConversionCycleLakhs = cashConversionCycleLakhs;
	}

	public boolean isRegisteredFulfilment() {
		return registeredFulfilment;
	}

	public void setRegisteredFulfilment(boolean registeredFulfilment) {
		this.registeredFulfilment = registeredFulfilment;
	}

	public double getInventoryFulfilment() {
		return inventoryFulfilment;
	}

	public void setInventoryFulfilment(double inventoryFulfilment) {
		this.inventoryFulfilment = inventoryFulfilment;
	}

	public boolean isOtherCompany() {
		return otherCompany;
	}

	public void setOtherCompany(boolean otherCompany) {
		this.otherCompany = otherCompany;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public boolean isAwardsRecognition() {
		return awardsRecognition;
	}

	public void setAwardsRecognition(boolean awardsRecognition) {
		this.awardsRecognition = awardsRecognition;
	}

	public boolean isEndorsementReceived() {
		return endorsementReceived;
	}

	public void setEndorsementReceived(boolean endorsementReceived) {
		this.endorsementReceived = endorsementReceived;
	}

	public boolean isEligible() {
		return eligible;
	}

	public void setEligible(boolean eligible) {
		this.eligible = eligible;
	}

	public boolean isGoogleSearch() {
		return googleSearch;
	}

	public void setGoogleSearch(boolean googleSearch) {
		this.googleSearch = googleSearch;
	}

	public boolean isRbiDefaulter() {
		return rbiDefaulter;
	}

	public void setRbiDefaulter(boolean rbiDefaulter) {
		this.rbiDefaulter = rbiDefaulter;
	}

}
