package com.lk.credittemplate.service;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lk.credittemplate.beans.CibilTOLBean;
import com.lk.credittemplate.beans.JsonResponse;
import com.lk.credittemplate.beans.response.ResponseAccountSegment;
import com.lk.credittemplate.beans.response.ResponseEnquirySegment;
import com.lk.credittemplate.cibilmodel.LKCibilResponse;
import com.lk.credittemplate.dao.ExcelTableDataDao;
import com.lk.credittemplate.dao.LoanEnquiriesDao;
import com.lk.credittemplate.model.Applicant;
import com.lk.credittemplate.model.Cibil;
import com.lk.credittemplate.model.CibilLoanSummary;
import com.lk.credittemplate.model.ExcelTableData;
import com.lk.credittemplate.model.LoanEnquiries;
import com.lk.credittemplate.model.MasterExcel;
import com.lk.credittemplate.model.NewExecSumm;
import com.lk.credittemplate.model.NonCibil;
import com.lk.credittemplate.model.Variables;

@Service
@Transactional
public class CibilService {

	@Autowired
	ExcelService excelService;
	
	@Autowired
	ApprovalService approvalService;
	
	@Autowired
	DataService dataService;
	
	@Autowired
	ExcelTableDataDao excelTableDataDao;
	
	@Autowired
	LoanEnquiriesDao loanEnquiriesDao;
	
	static Map<String,String> loanMap = new HashMap<String,String>();
	
	static{
		
		loanMap.put("Auto Loan (Personal)", "VEHICLE LOAN");
		loanMap.put("Two-wheeler Loan","VEHICLE LOAN");
		loanMap.put("Kisan Credit Card","CREDIT CARD");
		loanMap.put("Business Loan – Priority Sector – Agriculture","BUSINESS LOAN-NONCURRENT");
	    loanMap.put("Overdraft","CC/OD");		
	    loanMap.put("Business Loan – Priority Sector – Small Business","BUSINESS LOAN-NONCURRENT");	
	    loanMap.put("Commercial Vehicle Loan","VEHICLE LOAN");		    						
	    loanMap.put("Loan Against Bank Deposits","OTHER");
	    loanMap.put("Loan to Professional","OTHER");
		loanMap.put("Used Car Loan","VEHICLE LOAN");
		loanMap.put("Corporate Credit Card","CREDIT CARD");
		loanMap.put("Loan Against Shares/Securities","OTHER");
		loanMap.put("Construction Equipment Loan","TERM LOAN");
		loanMap.put("Secured Credit Card","CREDIT CARD");
		loanMap.put("Business Loan – Priority Sector – Others","BUSINESS LOAN-NONCURRENT");
		loanMap.put("Microfinance – Business Loan","BUSINESS LOAN-NONCURRENT");
		loanMap.put("Business Non-Funded Credit Facility – General","BUSINESS LOAN-NONCURRENT");
		loanMap.put("Tractor Loan","VEHICLE LOAN");
		loanMap.put("Business Loan Against Bank Deposits","BUSINESS LOAN-CURRENT");
		loanMap.put("Fleet Card","CREDIT CARD");
		loanMap.put("Business Non-Funded Credit Facility – Priority Sector – Small Busines","BUSINESS LOAN-NONCURRENT");
		loanMap.put("Non-Funded Credit Facility","BANK GUARANTEE");		
	}
	
	public String getExcelLoanType(String accountType, int repaymentTenure) {
		
		String type = accountType;
		if(accountType.equalsIgnoreCase("Business Loan – Priority") || accountType.equalsIgnoreCase("Business Loan – General")){
			
			if(repaymentTenure < 12){
				type =  "BUSINESS LOAN-CURRENT";
			}
			else{
				type =  "BUSINESS LOAN-NONCURRENT";
			}
		}
		
		else {
			
			type  = loanMap.get(accountType);
		}
		
		return type;
		
	}
	
	public void saveTOLvariables(String appId, MasterExcel masterExcel){
		
		List<Variables> variablesList = new ArrayList<Variables>();
		
		String pattern = "####.##";
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		
		int index = 0;
		
		List<CibilLoanSummary> cibilLoanSummaryList = createTolList(); 
		
		List<CibilTOLBean> cibilTOLBeanList = new ArrayList<CibilTOLBean>();
		
		long totalOutstanding = 0;
		long totalSancAmt = 0;
		double totalweightedAmtA = 0.0;
		double totalweightedAmtB = 0.0;
		
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
				weightedAmtA += calculateWeightage(cibil.getTypeofloan(),cibil.getStatus(),cibil.getSanctionedamt(),cibil.getOutstanding(),cibilLoanSummary.getMultiplier(),cibilLoanSummary.getWeightageA());
				weightedAmtB += calculateWeightage(cibil.getTypeofloan(),cibil.getStatus(),cibil.getSanctionedamt(),cibil.getOutstanding(),cibilLoanSummary.getMultiplier(),cibilLoanSummary.getWeightedB());
				
			}
			
			if (nonCibilList != null && nonCibilList.size()>0) {
				for (NonCibil nonCibil : nonCibilList) {

					totalTypeOutstanding += nonCibil.getOutstanding();
					totalTypeSancAmt += nonCibil.getSanctionedamt();
					weightedAmtA += calculateWeightage(nonCibil.getTypeofloan(), "Sanctioned",
							nonCibil.getSanctionedamt(), nonCibil.getOutstanding(), cibilLoanSummary.getMultiplier(),
							cibilLoanSummary.getWeightageA());
					weightedAmtB += calculateWeightage(nonCibil.getTypeofloan(), "Sanctioned",
							nonCibil.getSanctionedamt(), nonCibil.getOutstanding(), cibilLoanSummary.getMultiplier(),
							cibilLoanSummary.getWeightedB());
				}
			}
			cibilTOLBean.setOutstanding(totalTypeOutstanding);
			cibilTOLBean.setRemark1(cibilLoanSummary.getWeightageA()+"% "+cibilLoanSummary.getRemarkA());
			cibilTOLBean.setRemark2(cibilLoanSummary.getWeightedB()+"% "+cibilLoanSummary.getRemarkB());
			cibilTOLBean.setSanctionedAmt(totalTypeSancAmt);
			cibilTOLBean.setType(cibilLoanSummary.getType());
			cibilTOLBean.setWeightedAmtA(weightedAmtA);
			cibilTOLBean.setWeightedAmtB(weightedAmtB);
			
			totalOutstanding += totalTypeOutstanding;
			totalSancAmt += totalTypeSancAmt;
			totalweightedAmtA += weightedAmtA;
			totalweightedAmtB += weightedAmtB;
			
