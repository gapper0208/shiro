package com.gao.h_authorizing;

import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

public class MyRolePermissionResolver implements RolePermissionResolver {
	
	private static Logger log = Logger.getLogger(MyRolePermissionResolver.class.getName());
	
	@Override
	public Collection<Permission> resolvePermissionsInRole(String roleString) {
		// 这里为了简单起见，采用硬编码方式，只要用户包含admin角色，就返回user:*权限
//		权限类型为WildcardPermission
//		if("admin".equals(roleString)) {
//			log.info("根据角色解析出权限集合: " + roleString + ", 得到权限集合 [user:save]");
//			return Arrays.asList(new WildcardPermission("user:save"));
//		} else if("guest".equals(roleString)) {
//			log.info("根据角色解析出权限集合: " + roleString + ", 得到权限集合 [user:find]");
//			return Arrays.asList(new WildcardPermission("user:find"));
//		}
		
//		权限类型为BitPermission
		if("admin".equals(roleString)) {
			log.info("根据角色解析出权限集合: " + roleString + ", 得到权限集合 [user+1]");
			return Arrays.asList(new BitPermission("user+1"));
		} else if("guest".equals(roleString)) {
			log.info("根据角色解析出权限集合: " + roleString + ", 得到权限集合 [user+4]");
			return Arrays.asList(new BitPermission("user+4"));
		}
		
		return null;
	}
}
