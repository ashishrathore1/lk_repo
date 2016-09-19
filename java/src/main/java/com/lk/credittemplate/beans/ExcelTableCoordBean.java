/**
 * 
 */
package com.lk.credittemplate.beans;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author shivendra
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExcelTableCoordBean {

	private String versionId;
	private String tableName;
	private String xcord;
	private String ycord;
	private boolean keyval;
	private int div;
	private int tableId;
	private String desc;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public int getDiv() {
		return div;
	}

	public void setDiv(int div) {
		this.div = div;
	}

	public boolean isKeyval() {
		return keyval;
	}

	public void setKeyval(boolean keyval) {
		this.keyval = keyval;
	}

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getXcord() {
		return xcord;
	}

	public void setXcord(String xcord) {
		this.xcord = xcord;
	}

	public String getYcord() {
		return ycord;
	}

	public void setYcord(String ycord) {
		this.ycord = ycord;
	}

}
