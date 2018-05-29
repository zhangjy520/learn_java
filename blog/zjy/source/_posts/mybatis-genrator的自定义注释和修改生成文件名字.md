title: mybatis-genrator的自定义注释和修改生成文件名字
author: zjy
date: 2018-05-29 13:11:40
tags:
---
# 需求：将数据库的comment字段生成到代码的注释里面


## 1 pom.xml添加如下
	
    				<dependency>
                        <groupId>org.mybatis.generator</groupId>
                        <artifactId>mybatis-generator-core</artifactId>
                        <version>1.3.4</version>
                    </dependency>
                    
## 2 mybatis-generator.xml

	<?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE generatorConfiguration
            PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
            "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

    <generatorConfiguration>
        <!-- 配置文件路径 -->
        <properties resource="db.properties"/>

        <!-- 数据库驱动包路径 -->
        <classPathEntry location="${drive.class.path}"/>

        <context id="MySQLTables" targetRuntime="MyBatis3">
            <!-- 抑制警告 -->
            <property name="suppressTypeWarnings" value="true" />

            <!-- generate entity时，生成hashcode和equals方法 -->
            <plugin type="org.mybatis.generator.plugins.EqualsHashCodePlugin" />
            <!-- generate entity时，生成serialVersionUID -->
            <plugin type="org.mybatis.generator.plugins.SerializablePlugin" />
            <!-- 这个插件只会增加字符串字段映射到一个JDBC字符的方法 -->
            <plugin type="org.mybatis.generator.plugins.CaseInsensitiveLikePlugin" />
            <!-- genenat entity时,生成toString -->
            <plugin type="org.mybatis.generator.plugins.ToStringPlugin" />

            <!-- 抑制生成代码的注释 -->
            <!--<commentGenerator>
                &lt;!&ndash;<property name="suppressAllComments" value="true" />&ndash;&gt;
            </commentGenerator>-->
            
            <!-- 这里采用mybatis自定义的注释生成器 -->
            <commentGenerator type="cc.ligu.mvc.common.MyCommentGenerator">
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

            <!-- 文档库[doc_source 用户] 注意这里的mapperName属性在 generator.1.3.4以后才有，是生成的Mapper文件的名称-->
            <table schema="ligu" tableName="setCallCenter" domainObjectName="CallCenter" mapperName="CallCenterDao"
                   enableCountByExample="false"
                   enableUpdateByExample="true"
                   enableDeleteByExample="true"
                   enableSelectByExample="true"
                   selectByExampleQueryId="true">
                <property name="useActualColumnNames" value="false"/>
            </table>

        </context>
    </generatorConfiguration>
    
    
    
## 3 db.properties
	#db config
    jdbc.script=filesystem:D:/codes/git/smartBoard/src/main/resources/db/migrations
    jdbc.driver=com.mysql.jdbc.Driver
    jdbc.url=jdbc:mysql://47.94.98.20:3308/ligu?useUnicode=true&characterEncoding=utf-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false
    jdbc.username=root
    jdbc.password=123

  
    # 数据库驱动jar 路径
    drive.class.path=D:\\zjywork\\mysql-connector-java-5.1.25.jar

    target.project=C:\\Users\\shenyy\\Desktop
    # 包路径配置
    model.package=cc.ligu.mvc.persistence.entity
    dao.package=cc.ligu.mvc.persistence.dao
    xml.mapper.package=cc.ligu.mvc.persistence.mapping
    
