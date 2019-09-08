package com.gao.h_authorizing;

import java.util.logging.Logger;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

public class BitAndWildCardPermissionResolver implements PermissionResolver {
	
	private static Logger log = Logger.getLogger(BitAndWildCardPermissionResolver.class.getName());
	
	@Override
	public Permission resolvePermission(String permissionString) {
		if(permissionString.contains("+")) {
			log.info("将字符串权限解析为BitPermission：" + permissionString);
			return new BitPermission(permissionString);
		}
		log.info("将字符串权限解析为WildcardPermission：" + permissionString);
		return new WildcardPermission(permissionString);
	}
}
