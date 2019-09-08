package com.gao.b_realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

// 自定义realm
public class App {
	@Test
	public void test() throws Exception {
		DefaultSecurityManager securityManager = new DefaultSecurityManager();
		MyRealm myRealm = new MyRealm();
		securityManager.setRealm(myRealm);
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("foo", "123");
		
		try {
			subject.login(token);
		} catch (Exception e) {
			System.out.println("认证失败:" + e);
			return;
		}
		
		System.out.println("认证成功");
		
		
		System.out.println(subject.hasRole("admin"));
		System.out.println(subject.hasRole("guest"));
		
		subject.logout();
	}
}
