package com.gao.h_authorizing;

import java.util.Set;
import java.util.logging.Logger;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm extends AuthorizingRealm {

	private static Logger log = Logger.getLogger(MyRealm.class.getName());
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		log.info("��Ȩ, " + principals);
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo() {
			@Override
			public Set<String> getRoles() {
				Set<String> roles = super.getRoles();
				log.info("��ȡ���н�ɫ:" + roles);
				return roles;
			}
		};
		
		log.info("��realm�У�ȷ���û��Ľ�ɫ: [admin, guest]");
		authorizationInfo.addRole("admin");
		authorizationInfo.addRole("guest");
		
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		String password = new String((char[])token.getCredentials());
		log.info("��֤, username:" + username + ", password:" + password);
		if(!"foo".equals(username)) {
			log.info("unknown accout");
			throw new UnknownAccountException();
		}
		return new SimpleAuthenticationInfo(username, "123", getName());
	}
	
	
	
}
