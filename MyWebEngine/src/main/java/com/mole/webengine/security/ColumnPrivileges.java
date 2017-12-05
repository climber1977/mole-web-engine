package com.mole.webengine.security;

public class ColumnPrivileges {
	private static Privileges<String> p = new Privileges<String>(); 

	public static void init() throws Exception{
		String sql = "SELECT lower(concat(concat(table_name, '_'), field_name)) as field, " +
				"roleid FROM wb_column_role_relation";
				
		p.init(sql, "field", "roleId");	
	}
	//roleId标示不需要任何权限验证
	public static boolean hasPrivilege(String tableName, String fieldName, Long roleId) throws Exception{
		String key = tableName + "_" + fieldName;
		key = key.toLowerCase();
		
		return p.hasPrivilege(key, roleId);
	}
}
