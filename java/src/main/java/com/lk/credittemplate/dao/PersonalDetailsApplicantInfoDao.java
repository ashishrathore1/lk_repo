package com.lk.credittemplate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lk.credittemplate.model.Analysis;
import com.lk.credittemplate.model.LoanStatusMaster;
import com.lk.credittemplate.model.PersonalDetailsApplicantDetails;
import com.lk.credittemplate.model.PersonalDetailsInfo;


@Repository
public class PersonalDetailsApplicantInfoDao extends BaseDao{

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

	public List<PersonalDetailsInfo> getApplicantInfoAppId(String infoAppId) {
		// TODO Auto-generated method stub
		Query query = entityManager.createNamedQuery("getApplicantInfoById");
		query.setParameter("infoAppId",infoAppId);
		PersonalDetailsInfo  personalDetailsInfo=null ;
		List<PersonalDetailsInfo> details = query.getResultList();
		if(details !=null && details.size() >= 0){
			return details;
		}
		else
		{
			return details;
		}
		
		
	}
	
 public PersonalDetailsInfo getApplicantInfoDirId(String dirId) {
		// TODO Auto-generated method stub
		Query query = entityManager.createNamedQuery("getApplicantInfoByDirId");
		query.setParameter("dirId",dirId);
		List<PersonalDetailsInfo> details = query.getResultList();
		PersonalDetailsInfo detail= null;
		if(details != null && details.size()>0)
		{
			detail=details.get(0);
		}
		return detail;
		
		
	}
	
		
	
	

	@Transactional
	public void deleteByID(String dirId) {
		Query query = entityManager.createQuery("DELETE FROM PersonalDetailsInfo e WHERE e.dirId = :dirId");
		query.setParameter("dirId", dirId).executeUpdate();
	}
	

	/**
	 * 
	 * @param statusId
	 * @return
	 */
//	public LoanStatusMaster findStatusById(String statusId) {
//		LoanStatusMaster loanStatusMaster=entityManager.find(LoanStatusMaster.class, statusId);
//		return loanStatusMaster;
//	}
}
