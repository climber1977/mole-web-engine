package com.mole.webengine.dbapp;

import java.util.Date;

public class ExecutePageDatablesInfo{
	private long id;
	private String uuid;
	private String tableName;
	private long descGroupid;
	private String templet;
	private String jsVerifyParam;
	private String displayQuery;
	private String displayImport;
	private String displayExport;
	private long executeid;
	private long whereGroup;
	private String myjs;
	private String dbs;
	private long cacheSecond;	//select缓存时间
	private Date refreshTime;	//每天刷新时间
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public long getDescGroupid() {
		return descGroupid;
	}
	public void setDescGroupid(long descGroupid) {
		this.descGroupid = descGroupid;
	}
	public String getTemplet() {
		return templet;
	}
	public void setTemplet(String templet) {
		this.templet = templet;
	}
	public String getJsVerifyParam() {
		return jsVerifyParam;
	}
	public void setJsVerifyParam(String jsVerifyParam) {
		this.jsVerifyParam = jsVerifyParam;
	}
	public String getDisplayQuery() {
		return displayQuery;
	}
	public void setDisplayQuery(String displayQuery) {
		this.displayQuery = displayQuery;
	}
	public String getDisplayImport() {
		return displayImport;
	}
	public void setDisplayImport(String displayImport) {
		this.displayImport = displayImport;
	}
	public String getDisplayExport() {
		return displayExport;
	}
	public void setDisplayExport(String displayExport) {
		this.displayExport = displayExport;
	}
	public long getExecuteid() {
		return executeid;
	}
	public void setExecuteid(long executeid) {
		this.executeid = executeid;
	}
	public long getWhereGroup() {
		return whereGroup;
	}
	public void setWhereGroup(long whereGroup) {
		this.whereGroup = whereGroup;
	}
	public String getMyjs() {
		return myjs;
	}
	public void setMyjs(String myjs) {
		this.myjs = myjs;
	}
	public String getDbs() {
		return dbs;
	}
	public void setDbs(String dbs) {
		this.dbs = dbs;
	}
	public long getCacheSecond() {
		return cacheSecond;
	}
	public void setCacheSecond(long cacheSecond) {
		this.cacheSecond = cacheSecond;
	}
	public Date getRefreshTime() {
		return refreshTime;
	}
	public void setRefreshTime(Date refreshTime) {
		this.refreshTime = refreshTime;
	}
}
