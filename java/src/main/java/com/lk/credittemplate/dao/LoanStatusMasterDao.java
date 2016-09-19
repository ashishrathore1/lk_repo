package com.lk.credittemplate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lk.credittemplate.model.LoanStatusMaster;


@Repository
public class LoanStatusMasterDao extends BaseDao{

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

	public LoanStatusMaster getStatusIdByName(String name) {
		// TODO Auto-generated method stub
		Query query = entityManager.createNamedQuery("getStatusByName");
		query.setParameter("name",name);
		LoanStatusMaster  loanStatusMaster=null ;
		List<LoanStatusMaster> loanStatusMasterList = query.getResultList();
		if(loanStatusMasterList !=null && loanStatusMasterList.size() >= 1){
			loanStatusMaster=loanStatusMasterList.get(0);
		}
		
		return loanStatusMaster;
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
