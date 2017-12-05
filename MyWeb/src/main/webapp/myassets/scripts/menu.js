var MENU_TITLE1 = '';
var MENU_TITLE2 = '';
var CONTENT_LODDING = false;
var curThirdMenuUrl = "";
function setPageTitle(title) {
    $('#page-title').html(title);   
};
function setMenuTitle1(title) {
 		//alert('setMenuTitle1 按岗位浏览');
    //$('#menu-title1').html(title);
    MENU_TITLE1 = title;
};
function setMenuTitle2(title) {
 	//alert('setMenuTitle2 按岗位浏览');
    //$('#menu-title2').html(title);
    MENU_TITLE2 = title;
};
function setMenuTitle3(title) {
 	//alert('setMenuTitle3 按岗位浏览');
	$('#menu-title1').html(MENU_TITLE1);
	$('#menu-title2').html(MENU_TITLE2);
    $('#menu-title3').html(title);
};

var menu = (function() {
		//alert("Index");
    var me = {};
    
    // 处理一级菜单点击
    me.handleFirstMenuClickA = function() {
    	//alert("handleFirstMenuClickA");
    	$('.first-menu a').unbind(); 
        $('.first-menu a').click(function(e) { 
        	$('#page-content2').html("");
    		//屏蔽掉默认时间，不会在响应handleFirstMenuClick，相当于return false
    		e.preventDefault();	
    		//alert("handleFirstMenuClickA enter");
        	
        	setMenuTitle1($(this).text());
        	setMenuTitle2("");
            $(this).parent().addClass('active').siblings().removeClass('active');
            
            var url = this.href;
            if (url != null && url != 'javascript:;') {
        		if(CONTENT_LODDING){
        			return false;
        		}
        		CONTENT_LODDING = true;
                $.get(url, function(data) {
                		CONTENT_LODDING = false;
                    $('#left_menu').html('');
                		$('#left_menu').html(data);										
                });
            }                
            //alert("handleFirstMenuClickA leave");
            setMenuTitle3("");
        });
    };
    
    // 处理二级菜单点击
    me.handleSecondMenuClick = function() {
    	//alert("handleSecondMenuClick");
    	//$('.second-menu a').unbind(); 
    	$('.second-menu').unbind(); 
        //$('.second-menu a').click(function(e) {
    	$('.second-menu').click(function(e) {    		
    		//alert("handleSecondMenuClick enter");
    		e.preventDefault();  //屏蔽掉默认事件，相当于return false
            
            p = $(this).parent();
            submenu = p.children('.sub-menu');
            if(submenu.length <= 0){
            	submenu = p.children('.sub-menu-blank');
            }
            if(p.hasClass("active")){
            	//后续还有事件处理，所以这个地方要设为block
            	submenu.css('display','none');
            	p.removeClass('start active open');
            }
            else{
            	submenu.css('display','block');
            	p.addClass('start active open');
            	
            }

        });
    };

    // 处理三级菜单点击
    me.handleThirdMenuClick = function() {
    	
    	//alert("handleThirdMenuClick");
    	//$('.sub-menu li a').unbind(); 
    	$('.third-menu a').unbind(); 
        //$('.sub-menu li a').click(function(e) {
    	$('.third-menu a').click(function(e) {
            e.preventDefault();  //屏蔽掉默认事件，相当于return false
            var url = this.href;
            var id =this.id;
            
            //second-menu
            p = $(this).parent().parent().parent();            
            setMenuTitle2(p.children('a').children('span').text());
            setMenuTitle3(this.text);
            setPageTitle(this.text);
            curThirdMenuUrl = url;
            if (url != null && url != 'javascript:;') {
            		if(CONTENT_LODDING){
		        			return false;
		        		}
		        		CONTENT_LODDING = true;
                $.get(url, function(data) {
                		CONTENT_LODDING = false;
                    $('#page-content2').html('');
                	$('#page-content2').html(data);
                	$('html,body').animate({scrollTop: '0px'}, 800);
                });
            }
        });
    };

    me.init = function() {
    	me.handleFirstMenuClickA();        		
        me.handleSecondMenuClick();
        me.handleThirdMenuClick();
    };

    return me;
})();

