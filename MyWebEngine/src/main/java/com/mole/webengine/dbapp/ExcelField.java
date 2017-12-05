package com.mole.webengine.dbapp;

public class ExcelField {
	private int cellNum;
	private String name;
	
	public int getCellNum() {
		return cellNum;
	}
	public void setCellNum(int cellNum) {
		this.cellNum = cellNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean equalsIgnoreCase(String name) {
		return this.name.equalsIgnoreCase(name);
	}
}
