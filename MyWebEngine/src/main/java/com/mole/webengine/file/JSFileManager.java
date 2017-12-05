package com.mole.webengine.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.mole.webengine.debug.MyException;
import com.mole.webengine.file.FileUtils;
import com.mole.webengine.system.GlobalContext;
import com.mole.webengine.system.SysParams;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class JSFileManager {
	class MyInvocable{
		Invocable invoke;
		long lastModified;
		String path;
		public MyInvocable(Invocable invoke, String path){
			this.invoke = invoke;
			this.path = path;
			File f = new File(path);
			this.lastModified = f.lastModified();
		}
		public boolean isChange(){
			File f = new File(path);
			if(f.lastModified() != lastModified){
				return true;
			}
			return false;
		}
	}
	private HashMap<String, MyInvocable> invokes = new HashMap<String, MyInvocable>();
	private String rootDir = "";

	public static JSFileManager getInstance(String jsKey) throws Exception {
		jsKey = "jsfile_" + jsKey;
		JSFileManager jsm = (JSFileManager) GlobalContext.getGlobal(jsKey);
		if(jsm == null){
			jsm = new JSFileManager();
			GlobalContext.setGlobal(jsKey, jsm);
		}
		
		return jsm;
	}
	public static boolean loadJSFiles(String jsKey, String dir) throws Exception {
		JSFileManager jsm = getInstance(jsKey);
		if(jsm != null){
			return jsm.loadJSFiles(dir);
		}
		throw new MyException("cann't find file of js by" + jsKey);
	}
	public static Object callJs(String jsKey, String jsFileName, String funName, Object... args) throws Exception{
		JSFileManager jsm = getInstance(jsKey);
		if(jsm != null){
			return jsm.callJs(jsFileName, funName, args);
		}
		throw new MyException("cann't find file of js by" + jsKey);
	}
	
	public boolean loadJSFiles(String dir) throws Exception {
		if(rootDir.equals("")){
			rootDir = dir;
		}
		File f = new File(dir);
		String[] names = f.list(null);
		String path = "";
		for (int i = 0; i < names.length; i++) {
			path = FileUtils.conjPaths(dir, names[i]);
			if (names[i].endsWith(".js")) {
				if (loadJS(path) == null) {
					System.out.println("LoadJS [" + path + "] failed.");
				}
			} else {
				if (FileUtils.isDirectory(path)) {
					loadJSFiles(path);
				}
			}
		}
		return true;
	}
	
	public boolean init() throws Exception {
		//不使用clear，避免多线程冲突
		invokes = new HashMap<String, MyInvocable>();		
		return loadJSFiles(rootDir);
	}
	
	public Invocable reloadJSFile(String path) throws Exception {
		synchronized (invokes) {
			invokes.remove(path);
		}
		return loadJS(path);
	}

	public Invocable loadJS(String jsFileName) throws Exception {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
		
		System.out.println("jsFileName=" + jsFileName);
		
		//在不指定编码的情况下，有三个连续中文字时，会解析失败。		
		InputStreamReader reader=new InputStreamReader(new FileInputStream(jsFileName),"UTF-8");
		
		engine.eval(reader);
		Invocable invoke = (Invocable) engine; // 调用merge方法，并传入两个参数
		
		Object obj = engine.get("init");  
		if(obj != null)
			invoke.invokeFunction("init", jsFileName, invoke, SysParams.getGlobalContext());
		String fileName = jsFileName.substring(rootDir.length()+1);
		MyInvocable inv = new MyInvocable(invoke, jsFileName);
		synchronized (invokes) {
			invokes.put(fileName, inv);
		}
		reader.close();
		return invoke;
	}
	
	public Invocable getInvoke(String jsFileName) throws Exception
	{
		MyInvocable inv = invokes.get(jsFileName);
		if(inv == null || inv.isChange()){
			String path = rootDir +"/" + jsFileName;
			return  loadJS(path);
		}
		return inv.invoke;
	}
	
	public Object callJs(String jsFileName, String funName, Object... args) throws Exception
	{
		Invocable invoke = getInvoke(jsFileName);
		Object[] objs = new Object[args.length+1];
		objs[0] = SysParams.getGlobalContext();
		for(int i=0; i<args.length; i++){
			objs[i+1] = args[i];
		}
	
		return invoke.invokeFunction(funName, objs);
	}
	
	public void print() throws Exception {
		Iterator<Map.Entry<String, MyInvocable>> it = invokes.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, MyInvocable> e = (Map.Entry<String, MyInvocable>) it.next();
			System.out.println("Key: " + e.getKey() + "--Value: " + e.getValue().path);
		}
	}
}
