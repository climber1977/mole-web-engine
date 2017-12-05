<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
	<base href="<%=basePath%>">
	<title>MyWebEngine移动端效果演示</title>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="SpringMvc,Restful,Maven">
	<meta http-equiv="description" content="MyWebEngine移动端效果演示">
</head>
<body>
	<h1>MyWebEngine移动端效果演示</h1>
	<a href="h5/test/charts.jsp">图表演示（柱状图、饼状图、曲线图）</a><br><br>
	<a href="h5/erp/queryhxjapproval.jsp">晓健ERP代办</a><br><br>
	
</body>
</html>
