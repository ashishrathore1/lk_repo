package com.lk.credittemplate.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonalDetailsBusinessBean {

	    @JsonProperty("profitMargin")
	    private String profitMargin="";
	    @JsonProperty("cashDiscount")
	    private double cashDiscount=0.0;
	    @JsonProperty("productRejection")
	    private String productRejection="";
	    @JsonProperty("numOfEmployees")
	    private String numOfEmployees="";
	    @JsonProperty("ownedCapital")
	    private String ownedCapital="";
	    @JsonProperty("onlineBusiness")
	    private String onlineBusiness="";
	    @JsonProperty("offlineBusiness")
	    private String offlineBusiness="";
	    @JsonProperty("debtorDays")
	    private String debtorDays="";
	    @JsonProperty("currentAccountsReceived")
	    private String currentAccountsReceived="";
	    @JsonProperty("inventoryDays")
	    private String inventoryDays="";
	    @JsonProperty("currentInventoryAmount")
	    private String currentInventoryAmount="";
	    @JsonProperty("creditorDays")
	    private String creditorDays="";
	    @JsonProperty("currentAccountsPayable")
	    private String currentAccountsPayable="";
	    @JsonProperty("cashConversionCycleDays")
	    private String cashConversionCycleDays="";
	    @JsonProperty("cashConversionCycleLakhs")
	    private String cashConversionCycleLakhs="";
	    @JsonProperty("registeredFulfillment")
	    private boolean	 registeredFulfillment=false;
	    @JsonProperty("inventoryFulfillment")
	    private String inventoryFulfillment="";
	    @JsonProperty("otherCompany")
	    private boolean otherCompany=false;
	    private String reference="";
	    @JsonProperty("awardsAndRecognition")
	    private boolean awardsAndRecognition=false;
	    @JsonProperty("endorsementReceived")
	    private boolean endorsementReceived=false;
	    private boolean eligible=false;
	    @JsonProperty("commission")
	    private String commission="";
	    @JsonProperty("googleSearch")
	    private boolean googleSearch=false;
	    @JsonProperty("numOfRating")
	    private String numOfRating="";
	    @JsonProperty("previousNoOfReviews")
	    private String previousNoOfReviews="";
	    @JsonProperty("previousRating")
	    private String previousRating="";
	    @JsonProperty("ratingScore")
	    private String ratingScore="";
	    @JsonProperty("rbiDefaulter")
	    private boolean rbiDefaulter=false;
	    @JsonProperty("url")
	    private String url="";
		public String getNumOfRating() {
			return numOfRating;
		}
		public void setNumOfRating(String numOfRating) {
			this.numOfRating = numOfRating;
		}
		public String getPreviousNoOfReviews() {
			return previousNoOfReviews;
		}
		public void setPreviousNoOfReviews(String previousNoOfReviews) {
			this.previousNoOfReviews = previousNoOfReviews;
		}
		public String getPreviousRating() {
			return previousRating;
		}
		public void setPreviousRating(String previousRating) {
			this.previousRating = previousRating;
		}
		public String getRatingScore() {
			return ratingScore;
		}
		public void setRatingScore(String ratingScore) {
			this.ratingScore = ratingScore;
		}
		public boolean isRbiDefaulter() {
			return rbiDefaulter;
		}
		public void setRbiDefaulter(boolean rbiDefaulter) {
			this.rbiDefaulter = rbiDefaulter;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public boolean isGoogleSearch() {
			return googleSearch;
		}
		public void setGoogleSearch(boolean googleSearch) {
			this.googleSearch = googleSearch;
		}
		public String getCommission() {
			return commission;
		}
		public void setCommission(String commission) {
			this.commission = commission;
		}
		public String getProfitMargin() {
			return profitMargin;
		}
		public void setProfitMargin(String profitMargin) {
			this.profitMargin = profitMargin;
		}
		public double getCashDiscount() {
			return cashDiscount;
		}
		public void setCashDiscount(double cashDiscount) {
			this.cashDiscount = cashDiscount;
		}
		public String getProductRejection() {
			return productRejection;
		}
		public void setProductRejection(String productRejection) {
			this.productRejection = productRejection;
		}
		public String getNumOfEmployees() {
			return numOfEmployees;
		}
		public void setNumOfEmployees(String numOfEmployees) {
			this.numOfEmployees = numOfEmployees;
		}
		public String getOwnedCapital() {
			return ownedCapital;
		}
		public void setOwnedCapital(String ownedCapital) {
			this.ownedCapital = ownedCapital;
		}
		public String getOnlineBusiness() {
			return onlineBusiness;
		}
		public void setOnlineBusiness(String onlineBusiness) {
			this.onlineBusiness = onlineBusiness;
		}
		public String getOfflineBusiness() {
			return offlineBusiness;
		}
		public void setOfflineBusiness(String offlineBusiness) {
			this.offlineBusiness = offlineBusiness;
		}
		public String getDebtorDays() {
			return debtorDays;
		}
		public void setDebtorDays(String debtorDays) {
			this.debtorDays = debtorDays;
		}
		public String getCurrentAccountsReceived() {
			return currentAccountsReceived;
		}
		public void setCurrentAccountsReceived(String currentAccountsReceived) {
			this.currentAccountsReceived = currentAccountsReceived;
		}
		public String getInventoryDays() {
			return inventoryDays;
		}
		public void setInventoryDays(String inventoryDays) {
			this.inventoryDays = inventoryDays;
		}
		public String getCurrentInventoryAmount() {
			return currentInventoryAmount;
		}
		public void setCurrentInventoryAmount(String currentInventoryAmount) {
			this.currentInventoryAmount = currentInventoryAmount;
		}
		public String getCreditorDays() {
			return creditorDays;
		}
		public void setCreditorDays(String creditorDays) {
			this.creditorDays = creditorDays;
		}
		public String getCurrentAccountsPayable() {
			return currentAccountsPayable;
		}
		public void setCurrentAccountsPayable(String currentAccountsPayable) {
			this.currentAccountsPayable = currentAccountsPayable;
		}
		public String getCashConversionCycleDays() {
			return cashConversionCycleDays;
		}
		public void setCashConversionCycleDays(String cashConversionCycleDays) {
			this.cashConversionCycleDays = cashConversionCycleDays;
		}
		public String getCashConversionCycleLakhs() {
			return cashConversionCycleLakhs;
		}
		public void setCashConversionCycleLakhs(String cashConversionCycleLakhs) {
			this.cashConversionCycleLakhs = cashConversionCycleLakhs;
		}
		public boolean isRegisteredFulfillment() {
			return registeredFulfillment;
		}
		public void setRegisteredFulfillment(boolean registeredFulfillment) {
			this.registeredFulfillment = registeredFulfillment;
		}
		public String getInventoryFulfillment() {
			return inventoryFulfillment;
		}
		public void setInventoryFulfillment(String inventoryFulfillment) {
			this.inventoryFulfillment = inventoryFulfillment;
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
		public boolean isAwardsAndRecognition() {
			return awardsAndRecognition;
		}
		public void setAwardsAndRecognition(boolean awardsAndRecognition) {
			this.awardsAndRecognition = awardsAndRecognition;
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


		
	
	
	
}
