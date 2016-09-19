package com.lk.credittemplate.common.constants;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.lk.credittemplate.common.util.LKUtils;
import com.lk.credittemplate.common.util.PropUtil;


/**
 * 
 * @author ramamohan
 *
 */
public interface LKConstants {

	List<String> FINANCIAL_HEALTH_SCORE_VARS = Arrays.asList("Salesg12", "Salesg23", "LoanEligible",
			"Number of Months of Bank Statement", "InventorySalesRatio", "CIBIL1", "Salesavg3m", "Inventory",
			"Total Liability", "Average Cheque Returns", "CrDrR", "DrRatioN", "CashDeposit");

	List<String> MARKETPLACE_PERFORMANCE_SCORE_VARS = Arrays.asList("MarketAvgScore", "Offline Vintage",
			"Online Vintage", "No. of Employees", "Product Categories Count");

	List<String> SOCIAL_RELIABILITY_SCORE_VARS = Arrays.asList("OwnHouse 1", "Spouse Employment 1",
			"Number of Children 1", "ParentDp 1", "Reference", "EduQualification 1", "No of Facebook Friends",
			"Linkedin", "PastJob 1");

	List<String> STATUTORY_COMPLIANCE_VARS = Arrays.asList("Nature of Business", "Type of Organization", "VATn");

	Map<String, String> VARS_MAP = LKUtils.mapFromJson(PropUtil.VARS_RS_BUNDLE.getString("lk.vars.names"), String.class,
			String.class);
	
	String LK_SUB_STATUS_T_AND_C_SENT = "1f974f0c-62b3-11e5-988d-d6b2fad01022";
	String LK_STATUS_T_AND_C_UNDER_FINALIZATION = "1f974f0c-62b3-11e5-988d-d6b2fad01020";
	String LK_STATUS_ANALYSIS_IN_PROGRESS = "dd1affa8-62c0-11e5-988d-d6b2fad02237";
	String LK_STATUS_AGREEMENT_SENT = "1f974f0c-62b3-11e5-988d-d6b2fad01028";
	String LK_STATUS_APPLICATION_REJECTED = "280e5bda-62b3-11e5-988d-d6b2fad02237";
	String LK_STATUS_DISBURSED = "1f974f0c-62b3-11e5-988d-d6b2fad01037";
	List<String> LK_STATUS_LIST = Arrays.asList(LK_STATUS_ANALYSIS_IN_PROGRESS, LK_STATUS_AGREEMENT_SENT,
			LK_STATUS_APPLICATION_REJECTED, LK_STATUS_DISBURSED);
}
