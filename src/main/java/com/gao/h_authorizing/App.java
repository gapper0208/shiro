package com.gao.h_authorizing;

import java.util.logging.Logger;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class App {
	
	private static Logger log = Logger.getLogger(App.class.getName());
	
	@Test
	public void test() throws Exception {
		DefaultSecurityManager securityManager = new DefaultSecurityManager();
		// �Զ���authorizer
		ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
		// �Զ���permissionResolver�����ڽ���Ȩ���ַ���
		BitAndWildCardPermissionResolver permissionResolver = new BitAndWildCardPermissionResolver();
		authorizer.setPermissionResolver(permissionResolver);
		// �Զ���rolePermissionResolver�����ڸ��ݽ�ɫ��ȡȨ��
		MyRolePermissionResolver rolePermissionResolver = new MyRolePermissionResolver();
		authorizer.setRolePermissionResolver(rolePermissionResolver);
		
		securityManager.setAuthorizer(authorizer);

		// �Զ���realm,������һ��Ҫ���������Ϊ��Ϊauthorizer����realm����ʱ����
		// ��Ϊ��Щrealm����permissionResolver��rolePermissionResolver������ʱҪ��֤��ǰ���Ѿ����ù���2������ˣ�
		MyRealm realm = new MyRealm();
		securityManager.setRealm(realm);
		
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("foo","123");
		
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			log.info("��֤ʧ��");
			return;
		}
		
		log.info("��֤�ɹ�");
	
//		String role = "admin";
//		if(subject.hasRole(role)) {
//			log.info(subject.getPrincipal() + "��" + role + "��ɫ");
//		} else {
//			log.info(subject.getPrincipal() + "û��" + role + "��ɫ");
//		}
		
		String permission = "user+0";
		if(subject.isPermitted(permission)) {
			log.info(subject.getPrincipal() + "��" + permission + "Ȩ��");
		} else {
			log.info(subject.getPrincipal() + "û��" + permission + "Ȩ��");
		}
		
	}
}
