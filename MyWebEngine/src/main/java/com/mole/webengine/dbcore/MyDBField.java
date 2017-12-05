package com.mole.webengine.dbcore;

import java.sql.Types;

public class MyDBField {
	private int index;
	private String columnName;
	private String columnLabel;
	private String tableName;
	private int columnType;
	private String className;
	private String columnTypeName;
	private boolean canAccess;
	private boolean show;
	
	public MyDBField(){
		setIndex(-1);
		columnName = "";
		columnLabel = "";
		tableName = "";
		columnType = 0;
		columnTypeName = "";
		canAccess = true;
		show = true;
	}
	
	public boolean isCanAccess() {
		return canAccess;
	}
	public void setCanAccess(boolean canAccess) {
		this.canAccess = canAccess;
	}
	
	public boolean isShow() {
		return show;
	}
	public void setShow(boolean show) {
		this.show = show;
		if(!show){
			setColumnType(Types.VARCHAR);
			setColumnTypeName("String");
			setColumnClassName("java.lang.String");
		}
	}
	
	//columnName
	public String getColumnName() {
		return columnName;
	}
	public String getColumnLabel() {
		return columnLabel;
	}
	public String getTableName() {
		return tableName;
	}
	
	//tableName
	public void setTableName(String tableName) {
		this.tableName = tableName.toLowerCase();
	}
	public void setColumnName(String columnName) {		
		this.columnName = columnName.toLowerCase();
	}
	public void setColumnLabel(String columnLabel) {		
		this.columnLabel = columnLabel.toLowerCase();
	}
	//columnType
	public int getColumnType() {
		return columnType;
	}
	public void setColumnType(int columnType) {
		this.columnType = columnType;
	}
	public String getColumnClassName() {
		return className;
	}
	public void setColumnClassName(String className) {
		this.className = className;
	}
	//columnTypeName
	public String getColumnTypeName() {
		return columnTypeName;
	}
	public void setColumnTypeName(String columnTypeName) {
		this.columnTypeName = columnTypeName;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
