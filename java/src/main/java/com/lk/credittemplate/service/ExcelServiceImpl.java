package com.lk.credittemplate.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lk.credittemplate.beans.DirectorMasterData;
import com.lk.credittemplate.beans.ExcelTableCoordBean;
import com.lk.credittemplate.cibildao.DirectorMasterDao;
import com.lk.credittemplate.cibilmodel.DirectorMaster;
import com.lk.credittemplate.dao.AuditDao;
import com.lk.credittemplate.dao.ExcelTableCoordDao;
import com.lk.credittemplate.dao.ExcelTableDataDao;
import com.lk.credittemplate.dao.MasterExcelDao;
import com.lk.credittemplate.dao.NewExecSummDao;
import com.lk.credittemplate.dao.VaraiblesDao;
import com.lk.credittemplate.model.Audit;
import com.lk.credittemplate.model.ExcelTableCoord;
import com.lk.credittemplate.model.ExcelTableData;
import com.lk.credittemplate.model.MasterExcel;
import com.lk.credittemplate.model.NewExecSumm;
import com.lk.credittemplate.model.Variables;


@Service
@Transactional
public class ExcelServiceImpl implements ExcelService {

	@Autowired
	VaraiblesDao variablesDao;

	@Autowired
	MasterExcelDao masterExcelDao;

	@Autowired
	DirectorMasterDao directorMasterDao;

	@Autowired
	ExcelTableDataDao excelTableDataDao;

	@Autowired
	ExcelTableCoordDao excelTableCoordDao;

	@Autowired
	AuditDao auditDao;
	
	@Autowired
	NewExecSummDao newExecSummDao;

	private static final Map<String, Integer> tableDivision = new HashMap<String, Integer>();

	@Override
	public MasterExcel saveMasterData(MasterExcel masterExcel) {
		return masterExcelDao.merge(masterExcel);
	}

	@Override
	public MasterExcel findByMasterExcelId(String appId) {
		List<MasterExcel> result = masterExcelDao.findByAppId(appId).getResultList();
		if (result.size() == 0) {
			return null;
		} else {
			return masterExcelDao.findByAppId(appId).getSingleResult();
		}
	}

	@Override
	public List<Variables> findByMasterIdVariables(MasterExcel masterExcel) {
		return variablesDao.findByMIdVar(masterExcel);
	}

	@Override
	public void saveVariablesData(List<Variables> variableNewList) {
		variablesDao.batchSaveExcel(variableNewList);
	}

	//@Override
	/*public LKCibilResponse getCibilData(String id) {

		List<LKCibilResponse> results = cibilRespnseDao.findCibilDetailsById(id).getResultList();
		if (results.size() > 0) {
			return cibilRespnseDao.findCibilDetailsById(id).getSingleResult();
		} else {
			return null;
		}
	}*/

	
	  
	  /*@Override public LoanMaster getLoanMasterDataBYClsID(String appId) {
      if(loanMasterDao.findLoanMasterDataByClsID(appId)!=null) { return
	  loanMasterDao.findLoanMasterDataByClsID(appId); } else{ return null; } }*/
	 

