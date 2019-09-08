package com.gao.a_hello;

import java.util.logging.Logger;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;


public class App {
	private static Logger log = Logger.getLogger(App.class.getName());
	
	@Test
	public void test() throws Exception {
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		IniRealm iniRealm = new IniRealm("classpath:com/gao/a_hello/shiro.ini");
		defaultSecurityManager.setRealm(iniRealm);
		
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		
		Subject subject = SecurityUtils.getSubject();
		
		// ��֤
		UsernamePasswordToken token = new UsernamePasswordToken("bar","123");
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			log.info("�˻�����");
			return;
		} catch (IncorrectCredentialsException e) {
			log.info("�������");
			return;
		} 
		log.info("��¼�ɹ�,��ǰ�û��ǣ�" + subject.getPrincipal());
		
		// ��Ȩ
		System.out.println("admin��ɫ:" + subject.hasRole("admin"));
		System.out.println("guest��ɫ:" + subject.hasRole("guest"));
		System.out.println("user:save:" + subject.isPermitted("user:save"));
		System.out.println("user:delete:" + subject.isPermitted("user:delete"));
		System.out.println("user:update:" + subject.isPermitted("user:update"));
		System.out.println("user:find:" + subject.isPermitted("user:find"));
		
		subject.checkRole("guest");
		subject.checkPermission("user:save");
		
		subject.logout();
	}
}
