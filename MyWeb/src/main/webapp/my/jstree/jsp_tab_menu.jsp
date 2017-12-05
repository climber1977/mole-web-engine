<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/init.jsp" %>
<center><h3 class="page-title">菜单的树形展示</h3></center>
&nbsp&nbsp1、参考“通过jsp的自定义标签，生成前端的树形组件代码”
<hr>
<lc:tree uuid="json_tree_get_menu" rootid="0" dbs="hr"></lc:tree>
