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
public class LoanScoreBean {

	private Long id;

	private String loanApplicationId;

	private String version;

	private String score = "N/A";

	private String scale = "N/A";

	private String percentile = "N/A";

	private String financialHealthScore = "N/A";

	private String financialPercentile = "N/A";

	private String marketplacePerformanceScore = "N/A";

	private String marketplacePercentile = "N/A";

	private String socialReliabiltyScore = "N/A";

	private String socialPercentile = "N/A";

	private String statutoryCompliance = "N/A";

	private String statutoryPercentile = "N/A";

	private Date createdDate = LKUtils.getDate();

	private Date updatedDate = LKUtils.getDate();

	private String operatedBy = "N/A";

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
		return "LoanScoreBean [id=" + id + ", loanApplicationId=" + loanApplicationId + ", version=" + version
				+ ", score=" + score + ", scale=" + scale + ", percentile=" + percentile + ", financialHealthScore="
				+ financialHealthScore + ", financialPercentile=" + financialPercentile
				+ ", marketplacePerformanceScore=" + marketplacePerformanceScore + ", marketplacePercentile="
				+ marketplacePercentile + ", socialReliabiltyScore=" + socialReliabiltyScore + ", socialPercentile="
				+ socialPercentile + ", statutoryCompliance=" + statutoryCompliance + ", statutoryPercentile="
				+ statutoryPercentile + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate
				+ ", operatedBy=" + operatedBy + "]";
	}

}
