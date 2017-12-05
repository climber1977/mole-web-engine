package com.mole.webengine.dbapp;

public class ExecutePageDatablesDescInfo{
	private long id;
	private long groupid;
	private boolean checkbox;
	private boolean sorting;
	private String width;
	private String field;
	private String alias;
	private String html;
	private String trTemplate;
	private String tdTemplate;
	private int showOrder;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getGroupid() {
		return groupid;
	}
	public void setGroupid(long groupid) {
		this.groupid = groupid;
	}
	public boolean isCheckbox() {
		return checkbox;
	}
	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}
	public boolean isSorting() {
		return sorting;
	}
	public void setSorting(boolean sorting) {
		this.sorting = sorting;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getTrTemplate() {
		return trTemplate;
	}
	public void setTrTemplate(String trTemplate) {
		this.trTemplate = trTemplate;
	}
	public String getTdTemplate() {
		return tdTemplate;
	}
	public void setTdTemplate(String tdTemplate) {
		this.tdTemplate = tdTemplate;
	}
	public int getShowOrder() {
		return showOrder;
	}
	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
	}
}
