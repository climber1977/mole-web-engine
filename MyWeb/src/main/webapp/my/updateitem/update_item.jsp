<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/init.jsp" %>
<center><h3 class="page-title">更新表单数据</h3></center>
&nbsp&nbsp1、本例展示如何动态更新表单数据。
<br>
&nbsp&nbsp2、在wb_execute_sql表中配置uuid为update_item_in_web的条目。
<hr>
<br><div id="show"> show </div><br> 

<lc:portal jsonDesc="example/update_item.json"></lc:portal>

<script type="text/javascript">
	myAjaxReq2Item("update_item_in_web?rtype=2",  {name:"孙悟空"});
</script>

