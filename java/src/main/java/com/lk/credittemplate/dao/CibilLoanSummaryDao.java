package com.lk.credittemplate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lk.credittemplate.model.CibilLoanSummary;

@Repository
public class CibilLoanSummaryDao extends BaseDao{

	@PersistenceContext
    private EntityManager entityManager;

	public List<CibilLoanSummary> getTypeofLoanByApplicantId(long id) {
		
			Query query = entityManager.createNamedQuery("getLoanSumaryDataByApplicantId");
			query.setParameter("applicantId",id);
			List<CibilLoanSummary> cibildataList = (List<CibilLoanSummary>) query.getResultList();
			return cibildataList;
		}
}

