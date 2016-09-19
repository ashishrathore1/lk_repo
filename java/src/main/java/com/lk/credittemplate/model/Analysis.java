package com.lk.credittemplate.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * @author Pooja Desai
 *
 */
@Entity
@Table(name = "LK_ANALYSIS")

@NamedQueries({

		//@NamedQuery(name = "findStatusByStatusId", query = "SELECT s FROM StatusTracker s WHERE s.flag=:flag"),
		//@NamedQuery(name = "findAllStatus", query = "SELECT s FROM StatusTracker s WHERE s.applicationId IN(:applicationId)"),
		//@NamedQuery(name = "getStatusTrackerByAppId", query = "SELECT s FROM StatusTracker s WHERE s.applicationId =:applicationId")
		//@NamedQuery(name="updateStatusTrackerById", query="UPDATE  statusTracker s  WHERE s.applicationId=:applicationId")
	      @NamedQuery(name = "findApplications", query = "SELECT l FROM Analysis l WHERE l.applicationID=:applicationID"),
	      @NamedQuery(name = "getAnalysisByName", query = "SELECT l FROM Analysis l WHERE l.applicationID=:applicationID")
	      

})
public class Analysis implements Serializable {

	private static final long serialVersionUID = 2805838946323485598L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long analysisID;

	@Column(name = "APPLICATION_D")
	private String applicationID;

	@Column(name = "CL_CONTRACT_ID")
	private String clContractId;

	@Column(name = "CLS_ID")
	private String clsId;

	@Column(name = "PRODUCT")
	private String product;

	@Column(name = "CYCLE")
	private String cycle;

	@Column(name = "TYPE")
	private String type;

	@Column(name = "APP_CREATED_DATE")
	private Date createdDate;

	@Column(name = "DATE_ASSIGNED_ANALYST")
	private Date dateAssignedAnalyst;

	@Column(name = "DATE_ANALYSIS")
	private Date dateAnalysis;
	
	@Column(name = "ANALYST_NAME")
	private String analystName;
	
	@Column(name = "LEAD_SOURCE")
	private String leadSource;
	
	@Column(name = "LOAN_PURPOSE")
	private String loanPurpose;
	
	@Column(name = "LOAN_ELIGIBLE")
	private double loanEligible;
	
	@Column(name = "LOAN_APPLIED")
	private double loanApplied;
	
	@Column(name = "LEAD_SOURCE_OTHERS")
	private String leadSourceOthers;

	public String getLeadSourceOthers() {
		return leadSourceOthers;
	}

	public void setLeadSourceOthers(String leadSourceOthers) {
		this.leadSourceOthers = leadSourceOthers;
	}

	public long getAnalysisID() {
		return analysisID;
	}

	public void setAnalysisID(long analysisID) {
		this.analysisID = analysisID;
	}

	public String getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}

	public String getClContractId() {
		return clContractId;
	}

	public void setClContractId(String clContractId) {
		this.clContractId = clContractId;
	}

	public String getClsId() {
		return clsId;
	}

	public void setClsId(String clsId) {
		this.clsId = clsId;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getDateAssignedAnalyst() {
		return dateAssignedAnalyst;
	}

	public void setDateAssignedAnalyst(Date dateAssignedAnalyst) {
		this.dateAssignedAnalyst = dateAssignedAnalyst;
	}

	public Date getDateAnalysis() {
		return dateAnalysis;
	}

	public void setDateAnalysis(Date dateAnalysis) {
		this.dateAnalysis = dateAnalysis;
	}

	public String getAnalystName() {
		return analystName;
	}

	public void setAnalystName(String analystName) {
		this.analystName = analystName;
	}

	public String getLeadSource() {
		return leadSource;
	}

	public void setLeadSource(String leadSource) {
		this.leadSource = leadSource;
	}

	public String getLoanPurpose() {
		return loanPurpose;
	}

	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}

	public double getLoanEligible() {
		return loanEligible;
	}

	public void setLoanEligible(double loanEligible) {
		this.loanEligible = loanEligible;
	}

	public double getLoanApplied() {
		return loanApplied;
	}

	public void setLoanApplied(double loanApplied) {
		this.loanApplied = loanApplied;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	
}