			cibilTOLBeanList.add(cibilTOLBean);
		}
			
		Variables variables = excelService.findByMasterIdVariables(masterExcel,"TermLoanOutstanding");
        variables.setVal(cibilTOLBeanList.get(index).getOutstanding()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"TermLoanWeighted");
        variables.setVal(cibilTOLBeanList.get(index).getWeightedAmtA()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"TermLoanSanctionAmt");
        variables.setVal(cibilTOLBeanList.get(index++).getSanctionedAmt()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"CurrBusinessLoanWeighted");
        variables.setVal(cibilTOLBeanList.get(index).getWeightedAmtA()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"CurrBusinessLoanSanctionAmt");
        variables.setVal(cibilTOLBeanList.get(index).getSanctionedAmt()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"CurrBusinessLoanOutstanding");
        variables.setVal(cibilTOLBeanList.get(index++).getOutstanding()+"");
        variablesList.add(variables);
        
        
        variables = excelService.findByMasterIdVariables(masterExcel,"NonCurrBusLoanWeightedB");
        variables.setVal(cibilTOLBeanList.get(index).getWeightedAmtB()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"NonCurrBusLoanWeighted");
        variables.setVal(cibilTOLBeanList.get(index).getWeightedAmtA()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"NonCurrBusLoanSanctionAmt");
        variables.setVal(cibilTOLBeanList.get(index).getSanctionedAmt()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"NonCurrBusLoanOutstanding");
        variables.setVal(cibilTOLBeanList.get(index++).getOutstanding()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"LAPWeightedB");
        variables.setVal(cibilTOLBeanList.get(index).getWeightedAmtB()+"");
        variablesList.add(variables);
        variables = excelService.findByMasterIdVariables(masterExcel,"LAPWeighted");
        variables.setVal(cibilTOLBeanList.get(index).getWeightedAmtA()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"LAPSanctionAmt");
        variables.setVal(cibilTOLBeanList.get(index).getSanctionedAmt()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"LAPOutstanding");
        variables.setVal(cibilTOLBeanList.get(index++).getOutstanding()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"EducLoanWeighted");
        variables.setVal(cibilTOLBeanList.get(index).getWeightedAmtA()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"EducLoanSanctionAmt");
        variables.setVal(cibilTOLBeanList.get(index).getSanctionedAmt()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"EducLoanOutstanding");
        variables.setVal(cibilTOLBeanList.get(index++).getOutstanding()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"GoldLoanWeighted");
        variables.setVal(cibilTOLBeanList.get(index).getWeightedAmtA()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"GoldLoanSanctionAmt");
        variables.setVal(cibilTOLBeanList.get(index).getSanctionedAmt()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"GoldLoanOutstanding");
        variables.setVal(cibilTOLBeanList.get(index++).getOutstanding()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"PersonalLoanWeighted");
        variables.setVal(cibilTOLBeanList.get(index).getWeightedAmtA()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"PersonalLoanSanctionAmt");
        variables.setVal(cibilTOLBeanList.get(index).getSanctionedAmt()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"PersonalLoanOutstanding");
        variables.setVal(cibilTOLBeanList.get(index++).getOutstanding()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"VehicleLoanWeighted");
        variables.setVal(cibilTOLBeanList.get(index).getWeightedAmtA()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"VehicleLoanSanctionAmt");
        variables.setVal(cibilTOLBeanList.get(index).getSanctionedAmt()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"VehicleLoanOutstanding");
        variables.setVal(cibilTOLBeanList.get(index++).getOutstanding()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"HousingLoanWeighted");
        variables.setVal(cibilTOLBeanList.get(index).getWeightedAmtA()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"HousingLoanSanctionAmt");
        variables.setVal(cibilTOLBeanList.get(index).getSanctionedAmt()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"HousingLoanOutstanding");
        variables.setVal(cibilTOLBeanList.get(index++).getOutstanding()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"CreditCardWeighted");
        variables.setVal(cibilTOLBeanList.get(index).getWeightedAmtA()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"CreditCardSanctionAmt");
        variables.setVal(cibilTOLBeanList.get(index).getSanctionedAmt()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"CreditCardOutstanding");
        variables.setVal(cibilTOLBeanList.get(index++).getOutstanding()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"ConsumerLoanWeighted");
        variables.setVal(cibilTOLBeanList.get(index).getWeightedAmtA()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"ConsumerLoanSanctionAmt");
        variables.setVal(cibilTOLBeanList.get(index).getSanctionedAmt()+"");
        variablesList.add(variables);
        
        
        variables = excelService.findByMasterIdVariables(masterExcel,"ConsumerLoanOutstanding");
        variables.setVal(cibilTOLBeanList.get(index++).getOutstanding()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"CC/ODWeighted");
        variables.setVal(cibilTOLBeanList.get(index).getWeightedAmtA()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"CC/ODSanctionAmt");
        variables.setVal(cibilTOLBeanList.get(index).getSanctionedAmt()+"");
        variablesList.add(variables);
        
        double ccOdSancAmt =  cibilTOLBeanList.get(index).getSanctionedAmt();
        
        variables = excelService.findByMasterIdVariables(masterExcel,"CC/ODOutstanding");
        variables.setVal(cibilTOLBeanList.get(index++).getOutstanding()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"UnsecuredLoanWeighted");
        variables.setVal(cibilTOLBeanList.get(index).getWeightedAmtA()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"UnsecuredLoanSanctionAmt");
        variables.setVal(cibilTOLBeanList.get(index).getSanctionedAmt()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"UnsecuredLoanOutstanding");
        variables.setVal(cibilTOLBeanList.get(index++).getOutstanding()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"OtherLoanWeighted");
        variables.setVal(cibilTOLBeanList.get(index).getWeightedAmtA()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"OtherLoanSanctionAmt");
        variables.setVal(cibilTOLBeanList.get(index).getSanctionedAmt()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"OtherLoanOutstanding");
        variables.setVal(cibilTOLBeanList.get(index++).getOutstanding()+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"TotalSancAmt");
        variables.setVal(totalSancAmt+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"TotalOutsAmt");
        variables.setVal(totalOutstanding+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"TotalWeightedAmtA");
        variables.setVal(totalweightedAmtA+"");
        variablesList.add(variables);
        
        variables = excelService.findByMasterIdVariables(masterExcel,"TotalWeightedAmtB");
        variables.setVal(totalweightedAmtB+"");
        variablesList.add(variables);
        
		excelService.saveVariablesData(variablesList);
		
		NewExecSumm newExecSumm = new NewExecSumm();
		newExecSumm = excelService.findExecSummData("CC/OD Amount",masterExcel);
    	newExecSumm.setVal1(decimalFormat.format(ccOdSancAmt/100000)+"");
    	
    	
    	excelService.saveNewExecSumm(newExecSumm);

	}

