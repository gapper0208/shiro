package com.gao.h_authorizing;

import java.util.logging.Logger;

import org.apache.shiro.authz.Permission;

import com.alibaba.druid.util.StringUtils;

/*
 BitPermissionʹ��λ����ʾ��ͬ�Ĳ���
 0000	*
 0001	save
 0010	delete
 0100	update
 1000	find
 
 0011	save delete
 0111	save delete update
 1111	save delete update find
 1010	delete find
 
 Ȩ���ַ�������:	��Դ�ַ���+Ȩ������+ʵ��ID
*/
public class BitPermission implements Permission {
	private Logger log = Logger.getLogger(BitPermission.class.getName());
	// ��Դ
	private String resourceIdentity;
	// ����
	private int permissionBit;
	// ʵ��
	private String instanceId;
	
	public BitPermission(String permissionString) {
		String[] array = permissionString.split("\\+");
		if(array.length > 0) {
			resourceIdentity = array[0];
		}
		if (StringUtils.isEmpty(resourceIdentity)) {
			resourceIdentity = "*";
		}
		if(array.length > 1) {
			permissionBit = Integer.valueOf(array[1]);
		}
		if(array.length > 2) {
			instanceId = array[2];
		}
		if(StringUtils.isEmpty(instanceId)) {
			instanceId = "*";
		}
	}
	
	@Override
	public boolean implies(Permission p) {
		if(!(p instanceof BitPermission)) {
			return false;
		}
		BitPermission other = (BitPermission) p;
		if(!("*".equals(this.resourceIdentity) || this.resourceIdentity.equals(other.resourceIdentity))) {
			log.info("��Դ����ͬ");
			return false;
		}
		if(!(this.permissionBit == 0 || (this.permissionBit == other.permissionBit))) {
			log.info("��������ͬ");
			return false;
		}
		if(!("*".equals(this.instanceId) || this.instanceId.equals(other.instanceId))) {
			log.info("ʵ������ͬ");
			return false;
		}
		return true;
	}

}
