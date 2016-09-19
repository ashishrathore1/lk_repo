package com.lk.credittemplate.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;


@NamedQueries(
		{
			@NamedQuery(name = "getStatusByName",
					query = "SELECT s FROM LoanStatusMaster s WHERE s.name =:name")
		}
	)

@Entity
@Table(name="LK_STATUS_MASTER")
public class LoanStatusMaster implements Serializable
{
	private static final long serialVersionUID = 2805838946323485598L;
	
	@Id
	@Column(name="LS_GUID")
	private String loanStatustId;
	
	/*@OneToMany(cascade = CascadeType.ALL, mappedBy = "loanStatusMaster",fetch = FetchType.EAGER)
    private Set<LoanMaster> loanMastersSet = new HashSet<LoanMaster>();
	*/
	@Column(name="LS_NAME")
	private String name;
	
	@Column(name="LS_DESCRIPTION")
	private String description;
	
	@Column(name = "LS_CREATED_DATE")
	private Date created;
	
	@Column(name = "LS_UPDATED_DATE")
	private Date modified;
	
	@Column(name="LS_OPERATED_BY")
	private String operatedBy;

	
	public LoanStatusMaster() {
		if(StringUtils.isEmpty(this.loanStatustId)){
			this.loanStatustId = UUID.randomUUID().toString();
		}
		
	}
	
	/**
	 * @return the loanStatustId
	 */
	public String getLoanStatustId() {
		return loanStatustId;
	}

	/**
	 * @param loanStatustId the loanStatustId to set
	 */
	public void setLoanStatustId(String loanStatustId) {
		this.loanStatustId = loanStatustId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the modified
	 */
	public Date getModified() {
		return modified;
	}

	/**
	 * @param modified the modified to set
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * @return the operatedBy
	 */
	public String getOperatedBy() {
		return operatedBy;
	}

	/**
	 * @param operatedBy the operatedBy to set
	 */
	public void setOperatedBy(String operatedBy) {
		this.operatedBy = operatedBy;
	}

	/**
	 * @return the loanMastersSet
	 */
	/*public Set<LoanMaster> getLoanMastersSet() {
		return loanMastersSet;
	}

	*//**
	 * @param loanMastersSet the loanMastersSet to set
	 *//*
	public void setLoanMastersSet(Set<LoanMaster> loanMastersSet) {
		this.loanMastersSet = loanMastersSet;
	}
*/
	
}
