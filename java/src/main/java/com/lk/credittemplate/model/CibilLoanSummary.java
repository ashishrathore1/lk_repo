package com.lk.credittemplate.model;


public class CibilLoanSummary {


	private String type;
	private int weightageA;
	private String remarkA;
	private int weightedB;
	private String remarkB;
	private int multiplier;
	private String category;
	
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getMultiplier() {
		return multiplier;
	}
	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getWeightageA() {
		return weightageA;
	}
	public void setWeightageA(int weightageA) {
		this.weightageA = weightageA;
	}
	public String getRemarkA() {
		return remarkA;
	}
	public void setRemarkA(String remarkA) {
		this.remarkA = remarkA;
	}
	public int getWeightedB() {
		return weightedB;
	}
	public void setWeightedB(int weightedB) {
		this.weightedB = weightedB;
	}
	public String getRemarkB() {
		return remarkB;
	}
	public void setRemarkB(String remarkB) {
		this.remarkB = remarkB;
	}

}
