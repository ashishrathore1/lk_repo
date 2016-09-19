package com.lk.credittemplate.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BaseDao {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 
	 * @param entities
	 */
	@Transactional
	public <T> void batchSave(List<T> entities) {

		for (T t : entities) {
			entityManager.persist(t);
		}

	}

	/**
	 * 
	 * @param entities
	 * @return
	 */
	@Transactional
	public <T> List<T> batchSaveUpdate(List<T> entities) {
		List<T> returnList = new ArrayList<T>();

		for (T t : entities) {
			t = entityManager.merge(t);
			// flush();
			returnList.add(t);
		}

		return returnList;
	}

	/**
	 * 
	 * @param entities
	 */
	@Transactional
	public <T> void batchSaveSet(Set<T> entities) {

		for (T t : entities) {
			entityManager.persist(t);
			// flush();
		}

	}

	@Transactional
	public <T> Set<T> batchSaveUpdateSet(Set<T> entities) {
		Set<T> returnList = new HashSet<T>();

		for (T t : entities) {
			t = entityManager.merge(t);
			// flush();
			returnList.add(t);
		}

		return returnList;
	}

	/**
	 * 
	 * @param t
	 */
	@Transactional
	public <T> void persist(T t) {
		entityManager.persist(t);
		// flush();

	}

	/**
	 * 
	 * @param t
	 */
	@Transactional
	public <T> T merge(T t) {
		t = entityManager.merge(t);
		// flush();
		return t;

	}

	@Transactional
	public void flush() {
		entityManager.flush();
	}

	@Transactional
	public void clear() {
		entityManager.clear();
	}

	@Transactional
	public <T> void batchSaveExcel(List<T> entities) {
		
		for (T t : entities) {

			try {
				entityManager.merge(t);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
