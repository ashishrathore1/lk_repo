package com.lk.credittemplate.controller;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lk.credittemplate.beans.ApplicantBean;
import com.lk.credittemplate.beans.CibilLoanSummaryBean;
import com.lk.credittemplate.beans.CibilResponeBean;
import com.lk.credittemplate.beans.CibilResponseData;
import com.lk.credittemplate.beans.CibilSummaryBean;
import com.lk.credittemplate.beans.CibilTOLBean;
import com.lk.credittemplate.beans.EnquiryDataBean;
import com.lk.credittemplate.beans.JsonResponse;
import com.lk.credittemplate.beans.LoanEnquiriesBean;
import com.lk.credittemplate.beans.NonCibilBean;
import com.lk.credittemplate.beans.NonCibilDataBean;
import com.lk.credittemplate.cibilmodel.LKCibilResponse;
import com.lk.credittemplate.cibilmodel.LoanMaster;
import com.lk.credittemplate.dao.LoanEnquiriesDao;
import com.lk.credittemplate.model.Applicant;
import com.lk.credittemplate.model.Cibil;
import com.lk.credittemplate.model.CibilLoanSummary;
import com.lk.credittemplate.model.LoanEnquiries;
import com.lk.credittemplate.model.MasterExcel;
import com.lk.credittemplate.model.NonCibil;
import com.lk.credittemplate.service.ApprovalService;
import com.lk.credittemplate.service.CibilService;
import com.lk.credittemplate.service.DataService;
import com.lk.credittemplate.service.ExcelService;


@Controller
public class DataController {

	@Autowired
	ExcelService excelService;
	
	@Autowired
	ApprovalService approvalService;
	
	@Autowired
	DataService dataService;
	
	@Autowired
	CibilService cibilService;
	
	@Autowired
	LoanEnquiriesDao loanEnquiriesDao;

	private static final Logger logger = LoggerFactory.getLogger(DataController.class);
	

