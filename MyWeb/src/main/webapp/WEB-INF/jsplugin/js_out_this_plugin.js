function request(global, uuid, request,response, db)  {
    var line = "";
	for(var w in this){
        line += (w  + ':' + this[w] + "<br>");
    }
	line = line.replace(/\\/g, "\\\\");
	line = line.replace(/\"/g, "\\\"");
	line = line.replace(/\n/g, "<br>");	
	line = line.replace(/ /g, "&nbsp");		
	return '{"result":"' + line + '"}';
}