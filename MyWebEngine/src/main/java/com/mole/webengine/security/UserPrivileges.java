package com.mole.webengine.security;

public class UserPrivileges{
	private static Privileges<Long> p = new Privileges<Long>(); 
	
	public static void init() throws Exception{
		String sql = "select id, resid, userid from wb_res_user_relation";
		p.init(sql, "resid", "userid");
	}
	public static boolean hasPrivilege(Long resid, Long userid) throws Exception{
		return p.hasPrivilege(resid, userid);
	}
}
