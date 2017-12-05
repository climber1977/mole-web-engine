<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/init.jsp" %>
<center><h3 class="page-title">通过jsp的自定义标签，生成前端的database相关代码</h3></center>
&nbsp&nbsp1、相关代码包括查询条件（参见控件示例），database的html和js相关代码，导入（参见控件示例）。
<br>
&nbsp&nbsp2、通过jsp自定义标签生成的database的html和js相关代码和“ajax请求展示datatable的数据”中的代码一致。
<br>
&nbsp&nbsp3、在wb_page_datatables表中配置uuid为jsp_tab_datatable_get_user的条目。
<br>
&nbsp&nbsp4、wb_page_datatables表中desc_groupid字段即wb_page_datatables_desc表的groupid
<br>
&nbsp&nbsp5、在wb_page_datatables表中配置每一类的效果，
“姓名”列大字体，“删除”、“修改”列的连接、“测试”列表头的大字体，内容的文本框等效果，都是在wb_page_datatables_desc表中配置的。
<br>
&nbsp&nbsp6、本例使用wb_execute_where_sql表中配置的查询条件，还需配置wb_page_datatables表的where_group字段。 
<br>
&nbsp&nbsp7、请在工号栏输入“10%' or '%'='”验证sql防注入。 
<hr>
<lc:portal jsonDesc="example/query_tab_mysql.json"></lc:portal>
<lc:table uuid="jsp_tab_datatable_get_user" jsonDesc="example/datatables_jsp.json" dbs="hr"></lc:table>
<lc:portal jsonDesc="example/datatables_import.json"></lc:portal>

