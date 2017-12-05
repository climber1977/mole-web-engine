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
	<title>MyWebEngine引擎演示</title>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="SpringMvc,Restful,Maven">
	<meta http-equiv="description" content="基于SpringMvc实现Restful架构风格">
</head>
<body>
	<h1>MyWebEngine引擎演示</h1>
	
	<h3>框架</h3>
	<a href="my/index.jsp">主页</a><br><br>
	<a href="my/page_user_login_1.html">
		登录&nbsp
		请使用<font color="red">zhubajie</font>或<font color="red">sunwukong</font>登录，密码任任意。
	</a>
	<br><br>
	<a href="h5/index.jsp">移动端效果演示</a><br><br>
</body>
</html>
