package com.lk.credittemplate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lk.credittemplate.model.Audit;
import com.lk.credittemplate.model.LoanStatusMaster;

@Repository
public class AuditDao extends BaseDao {

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
	public Audit findSubmitByAppId(String appId) {
		TypedQuery<Audit> query = entityManager.createQuery(
				"SELECT c FROM " + "Audit AS c WHERE c.appId = :id  ORDER BY c.uploadTime desc", Audit.class);
		query.setParameter("id", appId);
		
		Audit audit=null;
		List<Audit> AuditList = query.getResultList();
		if(AuditList !=null && AuditList.size() > 0){
			audit=AuditList.get(0);
			
	}
		return audit;
     }
	@Transactional
	public Audit findSubmitByLoanId(String appId) {
		Query query = entityManager.createNamedQuery("findSubmitByLoanId");

		query.setParameter("appId",appId);
		
//		Audit audit =query.setMaxResults(1).getSingleResult();
		Audit audit=null;
		List<Audit> AuditList=query.getResultList();
		if(AuditList !=null && AuditList.size() >0)
		{
			audit=AuditList.get(0);
		
	}
	return audit;

}
}
