/**
 * 
 */
package com.lk.credittemplate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lk.credittemplate.model.ExcelTableCoord;

/**
 * @author shivendra
 *
 */
@Repository
public class ExcelTableCoordDao extends BaseDao {

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

	public List<ExcelTableCoord> findSimpleTableCoordByVID(String vid) {

		TypedQuery<ExcelTableCoord> query = entityManager
				.createQuery("SELECT c FROM " + "ExcelTableCoord AS c WHERE c.vid = :vid", ExcelTableCoord.class);
		query.setParameter("vid", vid);
		return query.getResultList();

	}
	
	public String findMaxVid()
	{
		String vid;
		vid= (String) entityManager
				.createQuery("SELECT MAX(c.vid) FROM ExcelTableCoord c ").getSingleResult();
		return vid;
		//query.setParameter("vid", vid);
		//return query.getMaxResults().uniqueResult();
	}

}
