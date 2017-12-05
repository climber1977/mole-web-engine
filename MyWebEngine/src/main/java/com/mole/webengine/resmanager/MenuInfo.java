package com.mole.webengine.resmanager;

import java.util.ArrayList;

public class MenuInfo {
	private long id;
	private long parentid;
	private String caption;
	private String url;
	private String params;

	private String templet;
	private int showOrder;
	
	ArrayList<MenuInfo> subMenu = new ArrayList<MenuInfo>();	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getParentid() {
		return parentid;
	}
	public void setParentid(long parentid) {
		this.parentid = parentid;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}

	public String getTemplet() {
		return templet;
	}
	public void setTemplet(String templet) {
		this.templet = templet;
	}
	public int getShowOrder() {
		return showOrder;
	}
	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
	}
	
	public ArrayList<MenuInfo> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(ArrayList<MenuInfo> subMenu) {
		this.subMenu = subMenu;
	}
	
	public void addSubMenu(MenuInfo menu){
		subMenu.add(menu);
	}
}
