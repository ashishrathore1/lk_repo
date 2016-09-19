/**
 * 
 */
package com.lk.credittemplate.beans.ds;

import java.util.Date;

import com.lk.credittemplate.common.util.LKUtils;

/**
 * @author ramamohan
 *
 */
public class LoanVarScoreBean {

	private Long id;

	private VarScoreBean varScoreBean;

	private String loanApplicationId;

	private String value;

	private double varScore;

	private String version;

	private Date createdDate = LKUtils.getDate();

	private Date updatedDate = LKUtils.getDate();

	private String operatedBy;

	public Long getId() {
		return id;
	}

	public VarScoreBean getVarScoreBean() {
		return varScoreBean;
	}

	public String getLoanApplicationId() {
		return loanApplicationId;
	}

	public String getValue() {
		return value;
	}

	public double getVarScore() {
		return varScore;
	}

	public String getVersion() {
		return version;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public String getOperatedBy() {
		return operatedBy;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVarScoreBean(VarScoreBean varScoreBean) {
		this.varScoreBean = varScoreBean;
	}

	public void setLoanApplicationId(String loanApplicationId) {
		this.loanApplicationId = loanApplicationId;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setVarScore(double varScore) {
		this.varScore = varScore;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setOperatedBy(String operatedBy) {
		this.operatedBy = operatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "LoanVarScoreBean [id=" + id + ", varScoreBean=" + varScoreBean + ", loanApplicationId="
				+ loanApplicationId + ", value=" + value + ", varScore=" + varScore + ", version=" + version
				+ ", createdDate=" + createdDate + ", operatedBy=" + operatedBy + ", updatedDate=" + updatedDate + "]";
	}
}
