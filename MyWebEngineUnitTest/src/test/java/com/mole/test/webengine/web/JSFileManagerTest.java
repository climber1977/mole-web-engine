package com.mole.test.webengine.web;

import com.mole.webengine.debug.MyDebug;
import com.mole.webengine.file.JSFileManager;
import com.mole.webengine.system.GlobalContext;


public class JSFileManagerTest {
	public MyDebug getDebug(){
		return new MyDebug();
	}
	public static void main(java.lang.String[] args)  throws Exception {
		GlobalContext.setGlobal("debug", new MyDebug());		
		JSFileManager.loadJSFiles("javajs", "javajs");	
		
		String request = "request";
		String response = "response";
		String uuid = "uuid";
		String params = "params";
		
		JSFileManager.callJs("javajs", "append_common_params.js", "appendParams", request, response, uuid, params);		
		
	}
}
