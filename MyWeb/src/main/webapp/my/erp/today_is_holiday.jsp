<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/init.jsp" %>

<div>
	
	<label class="col-md-12 control-label">
	   	<button type="button" class="btn green" onclick='todayIsHoliday()'>今天是否是节假日</button>                
	</label>
</div>


<script type="text/javascript">

function todayIsHoliday(){
	params = {};
	myajax("todayIsHoliday?dbs=erp&act=plugin", params, function (result){		
		if(result.ret){
			alert("Today is a holiday.");
		} else {
			alert("Today is a workday.");
		}
	}, function(x, e){
		alert(e);
	} )
}
	
</script>
