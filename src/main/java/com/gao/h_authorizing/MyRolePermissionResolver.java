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
		// ����Ϊ�˼����������Ӳ���뷽ʽ��ֻҪ�û�����admin��ɫ���ͷ���user:*Ȩ��
//		Ȩ������ΪWildcardPermission
//		if("admin".equals(roleString)) {
//			log.info("���ݽ�ɫ������Ȩ�޼���: " + roleString + ", �õ�Ȩ�޼��� [user:save]");
//			return Arrays.asList(new WildcardPermission("user:save"));
//		} else if("guest".equals(roleString)) {
//			log.info("���ݽ�ɫ������Ȩ�޼���: " + roleString + ", �õ�Ȩ�޼��� [user:find]");
//			return Arrays.asList(new WildcardPermission("user:find"));
//		}
		
//		Ȩ������ΪBitPermission
		if("admin".equals(roleString)) {
			log.info("���ݽ�ɫ������Ȩ�޼���: " + roleString + ", �õ�Ȩ�޼��� [user+1]");
			return Arrays.asList(new BitPermission("user+1"));
		} else if("guest".equals(roleString)) {
			log.info("���ݽ�ɫ������Ȩ�޼���: " + roleString + ", �õ�Ȩ�޼��� [user+4]");
			return Arrays.asList(new BitPermission("user+4"));
		}
		
		return null;
	}
}
