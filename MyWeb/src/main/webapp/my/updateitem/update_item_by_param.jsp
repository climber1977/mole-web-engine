<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/init.jsp" %>
<center><h3 class="page-title">带有查新条件的更新表单数据</h3></center>
&nbsp&nbsp1、参见“控件示例”和“更新表单数据”。
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
<hr>
<lc:portal jsonDesc="example/update_item_by_param.json"></lc:portal>     

<script type="text/javascript">
	myAjaxReq2Item("query_item_by_param?rtype=2",  {name:"孙悟空"});
</script>
