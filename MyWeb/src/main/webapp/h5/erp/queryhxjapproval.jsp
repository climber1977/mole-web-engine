<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/init.jsp" %>
<!DOCTYPE html>
<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>只有晓健审批的</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />       
        <style>
            table{
            	width: 100%;
            	border: 0;
            	border-spacing:0;
            }
            th,td{
	            display: table-cell;
	    		vertical-align: inherit;
            }
	        tr{
	        	background:#fff;
	        }
        	tr:nth-child(2n+1) {
        		background:#eef1f5;
        	}
        	th{
        		background:#fff;
        		font: 14px/ 1.45 '微软雅黑';
        		padding:8px;
	        	border-bottom: 1px solid #ddd;
        	}
        	td{
        		font: 13px/1.45 '微软雅黑';
        		padding: 8px;
        		color:#666;
	        	border-bottom: 1px solid #e7ecf1;
        	}
        </style>
    <body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
       
       <div class="page-content">
        <div class="portlet light bordered">
            <div class="bootstrap-table">
               <table class="table table-hover" style="width: 472px;">
                    <thead>
                        <tr>
                            <th class="col-md-2" style="" data-field="id" tabindex="0">
                                <div class="th-inner ">申请人</div>
                            </th>
                            <th class="col-md-6" style="" data-field="name" tabindex="0">
                                <div class="th-inner ">
                                    <i class="glyphicon glyphicon-star"></i>主题 
                                </div>
                                <div class="fht-cell"></div>
                            </th>
                            <th class="col-md-4" style="" data-field="price" tabindex="0">
                                <div class="th-inner ">
                                    <i class="glyphicon glyphicon-heart"></i>申请时间
                                </div>
                                <div class="fht-cell"></div>
                            </th>
                        </tr>
                   </thead>
                    <tbody id="tdata">
                        <tr data-index="0" bgcolor="#eef1f5">
                            <td class="col-md-2" style="">0</td>
                            <td class="col-md-6" style="">test0</td>
                            <td class="col-md-4" style="">$0</td>
                        </tr>
                        
                    </tbody>
                </table>
            </div>
            
        </div>
       </div>
      
        <script src="/myweb/metronic/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
        <script src="/myweb/myassets/scripts/global_js.jsp"></script>
    	<script src="/myweb/myassets/scripts/myutils.js" type="text/javascript"></script>
 <script type="text/javascript">
	        function successCallback(result){
	        	 var show = document.getElementById("tdata");
	        	 var line = "";	        	 
	        	 for(var r in result){
	        		 //show.innerHTML += result[r].starter_name + "&nbsp" + result[r].flow_subject + "&nbsp" + result[r].flow_start_time;
	        		 //show.innerHTML += "<br>";
	        		 line += '<tr data-index="0">';
	        		 
	        		 line += '<td class="col-md-2" style="">' + result[r].starter_name + '</td>';
	        		 line += '<td class="col-md-6" style="">' + result[r].flow_subject + '</td>';
	        		 line += '<td class="col-md-4" style="">' + result[r].flow_start_time + '</td>';
	        		 line += '</tr>';
        	      }
	        	 show.innerHTML = line;
	        }
	        function failedCallback(result){
	        }
        	myajax("erp_query_hxj_approval?rtype=2&dbs=erp",  {}, successCallback, failedCallback);
		</script>
</body>

