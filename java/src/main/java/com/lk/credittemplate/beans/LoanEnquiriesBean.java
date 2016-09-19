package com.lk.credittemplate.beans;

public class LoanEnquiriesBean {

	private int sixMonthlyEnquiry;
	private int yearlyEnquiry;
	private int totalLoanEnquiries;
	private int threeMonthlyEnquiries;
	
	public int getTotalLoanEnquiries() {
		return totalLoanEnquiries;
	}
	public void setTotalLoanEnquiries(int totalLoanEnquiries) {
		this.totalLoanEnquiries = totalLoanEnquiries;
	}
	public int getThreeMonthlyEnquiries() {
		return threeMonthlyEnquiries;
	}
	public void setThreeMonthlyEnquiries(int threeMonthlyEnquiries) {
		this.threeMonthlyEnquiries = threeMonthlyEnquiries;
	}
	public int getSixMonthlyEnquiry() {
		return sixMonthlyEnquiry;
	}
	public void setSixMonthlyEnquiry(int sixMonthlyEnquiry) {
		this.sixMonthlyEnquiry = sixMonthlyEnquiry;
	}
	public int getYearlyEnquiry() {
		return yearlyEnquiry;
	}
	public void setYearlyEnquiry(int yearlyEnquiry) {
		this.yearlyEnquiry = yearlyEnquiry;
	}
	
}
