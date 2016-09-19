package com.lk.credittemplate.service;

import java.util.List;

import com.lk.credittemplate.cibilmodel.DirectorMaster;
import com.lk.credittemplate.cibilmodel.LKCibilResponse;
import com.lk.credittemplate.cibilmodel.LoanMaster;
import com.lk.credittemplate.model.Applicant;
import com.lk.credittemplate.model.Audit;
import com.lk.credittemplate.model.Cibil;
import com.lk.credittemplate.model.LoanStatusMaster;
import com.lk.credittemplate.model.NonCibil;
import com.lk.credittemplate.model.UserMaster;



public interface DataService {
	
	public LoanMaster getApplicationByAppId(String appId);

	public LoanMaster findLoanMasterId(String appId);

	public UserMaster getByUserId(String userId);

	public void commitInitialUpload(Audit audit, LoanMaster loanMaster);

	public List<UserMaster> getUsersByLevels(int curLevel);

	public DirectorMaster getByDirectorId(String dirId);

	public List<UserMaster> getRoleList(String roleName);
	
	public void saveNonCibilData(List<NonCibil> newNonCibilList);

	public List<NonCibil> fetchNonCibilByApplicantId(long l);

	public LKCibilResponse getCibilData(String appId, String dirId);

	public void saveCibilList(List<Cibil> resultList);

	public List<Cibil> getCibilData(String appId);

	public List<Applicant> getApplicantListByLoanId(String appId);

	public Applicant saveApplicantsData(Applicant applicant);

	public List<Cibil> getCibilAnalysisDataByApplicantId(long id);

	public List<Cibil> getCibilDataByType(String s, String appId);

	public List<NonCibil> getNonCibilDataByType(String type, String appId);

	public Applicant getPrimaryApplicantByLoanId(String appId);

	public void deleteApplicantDataByLoanId(String appId);

	public void deleteCibilDataByLoanId(String loanMastertId);

	public NonCibil getNonCibilById(long id);

	public List<NonCibil> fetchNonCibilByLoanId(String loanId);

	public int getMinimumCibilByLoanId(String appId);

	public String[] getCibilDirectors(String appId);

	public boolean filterCibilByGuarantor(Cibil cibil);
	
	public LoanMaster getApplicationByGuid(String appId);

	public LoanStatusMaster findByStatusId(String statusId);

	public void saveLoan(LoanMaster loanMaster); 
	
}
