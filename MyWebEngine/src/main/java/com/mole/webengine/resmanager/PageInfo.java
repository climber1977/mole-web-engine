package com.mole.webengine.resmanager;

public class PageInfo {
	private String uuid;
	private String zoneUUID;
	private String zoneType;
	
	public String getZoneUUID() {
		return zoneUUID;
	}
	public void setZoneUUID(String zoneUUID) {
		this.zoneUUID = zoneUUID;
	}
	public String getZoneType() {
		return zoneType;
	}
	public void setZoneType(String zoneType) {
		this.zoneType = zoneType;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
