<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/init.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>图标展示</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />        
    </head>
    <body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
        <script src="/myweb/metronic/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/highcharts/js/highcharts.js" type="text/javascript"></script>
        <script src="/myweb/myassets/pages/scripts/charts-highcharts.js" type="text/javascript"></script>
        <script src="/myweb/myassets/scripts/global_js.jsp"></script>
        <script src="/myweb/myassets/scripts/myutils.js" type="text/javascript"></script>
        <lc:portal jsonDesc="erp/erp_bx_months_curve.json"></lc:portal>
    </body>

</html>