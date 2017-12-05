function request(global, uuid, request,response, db)  {
	sql = "select id, work_code, name, ch_name, en_name from wb_user";
	//sql += " where id<5";
	sql += " order by id";

	req = global.getGlobal("RequestUtils");
	params = req.getParams(request, "");
	startPageIndex = params.getInt("start");		
	pageCount = params.getInt("length");
	sql = db.getPagingSql(sql, startPageIndex, pageCount);		
	sql = db.addCalcFoundRowsOfPaginSql(sql);
	
	suser = global.getGlobal("SecurityUser");
	suser.initColumnPrivilege(db, true);
	rs = db.queryReturnListList(sql, true);
	
	web = global.getGlobal("WebRequest");
	return web.ConvertListPaginResult(rs.getResultObject(), rs.getFoundRows(), request);	
}