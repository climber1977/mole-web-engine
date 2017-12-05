package com.mole.webengine.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.utils.JsonUtils;

public class PluginManager {
	static class FunInfo{
		String uuid;
		String jarPath;
		Class<?> cls;
		Object ins;
		Method m;
		long lastModified;
	}
	private static HashMap<String, String> jars = new HashMap<String, String>();
	private static String pluginPath = null;
	private static PluginClassLoader loader = null;

	final static Logger logger = LoggerFactory.getLogger(PluginClassLoader.class);
	
	public static void init(String pluginPath){		
		PluginManager.pluginPath = pluginPath + "/WEB-INF/plugin";
		logger.info(PluginManager.pluginPath);
	}

	public static String callFun(String uuid, HttpServletRequest request,HttpServletResponse response, MyJDBC db) throws Exception {
		loader = new PluginClassLoader();
		FunInfo finfo = null;
		if(jars.containsKey(uuid)){
			finfo = findMethodInJar(uuid, jars.get(uuid));
		} else {
			finfo = findMethodInDir(uuid, pluginPath);
		}
		if(finfo == null){
			throw new Exception("cann't find method by " + uuid);
		}
		String result = null;
		try{
			result = (String) finfo.m.invoke(finfo.ins, uuid, request, response, db);
		} finally{
			loader.unloadJarFiles();
		}
		
		return result;
	}
	private static FunInfo getFunInfo(String uuid, JSONObject jobj, String jarPath, File f, URLClassLoader loader) throws Exception{
		FunInfo finfo =new FunInfo();
		finfo.uuid = jobj.getString("uuid");
		finfo.jarPath = jarPath;
		finfo.cls = loader.loadClass(jobj.getString("class"));
		finfo.ins = finfo.cls.newInstance();
		String funName = jobj.getString("funname");
		finfo.m = finfo.cls.getDeclaredMethod(funName, String.class, HttpServletRequest.class,
				HttpServletResponse.class, MyJDBC.class);	
		finfo.lastModified = f.lastModified();
		jars.put(uuid, jarPath);
		return finfo;
	}

	private static FunInfo findMethodInJar(String uuid, String jarPath) throws Exception {
		Path p = Paths.get(jarPath);
		URL url = new URL("jar:file:" + p.toString() + "!/");
		loader.addURLFile(url);
		InputStream input = null;
		JarFile jarFile = new JarFile(jarPath);
		File f = new File(jarPath);
		String line = null;
		JSONObject jobj = null;
		FunInfo finfo = null;
		Enumeration<?> enu = jarFile.entries();
		while (enu.hasMoreElements()) {
			JarEntry entry = (JarEntry) enu.nextElement();
			String name = entry.getName();			
			if (!name.equals("packet.cfg")) {
				continue;
			}
			input = jarFile.getInputStream(entry);
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			while((line = br.readLine()) != null){
				line = line.trim();
				if(line.equals("")){
					continue;
				}
				if(line.startsWith("#")){
					continue;
				}
				jobj = JsonUtils.jstring2JObject(line);
				if(!jobj.getString("uuid").equals(uuid)){
					continue;
				}
				finfo = getFunInfo(uuid, jobj, jarPath, f, loader);
				break;
			}
			
			br.close();
			input.close();
			break;
		}
		jarFile.close();
		return finfo;
	}
	private static FunInfo findMethodInDir(String uuid, String path) throws Exception {
		File file = new File(path);
		File[] files = file.listFiles();
		FunInfo finfo = null;
		for (File file2 : files) {
			if (file2.isDirectory()) {
				finfo = findMethodInDir(uuid, file2.getAbsolutePath());
			} else if (file2.getAbsolutePath().endsWith(".jar")) {
				finfo = findMethodInJar(uuid, file2.getAbsolutePath());
			}
			if(finfo != null){
				return finfo;
			}
		}
		return finfo;
	}
	
	public static void copyFile(File fromFile,File toFile) throws IOException{
        FileInputStream ins = new FileInputStream(fromFile);
        FileOutputStream out = new FileOutputStream(toFile);
        byte[] b = new byte[1024];
        int n;
        while((n=ins.read(b))!=-1){
            out.write(b, 0, n);
        }
        
        ins.close();
        out.close();
    }
}