public void saveCibilNonCibilVariables(String appId, MasterExcel masterExcel,Date analysisDate){
	
	String pattern = "####.##";
	DecimalFormat decimalFormat = new DecimalFormat(pattern);

	double cibilLoansCurrentOS = 0.0; 
	double nonCibilLoansCurrentOS = 0.0;
	double cibilLoansNonCurrentOS = 0.0;
	double nonCibilLoansNonCurrentOS = 0.0;
	double wcLoan = 0.0;
	int outstandingLoanCount = 0;
	int fourYrOutstandingCnt = 0;
	long curLib = 0;
	long otherTermLib = 0;
	long housingloan = 0;
	
	int	overdueLoanCount = 0;	
	int settledLoanCount = 0;
	int writtenOffLoanCount = 0;
	long amtOverdueSum = 0;
	int delaymcountSum = 0;
	
	int fourYrOverdueCnt = 0;
	int fourYrSettledCnt = 0;
	int fourYrWrittenCnt = 0;
	int fourYrAmtOverdue = 0;
	int fourYrDelayMCnt = 0;
	
	List<Integer> maxLateDaysList = new ArrayList<Integer>();
	List<Integer> monthslastDelayList = new ArrayList<Integer>();
	List<Integer> fourYrMonthslastDelayList = new ArrayList<Integer>();
	List<Integer> fourYrMaxLateDaysList = new ArrayList<Integer>();
	
	int cibilFinalScore = getCibilFinalScore(appId);
	
	List<Variables> variablesList = new ArrayList<Variables>();
	
	List<NewExecSumm> newExecSummList = new ArrayList<NewExecSumm>();
	
	List<Cibil> cibilDataList = dataService.getCibilData(appId);
	
	List<NonCibil> nonCibilList = dataService.fetchNonCibilByLoanId(appId);
	
	for(NonCibil nonCibil : nonCibilList){
		
		if(nonCibil.getCurrentnoncur().equals("Current")){
			
			nonCibilLoansCurrentOS += nonCibil.getOutstanding(); 
		}
		else{
			nonCibilLoansNonCurrentOS += nonCibil.getOutstanding();
		}
	
		if(nonCibil.getCategory().equals("Current Liability")){
			 curLib += nonCibil.getOutstanding();
		 }
		 else if(nonCibil.getCategory().equals("Other Term Liability")){
			 otherTermLib += nonCibil.getOutstanding();
		 }
		 else if(nonCibil.getCategory().equals("Housing Loan")){
			 housingloan += nonCibil.getOutstanding();
		 }
		
		if(nonCibil.getOutstanding() > 0){
			outstandingLoanCount++;
		}
		
	}
	
	for(Cibil cibil : cibilDataList){
		
		int diffYears = getDiffYears(cibil.getOpeneddate(),analysisDate);
		
		if(cibil.getTypeofloan().equalsIgnoreCase("CC/OD")){
			wcLoan += cibil.getOutstanding();
		}
		
		if(cibil.getCurrentnoncur().equals("Current")){
			 cibilLoansCurrentOS += cibil.getOutstanding(); 
		 }
		 else{
			 cibilLoansNonCurrentOS += cibil.getOutstanding();
		 }
		maxLateDaysList.add(cibil.getMaxlateDays());
		
		if(cibil.getCategory().equals("Current Liability")){
			 curLib += cibil.getOutstanding();
		 }
		 else if(cibil.getCategory().equals("Other Term Liability")){
			 otherTermLib += cibil.getOutstanding();
		 }
		 else if(cibil.getCategory().equals("Housing Loan")){
			 housingloan += cibil.getOutstanding();
		 }
		
		if(cibil.getOutstanding() > 0){
			outstandingLoanCount++;
		
			if(diffYears <= 4 && (!cibil.getStatus().equals("Written-off")))
				fourYrOutstandingCnt++;
		}
		
		if(cibil.getStatus().equals("Overdue")){
			overdueLoanCount++;
			if(diffYears <= 4)
				fourYrOverdueCnt++;
		}
		else if(cibil.getStatus().equals("Written-off")){
			writtenOffLoanCount++;
			if( diffYears <= 4)
				fourYrWrittenCnt++;
		}	
		else if(cibil.getStatus().equals("Settled")){	
			settledLoanCount++;
			if(diffYears <= 4)
				fourYrSettledCnt++;
		}
		else if(cibil.getStatus().equals("Sub-Standard")){
			if(diffYears <= 4)
			fourYrOverdueCnt++;
		}
		
		maxLateDaysList.add(cibil.getMaxlateDays());
		monthslastDelayList.add(cibil.getMsincelastdelay());
		
		if(diffYears <= 4)
			fourYrMonthslastDelayList.add(cibil.getMsincelastdelay());
		
		if(diffYears <= 4)
			fourYrMaxLateDaysList.add(cibil.getMaxlateDays());
		
		 amtOverdueSum += cibil.getAmtoverdue();
		 delaymcountSum += cibil.getDelaymcount();
		
		 if(diffYears <= 4)
			 fourYrAmtOverdue += cibil.getAmtoverdue();
		 if(diffYears <= 4)
			 fourYrDelayMCnt += cibil.getDelaymcount();

	
	}
	
	List<ExcelTableData> excelTableDataCycleList = excelService.getByTableNameAndMId("Cycle",masterExcel);
	double nonCibilCurrent = (nonCibilLoansCurrentOS)/100000;
	
	Variables variables = excelService.findByMasterIdVariables(masterExcel,"Current Liability");
    variables.setVal(curLib+"");
    variablesList.add(variables);
	
	if(excelTableDataCycleList.size()>0){
	double cycleOutstanding = Double.parseDouble(excelTableDataCycleList.get(excelTableDataCycleList.size()-7).getVal().replaceAll(",",""));
    nonCibilCurrent = (nonCibilLoansCurrentOS+cycleOutstanding)/100000;
	
    NewExecSumm newExecSumm = excelService.findExecSummData("Present Outstanding with AIL",masterExcel);
	newExecSumm.setVal1(decimalFormat.format(cycleOutstanding)+"");
	newExecSummList.add(newExecSumm);
	
	variables = excelService.findByMasterIdVariables(masterExcel,"Current Liability");
    variables.setVal(curLib+cycleOutstanding+"");
    variablesList.add(variables);
	
    double outstandingVal;
	for(int z = 1;z<=5;z++){
		
		String outstandingStr = excelTableDataCycleList.get(97+z).getVal();
		if(StringUtils.isNotEmpty(outstandingStr)){
			outstandingVal = Double.parseDouble(outstandingStr.replaceAll(",",""));
			
			if(outstandingVal>0){
				outstandingLoanCount++;
			}
		
		}
	}
  }
	
	System.out.println("outstandingLoanCount"+outstandingLoanCount);
	
    double cibilCurrent = cibilLoansCurrentOS/100000;
  
    double cibilNonCurrent = cibilLoansNonCurrentOS/100000;
    double noncibilNonCurrent = nonCibilLoansNonCurrentOS/100000;
    
    	NewExecSumm newExecSumm = excelService.findExecSummData("CIBIL Score",masterExcel);
    	newExecSumm.setVal1(cibilFinalScore+"");
    	newExecSummList.add(newExecSumm);
    	
    	newExecSumm = excelService.findExecSummData("Total Liability",masterExcel);
    	newExecSumm.setVal1(decimalFormat.format(nonCibilCurrent+cibilCurrent+noncibilNonCurrent+cibilNonCurrent)+"");
    	newExecSummList.add(newExecSumm);
    	
    	newExecSumm = excelService.findExecSummData("Max delay days in CIBIL",masterExcel);
    	newExecSumm.setVal1(Collections.max(maxLateDaysList)+"");
    	newExecSummList.add(newExecSumm);
    	
    	
    	newExecSumm = excelService.findExecSummData("O/S CIBIL Loans (WC)",masterExcel);
    	newExecSumm.setVal1(decimalFormat.format(cibilCurrent)+"");
    	newExecSummList.add(newExecSumm);
    	
    	newExecSumm = excelService.findExecSummData("O/S Non-CIBIL Loans (WC)",masterExcel);
    	newExecSumm.setVal1(decimalFormat.format(nonCibilCurrent)+"");
    	newExecSummList.add(newExecSumm);
    	
    	newExecSumm = excelService.findExecSummData("O/S Total (WC)",masterExcel);
    	newExecSumm.setVal1(decimalFormat.format(nonCibilCurrent+cibilCurrent)+"");
    	newExecSummList.add(newExecSumm);
    	
    	newExecSumm = excelService.findExecSummData("O/S CIBIL Loans (TL)",masterExcel);
    	newExecSumm.setVal1(decimalFormat.format(cibilNonCurrent)+"");
    	newExecSummList.add(newExecSumm);
    	
    	newExecSumm = excelService.findExecSummData("O/S Non-CIBIL Loans (TL)",masterExcel);
    	newExecSumm.setVal1(decimalFormat.format(noncibilNonCurrent)+"");
    	newExecSummList.add(newExecSumm);
    	
    	newExecSumm = excelService.findExecSummData("O/S Total (TL)",masterExcel);
    	newExecSumm.setVal1(decimalFormat.format(noncibilNonCurrent+cibilNonCurrent)+"");
    	newExecSummList.add(newExecSumm);
    	
    	excelTableDataDao.batchSaveExcel(newExecSummList);
    	
    	 variables = excelService.findByMasterIdVariables(masterExcel,"Total Liability");
    	 variables.setVal(decimalFormat.format(nonCibilCurrent+cibilCurrent+noncibilNonCurrent+cibilNonCurrent)+"");
    	 variablesList.add(variables);
    	 
    	 variables = excelService.findByMasterIdVariables(masterExcel,"CIBIL final score");
    	 variables.setVal(cibilFinalScore+"");
    	 variablesList.add(variables);
    	 
    	    variables = excelService.findByMasterIdVariables(masterExcel,"Other Term Liability");
    	    variables.setVal(otherTermLib+"");
    	    variablesList.add(variables);
    	    
    	    variables = excelService.findByMasterIdVariables(masterExcel,"Housing Loan");
    	    variables.setVal(housingloan+"");
    	    variablesList.add(variables);
    	    
    	    variables = excelService.findByMasterIdVariables(masterExcel,"WCLoan");
    	    variables.setVal(wcLoan+nonCibilLoansCurrentOS+nonCibilLoansNonCurrentOS+"");
    	    variablesList.add(variables);
    	    
    	    variables = excelService.findByMasterIdVariables(masterExcel,"OutstandingLoanCount");
    	    variables.setVal(outstandingLoanCount+"");
    	    variablesList.add(variables);
    	    
    	    variables = excelService.findByMasterIdVariables(masterExcel,"4yrOutstandingLoanCount");
    	    variables.setVal(fourYrOutstandingCnt+"");
    	    variablesList.add(variables);
    	    
    	    variables = excelService.findByMasterIdVariables(masterExcel,"OverdueLoanCount");
    	    variables.setVal(overdueLoanCount+"");
    	    variablesList.add(variables);
    	    
    	    variables = excelService.findByMasterIdVariables(masterExcel,"SettledLoanCount");
    	    variables.setVal(settledLoanCount+"");
    	    variablesList.add(variables);
    	    
    	    variables = excelService.findByMasterIdVariables(masterExcel,"WrittenOffLoanCount");
    	    variables.setVal(writtenOffLoanCount+"");
    	    variablesList.add(variables);
    	    
    	    variables = excelService.findByMasterIdVariables(masterExcel,"MonthsLastDelinquency");
    	    variables.setVal(Collections.min(monthslastDelayList)+"");
    	    variablesList.add(variables);
    	    
    	    variables = excelService.findByMasterIdVariables(masterExcel,"MaxLateDays");
    	    variables.setVal(Collections.max(maxLateDaysList)+"");
    	    variablesList.add(variables);
    	    
    	    variables = excelService.findByMasterIdVariables(masterExcel,"AmountOverdue");
    	    variables.setVal(amtOverdueSum+"");
    	    variablesList.add(variables);
    	    
    	    variables = excelService.findByMasterIdVariables(masterExcel,"DelayMonthsCount");
    	    variables.setVal(delaymcountSum+"");
    	    variablesList.add(variables);
    	    
    	    variables = excelService.findByMasterIdVariables(masterExcel,"4yrOverdueLoanCount");
    	    variables.setVal(fourYrOverdueCnt+"");
    	    variablesList.add(variables);
    	    
    	    variables = excelService.findByMasterIdVariables(masterExcel,"4yrSettledLoanCount");
    	    variables.setVal(fourYrSettledCnt+"");
    	    variablesList.add(variables);
    	    
    	    variables = excelService.findByMasterIdVariables(masterExcel,"4yrWrittenOffLoanCount");
    	    variables.setVal(fourYrWrittenCnt+"");
    	    variablesList.add(variables);
    	    
    	    
    	    variables = excelService.findByMasterIdVariables(masterExcel,"4yrMonthsLastDelinquency");
    	    variables.setVal(Collections.min(fourYrMonthslastDelayList)+"");	
    	    variablesList.add(variables);
    	    
    	    variables = excelService.findByMasterIdVariables(masterExcel,"4yrMaxLateDays");
    	    variables.setVal(Collections.max(fourYrMaxLateDaysList)+"");
    	    variablesList.add(variables);
    	    
    	    variables = excelService.findByMasterIdVariables(masterExcel,"4yrAmountOverdue");
    	    variables.setVal(fourYrAmtOverdue+"");
    	    variablesList.add(variables);
    	    
    	    variables = excelService.findByMasterIdVariables(masterExcel,"4yrDelayMonthsCount");
    	    variables.setVal(fourYrDelayMCnt+"");
    	    variablesList.add(variables);
    	 
    	 excelService.saveVariablesData(variablesList);
    
 }


	public int getCibilFinalScore(String appId) {
		
		List<Applicant> applicantList = dataService.getApplicantListByLoanId(appId);
		
		List<Integer> scoreListLow = new ArrayList<Integer>();
		List<Integer> scoreListHigh = new ArrayList<Integer>();
		
		int minScore = 0;
		
		for(Applicant applicant : applicantList){
			
			if(applicant.getCibilscore() > -1 && applicant.getCibilscore() <= 5){
				scoreListLow.add(applicant.getCibilscore());
			}
			else{
				if(applicant.getCibilscore() > 5)
				scoreListHigh.add(applicant.getCibilscore());
			}
			
			if(applicant.getPersonalscore() > -1 && applicant.getPersonalscore() <= 5){
				scoreListLow.add(applicant.getPersonalscore());
			}
			else{
				if(applicant.getPersonalscore() > 5)
					scoreListHigh.add(applicant.getPersonalscore());
			}
			
		}
		
		Collections.sort(scoreListHigh);
		Collections.sort(scoreListLow);
		
				if(scoreListHigh.size() > 0){
					minScore = scoreListHigh.get(0);
				}
				else if(scoreListLow.size() > 0){
					minScore = scoreListLow.get(0);
				}
				else{
					minScore = -1;
				}
				
				
		return minScore;
	}

