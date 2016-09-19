/**
 * 
 */
package com.lk.credittemplate.model.ds;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lk.credittemplate.common.util.LKUtils;

/**
 * @author ramamohan
 *
 */
@Entity
@Table(name = "LK_LOAN_VARSCORE_MASTER")
@NamedQueries({

		@NamedQuery(name = "getLoanVarScoresByLoanId", query = "SELECT lv FROM LoanVarScoreModel AS lv WHERE lv.loanApplicationId = :loanApplicationId "
				+ "AND lv.version = (SELECT max(lvs.version) FROM LoanVarScoreModel AS lvs)")

})
public class LoanVarScoreModel {

	@Id
	@GeneratedValue
	@Column(name = "LV_GUID", nullable = false)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LV_VARSCORE_ID")
	private VarScoreModel varScoreModel;

	@Column(name = "LV_LOAN_APPLICATION_ID", nullable = false)
	private String loanApplicationId;

	@Column(name = "LV_VALUE")
	private String value;

	@Column(name = "LV_VARSCORE")
	private double varScore;

	@Column(name = "LV_VERSION", nullable = false)
	private String version;

	@Column(name = "LV_CREATED_DATE", nullable = false)
	private Date createdDate;

	@Column(name = "LV_UPDATED_DATE", nullable = false)
	private Date updatedDate;

	@Column(name = "LV_OPERATED_BY")
	private String operatedBy;

	public Long getId() {
		return id;
	}

	public VarScoreModel getVarScoreModel() {
		return varScoreModel;
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

	public void setVarScoreModel(VarScoreModel varScoreBean) {
		this.varScoreModel = varScoreBean;
	}

	public void setLoanApplicationId(String loanApplicationId) {
		this.loanApplicationId = loanApplicationId;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setVarScore(double varScore) {
		this.varScore = LKUtils.doubleRound(varScore);
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
		return "LoanVarScoreModel [id=" + id + ", varScoreModel=" + varScoreModel + ", loanApplicationId="
				+ loanApplicationId + ", value=" + value + ", varScore=" + varScore + ", version=" + version
				+ ", createdDate=" + createdDate + ", operatedBy=" + operatedBy + ", updatedDate=" + updatedDate + "]";
	}
}
