package com.lk.credittemplate.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lk.credittemplate.beans.CAD.DataBean;
import com.lk.credittemplate.beans.CAD.RequestDataBean;
import com.lk.credittemplate.beans.CAD.SearchDataBean;
import com.lk.credittemplate.beans.CAD.StatusAndCountBean;
import com.lk.credittemplate.cibilmodel.LoanMaster;
import com.lk.credittemplate.service.CADService;
import com.lk.credittemplate.service.DataService;

@Controller
public class CADashboardController {
	
	@Autowired
	CADService cADService;
	
	@Autowired
	DataService dataService;
	
	@RequestMapping(value = "/CAD/getAppData", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String getAppData(@RequestBody RequestDataBean requestDataBean ,HttpServletRequest request) {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		//List<LoanMaster> loanMasterList = new ArrayList<LoanMaster>();
		
		/*if(StringUtils.isEmpty(requestDataBean.getAssignee())){ 
		   
			if(requestDataBean.getType().equals("status")){
			//loanMasterList = cADService.getApplicationsByStatusId(requestDataBean.getStatusId(),lowerLimt);
			}
			else{
				loanMasterList = cADService.getApplicationsBySubStatus(requestDataBean.getStatusId(),lowerLimt);	
			}
		}
		else{
			if(requestDataBean.getType().equals("status")){	
		   loanMasterList = cADService.getApplicationsByStatusIdAndAssignee(requestDataBean.getStatusId(),requestDataBean.getAssignee(),lowerLimt);
			}
			else{
				//loanMasterList = cADService.getApplicationsBy(requestDataBean.getStatusId(),requestDataBean.getAssignee(),lowerLimt);		
			}
		}*/
		
		/*for(LoanMaster loanMaster : loanMasterList){
			
			
		}*/
		//getResponseData(loanMasterList)
		 return gson.toJson(cADService.getData(requestDataBean));
	}
	
	@RequestMapping(value = "/CAD/getStatusAndCount/{name}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getIndivStatusAndCount(@PathVariable("name") String assignee) {
		
		List<StatusAndCountBean> statusAndCountList = new ArrayList<StatusAndCountBean>(); 
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		StatusAndCountBean statusAndCount = new StatusAndCountBean();
		statusAndCount.setStatus("Data Extraction Completed");
		statusAndCount.setType("status");
		statusAndCount.setId("1f974f0c-62b3-11e5-988d-d6b2fad01010");
		if(assignee.equals("CM"))
		statusAndCount.setCount(cADService.getCountByStatusId("1f974f0c-62b3-11e5-988d-d6b2fad01010"));
		else
		statusAndCount.setCount(cADService.getCountByStatusIdAndAssignee("1f974f0c-62b3-11e5-988d-d6b2fad01010",assignee));	
		statusAndCountList.add(statusAndCount);

		statusAndCount = new StatusAndCountBean();
		statusAndCount.setStatus("Assigned for Credit Analysis");
		statusAndCount.setType("status");
		statusAndCount.setId("1f974f0c-62b3-11e5-988d-d6b2fad01011");
		if(assignee.equals("CM"))
		statusAndCount.setCount(cADService.getCountByStatusId("1f974f0c-62b3-11e5-988d-d6b2fad01011"));
		else
		statusAndCount.setCount(cADService.getCountByStatusIdAndAssignee("1f974f0c-62b3-11e5-988d-d6b2fad01011",assignee));	
		statusAndCountList.add(statusAndCount);
		
		statusAndCount = new StatusAndCountBean();
		statusAndCount.setStatus("CAT In Progress");
		statusAndCount.setType("subStatus");
		statusAndCount.setId("1f974f0c-62b3-11e5-988d-d6b2fad01013");
		if(assignee.equals("CM"))
		statusAndCount.setCount(cADService.getCountBySubStatusId("1f974f0c-62b3-11e5-988d-d6b2fad01013"));
		else
		statusAndCount.setCount(cADService.getCountBySubStatusIdAndAssignee("1f974f0c-62b3-11e5-988d-d6b2fad01013",assignee));	
		statusAndCountList.add(statusAndCount);
		
		statusAndCount = new StatusAndCountBean();
		statusAndCount.setStatus("PD In Progress");
		statusAndCount.setType("subStatus");
		statusAndCount.setId("1f974f0c-62b3-11e5-988d-d6b2fad01014");
		if(assignee.equals("CM"))
		statusAndCount.setCount(cADService.getCountBySubStatusId("1f974f0c-62b3-11e5-988d-d6b2fad01014"));
		else
		statusAndCount.setCount(cADService.getCountBySubStatusIdAndAssignee("1f974f0c-62b3-11e5-988d-d6b2fad01014",assignee));	
		statusAndCountList.add(statusAndCount);
		
		statusAndCount = new StatusAndCountBean();
		statusAndCount.setStatus("Pending L1 Approval");
		statusAndCount.setType("subStatus");
		statusAndCount.setId("1f974f0c-62b3-11e5-988d-d6b2fad01016");
		if(assignee.equals("CM"))
		statusAndCount.setCount(cADService.getCountBySubStatusId("1f974f0c-62b3-11e5-988d-d6b2fad01016"));
		else
		statusAndCount.setCount(cADService.getCountBySubStatusIdAndAssignee("1f974f0c-62b3-11e5-988d-d6b2fad01016",assignee));	
		statusAndCountList.add(statusAndCount);
		
		statusAndCount = new StatusAndCountBean();
		statusAndCount.setStatus("Pending L2 Approval");
		statusAndCount.setType("subStatus");
		statusAndCount.setId("1f974f0c-62b3-11e5-988d-d6b2fad01017");
		if(assignee.equals("CM"))
		statusAndCount.setCount(cADService.getCountBySubStatusId("1f974f0c-62b3-11e5-988d-d6b2fad01017"));
		else
		statusAndCount.setCount(cADService.getCountBySubStatusIdAndAssignee("1f974f0c-62b3-11e5-988d-d6b2fad01017",assignee));
		statusAndCountList.add(statusAndCount);
		
		statusAndCount = new StatusAndCountBean();
		statusAndCount.setStatus("Pending L3 Approval");
		statusAndCount.setType("subStatus");
		statusAndCount.setId("1f974f0c-62b3-11e5-988d-d6b2fad01018");
		if(assignee.equals("CM"))
		statusAndCount.setCount(cADService.getCountBySubStatusId("1f974f0c-62b3-11e5-988d-d6b2fad01018"));
		else
		statusAndCount.setCount(cADService.getCountBySubStatusIdAndAssignee("1f974f0c-62b3-11e5-988d-d6b2fad01018",assignee));	
		statusAndCountList.add(statusAndCount);
		
		return gson.toJson(statusAndCountList);
		
	}
	
	@RequestMapping(value = "/CAD/saveData", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String saveAppData(@RequestBody List<DataBean> cADdataBeanList) {
		
		List<LoanMaster> loanMasterList = new ArrayList<LoanMaster>();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try{
		
		  for(DataBean cADdataBean : cADdataBeanList){
		
			  Integer i = 0;
			if(StringUtils.isNotEmpty(cADdataBean.getTenLoanAmt())){
				LoanMaster loanMaster = dataService.getApplicationByGuid(cADdataBean.getLoanId());
				loanMaster.setTenloanAmnt(Double.parseDouble(cADdataBean.getTenLoanAmt()));
				loanMasterList.add(loanMaster);
			}
			
		 }
		  
		  if(loanMasterList.size()>0){
			  cADService.saveLoanMasterList(loanMasterList);
			  }
			  return gson.toJson("Saved");
			}
			catch(Exception e){
				e.printStackTrace();
				return gson.toJson("Couldn't Saved");
			
			}
		
		
	}
	
	@RequestMapping(value = "/CAD/searchAppData", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String searchAppData(@RequestBody SearchDataBean searchDataBean) {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try{
		  
		  int lowerLimt = searchDataBean.getPageCount()*50;
		  List<LoanMaster> loanMasterList =  cADService.searchAppData(searchDataBean.getSearchkey(),searchDataBean.getStatusId(),searchDataBean.getAssignee(),lowerLimt);
		  
		  return gson.toJson(getResponseData(loanMasterList));
		  
		}
		catch(Exception e){
			e.printStackTrace();
			return gson.toJson("GG");
		}
		
	}
	
	public List<DataBean> getResponseData(List<LoanMaster> loanMasterList){
		
		List<DataBean> dataBeanList = new ArrayList<DataBean>();
		
		for(LoanMaster l : loanMasterList){
			
			DataBean cADdataBean = new DataBean();
			
			cADdataBean.setAppId(l.getApplicationId());
			//cADdataBean.setAssignedDate();
			cADdataBean.setCaName(l.getAssignedTo());
			cADdataBean.setClsAppId(l.getClsApplicationId());
			cADdataBean.setCompany(l.getCompanyName());
			cADdataBean.setContactNo(l.getContactNo());
			cADdataBean.setEmail(l.getEmail());
			cADdataBean.setLeadSrc(l.getLeadSource());
			cADdataBean.setLoanType(l.getLoanType());
			cADdataBean.setStatus(l.getStatusId());
			cADdataBean.setSubStatus(l.getSubStatus());
			if(l.getTenloanAmnt() == null)
			cADdataBean.setTenLoanAmt(cADdataBean.getTenLoanAmt());
			else
			cADdataBean.setTenLoanAmt("");	
			cADdataBean.setTurnOver(l.getTurnOver());
			
			dataBeanList.add(cADdataBean);
		}
		
		return dataBeanList;
	}
	
}