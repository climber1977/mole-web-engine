package com.mole.webengine.security;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import com.mole.webengine.dbcore.MyDBField;
import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResultSetMetaData;
import com.mole.webengine.system.SysParams;
import com.mole.webengine.utils.Pair;
import com.mole.webengine.utils.RequestUtils;
import com.mole.webengine.utils.ResponseUtils;

public class SecurityUser{
	private long userId;
	private static final String session_securityUser = "securityUser";
	
	/** 线程内共享SecurityUser，ThreadLocal通常是全局的，支持泛型 */  
    private static ThreadLocal<SecurityUser> threadLocalUser = new ThreadLocal<SecurityUser>();
    
	public long getUserId() {
		return userId;
	}
	
	private List<Long> roles;
	
	public List<Long> getRoles() {
		return roles;
	}

	public void init(long userid) throws Exception{
		userId = userid;
		roles = UserRoleRelation.getDatableDescByGroupid(userid);		
	}
	public static SecurityUser getThreadLocalUser(){
		return threadLocalUser.get();
	}
	public static SecurityUser getUser(){
		return getThreadLocalUser();
	}
	public static void setThreadLocalUser(SecurityUser u){
		threadLocalUser.set(u);
	}
	public static boolean hasResPrivilege(Pair<Long, Boolean> res, HttpServletRequest request, HttpServletResponse response) 
			throws Exception{		
		SecurityUser u = (SecurityUser)request.getSession().getAttribute(session_securityUser);
		//该方法在安全检查的filter中调动，没调用一次，都将当前request中的SecurityUser，同步到线程中，防止因线程销毁，而丢失
		setThreadLocalUser(u);
		if(u == null){			
			return false;
		}
		
		if(UserPrivileges.hasPrivilege(res.first(), u.getUserId())){
			return true;
		}
		
		List<Long> r = u.getRoles();
		for(int i=0; i<r.size(); i++){
			if(RolePrivileges.hasPrivilege(res.first(), r.get(i))){
				return true;
			}
		}
		
		if(res.second()){
			return true;
		}
		ResponseUtils.writeUtf8(response, "no privilege\r\n没有权限");
		return false;
	}
	public static void initColumnPrivilege(MyJDBC db, boolean isShowColumn) throws Exception{
		if(!SysParams.isUseColumnPrivilege()){
			return;
		}
		Class<SecurityUser> clazz = SecurityUser.class;
        Method m = clazz.getDeclaredMethod("getColumnPrivilege", boolean.class, MyResultSetMetaData.class);
        db.setInitColumnPrivilegeMethod(isShowColumn, m);
	}
	
	public static MyResultSetMetaData getColumnPrivilege(boolean isShowColumn, MyResultSetMetaData rsm) 
			throws Exception{
		if(!SysParams.isUsePrivilege()){
			return rsm;
		}
		SecurityUser u = getUser();	
		MyResultSetMetaData rsmNew = new MyResultSetMetaData();
		boolean canAccess = false;
		MyDBField field = null;
		for(int i=0; i<rsm.getColumnCount(); i++){
			field = rsm.getColumn(i);
			canAccess = false;
			if(!SysParams.isUsePrivilege()){
				canAccess = true;
			}
			else if(u == null || u.getRoles().size() <= 0){
				canAccess = ColumnPrivileges.hasPrivilege(field.getTableName(), 
						field.getColumnName(), -1L);
			} else {
				int j = 0;
				for(j=0; j<u.getRoles().size(); j++){
					canAccess = ColumnPrivileges.hasPrivilege(field.getTableName(), 
							field.getColumnName(), u.getRoles().get(j));
					if(canAccess){
						break;
					}
				}
			}
			if(!canAccess){
				if(!isShowColumn){
					continue;
				}
				field.setCanAccess(canAccess);
				field.setShow(isShowColumn);
			}
			rsmNew.addColumn(field);
		}
		return rsmNew;
		
	}
	
	public static boolean hasColumnPrivilege(String tableName, String fieldName) 
			throws Exception{
		if(!SysParams.isUsePrivilege()){
			return true;
		}
		SecurityUser u = SecurityUser.getUser();
		if(u == null || u.getRoles().size() <= 0){
			return ColumnPrivileges.hasPrivilege(tableName, fieldName, -1L);
		}
		return u.hasColumnPrivilege2(tableName, fieldName);
		
	}
	private  boolean hasColumnPrivilege2(String tableName, String fieldName) 
			throws Exception{	
		int i;
		for(i=0; i<getRoles().size(); i++){
			boolean b = ColumnPrivileges.hasPrivilege(tableName, 
					fieldName, getRoles().get(i));
			if(b){
				return true;
			}
		}
		
		return false;
	}
	private  boolean hasButtonPrivilege2(Long buttonId) throws Exception{	
		int i;
		
		for(i=0; i<getRoles().size(); i++){
			boolean b = ButtonPrivileges.hasPrivilege(buttonId, getRoles().get(i));
			if(b){
				return true;
			}
		}
		
		return false;
	}
	public  static boolean hasButtonPrivilege(Long buttonId) throws Exception{	
		if(!SysParams.isUsePrivilege()){
			return true;
		}
		SecurityUser u = SecurityUser.getUser();
		if(u == null || u.getRoles().size() <= 0){
			return ButtonPrivileges.hasPrivilege(buttonId, -1L);
		}
		return u.hasButtonPrivilege2(buttonId);
	}
	private  boolean hasMenuPrivilege2(Long menuId) throws Exception{	
		int i;
		
		for(i=0; i<getRoles().size(); i++){
			boolean b = MenuPrivileges.hasPrivilege(menuId, getRoles().get(i));
			if(b){
				return true;
			}
		}
		
		return false;
	}
	public static boolean hasMenuPrivilege(Long menuId) throws Exception{	
		if(!SysParams.isUsePrivilege()){
			return true;
		}
		SecurityUser u = SecurityUser.getUser();
		if(u == null || u.getRoles().size() <= 0){
			return MenuPrivileges.hasPrivilege(menuId, -1L);
		}
		return u.hasMenuPrivilege2(menuId);
	}
	private  boolean hasCompentPrivilege2(String compentId) throws Exception{	
		int i;
		
		for(i=0; i<getRoles().size(); i++){
			boolean b = CompentPrivileges.hasPrivilege(compentId, getRoles().get(i));
			if(b){
				return true;
			}
		}
		
		return false;
	}
	public  static boolean hasCompentPrivilege(String compentId) throws Exception{	
		if(!SysParams.isUsePrivilege()){
			return true;
		}
		SecurityUser u = SecurityUser.getUser();
		if(u == null || u.getRoles().size() <= 0){
			return CompentPrivileges.hasPrivilege(compentId, -1L);
		}
		return u.hasCompentPrivilege2(compentId);
	}
	public static void registerUser(HttpServletRequest request, long userid) throws Exception{
		SecurityUser u = new SecurityUser();
		u.init(userid);
		request.getSession().setAttribute(session_securityUser, u);
		RequestUtils.putSessionParam(request, "s_userid", userid);
		
		setThreadLocalUser(u);
	}
	
	public static SecurityUser getUser(HttpServletRequest request){
		return (SecurityUser) request.getSession().getAttribute(session_securityUser);
	}
	public static SecurityUser getUser(PageContext pageContext){		
		return getUser((HttpServletRequest)pageContext.getRequest());
	}
}
