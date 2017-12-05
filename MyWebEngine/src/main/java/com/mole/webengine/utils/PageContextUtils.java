package com.mole.webengine.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

public class PageContextUtils {
	public static int getInt(PageContext pageContext, String key){
		Object o = pageContext.getAttribute(key);
		if(o == null){
			return 0;
		}
		return (int)o;
	}	
	
	public static Object getAttribute(PageContext pageContext, String key){
		return pageContext.getAttribute(key);
	}
	
	public static String getString(PageContext pageContext, String key){
		Object o = pageContext.getAttribute(key);
		if(o == null){
			return "";
		}
		return o.toString();
	}
	public static String getRequestParamString(PageContext pageContext, String key){
		HttpServletRequest r = (HttpServletRequest)pageContext.getRequest();
		return RequestUtils.getParamString(r, key);
	}
	
}
