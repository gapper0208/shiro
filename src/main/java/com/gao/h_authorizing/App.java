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
		// 自定义authorizer
		ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
		// 自定义permissionResolver，用于解析权限字符串
		BitAndWildCardPermissionResolver permissionResolver = new BitAndWildCardPermissionResolver();
		authorizer.setPermissionResolver(permissionResolver);
		// 自定义rolePermissionResolver，用于根据角色获取权限
		MyRolePermissionResolver rolePermissionResolver = new MyRolePermissionResolver();
		authorizer.setRolePermissionResolver(rolePermissionResolver);
		
		securityManager.setAuthorizer(authorizer);

		// 自定义realm,该配置一定要放在最后，因为在为authorizer设置realm的这时机，
		// 会为这些realm设置permissionResolver和rolePermissionResolver，而此时要保证在前面已经设置过这2个组件了！
		MyRealm realm = new MyRealm();
		securityManager.setRealm(realm);
		
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("foo","123");
		
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			log.info("认证失败");
			return;
		}
		
		log.info("认证成功");
	
//		String role = "admin";
//		if(subject.hasRole(role)) {
//			log.info(subject.getPrincipal() + "有" + role + "角色");
//		} else {
//			log.info(subject.getPrincipal() + "没有" + role + "角色");
//		}
		
		String permission = "user+0";
		if(subject.isPermitted(permission)) {
			log.info(subject.getPrincipal() + "有" + permission + "权限");
		} else {
			log.info(subject.getPrincipal() + "没有" + permission + "权限");
		}
		
	}
}
