package com.lk.credittemplate.beans;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EditTableBean {

	private List<ResultBean> resultList;
	private String appId;
	private String tableName;
	private int pos;
	private String val;

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public List<ResultBean> getResultList() {
		return resultList;
	}

	public void setResultList(List<ResultBean> resultList) {
		this.resultList = resultList;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
