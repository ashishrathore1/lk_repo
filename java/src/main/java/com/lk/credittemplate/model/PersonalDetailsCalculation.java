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
@Table(name = "LK_PD_CALCULATIONS")

@NamedQueries({

		//@NamedQuery(name = "findStatusByStatusId", query = "SELECT s FROM StatusTracker s WHERE s.flag=:flag"),
		//@NamedQuery(name = "findAllStatus", query = "SELECT s FROM StatusTracker s WHERE s.applicationId IN(:applicationId)"),
		//@NamedQuery(name = "getStatusTrackerByAppId", query = "SELECT s FROM StatusTracker s WHERE s.applicationId =:applicationId")
		//@NamedQuery(name="updateStatusTrackerById", query="UPDATE  statusTracker s  WHERE s.applicationId=:applicationId")
	     // @NamedQuery(name = "findCompanyApplication", query = "SELECT l FROM PersonalDetailsCompany l WHERE l.appID=:appID"),
	      @NamedQuery(name = "getPersonalDetailsCalculationsById", query = "SELECT l FROM PersonalDetailsCalculation l WHERE l.CalappId=:CalappId"),
	      @NamedQuery(name = "deletePersonalDetailsCalculationsById", query = "DELETE FROM PersonalDetailsCalculation s WHERE s.CalappId=:CalappId")
	      //DELETE FROM PersonalDetailsMarket e WHERE e.marketGuid = :marketGuid
})
public class PersonalDetailsCalculation implements Serializable {

	private static final long serialVersionUID = 2805838946323485598L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long Id;

	@Column(name = "LK_APP_COL_ID")
	private int calId;
	
	@Column(name = "LK_APP_ID")
	private String CalappId;

	@Column(name = "LK_SANCTIONED_AMOUNT")
	private double sanctionedAmount;

	@Column(name = "LK_RATE_INTEREST")
	private double rateOfInterest;
	
	@Column(name = "LK_INTEREST_TYPE")
	private String interestType;

	@Column(name = "LK_DURATION")
	private double duration;
	

	@Column(name = "LK_INSTALLMENT_FREQUENCY")
	private String installmentFrequency;
	
	@Column(name = "LK_INSTALLMENT_AMOUNT")
	private double installmentAmount;
	
	@Column(name = "LK_DISBURSEMENT_DATE")
	private Date disbursementDate;
	
	@Column(name = "LK_INSTALLMENT_ENDDATE")
	private Date installmentEndDate;
	
	@Column(name = "LK_LATE_PAYMENT_DAYS")
	private int latePaymentDays;
	
	@Column(name = "LK_INSTALLMENT_DELAYS")
	private double installmentDelays;
	
	@Column(name = "LK_MAX_DELAY_DAYS")
	private double maxDelayDays;
	
	@Column(name = "LK_OUTSTANDING_AMOUNT")
	private double outstandingAmount;
	
	@Column(name = "LK_LATE_PAYMENT_CHARGES")
	private double latePaymentCharges;
	
	@Column(name = "LK_ECOLLECTION")
	private boolean eCollection;
	
	@Column(name = "LK_NACH_ACTIVATION")
	private boolean nachActivation;
	
	@Column(name = "LK_DISBURSEMENT_ACCOUNT_NUM")
	private String bankAccountNum;
	



	public long getId() {
		return Id;
	}



	public void setId(long id) {
		Id = id;
	}



	public int getCalId() {
		return calId;
	}



	public void setCalId(int calId) {
		this.calId = calId;
	}



	public String getCalappId() {
		return CalappId;
	}



	public void setCalappId(String calappId) {
		CalappId = calappId;
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



	public int getLatePaymentDays() {
		return latePaymentDays;
	}



	public void setLatePaymentDays(int latePaymentDays) {
		this.latePaymentDays = latePaymentDays;
	}



	public double getInstallmentDelays() {
		return installmentDelays;
	}



	public void setInstallmentDelays(double installmentDelays) {
		this.installmentDelays = installmentDelays;
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



	public double getLatePaymentCharges() {
		return latePaymentCharges;
	}



	public void setLatePaymentCharges(double latePaymentCharges) {
		this.latePaymentCharges = latePaymentCharges;
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



	public String getBankAccountNum() {
		return bankAccountNum;
	}



	public void setBankAccountNum(String bankAccountNum) {
		this.bankAccountNum = bankAccountNum;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	



	
}
