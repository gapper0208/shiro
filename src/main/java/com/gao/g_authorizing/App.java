package com.gao.g_authorizing;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class App {
	@Test
	public void test() throws Exception {
		DefaultSecurityManager securityManager = new DefaultSecurityManager();
		IniRealm iniRealm = new IniRealm("classpath:com/gao/g_authorizing/shiro.ini");
		securityManager.setRealm(iniRealm);
		
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken("gao", "123");
		try {
			subject.login(token);
		} catch (Exception e) {
			System.out.println("认证失败：" + e);
			return;
		}
		
		System.out.println("认证成功");
		
		// 基于角色的访问控制
//		System.out.println(subject.hasRole("role"));
//		System.out.println(subject.hasAllRoles(Arrays.asList("role","role2")));
//		subject.checkRole("role");
//		subject.checkRoles("role","role2");
		
		// 基于资源的访问控制
//		System.out.println(subject.isPermitted("user:save"));
//		System.out.println(subject.isPermittedAll("user:save", "user:find"));
//		subject.checkPermission("user:save");
//		subject.checkPermissions("user:save","user:delete");
		
//		System.out.println(subject.isPermitted("user:save"));
//		System.out.println(subject.isPermitted("user:delete"));
//		System.out.println(subject.isPermitted("user:update"));
//		System.out.println(subject.isPermitted("user:find"));
		
//		System.out.println(subject.isPermitted("book:save"));
//		System.out.println(subject.isPermitted("book:delete"));
//		System.out.println(subject.isPermitted("book:update"));
//		System.out.println(subject.isPermitted("book:find"));
		
		System.out.println(subject.isPermitted("user:save:1"));
		System.out.println(subject.isPermitted("user:delete:1"));
		System.out.println(subject.isPermitted("user:save:2"));
		System.out.println(subject.isPermitted("user:delete:2"));
		
		System.out.println(subject.isPermitted("book:save:1"));
		System.out.println(subject.isPermitted("book:delete:1"));
		System.out.println(subject.isPermitted("book:update:1"));
		System.out.println(subject.isPermitted("book:find:1"));
		System.out.println(subject.isPermitted("book:save:2"));
		System.out.println(subject.isPermitted("book:delete:2"));
		System.out.println(subject.isPermitted("book:update:2"));
		System.out.println(subject.isPermitted("book:find:2"));
		
		subject.logout();
	}
}