	@Override
	public String getDirectorMasterDataByAppId(String appId) {
		// ArrayList<DirectorMaster> list= new
		// ArrayList<DirectorMaster>(directorMasterDao.findDirectorMasterDataByAppID(appId));
		/*
		 * if(results.size()>0) { return results; } else { return null; }
		 */
		String json = null, str = " ";
		try {
			List<DirectorMaster> list = new ArrayList<DirectorMaster>(
					directorMasterDao.findDirectorMasterDataByAppID(appId));
			if (list.size() > 0) {
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				DirectorMasterData directorMasterData = new DirectorMasterData();
				// for(DirectorMaster directorMaster : list)
				for (int i = 0; i < list.size(); i++) {
					// System.out.println(list.get(i).setaCity(aCity););
					directorMasterData.setdGuid(list.get(i).getdGuid());
					directorMasterData.setdName(list.get(i).getdName());
					directorMasterData.setdMobile(list.get(i).getdMobile());
					directorMasterData.setdDateOfBirth(String.valueOf(list.get(i).getdDateOfBirth()));
					directorMasterData.setdIncome(list.get(i).getdIncome());
					directorMasterData.setdPan(list.get(i).getdPan());
					directorMasterData.setdEducation(list.get(i).getdEducation());
					directorMasterData.setdEducationOthers(list.get(i).getdEducationOthers());
					directorMasterData.setdEmail(list.get(i).getdEmail());
					directorMasterData.setDTabName(list.get(i).getDTabName());
					directorMasterData.setdFamilyDetails(list.get(i).getdFamilyDetails());
					directorMasterData.setdSpouseOccupation(list.get(i).getdSpouseOccupation());
					directorMasterData.setdNoOfChildren(list.get(i).getdNoOfChildren());
					directorMasterData.setdSameAsBussAddress(String.valueOf(list.get(i).getdSameAsBussAddress()));
					directorMasterData.setdResidenceAddress(list.get(i).getdResidenceAddress());
					directorMasterData.setdResidenceAddress(list.get(i).getdResidenceAddress());
					directorMasterData.setdPincode(list.get(i).getdPincode());
					directorMasterData.setdAddress(list.get(i).getdAddress());
					directorMasterData.setaCity(list.get(i).getaCity());
					directorMasterData.setDstate(list.get(i).getDstate());
					directorMasterData.setdSameAsResAddress(String.valueOf(list.get(i).getdSameAsBussAddress()));
					directorMasterData.setdPaADDRESS(list.get(i).getdAddress());
					directorMasterData.setdPaADDRESS(list.get(i).getdAddress());
					directorMasterData.setdPermanentAddress(list.get(i).getdPermanentAddress());
					directorMasterData.setdPermanentState(list.get(i).getdPermanentState());
					directorMasterData.setdPermanentPincode(list.get(i).getdPermanentPincode());
					directorMasterData.setdPermanentCity(list.get(i).getdPermanentAddress());
					directorMasterData.setdCreatedDate(String.valueOf(list.get(i).getdCreatedDate()));
					directorMasterData.setdUpdatedDate(String.valueOf(list.get(i).getdUpdatedDate()));
					directorMasterData.setdOperatedBy(list.get(i).getdOperatedBy());

					json = gson.toJson(directorMasterData);
					System.out.println(json);
					str = str + "\n" + json;
				}
			} else {
				System.out.println("App Id Not found");
				str = "#!D!#";
			}
		} catch (Exception e) {
			e.printStackTrace();
			str = "#!D!#";
		}
		return str;
	}

	@Override
	public void saveOnce(List<ExcelTableCoord> excelCoordinatesList) {
		variablesDao.batchSaveExcel(excelCoordinatesList);
	}

	@Override
	public List<ExcelTableData> getByTableNameAndMId(String tablename, MasterExcel masterExcel) {
		return excelTableDataDao.getByTableNameAndMId(tablename, masterExcel);
	}

	@Override
	public String findSimpleTableCoordByVID(String vid) {

		mapTables();
		String json = null;
		try {
			List<ExcelTableCoord> list = excelTableCoordDao.findSimpleTableCoordByVID(vid);
			List<ExcelTableCoordBean> resultList = new ArrayList<ExcelTableCoordBean>();
			List<String> dupTableName = new ArrayList<String>();

			if (list.size() > 0) {

				for (ExcelTableCoord excelTableCoord : list) {

					if (dupTableName.contains(excelTableCoord.getTableName())) {
					} else {

						dupTableName.add(excelTableCoord.getTableName());
						ExcelTableCoordBean excelTableCoordBean = new ExcelTableCoordBean();
						String[] ycords = excelTableCoord.getYcord().split(",");
						int y1 = Integer.parseInt(ycords[0]);
						int y2 = Integer.parseInt(ycords[1]);

						int diff = y2 - y1 + 1;
						if (diff == 2) {
							excelTableCoordBean.setKeyval(true);
						} else {
							excelTableCoordBean.setKeyval(false);
						}

						excelTableCoordBean.setTableName(excelTableCoord.getTableName());
						excelTableCoordBean.setDesc(excelTableCoord.getDesc());
						excelTableCoordBean.setTableId(excelTableCoord.getId());

						try {
							excelTableCoordBean.setDiv(tableDivision.get(excelTableCoord.getTableName()));
						} catch (Exception e) {
							excelTableCoordBean.setDiv(7);
						}

						if (excelTableCoord.getTableName().equals("execSummHeaders")) {
							excelTableCoordBean.setKeyval(true);
						}

						resultList.add(excelTableCoordBean);

					}

				}

				Gson gson = new GsonBuilder().setPrettyPrinting().create();

				json = gson.toJson(resultList);
			} else {
				json = "#!D!#";
			}
		} catch (Exception e) {
			e.printStackTrace();
			json = "#!D!#";
		}
		return json;

	}

