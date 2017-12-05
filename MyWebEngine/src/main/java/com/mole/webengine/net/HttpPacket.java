package com.mole.webengine.net;  

import java.io.IOException;  
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class HttpPacket{  
	public List<String> heads = new ArrayList<String>(); 
	public int dataLen = 0;
	public boolean isChunk = false;
    
    protected String readLine(InputStream in) throws IOException {
		byte r[] = new byte[2048];
		int i = 0;
		int read =0;
		do{
			read=in.read();
			if(read==-1){
				return null;
			}
			r[i] = (byte)read;
			i++;
		}while(read!='\n');		
		
		String res =new String(r, 0, i-2); 
		return res; 
	}
    public String getFirstLine() throws IOException{
    	return heads.get(0);
    }    

    protected boolean transferData(InputStream  in, int len, OutputStream out) throws IOException{
    	if(len <= 0){
    		return true;
    	}
    	
    	byte[] data = new byte[len];
    	int read=0;
		while(read<len){
			//因为
			int r = in.read(data, 0, len-read);
			if(r > 0){
				out.write(data, 0, r);
			}
			if(r==-1 || r+read == len){
				break;
			}
			read+=r;
		}
		//MyDebug.println(new String(body));
        return true;
    }
    protected boolean transferHttpCommonBody(InputStream  in, OutputStream out) throws IOException{
    	if(dataLen <= 0){
    		return true;
    	}    	
    	transferData(in, dataLen, out);
    	return true;
    }
    
    protected boolean transferHttpChunk(InputStream  in, OutputStream out) throws IOException{
    	//int CRLFLen = 2;
    	String lengthOfHex = readLine(in);  //chunk-size and CRLF
    	int length = Integer.valueOf(lengthOfHex, 16); 
    	    	
    	if(length > 0){
    		//数据
    		transferData(in, length, out);
    	}    		
    	//读出CRLF或footer,丢弃
		readLine(in);  	
    	
    	return !(length == 0);
    }
   
    public boolean transferHttpBody(InputStream  in, OutputStream out) throws IOException{
    	if(isChunk){
    		while(transferHttpChunk(in, out));
    	}
    	transferHttpCommonBody(in, out);

    	return false;
    }
    
    protected boolean transferHeads(OutputStream  out) throws IOException{
    	for(int i=0; i<heads.size(); i++){
    		String line = heads.get(i) + "\r\n";
    		out.write(line.getBytes());
    	}
    	out.write("\r\n".getBytes());
    	return true;
    }
}  