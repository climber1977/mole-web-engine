package com.mole.webengine.utils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	
	public static String null2String(Object o){
		if(o == null){
			return "";
		} else {
			return o.toString();
		}
	}
	public static String null2String(Object o, String def){
		String result;
		if(o == null) {
			result = def;
		} else {
			result = o.toString();
			if(result.equals("")){
				result = def;
			}
		}
		return result;
	}
	public static Long null2Long(BigDecimal o){
		if(o == null) {
			return -1L;
		} else {
			return o.longValue();
		}
	}
	
	public static String utf2bgk(String str) throws Exception{
		return changeCharset(str,  "GBK");
	}	
	
	public static String changeCharset(String str, String charsetName) throws Exception{
		String gbkStr = new String(str.getBytes(charsetName),  charsetName);
		
		return gbkStr;
	}	
	
	public static String removeBracket(String str){
		if(str.startsWith("{")){
			str = str.substring(1);
		}
		if(str.endsWith("}")){
			str = str.substring(0,  str.length()-1);
		}
		return str;
	}
	
	public static String formateTemplate(String template, MyParams params, boolean emptyContinue, String singleQuoteTransfer, boolean keepOriginal){
		if(params == null){
			return template;
		}
		Pattern pattern = Pattern.compile("\\{.*?\\}");
		Matcher matcher = pattern.matcher(template);
		
		StringBuffer result = new StringBuffer();
		boolean match = true;
		while (matcher.find()) {
			//System.out.println(matcher.group());
			if(!params.containsKey(matcher.group())  && !emptyContinue){
				match = false;
				break;
			}			
			String p = params.getString(matcher.group());
			if((p == null || p.equals("")) && !emptyContinue){
				match = false;
				break;
			}
			
			if(p == null){
				p = "";
			}
			if(singleQuoteTransfer != null && !keepOriginal){
				p = p.replace("\'", singleQuoteTransfer);
			}
		    matcher.appendReplacement(result, p);
		    //System.out.println(conSql.toString());
		}
		matcher.appendTail(result);
		
		return match ? result.toString() : "";
	}
	//取得不带参数的请求地址
	public static String getDestinationlUrl(String url, String remoteStartUrl, String regex){		
		String nurl = "";
		if(url.matches(regex + ".*?")){
			nurl = url.replaceFirst(regex, remoteStartUrl);
		}
		return nurl;
	}
	
	//修正sql，去掉and()
	public static String modifySqlEx(String sql){
		Pattern pattern = Pattern.compile("and\\s*\\(\\s*\\)");
		Matcher matcher = pattern.matcher(sql);		
		String result = matcher.replaceAll(" ");
		
		pattern = Pattern.compile("or\\s*\\(\\s*\\)");
		matcher = pattern.matcher(result);
		result = matcher.replaceAll(" ");
		
		pattern = Pattern.compile("\\(\\s*or");
		matcher = pattern.matcher(result);
		result = matcher.replaceAll("( ");
		
		return result;
	}
	public static String modifySql(String sql){
		String oldsql = "";
		while(!oldsql.equals(sql)){
			oldsql = sql;
			sql = modifySqlEx(sql);
		}
		
		return sql;
	}
	//匹配排序
	public static boolean matchOrder(String sql){
		//文字替换（全部）
		Pattern pattern = Pattern.compile("order\\s.*by\\s.*");
		Matcher matcher = pattern.matcher(sql);
		boolean result = matcher.find();
		return result;
	}
	
	public static boolean isLegalName(String str){
		Pattern pattern = Pattern.compile("^[a-zA-Z_][a-zA-Z0-9_]*",Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
}
