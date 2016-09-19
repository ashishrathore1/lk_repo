package com.lk.credittemplate.cibildao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lk.credittemplate.cibilmodel.DirectorMaster;
import com.lk.credittemplate.dao.BaseDao;
import com.lk.credittemplate.model.PersonalDetailsApplicantDetails;


@Repository
public class DirectorMasterDao extends BaseDao {

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

	public DirectorMaster findByDirectorId(String dirId){
		return entityManager.find(DirectorMaster.class,dirId);
	}
	
	public List<DirectorMaster> findDirectorMasterDataByAppID(String appId) {
		Query query = entityManager.createQuery(
				"SELECT d FROM " + "DirectorMaster AS d WHERE d.loanMaster.applicationId = ?1", DirectorMaster.class);
		query.setParameter(1, appId);
		if (query != null) {
			return query.getResultList();
		} else {
			return null;
		}
	}
	public List<DirectorMaster> selectDirectorMasterDataByAppID(String appId) {
		Query query = entityManager.createQuery(
				"SELECT d FROM " + "DirectorMaster AS d WHERE d.loanMaster.applicationId = ?1", DirectorMaster.class);
		query.setParameter(1, appId);
		if (query != null) {
			return query.getResultList();
		} else {
			return null;
		}
	}
		
		public List<DirectorMaster> getDirectorMasterDataByAppID(String appId) {
			Query query = entityManager.createQuery(
					"SELECT d FROM " + "DirectorMaster AS d WHERE d.loanMaster.loanMastertId = :appId  and isDeleted= :isDeleted", DirectorMaster.class);
			query.setParameter("appId", appId);
			query.setParameter("isDeleted", false);
			if (query != null) {
				return query.getResultList();
			} else {
				return null;
			}
	}
		
		public List<DirectorMaster> findLatestDirectors(String appId  ) {
			Query query = entityManager.createQuery(
					"SELECT d FROM " + "DirectorMaster AS d WHERE d.loanMaster.loanMastertId = :appId order by d.dCreatedDate ", DirectorMaster.class);
			query.setParameter("appId", appId);
			if (query != null) {
				return query.getResultList();
			} else {
				return null;
			}
	}
}
