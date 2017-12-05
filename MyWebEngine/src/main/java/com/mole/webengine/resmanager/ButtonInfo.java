package com.mole.webengine.resmanager;

public class ButtonInfo {
	private long id;
	private String uuid;
	private String name;
	private String caption;
	private String url;
	private boolean hide;	//没有权限时，是否隐藏
	private String template;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUid() {
		return uuid;
	}
	public void setUid(String uuid) {
		this.uuid = uuid.toLowerCase();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public boolean getHide() {
		return hide;
	}
	public void setHide(boolean hide) {
		this.hide = hide;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
}
