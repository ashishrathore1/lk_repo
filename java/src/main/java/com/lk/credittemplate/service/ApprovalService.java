package com.lk.credittemplate.service;

import java.util.List;

import com.lk.credittemplate.cibilmodel.DirectorMaster;
import com.lk.credittemplate.cibilmodel.LoanMaster;
import com.lk.credittemplate.model.AsigneeApps;
import com.lk.credittemplate.model.Audit;
import com.lk.credittemplate.model.LoanComments;
import com.lk.credittemplate.model.LoanCommentsCat;
import com.lk.credittemplate.model.LoanCommentsCibil;
import com.lk.credittemplate.model.StatusTracker;



public interface ApprovalService {

	public AsigneeApps findAssignedAppById(String appId);
	
	public void saveAsigneeTrack(AsigneeApps asigneeApps);
	
	public void commitCommentTrans(LoanCommentsCat loanCommentsCat, AsigneeApps asigneeApps, LoanMaster loanMaster,StatusTracker statusTracker);
	
	public void saveLoanMaster(LoanMaster loanMaster);

	public void commitSubmitForApproval(AsigneeApps asigneeApps, Audit audit, LoanCommentsCat loanCommentsCat, LoanMaster loanMaster);

	public List<LoanCommentsCibil> getCibilCommentsByLoanIdAndDirId(String appId, DirectorMaster directorMaster);

	public void saveCibilComments(LoanCommentsCibil loan_Comments_Cibil);

	public void saveCatComments(LoanCommentsCat loanCommentsCat);

	public List<LoanComments> getCatCommentsByLoanId(String appId);
	
}
