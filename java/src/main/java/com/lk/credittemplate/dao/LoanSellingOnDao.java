package com.lk.credittemplate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lk.credittemplate.model.Analysis;
import com.lk.credittemplate.model.LoanSellingOn;
import com.lk.credittemplate.model.LoanStatusMaster;
import com.lk.credittemplate.model.PersonalDetailsBusiness;


@Repository
public class LoanSellingOnDao extends BaseDao{

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

	public List<LoanSellingOn> getDataByMasterId(String lsoLoanId) {
		// TODO Auto-generated method stub
		Query query = entityManager.createNamedQuery("getSellingIdsByLoanId");
		query.setParameter("lsoLoanId",lsoLoanId);
		//LoanSellingOn  loanSellingOn=null ;
		List<LoanSellingOn> loanSellingOnList = query.getResultList();
		if(loanSellingOnList !=null && loanSellingOnList.size() > 0){
			return loanSellingOnList;
		}
		else
		{
			return null;
		}
		
	}
	
//	public Analysis findApplication(String applicationID) {
//		Query query = entityManager.createNamedQuery("findApplications");
//		query.setParameter("applicationID", applicationID);
//		List<Analysis> analysis = query.getResultList();
//		if (analysis != null && analysis.size() > 0) {
//			return analysis.get(0);
//		} else {
//			
//			return null;
//		}
//	}

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
