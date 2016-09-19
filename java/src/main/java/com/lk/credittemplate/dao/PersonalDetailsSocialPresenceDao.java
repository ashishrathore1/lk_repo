package com.lk.credittemplate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lk.credittemplate.model.PersonalDetailsSocialPresence;
import com.lk.credittemplate.model.Analysis;
import com.lk.credittemplate.model.LoanStatusMaster;
import com.lk.credittemplate.model.PersonalDetailsCompany;


@Repository
public class PersonalDetailsSocialPresenceDao extends BaseDao{

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

	public List<PersonalDetailsSocialPresence> getSocialPresenceByAppId(String loanAppId) {
		// TODO Auto-generated method stub
		Query query = entityManager.createNamedQuery("getSocialPresenceById");
		query.setParameter("loanAppId",loanAppId);
		//PersonalDetailsSocialPresence  personalDetailsSocialPresence=null ;
		List<PersonalDetailsSocialPresence> details = query.getResultList();
		if(details !=null ){
			return details;
		}
		else
		{
		return details;
	}
		
	}
	
	public PersonalDetailsSocialPresence findApplication(String loanAppId) {
		Query query = entityManager.createNamedQuery("getSocialPresenceById");
		query.setParameter("loanAppId", loanAppId);
		PersonalDetailsSocialPresence personalDetailsSocialPresence=null;
		List<PersonalDetailsSocialPresence> personalDetailsCompanies = query.getResultList();
		if (personalDetailsCompanies != null && personalDetailsCompanies.size() > 0) {
			 personalDetailsSocialPresence=personalDetailsCompanies.get(0);
		} 
			
			return personalDetailsSocialPresence;
		
	}

	/**
	 * 
	 * @param statusId
	 * @return
	 */
	@Transactional
	public void deleteByID(String loanAppId) {
		Query query = entityManager.createNamedQuery("deleteSocialPresenceById");
		query.setParameter("loanAppId", loanAppId).executeUpdate();
	}
}
