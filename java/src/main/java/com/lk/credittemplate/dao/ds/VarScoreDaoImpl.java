package com.lk.credittemplate.dao.ds;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lk.credittemplate.dao.BaseDao;
import com.lk.credittemplate.model.Variables;
import com.lk.credittemplate.model.ds.LoanScoreModel;
import com.lk.credittemplate.model.ds.LoanVarScoreModel;
import com.lk.credittemplate.model.ds.VarScoreModel;


@Repository
@SuppressWarnings("unchecked")
public class VarScoreDaoImpl extends BaseDao implements IVarScoreDao {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * saveVarScores
	 */
	public void saveVarScores(List<VarScoreModel> varScoreModels) {
		batchSave(varScoreModels);
	}

	/**
	 * saveLoanVarScores
	 * 
	 * @return
	 */
	public List<LoanVarScoreModel> saveOrUpdateLoanVarScores(List<LoanVarScoreModel> loanVarScores) {
		return batchSaveUpdate(loanVarScores);
	}

	/**
	 * getVarScores
	 */
	public List<VarScoreModel> getVarScores() {
		Query query = entityManager.createNamedQuery("getVarScores");
		List<VarScoreModel> varScoreModels = query.getResultList();
		return varScoreModels != null ? varScoreModels : new ArrayList<VarScoreModel>();
	}

	/**
	 * getLoanVarScoresByLoanId
	 */
	@Override
	public List<LoanVarScoreModel> getLoanVarScoresByLoanId(String loanAppId) {
		Query query = entityManager.createNamedQuery("getLoanVarScoresByLoanId");
		query.setParameter("loanApplicationId", loanAppId);
		List<LoanVarScoreModel> loanVarScoreModels = query.getResultList();
		return loanVarScoreModels != null ? loanVarScoreModels : new ArrayList<LoanVarScoreModel>();
	}

	@Override
	public List<Variables> getVariables(String loanAppId, String version, List<String> varNames) {
		Query query = entityManager.createNamedQuery("getVariables");
		query.setParameter("appId", loanAppId);
		query.setParameter("version", version);
		query.setParameter("keyNames", varNames);
		List<Variables> variables = query.getResultList();
		return variables != null ? variables : new ArrayList<Variables>();
	}

	@Override
	public List<String> getLoanAppIds() {
		Query query = entityManager.createNativeQuery("SELECT lm.L_APPLICATION_ID FROM LK_LOAN_MASTER lm");
		List<String> loanAppIds = query.getResultList();
		return loanAppIds != null ? loanAppIds : new ArrayList<String>();
	}

	/*
	 * @Override public List<String> getLoanMasterData(String loanAppId) { Query
	 * query = entityManager.createNativeQuery(
	 * "SELECT lm.* FROM LK_LOAN_MASTER lm"); entityManager.createQuery("").
	 * Map<String, Object> loanAppIds = query.getHints(); Set<Parameter<?>>
	 * parameters = query.getParameters(); return loanAppIds==null?null:new
	 * ArrayList<String>(); }
	 */

	@Override
	public LoanScoreModel saveLoanScores(LoanScoreModel loanScoreModel) {
		LoanScoreModel scoreModel = merge(loanScoreModel);
		return scoreModel != null ? scoreModel : new LoanScoreModel();
	}

	@Override
	public LoanScoreModel getLoanScores(String loanAppId) {
		Query query = entityManager.createNamedQuery("getLoanScoresByLoanId");
		query.setParameter("loanApplicationId", loanAppId);
		LoanScoreModel loanScoreModel = (LoanScoreModel) query.getSingleResult();
		return loanScoreModel != null ? loanScoreModel : new LoanScoreModel();
	}

	@Override
	public double getLoanAmount(String loanAppId, String version, String varName) {
		Query query = entityManager.createNamedQuery("getLoanAmount");
		query.setParameter("appId", loanAppId);
		query.setParameter("version", version);
		query.setParameter("keyNames", varName);
		Variables variable = (Variables) query.getSingleResult();
		return variable != null ? Double.valueOf(variable.getVal()) : 0;
	}

	@Override
	public void saveLKScore(String score, String loanAppId) {
		Query query = entityManager.createNativeQuery(
				"UPDATE LK_LOAN_MASTER lm SET lm.L_LK_SCORE=:lkScore WHERE lm.L_APPLICATION_ID=:loanAppId");
		query.setParameter("lkScore", score);
		query.setParameter("loanAppId", loanAppId);
		int executeUpdate = query.executeUpdate();
		System.out.println("executeUpdate LKSCORE :: " + executeUpdate);
	}

}
