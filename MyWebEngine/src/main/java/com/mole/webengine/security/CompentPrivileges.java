package com.mole.webengine.security;

public class CompentPrivileges {	
	private static Privileges<String> p = new Privileges<String>(); 

	public static void init() throws Exception{
		String sql = "SELECT lower(compentid) compentid, roleId FROM wb_compent_role_relation";
				
		p.init(sql, "compentid", "roleId");
	}
	//roleId标示不需要任何权限验证
	public static boolean hasPrivilege(String compentid, Long roleid) throws Exception{
		String key = compentid.toLowerCase();		
		return p.hasPrivilege(key, roleid);
	}
}
