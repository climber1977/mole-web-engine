<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.mole.webengine.system.SysParams"%>

function getRequestUrl(){
	return "<%=SysParams.getRequestUrl()%>"
}

var testServerUrl = "<%=SysParams.getStringParam("testServerUrl")%>";