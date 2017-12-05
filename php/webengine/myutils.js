function myTrim(x) {
    return x.replace(/^\s+|\s+$/gm,'');
}
function getParams(){
	var params={};
	addItemToJson(params);
	return params;
}
function getSelectItem(sitem){
	for(var i=0;i<sitem.options.length;i++){
		if(sitem.options[i].selected){
			return sitem.options[i].text;
		}
	}
	return "";
}
function getSelectItemByName(name){
	var param = "SELECT[name='" + name +"']"
	return getSelectItem($(param)[0]);
}
function addItemToJson(j){
	$('.param').each(function(i,item){ 
		if(item.tagName == "SELECT"){
			j[item.name] = getSelectItem(item);
		}else{
			j[item.name] = myTrim(item.value);	
		}		
	})
}

function getRequestUrl(){
	return "/myweb/request/"
}

function myajaxWithDataType(requestUrl, params, successCallback, failedCallback, datatype){
	requestUrl = getRequestUrl() + requestUrl;
	
	$.ajax({
    url: requestUrl,
    type: "POST",   
    dataType: datatype,
    "Accept":"*/*",
    contentType:"application/x-www-form-urlencoded; charset=utf-8",
    data: params,
    success: function (result) {
    	successCallback(result);    
    },
    error: function (x, e) {
    	failedCallback(x, e);
    }
	});
}
function myajax(requestUrl, params, successCallback, failedCallback){
	myajaxWithDataType(requestUrl, params, successCallback, failedCallback, "json");
	requestUrl = getRequestUrl() + requestUrl;
}
function getUpdateParams(){
	var params={};
	$('.vajax').each(function(i,item){ 
		params[item.name] = item.value;	
	})
	return params;
}
function showResult(result){
	var show = document.getElementById("show");
	if("undefined" != typeof show && show != null){
		show.innerHTML = result;
	}    	
}
function updateajax(requestUrl){
	var params = getUpdateParams();
	myajax(requestUrl, params, function(result){
		var show = document.getElementById("show");
    	if("undefined" != typeof show && show != null){
    		show.innerHTML = JSON.stringify(result);
    	}    	
	}, function(x, e){
			console.log(x);
    	console.log(e);
	});	
}
function showRequestResultAjax(requestUrl){
	var params = getUpdateParams();
	myajax(requestUrl, params, function(r){
		showResult(r.result);		
	}, function(x, e){
		showResult(JSON.stringify(e + x));
		console.log(x);
    	console.log(e);
	});	
}
function ajaxFileUpload(requestUrl, fileElementId) {
	requestUrl = getRequestUrl() + requestUrl;
	
	$.ajaxFileUpload({
		url : requestUrl,
		secureuri : false,
		fileElementId : fileElementId,
		dataType : 'json',
		data : {},
		success : function(data, status) {
			alert('导入成功');
			$('#importwin').modal('hide');
		},
		error : function(data, status, e) {
			alert('导入失败');
		}
	})

	return false;

}
function downLoad(strUrl) { 
	strUrl = getRequestUrl() + strUrl + "&act=export";
	
    var form = $("<form>");   //定义一个form表单
    form.attr('style', 'display:none');   //在form表单中添加查询参数
    form.attr('target', '');
    form.attr('method', 'post');
    form.attr('action', strUrl);

    var input1 = $('<input>');
    input1.attr('type', 'hidden');
    input1.attr('name', 'strUrl');
    input1.attr('value', strUrl);
    $('body').append(form);  //将表单放置在web中 
    form.append(input1);   //将查询参数控件提交到表单上
    form.submit();
 }
function myAjaxReq(requestUrl, params){
	myajax(requestUrl, params, function(result){
		var show = document.getElementById("show");
    	if("undefined" != typeof show && show != null){
    		show.innerHTML = JSON.stringify(result);
    	}
    	json2Item(result);
	}, function(x, e){
			console.log(x);
    	console.log(e);
	});	
}

function myAjaxReq2Item(requestUrl, params){
	myAjaxReq(requestUrl, params);
}
function myAjaxReq2Item2(requestUrl){
	var params = getParams();
	myAjaxReq(requestUrl, params, 1);
}
function setItemValue(item, idOrName, json){
	idOrName = idOrName.toLowerCase();
	if(idOrName == ""){
		return;
	}
	var strs= new Array(); //定义一数组 
	strs=idOrName.split("#"); //字符分割 
	
	var key = strs[0];
	var index = 0;
	if(strs.length >= 2){
		index = parseInt(strs[1])
	}
	
	if(json[index] == null){
		return;
	}
	else if("undefined" == typeof json[index]){
		return;
	}
	if(json[index][key] == null){
		return;
	}
	else if("undefined" == typeof json[index][key]){
		return;
	}
	item.value = json[index][key];			
}

function json2Item(json){
	console.log(json);
	console.log(json[0]);
	$('.vajax').each(function(i,item){ 
		console.log(item.id + " " + item.tagName);
		if(item.tagName = 'INPUT'){
			setItemValue(item, item.name, json);
		}
	})
}
function setItemValue2(itemName, value){
	var item = document.getElementsByName(itemName);
	if("undefined" != typeof item && item != null){
		item[0].value = value;
	}		
}
function cleanItemValue2(itemName){
	setItemValue2(itemName, "");	
}
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

function myBrowser(){
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
    var isOpera = userAgent.indexOf("Opera") > -1;
    if (isOpera) {
        return "Opera"
    }; //判断是否Opera浏览器
    if (userAgent.indexOf("Firefox") > -1) {
        return "FF";
    } //判断是否Firefox浏览器
    if (userAgent.indexOf("Chrome") > -1){
		  return "Chrome";
		 }
    if (userAgent.indexOf("Safari") > -1) {
        return "Safari";
    } //判断是否Safari浏览器
    if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {
        return "IE";
    }; //判断是否IE浏览器
    if (userAgent.indexOf("Mozilla") > -1) {
        return "IE";
    }; //判断是否IE浏览器
}
function openWindow(requesturl, wh, hg,callback) {
	var xLeft=(window.screen.width-wh)/2;
    var yTop=(window.screen.height-hg)/2;
	if(window.showModalDialog){ 
		var params = "status:false;dialogWidth:"+wh+"px;dialogHeight:"+hg+"px;scrollbar=no;help: no;resizable:no;status:no;";
		var msg = window.showModalDialog(requesturl, "_blank", params);
		if( msg == null){
			 msg = getCookie("returnValue");
		}
		callback(msg);
	} else {	
		//var params = "toolbar=no,location=no,directories=no,status=no,menubar=no,resizable=yes,scrollbars=auto,width="+wh+",height="+hg+",left="+xLeft+",top="+yTop;		
		var params = "toolbar=no,location=no,directories=no,status=no,menubar=no,resizable=yes,scrollbars=auto,width="+wh+",height="+hg+",left="+xLeft+",top=10";
	    var outerWindow = window.open(requesturl, "_blank", params);
	    messenger.addTarget(outerWindow, 'childWindow');	   
    };
}
function setCookie(name,value)
{	
	document.cookie = name + "="+ escape (value) + ";domain=.mole.com;path=/"  
}

function getCookie(name)
{
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg))
		return unescape(arr[2]).substr(1);
	else
		return null;
}