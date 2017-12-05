package com.mole.webengine.net;  
  
import java.io.IOException;  

import java.io.OutputStream;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

import com.mole.webengine.utils.RequestUtils;

public class TransferRequest extends HttpPacket{  
    
    public void readHttpHead(HttpServletRequest request) throws Exception
	{
    	String method = request.getMethod();
    	
    	String url = RequestUtils.getUrl(request);
    	url = RouterTable.getDestinationlUrl(url); 
		
    	String protocol = request.getProtocol();
    	String line = method + " " + url+ " " + protocol;

        heads.add(line);
        
		//设置HTTP包头
		Enumeration<?> e = request.getHeaderNames();  
        while(e.hasMoreElements()){  
             String name = (String) e.nextElement();  
             String value = (String)request.getHeader(name);  
             line = name + ":" + value;             
             heads.add(line);
         } 
	}

    public boolean transferHttp(HttpServletRequest request, OutputStream out) throws IOException
	{
    	transferHeads(out);
		//取得原包体
    	dataLen = request.getContentLength();
    	transferHttpBody(request.getInputStream(), out);
		
		return true;
	}
}  