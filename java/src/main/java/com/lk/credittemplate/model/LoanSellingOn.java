package com.lk.credittemplate.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "LK_LOAN_SELLINGONS")
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQueries(
{
//@NamedQuery(name = "showCatComments",
//query = "SELECT c FROM LoanComments c where c.appId =:appId order by c.createdDate"),
@NamedQuery(name = "getSellingIdsByLoanId",
query = "SELECT c FROM LoanSellingOn c where c.lsoLoanId =:lsoLoanId ")
//
	})
public class LoanSellingOn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "LSO_GUID")
	private String lsoGuid;

	@Column(name = "LSO_NAME")
	private String lsoName;

	@Column(name = "LSO_SELLER_CODE")
	private String lsoSellerCode;

	@Column(name = "LSO_FULLFILLMENT")
	private String lsoFullfillment;
	
	@Column(name = "LSO_CREATED_DATE")
	private Date lsoCreatedDate;
	
	@Column(name = "LSO_UPDATED_DATE")
	private Date lsoUpdatedDate;
	
	@Column(name = "LSO_LOAN_ID")
	private String lsoLoanId;
	
	@Column(name = "LSO_SELLINGON_ID")
	private String lsoSellingId;


	public String getLsoGuid() {
		return lsoGuid;
	}

	public void setLsoGuid(String lsoGuid) {
		this.lsoGuid = lsoGuid;
	}

	public String getLsoName() {
		return lsoName;
	}

	public void setLsoName(String lsoName) {
		this.lsoName = lsoName;
	}

	public String getLsoSellerCode() {
		return lsoSellerCode;
	}

	public void setLsoSellerCode(String lsoSellerCode) {
		this.lsoSellerCode = lsoSellerCode;
	}

	public String getLsoFullfillment() {
		return lsoFullfillment;
	}

	public void setLsoFullfillment(String lsoFullfillment) {
		this.lsoFullfillment = lsoFullfillment;
	}

	public Date getLsoCreatedDate() {
		return lsoCreatedDate;
	}

	public void setLsoCreatedDate(Date lsoCreatedDate) {
		this.lsoCreatedDate = lsoCreatedDate;
	}

	public Date getLsoUpdatedDate() {
		return lsoUpdatedDate;
	}

	public void setLsoUpdatedDate(Date lsoUpdatedDate) {
		this.lsoUpdatedDate = lsoUpdatedDate;
	}

	public String getLsoLoanId() {
		return lsoLoanId;
	}

	public void setLsoLoanId(String lsoLoanId) {
		this.lsoLoanId = lsoLoanId;
	}

	public String getLsoSellingId() {
		return lsoSellingId;
	}

	public void setLsoSellingId(String lsoSellingId) {
		this.lsoSellingId = lsoSellingId;
	}

	
}
