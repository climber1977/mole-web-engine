package com.mole.webengine.security;



public class ButtonPrivileges {
	private static Privileges<Long> p = new Privileges<Long>();
	
	public static void init() throws Exception{
		String sql = "select id, buttonid, roleId from wb_button_role_relation";
		p.init(sql, "buttonid", "roleId");	
	}
	public static boolean hasPrivilege(Long buttonid, Long roleId) throws Exception{		
		return p.hasPrivilege(buttonid, roleId);
	}

}
