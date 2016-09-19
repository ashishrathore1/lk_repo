package com.lk.credittemplate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lk.credittemplate.model.LoanEnquiries;


@Repository
public class LoanEnquiriesDao extends BaseDao{
	
	@PersistenceContext
    private EntityManager entityManager;

	public List<LoanEnquiries> getLoanEnquiries(long id) {

		Query query = entityManager.createNamedQuery("getLoanEnquiries");
		query.setParameter("applicantId",id);
		return (List<LoanEnquiries>)query.getResultList();
	}

	public void deleteByAppId(long id) {

		Query query = entityManager.createQuery("DELETE FROM LoanEnquiries l WHERE l.applicantId =:id");
		query.setParameter("id", id).executeUpdate();
	}

	
}
