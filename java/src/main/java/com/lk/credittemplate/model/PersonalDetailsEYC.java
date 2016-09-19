package com.lk.credittemplate.model;

import java.io.Serializable;
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
@Table(name = "LK_PD_EYC_DETAILS")

@NamedQueries({

	      @NamedQuery(name = "getKycByName", query = "SELECT l FROM PersonalDetailsEYC l WHERE l.appId=:appId")

})
public class PersonalDetailsEYC implements Serializable {

	private static final long serialVersionUID = 2805838946323485598L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long Id;

	@Column(name = "LK_APP_ID")
	private String appId;

	@Column(name = "LK_COMPANY_PAN")
	private String companyPan;

	@Column(name = "LK_REGISTERED_ADDRESS")
	private String registeredAddress;

	@Column(name = "LK_OWNERSHIP")
	private boolean ownership;

	@Column(name = "LK_PIN_CODE")
	private String pinCode;

	@Column(name = "LK_CITY")
	private String city;

	@Column(name = "LK_STATE")
	private String state;

	@Column(name = "LK_COMMUNICATION_ADDRESS")
	private String communicationAddress;
	
	@Column(name = "LK_WAREHOUSE_ADDRESS")
	private String warehouseAddress;
	
	@Column(name = "LK_WAREHOUSE_OWNERSHIP")
	private boolean whownership;
	
	@Column(name = "LK_WAREHOUSE_PINCODE")
	private String whpinCode;
	
	@Column(name = "LK_WAREHOUSE_CITY")
	private String whcity;
	
	@Column(name = "LK_WAREHOUSE_STATE")
	private String whstate;
	
	@Column(name = "LK_VAT_NO")
	private String vatNum;
	
	@Column(name = "LK_NO_VAT_MONTHLY")
	private String numOfVatMonthly;
	
	@Column(name = "LK_SERVICE_TAX")
	private String serviceTax;
	
	@Column(name = "LK_NO_SERVICE_TAX")
	private String numOfServiceTax;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}


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

	public boolean isOwnership() {
		return ownership;
	}

	public void setOwnership(boolean ownership) {
		this.ownership = ownership;
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

	public boolean isWhownership() {
		return whownership;
	}

	public void setWhownership(boolean whownership) {
		this.whownership = whownership;
	}

	public String getWhpinCode() {
		return whpinCode;
	}

	public void setWhpinCode(String whpinCode) {
		this.whpinCode = whpinCode;
	}

	public String getWhcity() {
		return whcity;
	}

	public void setWhcity(String whcity) {
		this.whcity = whcity;
	}

	public String getWhstate() {
		return whstate;
	}

	public void setWhstate(String whstate) {
		this.whstate = whstate;
	}

	public String getVatNum() {
		return vatNum;
	}

	public void setVatNum(String vatNum) {
		this.vatNum = vatNum;
	}

	public String getNumOfVatMonthly() {
		return numOfVatMonthly;
	}

	public void setNumOfVatMonthly(String string) {
		this.numOfVatMonthly = string;
	}

	public String getServiceTax() {
		return serviceTax;
	}

	public void setServiceTax(String string) {
		this.serviceTax = string;
	}

	public String getNumOfServiceTax() {
		return numOfServiceTax;
	}

	public void setNumOfServiceTax(String string) {
		this.numOfServiceTax = string;
	}

	
	

}


	

