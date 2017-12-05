package com.mole.webengine.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mole.webengine.debug.MyDebug;
import com.mole.webengine.debug.MyException;
import com.mole.webengine.net.Router;
import com.mole.webengine.security.AuthorizedServerPrivileges;
import com.mole.webengine.security.Resource;
import com.mole.webengine.security.SecurityUser;
import com.mole.webengine.system.SysParams;
import com.mole.webengine.utils.Pair;
import com.mole.webengine.utils.RequestUtils;
import com.mole.webengine.utils.ResponseUtils;

public class MyFilter implements Filter {		
	    public void destroy() {}  
	    
	    private boolean hasResPrivilege(HttpServletRequest req, HttpServletResponse resp) throws Exception, Exception{
	    	String url = RequestUtils.getFile(req);
	    	Pair<Long, Boolean> res = Resource.getResId(url);
			if(res.first() == -1){
				return true;
			}
			if(SysParams.isUsePrivilege()){
				if(SecurityUser.hasResPrivilege(res, req, resp)){
					return true;
				}
			}
			
			if(SysParams.isServerPrivilege()){
				if(AuthorizedServerPrivileges.isAuthorized(req)){
		    		return true;
		    	}
			}
			
			if(!SysParams.isUsePrivilege() && !SysParams.isServerPrivilege()){
				return true;
			}
			if(url.equals(SysParams.getStringParam("homeUrl1")) 
				|| url.equals(SysParams.getStringParam("homeUrl2"))){
				resp.sendRedirect(SysParams.getStringParam("loginUrl"));
			}
			ResponseUtils.writeUtf8(resp, "no privilege\r\n没有权限");
			return false;
	    }
	    
	    public void doFilter(ServletRequest request, ServletResponse response,  
	            FilterChain chain) {  
	      	
	        HttpServletRequest req      = (HttpServletRequest)request;   
	        HttpServletResponse resp    = (HttpServletResponse)response;
	        
	        String result = null;
			try {
				req.setCharacterEncoding("UTF-8");				
				if(!hasResPrivilege(req, resp)){
					return;
				}
				if(SysParams.isUseProxy()){
					if(Router.transferRequest(req, resp)){
						return;
					}
				}
				chain.doFilter(request, response);
				return;
			} catch (MyException e) {
				result = e.getMessage();
			}
			catch (Exception e) {
				result = MyDebug.dealException(this.getClass().getName(), "doFilter", e);
			}
			try {
				resp.getWriter().write(result);
			} catch (IOException e1) {					
				MyDebug.dealException(this.getClass().getName(), "doFilter", e1);
			}
	    }  
	      
	    public void init(FilterConfig filterConfig) throws ServletException {}  
	  
	} 
