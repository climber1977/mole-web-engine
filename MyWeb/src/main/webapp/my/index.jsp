<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 4.1.0
Author: KeenThemes
Website: http://www.keenthemes.com/
Contact: support@keenthemes.com
Follow: www.twitter.com/keenthemes
Like: www.facebook.com/keenthemes
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
License: You must have a valid license purchased only from themeforest(the above link) in order to legally use the theme for your project.
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>MyWebEngine引擎演示</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta content="" name="description"/>
<meta content="" name="author"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<!-- BEGIN GLOBAL MANDATORY STYLES -->
        <link href="/myweb/myassets/css/my.css" rel="stylesheet" type="text/css" />
        
        <link href="/myweb/metronic/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="/myweb/metronic/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        <link href="/myweb/metronic/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        
        <link href="/myweb/metronic/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <link href="/myweb/metronic/assets/global/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css">
        <link href="/myweb/metronic/assets/global/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="/myweb/metronic/assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" type="text/css" />
        <link href="/myweb/metronic/assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css" />
        <link href="/myweb/metronic/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css" rel="stylesheet" type="text/css" />
        <link href="/myweb/metronic/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.css" rel="stylesheet" type="text/css" />
        <link href="/myweb/metronic/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
        <link href="/myweb/metronic/assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css" rel="stylesheet" type="text/css" />
        <link href="/myweb/metronic/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />

        <link href="/myweb/metronic/assets/global/plugins/morris/morris.css" rel="stylesheet" type="text/css" />
        <link href="/myweb/metronic/assets/global/plugins/fullcalendar/fullcalendar.min.css" rel="stylesheet" type="text/css" />
        <link href="/myweb/metronic/assets/global/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL STYLES -->
        <link href="/myweb/metronic/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
        <link href="/myweb/myassets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
        <!-- END THEME GLOBAL STYLES -->
        <!-- BEGIN THEME LAYOUT STYLES -->
        <link href="/myweb/metronic/assets/layouts/layout/css/layout.min.css" rel="stylesheet" type="text/css" />
        <link href="/myweb/metronic/assets/layouts/layout/css/themes/darkblue.min.css" rel="stylesheet" type="text/css" id="style_color" />
        <link href="/myweb/metronic/assets/layouts/layout/css/custom.min.css" rel="stylesheet" type="text/css" />
        <!-- END THEME LAYOUT STYLES -->
        <link rel="shortcut icon" href="favicon.ico" /> </head>        
</head>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
<!-- BEGIN HEADER -->
<%@ include file="page-header.jsp" %> 
<!-- END HEADER -->
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<div class="page-container">
	<!-- BEGIN SIDEBAR -->
	<div class="page-sidebar-wrapper">
		<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
		<!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
		<div class="page-sidebar navbar-collapse collapse">
			<!-- BEGIN SIDEBAR MENU1 -->
			<ul class="page-sidebar-menu  page-header-fixed " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" style="padding-top: 20px" id="left_menu">
				<!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
				<!-- DOC: This is mobile version of the horizontal menu. The desktop version is defined(duplicated) in the header above -->
				
			</ul>
			<!-- END SIDEBAR MENU1 -->
		</div>
	</div>
	<!-- END SIDEBAR -->
	<!-- BEGIN CONTENT -->
	<div class="page-content-wrapper">
		<div class="page-content">			
			<%@ include file="page-content.jsp" %>
		</div>
	</div>
	<!-- END CONTENT -->

</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<div class="page-footer">
	<div class="page-footer-inner">
		 2014 &copy; Metronic by keenthemes. <a href="http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes" title="Purchase Metronic just for 27$ and get lifetime updates for free" target="_blank">Purchase Metronic!</a>
	</div>
	<div class="scroll-to-top">
		<i class="icon-arrow-up"></i>
	</div>
</div>

<!-- END FOOTER -->
 <!--[if lt IE 9]>
