package com.lk.credittemplate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lk.credittemplate.cibildao.LoanMasterDao;
import com.lk.credittemplate.cibilmodel.DirectorMaster;
import com.lk.credittemplate.cibilmodel.LoanMaster;
import com.lk.credittemplate.common.util.LKUtils;
import com.lk.credittemplate.dao.AsigneeAppsDao;
import com.lk.credittemplate.dao.AuditDao;
import com.lk.credittemplate.dao.LoanCommentsDao;
import com.lk.credittemplate.dao.StatusTrackerDao;
import com.lk.credittemplate.model.AsigneeApps;
import com.lk.credittemplate.model.Audit;
import com.lk.credittemplate.model.LoanComments;
import com.lk.credittemplate.model.LoanCommentsCat;
import com.lk.credittemplate.model.LoanCommentsCibil;
import com.lk.credittemplate.model.StatusTracker;




@Service
@Transactional
public class ApprovalServiceImpl implements ApprovalService{
	
	@Autowired
	AsigneeAppsDao asigneeAppsDao;
	
	@Autowired
	LoanMasterDao loanMasterDao;

	@Autowired
	AuditDao auditDao;
	
	@Autowired
	LoanCommentsDao loanCommentsDao;
	
	@Autowired
	StatusTrackerDao statusTrackerDao;
	
	
	public AsigneeApps findAssignedAppById(String appId) {

		List<AsigneeApps> result = asigneeAppsDao.findByAppId(appId).getResultList();
		if (result.size() == 0) {
			return null;
		} else {
			return asigneeAppsDao.findByAppId(appId).getSingleResult();
		}
	}
	
	public void saveAsigneeTrack(AsigneeApps asigneeApps) {
		asigneeAppsDao.merge(asigneeApps);
	}
	
	
	public void commitCommentTrans(LoanCommentsCat loanCommentsCat, AsigneeApps asigneeApps, LoanMaster loanMaster,StatusTracker statusTracker){
		
		loanCommentsDao.merge(loanCommentsCat);
		statusTracker.setStatus(loanMaster.getStatusId());
		statusTrackerDao.merge(statusTracker);
		saveAsigneeTrack(asigneeApps);
		
		
		if(loanMaster != null)
		saveLoanMaster(loanMaster);

	}
	
	public void saveLoanMaster(LoanMaster loanMaster) {

		loanMasterDao.merge(loanMaster);
	}
	
	public void commitSubmitForApproval(AsigneeApps asigneeApps, Audit audit, LoanCommentsCat loanCommentsCat, LoanMaster loanMaster){
		auditDao.merge(audit);
		saveAsigneeTrack(asigneeApps);
		loanCommentsDao.merge(loanCommentsCat);
		loanMaster.setModified(LKUtils.getDate());
		loanMasterDao.merge(loanMaster);
	}
	
	public List<LoanCommentsCibil> getCibilCommentsByLoanIdAndDirId(String appId, DirectorMaster directorMaster){
		return loanCommentsDao.getCibilComments(appId,directorMaster);
	}
	
	public void saveCibilComments(LoanCommentsCibil cibilComments){
		loanCommentsDao.merge(cibilComments);
	}
	
	public void saveCatComments(LoanCommentsCat catComments){
		loanCommentsDao.merge(catComments);
	}
	
	public List<LoanComments> getCatCommentsByLoanId(String appId){
		return loanCommentsDao.getCatComments(appId);
	}


}
