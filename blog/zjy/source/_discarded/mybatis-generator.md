title: mybatis_generator
author: zjy
date: 2017-12-13 11:54:12
tags:
---
# mybatis_generator使用

pom.xml
     
   	    （默认本地jar包路径C:\Users\Administrator\.m2\repository\org\mybatis\generator）
    用附件jar替换 C:\Users\Administrator\.m2\repository\org\mybatis\generator\mybatis-generator-core\1.3.2
    这个路径下的 mybatis-generator-core-1.3.2.jar
    修改后的jar包下载地址：
    
        <plugin>
                <groupId>org.mybatis.generator</groupId>
                <artifactId>mybatis-generator-maven-plugin</artifactId>
                <version>1.3.2</version>
                <configuration>
                    <configurationFile>src/main/resources/mybatis-generator.xml</configurationFile>
                    <verbose>true</verbose>
                    <overwrite>true</overwrite>
                </configuration>
            </plugin>
mybatis-generator.xml
 	

    <generatorConfiguration>
        <!-- 配置文件路径 -->
        <properties resource="db.properties"/>

        <!-- 数据库驱动包路径 -->
        <classPathEntry location="${drive.class.path}"/>

        <context id="MySQLTables" targetRuntime="MyBatis3">
			
				<!-- xml合并，true合并，false 覆盖，javaMergeable同理 -->
            <property name="xmlMergeable" value="true" />
            <property name="javaMergeable" value="true" />
            <!-- 抑制警告 -->
            <property name="suppressTypeWarnings" value="true"/>

            <!-- generate entity时，生成hashcode和equals方法 -->
            <plugin type="org.mybatis.generator.plugins.EqualsHashCodePlugin"/>
            <!-- generate entity时，生成serialVersionUID -->
            <plugin type="org.mybatis.generator.plugins.SerializablePlugin"/>
            <!-- 这个插件只会增加字符串字段映射到一个JDBC字符的方法 -->
            <plugin type="org.mybatis.generator.plugins.CaseInsensitiveLikePlugin"/>
            <!-- genenat entity时,生成toString -->
            <plugin type="org.mybatis.generator.plugins.ToStringPlugin"/>

            <!-- 抑制生成代码的注释 -->
            <commentGenerator>
                <property name="suppressAllComments" value="true"/>
            </commentGenerator>


            <!-- 数据库连接信息 -->
            <jdbcConnection driverClass="${jdbc.driver}" connectionURL="${jdbc.url}" userId="${jdbc.username}"
                            password="${jdbc.password}">
            </jdbcConnection>

            <!-- 生成的model 包路径 -->
            <javaModelGenerator targetPackage="${model.package}" targetProject="${target.project}">
                <property name="enableSubPackages" value="ture"/>
                <property name="trimStrings" value="true"/>
            </javaModelGenerator>

            <!-- 生成xml mapper文件 路径 -->
            <sqlMapGenerator targetPackage="${xml.mapper.package}" targetProject="${target.project}">
                <property name="enableSubPackages" value="ture"/>
            </sqlMapGenerator>

            <!-- 生成的Dao接口 的包路径 -->
            <javaClientGenerator type="XMLMAPPER" targetPackage="${dao.package}" targetProject="${target.project}">
                <property name="enableSubPackages" value="ture"/>
            </javaClientGenerator>

            <!-- entity mapping relation -->
            <!-- 用户表[sys_user 用户] -->
            <table schema="cheea" tableName="t_course" domainObjectName="Course"
                   enableCountByExample="false"
                   enableUpdateByExample="true"
                   enableDeleteByExample="true"
                   enableSelectByExample="true"
                   selectByExampleQueryId="true">

                <property name="useActualColumnNames" value="false"/>

                <!--<columnOverride column="login_name" property="account" javaType="String"/>-->

            </table>


        </context>
    </generatorConfiguration>
    
 ide--->view--tool windows---maven projects----plugins---mybatis generator 双击生成


# mybatis-generator 优化
#### 需求：生成xml 会追加，例如：updateByPrimaryKey这样的xml节点会追加，这样xml就无法正常运行。

## 优化目标：再次生成的同名的方法，覆盖之前的方法，之前新增的方法继续保留。

## org.mybatis.generator.api.MyBatisGenerator的 generate方法是生成的入口。

生成xml的部分：

	 File directory = shellCallback.getDirectory(gxf
                        .getTargetProject(), gxf.getTargetPackage());
                targetFile = new File(directory, gxf.getFileName());
                if (targetFile.exists()) {
                    if (gxf.isMergeable()) {
                        source = XmlFileMergerJaxp.getMergedSource(gxf,
                                targetFile);
                    } else if (shellCallback.isOverwriteEnabled()) {
                        source = gxf.getFormattedContent();
                        warnings.add(getString("Warning.11", //$NON-NLS-1$
                                targetFile.getAbsolutePath()));
                    } else {
                        source = gxf.getFormattedContent();
                        targetFile = getUniqueFileName(directory, gxf
                                .getFileName());
                        warnings.add(getString(
                                "Warning.2", targetFile.getAbsolutePath())); //$NON-NLS-1$
                    }
                } else {
                    source = gxf.getFormattedContent();
                }


生成 java代码的部分：（这是修改后的）

                File directory = shellCallback.getDirectory(gjf
                        .getTargetProject(), gjf.getTargetPackage());
                targetFile = new File(directory, gjf.getFileName());
                if (targetFile.exists()) {
                    if (gjf.isMergeable()) {
                        source = shellCallback.mergeJavaFile(gjf
                                .getFormattedContent(), targetFile
                                .getAbsolutePath(),
                                MergeConstants.OLD_ELEMENT_TAGS,
                                gjf.getFileEncoding());
                    } else if (shellCallback.isOverwriteEnabled()) {
                        source = gjf.getFormattedContent();
                        warnings.add(getString("Warning.11", //$NON-NLS-1$
                                targetFile.getAbsolutePath()));
                    } else {
                        source = gjf.getFormattedContent();
                        targetFile = getUniqueFileName(directory, gjf
                                .getFileName());
                        warnings.add(getString(
                                "Warning.2", targetFile.getAbsolutePath())); //$NON-NLS-1$
                    }


流程：这里判断文件是否存在，文件是否合并或者覆盖。

实体类GeneratedJavaFile，GeneratedXMLFile里加：isMergeable字段判断文件是否合并。

这个字段赋值在generator.xml   context节点里面加入是否合并的初始化值。

如果合并true，走合并方法。否则覆盖override

官方的源码，xml合并虽然支持，但是，合并是直接追加。每次生成，新文件+旧文件导致很多像updateByPrimaryKey 这样的方法出现两次，不人工删除启动就会报错。

 