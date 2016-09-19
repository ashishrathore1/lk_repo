package com.lk.credittemplate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lk.credittemplate.model.Analysis;
import com.lk.credittemplate.model.LoanStatusMaster;
import com.lk.credittemplate.model.PersonalDetailsBusiness;
import com.lk.credittemplate.model.PersonalDetailsInfo;
import com.lk.credittemplate.model.PersonalDetailsMarket;


@Repository
public class PersonalDetailsMarketDao extends BaseDao{

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

	public PersonalDetailsMarket getDataBySellingId(String marketGuid) {
		// TODO Auto-generated method stub
		Query query = entityManager.createNamedQuery("getSingleMarketById");
		query.setParameter("marketGuid",marketGuid);
		List<PersonalDetailsMarket> details = query.getResultList();
		PersonalDetailsMarket market=null;
		
		if(details != null && details.size()>0)
		{
			market=details.get(0);
		}
		
	return market;
	}
	
	public List<PersonalDetailsMarket> getMarketByAppId(String marketId) {
		// TODO Auto-generated method stub
		Query query = entityManager.createNamedQuery("getMarketDetailsById");
		query.setParameter("marketId",marketId);
		PersonalDetailsMarket  personalDetailsInfo=null ;
		List<PersonalDetailsMarket> details = query.getResultList();
		if(details !=null && details.size() >= 0){
			return details;
		}
		else
		{
			return details;
		}
		
		
	}
	
	
	
	
	@Transactional
	public void deleteByID(String marketGuid) {
		Query query = entityManager.createQuery("DELETE FROM PersonalDetailsMarket e WHERE e.marketGuid = :marketGuid");
		query.setParameter("marketGuid", marketGuid).executeUpdate();
	}
}
		

	

	/**
	 * 
	 * @param statusId
	 * @return
	 */

