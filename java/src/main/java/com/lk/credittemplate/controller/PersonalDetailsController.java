package com.lk.credittemplate.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lk.credittemplate.common.util.LKUtils;
import com.lk.credittemplate.service.ApprovalService;
import com.lk.credittemplate.service.CibilService;
import com.lk.credittemplate.service.DataService;
import com.lk.credittemplate.service.ExcelService;
import com.lk.credittemplate.service.ParseExcel;
import com.lk.credittemplate.service.PersonalDetailsService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lk.credittemplate.beans.MasterBean;
import com.lk.credittemplate.beans.PDSocialPresenceBean;
import com.lk.credittemplate.beans.PersonalCompanyBean;
import com.lk.credittemplate.beans.PersonalDetailsApplicantBean;
import com.lk.credittemplate.beans.PersonalDetailsApplicantInfoBean;
import com.lk.credittemplate.beans.PersonalDetailsBean;
import com.lk.credittemplate.beans.PersonalDetailsBusinessBean;
import com.lk.credittemplate.beans.PersonalDetailsCalculationBean;
import com.lk.credittemplate.beans.PersonalDetailsMarketBean;
import com.lk.credittemplate.beans.PersonalKYCBean;
import com.lk.credittemplate.beans.ResponseBean;
import org.springframework.beans.BeanUtils;
import java.lang.reflect.Type;
/**
 * Handles requests for the application home page.
 */
@Controller
public class PersonalDetailsController {

	@Autowired
	ExcelService excelService;

	@Autowired
	ParseExcel parseExcel;
	
	@Autowired
	DataService dataService;
	
	@Autowired
	ApprovalService approvalService;
	
	@Autowired
	CibilService cibilService;
	
