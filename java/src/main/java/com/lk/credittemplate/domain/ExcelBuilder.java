package com.lk.credittemplate.domain;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.lk.credittemplate.beans.TmpExcelDTO;

public class ExcelBuilder extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// get data model which is passed by the Spring container
		@SuppressWarnings("unchecked")
		Map<String, Object> execSummDetailsMap = (Map<String, Object>) model.get("valuemap");
		
		List<TmpExcelDTO> tmpExcelDTOList = (List<TmpExcelDTO>)execSummDetailsMap.get("headers");
		
		Sheet sheet = workbook.createSheet("Executive Summary");
		sheet.setDefaultColumnWidth(30);

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "inline; filename=.xlsx");

		// create style for header cells
		CellStyle style = workbook.createCellStyle();
		org.apache.poi.ss.usermodel.Font font = workbook.createFont();
		font.setFontName("Arial");
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFillForegroundColor(HSSFColor.BLUE.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);

		CellStyle greenstyle = workbook.createCellStyle();
		greenstyle.setAlignment(CellStyle.ALIGN_CENTER);
		greenstyle.setFillForegroundColor(HSSFColor.GREEN.index);
		greenstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setColor(HSSFColor.WHITE.index);
		greenstyle.setFont(font);
		int i = 0;
		int index;

		// create header row
		Row header = sheet.createRow(0);

		header.createCell(i).setCellValue("CREDIT ANALYSIS - EXECUTIVE SUMMARY");
		header.getCell(i++).setCellStyle(style);


		header = sheet.createRow(1);
		

	
		header.createCell(i).setCellValue("#");
		header.getCell(i++).setCellStyle(style);

		header.createCell(i).setCellValue("Analysis Variables");
		header.getCell(i++).setCellStyle(style);

		header.createCell(i).setCellValue("Values");
		header.getCell(i++).setCellStyle(style);

		header.createCell(i).setCellValue("Analyst");
		header.getCell(i++).setCellStyle(style);

		
	}

}
