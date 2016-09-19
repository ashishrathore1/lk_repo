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
@Table(name = "LK_PD_APPLICANT_SOCIAL")

@NamedQueries({

		// @NamedQuery(name = "findStatusByStatusId", query = "SELECT s FROM
		// StatusTracker s WHERE s.flag=:flag"),
		// @NamedQuery(name = "findAllStatus", query = "SELECT s FROM
		// StatusTracker s WHERE s.applicationId IN(:applicationId)"),
		// s@NamedQuery(name = "getDirectorFromDirId", query = "SELECT s FROM
		// StatusTracker s WHERE s.applicationId =:applicationId"),
		// @NamedQuery(name="deletePersonalDetailsById", query="DELETE FROM
		// PersonalDetailsApplicantDetails e WHERE e.socialAppId =
		// :socialAppId"),
		@NamedQuery(name = "getPersonalDetailsApplicantByDirId", query = "SELECT l FROM PersonalDetailsApplicantDetails l WHERE l.directorId =:directorId"),
		@NamedQuery(name = "getPersonalDetailsApplicantById", query = "SELECT l FROM PersonalDetailsApplicantDetails l WHERE l.socialAppId =:socialAppId")

})
public class PersonalDetailsApplicantDetails implements Serializable {

	private static final long serialVersionUID = 2805838946323485598L;

	@Id
	@Column(name = "LK_DIR_ID")
	private String directorId;

	@Column(name = "LK_APP_ID")
	private String socialAppId;

	@Column(name = "LK_FB_URL")
	private String fbUrl;

	@Column(name = "LK_NUM_OF_FRIENDS")
	private String NumOfFriends;

	@Column(name = "LK_LINKEDIN_URL")
	private String linkedinUrl;

	@Column(name = "LK_LINKEDIN_CONNECTIONS")
	private String linkedinConnections;

	@Column(name = "LK_DIR_TAB_NAME")
	private String dirTabName;

	@Column(name = "LK_CREATED_DATE")
	private Date createdDate;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date date) {
		this.createdDate = date;
	}

	public String getDirectorId() {
		return directorId;
	}

	public void setDirectorId(String string) {
		this.directorId = string;
	}

	public String getDirTabName() {
		return dirTabName;
	}

	public void setDirTabName(String dirTabName) {
		this.dirTabName = dirTabName;
	}

	public String getSocialAppId() {
		return socialAppId;
	}

	public void setSocialAppId(String socialAppId) {
		this.socialAppId = socialAppId;
	}

	public String getFbUrl() {
		return fbUrl;
	}

	public void setFbUrl(String fbUrl) {
		this.fbUrl = fbUrl;
	}

	public String getNumOfFriends() {
		return NumOfFriends;
	}

	public void setNumOfFriends(String numOfFriends) {
		NumOfFriends = numOfFriends;
	}

	public String getLinkedinUrl() {
		return linkedinUrl;
	}

	public void setLinkedinUrl(String linkedinUrl) {
		this.linkedinUrl = linkedinUrl;
	}

	public String getLinkedinConnections() {
		return linkedinConnections;
	}

	public void setLinkedinConnections(String linkedinConnections) {
		this.linkedinConnections = linkedinConnections;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
