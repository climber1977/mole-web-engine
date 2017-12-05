<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/init.jsp" %>
<br>
<div>
	<div class="col-md-12 ">
		<lc:portal jsonDesc="erp/time_is_range_except_holiday.json"></lc:portal>
	</div>
	<label class="col-md-12 control-label">
	   	<button type="button" class="btn green" onclick='timeIsRangeExceptHoliday()'>当前时间是否在订购时间提醒范围内</button>                
	</label>
</div>


<script type="text/javascript">
function timeIsRangeExceptHoliday(){
	params = getParams();
	myajax("timeIsRangeExceptHoliday?dbs=erp&act=plugin", params, function (result){		
		if(result.ret){
			alert("当前时间在订购时间提醒范围内");
		} else {
			alert("当前时间不在订购时间提醒范围内");
		}
	}, function(x, e){
		alert(e);
	} )
}
</script>
