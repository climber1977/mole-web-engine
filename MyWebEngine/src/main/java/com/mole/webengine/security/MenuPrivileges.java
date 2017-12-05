package com.mole.webengine.security;

public class MenuPrivileges {
	private static Privileges<Long> p = new Privileges<Long>(); 

	public static void init() throws Exception{
		String sql = "SELECT * FROM wb_menu_role_relation";
				
		p.init(sql, "menuid", "roleId");
	}
	//roleId标示不需要任何权限验证
	public static boolean hasPrivilege(Long menuid, Long roleId) throws Exception{
		return p.hasPrivilege(menuid, roleId);
	}
}
