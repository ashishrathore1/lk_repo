package com.lk.credittemplate.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.NonUniqueResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lk.credittemplate.beans.DoubleValExecSummBean;
import com.lk.credittemplate.beans.EditTableBean;
import com.lk.credittemplate.beans.ExcelColumn;
import com.lk.credittemplate.beans.ExcelTableCoordBean;
import com.lk.credittemplate.beans.MultipleValBean;
import com.lk.credittemplate.beans.NewExecSummBean;
import com.lk.credittemplate.beans.RequestTableBean;
import com.lk.credittemplate.beans.ResultBean;
import com.lk.credittemplate.beans.ResultKeyBean;
import com.lk.credittemplate.beans.SingleValExecSummBean;
import com.lk.credittemplate.beans.TmpExcelDTO;
import com.lk.credittemplate.cibilmodel.LoanMaster;
import com.lk.credittemplate.model.AsigneeApps;
import com.lk.credittemplate.model.Audit;
import com.lk.credittemplate.model.ExcelTableCoord;
import com.lk.credittemplate.model.ExcelTableData;
import com.lk.credittemplate.model.LoanStatusMaster;
import com.lk.credittemplate.model.MasterExcel;
import com.lk.credittemplate.model.NewExecSumm;
import com.lk.credittemplate.model.UserMaster;
import com.lk.credittemplate.model.Variables;
import com.lk.credittemplate.service.ApprovalService;
import com.lk.credittemplate.service.CibilService;
import com.lk.credittemplate.service.DataService;
import com.lk.credittemplate.service.ExcelService;
import com.lk.credittemplate.service.ParseExcel;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ExcelController {

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

	
	private static final Logger logger = LoggerFactory.getLogger(ExcelController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String uploadExcel() {
		return "excelUpload";
	}

	@RequestMapping(value = "/savecibildetails/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String cibilDetails(@PathVariable("id") String appId) {
		String json, str = "";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		LoanMaster loanMaster = dataService.getApplicationByGuid(appId);
		String loanId = loanMaster.getLoanMastertId();
		String applicationId=loanMaster.getApplicationId();
		try {
			cibilService.saveCibilData(appId,loanMaster.getLoanMastertId());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		str = "success";
		json = gson.toJson("success");
		return gson.toJson(str);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String viewCreditTemplate(@PathVariable("id") String appId, @RequestParam("role") String uid,
			Map<String, Object> model) {

		String loanId;
		boolean showUpload = false;
		
		MasterExcel masterExcel = excelService.findByMasterExcelId(appId);
		if (masterExcel == null) {
			return "error";
		}
		 else {

			 try {
			LoanMaster loanMaster = dataService.getApplicationByAppId(appId);
			loanId = loanMaster.getLoanMastertId();
			UserMaster userMaster = dataService.getByUserId(uid);
			try {
				cibilService.saveCibilData(appId,loanMaster.getLoanMastertId());
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			
			Audit audit = excelService.findSubmit(loanId);
		    boolean blocked = true;
			
			AsigneeApps asigneeApps = approvalService.findAssignedAppById(loanId);
			
			if (asigneeApps != null && asigneeApps.getUserMaster() != null){  
				String roleName = asigneeApps.getUserMaster().getRoleMaster().getName(); 
				
				if (asigneeApps.getUserMaster().getUserId().equals(uid)) {
					blocked = false;
					
					if(roleName.equals("Credit Analyst")){
					showUpload = true;
					blocked = true;
					}
					
				}
				model.put("rolename", roleName);
			}
			else{
				if(audit != null)
				{
				showUpload = !audit.isSubmitFlag();
				}
				
			}
			if(loanMaster.getStatusId().equals("1f974f0c-62b3-11e5-988d-d6b2fad01020")){
				
				if(uid.equals("7feaca8d-ad6e-11e5-8af7-0290fb34887d") || uid.equals("10821c59-ba82-11e5-8af7-0290fb34887d")){
					blocked = false;
				}
			}
			
			model.put("loanId", loanId);
			model.put("vid", masterExcel.getVersionId());
			model.put("uId", uid);
			model.put("appId", appId);
			model.put("blocked", blocked);
			model.put("uName", userMaster.getName());
			model.put("showUpload",showUpload);
			
			return "credit";
			
			
		} catch (Exception e) {
				e.printStackTrace();
				logger.error("Failed",e);
				return "error";
			}
		}

	}

	@RequestMapping(value = "/uploadCreditTemplate/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String creditTemplate(@PathVariable("id") String appId, @RequestParam("role") String uid,
			Map<String, Object> model) {
		String json;
		String loanId, str = "";
		//int n=0;

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		try{
		
		String saveDirectory = "/opt/CTExcels/";
		//String saveDirectory = "/D:/Mails/";
		LoanMaster loanMaster = dataService.getApplicationByAppId(appId);
		MasterExcel masterExcel = excelService.findByMasterExcelId(appId);

		if (masterExcel == null) {
			String vid = excelService.findMaxVid();
			masterExcel = new MasterExcel();
			masterExcel.setAppId(appId);
			masterExcel.setVersionId(vid);
			masterExcel.setSaveflag(false);
			masterExcel = excelService.saveMasterData(masterExcel);
		}
		else{
			if(!masterExcel.isSaveflag())//Template is not uploaded
				{
						Double vidNumber = Double.parseDouble(masterExcel.getVersionId());
						String excelFileName = "nhp_CreditAnalysisTemplate" + ".xlsm";
						String excelFilePath = saveDirectory + excelFileName;
						logger.info(excelFilePath);
						UserMaster uploadPerson = dataService.getByUserId(uid);
						loanId = loanMaster.getLoanMastertId();
						loanMaster.setAssignedTo(uploadPerson.getName());
						Workbook wb = WorkbookFactory.create(new File(excelFilePath));
						Sheet executiveSumm = wb.getSheetAt(0);
						List<Variables> variablesList = new ArrayList<Variables>();
						if (vidNumber >= 4.6) {
							parseExcel.parseExcelSave(executiveSumm, masterExcel.getVersionId(), masterExcel);
							masterExcel.setSaveflag(true);
					    }
						
						Sheet variables = wb.getSheetAt(2);
						if (vidNumber >= 4.6) {
						
							parseExcel.parseVariables(variables, variablesList, masterExcel);
							
						}
						
						Audit audit = new Audit();
						audit.setAppId(loanId);
						audit.setSubmitFlag(false);
						audit.setUserMaster(uploadPerson);
						audit.setUploadTime(Calendar.getInstance().getTime());
						List<ExcelTableData> execSummHeadList = excelService.getByTableNameAndMId("execSummHeaders", masterExcel);
						ExcelTableData nhp = execSummHeadList.get(1);
						nhp.setVal(loanMaster.getCompanyName());
						

		                excelService.saveTableDataObj(nhp);
						dataService.commitInitialUpload(audit, loanMaster);
						try {
							cibilService.saveCibilData(appId,loanMaster.getLoanMastertId());
						} catch (Exception e) {
							logger.error(e.getMessage());
						}
				}
			
			} 
			str = "success";
			json = gson.toJson("success");
			excelService.updateSaveFlag(masterExcel);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Failed", e);
			str = "error";
		}

	return gson.toJson(str);
	}
	

	@RequestMapping(value = "/uploadCreditExcel", method = RequestMethod.POST)
	public String w0w(MultipartHttpServletRequest request, HttpServletRequest requestForm, Model map,
			RedirectAttributes redir) {
		logger.info("ExcelController uploadCreditExcel START.......");
		Iterator<String> itr = request.getFileNames();
		String saveDirectory = "/opt/CTExcels/";
		//String saveDirectory = "/D:/Mails/";
		MultipartFile mpf = null;
		String appId = requestForm.getParameter("appId");
		String uploadPersonId = requestForm.getParameter("uid");

		String loanId;

			String excelFileName = appId + "_credit_template" + ".xlsm";
			File file = new File(saveDirectory);
			if (!file.exists()) {
				if (file.mkdir()) {
					logger.info("Directory is created!");
				} else {
					logger.info("Failed to create directory!");
					return "uploaderror";
				}
			}
			while (itr.hasNext()) {
				mpf = request.getFile(itr.next());
				String orgFileName = mpf.getOriginalFilename();
				if (!"".equalsIgnoreCase(orgFileName)) {
					if (orgFileName.indexOf(".xlsm") != -1) {
						try {
							logger.info(mpf.getOriginalFilename() + " uploaded! ");
							FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(saveDirectory + excelFileName));
						} catch (IOException e) {
							logger.error("Failed",e);
						}
					} else {
						logger.error("Not xlsm");
						return "error";
					}

				}
			}

			logger.info("File uploaded");
			try {
				
				UserMaster uploadPerson = dataService.getByUserId(uploadPersonId);

				LoanMaster loanMaster = dataService.getApplicationByAppId(appId);
				loanId = loanMaster.getLoanMastertId();
				loanMaster.setAssignedTo(uploadPerson.getName());
				
				String excelFilePath = saveDirectory + excelFileName;
				Workbook wb = WorkbookFactory.create(new File(excelFilePath));
				Sheet executiveSumm = wb.getSheetAt(0);
				Sheet analysis = wb.getSheetAt(1);

				MasterExcel masterExcel = excelService.findByMasterExcelId(appId);
				
				Double vidNumber = Double.parseDouble(masterExcel.getVersionId());
				
				List<Variables> variablesList = new ArrayList<Variables>();

				if (vidNumber >= 4.6) {
					logger.info("UploadCreditExcel Saving Parse excel tables Update .......");
					parseExcel.parseExcelTablesUpdate(analysis, executiveSumm, masterExcel.getVersionId(), masterExcel);
				}

				Sheet variables = wb.getSheetAt(2);

				if(vidNumber >= 4.6){
					logger.info("UploadCreditExcel Saving Parse variables .......");
					parseExcel.parseVariables(variables, variablesList, masterExcel);
				}
				Audit audit = new Audit();

				audit.setAppId(loanId);
				audit.setSubmitFlag(false);
				audit.setUserMaster(uploadPerson);
				audit.setUploadTime(Calendar.getInstance().getTime());
				
				logger.info("UploadCreditExcel Saving Initial Upload data .......");
				dataService.commitInitialUpload(audit,loanMaster);
				try {
					logger.info("UploadCreditExcel Saving Cibil data .......");
					cibilService.saveCibilData(appId,loanId);
				} catch (Exception e) {
					logger.error("Duplicate cibil record : ",e);
				}
				

				//cibilService.saveCibilData(appId,loanId);

				logger.info("ExcelController uploadCreditExcel END.......");
				map.addAttribute("appId",appId);
				map.addAttribute("uploadPersonId",uploadPersonId);
				return "creditUploadSuccess";
				
			} catch (NonUniqueResultException e) {
				logger.info("ExcelController uploadCreditExcel GG Duplicate ApplicationId");
				return "error";
			} catch (Exception e) {
				logger.error("ExcelController uploadCreditExcel Failed",e);
				e.printStackTrace();
				return "error";
			}
			
	}

	@RequestMapping(value = "/vidError", method = RequestMethod.GET)
	public String versionIdError() {
		return "vidError";
	}

	@RequestMapping(value = "/downloadExcel/{id}", method = RequestMethod.GET)
	public void downloadExcel(HttpServletResponse response, HttpServletRequest request,
			@PathVariable("id") String appId) {

		String path = "/opt/CTExcels/"; // required in production server
		//String path = "/D:/Mails/";
		File file = new File(path + File.separator + appId + "_credit_template.xlsm");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			response.setContentType(file.getName());
			response.setHeader("Content-disposition", "attachment; filename=\"" + file.getName() + "\"");
			FileCopyUtils.copy(IOUtils.toByteArray(fis), response.getOutputStream());
		} catch (IOException e) {
			logger.error("Failed",e);
		}

	}

	@RequestMapping(value = "/tabledata", method = RequestMethod.GET)
	@ResponseBody
	public String getData(@RequestParam("appId") String appId, @RequestParam("tablename") String tablename) {
		MasterExcel masterExcel = excelService.findByMasterExcelId(appId);
		String json2;
		List<ExcelTableData> tableData = excelService.getByTableNameAndMId(tablename, masterExcel);
		if (tableData.size() > 0) {
			List<ResultBean> resultList = new ArrayList<ResultBean>();
			List<ExcelColumn> columnList = new ArrayList<ExcelColumn>();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			// String json2;
			for (ExcelTableData excelTableData : tableData) {
				if (excelTableData.getVal().equals("#!D!#")) {
					ExcelColumn excelColumn = new ExcelColumn();
					excelColumn.setResultList(resultList);
					columnList.add(excelColumn);
					resultList = new ArrayList<ResultBean>();
				} else {
					ResultBean resultBean = new ResultBean();
					resultBean.setVal(excelTableData.getVal());
					resultList.add(resultBean);
				}
			}
			json2 = gson.toJson(columnList);

			return json2;
		} else {
			json2 = "#!D!#";
			return json2;
		}

	}

	@RequestMapping(value = "/lkcredit", method = RequestMethod.GET)
	public String lkcredit() {
		return "lkcredit";
	}

	@RequestMapping(value = "/admincoordinates", method = RequestMethod.GET)
	public String admincoordinates() {
		return "admincoordinates";
	}

	@RequestMapping(value = "/savecoordinates", method = RequestMethod.POST)
	public String saveCoordinates(@RequestBody RequestTableBean requestTableBean) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String json = gson.toJson(requestTableBean.getTabledata());

		List<ExcelTableCoordBean> tableList = requestTableBean.getTabledata();

		List<ExcelTableCoord> resultList = new ArrayList<ExcelTableCoord>();

		for (ExcelTableCoordBean excelTableCoordBean : tableList) {
			
			if (excelTableCoordBean.getXcord().isEmpty() || excelTableCoordBean.getYcord().isEmpty()) {
				ExcelTableCoord excelTableCoord = new ExcelTableCoord();
				excelTableCoord.setTableName(excelTableCoordBean.getTableName());
				excelTableCoord.setVid(excelTableCoordBean.getVersionId());
				excelTableCoord.setXcord(excelTableCoordBean.getXcord());
				excelTableCoord.setYcord(excelTableCoordBean.getYcord());
				excelTableCoord.setCreated((Calendar.getInstance().getTime()));
				resultList.add(excelTableCoord);
			}
		}

		return "waa";

	}

	@RequestMapping(value = "/variables/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getVariables(@PathVariable("id") String appId, HttpServletRequest request) {

		MasterExcel masterExcel = excelService.findByMasterExcelId(appId);
		List<ResultKeyBean> resultList = new ArrayList<ResultKeyBean>();
		List<Variables> varaiablesList = excelService.findByMasterIdVariables(masterExcel);
		for (Variables variables : varaiablesList) {
			ResultKeyBean resultkeyBean = new ResultKeyBean();
			resultkeyBean.setVal(variables.getVal());
			resultkeyBean.setKey(variables.getKey());
			resultList.add(resultkeyBean);
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(resultList);
		return json2;

	}

	@RequestMapping(value = "/appidpresent/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String checkIfPresent(@PathVariable("id") String appId, HttpServletRequest request) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = "";
		MasterExcel masterExcel = excelService.findByMasterExcelId(appId);
		if(masterExcel==null)
		{
			String vid = excelService.findMaxVid();
			masterExcel = new MasterExcel();
			masterExcel.setAppId(appId);
		    masterExcel.setVersionId(vid);
			masterExcel.setSaveflag(false);
			masterExcel.setCreated((Calendar.getInstance().getTime()));
			masterExcel = excelService.saveMasterData(masterExcel);
		}
		
		if (masterExcel.isSaveflag() == false)
		{
			json = gson.toJson("nhp");
		} else {
			json = gson.toJson("go");
		}

		return json;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public @ResponseBody String editTable(@RequestBody EditTableBean editTableBean) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		//System.out.println("Your Data :" + editTableBean.getResultList().size());
		//System.out.println("Your Data :" + gson.toJson(editTableBean.getResultList()));

		MasterExcel masterExcel = excelService.findByMasterExcelId(editTableBean.getAppId());
		if (masterExcel == null) {

			return "Not able to Save Invalid AppId";

		} else {
			try {

				List<ExcelTableData> tableData = excelService.getByTableNameAndMId(editTableBean.getTableName(),
						masterExcel);

				//System.out.println("old" + tableData.size());

				List<ExcelTableData> tableDataList = new ArrayList<ExcelTableData>();
				ExcelTableData excelTableData;
				int i;
				for (i = 0; i < tableData.size(); i++) {
					if (tableData.get(i).getVal().equals("#!D!#")) {
						tableDataList.add(tableData.get(i));
						break;
					} else {
						tableDataList.add(tableData.get(i));
					}
				}

				int oldIndex = ++i;
				//System.out.println("Headers" + oldIndex);
				int newIndex = 0;
				int multiplier = 2;
				int counter = oldIndex * (multiplier++) - 1;

				for (int j = oldIndex; j < tableData.size(); j++) {
					excelTableData = tableData.get(j);
					if (j == counter) {
						tableDataList.add(excelTableData);
						counter = oldIndex * (multiplier++) - 1;
					} else {
						excelTableData.setVal(editTableBean.getResultList().get(newIndex++).getVal());
						tableDataList.add(excelTableData);
					}
				}

				excelService.updateTableData(tableDataList);
			} catch (Exception e) {
				logger.error("Failed",e);
				//System.out.println("Error Occured");
				return "Not able to Save TableName Incorrect";
			}
		}

		return "Saved";
	}

	@RequestMapping(value = "/getnewExecSumm/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getNewExecSumm(@PathVariable("id") String appId, HttpServletRequest request) {

		MasterExcel masterExcel = excelService.findByMasterExcelId(appId);
		String singleval[] = {"ANALYTICS","REVENUE","FINANCIAL DISCIPLINE","CIBIL"
				,"VINTAGE, STATUTORY COMPLIANCE & DEFAULT STATUS","WORKING CAPITAL CYCLE",
				"PURPOSE"};
		
		String doubleVal[] = {"LOAN ELIGIBILTY","CMA","RATIOS"};
		List<SingleValExecSummBean> singleValList = new ArrayList<SingleValExecSummBean>();
		List<DoubleValExecSummBean> doubleValList = new ArrayList<DoubleValExecSummBean>();
		
		for(String table:singleval){
			List<NewExecSumm> execSummList = excelService.getExcelSummData(masterExcel,table);
			List<ResultKeyBean> resultList = new ArrayList<ResultKeyBean>();
			for(NewExecSumm newExecSumm: execSummList){
				ResultKeyBean resultKeyBean = new ResultKeyBean();
				resultKeyBean.setKey(newExecSumm.getKey());
				resultKeyBean.setVal(newExecSumm.getVal1());
				resultList.add(resultKeyBean);
			}
			SingleValExecSummBean singleValExecSummBean = new SingleValExecSummBean();
			singleValExecSummBean.setResultList(resultList);
			singleValExecSummBean.settName(table);
			singleValList.add(singleValExecSummBean);

		}
		
		for(String table:doubleVal){
			List<NewExecSumm> execSummList = excelService.getExcelSummData(masterExcel,table);
			List<MultipleValBean> resultList = new ArrayList<MultipleValBean>();
			for(NewExecSumm newExecSumm: execSummList){
				MultipleValBean multipleValBean = new MultipleValBean();
				multipleValBean.setKey(newExecSumm.getKey());
				multipleValBean.setVal1(newExecSumm.getVal1());
				multipleValBean.setVal2(newExecSumm.getVal2());
				resultList.add(multipleValBean);
			}
			DoubleValExecSummBean doubleValExecSummBean = new DoubleValExecSummBean(); 
			doubleValExecSummBean.setResultList(resultList);
			doubleValExecSummBean.settName(table);
			doubleValList.add(doubleValExecSummBean);
		}
		NewExecSummBean newExecSummBean = new NewExecSummBean();
		
		newExecSummBean.setDoubleValList(doubleValList);
		newExecSummBean.setSingleValList(singleValList);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(newExecSummBean);	
		
		
		return json2;

	}
	
	@RequestMapping(value = "/editCard", method = RequestMethod.POST)
	public @ResponseBody String editCardTable(@RequestBody EditTableBean editTableBean) {

		MasterExcel masterExcel = excelService.findByMasterExcelId(editTableBean.getAppId());
		if (masterExcel == null) {

			return "Not able to Save Invalid AppId";

		} else {
			try {

				List<ExcelTableData> tableData = excelService.getByTableNameAndMId(editTableBean.getTableName(),
						masterExcel);
				int noofrows = editTableBean.getPos() / 2 - 1;
				int valPos = editTableBean.getPos() + noofrows - 1;
				ExcelTableData excelTableData = tableData.get(valPos);
				excelTableData.setVal(editTableBean.getVal());
				excelService.saveTableDataObj(excelTableData);
			} catch (Exception e) {
				logger.error("Failed",e);
				//System.out.println("Error Occured");
				return "Not able to Save TableName Incorrect";
			}
		}

		return "Saved";
	}

	
	@RequestMapping(value = "/tableList", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getTable(@RequestParam("vid") String vid, HttpServletRequest request) {

		return excelService.findSimpleTableCoordByVID(vid);

	}
	
	@RequestMapping(value = "/generateExcel", method = RequestMethod.GET)
	public String getExcel(Map<String, Object> adminUserDetailModel) {

		try{
		String saveDirectory = "/opt/Book2.xlsx";
		Workbook wb = WorkbookFactory.create(new File(saveDirectory));
		Sheet sheet = wb.getSheetAt(0);
		
		String str;
		Cell cell;

		Iterator<Row> iterator = sheet.iterator();
		List<TmpExcelDTO> tmpExcelDTOList = new ArrayList<TmpExcelDTO>(); 
		
		int i = 1;
		Row nextRow;
		nextRow = iterator.next();
		while (iterator.hasNext()) {
			
			System.out.println(i++);
			
			if(i<1000){
			
			nextRow = iterator.next();
			
			TmpExcelDTO tmpExcelDTO = new TmpExcelDTO();
			cell = nextRow.getCell(1);
			
			str = ParseExcel.parseCell(cell);
			MasterExcel  masterexcel = excelService.findByMasterExcelId(str);
			
			List<ExcelTableData> excelTableDataList = excelService.getByTableNameAndMId("PersonalDetails",masterexcel);
			
			//if(masterexcel.getVersionId().equals("4.4") ){
			
			tmpExcelDTO.setAppId(str);
			
			if(excelTableDataList.size() > 0){
				
			tmpExcelDTO.setContactDetails(excelTableDataList.get(7).getVal());
			
			if(excelTableDataList.size()<105){
				
				tmpExcelDTO.setEmail(excelTableDataList.get(43).getVal());
				tmpExcelDTO.setState(excelTableDataList.get(37).getVal());
			}
			else{
			
			tmpExcelDTO.setEmail(excelTableDataList.get(40).getVal());
			}
			}else{
				
				tmpExcelDTO.setContactDetails("No Data");
				tmpExcelDTO.setEmail("No Data");
			}
				
			tmpExcelDTOList.add(tmpExcelDTO);
			
			}
			else{
				break;
			}
		  }
		//String excelFilePath = "/opt/NiceJavaBooks.xls";
		 
		//writeExcel(tmpExcelDTOList, excelFilePath);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		return "Done";
		
		
	}
	
	public void writeExcel(List<TmpExcelDTO> tmpExcelDTOList, String excelFilePath) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
	    
		Sheet sheet = workbook.createSheet();
	 
	    int rowCount = 0;
	 
	    Row row = sheet.createRow(0);
	    
	    row.createCell(0).setCellValue("Index");
	    row.createCell(1).setCellValue("Application-Id");
	    row.createCell(2).setCellValue("Contact Details");
	    row.createCell(3).setCellValue("Email");
	    row.createCell(4).setCellValue("State(Only for 4.6)");
	    
	    for (TmpExcelDTO tmpExcelDTO : tmpExcelDTOList) {
	         row = sheet.createRow(++rowCount);
	        writeBook(tmpExcelDTO, row,rowCount);
	    }
	 
	    try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
	        workbook.write(outputStream);
	    }
	}
	
	private void writeBook(TmpExcelDTO tmpExcelDTO, Row row,int cnt) {
	    
		Cell cell = row.createCell(0);
	    cell.setCellValue(cnt);
	    
	    cell = row.createCell(1);
	    cell.setCellValue(tmpExcelDTO.getAppId());
	 
	    cell = row.createCell(2);
	    cell.setCellValue(tmpExcelDTO.getContactDetails());
	 
	    cell = row.createCell(3);
	    cell.setCellValue(tmpExcelDTO.getEmail());
	    
	    cell = row.createCell(4);
	    cell.setCellValue(tmpExcelDTO.getState());
	    
	}
	
	@RequestMapping(value = "/saveStatus", method = RequestMethod.GET)
	public String saveStatus(Map<String, Object> adminUserDetailModel) {

		try{
			
		String saveDirectory = "/opt/Data.xlsx";
		Workbook wb = WorkbookFactory.create(new File(saveDirectory));
		Sheet sheet = wb.getSheetAt(0);
		
		Cell cell;

		Iterator<Row> iterator = sheet.iterator();
		
		int i = 1;
		Row nextRow;
		nextRow = iterator.next();
		while (iterator.hasNext()) {
			
			nextRow = iterator.next();
			System.out.println(i++);
			
			if(i<=10561){
				
			}
			else{
				
			cell = nextRow.getCell(0);
			
			String appId = ParseExcel.parseCell(cell);
			
			cell = nextRow.getCell(2);
			
			String statusName = ParseExcel.parseCell(cell);
			
			cell = nextRow.getCell(3);
			
			System.out.println("App-Id"+appId);
			
			String subStatusName = ParseExcel.parseCell(cell);
			
			LoanMaster loanMaster = dataService.getApplicationByAppId(appId);
			
			LoanStatusMaster loanStatusMaster = dataService.findByStatusId(statusName);
			
			LoanStatusMaster loanStatusMasterSub = dataService.findByStatusId(subStatusName);
			
			loanMaster.setStatusId(loanStatusMaster.getLoanStatustId());
			loanMaster.setSubStatus(loanStatusMasterSub.getLoanStatustId());
			
			
			dataService.saveLoan(loanMaster);
			
			}
			
		  }
		 
    	}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return "Done";
		
		
	}

	
}
