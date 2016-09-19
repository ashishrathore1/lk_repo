package com.lk.credittemplate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;


@Entity
@Table(name = "CT_Cibil")
@NamedQueries(
		{
			@NamedQuery(name = "getCibilDataByAppId",
				query = "SELECT cr FROM Cibil AS cr WHERE cr.loanId=:applicationID"),
			@NamedQuery(name = "getCibilDataByApplicantId",
			query = "SELECT cr FROM Cibil AS cr WHERE cr.applicantId=:applicantId"),
			@NamedQuery(name = "getByLoanType",
			query = "SELECT cr FROM Cibil AS cr WHERE cr.loanId =:appId AND cr.typeofloan LIKE :type")
		}
		
	)
public class Cibil {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Column(name = "loantype")
	private String typeofloan;
	
	@Column(name = "sanctionedamt")
	private long sanctionedamt;
	
	@Column(name = "outstanding")
	private long outstanding;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "maxlateDays")
	private int maxlateDays;
	
	@Column(name = "amtoverdue")
	private int amtoverdue;
	
	@Column(name = "delaymcount")
	private int delaymcount;
	
	@Column(name = "msincelastdelay")
	private int msincelastdelay;
	
	@Column(name = "tenure")
	private int tenure;
	
	@Column(name = "interestrate")
	private float interestrate;
	
	@Column(name = "openeddate")
	private Date openeddate;
	
	@Column(name = "curnoncur")
	private String currentnoncur;
	
	@Column(name = "category")
	private String category;

	@Column(name = "applicantid")
	private long applicantId;
	
	@Column(name = "ownershiptype")
	private String ownershipType;
	
	@Column(name = "loanid")
	private String loanId;
	
	@Column(name = "originalloantype")
	private String originalLoanType;
	
	@Column(name ="CREATED_DATE")
	private Date created;
	
	@Column(name ="UPDATED_DATE")
	private Date updated;

	public String getOriginalLoanType() {
		return originalLoanType;
	}

	public void setOriginalLoanType(String originalLoanType) {
		this.originalLoanType = originalLoanType;
	}
	
	public String getOwnershipType() {
		return ownershipType;
	}

	public void setOwnershipType(String ownershipType) {
		this.ownershipType = ownershipType;
	}

	public long getApplicantId() {
		return applicantId;
	}
	public void setApplicantId(long applicantId) {
		this.applicantId = applicantId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTypeofloan() {
		return typeofloan;
	}
	public void setTypeofloan(String typeofloan) {
		this.typeofloan = typeofloan;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getMaxlateDays() {
		return maxlateDays;
	}
	public void setMaxlateDays(int maxlateDays) {
		this.maxlateDays = maxlateDays;
	}
	public int getAmtoverdue() {
		return amtoverdue;
	}
	public void setAmtoverdue(int amtoverdue) {
		this.amtoverdue = amtoverdue;
	}
	public int getDelaymcount() {
		return delaymcount;
	}
	public void setDelaymcount(int delaymcount) {
		this.delaymcount = delaymcount;
	}
	public int getMsincelastdelay() {
		return msincelastdelay;
	}
	public void setMsincelastdelay(int msincelastdelay) {
		this.msincelastdelay = msincelastdelay;
	}
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public float getInterestrate() {
		return interestrate;
	}
	public void setInterestrate(float interestrate) {
		this.interestrate = interestrate;
	}
	public Date getOpeneddate() {
		return openeddate;
	}
	public void setOpeneddate(Date openeddate) {
		this.openeddate = openeddate;
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
}