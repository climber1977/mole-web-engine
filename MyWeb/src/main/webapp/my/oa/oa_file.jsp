<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/init.jsp" %>
<p><font color="#FF0000">&nbsp&nbsp&nbsp关联流程，查询很慢，所以需要再开发</font> <p>
<lc:portal jsonDesc="oa/oa_file_query.json"></lc:portal>
<lc:table uuid="oa_query_file" jsonDesc="oa/oa_file.json" dbs="oa"></lc:table>