<script src="/myweb/metronic/assets/global/plugins/respond.min.js"></script>
<script src="/myweb/metronic/assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
        <!-- BEGIN CORE PLUGINS -->
        <script src="/myweb/metronic/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
        
        <script src="/myweb/metronic/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
        <!-- END CORE PLUGINS -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script src="/myweb/metronic/assets/global/plugins/highcharts/js/highcharts.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/highcharts/js/highcharts-3d.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/highcharts/js/highcharts-more.js" type="text/javascript"></script>
        
        <script src="/myweb/metronic/assets/global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
        
        <script src="/myweb/metronic/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
        
        <script src="/myweb/metronic/assets/global/plugins/bootstrap-modal/js/bootstrap-modalmanager.js" type="text/javascript"></script>
        <script src="/myweb/myassets/global/plugins/bootstrap-modal/js/bootstrap-modal.js" type="text/javascript"></script>        
        <script src="/myweb/metronic/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js" type="text/javascript"></script>
        <link href="/myweb/metronic/assets/global/plugins/jstree/dist/themes/default/style.min.css" rel="stylesheet" type="text/css" />
        <script src="/myweb/myassets/global/plugins/jstree/dist/jstree.js" type="text/javascript"></script>
        <script src="/myweb/myassets/global/scripts/datatable.js" type="text/javascript"></script>
        <script src="/myweb/myassets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
        
        <script src="/myweb/metronic/assets/global/plugins/moment.min.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
        
        <script src="/myweb/metronic/assets/global/plugins/morris/morris.min.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/morris/raphael-min.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/counterup/jquery.waypoints.min.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/counterup/jquery.counterup.min.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/amcharts/amcharts/amcharts.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/amcharts/amcharts/serial.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/amcharts/amcharts/pie.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/amcharts/amcharts/radar.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/amcharts/amcharts/themes/light.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/amcharts/amcharts/themes/patterns.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/amcharts/amcharts/themes/chalk.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/amcharts/ammap/ammap.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/amcharts/ammap/maps/js/worldLow.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/amcharts/amstockcharts/amstock.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/horizontal-timeline/horozontal-timeline.min.js" type="text/javascript"></script>
        <!--  
        <script src="/myweb/metronic/assets/global/plugins/flot/jquery.flot.min.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/flot/jquery.flot.resize.min.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/flot/jquery.flot.categories.min.js" type="text/javascript"></script>
        -->
        <script src="/myweb/metronic/assets/global/plugins/jquery-easypiechart/jquery.easypiechart.min.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/jquery.sparkline.min.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/jqvmap/jqvmap/jquery.vmap.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.russia.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.world.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.europe.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.germany.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.usa.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/global/plugins/jqvmap/jqvmap/data/jquery.vmap.sampledata.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL SCRIPTS -->
        <script src="/myweb/metronic/assets/global/scripts/app.min.js" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <script src="/myweb/metronic/assets/pages/scripts/form-validation.min.js" type="text/javascript"></script>
                
        <script src="/myweb/myassets/pages/scripts/charts-highcharts.js" type="text/javascript"></script>
                
        <script src="/myweb/myassets/pages/scripts/components-select2.js" type="text/javascript"></script>
        
        <script src="/myweb/metronic/assets/pages/scripts/components-date-time-pickers.min.js" type="text/javascript"></script>
        
        <script src="/myweb/myassets/pages/scripts/ui-tree.js" type="text/javascript"></script>
        <script src="/myweb/myassets/pages/scripts/table-datatables-ajax.js" type="text/javascript"></script>
        <script src="/myweb/myassets/scripts/global_js.jsp"></script>
        <script src="/myweb/myassets/scripts/myutils.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/pages/scripts/dashboard.min.js" type="text/javascript"></script>
        <script src="/myweb/myassets/pages/scripts/components-date-time-pickers.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <script src="/myweb/metronic/assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/layouts/layout/scripts/demo.js" type="text/javascript"></script>
        <script src="/myweb/metronic/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
        <!-- END THEME LAYOUT SCRIPTS -->
        <script src="/myweb/myassets/scripts/ajaxfileupload.js" type="text/javascript"></script>
        <script src="/myweb/myassets/scripts/menu.js"></script>
        <script src="/myweb/myassets/scripts/test.js" type="text/javascript"></script>
        <script src="/myweb/myassets/scripts/messenger.js" type="text/javascript"></script>
        <script type="text/javascript">
	   		jQuery(document).ready(function() {
				menu.init();
			   /*
			   //显示二级菜单
			   $.get("left_menu.jsp?id=1&sub=1", function(data) {
		       		CONTENT_LODDING = false;
		           	$('#left_menu').html('');
		       		$('#left_menu').html(data);										
		       });
			   */
			   //alert( $('.navbar-nav').find('li:first').find('a:first')[0].text);
			   //alert( $('.navbar-nav').find('li:first').find('a:first')[0].id);
			   //模拟点击1级菜单
			   $('.navbar-nav').find('li:first').find('a:first')[0].click()
			   /*
			   //显示正文内容
			   $.get("./controls/controls_example.jsp", function(data) {
	       			CONTENT_LODDING = false;
	           		$('#page-content2').html('');
	       			$('#page-content2').html(data);
	      		 });
			   */
			});
	</script>  
</body>
<!-- END BODY -->
</html>