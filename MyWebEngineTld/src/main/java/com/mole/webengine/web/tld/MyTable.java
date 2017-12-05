package com.mole.webengine.web.tld;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;

import com.mole.webengine.dbapp.ExecutePageDatablesDescInfo;
import com.mole.webengine.dbapp.ExecutePageDatablesInfo;
import com.mole.webengine.dbapp.ExecutePageDatablesManager;
import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.debug.MyDebug;
import com.mole.webengine.file.FilesCache;
import com.mole.webengine.resmanager.I18n;
import com.mole.webengine.security.SecurityUser;
import com.mole.webengine.utils.JsonUtils;
import com.mole.webengine.utils.PageContextUtils;
import com.mole.webengine.utils.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


@SuppressWarnings( { "serial" })
public class MyTable extends MyBase {
	
	private static JSONObject createThContent(int i, long langueIndex, ExecutePageDatablesDescInfo info) throws Exception{						
		JSONObject json = new JSONObject();
		
		String field = info.getField();
		json.put("dataset", field);
		if(info.getTrTemplate().isEmpty()){
			json.put("templet", "default_table_th_check.html");
		}else{
			json.put("templet", info.getTrTemplate());
		}
		return json;
	}
	private static JSONObject createTh(int i, long langueIndex, ExecutePageDatablesDescInfo info) throws Exception{
		JSONObject json = new JSONObject();
		String width = info.getWidth();
		
		json.put("width", width);
		json.put("templet", "default_table_th.html");
		
		String thContent = I18n.getLangue(info.getAlias(), langueIndex);
		boolean chk = info.isCheckbox();	
		if(chk){
			JSONObject jchk = createThContent(i, langueIndex, info);	
			json.put("child", jchk);
			json.put("content", "th_content");
		} else {
			json.put("th_content", thContent);
		}
		return json;
	}
	public static String createTable(String dbs, MyJDBC db, String uuid, String jsonDesc, 
			String dataUrl, long langueIndex) throws Exception{		
		JSONObject jroot = null;
		if(jsonDesc.isEmpty()){
			jroot = new JSONObject();
		} else {
			String jstring = FilesCache.getJson(jsonDesc);
			jroot = JsonUtils.jstring2JObject(jstring);
		}
		JSONArray jchild = new JSONArray();
		String sorting = "";

		ExecutePageDatablesInfo info = ExecutePageDatablesManager.getDatableByUUID(uuid);
		List<ExecutePageDatablesDescInfo> lst = ExecutePageDatablesManager.getDataTableDesc(uuid);
		
		int j = 0;
		for(int i=0; i<lst.size(); i++){
			
			if(!SecurityUser.hasColumnPrivilege(info.getTableName(), lst.get(i).getField())){
				continue;
			}
			JSONObject jth = createTh(i, langueIndex, lst.get(i));				
			jchild.add(jth);
			
			if(!lst.get(i).isSorting()){
				if(!sorting.equals("")){
					sorting += ",";
				}
				sorting += String.valueOf(j);
			}	
			j++;
		}		
		
		jroot.put("content", "tr_content");
		jroot.put("uuid", uuid);
		jroot.put("dataUrl", dataUrl);
		jroot.put("templet", "default_table.html");
		jroot.put("sorting", sorting);
		JsonUtils.putObject(jroot, "templet", info.getTemplet());
		JsonUtils.putObject(jroot, "displayquery", info.getDisplayQuery());
		JsonUtils.putObject(jroot, "displayimport", info.getDisplayImport());
		JsonUtils.putObject(jroot, "displayexport", info.getDisplayExport());		
		JsonUtils.putString(jroot, "fverifyparam", info.getJsVerifyParam());
		String myjs = info.getMyjs();
		if(myjs == null){
			myjs = "";			
		}else{
			myjs = myjs.replace("$", "\\$");
		}
		JsonUtils.putString(jroot, "myjs", myjs);
		jroot.put("child", jchild);
		if(!info.getDbs().isEmpty()){
			jroot.put("dbs", info.getDbs());
		}else{
			jroot.put("dbs", dbs);
		}
		
		String table = MyBase.createPortal(jroot, 0, null, ""); 
		
		return table;
	}
	@Override
	public int doStartTag() throws JspException {
		//MyDebug.println(this.getClass().getName(), "doStartTag", "uuid=", uuid);		
		String result;
		try {
			String dbs = getDbs();
			MyJDBC db = (MyJDBC)PageContextUtils.getAttribute(pageContext, "db");
			
			String uuid = getUuid();
			String dataUrl = getUrl(uuid, "showColumn=0");
			String jsonDesc = getJsonDesc();
			long langueIndex = I18n.getLanague(pageContext);	
			result = createTable(dbs, db, uuid, jsonDesc, dataUrl, langueIndex);
			pageContext.getOut().write(result);
		} catch (Exception e) {
			MyDebug.dealException(this.getClass().getName(), "doStartTag",  e);
		}
		
		return SKIP_BODY;
	}
	private static String formateHtml(String html, int row, MyResult rs) throws Exception{
		Pattern pattern = Pattern.compile("\\{.*?\\}");
		Matcher matcher = pattern.matcher(html);
		
		StringBuffer result = new StringBuffer();
		boolean match = true;
		while (matcher.find()) {
			String field = StringUtils.removeBracket(matcher.group());
			if(rs.getColumnIndex(field) ==-1){
				match = false;
				break;
			}
				
			Object obj = rs.getValue(row, field);
			if(obj == null){
				match = false;
				break;
			}
		    matcher.appendReplacement(result, obj.toString());
		    //System.out.println(conSql.toString());
		}
		matcher.appendTail(result);
		
		return match ? result.toString() : "";
	}
	public static List<List<Object>> formatHtmlFiled(MyResult rs, List<ExecutePageDatablesDescInfo> descs) throws Exception{		
		List<List<Object>> list = new ArrayList<List<Object>>();
		String fieldName = null;
		String item = "";
		String html = null;
		String tdTemplateName = null;
		boolean chk = false;
		for(int i=0; i<rs.getRowCount(); i++){
			List<Object> row = new ArrayList<Object>();
			for(int j=0; j<descs.size(); j++){				
				html = descs.get(j).getHtml();
				fieldName = descs.get(j).getField();	
				tdTemplateName = descs.get(j).getTdTemplate();
				chk = descs.get(j).isCheckbox();
				if(chk){					
					JSONObject jroot = new JSONObject();
					jroot.put("columnLabel", fieldName);
					jroot.put("value", rs.getString(i, fieldName));
					if(tdTemplateName.isEmpty()){
						jroot.put("templet", "default_table_td_check.html");
					} else {
						jroot.put("templet", tdTemplateName);
					}
					
					item = MyBase.createPortal(jroot, 0, null, ""); 
				} else if(!tdTemplateName.isEmpty()){
					String template = FilesCache.getTemplate(tdTemplateName);
					item = formateHtml(template, i, rs);
				}
				else if(html.equals("")){
					if(fieldName.equals("")){
						item = rs.getString(i, j);	
					} else {
						if(rs.getColumnIndex(fieldName) == -1){
							//无权限访问时，不再返回列表中
							continue;
						}
						item = rs.getString(i, fieldName);
					}
				} else {
					item = formateHtml(html, i, rs);
				}			
				
				row.add(item);
			}
			list.add(row);
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static List<List<Object>> formatResult(String uuid, MyResult rs) throws Exception{		
		List<ExecutePageDatablesDescInfo> lst = ExecutePageDatablesManager.getDataTableDesc(uuid);
		if(lst == null){
			return (List<List<Object>>)rs.getResultObject();
		}
		return formatHtmlFiled(rs, lst);
	}
}
