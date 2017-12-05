<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/init.jsp" %>
<center><h3 class="page-title">树形组件样式使用json文件控制</h3></center>
&nbsp&nbsp1、本例用“&lt;lc:portal jsonDesc="example/tree.json"&gt;&lt;/lc:portal&gt;”代替
“通过jsp的自定义标签，生成前端的树形组件代码”中的
“&lt;lc:tree uuid="ajax_tree_get_department" rootid="1" dbs="wt"&gt;&lt;/lc:tree&gt;”

<hr>
<lc:portal jsonDesc="example/tree.json"></lc:portal>