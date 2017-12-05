package com.mole.webengine.dbapp;

import java.util.Date;

public class ExecuteSqlInfo {
	private long id;
	private String uuid;
	private String expression;
	private String dbs;
	private long whereGroup;
	private int requestType;
	private boolean keepOriginal;	//对字符串类型不做'到\'的替换
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
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public String getDbs() {
		return dbs;
	}
	public void setDbs(String dbs) {
		this.dbs = dbs;
	}
	public long getWhereGroup() {
		return whereGroup;
	}
	public void setWhereGroup(long whereGroup) {
		this.whereGroup = whereGroup;
	}
	public int getRequestType() {
		return requestType;
	}
	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}
	public boolean isKeepOriginal() {
		return keepOriginal;
	}
	public void setKeepOriginal(boolean keepOriginal) {
		this.keepOriginal = keepOriginal;
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
