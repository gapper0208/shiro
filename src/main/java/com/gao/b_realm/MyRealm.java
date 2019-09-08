package com.gao.b_realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
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
		// 用户传来的账户密码
		String username = token.getPrincipal().toString();
		String password = new String((char[])token.getCredentials());

		// 与持久层的账户密码进行匹配
		if(!("foo".equals(username) || "bar".equals(username))) {
			throw new UnknownAccountException();
		}
		if("foo".equals(username)) {
			if(!"123".equals(password)) {
				throw new IncorrectCredentialsException();
			}
		}
		if("bar".equals(username)) {
			if(!"123".equals(password)) {
				throw new IncorrectCredentialsException();
			}
		}
		return new SimpleAuthenticationInfo(username,password,"myRealm");
	}

}
