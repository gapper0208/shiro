该包讲解授权

1. 授权中必须知道的4个概念
	a.主体：访问应用的用户
	b.资源：在应用中用户可以访问的任何东西，比如jsp页面、查看\编辑数据、访问某个业务方法、打印文本等等
	c.权限：在某个资源上的某个操作，比如对用户的增加、对用户的修改都是权限
	d.角色：权限的集合

2. RBCA role-based access control	基于角色的访问控制(隐式角色)
       	即直接通过角色来验证用户有没有操作权限，如在应用中CTO、技术总监、开发工程师可以使用打印机，
       	假设某天不允许开发工程师使用打印机，此时需要从应用中删除相应代码；再如在应用中CTO、技术总监可以查看用户、
       	查看权限；突然有一天不允许技术总监查看用户、查看权限了，需要在相关代码中把技术总监角色从判断逻辑中删除掉；
       	即粒度是以角色为单位进行访问控制的，粒度较粗；如果进行修改可能造成多处代码修改。
   RBCA resource-based access control	基于资源的访问控制(显示角色)
   	在程序中通过权限控制谁能访问某个资源，角色聚合一组权限集合；这样假设哪个角色不能访问某个资源，
       	只需要从角色代表的权限集合中移除即可；无须修改多处代码；即粒度是以资源/实例为单位的；粒度较细。
 
3. 授权方式
	a.编程式
		Subject subject = SecurityManager.getSubject();
		if(subject.hasRole("admin")) {
			// 有权限
		} else {
			// 无权限
		}
	b.注解式
		@RequiresRoles("admin")
		public void f1() {
		}
	c.JSP标签
		<shiro:hasRole name="admin">
			<!-- 有权限 -->
		</shiro>
		
4. 基于角色的访问控制api
	hasRole
	hasAllRoles
	checkRole
	checkAllRoles
	
5. 基于资源的访问控制api
	isPermitted
	isPermittedAll
	checkPermission
	checkPermissions
	
6. 字符串通配符权限
	资源标识符:操作:对象实例ID， 表示对哪个资源的哪个实例进行什么操作
	eg.	user:delete:5
		user:*
	其中,*表示任意资源/操作/实例
	
7.	单个资源单个权限	
	user:save
	单个资源多个权限
	user:save,delete
	单个资源全部权限
	user:save,delete,update,find	或者写成	user:*
	所有资源单个权限
	*:save
	所有资源多个权限
	*:save,delete
	所有资源所有权限
	*:*
	单个实例单个权限
	user:save:1
	单个实例多个权限
	user:save:1,user:delete:1
	单个实例所有权限
	book:*:1

8.	shiro对权限字符串缺失部分的处理
	user:find	等价于	user:find:*	
	user		等价于	user:*		等价于	user:*:*