	@RequestMapping(value = "/cibilData/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getCibilData(@PathVariable("id") String appId) {

		//appId = loanId here
		String json = "";
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		try {
			
			List<Applicant> applicantList = dataService.getApplicantListByLoanId(appId);
			
			List<CibilResponseData> cibilResponseDataList = new ArrayList<CibilResponseData>();
			
			List<String> jointLoans = new ArrayList<String>();
			
			if (applicantList != null && applicantList.size()!=0) {
			
				List<String> jointLoansIndiv = new ArrayList<String>();
				
			for(Applicant applicant: applicantList){
			
			int	overdueLoanCount = 0;	
			int outstandingLoanCount = 0;
			int settledLoanCount = 0;
			int writtenOffLoanCount = 0;
			long sancSum = 0;
			long outstandingSum = 0;
			long amtOverdueSum = 0;
			int delaymcountSum = 0;
			
			List<Integer> maxLateDaysList = new ArrayList<>();
			List<Integer> lastDelayList = new ArrayList<>();
			
			List<Cibil> cibilDataList = dataService.getCibilAnalysisDataByApplicantId(applicant.getId());
			
			CibilResponseData cibilResponseData = new CibilResponseData();
			
			ApplicantBean applicantBean = new ApplicantBean();
			
			applicantBean.setId(applicant.getId());
			applicantBean.setAge(applicant.getAge());
			applicantBean.setCibilScore(applicant.getCibilscore());
			applicantBean.setDob(applicant.getDob());
			applicantBean.setName(applicant.getName());
			applicantBean.setPerScore(applicant.getPersonalscore());
			
			cibilResponseData.setApplicantDetails(applicantBean);

			List<LoanEnquiries> loanEnquiriesList = loanEnquiriesDao.getLoanEnquiries(applicant.getId());
			
			int  enquiries[] = {0,0,0,0};
			if(loanEnquiriesList != null && loanEnquiriesList.size()!=0){
				enquiries = cibilService.getLoanEnquiriesCount(loanEnquiriesList,applicant.getLastEnquiry());
			}
			
			EnquiryDataBean enquiryDataBean = new EnquiryDataBean();
			
			enquiryDataBean.setSixMonthlyEnquiry(enquiries[2]);
			enquiryDataBean.setThreeMonthlyEnquiry(enquiries[1]);
			enquiryDataBean.setTotalLoanInquiries(enquiries[0]);
			enquiryDataBean.setYearlyEnquiry(enquiries[3]);
			
			cibilResponseData.setEnquiryData(enquiryDataBean);
			
			//if (cibilDataList != null && cibilDataList.size()!=0) {
			
				List<CibilResponeBean> resultList = new ArrayList<CibilResponeBean>();
				
				for(Cibil cibil : cibilDataList){
					
					boolean addCibil = true;
					
					
					if(cibil.getOwnershipType().equals("Joint")){
						
						String jointLoanString = cibil.getSanctionedamt()+cibil.getOutstanding()+cibil.getTypeofloan()+
								cibil.getStatus();
						
									if(jointLoans.contains(jointLoanString)){
										addCibil = false;
									}
									else{
										jointLoansIndiv.add(jointLoanString);
									}
					}
					else if(cibil.getOwnershipType().equals("Guarantor")){
					addCibil = dataService.filterCibilByGuarantor(cibil);
					}
					
					if(addCibil){
						
						CibilResponeBean cibilResponseBean = new CibilResponeBean();
						
						cibilResponseBean.setCibilId(cibil.getId());
						cibilResponseBean.setNewRecord(false);
						cibilResponseBean.setType(cibil.getTypeofloan().toUpperCase());
						cibilResponseBean.setSancAmt(cibil.getSanctionedamt());
						cibilResponseBean.setOutStnding(cibil.getOutstanding());
						
						if(cibil.getOutstanding() > 0)
							outstandingLoanCount++;
							
						cibilResponseBean.setAmtOverdue(cibil.getAmtoverdue());
						cibilResponseBean.setCategory(cibil.getCategory());
						cibilResponseBean.setCurnonCur(cibil.getCurrentnoncur());
						cibilResponseBean.setDelayMonthsCount(cibil.getDelaymcount());
						if(cibil.getMsincelastdelay() == 999)
							cibilResponseBean.setLastDelay("NA");
						else
							cibilResponseBean.setLastDelay(cibil.getMsincelastdelay()+"");
						
						cibilResponseBean.setRate(cibil.getInterestrate());
						cibilResponseBean.setStatus(cibil.getStatus());
						
						if(cibil.getStatus().equals("Overdue"))
							overdueLoanCount++;
						else if(cibil.getStatus().equals("Written-off"))
							writtenOffLoanCount++;
						else if(cibil.getStatus().equals("Settled"))	
							settledLoanCount++;
						
						cibilResponseBean.setTenure(cibil.getTenure());
						cibilResponseBean.setMaxLateDays(cibil.getMaxlateDays());
						if(cibil.getOpeneddate() != null)
						cibilResponseBean.setDate(df.format(cibil.getOpeneddate()));
						else
							cibilResponseBean.setDate("");
						
						maxLateDaysList.add(cibil.getMaxlateDays());
						lastDelayList.add(cibil.getMsincelastdelay());
						
						 sancSum += cibil.getSanctionedamt();
						 outstandingSum += cibil.getOutstanding();
						 amtOverdueSum += cibil.getAmtoverdue();
						 delaymcountSum += cibil.getDelaymcount();
						 
						 resultList.add(cibilResponseBean);
						}
					else{
						System.out.println(applicant.getName()+"-----Not included---"+cibil.getTypeofloan()+"---"+cibil.getOwnershipType()+"---"+cibil.getSanctionedamt()+"---"+cibil.getOutstanding()+"--"+cibil.getStatus());
						}
					}
				
				cibilResponseData.setCibilDataList(resultList);
				
				CibilSummaryBean loanSummaryBean = new CibilSummaryBean();
				
				loanSummaryBean.setAmtOverdueTotal(amtOverdueSum);
				loanSummaryBean.setDelaymcountTotal(delaymcountSum);
				if(maxLateDaysList.size()>0)
				{
				loanSummaryBean.setMaxLateDays(Collections.max(maxLateDaysList));
				}
				if(lastDelayList.size()>0)
				{
				loanSummaryBean.setMinlastDelay(Collections.min(lastDelayList));
				}
				loanSummaryBean.setOutstandingTotal(outstandingSum);
				loanSummaryBean.setSanctionedAmtTotal(sancSum);
				loanSummaryBean.setOutstandingLoanCount(outstandingLoanCount);
				loanSummaryBean.setOverdueLoanCount(overdueLoanCount);
				loanSummaryBean.setSettledLoanCount(settledLoanCount);
				loanSummaryBean.setWrittenOffLoanCount(writtenOffLoanCount);
				
				cibilResponseData.setLoanSummaryBean(loanSummaryBean);
				
			jointLoans.addAll(jointLoansIndiv);	
			cibilResponseDataList.add(cibilResponseData);
		}//for
			
		json = gson.toJson(cibilResponseDataList);
	}//if
			else{
				json = gson.toJson("No data present");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Failed",e);
		}
		return json;
	}
	
	@RequestMapping(value = "/gettypeOfLoanData/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String typeOfLoanData(@PathVariable("id") String appId) {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
			long totalOutstanding = 0;
			long totalSanctAmt = 0;
			double totalWeightedAmtA = 0.0;
			double totalWeightedAmtB = 0.0;
			
			List<CibilLoanSummary> cibilLoanSummaryList = CibilService.createTolList(); 
			
			List<CibilTOLBean> cibilTOLBeanList = new ArrayList<CibilTOLBean>();
			
			CibilLoanSummaryBean cibilLoanSummaryBean = new CibilLoanSummaryBean();
			
			for(CibilLoanSummary cibilLoanSummary :cibilLoanSummaryList){
				
				CibilTOLBean cibilTOLBean = new CibilTOLBean();
				
				List<Cibil> cibilList  = dataService.getCibilDataByType(cibilLoanSummary.getType(),appId);
				List<NonCibil> nonCibilList = dataService.getNonCibilDataByType(cibilLoanSummary.getType(),appId);
				
				long totalTypeOutstanding = 0;
				long totalTypeSancAmt = 0;
				double weightedAmtA = 0.0;
				double weightedAmtB = 0.0;
				
				for(Cibil cibil : cibilList){
				
					totalTypeOutstanding += cibil.getOutstanding();
					totalTypeSancAmt += cibil.getSanctionedamt();
					weightedAmtA += cibilService.calculateWeightage(cibil.getTypeofloan(),cibil.getStatus(),cibil.getSanctionedamt(),cibil.getOutstanding(),cibilLoanSummary.getMultiplier(),cibilLoanSummary.getWeightageA());
					weightedAmtB += cibilService.calculateWeightage(cibil.getTypeofloan(),cibil.getStatus(),cibil.getSanctionedamt(),cibil.getOutstanding(),cibilLoanSummary.getMultiplier(),cibilLoanSummary.getWeightedB());
					
				}
				
				for(NonCibil nonCibil : nonCibilList){
					
					totalTypeOutstanding += nonCibil.getOutstanding();
					totalTypeSancAmt += nonCibil.getSanctionedamt();
					weightedAmtA += cibilService.calculateWeightage(nonCibil.getTypeofloan(),"Sanctioned",nonCibil.getSanctionedamt(),nonCibil.getOutstanding(),cibilLoanSummary.getMultiplier(),cibilLoanSummary.getWeightageA());
					weightedAmtB += cibilService.calculateWeightage(nonCibil.getTypeofloan(),"Sanctioned",nonCibil.getSanctionedamt(),nonCibil.getOutstanding(),cibilLoanSummary.getMultiplier(),cibilLoanSummary.getWeightedB());
				}
				
					
				cibilTOLBean.setOutstanding(totalTypeOutstanding);
				cibilTOLBean.setRemark1(cibilLoanSummary.getWeightageA()+"% "+cibilLoanSummary.getRemarkA());
				cibilTOLBean.setRemark2(cibilLoanSummary.getWeightedB()+"% "+cibilLoanSummary.getRemarkB());
				cibilTOLBean.setSanctionedAmt(totalTypeSancAmt);
				cibilTOLBean.setType(cibilLoanSummary.getType());
				cibilTOLBean.setWeightedAmtA(weightedAmtA);
				cibilTOLBean.setWeightedAmtB(weightedAmtB);
				
				
				cibilTOLBeanList.add(cibilTOLBean);
				totalWeightedAmtA += weightedAmtA;
				totalWeightedAmtB += weightedAmtB;
				totalSanctAmt  +=  totalTypeSancAmt;
				totalOutstanding  +=  totalTypeOutstanding;
				
			}
			
			cibilLoanSummaryBean.setTolList(cibilTOLBeanList);
			cibilLoanSummaryBean.setTotalOutstanding(totalOutstanding);
			cibilLoanSummaryBean.setTotalSancAmt(totalSanctAmt);
			cibilLoanSummaryBean.setTotalWeightedAmtA(totalWeightedAmtA);
			cibilLoanSummaryBean.setTotalWeightedAmtB(totalWeightedAmtB);

			
		return gson.toJson(cibilLoanSummaryBean);
			
	}	
		
	@RequestMapping(value = "/saveCibil/{id}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String saveCibil(@RequestBody List<CibilResponeBean> cibilBeanList,
			@PathVariable("id") String loanId) {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		try{
			
		List<Cibil> cibilList = dataService.getCibilAnalysisDataByApplicantId(cibilBeanList.get(0).getApplicantId());
		List<Cibil> newcibilList = new ArrayList<Cibil>();
		
		for(CibilResponeBean cibilResponeBean : cibilBeanList){
		
			List<CibilLoanSummary> cibilLoanSummaryList = CibilService.createTolList();
			
			//String excelType = cibilService.getExcelLoanType(cibilResponeBean.getType(),-1);
			String category = "Error";
			for(CibilLoanSummary cibilLoanSummary : cibilLoanSummaryList){
				
				if(cibilLoanSummary.getType().equalsIgnoreCase(cibilResponeBean.getType())){
					 category = cibilLoanSummary.getCategory();
					 break;
				}
				
			}

			Cibil cibil = new Cibil();
			//cibil.setTypeofloan(excelType);
			
			if(cibilResponeBean.isNewRecord()){
				
				cibil.setCreated(Calendar.getInstance().getTime());
				cibil.setAmtoverdue(cibilResponeBean.getAmtOverdue());	
				cibil.setApplicantId(cibilResponeBean.getApplicantId());
				cibil.setDelaymcount(cibilResponeBean.getDelayMonthsCount());
				cibil.setInterestrate(cibilResponeBean.getRate());
				cibil.setLoanId(loanId);
				cibil.setMaxlateDays(cibilResponeBean.getMaxLateDays());	
				
				if(StringUtils.isNotEmpty(cibilResponeBean.getLastDelay())){
					cibil.setMsincelastdelay(Integer.parseInt(cibilResponeBean.getLastDelay()));	
				}
				else{
					cibil.setMsincelastdelay(999);
				}
				
				cibil.setOpeneddate(df.parse(cibilResponeBean.getDate()));
				cibil.setOriginalLoanType(cibilResponeBean.getType());
				cibil.setOwnershipType("Individual");
				cibil.setSanctionedamt(cibilResponeBean.getSancAmt());
				
			}
			else{
			
				cibil = findCibilObjectById(cibilList,cibilResponeBean.getCibilId());
			
			}
			
			cibil.setCategory(category);
			cibil.setCurrentnoncur(category=="Current Liability"?"Current":"Non-Current");
			cibil.setUpdated(Calendar.getInstance().getTime());
			cibil.setTenure(cibilResponeBean.getTenure());
			cibil.setStatus(cibilResponeBean.getStatus());
			cibil.setTypeofloan(cibilResponeBean.getType());
			cibil.setOutstanding(cibilResponeBean.getOutStnding());
			cibil.setInterestrate(cibilResponeBean.getRate());
			
			newcibilList.add(cibil);
		}
			
		dataService.saveCibilList(newcibilList);
		
		return gson.toJson("Saved");
		}
		catch(Exception e){
			e.printStackTrace();
			return gson.toJson("Couldn't Save Error!");
		}
		
	}

	private Cibil findCibilObjectById(List<Cibil> cibilList, long cibilId) {
		
		Cibil currentObj = null;
		for(Cibil cibil : cibilList){
			
			if(cibil.getId() == cibilId){
				currentObj = cibil;
				break;
			}
		}
		
		return currentObj;
	}

	@RequestMapping(value = "/reCalData/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String reCalculateAndSave(@PathVariable("id") String appId) {
			
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
		try{
			
			LoanMaster loanMaster = dataService.findLoanMasterId(appId);
			MasterExcel masterExcel = excelService.findByMasterExcelId(loanMaster.getApplicationId());
			cibilService.recalculateData(appId,masterExcel);
			
			return gson.toJson("Saved");
		}
		catch(Exception e){
			e.printStackTrace();
			logger.error("Failed",e);
		return	gson.toJson("Couldnt save Error!");
		}
		
	}

	@RequestMapping(value = "/saveNonCibil", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String saveNonCibil(@RequestBody List<NonCibilBean> nonCibilBeanList) {
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		NonCibil nonCibil = null;
		int count =0;
		try{
		List<NonCibil> nonCibilList = dataService.fetchNonCibilByLoanId(nonCibilBeanList.get(0).getLoanId());
		List<NonCibil> newNonCibilList = new ArrayList<NonCibil>();
		
		for(NonCibilBean nonCibilBean : nonCibilBeanList){
		
			if(count >= nonCibilList.size()){
				 nonCibil = new NonCibil();
				 nonCibil.setCreatedDate(Calendar.getInstance().getTime());
				 nonCibil.setUpdateddate(Calendar.getInstance().getTime());
			}
			else{
				nonCibil = nonCibilList.get(count++);
			}
			
			
			List<CibilLoanSummary> cibilLoanSummaryList = CibilService.createTolList();
			
			String category = "Error";
			for(CibilLoanSummary cibilLoanSummary : cibilLoanSummaryList){
				if(cibilLoanSummary.getType().equalsIgnoreCase(nonCibilBean.getTypeofloan())){
					 category = cibilLoanSummary.getCategory();
				}
				
			}
		
		nonCibil.setCategory(category);
		nonCibil.setCurrentnoncur(category=="Current Liability"?"Current":"Non-Current");
		nonCibil.setDuration(nonCibilBean.getDuration());
		nonCibil.setEmi(nonCibilBean.getEmi());
		nonCibil.setLoanId(nonCibilBean.getLoanId());
		nonCibil.setOutstanding(nonCibilBean.getOutstanding());
		nonCibil.setParticulars(nonCibilBean.getParticulars());
		nonCibil.setSanctionedamt(nonCibilBean.getSanctionedamt());
		nonCibil.setTypeofloan(nonCibilBean.getTypeofloan());
		nonCibil.setOpeneddate(df.parse(nonCibilBean.getOpeneddate()));
		
		newNonCibilList.add(nonCibil);
		}	
		
		dataService.saveNonCibilData(newNonCibilList);
		
		return gson.toJson("Saved");
		}
		catch(Exception e){
			e.printStackTrace();
			logger.error("Failed",e);
			return gson.toJson("Couldn't Save Error!");
		}
		
	}
	
	@RequestMapping(value = "/getNonCibilData/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getNonCibilData(@PathVariable("id") String loanId) {
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
			long totalsanctionedamt = 0;
			long totalOutstanding = 0;
			
			NonCibilDataBean nonCibilDataBean = new NonCibilDataBean();

			List<NonCibil> nonCibilList = dataService.fetchNonCibilByLoanId(loanId);
			List<NonCibilBean> nonCibilBeanList = new ArrayList<NonCibilBean>();
			
			for(NonCibil nonCibil : nonCibilList){
			
			NonCibilBean nonCibilBean = new NonCibilBean();
			
			nonCibilBean.setTypeofloan(nonCibil.getTypeofloan());
			nonCibilBean.setCategory(nonCibil.getCategory());
			nonCibilBean.setCurrentnoncur(nonCibil.getCurrentnoncur());
			nonCibilBean.setDuration(nonCibil.getDuration());
			nonCibilBean.setEmi(nonCibil.getEmi());
			nonCibilBean.setOpeneddate(df.format(nonCibil.getOpeneddate()));
			nonCibilBean.setOutstanding(nonCibil.getOutstanding());
			totalOutstanding += nonCibil.getOutstanding();
			nonCibilBean.setParticulars(nonCibil.getParticulars());
			nonCibilBean.setSanctionedamt(nonCibil.getSanctionedamt());
			totalsanctionedamt += nonCibil.getSanctionedamt();
			
			nonCibilBeanList.add(nonCibilBean);
			 
		}
		
		nonCibilDataBean.setNonCibilBeanList(nonCibilBeanList);
		nonCibilDataBean.setTotaloutstandingAmt(totalOutstanding);
		nonCibilDataBean.setTotalSancAmt(totalsanctionedamt);
		
		
		return gson.toJson(nonCibilDataBean);
		
	}
	
	@RequestMapping(value = "/getLoanTypes", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getLoanTypeList() {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List<CibilLoanSummary> cibilLoanSummaryList = CibilService.createTolList();
		List<String> typeofLoanList = new ArrayList<String>();
		
		for(CibilLoanSummary cibilLoanSummary : cibilLoanSummaryList){
			
			typeofLoanList.add(cibilLoanSummary.getType().toUpperCase());
		}
		return gson.toJson(typeofLoanList);
		
	}
	
	//create loan enq get call
	@RequestMapping(value = "/getLoanEnq/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getLoanEnquiries(@PathVariable("id") String loanId) {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		List<Applicant> applicantList = dataService.getApplicantListByLoanId(loanId);
		
		int enquiries[] = cibilService.getTotalLoanEnquiriesCount(applicantList);
		
		LoanEnquiriesBean loanEnquiriesBean = new LoanEnquiriesBean();
		
		loanEnquiriesBean.setTotalLoanEnquiries(enquiries[0]);
		loanEnquiriesBean.setThreeMonthlyEnquiries(enquiries[1]);
		loanEnquiriesBean.setSixMonthlyEnquiry(enquiries[2]);
		loanEnquiriesBean.setYearlyEnquiry(enquiries[3]);
		
		return gson.toJson(loanEnquiriesBean);
		
	}
	
	@RequestMapping(value ="/extractCibil/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String extractCibilData(@PathVariable("id") String appId) {
			
		LoanMaster loanMaster  = dataService.getApplicationByAppId(appId);
		try{
		cibilService.saveCibilData(appId,loanMaster.getLoanMastertId());
		return "Done";
		}
		catch(Exception e){
			return e.getMessage();
		}
		
	}
	
	@RequestMapping(value ="/getStatus", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String findStatusData() {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		LKCibilResponse lKCibilResponse = dataService.getCibilData("LAI-100025715","2422f876-3e14-11e6-b859-028a35a1fc65");
		
				JsonResponse cibilResponseData = gson.fromJson(lKCibilResponse.getResponseJsonData(),
						JsonResponse.class);
				
				List<Applicant> applicantList = dataService.getApplicantListByLoanId("2f1b9d51-3e13-11e6-b859-028a35a1fc65");

				System.out.println(applicantList.get(1).getName());
				
				System.out.println(cibilService.getmonthsSinceLastDelay(cibilResponseData.getAccountSegments().get(0).getDateofLastPayment(),null,applicantList.get(1).getLastEnquiry()));

		return "Done";
	}
	
}
	

