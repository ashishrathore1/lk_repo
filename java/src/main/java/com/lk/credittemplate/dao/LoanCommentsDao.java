package com.lk.credittemplate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lk.credittemplate.cibilmodel.DirectorMaster;
import com.lk.credittemplate.model.LoanComments;
import com.lk.credittemplate.model.LoanCommentsCibil;


@Repository
public class LoanCommentsDao extends BaseDao{

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

	public List<LoanCommentsCibil> getCibilComments(String appId, DirectorMaster directorMaster) {
		Query query = entityManager.createNamedQuery("showCibilComments");
		query.setParameter("appId",appId );
		query.setParameter("dirId", directorMaster);
		return query.getResultList();
		
	}

	public List<LoanComments> getCatComments(String appId) {
		Query query = entityManager.createNamedQuery("showCatComments");
		query.setParameter("appId", appId);
		return query.getResultList();
	}

}
