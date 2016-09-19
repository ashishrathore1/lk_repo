package com.lk.credittemplate.beans;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CibilResponeBean {

	private String type;
	private long sancAmt;
	private long outStnding;
	private String status;
	private int maxLateDays;
	private int amtOverdue;
	private int delayMonthsCount;
	private String lastDelay;
	private int tenure;
	private float rate;
	private String category;
	private String curnonCur;
	private long applicantId;
	private String date;
	private boolean newRecord;
	private long cibilId;
	
	
	public long getCibilId() {
		return cibilId;
	}

	public void setCibilId(long cibilId) {
		this.cibilId = cibilId;
	}

	public boolean isNewRecord() {
		return newRecord;
	}

	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(long applicantId) {
		this.applicantId = applicantId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public int getAmtOverdue() {
		return amtOverdue;
	}

	public void setAmtOverdue(int amtOverdue) {
		this.amtOverdue = amtOverdue;
	}

	public int getMaxLateDays() {
		return maxLateDays;
	}

	public void setMaxLateDays(int maxLateDays) {
		this.maxLateDays = maxLateDays;
	}

	public int getDelayMonthsCount() {
		return delayMonthsCount;
	}

	public void setDelayMonthsCount(int delayMonthsCount) {
		this.delayMonthsCount = delayMonthsCount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCurnonCur() {
		return curnonCur;
	}

	public void setCurnonCur(String curnonCur) {
		this.curnonCur = curnonCur;
	}

	public long getSancAmt() {
		return sancAmt;
	}

	public void setSancAmt(long sancAmt) {
		this.sancAmt = sancAmt;
	}

	public long getOutStnding() {
		return outStnding;
	}

	public void setOutStnding(long outStnding) {
		this.outStnding = outStnding;
	}

	public String getLastDelay() {
		return lastDelay;
	}

	public void setLastDelay(String lastDelay) {
		this.lastDelay = lastDelay;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}
	
}