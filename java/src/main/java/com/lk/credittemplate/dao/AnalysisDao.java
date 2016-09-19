package com.lk.credittemplate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lk.credittemplate.model.Analysis;
import com.lk.credittemplate.model.LoanStatusMaster;


@Repository
public class AnalysisDao extends BaseDao{

	@PersistenceContext
    private EntityManager entityManager;
	
	@Transactional
    public void flush() {
        entityManager.flush();
    }

	@Transactional
    public void clear() {
        entityManager.clear();
    }

	public Analysis getAnalysisByAppId(String applicationID) {
		// TODO Auto-generated method stub
		Query query = entityManager.createNamedQuery("getAnalysisByName");
		query.setParameter("applicationID",applicationID);
		Analysis  analysis=null ;
		List<Analysis> AnalysisList = query.getResultList();
		if(AnalysisList !=null && AnalysisList.size() >= 1){
			analysis=AnalysisList.get(0);
		}
		
		return analysis;
	}
	
	public Analysis findApplication(String applicationID) {
		Query query = entityManager.createNamedQuery("findApplications");
		query.setParameter("applicationID", applicationID);
		List<Analysis> analysis = query.getResultList();
		if (analysis != null && analysis.size() > 0) {
			return analysis.get(0);
		} else {
			
			return null;
		}
	}

	/**
	 * 
	 * @param statusId
	 * @return
	 */
	public LoanStatusMaster findStatusById(String statusId) {
		LoanStatusMaster loanStatusMaster=entityManager.find(LoanStatusMaster.class, statusId);
		return loanStatusMaster;
	}
}
