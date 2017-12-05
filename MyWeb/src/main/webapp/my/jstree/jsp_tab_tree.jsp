<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/init.jsp" %>
<center><h3 class="page-title">通过jsp的自定义标签，生成前端的树形组件代码</h3></center>
&nbsp&nbsp1、通过jsp自定义标签生成的tree的html和js相关代码和“ajax请求展示树形组件”中的代码一致。
<hr>
<lc:tree uuid="ajax_tree_get_department" rootid="1" dbs="wt"></lc:tree>
