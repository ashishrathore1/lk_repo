package com.lk.credittemplate.cibildao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lk.credittemplate.cibilmodel.LoanMaster;
import com.lk.credittemplate.dao.BaseDao;

@Repository
public class LoanMasterDao extends BaseDao {

	@PersistenceContext
	private EntityManager entityManager;

	public LoanMaster findLoanDetails(String id) {
		LoanMaster loanMaster = entityManager.find(LoanMaster.class, id);
		return loanMaster;
	}

	@Transactional
	public void flush() {
		entityManager.flush();
	}

	@Transactional
	public void clear() {
		entityManager.clear();
	}

	
	/**
	 * 
	 * @param clsApplicationId
	 * @return
	 */
	public List<LoanMaster> findApplicationByClsApplicationId() {
		List<LoanMaster> loanMasterList = new ArrayList<LoanMaster>();
		Query query = entityManager.createNamedQuery("findAllApplications");
		boolean b = false;
		query.setParameter("status", b);
		return loanMasterList = query.getResultList();

	}

	public LoanMaster findLoanMasterDataByClsID(String appId) {
		TypedQuery<LoanMaster> query = entityManager
				.createQuery("SELECT l FROM " + "LoanMaster AS l WHERE l.clsApplicationId = :id", LoanMaster.class);
		query.setParameter("id", appId);
		return query.getSingleResult();
	}

	public LoanMaster getApplicationByAppId(String appId) {
		TypedQuery<LoanMaster> query = entityManager
				.createQuery("SELECT l FROM " + "LoanMaster AS l WHERE l.applicationId = :id", LoanMaster.class);
		query.setParameter("id", appId);
		return query.getSingleResult();
	}
    
	public LoanMaster getApplicationByGuid(String appId) {
		TypedQuery<LoanMaster> query = entityManager
				.createQuery("SELECT l FROM " + "LoanMaster AS l WHERE l.loanMastertId = :id", LoanMaster.class);
		query.setParameter("id", appId);
		return query.getSingleResult();
	}
	public Object getAppCountByStatusId(String statusId) {
		Query query = entityManager.createNamedQuery("getCountByStatusId");
		query.setParameter("statusid",statusId);
		Object count =  query.getSingleResult();
		return count;
	}

	public List<LoanMaster> getAppDataByStatusId(String statusId, int lowerLimt) {

		Query query = entityManager.createNamedQuery("getDataByStatusId");
		query.setParameter("statusid",statusId);
		return query.setFirstResult(lowerLimt).setMaxResults(50).getResultList();
		
	}

	public Object getAppCountBySubStatusId(String id) {
		
		Query query = entityManager.createNamedQuery("getCountBySubStatusId");
		query.setParameter("statusid",id);
		Object count =  query.getSingleResult();
		return count;
		
	}

	public Object getAppCountByStatusIdAndAssignee(String id, String name) {
		
		Query query = entityManager.createNamedQuery("getCountByStatusIdAndAssignee");
		query.setParameter("statusid",id);
		query.setParameter("name",name);
		Object count =  query.getSingleResult();
		return count;
	
	}

	public Object getAppCountBySubStatusIdAndAssignee(String id, String name) {
		Query query = entityManager.createNamedQuery("getCountBySubStatusAndAssignee");
		query.setParameter("statusid",id);
		query.setParameter("name",name);
		Object count =  query.getSingleResult();
		return count;
	}

	public List<LoanMaster> getAppDataByStatusId(String statusId, String assignee,int lowerLimt) {

		Query query = entityManager.createNamedQuery("getDataByStatusIdAndAssignee");
		query.setParameter("statusid",statusId);
		query.setParameter("name", assignee);
		return query.setFirstResult(lowerLimt).setMaxResults(50).getResultList();
		
	}

	public List<LoanMaster> getAppDataBySubStatus(String statusId, int lowerLimt) {
		Query query = entityManager.createNamedQuery("getDataBySubStatus");
		query.setParameter("statusid",statusId);
		return query.setFirstResult(lowerLimt).setMaxResults(50).getResultList();
	}

	public List<LoanMaster> findAppsBySearchData(String searchkey, String statusId, String assignee,int lowerLimit) {

		Query query = entityManager.createNamedQuery("getDataBySubStatus");
		query.setParameter("statusid",statusId);
		query.setParameter("name", assignee);
		return query.setFirstResult(lowerLimit).setMaxResults(50).getResultList();
		
	}

	public List<LoanMaster> findAppsBySearchData(String searchkey, String statusId,int lowerLimit) {
		Query query = entityManager.createNamedQuery("getDataBySubStatus");
		query.setParameter("statusid",statusId);
		return query.setFirstResult(lowerLimit).setMaxResults(50).getResultList();
	}

	
	public String findLoanMasterIdByAppId(String applicationId)
	{
		Query query =entityManager.createNamedQuery("getAppIdById");
		query.setParameter("applicationId", applicationId);
		return (String)query.getSingleResult();
	}

	public List<LoanMaster> getCADashboardApps(Map<String, String> columnMap, boolean search, int lowerLimt,String searchKey) {

		
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<LoanMaster> query = builder.createQuery(LoanMaster.class);
	    Root<LoanMaster> root = query.from(LoanMaster.class);
	    query.select(root);
	    
	    List<Predicate> predicatesGG = new ArrayList<Predicate>();
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		for (final Entry<String, String> e : columnMap.entrySet()) {

		    final String key = e.getKey();
		    final String value = e.getValue();
		            
		    predicates.add(builder.like(root.<String> get(key), value));
		        
			}
		
		query.where();
		
		if(search){
		
		  List<Predicate> predicatesOr = new ArrayList<Predicate>();	
		  predicatesOr.add(builder.like(root.<String> get("companyName"),"%"+searchKey+"%"));
		  predicatesOr.add(builder.like(root.<String> get("contactNo"),"%"+searchKey+"%"));
		  predicatesOr.add(builder.like(root.<String> get("email"),"%"+searchKey+"%"));
		  predicatesOr.add(builder.like(root.<String> get("applicationId"),"%"+searchKey+"%"));
		
		  Predicate gg = builder.or(predicatesOr.toArray(new Predicate[predicates.size()]));
		  Predicate omg = builder.and(predicates.toArray(new Predicate[predicates.size()]));
		  
		  predicatesGG.add(gg);
		  predicatesGG.add(omg);
		  
		  query.where(builder.and(predicatesGG.toArray(new Predicate[predicates.size()])));
		//).where()
		}
		
	    return entityManager.createQuery(query).setFirstResult(lowerLimt).setMaxResults(50).getResultList();
		}

}
