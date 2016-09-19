package com.lk.credittemplate.cibilmodel;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name = "LK_DIRECTOR_MASTER")
public class DirectorMaster implements Serializable {
	private static final long serialVersionUID = -7550527054087045584L;

	@Id
	@Column(name = "D_GUID")
	private String dGuid;

	@Column(name = "D_NAME")
	private String dName;
    
	@ Column(name = "D_FIRST_NAME")
	private String FirstName;
	
	@ Column(name = "D_MIDDLE_NAME")
	private String MiddleName;
	
	@ Column(name = "D_LAST_NAME")
	private String LastName;
	

	@Column(name = "D_TAB_NAME")
	private String DTabName;

	@Column(name = "D_MOBILE")
	private String dMobile;

	@Column(name = "D_DATE_OF_BIRTH")
	private Date dDateOfBirth;

	@Column(name = "D_EMAIL")
	private String dEmail;

	@Column(name = "D_INCOME")
	private String dIncome;

	@Column(name = "D_PAN")
	private String dPan;

	@Column(name = "D_EDUCTION")
	private String dEducation;

	@Column(name = "D_EDUCTION_OTHERS")
	private String dEducationOthers;

	@Column(name = "D_FAMILY_DETAILS")
	private String dFamilyDetails;

	@Column(name = "D_SPOUSE_OCCUPATION")
	private String dSpouseOccupation;

	@Column(name = "D_NO_OF_CHILDREN")
	private String dNoOfChildren;

	@Column(length = 4, name = "D_SAME_AS_BUSS_ADDRESS")
	private Integer dSameAsBussAddress;

	@Column(name = "D_RESIDENCE_ADDRESS")
	private String dResidenceAddress;

	@Column(name = "D_PINCODE")
	private String dPincode;

	@Column(name = "D_ADDRESS")
	private String dAddress;

	@Column(name = "D_CITY")
	private String aCity;

	@Column(name = "D_STATE")
	private String dstate;

	@Column(name = "D_SAME_AS_RES_ADDRESS", length = 4)
	private Integer dSameAsResAddress;

	@Column(name = "D_PA_ADDRESS")
	private String dPaADDRESS;

	@Column(name = "D_PERMINENT_PINCODE")
	private String dPermanentPincode;

	@Column(name = "D_PERMINENT_ADDRESS")
	private String dPermanentAddress;

	@Column(name = "D_PERMINENT_CITY")
	private String dPerminentCity;

	@Column(name = "D_PERMINENT_STATE")
	private String dPermanentState;

	@Column(name = "D_CREATED_DATE")
	private Date dCreatedDate;

	@Column(name = "D_UPDATED_DATE")
	private Date dUpdatedDate;

	@Column(name = "D_OPERATED_BY")
	private String dOperatedBy;

