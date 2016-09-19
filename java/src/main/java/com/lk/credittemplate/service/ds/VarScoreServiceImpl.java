package com.lk.credittemplate.service.ds;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lk.credittemplate.beans.ds.LoanScoreBean;
import com.lk.credittemplate.beans.ds.LoanVarScoreBean;
import com.lk.credittemplate.beans.ds.VarScoreBean;
import com.lk.credittemplate.common.util.DSVarScoreUtil;
import com.lk.credittemplate.common.util.LKScoreCalcUtil;
import com.lk.credittemplate.common.util.LKUtils;
import com.lk.credittemplate.dao.ds.IVarScoreDao;
import com.lk.credittemplate.model.Variables;
import com.lk.credittemplate.model.ds.LoanScoreModel;
import com.lk.credittemplate.model.ds.LoanVarScoreModel;
import com.lk.credittemplate.model.ds.VarScoreModel;



/**
 * 
 * @author ramamohan
 *
 */
@Service
public class VarScoreServiceImpl implements IVarScoreService {

	@Autowired
	private IVarScoreDao varScoreDao;

	private String currentVersion;

	private static List<String> varNamesList = new ArrayList<>();

	// private static List<String> deriveredVarNames =
	// Arrays.asList("InvtSalesR","");

	@Transactional
	public void saveVariables(List<VarScoreBean> varScoreBeans) {

		List<VarScoreModel> scoreModels = new ArrayList<VarScoreModel>();
		for (VarScoreBean varScoreBean : varScoreBeans) {
			VarScoreModel varScoreModel = new VarScoreModel();
			BeanUtils.copyProperties(varScoreBean, varScoreModel);
			scoreModels.add(varScoreModel);
		}
		varScoreDao.saveVarScores(scoreModels);
	}

	@Transactional
	public List<LoanVarScoreBean> getLoanVarScores(String loanAppId) {
		List<LoanVarScoreModel> loanVarScoreModels = varScoreDao.getLoanVarScoresByLoanId(loanAppId);
		return DSVarScoreUtil.copyToVarScoreBean(loanVarScoreModels);
	}

	@Transactional
	public LoanScoreBean getLoanScores(String loanAppId) {
		LoanScoreModel loanScoreModel = varScoreDao.getLoanScores(loanAppId);
		LoanScoreBean loanScoreBean = new LoanScoreBean();
		DSVarScoreUtil.copyValues(loanScoreModel, loanScoreBean);
		return loanScoreBean;
	}

	@Transactional
	public List<LoanVarScoreModel> calculateVarScores(String loanAppId) {
		List<LoanVarScoreModel> loanVarScoreModels = varScoreDao.getLoanVarScoresByLoanId(loanAppId);
		Map<String, VarScoreModel> varScoresMap = getVarScores();
		if (loanVarScoreModels.isEmpty() || !loanVarScoreModels.get(0).getVersion().equalsIgnoreCase(currentVersion)) {
			double loanAmount = getLoanAmount(loanAppId);
			List<Variables> variables = getVariableValues(loanAppId, varNamesList);
			for (Variables variable : variables) {
				LoanVarScoreModel loanVarScoreModel = new LoanVarScoreModel();
				loanVarScoreModel.setLoanApplicationId(loanAppId);
				loanVarScoreModel.setCreatedDate(LKUtils.getSQLDate(new Date()));
				String varName = variable.getKey();
				VarScoreModel varScoreModel = varScoresMap.get(varName.toLowerCase());
				if (varName.equalsIgnoreCase("inventory")) {
					loanVarScoreModel.setVarScore(LKScoreCalcUtil.getInventoryScore(variable.getVal(), loanAmount + "")
							* varScoreModel.getWeight());
				} else {
					loanVarScoreModel.setVarScore(LKScoreCalcUtil.getVarScore(variable, varScoreModel));
				}

				loanVarScoreModel.setValue(variable.getVal());
				loanVarScoreModel.setVersion(varScoreModel.getVersion());
				loanVarScoreModel.setVarScoreModel(varScoreModel);
				loanVarScoreModel.setUpdatedDate(LKUtils.getSQLDate(new Date()));
				loanVarScoreModels.add(loanVarScoreModel);
			}
			List<LoanVarScoreModel> newLoanVarScoreModels = varScoreDao.saveOrUpdateLoanVarScores(loanVarScoreModels);
			LoanScoreModel loanScoreModel = new LoanScoreModel();
			loanScoreModel.setLoanApplicationId(loanAppId);
			loanScoreModel.setVersion(currentVersion);
			// DSVarScoreUtil.setLoanScoreDefaultData(loanScoreModel);
			LKScoreCalcUtil.calculateLoanScores(newLoanVarScoreModels, loanScoreModel);
			varScoreDao.saveLoanScores(loanScoreModel);
			varScoreDao.saveLKScore(loanScoreModel.getScore(), loanAppId);
			return newLoanVarScoreModels;
		}
		return loanVarScoreModels;
	}

	/**
	 * getVarScores
	 * 
	 * @return
	 */
	@Transactional
	public Map<String, VarScoreModel> getVarScores() {
		Map<String, VarScoreModel> varScoresMap = new HashMap<String, VarScoreModel>();
		List<VarScoreModel> varScoreModels = varScoreDao.getVarScores();
		currentVersion = varScoreModels.isEmpty() ? "0" : varScoreModels.get(0).getVersion();
		for (VarScoreModel varScoreModel : varScoreModels) {
			String varName = varScoreModel.getVarName();
			varScoresMap.put(varName.toLowerCase(), varScoreModel);
			if (!varNamesList.contains(varName)) {
				varNamesList.add(varName);
			}
		}
		return varScoresMap;
	}

	/**
	 * getVariableValues
	 * 
	 * @param loanAppId
	 * @param currentVersion2
	 * @param varNames
	 * @return
	 */
	@Transactional
	public List<Variables> getVariableValues(String loanAppId, List<String> varNames) {
		return varScoreDao.getVariables(loanAppId, currentVersion, varNames);
	}

	@Transactional
	public double getLoanAmount(String loanAppId) {
		return varScoreDao.getLoanAmount(loanAppId, currentVersion, "LoanEligible");
	}

	@Transactional
	public List<String> getLoanAppIds() {
		return varScoreDao.getLoanAppIds();
	}

	@Transactional
	public LoanScoreBean saveLoanScores(LoanScoreBean loanScoreBean) {
		LoanScoreModel loanScoreModel = new LoanScoreModel();
		DSVarScoreUtil.copyValues(loanScoreBean, loanScoreModel);
		loanScoreModel = varScoreDao.saveLoanScores(loanScoreModel);
		DSVarScoreUtil.copyValues(loanScoreModel, loanScoreBean);
		return loanScoreBean;
	}

	@Transactional
	public String getLoanScore(String loanAppId) {
		LoanScoreModel loanScores = varScoreDao.getLoanScores(loanAppId);
		return loanScores != null ? loanScores.getScore() : "0";
	}
}
