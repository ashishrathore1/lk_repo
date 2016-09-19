package com.lk.credittemplate.beans;

import java.util.List;

public class CibilLoanSummaryBean {

	private List<CibilTOLBean> tolList;
	private String name;
	private long totalSancAmt;
	private long totalOutstanding;
	private double totalWeightedAmtA;
	private double totalWeightedAmtB;
	private long applicantId;
	

	public long getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(long applicantId) {
		this.applicantId = applicantId;
	}

	public long getTotalSancAmt() {
		return totalSancAmt;
	}

	public void setTotalSancAmt(long totalSancAmt) {
		this.totalSancAmt = totalSancAmt;
	}

	public long getTotalOutstanding() {
		return totalOutstanding;
	}

	public void setTotalOutstanding(long totalOutstanding) {
		this.totalOutstanding = totalOutstanding;
	}

	public double getTotalWeightedAmtA() {
		return totalWeightedAmtA;
	}

	public void setTotalWeightedAmtA(double totalWeightedAmtA) {
		this.totalWeightedAmtA = totalWeightedAmtA;
	}

	public double getTotalWeightedAmtB() {
		return totalWeightedAmtB;
	}

	public void setTotalWeightedAmtB(double totalWeightedAmtB) {
		this.totalWeightedAmtB = totalWeightedAmtB;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CibilTOLBean> getTolList() {
		return tolList;
	}

	public void setTolList(List<CibilTOLBean> tolList) {
		this.tolList = tolList;
	} 
	
}
