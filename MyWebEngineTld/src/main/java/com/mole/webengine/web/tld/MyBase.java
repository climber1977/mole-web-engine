package com.mole.webengine.web.tld;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.jsp.tagext.BodyTagSupport;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mole.webengine.file.FileUtils;
import com.mole.webengine.file.FilesCache;
import com.mole.webengine.security.SecurityUser;
import com.mole.webengine.system.SysParams;
import com.mole.webengine.utils.JsonUtils;
import com.mole.webengine.utils.MyParams;
import com.mole.webengine.utils.StringUtils;

@SuppressWarnings( { "serial" })
public class MyBase extends BodyTagSupport{
	private String uuid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	private String dbs;
	
	public String getDbs() {
		return dbs;
	}

	public void setDbs(String dbs) {
		this.dbs = dbs;
	}

	private String url;

	public String getUrl() {		
		return url;
	}
	public String getUrl(String uuid, String param) {
		if(url== null || url.equals("")){
			return uuid + "?dbs=" + getDbs() + "&" + param;
		}
		return url;
	}
	public static String getUrl(String dbs, String uuid, String param) {
		return uuid + "?dbs=" + dbs + "&" + param;		
	}
	public void setUrl(String url) {
		this.url = url;
	}	
	
	String template;
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	
	private String jsonDesc;
	public String getJsonDesc() {
		if(jsonDesc == null){
			return "";
		}
		return jsonDesc.trim();
	}
	public void setJsonDesc(String jsonDesc) {
		this.jsonDesc = jsonDesc;
	}
	
	public static String createPortal(JSONArray json, int index, MyParams parentParams, String keypath) throws Exception{
		MyParams params = new MyParams();
		if(parentParams != null){
			parentParams.clone(params);
		}
		
		String result = "";		
		//System.out.println("[");		
		for(int i=0 ;i<json.size(); i++){
			JSONObject jobj = (JSONObject)json.get(i);
			if(i > 0){
				//System.out.println(",");	
			}
			String keypath1 = keypath + "/[" + i+ "]";
			result += createPortal(jobj, index, params, keypath1);
		}
		//System.out.println("]");
		return result;
	}
	public static String createPortal(JSONObject json, int index, MyParams parentParams, String keypath) throws Exception{
		if(json == null){
			return "";
		}
		MyParams params = new MyParams();
		if(parentParams != null){
			parentParams.clone(params);
		}		
		
		String templeName = "";	
		String content = "";
		String name = "";
		Object child = null;
		
		//System.out.println("{");
		
		Set<String> keys = json.keySet();
		Iterator<String> ite = keys.iterator();
		while(ite.hasNext()){			
			String key = ite.next();
			Object o = json.get(key);
			if(o == null){
				continue;
			}
			else if(key.equalsIgnoreCase("child")){				
				child = o;				
			} else if(key.equalsIgnoreCase("id")){
				boolean access = SecurityUser.hasCompentPrivilege(o.toString());
				if(!access){
					return "";
				}
				params.put(key, o);
			} else if(key.equalsIgnoreCase("content")){
				content = o.toString();
			}else if(key.equalsIgnoreCase("templet")){
				templeName = o.toString();
			}else if(key.equalsIgnoreCase("name")){
				if(index !=0){
					name = o.toString() + "#" + String.valueOf(index);
				} else {
					name = o.toString();
				}
				params.put("name", name);
				//System.out.println("\"name\":" + "\"" + name + "\",");
			}else {
				params.put(key, o);
				//System.out.println("\"" + key + "\":" + "\"" + o + "\",");
			}
		}
		if(!name.equals("")){
			keypath += "/" + name;
		} else {
			keypath += "/null";
		}
		
		String result = "";		
		if(child != null){
			//System.out.println("\"itemtype\":" + "\"" + itemtype + "\",");
			//System.out.println("\"child\":");
			if(JsonUtils.isJSONArray(child)){
				JSONArray jarry = (JSONArray)child;
				result = createPortal(jarry, index, params, keypath);
			} else if(JsonUtils.isJSONObject(child)){
				JSONObject jobj = (JSONObject)child;
				result = createPortal(jobj, index, params, keypath);
			}
		} else {
			//System.out.println("\"itemtype\":" + "\"" + itemtype + "\"");
		}
		
		if(!content.equals("")){
			params.put(content, result);
		}
		//System.out.println("}");
		if(!templeName.equals("")){			
			if(FileUtils.exists(SysParams.getTemplateFilePath(templeName))){
				String template = FilesCache.getTemplate(templeName);
				result = StringUtils.formateTemplate(template, params, true, null, false);
			} else {
				System.out.println("\"the " + templeName + "isn't exists");
			}
		}
		
//		System.out.println("keypath:" + keypath + "/" + templeName);
//		System.out.println("result:["+ result + "]");
//		System.out.println();
		return result;
	}
}
