package com.gao.d_jdbcrealm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;

public class App {
	@Test
	public void test() throws Exception {
		
		DruidDataSource ds = new DruidDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql:///shiro");
		ds.setUsername("root");
		ds.setPassword("123");
		
		
		JdbcRealm jdbcRealm = new JdbcRealm();
		jdbcRealm.setDataSource(ds);
		
		// ��дSQL��ѯ
		// ��֤
		jdbcRealm.setAuthenticationQuery("SELECT password FROM users WHERE username=?");
		// �����û�����ѯ��ɫ
		jdbcRealm.setUserRolesQuery("SELECT rname FROM users_roles ur INNER JOIN users u ON ur.`uid` = u.`uid` INNER JOIN roles r ON ur.rid = r.rid AND username = ?");
		// ���ݽ�ɫ����ѯȨ��
		jdbcRealm.setPermissionsQuery("SELECT pname FROM roles_permissions rp INNER JOIN roles r ON rp.`rid` = r.`rid` INNER JOIN permissions p ON rp.`pid` = p.`pid` AND rname = ?");
		
		// ֧��Ȩ�޲�ѯ(�������ø�������޷��ж��û�ӵ�еĽ�ɫ�Ƿ�ӵ��ĳ��Ȩ��)
		jdbcRealm.setPermissionsLookupEnabled(true);
		
		DefaultSecurityManager securityManager = new DefaultSecurityManager();
		securityManager.setRealm(jdbcRealm);
		SecurityUtils.setSecurityManager(securityManager);
		
		// ��֤
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("foo", "123");
		
		try {
			subject.login(token);
		} catch (Exception e) {
			System.out.println("��֤ʧ�ܣ�" + e);
			return;
		}
		
		System.out.println("��֤�ɹ���" + subject.getPrincipal());
		
		// ��Ȩ
		System.out.println(subject.hasRole("admin"));
		System.out.println(subject.isPermitted("user:save"));
		System.out.println(subject.isPermitted("user:delete"));
		System.out.println(subject.isPermitted("user:update"));
		System.out.println(subject.isPermitted("user:find"));
		
		subject.logout();
		
		
	}
}
