<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/init.jsp" %>
<center><h3 class="page-title">表格样式使用json文件控制</h3></center>
&nbsp&nbsp1、本例用“&lt;lc:portal jsonDesc="example/datatables.json"&gt;&lt;/lc:portal&gt;”代替
“通过jsp的自定义标签，生成前端的database相关代码”中的
“&lt;lc:table uuid="jsp_tab_datatable_get_user" jsonDesc="example/datatables_jsp.json" dbs="hr"&gt;&lt;/lc:table&gt;”

<br>
&nbsp&nbsp2、通过jsp自定义标签生成的database的html和js相关代码和“ajax请求展示datatable的数据”中的代码一致。
<br>
3、在wb_page_datatables表中配置uuid为json_datatable_get_user的条目。
<hr>
<lc:portal jsonDesc="example/query_common.json"></lc:portal>
<lc:portal jsonDesc="example/datatables.json"></lc:portal>
<lc:portal jsonDesc="example/datatables_import.json"></lc:portal>