	public static void mapTables() {

		tableDivision.put("execSummHeaders", 6);
		tableDivision.put("execSummValues", 6);
		tableDivision.put("Cycle", 1);
		tableDivision.put("Product", 1);
		tableDivision.put("Cibil", 2);
		tableDivision.put("NonCibil", 2);
		tableDivision.put("LoanData",2);
		tableDivision.put("PersonalDetails", 3);
		tableDivision.put("PersonalDetails", 3);
		tableDivision.put("PersonalDetails", 3);
		tableDivision.put("Applicants", 3);
		tableDivision.put("bankDetails1", 4);
		tableDivision.put("BankDetailsData1", 4);
		tableDivision.put("BankLoanData1", 4);
		tableDivision.put("AdditionalInfo1", 4);
		tableDivision.put("AdditionalInfo1", 4);
		tableDivision.put("BankRevenue1", 4);
		tableDivision.put("bankDetails2", 4);
		tableDivision.put("BankDetailsData2", 4);
		tableDivision.put("BankLoanData2", 4);
		tableDivision.put("AdditionalInfo2", 4);
		tableDivision.put("AdditionalInfo2", 4);
		tableDivision.put("BankRevenue2", 4);
		tableDivision.put("bankDetails3", 4);
		tableDivision.put("BankLoanData3", 4);
		tableDivision.put("BankDetailsData3", 4);
		tableDivision.put("AdditionalInfo3", 4);
		tableDivision.put("AdditionalInfo3", 4);
		tableDivision.put("BankRevenue3", 4);
		tableDivision.put("bankDetails4", 4);
		tableDivision.put("BankDetailsData4", 4);
		tableDivision.put("BankLoanData4", 4);
		tableDivision.put("AdditionalInfo4", 4);
		tableDivision.put("AdditionalInfo4", 4);
		tableDivision.put("BankRevenue4", 4);
		tableDivision.put("bankDetails5", 4);
		tableDivision.put("BankDetailsData5", 4);
		tableDivision.put("BankLoanData5", 4);
		tableDivision.put("AdditionalInfo5", 4);
		tableDivision.put("AdditionalInfo5", 4);
		tableDivision.put("BankRevenue5", 4);
		tableDivision.put("Consolidated", 4);
		tableDivision.put("Cumulative", 4);
		tableDivision.put("Cumulative", 4);
		tableDivision.put("LoanEligibility", 4);
		tableDivision.put("PRevenue", 4);
		tableDivision.put("Comments-1", 5);
		tableDivision.put("Comments-2", 5);
		tableDivision.put("Comments-3", 5);
		tableDivision.put("Comments-4", 5);

	}

	public void updateTableData(List<ExcelTableData> tableDataList) {
		excelTableDataDao.batchSaveExcel(tableDataList);
	}

	public void saveTableDataObj(ExcelTableData excelTableData) {
		excelTableDataDao.merge(excelTableData);
	}

	public Audit findSubmit(String appId) {

		return auditDao.findSubmitByAppId(appId);

	}

	public Audit findSubmitByLoanId(String appId) {

		return auditDao.findSubmitByLoanId(appId);

	}  
	public void saveAudit(Audit audit) {
		auditDao.merge(audit);
	}
	
	public Variables findByMasterIdVariables(MasterExcel masterExcel, String key){
		return variablesDao.getByKey(masterExcel,key);
	}
	
	public ExcelTableData findDataByTableAndKey(MasterExcel masterExcel,String key, String tableName){
		return excelTableDataDao.findByKeyAndTableName(masterExcel,key,tableName);
	}
	
	public void saveExcelTableDataList(List<ExcelTableData> excelTableDataList){
		 excelTableDataDao.batchSaveExcel(excelTableDataList);
	}

	public ExcelTableData findExcelDataById(long l){
		return excelTableDataDao.getById(l);
	}
	
	public List<NewExecSumm> getExcelSummData(MasterExcel masterExcel, String table){
		return newExecSummDao.getDataByAppIdAndTable(masterExcel,table);
	}
	
	public NewExecSumm findExecSummData(String key, MasterExcel masterExcel){
		
		return newExecSummDao.findByKeyAndAppId(key,masterExcel);
	}
	
	public void saveVariable(Variables variables){
		
		variablesDao.persist(variables);
	}
	
	public void saveNewExecSumm(NewExecSumm newExecSumm){
		newExecSummDao.merge(newExecSumm);
	}

//public void updateFlag(MasterExcel masterExcel)
//{
//	  masterExcelDao.merge(masterExcel);
//}
	
 public String findMaxVid()
 {
	return excelTableCoordDao.findMaxVid();
	 
 }

@Override
public void updateSaveFlag(MasterExcel masterExcel) {
	// TODO Auto-generated method stub
	masterExcelDao.merge(masterExcel);
	
}
}
