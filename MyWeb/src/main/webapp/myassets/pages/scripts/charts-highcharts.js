function curveChartAjax(chartId, tooltip, title, yTitle, subtitle, requestUrl, dbs, legendenabled, categories){
	showChartAjax('curveChart', chartId, tooltip, title, yTitle, subtitle, requestUrl, dbs, legendenabled, categories);
}
function columnChartAjax(chartId, tooltip, title, yTitle, subtitle, requestUrl, dbs, legendenabled, categories){
	showChartAjax('columnChart', chartId, tooltip, title, yTitle, subtitle, requestUrl, dbs, legendenabled, categories);
}
function columnChart2Ajax(chartId, tooltip, title, yTitle, subtitle, requestUrl, dbs, legendenabled, categories){
	showChartAjax('columnChart2', chartId, tooltip, title, yTitle, subtitle, requestUrl, dbs, legendenabled, categories);
}
function barChartAjax(chartId, tooltip, title, yTitle, subtitle, requestUrl, dbs, legendenabled, categories){
	showChartAjax('barChart', chartId, tooltip, title, yTitle, subtitle, requestUrl, dbs, legendenabled, categories);
}
function createRequestUrl(requestUrl, dbs){
    if(requestUrl.indexOf("?")  == -1){
        requestUrl = requestUrl + "?";
    } else {
        requestUrl = requestUrl + "&";
    }
    requestUrl = requestUrl + "rtype=1&dbs=" + dbs;
    return requestUrl;
}
function showChartAjax(chartType, chartId, tooltip, title, yTitle, subtitle, requestUrl, dbs, legendenabled, categories){
	var params = getParams();
	//requestUrl = "?uuid=" + requestUrl + "&rtype=1&dbs=" + dbs;
	requestUrl = createRequestUrl(requestUrl, dbs);
	
	myajax(requestUrl, params, function(result){
	 if(typeof categories == "undefined"){
		categories = new Array();
	}
    var series = new Array();
    var items;
    var key = "";
    for(var i in result){ 
    		//console.debug(data[i][0] + ';' + result[i][1] + ';' + result[i][2]);
    		if(key=="" || key != result[i][1]){
    			if(key != ""){
    				var jgroup = {};
    				jgroup['name'] = key;
    				jgroup['data'] = items;
			    	series.push(jgroup);
			    }
			    key = result[i][1];
    			items = new Array();
    		}	
     		categories.push(result[i][0]); 
     		items.push(result[i][2]);
    }
    if(key != ""){
    	var jgroup = {};
			jgroup['name'] = key;
			jgroup['data'] = items;
    	series.push(jgroup);
    }
    //alert(categories);
    //alert(JSON.stringify(series));
    if(chartType.toLowerCase() == 'columnChart'.toLowerCase()){
			columnChart(chartId, tooltip, title, yTitle, subtitle, categories, series, legendenabled);
		} else if(chartType.toLowerCase() == 'columnChart2'.toLowerCase()){
			columnChart2(chartId, tooltip, title, yTitle, subtitle, categories, series, legendenabled);
		}else if(chartType.toLowerCase() == 'barChart'.toLowerCase()){
			barChart(chartId, tooltip, title, yTitle, subtitle, categories, series, legendenabled);
		}else{
			curveChart(chartId, tooltip, title, yTitle, subtitle, categories, series, legendenabled);
		}
	}, 
	function(x, e){
	});
}

function pieChartAjax(chartId, tooltip, title, yTitle, subtitle, requestUrl, dbs, legendenabled){
	var params = {};
	requestUrl = createRequestUrl(requestUrl, dbs);
	
	myajax(requestUrl, params, function(result){		
    var datas = new Array();

    for(var i in result){ 
				var jgroup = {};
				jgroup['name'] = result[i][0];
				jgroup['y'] = result[i][1];
	    	datas.push(jgroup);
    }
    
    var series= [ {
            data: datas,
            size: '100%',
            innerSize: '0%',
            dataLabels: {
                formatter: function () {
                    // display only if larger than 1
                    return this.y > 1 ? '' + this.point.name + ': ' + this.y + '%'  : null;
                }
            }
        }   
      ]; 
    categories = {};
		pieChart(chartId, tooltip, title, yTitle, subtitle, categories, series, legendenabled);
	
	}, 
	function(x, e){
	});
}
function curveChart(chartId, tooltip, title, yTitle, subtitle, categories, series, legendenabled) {
	var chart = {
    style: {
        fontFamily: 'Open Sans'
    }
  };
  var plotOptions = {};
  showChart(chartId, chart, plotOptions, tooltip, title, yTitle, subtitle, categories, series, legendenabled);
}
function columnChart(chartId, tooltip, title, yTitle, subtitle, categories, series, legendenabled) {
	var chart = {
    type: 'column'
 	};
	var plotOptions = {
      column: {
         stacking: 'normal',
         dataLabels: {
            enabled: true,
            color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
            style: {
               textShadow: '0 0 0 black'
            }
         }
      }
   	}		
  showChart(chartId, chart, plotOptions, tooltip, title, yTitle, subtitle, categories, series, legendenabled);
}
function columnChart2(chartId, tooltip, title, yTitle, subtitle, categories, series, legendenabled) {
	var chart = {
    type: 'column'
 	};
 	var plotOptions = {};
  showChart(chartId, chart, plotOptions, tooltip, title, yTitle, subtitle, categories, series, legendenabled);
}
function barChart(chartId, tooltip, title, yTitle, subtitle, categories, series, legendenabled) {
	var chart = {
	    type: 'bar'
	 };
	 var plotOptions = {};
  showChart(chartId, chart, plotOptions, tooltip, title, yTitle, subtitle, categories, series, legendenabled);
}
function pieChart(chartId, tooltip, title, yTitle, subtitle, categories, series, legendenabled) {
	var chart = {
	    type: 'pie'
	 };
	 tooltip['data'] = series;
	var plotOptions = {
      pie: {
         shadow: false,
         center: ['54%', '50%'],
         dataLabels: {
            enabled: true           
         },
         showInLegend: true,
         
      }
   	}	
  showChart(chartId, chart, plotOptions, tooltip, title, yTitle, subtitle, categories, series, legendenabled);
}
function showChart(chartId, chart, plotOptions, tooltip, title, yTitle, subtitle, categories, series, legendenabled) {
  // HIGHCHARTS DEMOS
    if("undefined" == typeof legendenabled)
        legendenabled = true;
  	// LINE CHART 1
	$('#' + chartId).highcharts({
    chart : chart,
		title: {
			text: title,
			x: -20 //center
		},
		subtitle: {
			text: subtitle,
			x: -20
		},
		xAxis: {
			categories: categories
		},
		yAxis: {
			title: {
				text: yTitle
			},
			plotLines: [{
				value: 0,
				width: 1,
				color: '#808080'
			}]
		},
		tooltip: tooltip,
		legend: {
			layout: 'vertical',
			align: 'right',
			verticalAlign: 'middle',
			borderWidth: 0,
			enabled: legendenabled      
		},
		series: series,
		credits: {
        enabled: false
	   },
	   plotOptions:plotOptions
		});
};

