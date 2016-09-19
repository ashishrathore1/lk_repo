package com.lk.credittemplate.service;

import java.util.List;

import com.lk.credittemplate.model.Audit;
import com.lk.credittemplate.model.ExcelTableCoord;
import com.lk.credittemplate.model.ExcelTableData;
import com.lk.credittemplate.model.MasterExcel;
import com.lk.credittemplate.model.NewExecSumm;
import com.lk.credittemplate.model.Variables;



public interface ExcelService {

	public MasterExcel saveMasterData(MasterExcel masterExcel);

	public MasterExcel findByMasterExcelId(String appId);

	public List<Variables> findByMasterIdVariables(MasterExcel masterExcel);

	public void saveVariablesData(List<Variables> variableNewList);

	public String getDirectorMasterDataByAppId(String appId);

	public void saveOnce(List<ExcelTableCoord> excelCoordinatesList);

	public List<ExcelTableData> getByTableNameAndMId(String tablename, MasterExcel masterExcel);

	public String findSimpleTableCoordByVID(String appId);

	public void updateTableData(List<ExcelTableData> tableDataList);

	public void saveTableDataObj(ExcelTableData excelTableData);

	public Audit findSubmit(String appId);
	
	public Audit findSubmitByLoanId(String appId);

	public void saveAudit(Audit audit);

	public Variables findByMasterIdVariables(MasterExcel masterExcel, String string);

	public ExcelTableData findDataByTableAndKey(MasterExcel masterExcel,String key, String tableName);

	public void saveExcelTableDataList(List<ExcelTableData> excelTableDataList);

	public ExcelTableData findExcelDataById(long l);

	public List<NewExecSumm> getExcelSummData(MasterExcel masterExcel, String table);

	public NewExecSumm findExecSummData(String key, MasterExcel masterExcel);

	public void saveVariable(Variables variables);

	public void saveNewExecSumm(NewExecSumm newExecSumm);
	
	public String findMaxVid();
	
	//public void updateFlag(MasterExcel masterExcel);
	
	public void updateSaveFlag(MasterExcel masterExcel);

}
