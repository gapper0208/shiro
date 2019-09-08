该包讲解自定义授权



shiro授权流程
	一、如果调用hasRole("admin")
		a.shiro则直接通过AuthorizationInfo的getRoles()方法获取用户已有的所有角色
		b.再与为hasRole方法传入的参数角色进行匹配
		
	二、如果调用isPermitted("user:find")
		a.shiro则先将isPermitted方法中的参数字符串权限解析为权限对象（这一步骤由PermissionResolver接口的实现类完成）
		b.再在realm的doGetAuthorizationInfo方法中根据用户身份，返回包含用户的角色（或许也有权限）信息的AuthorizationInfo的对象（这一步骤由自定义的MyRealm完成）
		c.在之后的某个时机，再从AuthorizationInfo对象的getRoles()方法获取用户的所有角色（这一步由shiro本身来做，我们不用管）
		d.然后根据用户的所有角色，通过RolePermissionResolver解析出每一个角色对应的权限集合（这一步由自定义的MyRolePermissionResolver完成）
		e.最后拿着传入的权限在所有权限集合中进行匹配（匹配的功能由Permission的implies方法完成）

	 注意，验证该包的例子时，要仔细阅读日志信息!!!
	 注意，验证该包的例子时，要仔细阅读日志信息!!!
	 注意，验证该包的例子时，要仔细阅读日志信息!!!
	 
	 注意，不能把我们自定义的权限字符串如"user+1"配置到INI配置文件，因为shiro默认使用的realm为IniRealm，而该IniRealm默认使用的是WildcardPermissionResovler
	 除非我们使用自定义Realm的方式把IniRealm注入给securityManager的方式，这样就可以将我们自定义的permissionResovler注入给securityManager(待测试)