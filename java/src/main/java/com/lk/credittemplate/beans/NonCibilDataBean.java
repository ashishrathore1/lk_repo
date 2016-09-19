package com.lk.credittemplate.beans;

import java.util.List;

public class NonCibilDataBean {

	private List<NonCibilBean> nonCibilBeanList;
	private long totalSancAmt;
	private long totaloutstandingAmt;
	private String name;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<NonCibilBean> getNonCibilBeanList() {
		return nonCibilBeanList;
	}
	public void setNonCibilBeanList(List<NonCibilBean> nonCibilBeanList) {
		this.nonCibilBeanList = nonCibilBeanList;
	}
	public long getTotalSancAmt() {
		return totalSancAmt;
	}
	public void setTotalSancAmt(long totalSancAmt) {
		this.totalSancAmt = totalSancAmt;
	}
	public long getTotaloutstandingAmt() {
		return totaloutstandingAmt;
	}
	public void setTotaloutstandingAmt(long totaloutstandingAmt) {
		this.totaloutstandingAmt = totaloutstandingAmt;
	}
	
	
	
}






