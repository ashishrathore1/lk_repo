package com.lk.credittemplate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lk.credittemplate.model.Analysis;
import com.lk.credittemplate.model.LoanStatusMaster;
import com.lk.credittemplate.model.PersonalDetailsCompany;
import com.lk.credittemplate.model.PersonalDetailsEYC;


@Repository
public class PersonalDetailsEycDao extends BaseDao{

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

	public PersonalDetailsEYC getKYCDetailsByAppId(String appId) {
		// TODO Auto-generated method stub
		Query query = entityManager.createNamedQuery("getKycByName");
		query.setParameter("appId",appId);
		PersonalDetailsEYC  personalDetailsEYC=null ;
		List<PersonalDetailsEYC> personalDetailsEYCs = query.getResultList();
		if(personalDetailsEYCs !=null && personalDetailsEYCs.size() >= 1){
			personalDetailsEYC=personalDetailsEYCs.get(0);
		}
		
		return personalDetailsEYC;
	}
	
//	public PersonalDetailsCompany findApplication(String applicationID) {
//		Query query = entityManager.createNamedQuery("findApplications");
//		query.setParameter("applicationID", applicationID);
//		List<PersonalDetailsCompany> personalDetailsCompanies = query.getResultList();
//		if (personalDetailsCompanies != null && personalDetailsCompanies.size() > 0) {
//			return personalDetailsCompanies.get(0);
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
