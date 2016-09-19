package com.lk.credittemplate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lk.credittemplate.model.PersonalDetailsSocialPresence;
import com.lk.credittemplate.cibilmodel.LoanMaster;
import com.lk.credittemplate.model.Analysis;
import com.lk.credittemplate.model.LoanStatusMaster;
import com.lk.credittemplate.model.MasterExcel;
import com.lk.credittemplate.model.PersonalDetailsApplicantDetails;
import com.lk.credittemplate.model.PersonalDetailsCompany;


@Repository
public class PersonalDetailsSocialApplicantDao extends BaseDao{

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
	@Transactional
	public List<PersonalDetailsApplicantDetails> getPersonalDetailsApplicantById(String socialAppId) {
		// TODO Auto-generated method stub
		Query query = entityManager.createNamedQuery("getPersonalDetailsApplicantById");
		query.setParameter("socialAppId",socialAppId);
		PersonalDetailsApplicantDetails  personalDetailsApplicantDetails=null ;
		List<PersonalDetailsApplicantDetails> details = query.getResultList();
//		if(details !=null && details.size() >= 1){
//			personalDetailsApplicantDetails=details.
//		}
		
		return details;
	}
	@Transactional
	public PersonalDetailsApplicantDetails getPersonalDirectorDetailsById(String directorId) {
		// TODO Auto-generated method stub
		Query query=entityManager.createNamedQuery("getPersonalDetailsApplicantByDirId");
		query.setParameter("directorId",directorId);
		List<PersonalDetailsApplicantDetails> details = query.getResultList();
		PersonalDetailsApplicantDetails personalDetailsApplicantDetails= null;
		if(details != null && details.size()>0)
		{
			personalDetailsApplicantDetails=details.get(0);
		}
		return personalDetailsApplicantDetails;
		
		
	}
	
	@Transactional
	public void deleteByID(String directorId) {
		Query query = entityManager.createQuery("DELETE FROM PersonalDetailsApplicantDetails e WHERE e.directorId = :directorId");
		query.setParameter("directorId", directorId).executeUpdate();
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
