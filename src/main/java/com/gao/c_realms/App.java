package com.gao.c_realms;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

// 多realm
public class App {
	@Test
	public void testName() throws Exception {
		DefaultSecurityManager securityManager = new DefaultSecurityManager();
		MyRealm realm = new MyRealm();
		MyRealm2 realm2 = new MyRealm2();
		securityManager.setRealms(Arrays.asList(realm,realm2));
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject();
		
		// 认证
		UsernamePasswordToken token = new UsernamePasswordToken("foo", "123");
		try {
			subject.login(token);
		} catch (Exception e) {
			System.out.println("认证失败：" + e);
			return;
		}
		
		System.out.println("认证成功：" + subject.getPrincipal());
		
		
		// 授权
		System.out.println("admin角色:" + subject.hasRole("admin"));
		System.out.println("guest角色:" + subject.hasRole("guest"));
		
		subject.logout();
		
	}
}
