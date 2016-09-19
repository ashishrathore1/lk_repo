package com.lk.credittemplate.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lk.credittemplate.dao.ExcelTableCoordDao;
import com.lk.credittemplate.dao.ExcelTableDataDao;
import com.lk.credittemplate.dao.MasterExcelDao;
import com.lk.credittemplate.dao.NewExecSummDao;
import com.lk.credittemplate.dao.VaraiblesDao;
import com.lk.credittemplate.model.ExcelTableCoord;
import com.lk.credittemplate.model.ExcelTableData;
import com.lk.credittemplate.model.MasterExcel;
import com.lk.credittemplate.model.NewExecSumm;
import com.lk.credittemplate.model.Variables;

@Service
public class ParseExcel {

	@Autowired
	ExcelTableCoordDao excelTableCoordDao;

	@Autowired
	ExcelTableDataDao excelTableDataDao;

	@Autowired
	VaraiblesDao varaiblesDao;

	@Autowired
	MasterExcelDao masterExcelDao;

	@Autowired
	NewExecSummDao newExecSummDao;
	
	private static final Logger logger = LoggerFactory.getLogger(ParseExcel.class);
	
	private static List<String> ignoreList = new ArrayList<String>();

	public void parseExcelTablesSave(Sheet analysis, Sheet executiveSumm, String vId, MasterExcel masterExcel) {

		ignoreList = new ArrayList<String>();

		List<ExcelTableCoord> list = excelTableCoordDao.findSimpleTableCoordByVID(vId);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List<ExcelTableData> resultList = new ArrayList<ExcelTableData>();

		Cell cell;
		String st;
		ExcelTableData simpleTableData;
		Row curRow;
		//ExecsummHeaders
		List<NewExecSumm> newExecSummList = new ArrayList<NewExecSumm>();
		
		int r = 0;
		int c = 0;
		
		for (int j = 1; j < 6; j++) {
			curRow = executiveSumm.getRow(j);
			for (int i = 1; i < 9; i++) {
				if (i != 2) {
					try {
						simpleTableData = new ExcelTableData();
						cell = curRow.getCell(i);
						st = parseCell(cell);
						simpleTableData.setVal(st);
						simpleTableData.setTname("execSummHeaders");
						simpleTableData.setMasterExcel(masterExcel);
						resultList.add(simpleTableData);
					} catch (Exception e) {
						logger.error("ParseExcel parseExcelTablesSave execSummHeaders ",e);
					}
					
				}
			}
			
			simpleTableData = new ExcelTableData();
			simpleTableData.setVal("#!D!#");
			simpleTableData.setTname("execSummHeaders");
			simpleTableData.setMasterExcel(masterExcel);
			resultList.add(simpleTableData);
			
	   }
		//SOCIAL
		
		for (int j = 38; j < 42; j++) {
			curRow = executiveSumm.getRow(j);
			for (int i = 1; i < 9; i++) {
				if (i != 2) {
					try {
						simpleTableData = new ExcelTableData();
						cell = curRow.getCell(i);
						st = parseCell(cell);
						simpleTableData.setVal(st);
						simpleTableData.setTname("SOCIAL");
						simpleTableData.setMasterExcel(masterExcel);
						resultList.add(simpleTableData);
					} catch (Exception e) {
						logger.error("ParseExcel parseExcelTablesSave SOCIAL ",e);
					}
					
				}
			}
			
			simpleTableData = new ExcelTableData();
			simpleTableData.setVal("#!D!#");
			simpleTableData.setTname("SOCIAL");
			simpleTableData.setMasterExcel(masterExcel);
			resultList.add(simpleTableData);
	    }
		
		
	    //ANALYTICS	
		r = 8;
		
		curRow = executiveSumm.getRow(r);
		
		c = 1;
		for(int i=1;i<=3;i++){
		
			NewExecSumm newExecSumm = new NewExecSumm();
			cell = curRow.getCell(c++);
			st = parseCell(cell);
			newExecSumm.setKey(st);
			
			if(c==2){
				c=3;
			}
			cell = curRow.getCell(c);
			st = parseCell(cell);
			newExecSumm.setVal1(st);
			newExecSumm.setTableName("ANALYTICS");
			newExecSumm.setMasterExcel(masterExcel);
			newExecSummList.add(newExecSumm);
			c=c+1;
		}
		
		r++;
		curRow = executiveSumm.getRow(r);
		c = 1;
		
		for(int i=1;i<=2;i++){
			
			NewExecSumm newExecSumm = new NewExecSumm();
			cell = curRow.getCell(c++);
			st = parseCell(cell);
			newExecSumm.setKey(st);
			if(c==2){
				c=3;
			}
			cell = curRow.getCell(c);
			st = parseCell(cell);
			newExecSumm.setVal1(st);
			newExecSumm.setTableName("ANALYTICS");
			newExecSumm.setMasterExcel(masterExcel);
			newExecSummList.add(newExecSumm);
			c=c+1;
		}
		
		//Loan Eligibilty
		
		for(r=13;r<16;r++){
			
			c=1;
			curRow = executiveSumm.getRow(r);
			
			NewExecSumm newExecSumm = new NewExecSumm();
			
			cell = curRow.getCell(c++);
			st = parseCell(cell);
			newExecSumm.setKey(st);
			
			if(c==2){
				c=3;
			}
			cell = curRow.getCell(c++);
			st = parseCell(cell);
			newExecSumm.setVal1(st);
			
			cell = curRow.getCell(c);
			st = parseCell(cell);
			newExecSumm.setVal2(st);
			
			newExecSumm.setTableName("LOAN ELIGIBILTY");
			newExecSumm.setMasterExcel(masterExcel);
			newExecSummList.add(newExecSumm);
		 }
		
			
		for(r=13;r<16;r++){			
			c=6;
			NewExecSumm newExecSumm = new NewExecSumm();
			curRow = executiveSumm.getRow(r);
			
			cell = curRow.getCell(c++);
			st = parseCell(cell);
			newExecSumm.setKey(st);
			
			cell = curRow.getCell(c++);
			st = parseCell(cell);
			newExecSumm.setVal1(st);
			
			cell = curRow.getCell(c);
			st = parseCell(cell);
			newExecSumm.setVal2(st);
			
			newExecSumm.setTableName("LOAN ELIGIBILTY");
			newExecSumm.setMasterExcel(masterExcel);
			newExecSummList.add(newExecSumm);
		}
			
		//PURPOSE
		
		r = 18;
		c=1;
		
		curRow = executiveSumm.getRow(r);
		
		NewExecSumm newExecSummSingle = new NewExecSumm();
		
		cell = curRow.getCell(c++);
		st = parseCell(cell);
		newExecSummSingle.setKey(st);
		
		if(c==2){
			c=3;
		}
		cell = curRow.getCell(c);
		st = parseCell(cell);
		newExecSummSingle.setVal1(st);
		
		
		newExecSummSingle.setTableName("PURPOSE");
		newExecSummSingle.setMasterExcel(masterExcel);
		newExecSummList.add(newExecSummSingle);
			
		
		//REVENUE
		
		for(r=20;r<23;r++){
			
			c=1;
			for(int i=1;i<=3;i++){
				
				curRow = executiveSumm.getRow(r);
				
				NewExecSumm newExecSumm = new NewExecSumm();
				
				cell = curRow.getCell(c++);
				st = parseCell(cell);
				newExecSumm.setKey(st);
				
				if(c==2){
					c=3;
				}
				cell = curRow.getCell(c++);
				st = parseCell(cell);
				newExecSumm.setVal1(st);
				
				newExecSumm.setTableName("REVENUE");
				newExecSumm.setMasterExcel(masterExcel);
				newExecSummList.add(newExecSumm);
			}
		}
		
		//FINANCIAL DISCIPLINE
		
		for(r=25;r<30;r++){
			
			c=1;
			for(int i=1;i<=3;i++){
				
				curRow = executiveSumm.getRow(r);
				
				NewExecSumm newExecSumm = new NewExecSumm();
				
				cell = curRow.getCell(c++);
				st = parseCell(cell);
				newExecSumm.setKey(st);
				
				if(c==2){
					c=3;
				}
				
				cell = curRow.getCell(c++);
				st = parseCell(cell);
				newExecSumm.setVal1(st);
				
				newExecSumm.setTableName("FINANCIAL DISCIPLINE");
				newExecSumm.setMasterExcel(masterExcel);
				newExecSummList.add(newExecSumm);
			}
		}
		
		r = 25;

		curRow = executiveSumm.getRow(r);
		
	    newExecSummSingle = new NewExecSumm();
		
		cell = curRow.getCell(8);
		st = parseCell(cell);
		newExecSummSingle.setKey(st);
		
		r++;

		curRow = executiveSumm.getRow(r);

		cell = curRow.getCell(8);
		st = parseCell(cell);
		newExecSummSingle.setVal1(st);
		
		
		newExecSummSingle.setTableName("FINANCIAL DISCIPLINE");
		newExecSummSingle.setMasterExcel(masterExcel);
		newExecSummList.add(newExecSummSingle);
		
		r = 28;

		curRow = executiveSumm.getRow(r);
		
	    newExecSummSingle = new NewExecSumm();
		
		cell = curRow.getCell(8);
		st = parseCell(cell);
		newExecSummSingle.setKey(st);
		
		r++;

		curRow = executiveSumm.getRow(r);

		cell = curRow.getCell(8);
		st = parseCell(cell);
		newExecSummSingle.setVal1(st);
		
		
		newExecSummSingle.setTableName("FINANCIAL DISCIPLINE");
		newExecSummSingle.setMasterExcel(masterExcel);
		newExecSummList.add(newExecSummSingle);
		
		//CIBIL
		
		for(r=32;r<36;r++){
			
			c=1;
			for(int i=1;i<=3;i++){
				
				curRow = executiveSumm.getRow(r);
				
				NewExecSumm newExecSumm = new NewExecSumm();
				
				cell = curRow.getCell(c++);
				st = parseCell(cell);
				newExecSumm.setKey(st);
				
				if(c==2){
					c=3;
				}
				
				cell = curRow.getCell(c++);
				st = parseCell(cell);
				newExecSumm.setVal1(st);
				
				newExecSumm.setTableName("CIBIL");
				newExecSumm.setMasterExcel(masterExcel);
				newExecSummList.add(newExecSumm);
			}
		}
		
		//VINTAGE, STATUTORY COMPLIANCE & DEFAULT STATUS 
		
		for(r=44;r<47;r++){
			
			c=1;
			for(int i=1;i<=3;i++){
				
				curRow = executiveSumm.getRow(r);
				
				NewExecSumm newExecSumm = new NewExecSumm();
				
				cell = curRow.getCell(c++);
				st = parseCell(cell);
				newExecSumm.setKey(st);
				
				if(c==2){
					c=3;
				}
				
				cell = curRow.getCell(c++);
				st = parseCell(cell);
				newExecSumm.setVal1(st);
				
				newExecSumm.setTableName("VINTAGE, STATUTORY COMPLIANCE & DEFAULT STATUS");
				newExecSumm.setMasterExcel(masterExcel);
				newExecSummList.add(newExecSumm);
				
			}
			//december quaterly : Last cell
		}
		
		
		//WORKING CAPITAL CYCLE
		
		for(r=49;r<53;r++){
			
			c=1;
			for(int i=1;i<=2;i++){
				
				curRow = executiveSumm.getRow(r);
				
				NewExecSumm newExecSumm = new NewExecSumm();
				
				cell = curRow.getCell(c++);
				st = parseCell(cell);
				newExecSumm.setKey(st);
				
				if(c==2){
					c=3;
				}
				
				cell = curRow.getCell(c++);
				st = parseCell(cell);
				newExecSumm.setVal1(st);
				
				newExecSumm.setTableName("WORKING CAPITAL CYCLE");
				newExecSumm.setMasterExcel(masterExcel);
				newExecSummList.add(newExecSumm);
			}
		}
		
		//CMA
		r= 48;
		
		curRow = executiveSumm.getRow(r);
		
	    newExecSummSingle = new NewExecSumm();
		
		newExecSummSingle.setKey("");
				
		cell = curRow.getCell(7);
		st = parseCell(cell);
		newExecSummSingle.setVal1(st);
		
		cell = curRow.getCell(8);
		st = parseCell(cell);
		newExecSummSingle.setVal2(st);
		
		newExecSummSingle.setTableName("CMA");
		newExecSummSingle.setMasterExcel(masterExcel);
		newExecSummList.add(newExecSummSingle);	
		
		for( r=49;r<65;r++){
			try {
				c=6;
				curRow = executiveSumm.getRow(r);
				
				NewExecSumm newExecSumm = new NewExecSumm();
				
				cell = curRow.getCell(c++);
				st = parseCell(cell);
				newExecSumm.setKey(st);
						
				cell = curRow.getCell(c++);
				st = parseCell(cell);
				newExecSumm.setVal1(st);
				
				cell = curRow.getCell(c++);
				st = parseCell(cell);
				newExecSumm.setVal2(st);
				
				newExecSumm.setTableName("CMA");
				newExecSumm.setMasterExcel(masterExcel);
				newExecSummList.add(newExecSumm);
			} catch (Exception e) {
				logger.error("ParseExcel parseExcelTablesSave CMA ",e);
			}
		    
		}
		
		//RATIOS
		
		for( r=66;r<75;r++){
			
		    c=6;
			curRow = executiveSumm.getRow(r);
			NewExecSumm newExecSumm = new NewExecSumm();
			try {
				cell = curRow.getCell(c++);
				st = parseCell(cell);
				newExecSumm.setKey(st);
			} catch (Exception e) {
				logger.error("ParseExcel parseExcelTablesSave RATIOS ",e);
			}
			
			try {
				cell = curRow.getCell(c++);
				st = parseCell(cell);
				newExecSumm.setVal1(st);
			} catch (Exception e) {
				logger.error("ParseExcel parseExcelTablesSave RATIOS ",e);
			}
			
			try {
				cell = curRow.getCell(c);
				st = parseCell(cell);
				newExecSumm.setVal2(st);
			} catch (Exception e) {
				logger.error("ParseExcel parseExcelTablesSave RATIOS ",e);
			}
			
			newExecSumm.setTableName("RATIOS");
			newExecSumm.setMasterExcel(masterExcel);
			newExecSummList.add(newExecSumm);
		}
		//System.out.println("Executive Summary Saved!");

		for (ExcelTableCoord table : list) {
	
			if (table.getTableName().length() > 10 && table.getTableName().substring(0, 11).equals("bankDetails")) {
				bankDetailsIsEmpty(table, analysis);
			}
	
			if (table.getTableName().equals("execSummHeaders")) {
	
			} else {
	
				if (ignoreList.contains(table.getTableName())) {
				} else {
	
					String[] s = table.getXcord().split(",");
					String[] s1 = table.getYcord().split(",");
					int rowStart = Integer.parseInt(s[0]);
					int rowEnd = Integer.parseInt(s[1]);
					int colStart = Integer.parseInt(s1[0]);
					int colEnd = Integer.parseInt(s1[1]);
					Sheet cursheet;
					String tablename = table.getTableName();
	
					if (tablename.equals("execSummValues")) {
						cursheet = executiveSumm;
					} else {
						cursheet = analysis;
					}
	
					for (int i = rowStart; i < rowEnd; i++) {
						curRow = cursheet.getRow(i);
						for (int j = colStart; j <= colEnd; j++) {
	
							simpleTableData = new ExcelTableData();
							try {
								cell = curRow.getCell(j);
								st = parseCell(cell);
								if (st.length() < 255) {
									simpleTableData.setVal(st);
								} else {
									////System.out.println("Greater Than 255");
									////System.out.println("Row-" + i + ", Column-" + j + "--" + tablename);
									simpleTableData.setVal(st.substring(0, 254));
								}
								simpleTableData.setTname(tablename);
								simpleTableData.setMasterExcel(masterExcel);
								resultList.add(simpleTableData);
	
							} catch (Exception e) {
								logger.error("ParseExcel parseExcelTablesSave Failed",e);
							}
						}
						simpleTableData = new ExcelTableData();
						simpleTableData.setVal("#!D!#");
						simpleTableData.setTname(tablename);
						simpleTableData.setMasterExcel(masterExcel);
						resultList.add(simpleTableData);
					}
				}
			}
		}
		newExecSummDao.deleteByMID(masterExcel);
		excelTableDataDao.batchSaveExcel(newExecSummList);	
		excelTableDataDao.deleteByMID(masterExcel);
		excelTableDataDao.batchSaveExcel(resultList);
		String json = gson.toJson(resultList);

	}

