/**
 * 
 */
package com.lk.credittemplate.dao.ds;

import java.util.List;

import com.lk.credittemplate.model.Variables;
import com.lk.credittemplate.model.ds.LoanScoreModel;
import com.lk.credittemplate.model.ds.LoanVarScoreModel;
import com.lk.credittemplate.model.ds.VarScoreModel;


/**
 * @author ramamohan
 *
 */
public interface IVarScoreDao {

	void saveVarScores(List<VarScoreModel> scoreModels);

	List<LoanVarScoreModel> saveOrUpdateLoanVarScores(List<LoanVarScoreModel> loanVarScores);

	List<VarScoreModel> getVarScores();

	List<LoanVarScoreModel> getLoanVarScoresByLoanId(String loanAppId);

	List<Variables> getVariables(String loanAppId, String currentVersion, List<String> varNames);

	List<String> getLoanAppIds();

	LoanScoreModel saveLoanScores(LoanScoreModel loanScoreModel);

	LoanScoreModel getLoanScores(String loanAppId);

	double getLoanAmount(String loanAppId, String currentVersion, String string);

	void saveLKScore(String score, String loanAppId);

}
