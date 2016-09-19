package com.lk.credittemplate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lk.credittemplate.model.NonCibil;


@Repository
public class NonCibilDao extends BaseDao{

	
	@PersistenceContext
	private EntityManager entityManager;

	public List<NonCibil> fetchNonCibilByApplicantId(long applicantId) {
		
		Query query = entityManager.createNamedQuery("getNonCibilByApplicantId");
		query.setParameter("appId",applicantId );
		return query.getResultList();
	}

	public List<NonCibil> getByLoanType(String type, String appId) {
		
		Query query = entityManager.createNamedQuery("getNonCibilByLoanType");
		query.setParameter("appId",appId);
		query.setParameter("type", type);
		return (List<NonCibil>)query.getResultList();
		
	}

	public NonCibil getById(long id) {
		
		NonCibil nonCibil = entityManager.find(NonCibil.class, id);
		return nonCibil;
	}
	
	public List<NonCibil> fetchNonCibilByLoanId(String appId) {
		
		Query query = entityManager.createNamedQuery("getNonCibilByLoanId");
		query.setParameter("appId",appId );
		return query.getResultList();
	}

}