public double calculateWeightage(String type,String status,long sancAmt,long outstanding, int multiplier,int weightage) {

	//if
	if (multiplier == 0) {
		if (type.equalsIgnoreCase("CC/OD")
				|| type.equalsIgnoreCase("CREDIT CARD")) {

			if (status.equalsIgnoreCase("Written-off") || status.equalsIgnoreCase("Closed")
					|| status.equalsIgnoreCase("Settled")) {

				return outstanding;
			} else {
				return sancAmt;
			}
		} else {

			if (weightage * 0.01 * sancAmt < outstanding) {
				return weightage * 0.01 * sancAmt;
			} else {
				return outstanding;
			}
		}
	} else {
		return weightage * 0.01 * outstanding;
	}
}
	
	public  int[] getLoanEnquiriesCount(List<LoanEnquiries> loanEnquiriesList, Date date) {

	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	Calendar c = Calendar.getInstance(); 
	c.setTime(date); 
	c.add(Calendar.MONTH, -6);
	
	Date sixMonthly = c.getTime();
	
	c = Calendar.getInstance(); 
	c.setTime(date); 
	c.add(Calendar.MONTH, -12);
	
	Date yearly = c.getTime();
	
	c = Calendar.getInstance(); 
	c.setTime(date); 
	c.add(Calendar.MONTH, -3);
	
	Date threeMonthly = c.getTime();
	
	
	int arr[] = {0,0,0,0};
	
	try{
		
		arr[0] = loanEnquiriesList.size();
	for(LoanEnquiries loanEnquiries : loanEnquiriesList){
		
		
		Date curEnquiry =  df.parse(loanEnquiries.getDateOfEnquiry());
		
		if(curEnquiry.after(threeMonthly) && curEnquiry.before(date)){
			arr[1]++;
		}
		if(curEnquiry.after(sixMonthly) && curEnquiry.before(date)){
			arr[2]++;
		}
	    if(curEnquiry.after(yearly) && curEnquiry.before(date)){
			arr[3]++;
		}
	    
	    
	}
	
	return arr;
	
	}
	catch(Exception e){
		
		return arr;
	}
}
	
