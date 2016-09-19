/**
 * 
 */
package com.lk.credittemplate.beans.ds;

import java.util.List;

/**
 * @author ramamohan
 *
 */
public class RespDataBean {

	private List<LoanVarScoreBean> loanVarScores;

	private LoanScoreBean loanScores;

	public List<LoanVarScoreBean> getLoanVarScores() {
		return loanVarScores;
	}

	public LoanScoreBean getLoanScores() {
		return loanScores;
	}

	public void setLoanVarScores(List<LoanVarScoreBean> loanVarScores) {
		this.loanVarScores = loanVarScores;
	}

	public void setLoanScores(LoanScoreBean loanScores) {
		this.loanScores = loanScores;
	}

	@Override
	public String toString() {
		return "RespDataBean [loanVarScores=" + loanVarScores + ", loanScores=" + loanScores + "]";
	}

}
