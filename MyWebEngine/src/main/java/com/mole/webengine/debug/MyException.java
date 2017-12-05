package com.mole.webengine.debug;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyException extends Exception{
	private static final long serialVersionUID = 4634837918550450016L;
	
	final static Logger logger = LoggerFactory.getLogger(MyDebug.class);
	String mymessage = null;
	
	public MyException(String message) {
        super(message);
    }
	
	private StringBuffer assembleMsg(Object args[]){
		StringBuffer msgbuf = new StringBuffer();
		for (Object v : args) {			
			msgbuf.append(v.toString());
			msgbuf.append(" ");
        }
		return msgbuf;
	}
	public MyException(Exception e, Object... args) {		
		StringBuffer msgbuf = assembleMsg(args);		
		msgbuf.append("\r\n");
		if(e.getStackTrace().length >= 1){
			msgbuf.append(e.getStackTrace()[0].toString());			
		}
		msgbuf.append("\r\n");
		msgbuf.append(e.getMessage());
		mymessage = msgbuf.toString();
		logger.error(mymessage);
    }
	
	public MyException(Object... args) {		
		mymessage = assembleMsg(args).toString();
		logger.error(mymessage);
	}
	
	private static int throwEx(int type) throws Exception{
		switch(type){
			case 0:
				break;
			case 1:
				throw new Exception("new Exception");				
			case 2:
				throw new MyException("new MyException");
			case 3:
				throw new MyException("new MyException-->", "a ", " 1");
			case 4:
				throw new MyException(new Exception("i am a Exception"), "new MyException ", "hello ", "world");
		}
		return type;
	}
	public String getMessage() {
		if(mymessage != null){
			return mymessage;
		}
        return super.getMessage();
    }
	private static void test(int type) throws Exception{
		try{
			int ret = throwEx(type);
			System.out.println("test-->" + ret);
		} catch(MyException e){
			System.out.println("MyException-->" + e.getMessage());
		} catch(Exception e){
			System.out.println("Exception-->" + e.getMessage());
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		test(0);
		test(1);
		test(2);
		test(3);
		test(4);
	}
}
