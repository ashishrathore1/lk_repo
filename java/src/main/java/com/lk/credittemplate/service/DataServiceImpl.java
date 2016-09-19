package com.lk.credittemplate.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lk.credittemplate.cibildao.ApplicantDao;
import com.lk.credittemplate.cibildao.CibilDao;
import com.lk.credittemplate.cibildao.CibilResponseDao;
import com.lk.credittemplate.cibildao.DirectorMasterDao;
import com.lk.credittemplate.cibildao.LoanMasterDao;
import com.lk.credittemplate.cibilmodel.DirectorMaster;
import com.lk.credittemplate.cibilmodel.LKCibilResponse;
import com.lk.credittemplate.cibilmodel.LoanMaster;
import com.lk.credittemplate.dao.AuditDao;
import com.lk.credittemplate.dao.LoanStatusMasterDao;
import com.lk.credittemplate.dao.NonCibilDao;
import com.lk.credittemplate.dao.UserMasterDao;
import com.lk.credittemplate.model.Applicant;
import com.lk.credittemplate.model.Audit;
import com.lk.credittemplate.model.Cibil;
import com.lk.credittemplate.model.LoanStatusMaster;
import com.lk.credittemplate.model.NonCibil;
import com.lk.credittemplate.model.UserMaster;



@Service
@Transactional
public class DataServiceImpl implements DataService{

	
	@Autowired
	LoanMasterDao loanMasterDao;
	
	@Autowired
	UserMasterDao userMasterDao;
	
	@Autowired
	AuditDao auditDao;
	
	@Autowired
	DirectorMasterDao directorMasterDao;
	
	@Autowired
	NonCibilDao nonCibildao;
	
	@Autowired
	CibilResponseDao cibilResponseDao;
	
	@Autowired
	CibilDao cibilDao;
	
	@Autowired
	ApplicantDao applicantDao;
	
	@Autowired
	LoanStatusMasterDao loanStatusMasterDao; 
	
	public LoanMaster getApplicationByAppId(String appId) {

		return loanMasterDao.getApplicationByAppId(appId);
	}

	public LoanMaster findLoanMasterId(String appId) {

		return loanMasterDao.findLoanDetails(appId);
	}

	public UserMaster getByUserId(String userId){
		return userMasterDao.findById(userId);
	}
	
	public void commitInitialUpload(Audit audit, LoanMaster loanMaster){
		loanMasterDao.merge(loanMaster);
		auditDao.merge(audit);
	}
	
	public List<UserMaster> getUsersByLevels(int curLevel){
		return userMasterDao.getByLevels(curLevel);
	}
	
	public DirectorMaster getByDirectorId(String dirId){
		return directorMasterDao.findByDirectorId(dirId);
	}
	
	public List<UserMaster> getRoleList(String rName){
		return userMasterDao.getRoleList(rName);
	}
	
	public void saveNonCibilData(List<NonCibil> newNonCibilList){
		nonCibildao.batchSaveExcel(newNonCibilList);
	}
	
	public List<NonCibil> fetchNonCibilByApplicantId(long applicantId){
		return nonCibildao.fetchNonCibilByApplicantId(applicantId);
	}
	
	public LKCibilResponse getCibilData(String appId, String dirId){
		
		return cibilResponseDao.getCibilresponseByAppIdandDirId(appId,dirId);
		
	}
	
	public void saveCibilList(List<Cibil> resultList){
		cibilDao.batchSaveExcel(resultList);
	}
	
	public List<Cibil> getCibilData(String appId){
		
		return filterCibilData(cibilDao.getByLoanId(appId));
	}
	
	public List<Cibil> filterCibilData(List<Cibil> cibilList) {
		
		List<String> jointLoans = new ArrayList<String>();
		
		for (Iterator<Cibil> iterator = cibilList.iterator(); iterator.hasNext();) {
			
			Cibil cibil = iterator.next();
			
			if(cibil.getOwnershipType().equals("Joint")){
			
			String jointLoanString = cibil.getSanctionedamt()+cibil.getOutstanding()+cibil.getTypeofloan()+
					cibil.getStatus()+"!"+cibil.getApplicantId();
			
					int matchedIndex = jointLoans.indexOf(jointLoanString);	
						if(matchedIndex == -1){
							jointLoans.add(jointLoanString);
						}
						else{
							String matchedString = jointLoans.get(matchedIndex);
							
							String matchedSplit[] = matchedString.split("!");
							
							String stringSplit[] = jointLoanString.split("!");
							if(matchedSplit[1].equals(stringSplit[1])){
								
								
							}
							else{
								iterator.remove();	
							}
							
						}
						
				}
			
			else if(cibil.getOwnershipType().equals("Guarantor")){
				
				if(filterCibilByGuarantor(cibil)){
					
				}
				else{
					iterator.remove();
				}
				
			}
				
			
			}
		
		return cibilList;
		
	}

	public List<Applicant> getApplicantListByLoanId(String appId){
		return applicantDao.getApplicantDataByAppId(appId);
	}
	
	public Applicant saveApplicantsData(Applicant applicant){
		return applicantDao.merge(applicant);
	}
	
	private Applicant getApplicantByLoanAndDirectorId(Applicant applicant) {
		return applicantDao.getApplicantByLoanAndDirectorId(applicant);		
	}

	public List<Cibil> getCibilAnalysisDataByApplicantId(long id){
		return cibilDao.getByApplicantId(id);
	}
	
	public List<Cibil> getCibilDataByType(String s, String appId){
		
		return  filterCibilData(cibilDao.getByLoanType(s,appId));
		
	}

	public List<NonCibil> getNonCibilDataByType(String type, String appId){
		return nonCibildao.getByLoanType(type,appId);
	}
	
	public Applicant getPrimaryApplicantByLoanId(String appId){
		return applicantDao.getPrimaryApplicant(appId);
	}
	
	public void deleteApplicantDataByLoanId(String appId){
		applicantDao.deleteByLoanId(appId);
	}
	
	public void deleteCibilDataByLoanId(String loanMastertId){
		cibilDao.deleteByLoanId(loanMastertId);
	}
	
	public NonCibil getNonCibilById(long id){
		return nonCibildao.getById(id);
	}
	
	public List<NonCibil> fetchNonCibilByLoanId(String loanId){
		return nonCibildao.fetchNonCibilByLoanId(loanId);
	}
	
	public int getMinimumCibilByLoanId(String appId){
		
		return (int)applicantDao.getMinCibilScore(appId);
		
	}
	
	public String[] getCibilDirectors(String appId){
		
		
		List<Object[]> objectList = cibilResponseDao.getCibilDirectorsByAppId(appId);
		Object[] objectArray = objectList.toArray(new Object[objectList.size()]);
		String[] stringArray = Arrays.copyOf(objectArray, objectArray.length, String[].class);
		return stringArray;
		
	}
	
	public boolean filterCibilByGuarantor(Cibil cibil){
		
		if(cibil.getTypeofloan().length()>7 && cibil.getTypeofloan().substring(0,8).equalsIgnoreCase("Business"))
			return true;
		
		else 
			return false;
	}

	@Override
	public LoanMaster getApplicationByGuid(String appId) {
		return loanMasterDao.getApplicationByGuid(appId);
	}
	
	@Override
	public LoanStatusMaster findByStatusId(String statusName){
		return loanStatusMasterDao.getStatusIdByName(statusName);
	}
	
	public void saveLoan(LoanMaster loanMaster){
		
		loanMasterDao.merge(loanMaster);
	}
	
}
