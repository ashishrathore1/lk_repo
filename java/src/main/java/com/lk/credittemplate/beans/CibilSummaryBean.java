package com.lk.credittemplate.beans;

public class CibilSummaryBean {

	private int overdueLoanCount;
	private int outstandingLoanCount;
	private int settledLoanCount;
	private int writtenOffLoanCount;
	
	private long sanctionedAmtTotal;
	private long outstandingTotal;
	private long amtOverdueTotal;
	private long delaymcountTotal;
	
	private int maxLateDays;
	private int minlastDelay;
	
	public int getOverdueLoanCount() {
		return overdueLoanCount;
	}

	public void setOverdueLoanCount(int overdueLoanCount) {
		this.overdueLoanCount = overdueLoanCount;
	}

	public int getOutstandingLoanCount() {
		return outstandingLoanCount;
	}

	public void setOutstandingLoanCount(int outstandingLoanCount) {
		this.outstandingLoanCount = outstandingLoanCount;
	}

	public int getSettledLoanCount() {
		return settledLoanCount;
	}

	public void setSettledLoanCount(int settledLoanCount) {
		this.settledLoanCount = settledLoanCount;
	}

	public int getWrittenOffLoanCount() {
		return writtenOffLoanCount;
	}

	public void setWrittenOffLoanCount(int writtenOffLoanCount) {
		this.writtenOffLoanCount = writtenOffLoanCount;
	}

	public long getSanctionedAmtTotal() {
		return sanctionedAmtTotal;
	}

	public void setSanctionedAmtTotal(long sanctionedAmtTotal) {
		this.sanctionedAmtTotal = sanctionedAmtTotal;
	}

	public long getOutstandingTotal() {
		return outstandingTotal;
	}

	public void setOutstandingTotal(long outstandingTotal) {
		this.outstandingTotal = outstandingTotal;
	}

	public long getAmtOverdueTotal() {
		return amtOverdueTotal;
	}

	public void setAmtOverdueTotal(long amtOverdueTotal) {
		this.amtOverdueTotal = amtOverdueTotal;
	}

	public long getDelaymcountTotal() {
		return delaymcountTotal;
	}

	public void setDelaymcountTotal(long delaymcountTotal) {
		this.delaymcountTotal = delaymcountTotal;
	}

	public int getMaxLateDays() {
		return maxLateDays;
	}

	public void setMaxLateDays(int maxLateDays) {
		this.maxLateDays = maxLateDays;
	}

	public int getMinlastDelay() {
		return minlastDelay;
	}

	public void setMinlastDelay(int minlastDelay) {
		this.minlastDelay = minlastDelay;
	}

}
