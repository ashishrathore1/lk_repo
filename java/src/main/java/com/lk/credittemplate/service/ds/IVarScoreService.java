package com.lk.credittemplate.service.ds;

import java.util.List;

import com.lk.credittemplate.beans.ds.LoanScoreBean;
import com.lk.credittemplate.beans.ds.LoanVarScoreBean;
import com.lk.credittemplate.beans.ds.VarScoreBean;
import com.lk.credittemplate.model.ds.LoanVarScoreModel;



/**
 * 
 * @author ramamohan
 *
 */
public interface IVarScoreService {

	void saveVariables(List<VarScoreBean> varScoreBeans);

	List<LoanVarScoreBean> getLoanVarScores(String loanAppId);

	List<LoanVarScoreModel> calculateVarScores(String loanAppId);

	List<String> getLoanAppIds();

	LoanScoreBean saveLoanScores(LoanScoreBean loanScoreBean);

	LoanScoreBean getLoanScores(String loanAppId);

	String getLoanScore(String loanAppId);

}
