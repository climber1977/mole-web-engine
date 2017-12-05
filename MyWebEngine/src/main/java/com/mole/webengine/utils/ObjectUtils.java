package com.mole.webengine.utils;

import java.math.BigDecimal;
import java.util.Date;

public class ObjectUtils {
	public static String obj2String(Object o){
		if(o == null){
			return "";
		} else if(o.getClass().equals(String.class)){
			return (String)o;
		} 
		return o.toString();
	}
	
	public static Integer obj2Integer(Object o){
		if(o == null){
			return 0;
		} else if(o.getClass().equals(BigDecimal.class)){
			return ((BigDecimal)o).intValue();
		} else if(o.getClass().equals(Long.class)){
			return ((Integer)o).intValue();
		} else if(o.getClass().equals(Integer.class)){
			return ((Integer)o).intValue();
		}
		return Integer.valueOf(o.toString());
	}
	
	public static Boolean obj2Boolean(Object o){
		if(o == null){
			return false;
		}  else if(o.getClass().equals(Boolean.class)){
			return ((Boolean)o);
		} else if(o.getClass().equals(BigDecimal.class)){
			return ((BigDecimal)o).longValue() > 0L;
		} else if(o.getClass().equals(Long.class)){
			return ((Long)o) > 0L;
		} else if(o.getClass().equals(Integer.class)){
			return ((Integer)o) > 0;
		} 
		return Boolean.valueOf(o.toString());
	}
	
	public static Long obj2Long(Object o){
		if(o == null){
			return 0L;
		} else if(o.getClass().equals(BigDecimal.class)){
			return ((BigDecimal)o).longValue();
		} else if(o.getClass().equals(Long.class)){
			return ((Long)o).longValue();
		} else if(o.getClass().equals(Integer.class)){
			return ((Integer)o).longValue();
		}
		return Long.valueOf(o.toString());
	}
	
	public static Date obj2Date(Object o) throws Exception{
		if(o == null){
			return null;
		} else if(o.getClass().equals(Date.class)){
			return (Date) o;
		} else if(o.getClass().equals(java.sql.Date.class)){
			return new java.util.Date(((java.sql.Date)o).getTime());
		} else if(o.getClass().equals(java.sql.Time.class)){
			return new java.util.Date(((java.sql.Time)o).getTime());
		} else if(o.getClass().equals(java.sql.Timestamp.class)){
			return new java.util.Date(((java.sql.Timestamp)o).getTime());
		} 

		throw new Exception("Object is not Date type.");
	}
}
