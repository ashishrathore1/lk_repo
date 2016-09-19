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
import com.lk.credittemplate.model.SellingOnMaster;


@Repository
public class SellingOnMasterDao extends BaseDao{

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

	public SellingOnMaster getDataBySellingOnId(String pGuid) {
		// TODO Auto-generated method stub
		Query query = entityManager.createNamedQuery("getSellingnamesFromSellGuid");
		query.setParameter("pGuid",pGuid);
		SellingOnMaster  sellingOnMaster=null ;
		List<SellingOnMaster> sellingOnMasterList = query.getResultList();
		if(sellingOnMasterList !=null && sellingOnMasterList.size() > 0){
			return sellingOnMaster=sellingOnMasterList.get(0);
		}
		else
		
			return sellingOnMaster;
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
