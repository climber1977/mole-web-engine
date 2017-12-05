<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/init.jsp" %>
<center><h3 class="page-title">更新表单上的多组数据</h3></center>
&nbsp&nbsp1、请求返回多组数据，同时更新到表单上的多个组建中。
<hr>
<div id="show"> show </div><br> 
<div class="row">
    <div class="col-md-6 ">
        <!-- BEGIN SAMPLE FORM PORTLET-->
        <div class="portlet light bordered">
            <div class="portlet-title">
                <div class="caption">
                    <i class="icon-settings font-dark"></i>
                    <span class="caption-subject font-dark sbold uppercase">用户1</span>
                </div>                                    
            </div>
            <div class="portlet-body form">
                <form class="form-horizontal" role="form">
                    <lc:portal jsonDesc="example/update_group_item.json"></lc:portal>                 
                </form>
            </div>
        </div>
    </div>
    <div class="col-md-6 ">
        <!-- BEGIN SAMPLE FORM PORTLET-->
        <div class="portlet light bordered">
            <div class="portlet-title">
                <div class="caption">
                    <i class="icon-settings font-dark"></i>
                    <span class="caption-subject font-dark sbold uppercase">用户2</span>
                </div>                                    
            </div>
            <div class="portlet-body form">
                <form class="form-horizontal" role="form">
                    <lc:portal index="1" jsonDesc="example/update_group_item.json"></lc:portal>                
                </form>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-6 ">
        <!-- BEGIN SAMPLE FORM PORTLET-->
        <div class="portlet light bordered">
            <div class="portlet-title">
                <div class="caption">
                    <i class="icon-settings font-dark"></i>
                    <span class="caption-subject font-dark sbold uppercase">用户3</span>
                </div>                                    
            </div>
            <div class="portlet-body form">
                <form class="form-horizontal" role="form">
                    <lc:portal index="2" jsonDesc="example/update_group_item.json"></lc:portal>                 
                </form>
            </div>
        </div>
    </div>
    <div class="col-md-6 ">
        <!-- BEGIN SAMPLE FORM PORTLET-->
        <div class="portlet light bordered">
            <div class="portlet-title">
                <div class="caption">
                    <i class="icon-settings font-dark"></i>
                    <span class="caption-subject font-dark sbold uppercase">用户4</span>
                </div>                                    
            </div>
            <div class="portlet-body form">
                <form class="form-horizontal" role="form">
                    <lc:portal index="3" jsonDesc="example/update_group_item.json"></lc:portal>                 
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
	myAjaxReq2Item("update_item_in_web?rtype=2",  {name:"海"});
</script>

