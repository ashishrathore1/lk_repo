package com.lk.credittemplate.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lk.credittemplate.model.MasterExcel;

@Repository
public class MasterExcelDao extends BaseDao {

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

	public TypedQuery<MasterExcel> findByAppId(String appId) {

		TypedQuery<MasterExcel> query = entityManager
				.createQuery("SELECT c FROM MasterExcel " + "AS c WHERE c.appId = :id", MasterExcel.class);
		query.setParameter("id", appId);

		return query;

	}

	public void deleteByAppId(MasterExcel masterExcel) {
		entityManager.remove(masterExcel);
	}

}
