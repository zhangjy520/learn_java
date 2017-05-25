#gkPlatform


maven project, empty project template.
spring mvc
spring data jpa
mysql


���ݿ�����====
	<!-- Ĭ������ -->
    <!-- <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="${db.driver}"/>
        <property name="url" value="${db.url}"/>
        <property name="username" value="${db.className}"/>
        <property name="password" value="${db.password}"/>
    </bean> -->

    <!-- bonecp connection pool -->
    <bean id="dataSource" class="com.jolbox.bonecp.BoneCPDataSource" destroy-method="close">
   		<property name="driverClass" value="${db.className}" />
        <property name="jdbcUrl" value="${db.url}" />
        <property name="username" value="${db.username}" />
        <property name="password" value="${db.password}" />

        <!-- ������ݿ����ӳ��п������ӵļ��ʱ�䣬��λ�Ƿ֣�Ĭ��ֵ��240�����Ҫȡ��������Ϊ0 -->
        <property name="idleConnectionTestPeriodInSeconds" value="120" />
        <!-- ���ӳ���δʹ�õ����������ʱ�䣬��λ�Ƿ֣�Ĭ��ֵ��60�����Ҫ��Զ�������Ϊ0 -->
        <property name="idleMaxAgeInSeconds" value="60" />
        <!-- ÿ���������������� -->
        <property name="maxConnectionsPerPartition" value="10" />
        <!-- ÿ��������С�������� -->
        <property name="minConnectionsPerPartition" value="1" />
     	<!-- ������ ��Ĭ��ֵ2����С1���Ƽ�3-4����Ӧ�ö��� -->
        <property name="partitionCount" value="2" />
        <!-- ÿ��ȥ�����ݿ����ӵ�ʱ��һ����Ҫ�ü���,Ĭ��ֵ��2 -->
        <property name="acquireIncrement" value="1" />
       	<!-- ����prepared statements�Ĵ�С��Ĭ��ֵ��0 -->
        <property name="statementsCacheSize" value="20" />
    </bean>
    
    <!-- alibaba druid -->
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close"> 
	    <!-- �������� url��user��password -->
	    <property name="url">
	    	<value>jdbc:mysql://localhost:3306/emtpyWebTest?useUnicode=true&amp;characterEncoding=utf-8</value>
	    </property>
	    <property name="username">
	    	<value>root</value>
	    </property>
	    <property name="password">
	    	<value>Jose1123</value>
	    </property>
	
	    <!-- ���ó�ʼ����С����С����� -->
	    <property name="initialSize" value="1" />
	    <property name="minIdle" value="1" /> 
	    <property name="maxActive" value="20" />
	
	    <!-- ���û�ȡ���ӵȴ���ʱ��ʱ�� -->
	    <property name="maxWait" value="60000" />
	
	   <!--  ���ü����òŽ���һ�μ�⣬�����Ҫ�رյĿ������ӣ���λ�Ǻ��� -->
	    <property name="timeBetweenEvictionRunsMillis" value="60000" />
	
	    <!-- ����һ�������ڳ�����С�����ʱ�䣬��λ�Ǻ��� -->
	    <property name="minEvictableIdleTimeMillis" value="300000" />
	
	    <property name="validationQuery" value="SELECT 'x'" />
	    <property name="testWhileIdle" value="true" />
	    <property name="testOnBorrow" value="false" />
	    <property name="testOnReturn" value="false" />
	
	    <!-- ��PSCache������ָ��ÿ��������PSCache�Ĵ�С -->
	    <property name="poolPreparedStatements" value="true" />
	    <property name="maxPoolPreparedStatementPerConnectionSize" value="20" />
	
	    <!-- ���ü��ͳ�����ص�filters -->
	    <property name="filters" value="stat" />
	</bean>


