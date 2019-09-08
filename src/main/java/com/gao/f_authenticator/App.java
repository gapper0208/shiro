package com.gao.f_authenticator;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class App {
	@Test
	public void test() throws Exception {
		DefaultSecurityManager securityManager = new DefaultSecurityManager();
		ModularRealmAuthenticator mra = new ModularRealmAuthenticator();
		// 使用自定义的认证策略
		mra.setAuthenticationStrategy(new AtLeastTwoSuccessfulStrategy());
		securityManager.setAuthenticator(mra);
		securityManager.setRealms(Arrays.asList(new MyRealm(),new MyRealm2(),new MyRealm3()));
		
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("foo","123");
		
		try {
			subject.login(token);
		} catch (Exception e) {
			System.out.println("认证失败：" + e);
			return;
		}
		
		System.out.println("认证成功");
		
		PrincipalCollection principals = subject.getPrincipals();
		for (Object object : principals) {
			System.out.println(object);
		}
		
		subject.logout();
		
		
	}
}
