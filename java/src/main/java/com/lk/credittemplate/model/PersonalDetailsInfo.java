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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Pooja Desai
 *
 */
@Entity
@Table(name = "LK_PD_APPLICANT_DATA")

@NamedQueries({

	      @NamedQuery(name = "getApplicantInfoById", query = "SELECT l FROM PersonalDetailsInfo l WHERE l.infoAppId=:infoAppId"),
	      @NamedQuery(name = "getApplicantInfoByDirId", query = "SELECT l FROM PersonalDetailsInfo l WHERE l.dirId=:dirId")
	      

})
public class PersonalDetailsInfo implements Serializable {

	private static final long serialVersionUID = 2805838946323485598L;

	@Id
	@Column(name = "DIR_ID")
	private String dirId;
    
    @Column(name ="DIR_TAB_NAME")
    private String dirTabName="";
    
    @Column(name = "LK_APP_ID")
	private String infoAppId;

	@Column(name = "LK_NAME")
	private String name;

	@Column(name = "LK_DOB")
	private Date  dateOfBirth;

	@Column(name = "LK_AGE")
	private int age;

	@Column(name = "LK_GENDER")
	private String gender;

	@Column(name = "LK_PAN_NUM")
	private String panNum;

	@Column(name = "LK_RELATIONSHIP")
	private String relationship;

	@Column(name = "LK_CIBIL_SCORE")
	private int cibilScore;

	@Column(name = "LK_PERSONAL_SCORE")
	private int personalScore;
	
	@Column(name = "LK_MARITAL_STATUS")
	private String maritalStatus;
	
	@Column(name = "LK_SPOUSE_EMPLOYMENT")
	private String spouseEmployment;
	
	@Column(name = "LK_NO_CHILDREN")
	private int numOfChildren;
	
	@Column(name = "LK_PARENT_DEPENDENT")
	private boolean parentDependent;
	
	@Column(name = "LK_PAST_JOB")
	private boolean pastJob;
	
	@Column(name = "LK_EXPERIENCE")
	private int experience;
	
	@Column(name = "LK_EDUCATION")
	private String education;
	
	@Column(name = "LK_HOUSEOWNERSHIP")
	private String houseOwnerShip;
	
	@Column(name = "LK_LIFECYCLE")
	private String lifeCycle;
	
	public String getDirTabName() {
		return dirTabName;
	}

	public void setDirTabName(String dirTabName) {
		this.dirTabName = dirTabName;
	}



	public String getDirId() {
		return dirId;
	}

	public void setDirId(String dirId) {
		this.dirId = dirId;
	}

	public String getInfoAppId() {
		return infoAppId;
	}

	public void setInfoAppId(String infoAppId) {
		this.infoAppId = infoAppId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPanNum() {
		return panNum;
	}

	public void setPanNum(String panNum) {
		this.panNum = panNum;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}


	public int getCibilScore() {
		return cibilScore;
	}

	public void setCibilScore(int cibilScore) {
		this.cibilScore = cibilScore;
	}

	public int getPersonalScore() {
		return personalScore;
	}

	public void setPersonalScore(int personalScore) {
		this.personalScore = personalScore;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getSpouseEmployment() {
		return spouseEmployment;
	}

	public void setSpouseEmployment(String spouseEmployment) {
		this.spouseEmployment = spouseEmployment;
	}

	public int getNumOfChildren() {
		return numOfChildren;
	}

	public void setNumOfChildren(int numOfChildren) {
		this.numOfChildren = numOfChildren;
	}

	public boolean isParentDependent() {
		return parentDependent;
	}

	public void setParentDependent(boolean parentDependent) {
		this.parentDependent = parentDependent;
	}

	public boolean isPastJob() {
		return pastJob;
	}

	public void setPastJob(boolean pastJob) {
		this.pastJob = pastJob;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getHouseOwnerShip() {
		return houseOwnerShip;
	}

	public void setHouseOwnerShip(String houseOwnerShip) {
		this.houseOwnerShip = houseOwnerShip;
	}

	public String getLifeCycle() {
		return lifeCycle;
	}

	public void setLifeCycle(String lifeCycle) {
		this.lifeCycle = lifeCycle;
	}
	
	
	





	
}
