该包演示jdbcrealm的使用

1. 依赖
	<dependency>
	    <groupId>mysql</groupId>
	    <artifactId>mysql-connector-java</artifactId>
	    <version>5.1.35</version>
	</dependency>
	<dependency>
		<groupId>com.alibaba</groupId>
		<artifactId>druid</artifactId>
		<version>1.1.16</version>
	</dependency>

2. 该例子还需要在mysql dbms下创建一个数据库，库中需要权限管理的5张表
	permissions
	roles
	users
	users_roles
	roles_permissions
	
	sql脚本如下：
	DROP DATABASE IF EXISTS shiro;
	
	CREATE DATABASE shiro;
	
	USE shiro;
	
	CREATE TABLE permissions
	(
	 pid INT PRIMARY KEY AUTO_INCREMENT,
	 pname VARCHAR(20)
	);
	
	CREATE TABLE roles
	(
	 rid INT PRIMARY KEY AUTO_INCREMENT,
	 rname VARCHAR(20)
	);
	
	CREATE TABLE users
	(
	 uid INT PRIMARY KEY AUTO_INCREMENT,
	 username VARCHAR(20),
	 PASSWORD VARCHAR(40)
	);
	
	CREATE TABLE roles_permissions
	(
	 rid INT,
	 pid INT,
	 PRIMARY KEY(rid, pid)
	);
	
	CREATE TABLE users_roles
	(
	 uid INT,
	 rid INT,
	 PRIMARY KEY(uid,rid)
	);
	
	ALTER TABLE users
	ADD CONSTRAINT UQ UNIQUE(username);
	
	ALTER TABLE roles_permissions
	ADD CONSTRAINT FK FOREIGN KEY(rid)
	REFERENCES roles(rid);
	
	ALTER TABLE roles_permissions
	ADD CONSTRAINT FK2 FOREIGN KEY(pid)
	REFERENCES permissions(pid);
	
	ALTER TABLE users_roles
	ADD CONSTRAINT FK3 FOREIGN KEY(uid)
	REFERENCES users(uid);
	
	ALTER TABLE users_roles
	ADD CONSTRAINT FK4 FOREIGN KEY(rid)
	REFERENCES roles(rid);
	
3.  测试数据：
	INSERT INTO permissions VALUES(NULL,'user:save');
	INSERT INTO permissions VALUES(NULL,'user:delete');
	INSERT INTO permissions VALUES(NULL,'user:update');
	INSERT INTO permissions VALUES(NULL,'user:find');
	
	INSERT INTO roles VALUES(NULL, 'admin');
	INSERT INTO roles VALUES(NULL, 'guest');
	
	INSERT INTO users VALUES(NULL, 'foo','123');
	INSERT INTO users VALUES(NULL, 'bar','123');
	
	INSERT INTO roles_permissions VALUES(1,1);
	INSERT INTO roles_permissions VALUES(1,2);
	INSERT INTO roles_permissions VALUES(1,3);
	INSERT INTO roles_permissions VALUES(1,4);
	
	INSERT INTO roles_permissions VALUES(2,1);
	INSERT INTO roles_permissions VALUES(2,4);
	
	INSERT INTO users_roles VALUES(1, 1);
	INSERT INTO users_roles VALUES(2, 2);
	
	
	SELECT * FROM permissions;
	SELECT * FROM roles;
	SELECT * FROM users;
	SELECT * FROM users_roles;
	SELECT * FROM roles_permissions;
	
4. 测试
	