package com.mole.webengine.net;  
  

import java.io.IOException;  
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

public class TransferReponse extends HttpPacket{
	
	public boolean readHttpHead(InputStream  in) throws IOException{    	
    	String line = "";
        while ((line = readLine(in)) != null && !"".equals(line)) {
            if(line.startsWith("Content-Length:")){
            	String[] ss = line.split(":");
            	dataLen = Integer.valueOf(ss[1].trim());
            } else if(line.startsWith("Transfer-Encoding: chunked")){
            	isChunk = true;
            } 
            heads.add(line);                
            //MyDebug.println(this.getClass().getName(), "readHttpHeads", line); 
        } 
        //MyDebug.println(this.getClass().getName(), "readHttpHeads", line); 
        return true;
    }
    
	public boolean transferHttpHead(HttpServletResponse response) throws IOException
	{
    	String[] strs = heads.get(0).split(" ");
    	//response.sendError(Integer.valueOf(strs[1]), strs[2]);
    	response.setStatus(Integer.valueOf(strs[1]));
    	for(int i=1; i<heads.size(); i++){
    		String line = heads.get(i);
    		
    		int index = line.indexOf(":");
    		if(index > 0){
    			String key = line.substring(0, index);
    			String value = line.substring(index+2, line.length());
    			//tomcat会重新加上Transfer标记
    			if(key.equals("Transfer-Encoding")){
    				continue;
    			} else if(key.equals("Connection")){
    				continue;
    			} 
    			response.addHeader(key, value);
    		}
    		
    	}
		return true;
	}
	
    public boolean transferHttp(InputStream in, HttpServletResponse response) throws Exception {
    	transferHttpHead(response);
    	transferHttpBody(in, response.getOutputStream());
    	return true;
	}
    
}  