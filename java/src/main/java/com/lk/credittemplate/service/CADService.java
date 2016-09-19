package com.lk.credittemplate.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lk.credittemplate.beans.CAD.RequestDataBean;
import com.lk.credittemplate.cibildao.LoanMasterDao;
import com.lk.credittemplate.cibilmodel.LoanMaster;


@Service
public class CADService {

	@Autowired
	LoanMasterDao loanMasterDao;
	public int getCountByStatusId(String id) {

		Long appCountByStatusId = (Long) loanMasterDao.getAppCountByStatusId(id);
		 return appCountByStatusId.intValue();
	}
	
	public int getCountBySubStatusId(String id) {

		Long appCountByStatusId = (Long)loanMasterDao.getAppCountBySubStatusId(id);
		return appCountByStatusId.intValue();
	}
	
	public int getCountByStatusIdAndAssignee(String id,String name) {

		Long appCountByStatusId = (Long)loanMasterDao.getAppCountByStatusIdAndAssignee(id,name);
		return appCountByStatusId.intValue();

	}
	
	public int getCountBySubStatusIdAndAssignee(String id,String name) {

		Long appCountByStatusId = (Long)loanMasterDao.getAppCountBySubStatusIdAndAssignee(id,name);
		return appCountByStatusId.intValue();
		
	}
	public List<LoanMaster> getApplicationsByStatusIdAndAssignee(String statusId, String assignee, int lowerLimt) {
		
		return loanMasterDao.getAppDataByStatusId(statusId,assignee,lowerLimt);

	}
	public List<LoanMaster> getApplicationsBySubStatus(String statusId, int lowerLimt) {

		return loanMasterDao.getAppDataBySubStatus(statusId,lowerLimt);
	}
	
	public void saveLoanMasterList(List<LoanMaster> loanMasterList) {

		loanMasterDao.batchSaveExcel(loanMasterList);
	}
	
	public List<LoanMaster> searchAppData(String searchkey, String statusId, String assignee,int lowerLimit) {
		
		if(StringUtils.isNotEmpty(assignee))
		return loanMasterDao.findAppsBySearchData(searchkey,statusId,assignee,lowerLimit);
		else
		return loanMasterDao.findAppsBySearchData(searchkey,statusId,lowerLimit);
	}

	
	public List<LoanMaster> getData(RequestDataBean requestDataBean){
		
		boolean search = false;
		Map<String,String> columnMap = new HashMap<String,String>();
		
		if(StringUtils.isNotEmpty(requestDataBean.getStatusId())){
			
			if(requestDataBean.getType().equals("status")){
				columnMap.put("statusId",requestDataBean.getStatusId());
			}
			else{
				columnMap.put("subStatus",requestDataBean.getStatusId());
			}
			
		}
		if(StringUtils.isNotEmpty(requestDataBean.getAssignee())){
			
			columnMap.put("assignedTo",requestDataBean.getAssignee());
		}
		if(StringUtils.isNotEmpty(requestDataBean.getSearchkey())){
			
			search = true;
		}
		
		int lowerLimt = requestDataBean.getPageCount()*50;
		
		return loanMasterDao.getCADashboardApps(columnMap,search,lowerLimt,requestDataBean.getSearchkey());
		
	}
	
}