	@Column(name = "D_IS_DELETED")
	private boolean isDeleted;
	
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = LoanMaster.class)
	@JoinColumn(name = "D_LOAN_ID")
	private LoanMaster loanMaster;

	public DirectorMaster() {
		if (StringUtils.isEmpty(this.dGuid)) {
			this.dGuid = UUID.randomUUID().toString();
		}

	}

	public String getdGuid() {
		return dGuid;
	}

	public void setdGuid(String dGuid) {
		this.dGuid = dGuid;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public String getdMobile() {
		return dMobile;
	}

	public void setdMobile(String dMobile) {
		this.dMobile = dMobile;
	}

	public Date getdDateOfBirth() {
		return dDateOfBirth;
	}

	public void setdDateOfBirth(Date dDateOfBirth) {
		this.dDateOfBirth = dDateOfBirth;
	}

	public String getdIncome() {
		return dIncome;
	}

	public void setdIncome(String dIncome) {
		this.dIncome = dIncome;
	}

	public String getdPan() {
		return dPan;
	}

	public void setdPan(String dPan) {
		this.dPan = dPan;
	}

	public String getdFamilyDetails() {
		return dFamilyDetails;
	}

	public void setdFamilyDetails(String dFamilyDetails) {
		this.dFamilyDetails = dFamilyDetails;
	}

	public String getdSpouseOccupation() {
		return dSpouseOccupation;
	}

	public void setdSpouseOccupation(String dSpouseOccupation) {
		this.dSpouseOccupation = dSpouseOccupation;
	}

	public String getdNoOfChildren() {
		return dNoOfChildren;
	}

	public void setdNoOfChildren(String dNoOfChildren) {
		this.dNoOfChildren = dNoOfChildren;
	}

	public Integer getdSameAsBussAddress() {
		return dSameAsBussAddress;
	}

	public void setdSameAsBussAddress(Integer dSameAsBussAddress) {
		this.dSameAsBussAddress = dSameAsBussAddress;
	}

	public String getdResidenceAddress() {
		return dResidenceAddress;
	}

	public void setdResidenceAddress(String dResidenceAddress) {
		this.dResidenceAddress = dResidenceAddress;
	}

	public String getdPincode() {
		return dPincode;
	}

	public void setdPincode(String dPincode) {
		this.dPincode = dPincode;
	}

	public String getdAddress() {
		return dAddress;
	}

	public void setdAddress(String dAddress) {
		this.dAddress = dAddress;
	}

	public String getaCity() {
		return aCity;
	}

	public void setaCity(String aCity) {
		this.aCity = aCity;
	}

	public String getDstate() {
		return dstate;
	}

	public void setDstate(String dstate) {
		this.dstate = dstate;
	}

	public Integer getdSameAsResAddress() {
		return dSameAsResAddress;
	}

	public void setdSameAsResAddress(Integer dSameAsResAddress) {
		this.dSameAsResAddress = dSameAsResAddress;
	}

	public String getdPermanentPincode() {
		return dPermanentPincode;
	}

	public void setdPermanentPincode(String dPermanentPincode) {
		this.dPermanentPincode = dPermanentPincode;
	}

	public String getdPermanentAddress() {
		return dPermanentAddress;
	}

	public void setdPermanentAddress(String dPermanentAddress) {
		this.dPermanentAddress = dPermanentAddress;
	}

	public String getdPermanentState() {
		return dPermanentState;
	}

	public void setdPermanentState(String dPermanentState) {
		this.dPermanentState = dPermanentState;
	}

	public Date getdCreatedDate() {
		return dCreatedDate;
	}

	public void setdCreatedDate(Date dCreatedDate) {
		this.dCreatedDate = dCreatedDate;
	}

	public Date getdUpdatedDate() {
		return dUpdatedDate;
	}

	public void setdUpdatedDate(Date dUpdatedDate) {
		this.dUpdatedDate = dUpdatedDate;
	}

	public String getdOperatedBy() {
		return dOperatedBy;
	}

	public void setdOperatedBy(String dOperatedBy) {
		this.dOperatedBy = dOperatedBy;
	}
	//
	// public Integer getdOfficeOwnership() {
	// return dOfficeOwnership;
	// }
	//
	// public void setdOfficeOwnership(Integer dOfficeOwnership) {
	// this.dOfficeOwnership = dOfficeOwnership;
	// }
	//
	// public Integer getdHouseOwnership() {
	// return dHouseOwnership;
	// }
	//
	// public void setdHouseOwnership(Integer dHouseOwnership) {
	// this.dHouseOwnership = dHouseOwnership;
	// }

	public String getDTabName() {
		return DTabName;
	}

	public void setDTabName(String dTabName) {
		DTabName = dTabName;
	}

	public String getdEducationOthers() {
		return dEducationOthers;
	}

	public void setdEducationOthers(String dEducationOthers) {
		this.dEducationOthers = dEducationOthers;
	}

	/**
	 * @return the dPaADDRESS
	 */
	public String getdPaADDRESS() {
		return dPaADDRESS;
	}

	/**
	 * @param dPaADDRESS
	 *            the dPaADDRESS to set
	 */
	public void setdPaADDRESS(String dPaADDRESS) {
		this.dPaADDRESS = dPaADDRESS;
	}

	/**
	 * @return the dEmail
	 */
	public String getdEmail() {
		return dEmail;
	}

	/**
	 * @param dEmail
	 *            the dEmail to set
	 */
	public void setdEmail(String dEmail) {
		this.dEmail = dEmail;
	}

	/**
	 * @return the dEducation
	 */
	public String getdEducation() {
		return dEducation;
	}

	/**
	 * @param dEducation
	 *            the dEducation to set
	 */
	public void setdEducation(String dEducation) {
		this.dEducation = dEducation;
	}

	/**
	 * @return the dPerminentCity
	 */
	public String getdPerminentCity() {
		return dPerminentCity;
	}

	/**
	 * @param dPerminentCity
	 *            the dPerminentCity to set
	 */
	public void setdPerminentCity(String dPerminentCity) {
		this.dPerminentCity = dPerminentCity;
	}

	/**
	 * @return the loanMaster
	 */
	public LoanMaster getLoanMaster() {
		return loanMaster;
	}

	/**
	 * @param loanMaster
	 *            the loanMaster to set
	 */
	public void setLoanMaster(LoanMaster loanMaster) {
		this.loanMaster = loanMaster;
	}
	
	
	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getMiddleName() {
		return MiddleName;
	}

	public void setMiddleName(String middleName) {
		MiddleName = middleName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}



}
