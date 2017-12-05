var UITree = function () {
     var ajaxTreeSample = function(treeid, requestUrl, rootid) {
    	requestUrl = getRequestUrl() + requestUrl;
    	
        var t = $(treeid).jstree({
            "core" : {
                "themes" : {
                    "responsive": false
                }, 
                // so that create works
                "check_callback" : true,
                'data' : {
                	'type' : 'POST',  
                    'url' : function (node) {
                      return requestUrl;
                    },
                    'data' : function (node) {
	                  if(node.id == '#'){
	                	return { 'parent' : rootid };
	                  }
                      return { 'parent' : node.id };
                    }
                }
            },
            "types" : {
                "default" : {
                    "icon" : "fa fa-folder icon-state-warning icon-lg"
                },
                "file" : {
                    "icon" : "fa fa-file icon-state-warning icon-lg"
                }
            },
            "state" : { "key" : "demo3" },
            "plugins" : [ "dnd", "state", "types" ]
        });
        return t;
    
    }


    return {
        //main function to initiate the module
        init: function (treeid, requestUrl, rootid) {
        	var bool = requestUrl.indexOf("?");
        	if(bool > 0){
        		requestUrl += "&rtype=2"
        	} else {
        		requestUrl += "?rtype=2"
        	}
            return ajaxTreeSample(treeid, requestUrl, rootid);

        }

    };

}();

