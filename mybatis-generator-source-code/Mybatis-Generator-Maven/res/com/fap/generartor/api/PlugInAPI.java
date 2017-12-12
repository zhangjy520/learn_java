// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PlugInAPI.java

package com.fap.generartor.api;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.mybatis.generator.api.ShellRunner;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.config.*;
import org.mybatis.generator.config.xml.ConfigurationParser;

// Referenced classes of package com.fap.generartor.api:
//            MergeTemplete, NameRuler

public class PlugInAPI
{

    public PlugInAPI(String jdbcPath, String url, String user, String password, boolean together, String project, String packageAPIPath, 
            String packageXMLPath, String tableAPIPath, String tableModelPath, String tableXMLPath)
    {
        JDBCPATH = jdbcPath;
        URL = url;
        USER = user;
        PASSWORD = password;
        TOGETHER = together;
        PROJECT = project;
        SOURCE_PATH_PACKAGE_API = packageAPIPath;
        SOURCE_PATH_PACKAGE_XML = packageXMLPath;
        SOURCE_PATH_TABLE_API = tableAPIPath;
        SOURCE_PATH_TABLE_MODEL = tableModelPath;
        SOURCE_PATH_TABLE_XML = tableXMLPath;
    }

    public List getTableNames()
    {
        Connection connection;
        List list;
        connection = createConnection(URL, USER, PASSWORD);
        System.out.println("+++++++++++++++++++========");
        list = new ArrayList();
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        for(ResultSet rs = databaseMetaData.getTables(null, USER.toUpperCase(), null, null); rs.next();)
        {
            String tableName = rs.getString("table_name");
            if(!tableName.contains("BIN"))
                list.add(tableName);
        }

        return list;
        SQLException e;
        e;
        e.printStackTrace();
        return null;
    }

    public void genTableFilesAllByOne(List tables)
    {
        String tempConfig = "temp.xml";
        File tempConfigFile = new File(tempConfig);
        String configStr = MergeTemplete.getStringFromStream(com/fap/generartor/api/MergeTemplete.getClassLoader().getResourceAsStream(confileFile));
        try
        {
            FileOutputStream fos = new FileOutputStream(tempConfigFile);
            fos.write(configStr.getBytes());
            fos.close();
        }
        catch(FileNotFoundException e1)
        {
            e1.printStackTrace();
        }
        catch(IOException e1)
        {
            e1.printStackTrace();
        }
        List warnings = new ArrayList();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = new Configuration();
        try
        {
            config = cp.parseConfiguration(tempConfigFile);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        config.getClassPathEntries().clear();
        config.addClasspathEntry(JDBCPATH);
        Context context = config.getContext("DB2Tables");
        context.getJavaClientGeneratorConfiguration().setTargetPackage(SOURCE_PATH_TABLE_API.substring(SOURCE_PATH_TABLE_API.indexOf("com")).replace("/", "."));
        context.getJavaClientGeneratorConfiguration().setTargetProject(SOURCE_PATH_TABLE_API.substring(0, SOURCE_PATH_TABLE_API.indexOf("com")));
        context.getJavaModelGeneratorConfiguration().setTargetPackage(SOURCE_PATH_TABLE_MODEL.substring(SOURCE_PATH_TABLE_MODEL.indexOf("com")).replace("/", "."));
        context.getJavaModelGeneratorConfiguration().setTargetProject(SOURCE_PATH_TABLE_MODEL.substring(0, SOURCE_PATH_TABLE_MODEL.indexOf("com")));
        context.getSqlMapGeneratorConfiguration().setTargetPackage(SOURCE_PATH_TABLE_XML.substring(SOURCE_PATH_TABLE_XML.indexOf("com")).replace("/", "."));
        context.getSqlMapGeneratorConfiguration().setTargetProject(SOURCE_PATH_TABLE_XML.substring(0, SOURCE_PATH_TABLE_XML.indexOf("com")));
        context.getJdbcConnectionConfiguration().setConnectionURL(URL);
        context.getJdbcConnectionConfiguration().setUserId(USER.toUpperCase());
        context.getJdbcConnectionConfiguration().setPassword(PASSWORD);
        Connection connection = createConnection(URL, USER, PASSWORD);
        context.cleanTableConfig();
        context.setId("DB2Tables");
        try
        {
            ResultSet rs = connection.getMetaData().getTables(null, USER.toUpperCase(), null, null);
            System.out.println("<<<<generate tables name>>>");
            while(rs.next()) 
            {
                String tableName = rs.getString("table_name");
                if(!tableName.contains("BIN") && tables.contains(tableName))
                {
                    System.out.println(tableName);
                    TableConfiguration tc = new TableConfiguration(config.getContext("DB2Tables"));
                    tc.setTableName(tableName);
                    tc.setDomainObjectName(NameRuler.classNameRuler(tableName));
                    tc.setSchema(USER.toUpperCase());
                    tc.addProperty("ignoreQualifiersAtRuntime", "true");
                    ColumnOverride columnOverride;
                    for(ResultSet rsc = connection.getMetaData().getColumns(null, USER.toUpperCase(), tableName, null); rsc.next(); tc.addColumnOverride(columnOverride))
                    {
                        String columnName = rsc.getString("column_name");
                        columnOverride = new ColumnOverride(columnName);
                        columnOverride.setJavaProperty(NameRuler.columnNameRuler(columnName));
                    }

                    context.addTableConfiguration(tc);
                    System.out.println("=======================");
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        Document doc = config.toDocument();
        System.out.println(doc.getFormattedContent());
        try
        {
            FileOutputStream fos = new FileOutputStream(tempConfigFile);
            fos.write(doc.getFormattedContent().getBytes());
            fos.close();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("==========begin gen=============");
        try
        {
            ShellRunner.main(new String[] {
                "-configfile", tempConfig, "-overwrite"
            });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("==========end gen=============");
    }

    private static Connection createConnection(String dburl, String dbname, String dbpwd)
    {
        Connection connection = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(dburl, dbname, dbpwd);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return connection;
    }

    private String URL;
    private String USER;
    private String PASSWORD;
    private boolean TOGETHER;
    private String PROJECT;
    private String SOURCE_PATH_TABLE_MODEL;
    private String SOURCE_PATH_TABLE_API;
    private String SOURCE_PATH_TABLE_XML;
    private String SOURCE_PATH_PACKAGE_API;
    private String SOURCE_PATH_PACKAGE_XML;
    private String JDBCPATH;
    private static String confileFile = "mybatisConfig.xml";

}
