package com.lk.credittemplate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "CT_LoanEnquiries")
@NamedQueries(
		{
			@NamedQuery(name = "getLoanEnquiries",
				query = "SELECT cr FROM LoanEnquiries AS cr WHERE cr.applicantId=:applicantId")
		}
		
	)

public class LoanEnquiries {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@Column(name = "dateofenquiry")
	private String dateOfEnquiry;
	
	@Column(name = "amount")
	private String amount;
	
	@Column(name = "purpose")
	private String purpose;
	
	@Column(name ="applicantid")
	private long applicantId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDateOfEnquiry() {
		return dateOfEnquiry;
	}

	public void setDateOfEnquiry(String dateOfEnquiry) {
		this.dateOfEnquiry = dateOfEnquiry;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public long getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(long applicantId) {
		this.applicantId = applicantId;
	}
	

}
