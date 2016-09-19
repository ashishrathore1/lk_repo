package com.lk.credittemplate.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonalKYCBean {

	    @JsonProperty("companyPan")
	    private String companyPan="";
	    @JsonProperty("registeredAddress")
	    private String registeredAddress="";
	    @JsonProperty("ownership")
	    private boolean ownership=false;
	    @JsonProperty("pinCode")
	    private String pinCode="";
	    @JsonProperty("city")
	    private String city="";
	    @JsonProperty("state")
	    private String state="";
	    @JsonProperty("communicationAddress")
	    private String communicationAddress="";
	    @JsonProperty("warehouseAddress")
	    private String warehouseAddress="";
	    @JsonProperty("whOwnership")
	    private boolean whOwnership=false;
	    @JsonProperty ("whPincode")
	    private String whPincode="";
	    @JsonProperty ("whCity")
	    private String whCity="";
	    @JsonProperty ("whState")
	    private String whState="";
	    @JsonProperty("vatNum")
	    private String vatNum="";
	    @JsonProperty("numOfVat")
	    private String numOfVat="";
	    @JsonProperty("serviceTax")
	    private String serviceTax="";
	    @JsonProperty("numOfServiceTax")
	    private String numOfServiceTax="";
		public String getCompanyPan() {
			return companyPan;
		}
		public void setCompanyPan(String companyPan) {
			this.companyPan = companyPan;
		}
		public String getRegisteredAddress() {
			return registeredAddress;
		}
		public void setRegisteredAddress(String registeredAddress) {
			this.registeredAddress = registeredAddress;
		}
		public String getPinCode() {
			return pinCode;
		}
		public void setPinCode(String pinCode) {
			this.pinCode = pinCode;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}

		public boolean isOwnership() {
			return ownership;
		}
		public void setOwnership(boolean ownership) {
			this.ownership = ownership;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getCommunicationAddress() {
			return communicationAddress;
		}
		public void setCommunicationAddress(String communicationAddress) {
			this.communicationAddress = communicationAddress;
		}
		public String getWarehouseAddress() {
			return warehouseAddress;
		}
		public void setWarehouseAddress(String warehouseAddress) {
			this.warehouseAddress = warehouseAddress;
		}
		public boolean isWhOwnership() {
			return whOwnership;
		}
		public void setWhOwnership(boolean whOwnership) {
			this.whOwnership = whOwnership;
		}
		public String getWhPincode() {
			return whPincode;
		}
		public void setWhPincode(String whPincode) {
			this.whPincode = whPincode;
		}
		public String getWhCity() {
			return whCity;
		}
		public void setWhCity(String whCity) {
			this.whCity = whCity;
		}
		public String getWhState() {
			return whState;
		}
		public void setWhState(String whState) {
			this.whState = whState;
		}
		public String getVatNum() {
			return vatNum;
		}
		public void setVatNum(String vatNum) {
			this.vatNum = vatNum;
		}
		public String getNumOfVat() {
			return numOfVat;
		}
		public void setNumOfVat(String numOfVat) {
			this.numOfVat = numOfVat;
		}
		public String getServiceTax() {
			return serviceTax;
		}
		public void setServiceTax(String serviceTax) {
			this.serviceTax = serviceTax;
		}
		public String getNumOfServiceTax() {
			return numOfServiceTax;
		}
		public void setNumOfServiceTax(String numOfServiceTax) {
			this.numOfServiceTax = numOfServiceTax;
		}
	     



		
	
	
	
}
