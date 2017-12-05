<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/init.jsp" %>
<center><h3 class="page-title">ajax查询分页查询数据库，返回list</h3></center>
&nbsp&nbsp1、先查询，后修改，不能修改id和用户名。
<br>
&nbsp&nbsp2、在wb_execute_sql中配置update_item_in_db条目。
<hr>
<br><div id="show"> show </div>
<br>

<script type="text/javascript">
	myAjaxReq2Item("test_execute_select_sql?dbs=hr&act=datatable&subact=sql",  {start:5, length:3});
	//myAjaxReq2Item("query_item_by_param?rtype=2",  {name:"孙悟空"});
</script>