public String convertDateToFormattedDate(String date) {
	if(date.length()==8){
		String day=date.substring(0, 2);
		String month=date.substring(2, 4);
		String year=date.substring(4, 8);
		return day+"/"+month+"/"+year;
	}else{
		return date;
	}

}


public int getAgefromDob(String date){
	
	if(!StringUtils.isEmpty(date)){
	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try{
		Date dob = df.parse(date);
		Calendar cal = Calendar.getInstance();
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(dob);
	    int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH);
	    int day = cal.get(Calendar.DAY_OF_MONTH);
		
	   int age = yearNow-year;
	   
	   if(monthNow<month){
		   
		   age--;
		   
	   }
	   else if(monthNow == month){
		   
		   if(dayNow < day){
			   
			   age--;
			   
		   }
	   }
	    
	   return age;
		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		
	}else{
		return -1;
	}
	
}
//Business Loan – General

	public int getDelayCount(String[] arr3) {
	
	int count = 0;
	for(String s : arr3){
		
		if(s.equals("000")||s.equals("XXX")||s.equals("STD")){
			
		}
		else{
			count++;
		}
	}
	return count;
}

	public int getmonthsSinceLastDelay(String str, String[] arr3, Date lastEnq){
 
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	try{
	Date paymentHistoryEndDate = df.parse(str);	
	
	Calendar cal = Calendar.getInstance();	
	
	cal.setTime(lastEnq);
	
	int lastEnqyear = cal.get(Calendar.YEAR);
	int lastEnqMonth = cal.get(Calendar.MONTH);

	int totalcurmonths = lastEnqyear*12+lastEnqMonth;

	if(arr3 != null){	
		
	int lastdelaycount=-1;
	
	for(int i=0;i<arr3.length;i++){
		
		if(arr3[i].equals("000")||arr3[i].equals("XXX")||arr3[i].equals("STD")||arr3[i].equals("SUB")
				||arr3[i].equals("SMA")){
			
		}
		else{
			lastdelaycount = i;
			break;
		}
		
	}
	
	if(lastdelaycount == -1){
		return 999;
	}
	else{
		
		cal.setTime(paymentHistoryEndDate);
		
		int startdateyear = cal.get(Calendar.YEAR);
		int startdatemonth = cal.get(Calendar.MONTH);
		
		int totaldelaymonths  = startdateyear*12 + startdatemonth - lastdelaycount;
	
		cal.setTime(lastEnq);
	
		return totalcurmonths - totaldelaymonths;
	
		}
	}
	else{
		
		cal.setTime(paymentHistoryEndDate);
		
		int startdateyear = cal.get(Calendar.YEAR);
		int startdatemonth = cal.get(Calendar.MONTH);
		
		int totaldelaymonths  = startdateyear*12 + startdatemonth;
		
		return totalcurmonths - totaldelaymonths;
		
		}
	}
	catch(Exception e){
	e.printStackTrace();
	return -1;
	}
	
}


	public int getMaxDelay(String[] arr3){
	
	int max=0;
	int temp = 0;
	
	for(int i=0;i<arr3.length;i++){
		
		if(arr3[i].equals("000")||arr3[i].equals("XXX")||arr3[i].equals("STD")||
				arr3[i].equals("SMA")||arr3[i].equals("SUB")||arr3[i].equals("DBT")
				||arr3[i].equals("LSS")){
			 
			if(arr3[i].equals("SMA")){
				return 75;
			}
			else if(arr3[i].equals("DBT")||arr3[i].equals("LSS")||arr3[i].equals("SUB")){
				return 0;
			}
		}
		else{
			try{
			
				temp = Integer.parseInt(arr3[i]);
				if(temp>max){
				max = temp;
				}
			
			}
			catch(Exception e){
				e.printStackTrace();
				return -1;
			}
			
		}
		
	}
	
		return max;
	}

	public String findCibilStatus(ResponseAccountSegment accnts, String[] arr3) {

		String status = "Sanctioned";
		
		if(StringUtils.isNotEmpty(accnts.getAmountOverdue())){
			status ="Overdue";
		}
		
		if(StringUtils.isNotEmpty(accnts.getDateClosed())){
			status = "Closed";
		}
		
		if(StringUtils.isNotEmpty(accnts.getWrittenoffandSettledStatus())){
			status = accnts.getWrittenoffandSettledStatus();
		}

		
		for(int i=0;i<arr3.length;i++){
			
		if(arr3[i].equals("LSS")){  		
			status = "Loss";
			break;
		}
			if(arr3[i].equals("DBT")){
				status = "Doubtful";
				break;
			}

			if(arr3[i].equals("SUB")){
				status = "Sub-Standard";
				break;
			}
			
		if(arr3[i].equals("SMA")){
			status = "Other";
			break;
		}
	}
		
		return status;
	}
	
	public void saveCibilData(String appId,String loanId){
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		try {

		List<Applicant> applicantOldList = dataService.getApplicantListByLoanId(loanId);
				
		String[] directorsList = dataService.getCibilDirectors(appId);
	
			
		if(applicantOldList == null || directorsList.length > applicantOldList.size()){
			
			MasterExcel masterExcel = excelService.findByMasterExcelId(appId);
			int cibilcntr = 1;
			int applicantCount = 1;
			List<Variables> variablesList = new ArrayList<Variables>();
			
			List<Applicant> applicantList = new ArrayList<Applicant>();
			
			Variables variables = excelService.findByMasterIdVariables(masterExcel,"CountofPartner");
	        variables.setVal(directorsList.length+"");
	        variablesList.add(variables);
			
	        for(String dirId  : directorsList){
			
	        if(applicantOldList != null  && applicantCount <= applicantOldList.size()){
					
	        }
	        else{
			
	        LKCibilResponse lKCibilResponse = dataService.getCibilData(appId,dirId);
						
			if (lKCibilResponse != null ) {
				
				List<Cibil> cibilList = new ArrayList<Cibil>();
				
					JsonResponse cibilResponseData = gson.fromJson(lKCibilResponse.getResponseJsonData(),
							JsonResponse.class);
					
					Applicant applicant =  new Applicant();
					
						applicant.setCreatedDate(Calendar.getInstance().getTime());
					
					if(cibilResponseData.getNameSegmaent()!=null){
						if(StringUtils.isNotEmpty(cibilResponseData.getNameSegmaent().getDateofBirth())){
							applicant.setDob(convertDateToFormattedDate(cibilResponseData.getNameSegmaent().getDateofBirth()));
							applicant.setAge(getAgefromDob(cibilResponseData.getNameSegmaent().getDateofBirth()));
						}
						
						if(StringUtils.isNotEmpty(cibilResponseData.getNameSegmaent().getGender())){
							applicant.setGender(cibilResponseData.getNameSegmaent().getGender());
						}
						
					if(StringUtils.isNotEmpty(cibilResponseData.getNameSegmaent().getConsumerFullName())){
						applicant.setName(cibilResponseData.getNameSegmaent().getConsumerFullName());
					}
				}
					
					if(cibilResponseData.getScoreSegmaent()!=null){
						if(StringUtils.isNotEmpty(cibilResponseData.getScoreSegmaent().getScore())){
							
							try{
							applicant.setCibilscore(Integer.parseInt(cibilResponseData.getScoreSegmaent().getScore()));
							}
							catch(Exception e){
								applicant.setCibilscore(-1);
							}
						}
					}
					
					if(cibilResponseData.getPlScoreSegmaent()!=null){
					if(StringUtils.isNotEmpty(cibilResponseData.getPlScoreSegmaent().getScore())){
						try{
						applicant.setPersonalscore(Integer.parseInt(cibilResponseData.getPlScoreSegmaent().getScore()));
						}catch(Exception e){
							applicant.setPersonalscore(-1);
						}
					  }
					}
					
					applicant.setLoanId(loanId);
					applicant.setLastEnquiry(lKCibilResponse.getCreatedTime());
					
					Applicant afterSave = dataService.saveApplicantsData(applicant);
					
					if(cibilResponseData.getEnquirySegments()!= null){
						
						saveEnquiryData(cibilResponseData.getEnquirySegments(),afterSave.getId());
					
					}
					
				    variables = excelService.findByMasterIdVariables(masterExcel,"CIBIL"+applicantCount);
					variables.setVal(afterSave.getCibilscore()+"");
					variablesList.add(variables);
					
			        variables = excelService.findByMasterIdVariables(masterExcel,"CIBILPersonal"+applicantCount);
			        variables.setVal(afterSave.getPersonalscore()+"");
			        variablesList.add(variables);
			        
			        variables = excelService.findByMasterIdVariables(masterExcel,"Age"+applicantCount);
			        variables.setVal(afterSave.getAge()+"");
			        variablesList.add(variables);
			        
			        variables = excelService.findByMasterIdVariables(masterExcel,"ApplicantName"+applicantCount);
			        variables.setVal(afterSave.getName()+"");
			        variablesList.add(variables);
			        
			        variables = excelService.findByMasterIdVariables(masterExcel,"Gender"+applicantCount);
			        variables.setVal(afterSave.getGender()+"");
			        variablesList.add(variables);
			        
			        variables = excelService.findByMasterIdVariables(masterExcel,"DOB"+applicantCount);
			        variables.setVal(afterSave.getDob()+"");
			        variablesList.add(variables);
			        
					applicantList.add(afterSave);
					
					
				if (cibilResponseData.getAccountSegments() != null) {
					
					for (ResponseAccountSegment accnts : cibilResponseData.getAccountSegments()) {
						
						
						String arr2[] = new String[0];
						String arr1[] = new String[0];
						
						Cibil cibil = new Cibil();
						
						cibil.setCreated(Calendar.getInstance().getTime());
						cibil.setUpdated(Calendar.getInstance().getTime());
						cibil.setLoanId(loanId);
						int repaymentTenure = -1;
						if(!StringUtils.isEmpty(accnts.getRepaymentTenure())){
							
							try{
							 repaymentTenure = Integer.parseInt(accnts.getRepaymentTenure());
							cibil.setTenure(repaymentTenure);
							}
							catch(Exception e){
								cibil.setTenure(repaymentTenure);
								}
							}
						
						List<CibilLoanSummary> cibilLoanSummaryList = createTolList();
						
						cibil.setTypeofloan(accnts.getAccountType());
						cibil.setOriginalLoanType(accnts.getAccountType());
						cibil.setOwnershipType(accnts.getOwnershipIndicator());
						
						String category = "Error";
						for(CibilLoanSummary cibilLoanSummary : cibilLoanSummaryList){
							if(cibilLoanSummary.getType().equalsIgnoreCase(cibil.getTypeofloan())){
								 category = cibilLoanSummary.getCategory();
							}
							
						}
						
						
						if(!StringUtils.isEmpty(accnts.getPaymentHistory1())){
						 arr1 = accnts.getPaymentHistory1().split("    ");
						}
						if(!StringUtils.isEmpty(accnts.getPaymentHistory2())){
						 arr2 = accnts.getPaymentHistory2().split("    ");	
						}
						
						String arr3[] = (String[])ArrayUtils.addAll(arr1,arr2); 

						if(!StringUtils.isEmpty(accnts.getHighCredit_SanctionedAmount())){
						try{
							cibil.setSanctionedamt(Long.parseLong(accnts.getHighCredit_SanctionedAmount().replaceAll(",","")));
						}
						catch(Exception e){
							cibil.setSanctionedamt(-1);
							}
						}
						
						if(cibil.getTypeofloan().equalsIgnoreCase("Credit Card")){
							
							if(!StringUtils.isEmpty(accnts.getCreditLimit())){
								
								try{
								int creditLimit  = Integer.parseInt(accnts.getCreditLimit().replaceAll(",",""));
								if(creditLimit > cibil.getSanctionedamt()){
									cibil.setSanctionedamt(creditLimit);
									}
								}
								catch(Exception e){
								   cibil.setSanctionedamt(-1);
								}
								
							}
							
						}
						
						if(!StringUtils.isEmpty(accnts.getCurrentBalance())){
							try{
						cibil.setOutstanding(Long.parseLong(accnts.getCurrentBalance().replaceAll(",","")));
							}
							catch(Exception e){
								cibil.setOutstanding(-1);
							}
						}
						
						if(!StringUtils.isEmpty(accnts.getAmountOverdue())){
							try{
						cibil.setAmtoverdue(Integer.parseInt(accnts.getAmountOverdue().replaceAll(",","")));
							}
							catch(Exception e){
							cibil.setAmtoverdue(-1);	
							}
						}
						cibil.setDelaymcount(getDelayCount(arr3));
						if(!StringUtils.isEmpty(accnts.getRateOfInterest())){
							try{
						cibil.setInterestrate(Float.parseFloat(accnts.getRateOfInterest()));
							}catch(Exception e){
								cibil.setInterestrate(-1.0f);
							}
						}
						
						if(cibilcntr>35){
						variables = new Variables();
						variables.setKey(cibilcntr+" CIBILDate");
						variables.setMasterExcel(masterExcel);
						}
						else{
						variables = excelService.findByMasterIdVariables(masterExcel,cibilcntr+" CIBILDate");	
						}
						
						if(StringUtils.isNotEmpty(accnts.getDateOpened_Disbursed())){
							try{
								variables.setVal(accnts.getDateOpened_Disbursed());
								cibil.setOpeneddate(df.parse(accnts.getDateOpened_Disbursed()));
							}
							catch(Exception e){
								cibil.setOpeneddate(null);
							}
						}
						variablesList.add(variables);	
						
						String status = findCibilStatus(accnts,arr3);
						String curnoncur = category=="Current Liability"?"Current":"Non-Current";
						
						if(status.equals("Written-off") || status.equals("Settled")){
							
							if(StringUtils.isNotEmpty(accnts.getDateofLastPayment())){
								cibil.setMsincelastdelay(getmonthsSinceLastDelay(accnts.getDateofLastPayment(),null,applicant.getLastEnquiry()));			
							}
							else{
								cibil.setMsincelastdelay(getmonthsSinceLastDelay(accnts.getPaymentHistoryStartDate(),null,applicant.getLastEnquiry()));
							}
						}
						else
						cibil.setMsincelastdelay(getmonthsSinceLastDelay(accnts.getPaymentHistoryStartDate(),arr3,applicant.getLastEnquiry()));
						
						cibil.setStatus(status);
						cibil.setCategory(category);
						cibil.setCurrentnoncur(curnoncur);
						cibil.setMaxlateDays(getMaxDelay(arr3));
						cibil.setApplicantId(afterSave.getId());
						
						cibilcntr++;
						cibilList.add(cibil);
					 
							
					}//cibil forLoop
					
				}//cibilResponseData.getAccountSegments() != null
				
				if(cibilList.size() > 0){
					dataService.saveCibilList(cibilList);
				}
				
			}//iflkcibilResponse	
		  
	      }//if applicant already present
	        applicantCount++;
	    }//directorList
			
			excelService.saveVariablesData(variablesList);
			recalculateData(loanId,masterExcel);
		  }// if applicantcount not matching	
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	public void saveLoanEnquiriesVariables(List<Applicant> applicantList, MasterExcel masterExcel) {
		
		List<Variables> variablesList = new ArrayList<Variables>();
		
		Variables variables1 = excelService.findByMasterIdVariables(masterExcel,"LoanEnq6m");
		Variables variables2 = excelService.findByMasterIdVariables(masterExcel,"LoanEnq12m");
		Variables variables3 = excelService.findByMasterIdVariables(masterExcel,"LoanInquiries");
		
		int enqiries[] = getTotalLoanEnquiriesCount(applicantList);
			
			variables1.setVal(enqiries[2]+"");
			variables2.setVal(enqiries[3]+"");
			variables3.setVal(enqiries[0]+"");
				
		variablesList.add(variables1);
		variablesList.add(variables2);
		variablesList.add(variables3);
	}
	
	public int[] getTotalLoanEnquiriesCount(List<Applicant> applicantList) {
		
	List<String> totalEnquiriesStringList = new ArrayList<String>();
	List<LoanEnquiries> totalLoanEnquiriesList = new ArrayList<LoanEnquiries>();
	for(int i = 0; i<applicantList.size(); i++){
		
	List<LoanEnquiries> loanEnquiriesList = loanEnquiriesDao.getLoanEnquiries(applicantList.get(i).getId());
	
	List<String> curApplicantEnq = new ArrayList<String>();
	if(i == 0){
		
		for(LoanEnquiries loanEnquiries : loanEnquiriesList){
			curApplicantEnq.add(loanEnquiries.getDateOfEnquiry()+loanEnquiries.getPurpose());
			totalLoanEnquiriesList.add(loanEnquiries);
		}
	}
	else{
		for(LoanEnquiries loanEnquiries : loanEnquiriesList){
			
			String str = loanEnquiries.getDateOfEnquiry()+loanEnquiries.getPurpose();
			if(!totalEnquiriesStringList.contains(str)){
				curApplicantEnq.add(str);
				totalLoanEnquiriesList.add(loanEnquiries);
			}
		}

	}
	totalEnquiriesStringList.addAll(curApplicantEnq);
	
 }
	
	return getLoanEnquiriesCount(totalLoanEnquiriesList,applicantList.get(applicantList.size()-1).getLastEnquiry());
		
	}


private void saveEnquiryData(List<ResponseEnquirySegment> enquirySegments,long applicantId) {

	List<LoanEnquiries> loanEnquiriesList = new ArrayList<LoanEnquiries>();
	for(ResponseEnquirySegment responseEnquirySegment : enquirySegments){
		LoanEnquiries loanEnquiries = new LoanEnquiries();
		loanEnquiries.setDateOfEnquiry(responseEnquirySegment.getDateofEnquiry());
		loanEnquiries.setAmount(responseEnquirySegment.getAmount());
		loanEnquiries.setApplicantId(applicantId);
		loanEnquiries.setPurpose(responseEnquirySegment.getPurpose());
		
		loanEnquiriesList.add(loanEnquiries);
	}
	
	loanEnquiriesDao.batchSave(loanEnquiriesList);
}

public static List<CibilLoanSummary> createTolList(){
	
	
	List<CibilLoanSummary> cibilLoanSummaryList = new ArrayList<CibilLoanSummary>();
	
	
	CibilLoanSummary cibilLoanSummary = new CibilLoanSummary();
	cibilLoanSummary.setType("Term Loan");
	cibilLoanSummary.setWeightageA(25);
	cibilLoanSummary.setRemarkA("(S/A)");
	cibilLoanSummary.setWeightedB(25);
	cibilLoanSummary.setRemarkB("(S/A)");
	cibilLoanSummary.setMultiplier(0);
	cibilLoanSummary.setCategory("Other Term Liability");
	cibilLoanSummaryList.add(cibilLoanSummary);

	
	cibilLoanSummary = new CibilLoanSummary();
	cibilLoanSummary.setType("Business Loan-Current");
	cibilLoanSummary.setWeightageA(100);
	cibilLoanSummary.setRemarkA("(O/S)");
	cibilLoanSummary.setWeightedB(100);
	cibilLoanSummary.setRemarkB("(O/S)");
	cibilLoanSummary.setMultiplier(1);
	cibilLoanSummary.setCategory("Current Liability");
	cibilLoanSummaryList.add(cibilLoanSummary);
	
    cibilLoanSummary = new CibilLoanSummary();
	cibilLoanSummary.setType("Business Loan-NonCurrent");
	cibilLoanSummary.setWeightageA(33);
	cibilLoanSummary.setRemarkA("(S/A)");
	cibilLoanSummary.setWeightedB(50);
	cibilLoanSummary.setRemarkB("(S/A)");
	cibilLoanSummary.setMultiplier(0);
	cibilLoanSummary.setCategory("Other Term Liability");
	cibilLoanSummaryList.add(cibilLoanSummary);
	
	cibilLoanSummary = new CibilLoanSummary();
	cibilLoanSummary.setType("Property Loan");
	cibilLoanSummary.setWeightageA(15);
	cibilLoanSummary.setRemarkA("(S/A)");
	cibilLoanSummary.setWeightedB(33);
	cibilLoanSummary.setRemarkB("(S/A)");
	cibilLoanSummary.setCategory("Other Term Liability");
	cibilLoanSummary.setMultiplier(0);
	cibilLoanSummaryList.add(cibilLoanSummary);
	
	cibilLoanSummary = new CibilLoanSummary();
	cibilLoanSummary.setType("Education Loan");
	cibilLoanSummary.setWeightageA(15);
	cibilLoanSummary.setRemarkA("(S/A)");
	cibilLoanSummary.setWeightedB(15);
	cibilLoanSummary.setRemarkB("(S/A)");
	cibilLoanSummary.setMultiplier(0);
	cibilLoanSummary.setCategory("Other Term Liability");
	
	cibilLoanSummaryList.add(cibilLoanSummary);
	
	cibilLoanSummary = new CibilLoanSummary();
	cibilLoanSummary.setType("Gold Loan");
	cibilLoanSummary.setWeightageA(100);
	cibilLoanSummary.setRemarkA("(O/S)");
	cibilLoanSummary.setWeightedB(100);
	cibilLoanSummary.setRemarkB("(O/S)");
	cibilLoanSummary.setMultiplier(1);
	cibilLoanSummary.setCategory("Current Liability");
	cibilLoanSummaryList.add(cibilLoanSummary);
	
	cibilLoanSummary = new CibilLoanSummary();
	cibilLoanSummary.setType("Personal Loan");
	cibilLoanSummary.setWeightageA(100);
	cibilLoanSummary.setRemarkA("(O/S)");
	cibilLoanSummary.setWeightedB(100);
	cibilLoanSummary.setRemarkB("(O/S)");
	cibilLoanSummary.setMultiplier(1);
	cibilLoanSummary.setCategory("Current Liability");
	cibilLoanSummaryList.add(cibilLoanSummary);
	
	cibilLoanSummary = new CibilLoanSummary();
	cibilLoanSummary.setType("Vehicle Loan");
	cibilLoanSummary.setWeightageA(20);
	cibilLoanSummary.setRemarkA("(S/A)");
	cibilLoanSummary.setWeightedB(20);
	cibilLoanSummary.setRemarkB("(S/A)");
	cibilLoanSummary.setMultiplier(0);
	cibilLoanSummary.setCategory("Other Term Liability");
	cibilLoanSummaryList.add(cibilLoanSummary);
	
	cibilLoanSummary = new CibilLoanSummary();
	cibilLoanSummary.setType("Housing Loan");
	cibilLoanSummary.setWeightageA(5);
	cibilLoanSummary.setRemarkA("(S/A)");
	cibilLoanSummary.setWeightedB(7);
	cibilLoanSummary.setRemarkB("(S/A)");
	cibilLoanSummary.setMultiplier(0);
	cibilLoanSummary.setCategory("Housing Loan");
	cibilLoanSummaryList.add(cibilLoanSummary);
	
	cibilLoanSummary = new CibilLoanSummary();
	cibilLoanSummary.setType("Credit Card");
	cibilLoanSummary.setWeightageA(100);
	cibilLoanSummary.setRemarkA("(S/A)");
	cibilLoanSummary.setWeightedB(100);
	cibilLoanSummary.setRemarkB("(S/A)");
	cibilLoanSummary.setMultiplier(0);
	cibilLoanSummary.setCategory("Current Liability");
	cibilLoanSummaryList.add(cibilLoanSummary);
	
	cibilLoanSummary = new CibilLoanSummary();
	cibilLoanSummary.setType("Consumer Loan");
	cibilLoanSummary.setWeightageA(100);
	cibilLoanSummary.setRemarkA("(O/S)");
	cibilLoanSummary.setWeightedB(100);
	cibilLoanSummary.setRemarkB("(O/S)");
	cibilLoanSummary.setMultiplier(1);
	cibilLoanSummary.setCategory("Current Liability");
	cibilLoanSummaryList.add(cibilLoanSummary);
	
	cibilLoanSummary = new CibilLoanSummary();
	cibilLoanSummary.setType("CC/OD");
	cibilLoanSummary.setWeightageA(100);
	cibilLoanSummary.setRemarkA("(S/A)");
	cibilLoanSummary.setWeightedB(100);
	cibilLoanSummary.setRemarkB("(S/A)");
	cibilLoanSummary.setMultiplier(0);
	cibilLoanSummary.setCategory("Current Liability");
	cibilLoanSummaryList.add(cibilLoanSummary);
	
	cibilLoanSummary = new CibilLoanSummary();
	cibilLoanSummary.setType("Unsecured Loan");
	cibilLoanSummary.setWeightageA(100);
	cibilLoanSummary.setRemarkA("(O/S)");
	cibilLoanSummary.setWeightedB(100);
	cibilLoanSummary.setRemarkB("(O/S)");
	cibilLoanSummary.setMultiplier(1);
	cibilLoanSummary.setCategory("Current Liability");
	cibilLoanSummaryList.add(cibilLoanSummary);
	
	cibilLoanSummary = new CibilLoanSummary();
	cibilLoanSummary.setType("Inland Bill");
	cibilLoanSummary.setWeightageA(100);
	cibilLoanSummary.setRemarkA("(O/S)");
	cibilLoanSummary.setWeightedB(100);
	cibilLoanSummary.setRemarkB("(O/S)");
	cibilLoanSummary.setMultiplier(1);
	cibilLoanSummary.setCategory("Current Liability");
	cibilLoanSummaryList.add(cibilLoanSummary);
	
	
	cibilLoanSummary = new CibilLoanSummary();
	cibilLoanSummary.setType("Bank Guarantee");
	cibilLoanSummary.setWeightageA(20);
	cibilLoanSummary.setRemarkA("(S/A)");
	cibilLoanSummary.setWeightedB(30);
	cibilLoanSummary.setRemarkB("(S/A)");
	cibilLoanSummary.setMultiplier(0);
	cibilLoanSummary.setCategory("Current Liability");
	cibilLoanSummaryList.add(cibilLoanSummary);
	
	cibilLoanSummary = new CibilLoanSummary();
	cibilLoanSummary.setType("Other");
	cibilLoanSummary.setWeightageA(100);
	cibilLoanSummary.setRemarkA("(O/S)");
	cibilLoanSummary.setWeightedB(100);
	cibilLoanSummary.setRemarkB("(O/S)");
	cibilLoanSummary.setMultiplier(1);
	cibilLoanSummary.setCategory("Current Liability");
	cibilLoanSummaryList.add(cibilLoanSummary);
	
	return cibilLoanSummaryList;
	
}

public int getDiffYears(Date first, Date last) {
    
	Calendar a = Calendar.getInstance();
	
	a.setTime(first);
	
    Calendar b = Calendar.getInstance();
    
    b.setTime(last);
    
    int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
    if (b.get(Calendar.MONTH) > a.get(Calendar.MONTH)){ 
    		diff++;
    }		
    else if(a.get(Calendar.MONTH) == b.get(Calendar.MONTH)){  
    	
    	if(b.get(Calendar.DATE) > a.get(Calendar.DATE)) 
    		diff++;
    }
    
    return diff;
}

	public void recalculateData(String appId, MasterExcel masterExcel) {
	
	List<Applicant> applicantList = dataService.getApplicantListByLoanId(appId);
	
	if(applicantList.size()>0){
	saveTOLvariables(appId,masterExcel);
	saveCibilNonCibilVariables(appId,masterExcel,applicantList.get(0).getLastEnquiry());
	saveLoanEnquiriesVariables(applicantList,masterExcel);
		}
	}

		/*public static void main(String args[]){
			
			CibilService cibilService = new CibilService();
			
		  // System.out.println(cibilService.getMaxDelay("DBT    483    453    422    392    361    330    300    269    239    208    180    149    118    088    057    027    STD    ".split("    ")));
		}*/

}
