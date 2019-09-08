package com.gao.e_authenticator;

import java.util.Arrays;
import java.util.Iterator;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AllSuccessfulStrategy;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
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
		// ��realms�µ�������֤����
		AllSuccessfulStrategy s1 = new AllSuccessfulStrategy();
		AtLeastOneSuccessfulStrategy s2 = new AtLeastOneSuccessfulStrategy();
		FirstSuccessfulStrategy s3 = new FirstSuccessfulStrategy();
		// ������֤����
		mra.setAuthenticationStrategy(s2);
		securityManager.setAuthenticator(mra);
		
		// ע�⣬��realm�����ã�������securityManager.setAuthenticator(mra);֮��
		securityManager.setRealms(Arrays.asList(new MyRealm(), new MyRealm2(), new MyRealm3()));
		
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("foo", "123");
		
		try {
			subject.login(token);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��֤ʧ�ܣ�" + e);
			return;
		}
		
		System.out.println("��֤�ɹ�");
		
		// �����ڲ�ͬ����֤�����£��񷵻ض��ٸ����
		PrincipalCollection principals = subject.getPrincipals();
		for (Object object : principals) {
			System.out.println("principal:" + object.toString());
		}
		subject.logout();
	}
}
