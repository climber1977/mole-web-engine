<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/init.jsp" %>
<center><h3 class="page-title">jsp表现实现的按钮组建展示</h3></center>
&nbsp&nbsp1、使用“孙悟空”登录能够看到“查询”按钮，使用“猪八戒”登录不看到“查询”、“删除”两个按钮，。
<br>
&nbsp&nbsp2、在wb_button表中配置uuid为test_btn_query和test_btn_delete的条目。
<br>
&nbsp&nbsp3、在wb_button_role_relation表中配置按钮和角色的关系。
<hr>
<lc:button uuid="test_btn_query" onClick="alert('hello')"/>
<lc:button uuid="test_btn_delete" onClick="alert('world')"/>
       	     

