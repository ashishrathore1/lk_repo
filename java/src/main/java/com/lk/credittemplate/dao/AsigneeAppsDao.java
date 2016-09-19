package com.lk.credittemplate.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lk.credittemplate.model.AsigneeApps;

@Repository
public class AsigneeAppsDao extends BaseDao {

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

	public TypedQuery<AsigneeApps> findByAppId(String appId) {
		TypedQuery<AsigneeApps> query = entityManager
				.createQuery("SELECT c FROM " + "AsigneeApps AS c WHERE c.loanId = :id )", AsigneeApps.class);
		query.setParameter("id", appId);
		return query;
	}

	
}
