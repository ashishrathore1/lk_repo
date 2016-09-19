package com.lk.credittemplate.beans;

import java.util.List;

public class CommentsBean {

	private List<UnitCommentsBean> unitCommentsList;
	private String rejectionReason;
	
	public List<UnitCommentsBean> getUnitCommentsList() {
		return unitCommentsList;
	}
	public void setUnitCommentsList(List<UnitCommentsBean> unitCommentsList) {
		this.unitCommentsList = unitCommentsList;
	}
	public String getRejectionReason() {
		return rejectionReason;
	}
	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}
	
	
}
