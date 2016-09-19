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
@Table(name="CT_Applicants")
@NamedQueries(
		{
			@NamedQuery(name = "getApplicantDataByAppId",
				query = "SELECT cr FROM Applicant AS cr WHERE cr.loanId=:applicationID ORDER BY cr.createdDate asc "),
			@NamedQuery(name = "getMinCibibyLoanId",
			query = "SELECT MIN(cr.cibilscore) FROM Applicant AS cr WHERE cr.loanId=:applicationID"),
			
			@NamedQuery(name = "getMinCibilbyLoanId",
			query = "SELECT MIN(cr.cibilscore) FROM Applicant AS cr WHERE cr.loanId=:applicationID"),
			
			@NamedQuery(name = "getApplicantByNameAndLoan",
			query = "SELECT ca FROM Applicant AS ca WHERE ca.loanId=:loanId AND ca.name=:name"),
			
			@NamedQuery(name = "getApplicantByLoanAndDirectorId",
			query = "SELECT cr FROM Applicant AS cr WHERE cr.loanId=:applicationID AND name=:directorName"),
			
			@NamedQuery(name = "getApplicantByIds",
			query = "SELECT cr FROM Applicant AS cr WHERE cr.loanId=:loanId AND name=:name"),
			
		}
		
	)

 public class Applicant{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "dob")
	private String dob;
	
	@Column(name = "relation")
	private String relation;
	
	@Column(name = "cibilscore")
	private int cibilscore;
	
	@Column(name = "personalscore")
	private int personalscore;
	
	@Column(name = "age")
	private int age;	
	
	@Column(name = "loanid")
	private String loanId;	
	
	@Column(name = "createddate")
	private Date createdDate;
	
	@Column(name ="isprimaryapplicant")
	private boolean primaryApplicant;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="lastenquiry")
	private Date lastEnquiry;
	
	
	public Date getLastEnquiry() {
		return lastEnquiry;
	}

	public void setLastEnquiry(Date lastEnquiry) {
		this.lastEnquiry = lastEnquiry;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isPrimaryApplicant() {
		return primaryApplicant;
	}

	public void setPrimaryApplicant(boolean primaryApplicant) {
		this.primaryApplicant = primaryApplicant;
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public int getCibilscore() {
		return cibilscore;
	}

	public void setCibilscore(int cibilscore) {
		this.cibilscore = cibilscore;
	}

	public int getPersonalscore() {
		return personalscore;
	}

	public void setPersonalscore(int personalscore) {
		this.personalscore = personalscore;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


}