package com.mole.webengine.dbapp;

public class ExecuteWhereSqlInfo{
	private long id;
	private long whereGroup;
	private String conj;
	private String expression;
	private boolean keepOriginal;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getWhereGroup() {
		return whereGroup;
	}
	public void setWhereGroup(long whereGroup) {
		this.whereGroup = whereGroup;
	}
	public String getConj() {
		return conj;
	}
	public void setConj(String conj) {
		this.conj = conj;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public boolean isKeepOriginal() {
		return keepOriginal;
	}
	public void setKeepOriginal(boolean keepOriginal) {
		this.keepOriginal = keepOriginal;
	}
	

}
