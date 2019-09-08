package com.gao.f_authenticator;

import java.util.Collection;
import java.util.logging.Logger;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.pam.AbstractAuthenticationStrategy;
import org.apache.shiro.realm.Realm;


public class AtLeastTwoSuccessfulStrategy extends AbstractAuthenticationStrategy {

	private static Logger log = Logger.getLogger(AtLeastTwoSuccessfulStrategy.class.getName());
	
	@Override
	public AuthenticationInfo beforeAllAttempts(Collection<? extends Realm> realms, AuthenticationToken token)
			throws AuthenticationException {
		log.info("new SimpleAuthenticationInfo()");
		return new SimpleAuthenticationInfo();
	}

	@Override
	public AuthenticationInfo beforeAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo aggregate)
			throws AuthenticationException {
		log.info(aggregate == null ? "aggregate is null" : "aggregate is not null");
		return aggregate;
	}

	@Override
	public AuthenticationInfo afterAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo singleRealmInfo,
			AuthenticationInfo aggregateInfo, Throwable t) throws AuthenticationException {
		
		AuthenticationInfo info;
		// 如果当前的Realm认证失败了，这里的singleRealmInfo就是null
		if(singleRealmInfo == null) {
			info = aggregateInfo;
			log.info("singleRealmInfo is null, info = aggregateInfo");
		} else {
			if(aggregateInfo == null) {
				info = singleRealmInfo;
				log.info("singleRealmInfo is not null, but aggregateInfo is null, info = singleRealmInfo");
			} else {
				info = merge(aggregateInfo, singleRealmInfo);
				log.info("singleRealmInfo and aggregateInfo are not null, info = merge(aggregateInfo, singleRealmInfo)");
			}
		}
		
		return info;
	}

	@Override
	public AuthenticationInfo afterAllAttempts(AuthenticationToken token, AuthenticationInfo aggregate)
			throws AuthenticationException {
		if(aggregate == null || aggregate.getPrincipals().getRealmNames().size() < 2) {
			throw new AuthenticationException("至少2个");
		} else {
			log.info("共有" + aggregate.getPrincipals().getRealmNames().size() + "个Realm认证成功");
			return aggregate;
		}
	}
	
}