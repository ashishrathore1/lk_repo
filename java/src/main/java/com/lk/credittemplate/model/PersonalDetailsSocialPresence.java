package com.lk.credittemplate.model;

import java.io.Serializable;
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
@Table(name = "LK_PD_SOCIAL_PRESENCE")

@NamedQueries({

		//@NamedQuery(name = "findStatusByStatusId", query = "SELECT s FROM StatusTracker s WHERE s.flag=:flag"),
		//@NamedQuery(name = "findAllStatus", query = "SELECT s FROM StatusTracker s WHERE s.applicationId IN(:applicationId)"),
		//@NamedQuery(name = "getStatusTrackerByAppId", query = "SELECT s FROM StatusTracker s WHERE s.applicationId =:applicationId")
		//@NamedQuery(name="updateStatusTrackerById", query="UPDATE  statusTracker s  WHERE s.applicationId=:applicationId")
	     @NamedQuery(name = "deleteSocialPresenceById", query = "DELETE FROM PersonalDetailsSocialPresence l WHERE l.loanAppId=:loanAppId"),
	      @NamedQuery(name = "getSocialPresenceById", query = "SELECT l FROM PersonalDetailsSocialPresence l WHERE l.loanAppId=:loanAppId")

})
public class PersonalDetailsSocialPresence implements Serializable {

	private static final long serialVersionUID = 2805838946323485598L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long socialId;

	@Column(name = "LK_APP_ID")
	private String loanAppId;

	@Column(name = "LK_SOCIAL_TAB")
	private String socialTab;

	@Column(name = "LK_VALUES")
	private String values;

	@Column(name = "LK_REMARKS")
	private String remarks;




	public long getSocialId() {
		return socialId;
	}

	public void setSocialId(long socialId) {
		this.socialId = socialId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLoanAppId() {
		return loanAppId;
	}

	public void setLoanAppId(String loanAppId) {
		this.loanAppId = loanAppId;
	}

	public String getSocialTab() {
		return socialTab;
	}

	public void setSocialTab(String socialTab) {
		this.socialTab = socialTab;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	




	
}
