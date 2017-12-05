package com.mole.webengine.dbapp;

public class ImportConfigInfo{
	private long id;
	private String tableName;
	private String idField;	
	private String generateIdSql;
	private String uniqueField;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getIdField() {
		return idField;
	}
	public void setIdField(String idField) {
		this.idField = idField;
	}
	public String getGenerateIdSql() {
		return generateIdSql;
	}
	public void setGenerateIdSql(String generateIdSql) {
		this.generateIdSql = generateIdSql;
	}
	public String getUniqueField() {
		return uniqueField;
	}
	public void setUniqueField(String uniqueField) {
		this.uniqueField = uniqueField;
	}
	
}
