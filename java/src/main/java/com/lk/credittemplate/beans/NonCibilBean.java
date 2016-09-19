package com.lk.credittemplate.beans;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NonCibilBean {

    private String particulars;
	
	private String typeofloan;
	
	private String openeddate;
	
	private long sanctionedamt;
	
	private long outstanding;
	
	private long emi;
	
	private int duration;
	
	private String currentnoncur;
	
	private String category;
	
	private String loanId;

	
	public String getParticulars() {
		return particulars;
	}

	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}

	public String getTypeofloan() {
		return typeofloan;
	}

	public void setTypeofloan(String typeofloan) {
		this.typeofloan = typeofloan;
	}

	public String getOpeneddate() {
		return openeddate;
	}

	public void setOpeneddate(String openeddate) {
		this.openeddate = openeddate;
	}

	public long getSanctionedamt() {
		return sanctionedamt;
	}

	public void setSanctionedamt(long sanctionedamt) {
		this.sanctionedamt = sanctionedamt;
	}

	public long getOutstanding() {
		return outstanding;
	}

	public void setOutstanding(long outstanding) {
		this.outstanding = outstanding;
	}

	public long getEmi() {
		return emi;
	}

	public void setEmi(long emi) {
		this.emi = emi;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getCurrentnoncur() {
		return currentnoncur;
	}

	public void setCurrentnoncur(String currentnoncur) {
		this.currentnoncur = currentnoncur;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}
	
}
