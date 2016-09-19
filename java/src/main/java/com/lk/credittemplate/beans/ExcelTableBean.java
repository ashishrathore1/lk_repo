/**
 * 
 */
package com.lk.credittemplate.beans;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author shivendra
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExcelTableBean {

	private List<ExcelTableCoordBean> excelTableCoordBean;

	public List<ExcelTableCoordBean> getExcelTableCoordBean() {
		return excelTableCoordBean;
	}

	public void setExcelTableCoordBean(List<ExcelTableCoordBean> excelTableCoordBean) {
		this.excelTableCoordBean = excelTableCoordBean;
	}

}
