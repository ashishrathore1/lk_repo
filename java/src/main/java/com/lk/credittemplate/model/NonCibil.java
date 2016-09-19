package com.lk.credittemplate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "CT_NonCibil")
@NamedQueries(
{

@NamedQuery(name = "getNonCibilByLoanType",
query = "SELECT cr FROM NonCibil AS cr WHERE cr.loanId=:appId AND cr.typeofloan LIKE :type"),
@NamedQuery(name = "getNonCibilByLoanId",
query = "SELECT cr FROM NonCibil AS cr WHERE cr.loanId =:appId ORDER BY cr.createdDate")
}
)
public class NonCibil {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@Column(name = "particulars")
	private String particulars;
	
	@Column(name = "loantype")
	private String typeofloan;
	
	@Column(name = "openeddate")
	private Date openeddate;
	
	@Column(name = "sanctionedamt")
	private long sanctionedamt;
	
	@Column(name = "outstanding")
	private long outstanding;
	
	@Column(name = "emi")
	private long emi;
	
	@Column(name = "duration")
	private int duration;
	
	@Column(name = "curnoncur")
	private String currentnoncur;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "loanid")
	private String loanId;
	
	@Column(name = "createddate")
	private Date createdDate;
	
	@Column(name = "updateddate")
	private Date updateddate;
	

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(Date updateddate) {
		this.updateddate = updateddate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getParticulars() {
		return particulars;
	}

	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}

	public String getTypeofloan() {
		return typeofloan;
	}

	public void setTypeofloan(String typeofloan) {
		this.typeofloan = typeofloan;
	}

	public Date getOpeneddate() {
		return openeddate;
	}

	public void setOpeneddate(Date openeddate) {
		this.openeddate = openeddate;
	}

	public long getSanctionedamt() {
		return sanctionedamt;
	}

	public void setSanctionedamt(long sanctionedamt) {
		this.sanctionedamt = sanctionedamt;
	}

	public long getOutstanding() {
		return outstanding;
	}

	public void setOutstanding(long outstanding) {
		this.outstanding = outstanding;
	}

	public long getEmi() {
		return emi;
	}

	public void setEmi(long emi) {
		this.emi = emi;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getCurrentnoncur() {
		return currentnoncur;
	}

	public void setCurrentnoncur(String currentnoncur) {
		this.currentnoncur = currentnoncur;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}
	
}
