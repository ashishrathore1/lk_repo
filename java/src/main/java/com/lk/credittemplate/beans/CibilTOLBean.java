package com.lk.credittemplate.beans;


public class CibilTOLBean {

	private long sanctionedAmt;
	
	private long outstanding;
	
	private double weightedAmtA;

	private double weightedAmtB;

	private String remark1;
	
	private String remark2;

	private long applicantId;
	
	private String loanId;
	
	private String type;

	public long getSanctionedAmt() {
		return sanctionedAmt;
	}

	public void setSanctionedAmt(long sanctionedAmt) {
		this.sanctionedAmt = sanctionedAmt;
	}

	public long getOutstanding() {
		return outstanding;
	}

	public void setOutstanding(long outstanding) {
		this.outstanding = outstanding;
	}

	public double getWeightedAmtA() {
		return weightedAmtA;
	}

	public void setWeightedAmtA(double weightedAmtA) {
		this.weightedAmtA = weightedAmtA;
	}

	public double getWeightedAmtB() {
		return weightedAmtB;
	}

	public void setWeightedAmtB(double weightedAmtB) {
		this.weightedAmtB = weightedAmtB;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public long getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(long applicantId) {
		this.applicantId = applicantId;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
