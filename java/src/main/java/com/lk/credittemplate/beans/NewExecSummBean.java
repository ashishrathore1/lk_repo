package com.lk.credittemplate.beans;

import java.util.List;

public class NewExecSummBean {

	List<SingleValExecSummBean> singleValList;
	
	List<DoubleValExecSummBean> doubleValList;

	public List<SingleValExecSummBean> getSingleValList() {
		return singleValList;
	}

	public void setSingleValList(List<SingleValExecSummBean> singleValList) {
		this.singleValList = singleValList;
	}

	public List<DoubleValExecSummBean> getDoubleValList() {
		return doubleValList;
	}

	public void setDoubleValList(List<DoubleValExecSummBean> doubleValList) {
		this.doubleValList = doubleValList;
	}
	
}
