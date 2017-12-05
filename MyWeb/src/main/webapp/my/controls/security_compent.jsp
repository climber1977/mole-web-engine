<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/init.jsp" %>

<center><h3 class="page-title">控件安全控制示例</h3></center>
&nbsp&nbsp1、本示例展示，使用猪八戒或孙悟空登录，显示不同的html组件。
<br>
&nbsp&nbsp2、在wb_compent_role_relation表中将json文件中的html组件id和角色关联，
即可根据登录用户的角色属性，控制html组件是否显示。
<hr>
<lc:portal jsonDesc="example/security_compent.json"></lc:portal>   

