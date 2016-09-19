/**
 * 
 */
package com.lk.credittemplate.common.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.lk.credittemplate.beans.ds.LoanVarScoreBean;
import com.lk.credittemplate.beans.ds.VarScoreBean;
import com.lk.credittemplate.model.ds.LoanScoreModel;
import com.lk.credittemplate.model.ds.LoanVarScoreModel;


/**
 * @author ramamohan
 *
 */
public class DSVarScoreUtil {

	/**
	 * copyToVarScoreBean
	 * 
	 * @param loanVarScores
	 * @return
	 */
	public static List<LoanVarScoreBean> copyToVarScoreBean(List<LoanVarScoreModel> loanVarScores) {
		List<LoanVarScoreBean> loanVarScoreBeans = new ArrayList<LoanVarScoreBean>();
		for (LoanVarScoreModel loanVarScoreModel : loanVarScores) {
			LoanVarScoreBean loanVarScoreBean = new LoanVarScoreBean();
			copyValues(loanVarScoreModel, loanVarScoreBean);
			VarScoreBean varScoreBean = new VarScoreBean();
			varScoreBean.setVarScore(loanVarScoreModel.getVarScore());
			copyValues(loanVarScoreModel.getVarScoreModel(), varScoreBean);
			loanVarScoreBean.setVarScoreBean(varScoreBean);
			loanVarScoreBeans.add(loanVarScoreBean);
		}
		return loanVarScoreBeans;
	}

	/**
	 * copyToVarScoreBean
	 * 
	 * @param loanVarScores
	 * @return
	 */
	public static List<LoanVarScoreBean> copyToVarScoreRespBean(List<LoanVarScoreModel> loanVarScores) {
		List<LoanVarScoreBean> loanVarScoreBeans = new ArrayList<LoanVarScoreBean>();
		for (LoanVarScoreModel loanVarScoreModel : loanVarScores) {
			LoanVarScoreBean loanVarScoreBean = new LoanVarScoreBean();
			copyValues(loanVarScoreModel, loanVarScoreBean);
			VarScoreBean varScoreBean = new VarScoreBean();
			copyValues(loanVarScoreModel.getVarScoreModel(), varScoreBean);
			loanVarScoreBean.setVarScoreBean(varScoreBean);
			loanVarScoreBeans.add(loanVarScoreBean);
		}
		return loanVarScoreBeans;
	}

	/**
	 * copyValues
	 * 
	 * @param s
	 * @param t
	 */
	public static <S, T> void copyValues(S s, T t) {
		BeanUtils.copyProperties(s, t);
	}

	/**
	 * setLoanScoreDefaultData
	 * 
	 * @param loanScoreModel
	 */
	public static void setLoanScoreDefaultData(LoanScoreModel loanScoreModel) {
		loanScoreModel.setCreatedDate(LKUtils.getDate());
		loanScoreModel.setUpdatedDate(LKUtils.getDate());
		loanScoreModel.setScore("670");
		loanScoreModel.setScale("20.4");
		loanScoreModel.setFinancialHealthScore("34.5");
		loanScoreModel.setFinancialPercentile("23.6");
		loanScoreModel.setMarketplacePerformanceScore("13.2");
		loanScoreModel.setMarketplacePercentile("33.3");
		loanScoreModel.setOperatedBy("SuperAdmin");
		loanScoreModel.setPercentile("78.9");
		loanScoreModel.setSocialPercentile("56.0");
		loanScoreModel.setSocialReliabiltyScore("99");
		loanScoreModel.setStatutoryCompliance("76.4");
		loanScoreModel.setStatutoryPercentile("45");
	}
}
