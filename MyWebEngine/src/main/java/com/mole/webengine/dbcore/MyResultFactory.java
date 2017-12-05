package com.mole.webengine.dbcore;

public class MyResultFactory {

	public static MyResult createMyResult(int returnType, String sql){
		switch(returnType){
		case MyJDBC.RETURN_INT:
			return new MyResult(sql);
		case MyJDBC.RETURN_LIST_LIST:
			return new MyResultListList(sql);
		case MyJDBC.RETURN_LIST_MAP:
			return new MyResultListMap(sql);
		}
		
		return null;
	}
}