## 4 MyCommentGenerator
	package cc.ligu.mvc.common;
    import org.mybatis.generator.api.CommentGenerator;
    import org.mybatis.generator.api.IntrospectedColumn;
    import org.mybatis.generator.api.IntrospectedTable;
    import org.mybatis.generator.api.dom.java.*;
    import org.mybatis.generator.api.dom.xml.XmlElement;
    import org.mybatis.generator.config.MergeConstants;
    import org.mybatis.generator.config.PropertyRegistry;
    import java.text.SimpleDateFormat;
    import java.util.Date;
    import java.util.Properties;
    import static org.mybatis.generator.internal.util.StringUtility.isTrue;

    /**
     * mybatis generator 自定义comment生成器.
     * 基于MBG 1.3.2. 
     *
     */
    public class MyCommentGenerator implements CommentGenerator {

        private Properties properties;
        private Properties systemPro;
        private boolean suppressDate;
        private boolean suppressAllComments;
        private String currentDateStr;

        public MyCommentGenerator() {
            super();
            properties = new Properties();
            systemPro = System.getProperties();
            suppressDate = false;
            suppressAllComments = false;
            currentDateStr = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
        }

        public void addJavaFileComment(CompilationUnit compilationUnit) {
            // add no file level comments by default
            return;
        }

        /**
         * Adds a suitable comment to warn users that the element was generated, and
         * when it was generated.
         */
        public void addComment(XmlElement xmlElement) {
            return;
        }

        public void addRootComment(XmlElement rootElement) {
            // add no document level comments by default
            return;
        }

        public void addConfigurationProperties(Properties properties) {
            this.properties.putAll(properties);

            suppressDate = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));

            suppressAllComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));
        }

        /**
         * This method adds the custom javadoc tag for. You may do nothing if you do
         * not wish to include the Javadoc tag - however, if you do not include the
         * Javadoc tag then the Java merge capability of the eclipse plugin will
         * break.
         *
         * @param javaElement the java element
         */
        protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
            javaElement.addJavaDocLine(" *");
            StringBuilder sb = new StringBuilder();
            sb.append(" * ");
            sb.append(MergeConstants.NEW_ELEMENT_TAG);
            if (markAsDoNotDelete) {
                sb.append(" do_not_delete_during_merge");
            }
            String s = getDateString();
            if (s != null) {
                sb.append(' ');
                sb.append(s);
            }
            javaElement.addJavaDocLine(sb.toString());
        }

        /**
         * This method returns a formated date string to include in the Javadoc tag
         * and XML comments. You may return null if you do not want the date in
         * these documentation elements.
         *
         * @return a string representing the current timestamp, or null
         */
        protected String getDateString() {
            String result = null;
            if (!suppressDate) {
                result = currentDateStr;
            }
            return result;
        }

        public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
            if (suppressAllComments) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            innerClass.addJavaDocLine("/**");
            sb.append(" * ");
            sb.append(introspectedTable.getFullyQualifiedTable());
            sb.append(" ");
            sb.append(getDateString());
            innerClass.addJavaDocLine(sb.toString());
            innerClass.addJavaDocLine(" */");
        }

        public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
            if (suppressAllComments) {
                return;
            }

            StringBuilder sb = new StringBuilder();

            innerEnum.addJavaDocLine("/**");
            //      addJavadocTag(innerEnum, false);
            sb.append(" * ");
            sb.append(introspectedTable.getFullyQualifiedTable());
            innerEnum.addJavaDocLine(sb.toString());
            innerEnum.addJavaDocLine(" */");
        }

        public void addFieldComment(Field field, IntrospectedTable introspectedTable,
                                    IntrospectedColumn introspectedColumn) {
            if (suppressAllComments) {
                return;
            }

            StringBuilder sb = new StringBuilder();

            field.addJavaDocLine("/**");
            sb.append(" * ");
            //这里就是数据库的注释
            sb.append(introspectedColumn.getRemarks());
            field.addJavaDocLine(sb.toString());

            //      addJavadocTag(field, false);

            field.addJavaDocLine(" */");
        }

        public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
            if (suppressAllComments) {
                return;
            }

            StringBuilder sb = new StringBuilder();

            field.addJavaDocLine("/**");
            sb.append(" * ");
            sb.append(introspectedTable.getFullyQualifiedTable());
            field.addJavaDocLine(sb.toString());
            field.addJavaDocLine(" */");
        }

        @Override
        public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        }

        public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
            if (suppressAllComments) {
                return;
            }
            //      method.addJavaDocLine("/**");
            //      addJavadocTag(method, false);
            //      method.addJavaDocLine(" */");
        }

        public void addGetterComment(Method method, IntrospectedTable introspectedTable,
                                     IntrospectedColumn introspectedColumn) {
            if (suppressAllComments) {
                return;
            }

            method.addJavaDocLine("/**");

            StringBuilder sb = new StringBuilder();
            sb.append(" * 获取");
            sb.append(introspectedColumn.getRemarks());
            method.addJavaDocLine(sb.toString());

            sb.setLength(0);
            sb.append(" * @return ");
            sb.append(introspectedColumn.getActualColumnName());
            sb.append(" ");
            sb.append(introspectedColumn.getRemarks());
            method.addJavaDocLine(sb.toString());

            //      addJavadocTag(method, false);

            method.addJavaDocLine(" */");
        }

        public void addSetterComment(Method method, IntrospectedTable introspectedTable,
                                     IntrospectedColumn introspectedColumn) {
            if (suppressAllComments) {
                return;
            }


            method.addJavaDocLine("/**");
            StringBuilder sb = new StringBuilder();
            sb.append(" * 设置");
            sb.append(introspectedColumn.getRemarks());
            method.addJavaDocLine(sb.toString());

            Parameter parm = method.getParameters().get(0);
            sb.setLength(0);
            sb.append(" * @param ");
            sb.append(parm.getName());
            sb.append(" ");
            sb.append(introspectedColumn.getRemarks());
            method.addJavaDocLine(sb.toString());

            //      addJavadocTag(method, false);

            method.addJavaDocLine(" */");
        }

        public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
            if (suppressAllComments) {
                return;
            }

            StringBuilder sb = new StringBuilder();

            innerClass.addJavaDocLine("/**");
            sb.append(" * ");
            sb.append(introspectedTable.getFullyQualifiedTable());
            innerClass.addJavaDocLine(sb.toString());

            sb.setLength(0);
            sb.append(" * @author ");
            sb.append(systemPro.getProperty("user.name"));
            sb.append(" ");
            sb.append(currentDateStr);

            //      addJavadocTag(innerClass, markAsDoNotDelete);

            innerClass.addJavaDocLine(" */");
        }
    }

## 5 生成
	package cc.ligu.mvc.common;

    import org.mybatis.generator.api.MyBatisGenerator;
    import org.mybatis.generator.config.Configuration;
    import org.mybatis.generator.config.xml.ConfigurationParser;
    import org.mybatis.generator.exception.InvalidConfigurationException;
    import org.mybatis.generator.exception.XMLParserException;
    import org.mybatis.generator.internal.DefaultShellCallback;

    import java.io.File;
    import java.io.IOException;
    import java.io.InputStream;
    import java.net.URISyntaxException;
    import java.sql.SQLException;
    import java.util.ArrayList;
    import java.util.List;

    /**
     * 描述：使用Java方式运行MBG
     */
    public class GeneratorStartUp {
        public static void main(String[] args) throws URISyntaxException {
            try {
                List<String> warnings = new ArrayList<String>();
                boolean overwrite = true;
                File configFile = new File("D:\\zjywork\\ligu\\src\\main\\resources\\mybatis-generator.xml");
                ConfigurationParser cp = new ConfigurationParser(warnings);
                Configuration config = cp.parseConfiguration(configFile);
                DefaultShellCallback callback = new DefaultShellCallback(overwrite);
                MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
                myBatisGenerator.generate(null);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (InvalidConfigurationException e) {
                e.printStackTrace();
            } catch (XMLParserException e) {
                e.printStackTrace();
            }
        }
    }
