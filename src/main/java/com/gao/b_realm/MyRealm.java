package com.gao.b_realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String principal = (String) principals.getPrimaryPrincipal();
		
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		
		if("foo".equals(principal)) {
			authorizationInfo.addRole("admin");
		}
		if("bar".equals(principal)) {
			authorizationInfo.addRole("guest");
		}
		
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// �û��������˻�
		String username = token.getPrincipal().toString();

		// realmֻ�����ж�ָ�����˻��Ƿ����
		if(!("foo".equals(username) || "bar".equals(username))) {
			throw new UnknownAccountException();
		}
		
		
		// ����ȶ����ϲ��ModularRealmAuthenticator����ɣ������������صģ�
		return new SimpleAuthenticationInfo(username,"123","myRealm");
	}

}