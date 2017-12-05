package com.mole.test.webengine.web;

import java.io.IOException;
import java.net.URL;

public class URLTest {
	
	public static void urlTest(String url) throws IOException{
		URL u = new URL(url);
		System.out.println("url=" + url);
		System.out.println("getFile=" + u.getFile());
		System.out.println("getPath=" + u.getPath());
		System.out.println("getPath=" + u.getHost());
		System.out.println();
	}
	public static void main(String args[]) throws Exception {
		
		String url = "http://localhost:8080/metronic/assets/global/plugins/font-awesome/css/font-awesome.min.css";
		urlTest(url);
		
		url = "http://localhost:8080/metronic/assets/global/plugins/font-awesome/css/font-awesome.min.css?a=123&b=中国";
		urlTest(url);
	}
	
	
}
