package com.lk.credittemplate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lk.credittemplate.model.UserMaster;

@Repository
public class UserMasterDao extends BaseDao{

	@PersistenceContext
    private EntityManager entityManager;
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	public UserMaster findRowbyEmail(String email) {
		Query query = entityManager.createNamedQuery("getUserUsingEmail");
		query.setParameter("email",email);
		UserMaster  userMaster=null ;
		List<UserMaster> userMasterList = query.getResultList();
		if(userMasterList !=null && userMasterList.size() >= 1){
			userMaster=userMasterList.get(0);
		}
		
		return userMaster;
	}
	
	@Transactional
    public void flush() {
        entityManager.flush();
    }

	@Transactional
    public void clear() {
        entityManager.clear();
    }

	public UserMaster findById(String userId) {

		return entityManager.find(UserMaster.class,userId);
	}

	public List<UserMaster> getByLevels(int curLevel) {
		
		TypedQuery<UserMaster> query = entityManager
				.createQuery("SELECT u FROM " + "UserMaster u join u.roleMaster RoleMaster WHERE RoleMaster.level < :cur and RoleMaster.level > 0"
						+ " Order by u.name asc", UserMaster.class);
		query.setParameter("cur", curLevel);
		return query.getResultList();
	}

	public List<UserMaster> getRoleList(String rName) {
		
		Query q = entityManager.createNamedQuery("getUsersByRole");
		q.setParameter("rName", rName);
		return q.getResultList();
	}

}
