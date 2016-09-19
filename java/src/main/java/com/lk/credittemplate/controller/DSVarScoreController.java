package com.lk.credittemplate.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lk.credittemplate.beans.ds.LoanScoreBean;
import com.lk.credittemplate.beans.ds.LoanVarScoreBean;
import com.lk.credittemplate.beans.ds.LoanVarScoreResp;
import com.lk.credittemplate.beans.ds.RespDataBean;
import com.lk.credittemplate.beans.ds.VarScoreBean;
import com.lk.credittemplate.common.util.LKUtils;
import com.lk.credittemplate.model.ds.LoanVarScoreModel;
import com.lk.credittemplate.service.ds.IVarScoreService;



@Controller
@RequestMapping(value = "/ds")
public class DSVarScoreController {

	@Autowired
	private IVarScoreService varScoreService;

	private static final Logger logger = LoggerFactory.getLogger(DSVarScoreController.class);

	@RequestMapping(value = "/getLoanVarScore", method = RequestMethod.GET)
	@ResponseBody
	public String getLoanVarScoreData(@RequestParam("applicationId") String loanApplicationId) {
		logger.info("********** loanApplicationId :: " + loanApplicationId);
		System.out.println("********** loanApplicationId :: " + loanApplicationId);
		LoanVarScoreResp loanVarScoreResp = new LoanVarScoreResp();
		loanVarScoreResp.setStatus("Success");
		try {
			List<LoanVarScoreBean> loanVarScores = varScoreService.getLoanVarScores(loanApplicationId);
			LoanScoreBean loanScores = varScoreService.getLoanScores(loanApplicationId);
			loanVarScoreResp.setMessage(loanVarScores.isEmpty() ? "No Data Found" : "Success");
			RespDataBean respDataBean = new RespDataBean();
			respDataBean.setLoanVarScores(loanVarScores);
			respDataBean.setLoanScores(loanScores);
			loanVarScoreResp.setData(respDataBean);
		} catch (Exception ex) {
			loanVarScoreResp.setStatus("Error");
			loanVarScoreResp.setMessage("Error While Proccessing! Please try again.....");
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}
		System.out.println(LKUtils.toJson(loanVarScoreResp));
		return LKUtils.toJson(loanVarScoreResp);
	}

	@RequestMapping(value = "/saveVarScore", method = RequestMethod.POST)
	@ResponseBody
	public String saveVarScore(@RequestBody VarScoreBean varScoreBean) {
		List<VarScoreBean> varScoreBeans = new ArrayList<VarScoreBean>();
		varScoreBeans.add(varScoreBean);
		varScoreService.saveVariables(varScoreBeans);
		return "success";
	}

	@RequestMapping(value = "/calculateVarScores", method = RequestMethod.GET)
	@ResponseBody
	public List<LoanVarScoreModel> calculateVarScores(@RequestParam("applicationId") String loanApplicationId) {
		List<LoanVarScoreModel> loanVarScores = new ArrayList<LoanVarScoreModel>();
		try {
			loanVarScores = varScoreService.calculateVarScores(loanApplicationId);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}

		return loanVarScores;
	}

	@RequestMapping(value = "/getLoanAppIds", method = RequestMethod.GET)
	@ResponseBody
	public String getLoanAppIds() {
		List<String> loanAppIds = varScoreService.getLoanAppIds();
		return LKUtils.toJson(loanAppIds);
	}

	@RequestMapping(value = "/saveLoanScores", method = RequestMethod.POST)
	@ResponseBody
	public String saveLoanScores(@RequestBody LoanScoreBean loanScoreBean) {
		LoanScoreBean loanScores = varScoreService.saveLoanScores(loanScoreBean);
		return LKUtils.toJson(loanScores);
	}

	@RequestMapping(value = "/getLKScore", method = RequestMethod.GET)
	@ResponseBody
	public String getLKScore(@RequestParam("applicationId") String loanApplicationId) {
		String score = "0";
		try {
			score = varScoreService.getLoanScore(loanApplicationId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return score;
	}
}
