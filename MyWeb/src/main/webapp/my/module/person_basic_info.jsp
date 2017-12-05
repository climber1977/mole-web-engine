<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/init.jsp" %>
<center><h3 class="page-title">基本信息</h3></center>
<lc:portal jsonDesc="example/update_item.json"></lc:portal>

<script type="text/javascript">
	myAjaxReq2Item("query_cur_user?rtype=2",  {});
</script>


