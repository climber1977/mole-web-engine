<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/init.jsp" %>
<center><h3 class="page-title">自定义sql查询1</h3></center>
&nbsp&nbsp1、本例请求到MyWeb\src\main\java\com\mole\web\controller\MyController.java的dtcustom1方法
<br>
&nbsp&nbsp2、dtcustom1内部通过调用com.mole.webengine.web.WebRequest的方法requestByUUID执行配置好的sql，获取数据。
<br>
&nbsp&nbsp3、需在wb_execute_sql中配置uuid为custom_query_1_1和custom_query_1_2的条目
<hr>
<table class="table table-striped table-bordered table-hover table-checkable" id="datatable_ajax">
    <thead>
        <tr role="row" class="heading">
            <th width="10%">id</th>
            <th width="10%">姓名</th>
            <th width="10%"> 英文姓名  </th>
        </tr>
    </thead>
    <tbody> </tbody>
</table>

<script type="text/javascript">
   jQuery(document).ready(function() {
	    dt = TableDatatablesAjax.init2("#datatable_ajax", "/myweb/dtcustom1?dbs=hr");
	    console.log(dt);
	});
</script>