// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GenericCIDAOTemplate.java

package org.mybatis.generator.codegen.ibatis2.dao.templates;

import org.mybatis.generator.api.dom.java.*;

// Referenced classes of package org.mybatis.generator.codegen.ibatis2.dao.templates:
//            AbstractDAOTemplate

public class GenericCIDAOTemplate extends AbstractDAOTemplate
{

    public GenericCIDAOTemplate()
    {
        sqlMapClientType = new FullyQualifiedJavaType("com.ibatis.sqlmap.client.SqlMapClient");
    }

    protected void configureCheckedExceptions()
    {
        addCheckedException(new FullyQualifiedJavaType("java.sql.SQLException"));
    }

    protected void configureConstructorTemplate()
    {
        Method constructor = new Method();
        constructor.setConstructor(true);
        constructor.setVisibility(JavaVisibility.PUBLIC);
        constructor.addParameter(new Parameter(sqlMapClientType, "sqlMapClient"));
        constructor.addBodyLine("super();");
        constructor.addBodyLine("this.sqlMapClient = sqlMapClient;");
        setConstructorTemplate(constructor);
    }

    protected void configureDeleteMethodTemplate()
    {
        setDeleteMethodTemplate("sqlMapClient.delete(\"{0}.{1}\", {2});");
    }

    protected void configureFields()
    {
        Field field = new Field();
        field.setVisibility(JavaVisibility.PRIVATE);
        field.setType(sqlMapClientType);
        field.setName("sqlMapClient");
        addField(field);
    }

    protected void configureImplementationImports()
    {
        addImplementationImport(sqlMapClientType);
    }

    protected void configureInsertMethodTemplate()
    {
        setInsertMethodTemplate("sqlMapClient.insert(\"{0}.{1}\", {2});");
    }

    protected void configureQueryForListMethodTemplate()
    {
        setQueryForListMethodTemplate("sqlMapClient.queryForList(\"{0}.{1}\", {2});");
    }

    protected void configureQueryForObjectMethodTemplate()
    {
        setQueryForObjectMethodTemplate("sqlMapClient.queryForObject(\"{0}.{1}\", {2});");
    }

    protected void configureUpdateMethodTemplate()
    {
        setUpdateMethodTemplate("sqlMapClient.update(\"{0}.{1}\", {2});");
    }

    private FullyQualifiedJavaType sqlMapClientType;
}
