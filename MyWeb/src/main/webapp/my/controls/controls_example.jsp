<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/init.jsp" %>

<center><h3 class="page-title">控件示例</h3></center>
&nbsp&nbsp1、本示例展示，如何通过json文件在表单上显示html组件。
参见MyWeb\src\main\webapp\my\controls\controls_example.jsp文件。
<br>
&nbsp&nbsp2、基本原理:后台通过响应jsp的自定义标签lc:portal,
将MyWeb\src\main\webapp\WEB-INF\json\example\form_controls.json文件内容，格式华为html组件。
<br>
&nbsp&nbsp3、每种html元素模板请参见MyWeb\src\main\webapp\WEB-INF\template
<hr>
<lc:portal jsonDesc="example/form_controls.json"></lc:portal>   

