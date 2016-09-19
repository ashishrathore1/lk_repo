/**
 * 
 */
package com.lk.credittemplate.cibildao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lk.credittemplate.cibilmodel.LKCibilResponse;
import com.lk.credittemplate.dao.BaseDao;



/**
 * @author kapil
 *
 */
@Repository
public class CibilResponseDao extends BaseDao {
	
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

	public LKCibilResponse getCibilresponseByAppIdandDirId(String applicationID, String directorId) {
		LKCibilResponse lkCibilResponse=null;
		Query query = entityManager.createNamedQuery("getLKCibilResponse");
		query.setParameter("directorId",directorId);
		query.setParameter("applicationID",applicationID);
		List<LKCibilResponse> lkCibilResponseList = (List<LKCibilResponse>) query.getResultList();
		if(lkCibilResponseList != null && lkCibilResponseList.size() > 0){
			lkCibilResponse=lkCibilResponseList.get(0);
		}
		return lkCibilResponse;
	}
	
	public List<Object[]> getCibilDirectorsByAppId(String appId) {
		Query query = entityManager.createNamedQuery("getLKCibilResponseDirectorsByAppId");
		query.setParameter("applicationID",appId);
		List<Object[]> lkCibilResponseList =  query.getResultList();
		return lkCibilResponseList;
	}
}
