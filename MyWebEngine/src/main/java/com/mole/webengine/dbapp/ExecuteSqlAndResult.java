package com.mole.webengine.dbapp;

import java.util.Date;

import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.security.SecurityUser;
import com.mole.webengine.system.SysParams;
import com.mole.webengine.utils.DateTimeUtils;

public class ExecuteSqlAndResult {
	private String uuid;
	private String sql;	
	private int requestType;
	private long cacheSecond;	//select缓存时间
	private Date refreshTime;	//下一次刷新时间	
	private Date nextRefreshTime;
	private Date lastRefreshTime;		//最后刷新时间
	private MyResult myResult;
	private long timestamp;
	
	public ExecuteSqlAndResult(String uuid, String sql, int requestType, 
			long cacheSecond, Date refreshTime){
		this.uuid = uuid;
		this.sql = sql;
		this.requestType = requestType;
		this.cacheSecond = cacheSecond;
		this.refreshTime = refreshTime;
	}	
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public int getRequestType() {
		return requestType;
	}
	public void setRequestType(int requestType) {
		this.requestType = requestType;
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
	public void setRefreshTime(Date nextRefreshTime) {
		this.refreshTime = nextRefreshTime;
	}
	public boolean isNeedCache() {
		if(cacheSecond > 0){
			return true;
		}
		if(refreshTime != null){
			return true;
		}
		return false;
	}
	public MyResult getMyResult() {
		if(!isNeedCache()){
			//未设置任何刷新条件
			return null;
		}
		if(cacheSecond > 0 && System.currentTimeMillis()-timestamp >= cacheSecond*1000){
			//按间隔的x秒刷新，已过期
			return null;
		}

		if(refreshTime != null){
			if(lastRefreshTime == null){
				return null;
			}
			Date curDate = DateTimeUtils.getCurDateTime();
			if(curDate.after(nextRefreshTime) && lastRefreshTime.before(nextRefreshTime)){
				return null;
			}
		}
		return myResult;
	}
	public void setMyResult(MyResult myResult) {
		this.myResult = myResult;
		this.timestamp = System.currentTimeMillis();
		this.lastRefreshTime = DateTimeUtils.getCurDateTime();
		this.nextRefreshTime = DateTimeUtils.getTomorrowTime(refreshTime);
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public Date getLastRefreshTime() {
		return lastRefreshTime;
	}
	public void setLastRefreshTime(Date lastRefreshTime) {
		this.lastRefreshTime = lastRefreshTime;
	}
	private void appendKey(StringBuilder builder, String key, String value){
		builder.append(key);
		builder.append(":");
		builder.append(value);
		builder.append(";");
	}
	public String generateKey() throws Exception{
		StringBuilder builder = new StringBuilder();
		if(SysParams.isUseColumnPrivilege()){
			SecurityUser u = SecurityUser.getThreadLocalUser();
			long userId = -1;
			if(u != null){
				userId = u.getUserId();
			}
			appendKey(builder, "userId", String.valueOf(userId));
		}
		appendKey(builder, "uuid", uuid);
		appendKey(builder, "sql", sql);
		appendKey(builder, "returnType", String.valueOf(requestType));
		return builder.toString();
	}
}
