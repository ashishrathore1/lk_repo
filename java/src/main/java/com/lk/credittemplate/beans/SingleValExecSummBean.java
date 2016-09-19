package com.lk.credittemplate.beans;

import java.util.List;

public class SingleValExecSummBean {

	
	List<ResultKeyBean> resultList;
	

	public List<ResultKeyBean> getResultList() {
		return resultList;
	}

	public void setResultList(List<ResultKeyBean> resultList) {
		this.resultList = resultList;
	}

	private String tName;

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}
	
}
