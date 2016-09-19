package com.lk.credittemplate.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lk.credittemplate.beans.PersonalDetailsCalculationBean;
import com.lk.credittemplate.model.Analysis;
import com.lk.credittemplate.model.LoanStatusMaster;
import com.lk.credittemplate.model.PersonalDetailsBusiness;
import com.lk.credittemplate.model.PersonalDetailsCalculation;


@Repository
public class PersonalDetailsCalculationDao extends BaseDao{

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

	public List<PersonalDetailsCalculationBean> getCalculationsByAppId(String CalappId) {
		// TODO Auto-generated method stub
		Query query = entityManager.createNamedQuery("getPersonalDetailsCalculationsById");
		query.setParameter("CalappId",CalappId);
		List<PersonalDetailsCalculation> personalDetailsCalculations = query.getResultList();
		 List<PersonalDetailsCalculationBean> response = new ArrayList<PersonalDetailsCalculationBean>();
		
		
		if(personalDetailsCalculations !=null && personalDetailsCalculations.size() > 0){
			for(PersonalDetailsCalculation iter: personalDetailsCalculations){
				PersonalDetailsCalculationBean tmp = new PersonalDetailsCalculationBean();
				
				tmp.setAppId(iter.getCalappId());
				tmp.setCalId(iter.getCalId());
				tmp.setDisbursementAccNum(iter.getBankAccountNum());
				tmp.setDisbursementDate(iter.getDisbursementDate());
				tmp.setDuration(iter.getDuration());
				tmp.seteCollection(iter.iseCollection());
				tmp.setInstallmentAmount(iter.getInstallmentAmount());
				tmp.setInstallmentEndDate(iter.getInstallmentEndDate());
				tmp.setInstallmentFrequency(iter.getInstallmentFrequency());
				tmp.setInstallmentWithDelay(iter.getInstallmentDelays());
				tmp.setInterestType(iter.getInterestType());
				tmp.setLatePaymentChargesDue(iter.getLatePaymentCharges());
				tmp.setLatePaymentInDays(iter.getLatePaymentDays());
				tmp.setMaxDelayDays(iter.getMaxDelayDays());
				tmp.setNachActivation(iter.isNachActivation());
				tmp.setOutstandingAmount(iter.getOutstandingAmount());
				tmp.setRateOfInterest(iter.getRateOfInterest());
				tmp.setSanctionedAmount(iter.getSanctionedAmount());
				response.add(tmp);
			}
			return response;
		}
		
		return null;
	}
	
	public boolean deletByAppId(String appId){
		
		try{
		Query query = entityManager.createNamedQuery("deletePersonalDetailsCalculationsById");
		query.setParameter("CalappId",appId);
		query.executeUpdate();
		return true;
		}
		 catch (Exception e){
	        return false;
	    }
		
	}
	
	
	
	
	
//	public Analysis findApplication(String applicationID) {
//		Query query = entityManager.createNamedQuery("findApplications");
//		query.setParameter("applicationID", applicationID);
//		List<Analysis> analysis = query.getResultList();
//		if (analysis != null && analysis.size() > 0) {
//			return analysis.get(0);
//		} else {
//			
//			return null;
//		}
//	}

	/**
	 * 
	 * @param statusId
	 * @return
	 */
	public LoanStatusMaster findStatusById(String statusId) {
		LoanStatusMaster loanStatusMaster=entityManager.find(LoanStatusMaster.class, statusId);
		return loanStatusMaster;
	}

	/*public void saveCalculationsByAppId(String loanId, List<PersonalDetailsCalculationBean> loanCycle) {
		
		
		
		
	}*/

	

	
}