	public static void bankDetailsIsEmpty(ExcelTableCoord table, Sheet analysis) {

		String[] s = table.getXcord().split(",");
		String[] s1 = table.getYcord().split(",");
		int rowStart = Integer.parseInt(s[0]);
		int colEnd = Integer.parseInt(s1[1]);
		String str;

		Row curRow = analysis.getRow(rowStart);
		Cell cell = curRow.getCell(colEnd);
		str = parseCell(cell);
		char lastChar = table.getTableName().charAt(table.getTableName().length() - 1);

		if (StringUtils.isEmpty(str)) {

			ignoreList.add("bankDetails" + lastChar);
			ignoreList.add("AdditionalInfo" + lastChar);
			ignoreList.add("BankRevenue" + lastChar);
			ignoreList.add("BankDetailsData" + lastChar);
			ignoreList.add("BankLoanData" + lastChar);
			
		}

	}

	public void parseExcelTablesUpdate(Sheet analysis, Sheet executiveSumm, String vId, MasterExcel masterExcel) {
		parseExcelTablesSave(analysis, executiveSumm, vId, masterExcel);

	}

	public static String parseCell(Cell cell) {
		String result = "";
		try {
			DataFormatter df = new DataFormatter();
			if (cell != null) {
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_BOOLEAN:
					result = df.formatCellValue(cell);
					break;
				case Cell.CELL_TYPE_NUMERIC:
					result = df.formatCellValue(cell);
					break;
				case Cell.CELL_TYPE_STRING:
					result = cell.getRichStringCellValue().getString().trim();
					break;
				case Cell.CELL_TYPE_BLANK:
					result = "";
					break;
				case Cell.CELL_TYPE_ERROR:
					result = "";
					break;
				case Cell.CELL_TYPE_FORMULA:
					switch (cell.getCachedFormulaResultType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						cell.setCellType(Cell.CELL_TYPE_BOOLEAN);
						result = df.formatCellValue(cell);
						break;
					case Cell.CELL_TYPE_NUMERIC:
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						result = df.formatCellValue(cell);
						break;
					case Cell.CELL_TYPE_STRING:
						result = cell.getRichStringCellValue().getString().trim();
						break;
					case Cell.CELL_TYPE_BLANK:
						result = 0+"";
						break;
					case Cell.CELL_TYPE_ERROR:
						result = 0+"";
						break;
					}
				}
			}
		} catch (Exception e) {
			logger.error("ParseExcel parseCell excelption ",e);
		}
		return result;

	}

	public void parseVariables(Sheet variables, List<Variables> variablesList, MasterExcel masterExcel) {

		varaiblesDao.deleteByMID(masterExcel);
		String str;

		Cell cell;

		Iterator<Row> iterator = variables.iterator();

		List<Variables> variableList = new ArrayList<Variables>();
		
		Row nextRow;
		
		while (iterator.hasNext()) {
			nextRow = iterator.next();
			
			Variables variable = new Variables();
			variable.setCreated((Calendar.getInstance().getTime()));
			cell = nextRow.getCell(1);
			str = parseCell(cell);
			
			variable.setKey(str);

			cell = nextRow.getCell(2);
			str = parseCell(cell);
			if (str.length() < 255) {
				variable.setVal(str);
			} else {
				variable.setVal(str.substring(0, 254));
			}
			variable.setMasterExcel(masterExcel);
			variableList.add(variable);	
		}
		
		varaiblesDao.batchSaveExcel(variableList);
		
	}
	
	public void parseExcelSave(Sheet executiveSumm, String vId, MasterExcel masterExcel) {

		ignoreList = new ArrayList<String>();

		List<ExcelTableCoord> list = excelTableCoordDao.findSimpleTableCoordByVID(vId);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List<ExcelTableData> resultList = new ArrayList<ExcelTableData>();
		Date createdDate=Calendar.getInstance().getTime();
		Cell cell;
		String st;
		ExcelTableData simpleTableData;
		Row curRow;
			//ExecsummHeaders
			List<NewExecSumm> newExecSummList = new ArrayList<NewExecSumm>();
			
			int r = 0;
			int c = 0;
			
			for (int j = 1; j < 6; j++) {
				curRow = executiveSumm.getRow(j);
				for (int i = 1; i < 9; i++) {
					if (i != 2) {
						simpleTableData = new ExcelTableData();
						simpleTableData.setCreated(createdDate);
						cell = curRow.getCell(i);
						st = parseCell(cell);
						simpleTableData.setVal(st);
						simpleTableData.setTname("execSummHeaders");
						simpleTableData.setMasterExcel(masterExcel);
						resultList.add(simpleTableData);
					}
				}
				
				simpleTableData = new ExcelTableData();
				simpleTableData.setCreated(createdDate);
				simpleTableData.setVal("#!D!#");
				simpleTableData.setTname("execSummHeaders");
				simpleTableData.setMasterExcel(masterExcel);
				resultList.add(simpleTableData);
				
		   }
			//SOCIAL
			
			for (int j = 38; j < 42; j++) {
				curRow = executiveSumm.getRow(j);
				for (int i = 1; i < 9; i++) {
					if (i != 2) {
						simpleTableData = new ExcelTableData();
						cell = curRow.getCell(i);
						st = parseCell(cell);
						simpleTableData.setVal(st);
						simpleTableData.setCreated(createdDate);
						simpleTableData.setTname("SOCIAL");
						simpleTableData.setMasterExcel(masterExcel);
						resultList.add(simpleTableData);
					}
				}
				
				simpleTableData = new ExcelTableData();
				simpleTableData.setVal("#!D!#");
				simpleTableData.setCreated(createdDate);
				simpleTableData.setTname("SOCIAL");
				simpleTableData.setMasterExcel(masterExcel);
				resultList.add(simpleTableData);
		   }
			
		    //ANALYTICS	
			r = 8;
			
			curRow = executiveSumm.getRow(r);
			
			c = 1;
			for(int i=1;i<=3;i++){
			
			NewExecSumm newExecSumm = new NewExecSumm();
			cell = curRow.getCell(c++);
			st = parseCell(cell);
			newExecSumm.setCreated(createdDate);
			newExecSumm.setKey(st);
			
			if(c==2){
				c=3;
			}
			cell = curRow.getCell(c);
			st = parseCell(cell);
			newExecSumm.setVal1(st);
			newExecSumm.setTableName("ANALYTICS");
			newExecSumm.setMasterExcel(masterExcel);
			newExecSummList.add(newExecSumm);
			c=c+1;
			}
			
			r++;
			curRow = executiveSumm.getRow(r);
			c = 1;
			
			for(int i=1;i<=2;i++){
				
				NewExecSumm newExecSumm = new NewExecSumm();
				newExecSumm.setCreated(createdDate);
				cell = curRow.getCell(c++);
				st = parseCell(cell);
				newExecSumm.setKey(st);
				if(c==2){
					c=3;
				}
				cell = curRow.getCell(c);
				st = parseCell(cell);
				newExecSumm.setVal1(st);
				newExecSumm.setTableName("ANALYTICS");
				newExecSumm.setMasterExcel(masterExcel);
				newExecSummList.add(newExecSumm);
				c=c+1;
			}
			
			//Loan Eligibilty
			
			for(r=13;r<16;r++){
				
				c=1;
				curRow = executiveSumm.getRow(r);
				
				NewExecSumm newExecSumm = new NewExecSumm();
				newExecSumm.setCreated(createdDate);
				cell = curRow.getCell(c++);
				st = parseCell(cell);
				newExecSumm.setKey(st);
				
				if(c==2){
					c=3;
				}
				cell = curRow.getCell(c++);
				st = parseCell(cell);
				newExecSumm.setVal1(st);
				
				cell = curRow.getCell(c);
				st = parseCell(cell);
				newExecSumm.setVal2(st);
				
				newExecSumm.setTableName("LOAN ELIGIBILTY");
				newExecSumm.setMasterExcel(masterExcel);
				newExecSummList.add(newExecSumm);
			}
			
				
				for(r=13;r<16;r++){
					
					c=6;
				NewExecSumm newExecSumm = new NewExecSumm();
				newExecSumm.setCreated(createdDate);
				curRow = executiveSumm.getRow(r);
				
				cell = curRow.getCell(c++);
				st = parseCell(cell);
				newExecSumm.setKey(st);
				
				cell = curRow.getCell(c++);
				st = parseCell(cell);
				newExecSumm.setVal1(st);
				
				cell = curRow.getCell(c);
				st = parseCell(cell);
				newExecSumm.setVal2(st);
				
				newExecSumm.setTableName("LOAN ELIGIBILTY");
				newExecSumm.setMasterExcel(masterExcel);
				newExecSummList.add(newExecSumm);
			}
				
				//PURPOSE
				
				r = 18;
				c=1;
				
				curRow = executiveSumm.getRow(r);
				
				NewExecSumm newExecSummSingle = new NewExecSumm();
				newExecSummSingle.setCreated(createdDate);
				cell = curRow.getCell(c++);
				st = parseCell(cell);
				newExecSummSingle.setKey(st);
				
				if(c==2){
					c=3;
				}
				cell = curRow.getCell(c);
				st = parseCell(cell);
				newExecSummSingle.setVal1(st);
				
				
				newExecSummSingle.setTableName("PURPOSE");
				newExecSummSingle.setMasterExcel(masterExcel);
				newExecSummList.add(newExecSummSingle);
				
			
			//REVENUE
			
			for(r=20;r<23;r++){
				
				c=1;
				for(int i=1;i<=3;i++){
					
					curRow = executiveSumm.getRow(r);
					
					NewExecSumm newExecSumm = new NewExecSumm();
					newExecSumm.setCreated(createdDate);
					cell = curRow.getCell(c++);
					st = parseCell(cell);
					newExecSumm.setKey(st);
					
					if(c==2){
						c=3;
					}
					cell = curRow.getCell(c++);
					st = parseCell(cell);
					newExecSumm.setVal1(st);
					
					newExecSumm.setTableName("REVENUE");
					newExecSumm.setMasterExcel(masterExcel);
					newExecSummList.add(newExecSumm);
				}
			}
			
			//FINANCIAL DISCIPLINE
			
			for(r=25;r<30;r++){
				
				c=1;
				for(int i=1;i<=3;i++){
					
					curRow = executiveSumm.getRow(r);
					
					NewExecSumm newExecSumm = new NewExecSumm();
					newExecSumm.setCreated(createdDate);
					cell = curRow.getCell(c++);
					st = parseCell(cell);
					newExecSumm.setKey(st);
					
					if(c==2){
						c=3;
					}
					
					cell = curRow.getCell(c++);
					st = parseCell(cell);
					newExecSumm.setVal1(st);
					
					newExecSumm.setTableName("FINANCIAL DISCIPLINE");
					newExecSumm.setMasterExcel(masterExcel);
					newExecSummList.add(newExecSumm);
				}
			}
			
			r = 25;

			curRow = executiveSumm.getRow(r);
			
		    newExecSummSingle = new NewExecSumm();
		    newExecSummSingle.setCreated(createdDate);
			cell = curRow.getCell(8);
			st = parseCell(cell);
			newExecSummSingle.setKey(st);
			
			r++;

			curRow = executiveSumm.getRow(r);

			cell = curRow.getCell(8);
			st = parseCell(cell);
			newExecSummSingle.setVal1(st);
			
			
			newExecSummSingle.setTableName("FINANCIAL DISCIPLINE");
			newExecSummSingle.setMasterExcel(masterExcel);
			newExecSummList.add(newExecSummSingle);
			
			r = 28;

			curRow = executiveSumm.getRow(r);
			
		    newExecSummSingle = new NewExecSumm();
		    newExecSummSingle.setCreated(createdDate);
			cell = curRow.getCell(8);
			st = parseCell(cell);
			newExecSummSingle.setKey(st);
			
			r++;

			curRow = executiveSumm.getRow(r);

			cell = curRow.getCell(8);
			st = parseCell(cell);
			newExecSummSingle.setVal1(st);
			
			
			newExecSummSingle.setTableName("FINANCIAL DISCIPLINE");
			newExecSummSingle.setMasterExcel(masterExcel);
			newExecSummList.add(newExecSummSingle);
			
			//CIBIL
			
			for(r=32;r<36;r++){
				
				c=1;
				for(int i=1;i<=3;i++){
					
					curRow = executiveSumm.getRow(r);
					
					NewExecSumm newExecSumm = new NewExecSumm();
					newExecSumm.setCreated(createdDate);
					cell = curRow.getCell(c++);
					st = parseCell(cell);
					newExecSumm.setKey(st);
					
					if(c==2){
						c=3;
					}
					
					cell = curRow.getCell(c++);
					st = parseCell(cell);
					newExecSumm.setVal1(st);
					
					newExecSumm.setTableName("CIBIL");
					newExecSumm.setMasterExcel(masterExcel);
					newExecSummList.add(newExecSumm);
				}
			}
			
			//VINTAGE, STATUTORY COMPLIANCE & DEFAULT STATUS 
			
			for(r=44;r<47;r++){
				
				c=1;
				for(int i=1;i<=3;i++){
					
					curRow = executiveSumm.getRow(r);
					
					NewExecSumm newExecSumm = new NewExecSumm();
					newExecSumm.setCreated(createdDate);
					cell = curRow.getCell(c++);
					st = parseCell(cell);
					newExecSumm.setKey(st);
					
					if(c==2){
						c=3;
					}
					
					cell = curRow.getCell(c++);
					st = parseCell(cell);
					newExecSumm.setVal1(st);
					
					newExecSumm.setTableName("VINTAGE, STATUTORY COMPLIANCE & DEFAULT STATUS");
					newExecSumm.setMasterExcel(masterExcel);
					newExecSummList.add(newExecSumm);
					
				}
				//december quaterly : Last cell
			}
			
			
			//WORKING CAPITAL CYCLE
			
			for(r=49;r<53;r++){
				
				c=1;
				for(int i=1;i<=2;i++){
					
					curRow = executiveSumm.getRow(r);
					
					NewExecSumm newExecSumm = new NewExecSumm();
					newExecSumm.setCreated(createdDate);
					cell = curRow.getCell(c++);
					st = parseCell(cell);
					newExecSumm.setKey(st);
					
					if(c==2){
						c=3;
					}
					
					cell = curRow.getCell(c++);
					st = parseCell(cell);
					newExecSumm.setVal1(st);
					
					newExecSumm.setTableName("WORKING CAPITAL CYCLE");
					newExecSumm.setMasterExcel(masterExcel);
					newExecSummList.add(newExecSumm);
				}
			}
			
			//CMA
			r= 48;
			
			curRow = executiveSumm.getRow(r);
			
		    newExecSummSingle = new NewExecSumm();
		    newExecSummSingle.setCreated(createdDate);
			newExecSummSingle.setKey("");
					
			cell = curRow.getCell(7);
			st = parseCell(cell);
			newExecSummSingle.setVal1(st);
			
			cell = curRow.getCell(8);
			st = parseCell(cell);
			newExecSummSingle.setVal2(st);
			
			newExecSummSingle.setTableName("CMA");
			newExecSummSingle.setMasterExcel(masterExcel);
			newExecSummList.add(newExecSummSingle);	
			
			for( r=49;r<65;r++){
				
				   c=6;
					curRow = executiveSumm.getRow(r);
					
					NewExecSumm newExecSumm = new NewExecSumm();
					newExecSumm.setCreated(createdDate);
					cell = curRow.getCell(c++);
					st = parseCell(cell);
					newExecSumm.setKey(st);
							
					cell = curRow.getCell(c++);
					st = parseCell(cell);
					newExecSumm.setVal1(st);
					
					cell = curRow.getCell(c++);
					st = parseCell(cell);
					newExecSumm.setVal2(st);
					
					newExecSumm.setTableName("CMA");
					newExecSumm.setMasterExcel(masterExcel);
					newExecSummList.add(newExecSumm);
				}
			
			//RATIOS
			
			for( r=66;r<75;r++){
				
				   c=6;
					curRow = executiveSumm.getRow(r);
					
					NewExecSumm newExecSumm = new NewExecSumm();
					newExecSumm.setCreated(createdDate);
					cell = curRow.getCell(c++);
					st = parseCell(cell);
					newExecSumm.setKey(st);
							
					cell = curRow.getCell(c++);
					st = parseCell(cell);
					newExecSumm.setVal1(st);
					
					cell = curRow.getCell(c);
					st = parseCell(cell);
					newExecSumm.setVal2(st);
					
					newExecSumm.setTableName("RATIOS");
					newExecSumm.setMasterExcel(masterExcel);
					newExecSummList.add(newExecSumm);
				}
			excelTableDataDao.batchSaveExcel(newExecSummList);	
		
		//System.out.println("Executive Summary Saved!");

//		for (ExcelTableCoord table : list) {
//
//			if (table.getTableName().length() > 10 && table.getTableName().substring(0, 11).equals("bankDetails")) {
//				//bankDetailsIsEmpty(table, analysis);
//			}

//			if (table.getTableName().equals("execSummHeaders")) {
//
//			} else {

//				if (ignoreList.contains(table.getTableName())) {
//				} else {
//
//					String[] s = table.getXcord().split(",");
//					String[] s1 = table.getYcord().split(",");
//					int rowStart = Integer.parseInt(s[0]);
//					int rowEnd = Integer.parseInt(s[1]);
//					int colStart = Integer.parseInt(s1[0]);
//					int colEnd = Integer.parseInt(s1[1]);
//					Sheet cursheet = executiveSumm;
//					String tablename = table.getTableName();

//					if (tablename.equals("execSummValues")) {
//						cursheet = executiveSumm;
//					} 
//					else {
//						cursheet = analysis;
//					}

//					for (int i = rowStart; i < rowEnd; i++) {
//						curRow = cursheet.getRow(i);
//						for (int j = colStart; j <= colEnd; j++) {
//
//							simpleTableData = new ExcelTableData();
//							try {
//								cell = curRow.getCell(j);
//								st = parseCell(cell);
//								if (st.length() < 255) {
//									simpleTableData.setVal(st);
//								} else {
//									////System.out.println("Greater Than 255");
//									////System.out.println("Row-" + i + ", Column-" + j + "--" + tablename);
//									simpleTableData.setVal(st.substring(0, 254));
//								}
//								simpleTableData.setTname(tablename);
//								simpleTableData.setMasterExcel(masterExcel);
//								resultList.add(simpleTableData);
//
//							} catch (Exception e) {
//								////System.out.println("Error Log Messages - Null Pointer");
//								////System.out.println("Row-" + i + ", Column-" + j + "--" + tablename);
//							}
//						}
//						simpleTableData = new ExcelTableData();
//						simpleTableData.setVal("#!D!#");
//						simpleTableData.setTname(tablename);
//						simpleTableData.setMasterExcel(masterExcel);
//						resultList.add(simpleTableData);
//					}
//				}
//			}
//		}
		excelTableDataDao.batchSaveExcel(resultList);
		String json = gson.toJson(resultList);

	}

}
