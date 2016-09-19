package com.lk.credittemplate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lk.credittemplate.model.MasterExcel;
import com.lk.credittemplate.model.Variables;

@Repository
public class VaraiblesDao extends BaseDao {

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

	public List<Variables> findByMIdVar(MasterExcel masterExcel) {
		TypedQuery<Variables> query = entityManager
				.createQuery("SELECT c FROM " + "Variables AS c WHERE c.masterExcel = :name", Variables.class);
		query.setParameter("name", masterExcel);
		return query.getResultList();

	}
	@Transactional
	public void deleteByMID(MasterExcel masterExcel) {
		Query query = entityManager.createQuery("DELETE FROM Variables e WHERE e.masterExcel = :p");
		query.setParameter("p", masterExcel).executeUpdate();
	}

	public Variables getByKey(MasterExcel masterExcel, String key) {
		Query query = entityManager.createNamedQuery("getByKey");
		query.setParameter("mex",masterExcel);
		query.setParameter("key", key);
		return (Variables)query.getSingleResult();
	}

	
}
