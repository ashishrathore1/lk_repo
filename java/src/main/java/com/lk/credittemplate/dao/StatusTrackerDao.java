package com.lk.credittemplate.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lk.credittemplate.model.StatusTracker;


@Repository
public class StatusTrackerDao extends BaseDao {

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

	@Transactional
	public List<StatusTracker> findStatusByStatusId(Boolean flag) {
		// TODO Auto-generated method stub
		List<StatusTracker> statusList = new ArrayList<StatusTracker>();
		Query query = entityManager.createNamedQuery("findStatusByStatusId");
		query.setParameter("flag", flag);
		statusList = query.getResultList();
		return statusList;
	}

	public StatusTracker getStatusTrackerByAppId(String applicationId) {
		StatusTracker statusTracker = null;
		List<StatusTracker> statusList = new ArrayList<StatusTracker>();
		Query query = entityManager.createNamedQuery("getStatusTrackerByAppId");
		query.setParameter("applicationId", applicationId);
		statusList = query.getResultList();
		if (statusList != null && statusList.size() > 0) {
			statusTracker = statusList.get(0);
		}

		return statusTracker;
	}
	

	}
	
	


