/**
 * 
 */
package com.lk.credittemplate.model.ds;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.lk.credittemplate.common.util.LKUtils;

/**
 * @author ramamohan
 *
 */
@Entity
@Table(name = "LK_LOAN_SCORE_MASTER")
@NamedQueries({

		@NamedQuery(name = "getLoanScoresByLoanId", query = "SELECT ls FROM LoanScoreModel AS ls WHERE ls.loanApplicationId = :loanApplicationId "
				+ "AND ls.version = (SELECT max(lsm.version) FROM LoanScoreModel AS lsm)")

})
public class LoanScoreModel {

	@Id
	@GeneratedValue
	@Column(name = "LSM_GUID", nullable = false)
	private Long id;

	@Column(name = "LSM_LOAN_APPLICATIONID", nullable = false)
	private String loanApplicationId;

	@Column(name = "LSM_VERSION", nullable = false)
	private String version;

	@Column(name = "LSM_SCORE")
	private String score;

	@Column(name = "LSM_SCALE")
	private String scale;

	@Column(name = "LSM_PERCENTILE")
	private String percentile;

	@Column(name = "LSM_FINANCIAL_HEALTH_SCORE")
	private String financialHealthScore;

	@Column(name = "LSM_FINANCEIAL_PERCENTILE")
	private String financialPercentile;

	@Column(name = "LSM_MARKETPLACE_SCORE")
	private String marketplacePerformanceScore;

	@Column(name = "LSM_MARKETPLACE_PERCENTILE")
	private String marketplacePercentile;

	@Column(name = "LSM_SOCIAL_RELIABILITY_SCORE")
	private String socialReliabiltyScore;

	@Column(name = "LSM_SOCIAL_PERCENTILE")
	private String socialPercentile;

	@Column(name = "LSM_STATUTORY_COMPLIANCE")
	private String statutoryCompliance;

	@Column(name = "LSM_STATUTORY_PERCENTILE")
	private String statutoryPercentile;

	@Column(name = "LSM_CREATED_DATE", nullable = false)
	private Date createdDate = LKUtils.getDate();

	@Column(name = "LSM_UPDATED_DATE", nullable = false)
	private Date updatedDate = LKUtils.getDate();

	@Column(name = "LSM_OPERATEDBY")
	private String operatedBy;

	public Long getId() {
		return id;
	}

	public String getLoanApplicationId() {
		return loanApplicationId;
	}

	public String getVersion() {
		return version;
	}

	public String getScore() {
		return score;
	}

	public String getScale() {
		return scale;
	}

	public String getPercentile() {
		return percentile;
	}

	public String getFinancialHealthScore() {
		return financialHealthScore;
	}

	public String getFinancialPercentile() {
		return financialPercentile;
	}

	public String getMarketplacePerformanceScore() {
		return marketplacePerformanceScore;
	}

	public String getSocialReliabiltyScore() {
		return socialReliabiltyScore;
	}

	public String getSocialPercentile() {
		return socialPercentile;
	}

	public String getStatutoryCompliance() {
		return statutoryCompliance;
	}

	public String getStatutoryPercentile() {
		return statutoryPercentile;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public String getOperatedBy() {
		return operatedBy;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLoanApplicationId(String loanApplicationId) {
		this.loanApplicationId = loanApplicationId;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public void setPercentile(String percentile) {
		this.percentile = percentile;
	}

	public void setFinancialHealthScore(String financialHealthScore) {
		this.financialHealthScore = financialHealthScore;
	}

	public void setFinancialPercentile(String financialPercentile) {
		this.financialPercentile = financialPercentile;
	}

	public void setMarketplacePerformanceScore(String marketplacePerformanceScore) {
		this.marketplacePerformanceScore = marketplacePerformanceScore;
	}

	public void setSocialReliabiltyScore(String socialReliabiltyScore) {
		this.socialReliabiltyScore = socialReliabiltyScore;
	}

	public void setSocialPercentile(String socialPercentile) {
		this.socialPercentile = socialPercentile;
	}

	public void setStatutoryCompliance(String statutoryCompliance) {
		this.statutoryCompliance = statutoryCompliance;
	}

	public void setStatutoryPercentile(String statutoryPercentile) {
		this.statutoryPercentile = statutoryPercentile;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public void setOperatedBy(String operatedBy) {
		this.operatedBy = operatedBy;
	}

	public String getMarketplacePercentile() {
		return marketplacePercentile;
	}

	public void setMarketplacePercentile(String marketplacePercentile) {
		this.marketplacePercentile = marketplacePercentile;
	}

	@Override
	public String toString() {
		return "LoanScoreModel [id=" + id + ", loanApplicationId=" + loanApplicationId + ", version=" + version
				+ ", score=" + score + ", scale=" + scale + ", percentile=" + percentile + ", financialHealthScore="
				+ financialHealthScore + ", financialPercentile=" + financialPercentile
				+ ", marketplacePerformanceScore=" + marketplacePerformanceScore + ", marketplacePercentile="
				+ marketplacePercentile + ", socialReliabiltyScore=" + socialReliabiltyScore + ", socialPercentile="
				+ socialPercentile + ", statutoryCompliance=" + statutoryCompliance + ", statutoryPercentile="
				+ statutoryPercentile + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate
				+ ", operatedBy=" + operatedBy + "]";
	}

}
