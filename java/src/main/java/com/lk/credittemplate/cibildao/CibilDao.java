package com.lk.credittemplate.cibildao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lk.credittemplate.dao.BaseDao;
import com.lk.credittemplate.model.Applicant;
import com.lk.credittemplate.model.Cibil;


@Repository
public class CibilDao extends BaseDao{

		@PersistenceContext
	    private EntityManager entityManager;	
		
		
		public List<Cibil> getByLoanId(String applicationID) {

			Query query = entityManager.createNamedQuery("getCibilDataByAppId");
			query.setParameter("applicationID",applicationID);
			List<Cibil> cibildataList = (List<Cibil>) query.getResultList();
			return cibildataList;
		}


		public List<Cibil> getByApplicantId(long id) {
			
			Query query = entityManager.createNamedQuery("getCibilDataByApplicantId");
			query.setParameter("applicantId",id);
			List<Cibil> cibildataList = (List<Cibil>) query.getResultList();
			return cibildataList;
		}


		public List<Cibil> getByLoanType(String type, String appId) {

			Query query = entityManager.createNamedQuery("getByLoanType");
			query.setParameter("appId",appId);
			query.setParameter("type", type);
			return (List<Cibil>) query.getResultList();
			
		}


		public void deleteByLoanId(String loanMastertId) {
			
			Query query = entityManager.createQuery("DELETE FROM Cibil a WHERE a.loanId = :id");
			query.setParameter("id", loanMastertId).executeUpdate();
			
		}
		
		/**
		 * getDuplicateApplicants
		 * 
		 * @return
		 */
		public Map<String,Date> getDuplicateApplicants() {
			Map<String, Date> map = new HashMap<>();
			try {
				Query query = entityManager.createQuery("select max(ca.createdDate),ca.name, ca.loanId,count(*) from Applicant ca group by ca.name, ca.loanId having count(*) > 1");
				List<Object[]> resultList = query.getResultList();
				for (Object[] objects : resultList) {
					map.put(objects[1]+"_"+objects[2], (Date)objects[0]);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return map;
		}

		/**
		 * getApplicantByNameAndLoan
		 * 
		 * @param name
		 * @param loanId
		 * @return
		 */
		public List<Applicant> getApplicantByNameAndLoan(String name, String loanId) {
			List<Applicant> applicants = new ArrayList<>();
			try {
				Query query = entityManager.createNamedQuery("getApplicantByNameAndLoan");
				query.setParameter("loanId",loanId);
				query.setParameter("name", name);
				applicants = query.getResultList();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return applicants;
		}
		
		@Transactional
		public void deleteCibilByApplicant(long applicantId) {
			try {
				Query query = entityManager.createQuery("DELETE FROM Cibil a WHERE a.applicantId= :applicantId");
				query.setParameter("applicantId", applicantId).executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		@Transactional
		public void deleteApplicantByApplicant(long applicantId) {
			
			Query query = entityManager.createQuery("DELETE FROM Applicant a WHERE a.id= :id");
			query.setParameter("id", applicantId).executeUpdate();
			
		}
		
		@Transactional
		public void deleteLoanEnquiriesByApplicant(long applicantId) {
			
			Query query = entityManager.createQuery("DELETE FROM LoanEnquiries a WHERE a.applicantId= :applicantId");
			query.setParameter("applicantId", applicantId).executeUpdate();
			
		}

}
