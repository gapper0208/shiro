�ð������Զ�����Ȩ



shiro��Ȩ����
	һ���������hasRole("admin")
		a.shiro��ֱ��ͨ��AuthorizationInfo��getRoles()������ȡ�û����е����н�ɫ
		b.����ΪhasRole��������Ĳ�����ɫ����ƥ��
		
	�����������isPermitted("user:find")
		a.shiro���Ƚ�isPermitted�����еĲ����ַ���Ȩ�޽���ΪȨ�޶�����һ������PermissionResolver�ӿڵ�ʵ������ɣ�
		b.����realm��doGetAuthorizationInfo�����и����û���ݣ����ذ����û��Ľ�ɫ������Ҳ��Ȩ�ޣ���Ϣ��AuthorizationInfo�Ķ�����һ�������Զ����MyRealm��ɣ�
		c.��֮���ĳ��ʱ�����ٴ�AuthorizationInfo�����getRoles()������ȡ�û������н�ɫ����һ����shiro�������������ǲ��ùܣ�
		d.Ȼ������û������н�ɫ��ͨ��RolePermissionResolver������ÿһ����ɫ��Ӧ��Ȩ�޼��ϣ���һ�����Զ����MyRolePermissionResolver��ɣ�
		e.������Ŵ����Ȩ��������Ȩ�޼����н���ƥ�䣨ƥ��Ĺ�����Permission��implies������ɣ�

	 ע�⣬��֤�ð�������ʱ��Ҫ��ϸ�Ķ���־��Ϣ!!!
	 ע�⣬��֤�ð�������ʱ��Ҫ��ϸ�Ķ���־��Ϣ!!!
	 ע�⣬��֤�ð�������ʱ��Ҫ��ϸ�Ķ���־��Ϣ!!!
	 
	 ע�⣬���ܰ������Զ����Ȩ���ַ�����"user+1"���õ�INI�����ļ�����ΪshiroĬ��ʹ�õ�realmΪIniRealm������IniRealmĬ��ʹ�õ���WildcardPermissionResovler
	 ��������ʹ���Զ���Realm�ķ�ʽ��IniRealmע���securityManager�ķ�ʽ�������Ϳ��Խ������Զ����permissionResovlerע���securityManager(������)