package com.lk.credittemplate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lk.credittemplate.model.MasterExcel;
import com.lk.credittemplate.model.NewExecSumm;


@Repository
public class NewExecSummDao extends BaseDao{
	
	@PersistenceContext
    private EntityManager entityManager;
	
	private static final Logger logger = LoggerFactory.getLogger(NewExecSummDao.class);
	
	public List<NewExecSumm> getDataByAppIdAndTable(MasterExcel masterExcel, String table) {
		
		Query query = entityManager.createNamedQuery("getDataByAppIdAndTable");
		query.setParameter("mid",masterExcel);
		query.setParameter("table", table);
		List<NewExecSumm> dataList = (List<NewExecSumm>) query.getResultList();
		return dataList;
		
	}
	
	@Transactional
	public void deleteByMID(MasterExcel masterExcel) {
		logger.error("NewExecSummDao deleteByMID START.... ");
		try {
			Query query = entityManager.createQuery("DELETE FROM  NewExecSumm e WHERE e.masterExcel = :p");
			query.setParameter("p", masterExcel).executeUpdate();
		} catch (Exception e) {
			logger.error("NewExecSummDao deleteByMID Error "+e.getMessage(),e);
			e.printStackTrace();
		}
		logger.error("NewExecSummDao deleteByMID END... ");
	}

	public NewExecSumm findByKeyAndAppId(String key, MasterExcel masterExcel) {
		
		Query query = entityManager.createNamedQuery("getDataByAppIdAndKey");
		query.setParameter("key",key);
		query.setParameter("mid", masterExcel);
		return (NewExecSumm)query.getSingleResult();
	}


}
