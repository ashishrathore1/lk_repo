package com.lk.credittemplate.beans;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountSegments {

	public String accountType;
	public String ownershipIndicator;
	public String currentBalance;
	public String reportingMemberShortName;
	public String accountNumber;
	// public String accountType;
	// String ownershipIndicator;
	public String dateOpened_Disbursed;
	public String dateofLastPayment;
	public String dateClosed;
	public String dateReportedandCertified;
	public String highCredit_SanctionedAmount;
	// public String currentBalance;,
	public String amountOverdue;
	public String paymentHistory1;
	public String paymentHistory2;
	public String paymentHistoryStartDate;
	public String paymentHistoryEndDate;
	/*
	 * public String SuitFiled_WilfulDefaul; public String
	 * writtenoffandSettledStatus; public String valueofCollateral; public
	 * String typeofCollateral;
	 */
	public String creditLimit;
	public String cashLimit;
	public String rateOfInterest;
	public String repaymentTenure;
	public String emiAmount;
	public String writtenoffAmount_Total;
	public String writtenoffAmount_Principal;
	public String settlementAmount;
	public String paymentFrequency;
	public String actualPaymentAmount;
	public String doeforErrorCode;
	public String errorCode;
	public String doeforCIBILRemarksCode;
	public String cIBILRemarksCode;
	public String doeforError_DisputeRemarksCode;
	public String error_DisputeRemarksCode1;
	public String error_DisputeRemarksCode2;

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getOwnershipIndicator() {
		return ownershipIndicator;
	}

	public void setOwnershipIndicator(String ownershipIndicator) {
		this.ownershipIndicator = ownershipIndicator;
	}

	public String getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(String currentBalance) {
		this.currentBalance = currentBalance;
	}

	public String getReportingMemberShortName() {
		return reportingMemberShortName;
	}

	public void setReportingMemberShortName(String reportingMemberShortName) {
		this.reportingMemberShortName = reportingMemberShortName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getDateOpened_Disbursed() {
		return dateOpened_Disbursed;
	}

	public void setDateOpened_Disbursed(String dateOpened_Disbursed) {
		this.dateOpened_Disbursed = dateOpened_Disbursed;
	}

	public String getDateofLastPayment() {
		return dateofLastPayment;
	}

	public void setDateofLastPayment(String dateofLastPayment) {
		this.dateofLastPayment = dateofLastPayment;
	}

	public String getDateClosed() {
		return dateClosed;
	}

	public void setDateClosed(String dateClosed) {
		this.dateClosed = dateClosed;
	}

	public String getDateReportedandCertified() {
		return dateReportedandCertified;
	}

	public void setDateReportedandCertified(String dateReportedandCertified) {
		this.dateReportedandCertified = dateReportedandCertified;
	}

	public String getHighCredit_SanctionedAmount() {
		return highCredit_SanctionedAmount;
	}

	public void setHighCredit_SanctionedAmount(String highCredit_SanctionedAmount) {
		this.highCredit_SanctionedAmount = highCredit_SanctionedAmount;
	}

	public String getAmountOverdue() {
		return amountOverdue;
	}

	public void setAmountOverdue(String amountOverdue) {
		this.amountOverdue = amountOverdue;
	}

	public String getPaymentHistory1() {
		return paymentHistory1;
	}

	public void setPaymentHistory1(String paymentHistory1) {
		this.paymentHistory1 = paymentHistory1;
	}

	public String getPaymentHistory2() {
		return paymentHistory2;
	}

	public void setPaymentHistory2(String paymentHistory2) {
		this.paymentHistory2 = paymentHistory2;
	}

	public String getPaymentHistoryStartDate() {
		return paymentHistoryStartDate;
	}

	public void setPaymentHistoryStartDate(String paymentHistoryStartDate) {
		this.paymentHistoryStartDate = paymentHistoryStartDate;
	}

	public String getPaymentHistoryEndDate() {
		return paymentHistoryEndDate;
	}

	public void setPaymentHistoryEndDate(String paymentHistoryEndDate) {
		this.paymentHistoryEndDate = paymentHistoryEndDate;
	}

	public String getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getCashLimit() {
		return cashLimit;
	}

	public void setCashLimit(String cashLimit) {
		this.cashLimit = cashLimit;
	}

	public String getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(String rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}

	public String getRepaymentTenure() {
		return repaymentTenure;
	}

	public void setRepaymentTenure(String repaymentTenure) {
		this.repaymentTenure = repaymentTenure;
	}

	public String getEmiAmount() {
		return emiAmount;
	}

	public void setEmiAmount(String emiAmount) {
		this.emiAmount = emiAmount;
	}

	public String getWrittenoffAmount_Total() {
		return writtenoffAmount_Total;
	}

	public void setWrittenoffAmount_Total(String writtenoffAmount_Total) {
		this.writtenoffAmount_Total = writtenoffAmount_Total;
	}

	public String getWrittenoffAmount_Principal() {
		return writtenoffAmount_Principal;
	}

	public void setWrittenoffAmount_Principal(String writtenoffAmount_Principal) {
		this.writtenoffAmount_Principal = writtenoffAmount_Principal;
	}

	public String getSettlementAmount() {
		return settlementAmount;
	}

	public void setSettlementAmount(String settlementAmount) {
		this.settlementAmount = settlementAmount;
	}

	public String getPaymentFrequency() {
		return paymentFrequency;
	}

	public void setPaymentFrequency(String paymentFrequency) {
		this.paymentFrequency = paymentFrequency;
	}

	public String getActualPaymentAmount() {
		return actualPaymentAmount;
	}

	public void setActualPaymentAmount(String actualPaymentAmount) {
		this.actualPaymentAmount = actualPaymentAmount;
	}

	public String getDoeforErrorCode() {
		return doeforErrorCode;
	}

	public void setDoeforErrorCode(String doeforErrorCode) {
		this.doeforErrorCode = doeforErrorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getDoeforCIBILRemarksCode() {
		return doeforCIBILRemarksCode;
	}

	public void setDoeforCIBILRemarksCode(String doeforCIBILRemarksCode) {
		this.doeforCIBILRemarksCode = doeforCIBILRemarksCode;
	}

	public String getcIBILRemarksCode() {
		return cIBILRemarksCode;
	}

	public void setcIBILRemarksCode(String cIBILRemarksCode) {
		this.cIBILRemarksCode = cIBILRemarksCode;
	}

	public String getDoeforError_DisputeRemarksCode() {
		return doeforError_DisputeRemarksCode;
	}

	public void setDoeforError_DisputeRemarksCode(String doeforError_DisputeRemarksCode) {
		this.doeforError_DisputeRemarksCode = doeforError_DisputeRemarksCode;
	}

	public String getError_DisputeRemarksCode1() {
		return error_DisputeRemarksCode1;
	}

	public void setError_DisputeRemarksCode1(String error_DisputeRemarksCode1) {
		this.error_DisputeRemarksCode1 = error_DisputeRemarksCode1;
	}

	public String getError_DisputeRemarksCode2() {
		return error_DisputeRemarksCode2;
	}

	public void setError_DisputeRemarksCode2(String error_DisputeRemarksCode2) {
		this.error_DisputeRemarksCode2 = error_DisputeRemarksCode2;
	}

}
