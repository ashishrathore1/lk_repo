/**
 * 
 */
package com.lk.credittemplate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lk.credittemplate.model.ExcelTableData;
import com.lk.credittemplate.model.MasterExcel;


/**
 * @author shivendra
 *
 */
@Repository
public class ExcelTableDataDao extends BaseDao {

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

	public List<ExcelTableData> getByTableNameAndMId(String tablename, MasterExcel masterExcel) {

		TypedQuery<ExcelTableData> query = entityManager.createQuery(
				"SELECT c FROM " + "ExcelTableData AS c WHERE c.tname = :tname AND c.masterExcel = :mid",
				ExcelTableData.class);
		query.setParameter("tname", tablename);
		query.setParameter("mid", masterExcel);
		return query.getResultList();
	}

	@Transactional
	public void deleteByMID(MasterExcel masterExcel) {
		try{
			Query query = entityManager.createQuery("DELETE FROM ExcelTableData e WHERE e.masterExcel = :p");
			query.setParameter("p", masterExcel).executeUpdate();
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}

	public ExcelTableData findByKeyAndTableName(MasterExcel masterExcel,String key, String tableName) {
		 
		Query query = entityManager.createNamedQuery("getByKeyAndTableName");
		
		query.setParameter("mex",masterExcel);
		query.setParameter("tname", tableName);
		query.setParameter("key", key);
		
		return (ExcelTableData)query.getSingleResult();
		
	}

	public ExcelTableData getById(long l) {

		ExcelTableData excelTableData = entityManager.find(ExcelTableData.class, l);
		return excelTableData;
	}

}
