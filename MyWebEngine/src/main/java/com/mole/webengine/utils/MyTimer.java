package com.mole.webengine.utils;

public class  MyTimer{
	private long startTime = System.currentTimeMillis();	
	private static long refreshTableSeconds = 0;
	
	public static void setRefreshTableSeconds(long refreshTableSeconds){
		MyTimer.refreshTableSeconds = refreshTableSeconds;
	}
	
	public void startClocking(){
		startTime = System.currentTimeMillis();
	}
	
	public long getTimeDiff(){
		return System.currentTimeMillis()-startTime;
	}
	
	public long getSecondDiff(){
		return (System.currentTimeMillis()-startTime)/1000;
	}
	
	public boolean isExpiry(long seconds){
		return ((System.currentTimeMillis()-startTime)/1000) > seconds;
	}
	
	public boolean isRefreshTable() throws Exception{
		return ((System.currentTimeMillis()-startTime)/1000) > refreshTableSeconds;
	}
}