	@Autowired
	PersonalDetailsService personalDetailsService;

	
	private static final Logger logger = LoggerFactory.getLogger(PersonalDetailsController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/application/getPersonalDetails", method = RequestMethod.POST)
	@ResponseBody
	public String getPersonaldetails(@RequestParam("appId") String appId, HttpServletRequest request) {
		logger.info("getCATdetails started");
		ResponseBean<MasterBean> responseBean = new ResponseBean<>();
		//personalDetailsService.saveAppId(appId);
		//PersonalDetailsBean personalDetailsBean = personalDetailsService.getPersonalDetails(appId);
		//PersonalCompanyBean personalCompanyBean=personalDetailsService.getCompanyDetails(appId);
		
		MasterBean masterResponseBean = personalDetailsService.getCatDetails(appId);
		
		if (masterResponseBean != null) {
			responseBean.setStatus("Success");
			responseBean.setData(masterResponseBean);
			logger.info(LKUtils.toJson(masterResponseBean));
			
		}
		

		logger.info("CATdetails Ended");
		return LKUtils.toJson(responseBean);

	}
	//batch 1
	@RequestMapping(value = "/application/savePersonalDetails/{loanId}", method = RequestMethod.POST)
	@ResponseBody
	public String saveOrUpdateLoandetails(@RequestBody PersonalDetailsBean loan,HttpServletRequest request,@PathVariable("loanId") String loanId) {
		logger.info("saveOrUpdatePersonaldetails started");
		PersonalDetailsBean loanUpdate = personalDetailsService.saveLoanDetails(loan,loanId);
		//saveOrUpdateCompanydetails(loan, request);
		logger.info(LKUtils.toJson(loanUpdate));
		logger.info("saveOrUpdateLoandetails Ended");

		return LKUtils.toJson(loanUpdate);

	}
	
	//batch 3
	@RequestMapping(value = "/application/saveCompanyDetails/{loanId}", method = RequestMethod.POST)
	@ResponseBody
	public String saveOrUpdateCompanydetails(@RequestBody PersonalCompanyBean loan, HttpServletRequest request,@PathVariable("loanId") String loanId) {
		logger.info("saveOrUpdateCompanydetails started");
		PersonalCompanyBean loanUpdate = personalDetailsService.saveCompanyDetails(loan,loanId);
		logger.info(LKUtils.toJson(loanUpdate));
		logger.info("saveOrUpdateCompanydetails Ended");

		return LKUtils.toJson(loanUpdate);

	}
	
//batch 4
	@RequestMapping(value = "/application/saveKYCDetails/{loanId}", method = RequestMethod.POST)
	@ResponseBody
	public String saveOrUpdateCompanydetails(@RequestBody PersonalKYCBean loan, HttpServletRequest request,@PathVariable("loanId") String loanId) {
		logger.info("saveOrUpdateKYCdetails started");
		PersonalKYCBean loanUpdate = personalDetailsService.saveKYCDetails(loan,loanId);
		logger.info(LKUtils.toJson(loanUpdate));
		logger.info("saveOrUpdateKYCdetails Ended");

		return LKUtils.toJson(loanUpdate);

	}
	
	@RequestMapping(value = "/application/saveMarketDetails/{loanId}", method = RequestMethod.POST)
	@ResponseBody
	public String saveOrUpdateBusinessdetails(@RequestBody List<PersonalDetailsMarketBean> loan, HttpServletRequest request,@PathVariable("loanId") String loanId) {
		logger.info("saveOrUpdateMarketdetails started");
		List<PersonalDetailsMarketBean> loanUpdate = personalDetailsService.saveMarketDetails(loan,loanId);
		logger.info(LKUtils.toJson(loanUpdate));
		logger.info("saveOrUpdateMarketdetails Ended");

		return LKUtils.toJson(loanUpdate);

	}
	//batch 7
//	
	@RequestMapping(value = "/application/saveSocialPresenceDetails/{loanId}", method = RequestMethod.POST)
	@ResponseBody
	public String saveOrUpdateSocialPresencedetails(@RequestBody List<PDSocialPresenceBean> loan, HttpServletRequest request,@PathVariable("loanId")String loanId) {
		logger.info("saveOrUpdateSocialPresencedetails started");
		List<PDSocialPresenceBean> loanUpdate = personalDetailsService.saveSocialDetails(loan, loanId);
		logger.info(LKUtils.toJson(loanUpdate));
		logger.info("saveOrUpdateSocialPresencedetails Ended");

		return LKUtils.toJson(loanUpdate);

	}
	
	//batch 8
	@RequestMapping(value = "/application/saveSocialApplicantDetails/{loanId}", method = RequestMethod.POST)
	@ResponseBody
	public String saveOrUpdateSocialApplicantdetails(@RequestBody List<PersonalDetailsApplicantBean> loan, HttpServletRequest request,@PathVariable("loanId")String loanId) {
		logger.info("saveOrUpdateSocialdetails started");
		List<PersonalDetailsApplicantBean> loanUpdate = personalDetailsService.saveSocialApplicantDetails(loan, loanId);
		logger.info(LKUtils.toJson(loanUpdate));
		logger.info("saveOrUpdateSocialdetails Ended");

		return LKUtils.toJson(loanUpdate);

	}
	
	//5th batch
	@RequestMapping(value = "/application/saveBusinessDetails/{loanId}", method = RequestMethod.POST)
	@ResponseBody
	public String saveOrUpdateBusinessdetails(@RequestBody PersonalDetailsBusinessBean loan, HttpServletRequest request,@PathVariable("loanId")String loanId) {
		logger.info("saveOrUpdateBusinessdetails started");
		PersonalDetailsBusinessBean loanUpdate = personalDetailsService.saveBusinessDetails(loan, loanId);
		logger.info(LKUtils.toJson(loanUpdate));
		logger.info("saveOrUpdateBusinessdetails Ended");

		return LKUtils.toJson(loanUpdate);

	}
	//batch 6
	@RequestMapping(value = "/application/saveApplicantInfo/{loanId}", method = RequestMethod.POST)
	@ResponseBody
	public String saveOrUpdateApplicantInfo(@RequestBody List<PersonalDetailsApplicantInfoBean> loan, HttpServletRequest request,String loanId) {
		logger.info("saveOrUpdatApplicantInfo started");
		List<PersonalDetailsApplicantInfoBean> loanUpdate = personalDetailsService.saveApplicantInfo(loan, loanId);
		logger.info(LKUtils.toJson(loanUpdate));
		logger.info("saveOrUpdateApplicantInfo Ended");

		return LKUtils.toJson(loanUpdate);

	}
    
	//batch 2
	@RequestMapping(value = "/application/getCalculation/{loanId}", method = RequestMethod.GET)
	@ResponseBody
	public String getCalculation(HttpServletRequest request,@PathVariable("loanId")String loanId) {
		logger.info("getting Calculation started");
		List<PersonalDetailsCalculationBean> loanUpdate = personalDetailsService.getPersonalCalDetails(loanId);
		logger.info("getCalculation Ended");
		return LKUtils.toJson(loanUpdate);

	}
    
	
	@RequestMapping(value = "/application/saveCalculation/{loanId}", method = RequestMethod.POST)
	@ResponseBody
	public String saveCalculation(@PathVariable("loanId")String loanId,@RequestBody String jsondata,HttpServletRequest request) {
		logger.info("getting Calculation started");
		Gson gson = new Gson();
		Type listType = new TypeToken<ArrayList<PersonalDetailsCalculationBean>>(){}.getType();
		List<PersonalDetailsCalculationBean> loanCycles = gson.fromJson(jsondata,listType);
		Map<String,List<PersonalDetailsCalculationBean> >  loanUpdate = personalDetailsService.savePersonalCalDetails(loanCycles,loanId);
		logger.info(LKUtils.toJson(loanUpdate));
		logger.info("savePersonalCalDetails Ended");
		return LKUtils.toJson(loanUpdate);

	}
    
    

	
}
