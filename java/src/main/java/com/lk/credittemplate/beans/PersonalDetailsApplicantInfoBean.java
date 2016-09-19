package com.lk.credittemplate.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonalDetailsApplicantInfoBean {
	public String getDirId() {
		return dirId;
	}
	public void setDirId(String dirId) {
		this.dirId = dirId;
	}
	@JsonProperty("name")
    private String name="";
    @JsonProperty("dateOfBirth")
    private String dateOfBirth="";
    @JsonProperty("age")
    private int age=0;
    @JsonProperty("dirName")
    private String dirName="";
    @JsonProperty("gender")
    private String gender="";
    @JsonProperty("panNo")
    private String panNo="";
    @JsonProperty("relationShip")
    private String relationShip="";
    @JsonProperty("cibilScore")
    private int cibilScore=0;
    @JsonProperty("personalCibilScore")
    private int personalCibilScore=0;
    @JsonProperty("martialStatus")
    private String martialStatus="";
    @JsonProperty("spouseEmployment")
    private String spouseEmployment="";
    @JsonProperty("noOfChildren")
    private int noOfChildren=0;
    @JsonProperty("parents")
    private boolean parents=false;
    @JsonProperty("pastJob")
    private boolean pastJob=false;
    @JsonProperty("experienceMonths")
    private int experienceMonths=0;
    @JsonProperty("education")
    private String education="";
	@JsonProperty("houseOwnership")
    private String houseOwnership="";
    @JsonProperty("lifeCycle")
    private String lifeCycle="";
    @JsonProperty("dirId")
    private String dirId="";
    
    public String getDirName() {
		return dirName;
	}
	public void setDirName(String dirName) {
		this.dirName = dirName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getRelationShip() {
		return relationShip;
	}
	public void setRelationShip(String relationShip) {
		this.relationShip = relationShip;
	}
	public String getMartialStatus() {
		return martialStatus;
	}
	public void setMartialStatus(String martialStatus) {
		this.martialStatus = martialStatus;
	}
	public String getSpouseEmployment() {
		return spouseEmployment;
	}
	public void setSpouseEmployment(String spouseEmployment) {
		this.spouseEmployment = spouseEmployment;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getCibilScore() {
		return cibilScore;
	}
	public void setCibilScore(int cibilScore) {
		this.cibilScore = cibilScore;
	}
	public int getPersonalCibilScore() {
		return personalCibilScore;
	}
	public void setPersonalCibilScore(int personalCibilScore) {
		this.personalCibilScore = personalCibilScore;
	}
	public int getNoOfChildren() {
		return noOfChildren;
	}
	public void setNoOfChildren(int noOfChildren) {
		this.noOfChildren = noOfChildren;
	}
	public boolean isParents() {
		return parents;
	}
	public void setParents(boolean parents) {
		this.parents = parents;
	}
	public boolean isPastJob() {
		return pastJob;
	}
	public void setPastJob(boolean pastJob) {
		this.pastJob = pastJob;
	}
	public int getExperienceMonths() {
		return experienceMonths;
	}
	public void setExperienceMonths(int experienceMonths) {
		this.experienceMonths = experienceMonths;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getHouseOwnership() {
		return houseOwnership;
	}
	public void setHouseOwnership(String houseOwnership) {
		this.houseOwnership = houseOwnership;
	}
	public String getLifeCycle() {
		return lifeCycle;
	}
	public void setLifeCycle(String lifeCycle) {
		this.lifeCycle = lifeCycle;
	}
		
	
	
	
}
