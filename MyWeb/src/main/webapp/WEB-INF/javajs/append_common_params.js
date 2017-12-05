function appendParams(global, request, response, uuid, params) {
	var d = global.getGlobal("debug");
	d.println("append_common_params.js", "appendParams", "enter");
	params.put("test", "test");
	d.println("append_common_params.js", "appendParams", "leave");
}