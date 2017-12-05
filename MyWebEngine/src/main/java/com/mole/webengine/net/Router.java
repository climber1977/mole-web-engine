package com.mole.webengine.net;

import java.net.Socket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mole.webengine.utils.Pair;
import com.mole.webengine.utils.RequestUtils;

public class Router{
	public static boolean transferRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String url = RequestUtils.getUrl(request);
		Pair<String, Integer> p = RouterTable.getIpPort(url);
		if(p == null){
			return false;
		}
		
		TransferRequest requestData = new TransferRequest();
		//可能需要根据http请求地址，确认转发的地址ip，因此先把文件头读出来
		requestData.readHttpHead(request);			
    	Socket server = new Socket(p.first(), p.second());
    	
    	requestData.transferHttp(request, server.getOutputStream()); 
    	
    	TransferReponse responseData = new TransferReponse();
    	responseData.readHttpHead(server.getInputStream());
        responseData.transferHttp(server.getInputStream(), response);
        
        server.close();	  
        response.getOutputStream().flush();
        response.getOutputStream().close();
		
		return true;
    }
}
