<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/init.jsp" %>
<center><h3 class="page-title">修改数据库中的数据</h3></center>
&nbsp&nbsp1、先查询，后修改，不能修改id和用户名。
<br>
&nbsp&nbsp2、在wb_execute_sql中配置update_item_in_db条目。
<hr>
<br><div id="show"> show </div>
<br>
<div>
	<div class="col-md-11 ">
		<lc:portal jsonDesc="example/query_common.json"></lc:portal>
	</div>
	<label class="col-md-1 control-label">
	   	<button type="button" class="btn green" onclick='myAjaxReq2Item2("query_item_by_param?rtype=2")'>查询</button>                
	</label>
</div>
<div>
	<div class="col-md-12 ">
		<lc:portal jsonDesc="example/show_user.json"></lc:portal> 
	</div>
	<label class="col-md-12 control-label">
		<center>
	   	<button type="button" class="btn green" onclick='updateajax("update_item_in_db?rtype=0")'>修改</button>   
	   	</center>             
	</label>
</div>

<script type="text/javascript">
	myAjaxReq2Item("query_item_by_param?rtype=2",  {name:"孙悟空"});
</script>
