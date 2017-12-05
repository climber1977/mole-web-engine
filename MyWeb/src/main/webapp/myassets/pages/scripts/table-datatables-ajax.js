function _createDataTableRequestUrl(requestUrl){
	var bool = requestUrl.indexOf("?");
	if(bool > 0){
		requestUrl += "&rtype=1&act=datatable"
	} else {
		requestUrl += "?rtype=1&act=datatable"
	}
	return requestUrl;
}
var TableDatatablesAjax = function () {

    var handleRecords = function (tableid, requestUrl, sorting, params) {

    	var params = getParams();
        
        var grid = new Datatable();

        grid.init({
            src: $(tableid),
            onSuccess: function (grid, response) {
                // grid:        grid object
                // response:    json object of server side ajax response
                // execute some code after table records loaded
            },
            onError: function (grid) {
                // execute some code on network or other general error  
            },
            onDataLoad: function(grid) {
                // execute some code on ajax data load
            },
            loadingMessage: 'Loading...',
            dataTable: { // here you can define a typical datatable settings from http://datatables.net/usage/options 

                // Uncomment below line("dom" parameter) to fix the dropdown overflow issue in the datatable cells. The default datatable layout
                // setup uses scrollable div(table-scrollable) with overflow:auto to enable vertical scroll(see: assets/global/scripts/datatable.js). 
                // So when dropdowns used the scrollable div should be removed. 
                //"dom": "<'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'<'table-group-actions pull-right'>>r>t<'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>>",
                
                "bStateSave": true, // save datatable state(pagination, sort, etc) in cookie.

                "lengthMenu": [
                    [10, 20, 50, 100, 150, -1],
                    [10, 20, 50, 100, 150, "All"] // change per page values here
                ],
                "pageLength": 10, // default record count per page
                "ajax": {
                    "url": requestUrl, // ajax source
                    "data": function (d) {	                    
                    	addItemToJson(d);
	                }
                },
                //"ordering":false,
                "columnDefs":[
                         {"orderable":false, "targets":sorting}
                ], 
                
                "order": [
                    [1, "asc"]
                ]
            }            
        });

        // handle group actionsubmit button click
        grid.getTableWrapper().on('click', '.table-group-action-submit', function (e) {
            e.preventDefault();
            var action = $(".table-group-action-input", grid.getTableWrapper());
            if (action.val() != "" && grid.getSelectedRowsCount() > 0) {
                grid.setAjaxParam("customActionType", "group_action");
                grid.setAjaxParam("customActionName", action.val());
                grid.setAjaxParam("id", grid.getSelectedRows());
                grid.getDataTable().ajax.reload();
                grid.clearAjaxParams();
            } else if (action.val() == "") {
                App.alert({
                    type: 'danger',
                    icon: 'warning',
                    message: 'Please select an action',
                    container: grid.getTableWrapper(),
                    place: 'prepend'
                });
            } else if (grid.getSelectedRowsCount() === 0) {
                App.alert({
                    type: 'danger',
                    icon: 'warning',
                    message: 'No record selected',
                    container: grid.getTableWrapper(),
                    place: 'prepend'
                });
            }
        });

        //grid.setAjaxParam("customActionType", "group_action");
        //grid.getDataTable().ajax.reload();
        //grid.clearAjaxParams();
        return grid;
    }
    
    return {
        //main function to initiate the module    	
        init: function (tableid, requestUrl, sorting) {
        	requestUrl = getRequestUrl() + requestUrl;
        	requestUrl = _createDataTableRequestUrl(requestUrl);
            return handleRecords(tableid, requestUrl, sorting);
        },
	    init2: function (tableid, requestUrl, sorting) {
	    	requestUrl = _createDataTableRequestUrl(requestUrl);
	        return handleRecords(tableid, requestUrl, sorting);
	    },
        init3: function (tableid, requestUrl, sorting) {	    	
	        return handleRecords(tableid, requestUrl, sorting);
	    }
    };

}();

