package com.mole.test.webengine.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mole.webengine.utils.StringUtils;


public class ReplaceUrlTest {
	public static void replaceUrl1(){		
		//文字替换（置换字符）
		String lurl = "http://localhost:8080/metronic/assets/global/plugins/font-awesome/css/font-awesome.min.css";
		String rhost = "http://test.mole.com:8080/metronic";
		
		Pattern pattern = Pattern.compile("http://.*?/metronic");
		Matcher matcher = pattern.matcher(lurl);
		StringBuffer sbr = new StringBuffer();
		while (matcher.find()) {
			System.out.println(matcher.group());			
		    matcher.appendReplacement(sbr, rhost);
		    System.out.println(sbr.toString());
		}
		matcher.appendTail(sbr);
		System.out.println(sbr.toString());
	}	
	
	public static void testMatches(){	
		String lurl = "http://localhost:8080/metronic/assets/global/plugins/font-awesome/css/font-awesome.min.css";
		String regex = "http://.*?/metronic.*?";
		
		System.out.println("lurl=" + lurl);
		System.out.println("regex=" + regex);
		boolean m = lurl.matches(regex);		
		System.out.println("m=" + m);
		
		m = Pattern.matches(regex, lurl);
		System.out.println("m=" + m);
		
		lurl = "abc";
		regex = "abc";
		System.out.println();
		System.out.println("lurl=" + lurl);
		System.out.println("regex=" + regex);
		m = lurl.matches(regex);		
		System.out.println("m=" + m);
		
		lurl = "abc";
		regex = "abc*";
		System.out.println();
		System.out.println("lurl=" + lurl);
		System.out.println("regex=" + regex);
		m = lurl.matches(regex);		
		System.out.println("m=" + m);
		
		lurl = "abcd";
		regex = "abc.*";
		System.out.println();
		System.out.println("lurl=" + lurl);
		System.out.println("regex=" + regex);
		m = lurl.matches(regex);		
		System.out.println("m=" + m);
		
		lurl = "abcd";
		regex = "abc.*?";
		System.out.println();
		System.out.println("lurl=" + lurl);
		System.out.println("regex=" + regex);
		m = lurl.matches(regex);		
		System.out.println("m=" + m);
		
		lurl = "http://localhost:8080/metronic";
		regex = "http://.*?/metronic";
		System.out.println();
		System.out.println("lurl=" + lurl);
		System.out.println("regex=" + regex);
		m = lurl.matches(regex);		
		System.out.println("m=" + m);
	}
		
	
	public static void main(String args[]) throws Exception {
		
		replaceUrl1();		
		
		System.out.println();
		
		String regex = "http://.*?/metronic";
		String remoteStartUrl = "http://test.mole:8080/metronic";
		String url = "http://localhost:8080/metronic/assets/global/plugins/font-awesome/css/font-awesome.min.css";
		System.out.println("nurl=" + StringUtils.getDestinationlUrl(url, remoteStartUrl, regex));
		
		url = "http://localhost:8080/myweb/my/index.jsp";
		System.out.println("nurl=" + StringUtils.getDestinationlUrl(url, remoteStartUrl, regex));
		
//		System.out.println();
//		testMatches();
	}
	
	
}
