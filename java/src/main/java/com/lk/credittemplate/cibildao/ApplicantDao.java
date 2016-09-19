package com.lk.credittemplate.cibildao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lk.credittemplate.dao.BaseDao;
import com.lk.credittemplate.model.Applicant;
import com.lk.credittemplate.model.PersonalDetailsApplicantDetails;


	@Repository
	public class ApplicantDao extends BaseDao{

			@PersistenceContext
		    private EntityManager entityManager;	
			
			
			public List<Applicant> getApplicantDataByAppId(String applicationID) {

				Query query = entityManager.createNamedQuery("getApplicantDataByAppId");
				query.setParameter("applicationID",applicationID);
				List<Applicant> applicantList = (List<Applicant>)query.getResultList();
				return applicantList;
			}


			public Applicant getPrimaryApplicant(String appId) {

				Query query = entityManager.createNamedQuery("getPrimaryApplicantDataByAppId");
				query.setParameter("applicationID",appId);
				return (Applicant)query.getSingleResult();
				
			}


			public void deleteByLoanId(String appId) {

					Query query = entityManager.createQuery("DELETE FROM Applicant a WHERE a.loanid = :id");
					query.setParameter("id", appId).executeUpdate();
			}


			public Object getMinCibilScore(String appId) {

					Query query = entityManager.createNamedQuery("getMinCibibyLoanId");
					query.setParameter("applicationID",appId);
					return query.getSingleResult();
					
				}

			public Applicant getMinCibil(String appId) {

				Query query = entityManager.createNamedQuery("getMinCibilbyLoanId");
				query.setParameter("applicationID",appId);
				return (Applicant) query.getSingleResult();
				
			}
			
			public Applicant getCibil(String appId) {

				Query query = entityManager.createNamedQuery("getMinCibilbyLoanId");
				query.setParameter("applicationID",appId);
				return (Applicant) query.getSingleResult();
				
			}

			public Applicant getApplicantByLoanAndDirectorId(Applicant applicant) {
				Applicant existApplicant = new Applicant();
				try {
					Query query = entityManager.createNamedQuery("getApplicantByLoanAndDirectorId");
					query.setParameter("applicationID",applicant.getLoanId());
					query.setParameter("directorName",applicant.getName());
					existApplicant = (Applicant)query.getSingleResult();
				} catch (Exception e) {
					e.printStackTrace();
				}				
				return existApplicant;
			}
			
			public Applicant getApplicantByLoanAndDirectorId(String name, String loanId) {
				Applicant existApplicant = new Applicant();
				
					Query query = entityManager.createNamedQuery("getApplicantByIds");
					query.setParameter("name",name);
					query.setParameter("loanId",loanId);
					
					List<Applicant> details = query.getResultList();
					Applicant applicant= null;
					if(details != null && details.size()>0)
					{
						applicant=details.get(0);
					}
					return applicant;
					
}
	}
