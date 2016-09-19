package com.lk.credittemplate.beans;

public class StatusListBean {

	private String name;
	private boolean value;
	private boolean dropdown;
	
	public boolean isDropdown() {
		return dropdown;
	}

	public void setDropdown(boolean dropdown) {
		this.dropdown = dropdown;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

}
