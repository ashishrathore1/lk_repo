package com.lk.credittemplate.beans;

import java.util.List;

public class DoubleValExecSummBean {


	List<MultipleValBean> resultList;
	
	
	private String tName;

	
	public List<MultipleValBean> getResultList() {
		return resultList;
	}

	public void setResultList(List<MultipleValBean> resultList) {
		this.resultList = resultList;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}
}
