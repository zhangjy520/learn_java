title: mybatis
author: zjy
date: 2017-12-13 11:51:13
tags:
---
# mybatis技术原理读后总结：

一级二级缓存：
只开一级：1，2只第一次查数据库，3再查数据库
开二级：123，只第一次查数据库。
SqlSession session1 = SqlSessionFactoryUtil.openSqlSession();
StudentMapper studentMapper = session1.getMapper(StudentMapper.class);

studentMapper.getStudent(1);//1
studentMapper.getStudent(1);//2
session1.commit();

session2 = SqlSessionFactoryUtil.openSqlSession();

StudentMapper studentMapper2 = session2.getMapper(StudentMapper.class);
studentMapper2.getStudent(1);//3

session2.commit();

//系统默认开启一级缓存，二级缓存开启在mybatis配置文件加 
<cache />
可自定义缓存，实现org.apache.ibatis.cache.Cache
<cache type = "com.your.SelfCache"/>


sqlSessionFactoryBuilder: 利用xml，java代码创建sqlsessionfactory.构建了sqlsessionfactory,可以销毁builder	
代码：new sqlSessionFactoryBuilder().build(inputStream);//mybatis.confg xml配置文件

sqlSessionFactory:同一个数据库对应一个，使用单例模式创建,生命周期贯穿mybatis整个生命周期，他创建sqlSession。

sqlsession：一个会话，相当于一个connection对象。生命周期=请求数据库处理的事务周期

mapper:mapper接口+xml。方法级别，生命周期在一个sqlsession内

简略代码：(具体各个对象单例等自行添加)
InputStream =Resourse.getResourceAsStream("mybatis-config.xml");
sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
sqlSessionFactory.openSqlSession();



动态代理：
jdk缺点：需要提供接口才能使用
cglib： CGLib创建的动态代理对象性能比JDK创建的动态代理对象的性能高不少，但是CGLib在创建代理对象时所花费的时间却比JDK多得多，所以对于单例的对象，因为无需频繁创建对象，用CGLib合适，反之，使用JDK方式要更为合适一些。同时，由于CGLib由于是采用动态创建子类的方法，对于final方法，无法进行代理。



sqlsession运行过程：
run.png


sqlSession 下四大对象:（插件拦截器的type下面四种）
1 Executor:执行器，由它调度StatementHandler,ParameterHandler,ResutlHandler 执行对应sql  
	java和数据库交互，有三类：
		SimpleExecutor:简易执行器，默认
		ReuseExecutor:重用预处理语句执行器
		BatchExecutor:批量专用执行器
2 StatementHandler数据库会话器 : 使用Statement(PreparedStatement)执行操作，四大对象核心，承上启下
3 ParameterHandler参数处理器：用于sql对参数的处理
4 ResutlHandler结果处理器：数据集封装返回处理。
 
 
	



mybatis插件：

所有插件需要实现接口 org.apache.ibatis.plugin.Interceptor

方法解释：
	1 intercept：覆盖拦截对象原有方法，通过Invocation可以调原来对象的方法
				invocation.proceed();//这句话是调用原来对象的方法
	2 plugin:target 是被拦截的对象，作用是给被拦截的对象生成代理对象，如下代码，只会为Executor对象生成代理对象，其他的都不生成代理对象，省空间省内存。	
			/**
			 * 只拦截Executor
			 *
			 * @param target
			 * @return
			 */
			public Object plugin(Object target) {
				if (target instanceof Executor) {
					return Plugin.wrap(target, this);
				} else {
					return target;
				}
			}
	3 setProperties:主要是为插件设置值，如下：
	  Properties.getProperty("dialect");//得到mysql
	  <plugins> 
        <plugin interceptor="com.github.pagehelper.PageHelper"> 
            <property name="dialect" value="mysql"/> 
            <property name="rowBoundsWithCount" value="true"/>
		</plugin>
	  </plugins> 
	 
	 
	 
	 
设计模式：
	
	单例模式 sqlSessionFactory 对应一个数据库连接，生命周期就是mybatis运行周期
	责任链模式：mybatis插件，一个对象，在多个角色传递，在传递链上的任何角色都有处理它的机会。简单来讲：我发起请假请求，项目经理，部门经理，总裁，他们都能对我的请假请求发起处理。这就是责任链。
	mybatis插件也是，sql运行过程中，每个插件都有处理它的权利
	
	
	代理模式：mapper接口代理，使用的是jdk代理
	
	
	

