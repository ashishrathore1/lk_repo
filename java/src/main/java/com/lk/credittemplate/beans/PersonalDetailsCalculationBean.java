package com.lk.credittemplate.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonalDetailsCalculationBean {

	
	@JsonProperty("appId")
	private String appId="";
	@JsonProperty("columnId")
	private int calId=1;
	@JsonProperty("sanctionedAmount")
    private double sanctionedAmount=0.0;
    @JsonProperty("rateOfInterest")
    private double rateOfInterest=0.0;
    @JsonProperty("interestType")
    private String interestType="";
    @JsonProperty("duration")
    private double duration=0.0;
    @JsonProperty("installmentFrequency")
    private String installmentFrequency="";
    @JsonProperty("installmentAmount")
    private double installmentAmount=0.0;
    @JsonProperty("disbursementDate")
    private Date disbursementDate= new Date();
    @JsonProperty("installmentEndDate")
    private Date installmentEndDate= new Date();
    @JsonProperty("latePaymentInDays")
    private int latePaymentInDays=0;
    @JsonProperty("installmentWithDelay")
    private double installmentWithDelay=0.0;
    @JsonProperty("maxDelayDays")
    private double maxDelayDays=0.0;
    @JsonProperty("outstandingAmount")
    private double outstandingAmount=0.0;
    @JsonProperty("latePaymentChargesDue")
    private double latePaymentChargesDue=0.0;
    @JsonProperty("eCollection")
    private boolean eCollection=false;
    @JsonProperty("nachActivation")
    private boolean nachActivation=false;
	@JsonProperty("disbursementAccNum")
    private String disbursementAccNum="";
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public int getCalId() {
		return calId;
	}
	public void setCalId(int calId) {
		this.calId = calId;
	}
	public double getSanctionedAmount() {
		return sanctionedAmount;
	}
	public void setSanctionedAmount(double sanctionedAmount) {
		this.sanctionedAmount = sanctionedAmount;
	}
	public double getRateOfInterest() {
		return rateOfInterest;
	}
	public void setRateOfInterest(double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}
	public String getInterestType() {
		return interestType;
	}
	public void setInterestType(String interestType) {
		this.interestType = interestType;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public String getInstallmentFrequency() {
		return installmentFrequency;
	}
	public void setInstallmentFrequency(String installmentFrequency) {
		this.installmentFrequency = installmentFrequency;
	}
	public double getInstallmentAmount() {
		return installmentAmount;
	}
	public void setInstallmentAmount(double installmentAmount) {
		this.installmentAmount = installmentAmount;
	}
	public Date getDisbursementDate() {
		return disbursementDate;
	}
	public void setDisbursementDate(Date disbursementDate) {
		this.disbursementDate = disbursementDate;
	}
	public Date getInstallmentEndDate() {
		return installmentEndDate;
	}
	public void setInstallmentEndDate(Date installmentEndDate) {
		this.installmentEndDate = installmentEndDate;
	}
	public int getLatePaymentInDays() {
		return latePaymentInDays;
	}
	public void setLatePaymentInDays(int latePaymentInDays) {
		this.latePaymentInDays = latePaymentInDays;
	}
	public double getInstallmentWithDelay() {
		return installmentWithDelay;
	}
	public void setInstallmentWithDelay(double installmentWithDelay) {
		this.installmentWithDelay = installmentWithDelay;
	}
	public double getMaxDelayDays() {
		return maxDelayDays;
	}
	public void setMaxDelayDays(double maxDelayDays) {
		this.maxDelayDays = maxDelayDays;
	}
	public double getOutstandingAmount() {
		return outstandingAmount;
	}
	public void setOutstandingAmount(double outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}
	public double getLatePaymentChargesDue() {
		return latePaymentChargesDue;
	}
	public void setLatePaymentChargesDue(double latePaymentChargesDue) {
		this.latePaymentChargesDue = latePaymentChargesDue;
	}
	public boolean iseCollection() {
		return eCollection;
	}
	public void seteCollection(boolean eCollection) {
		this.eCollection = eCollection;
	}
	public boolean isNachActivation() {
		return nachActivation;
	}
	public void setNachActivation(boolean nachActivation) {
		this.nachActivation = nachActivation;
	}
	public String getDisbursementAccNum() {
		return disbursementAccNum;
	}
	public void setDisbursementAccNum(String disbursementAccNum) {
		this.disbursementAccNum = disbursementAccNum;
	}
    
	
	
       
    


		
	
	
	
}
