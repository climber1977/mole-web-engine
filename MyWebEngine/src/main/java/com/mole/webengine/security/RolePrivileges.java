package com.mole.webengine.security;

public class RolePrivileges{
	private static Privileges<Long> p = new Privileges<Long>(); 
	
	public static void init() throws Exception{
		String sql = "select id, resid, roleid from wb_res_role_relation";
		p.init(sql, "resid", "roleid");		
	}
	public static boolean hasPrivilege(Long resid, Long roleid) throws Exception{
		return p.hasPrivilege(resid, roleid);
	}
}
