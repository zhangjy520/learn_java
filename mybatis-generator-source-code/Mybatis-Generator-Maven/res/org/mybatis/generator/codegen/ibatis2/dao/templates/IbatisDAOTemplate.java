// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IbatisDAOTemplate.java

package org.mybatis.generator.codegen.ibatis2.dao.templates;

import org.mybatis.generator.api.dom.java.*;

// Referenced classes of package org.mybatis.generator.codegen.ibatis2.dao.templates:
//            AbstractDAOTemplate

public class IbatisDAOTemplate extends AbstractDAOTemplate
{

    public IbatisDAOTemplate()
    {
        fqjt = new FullyQualifiedJavaType("com.ibatis.dao.client.DaoManager");
    }

    protected void configureConstructorTemplate()
    {
        Method method = new Method();
        method.setConstructor(true);
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addParameter(new Parameter(fqjt, "daoManager"));
        method.addBodyLine("super(daoManager);");
        setConstructorTemplate(method);
    }

    protected void configureDeleteMethodTemplate()
    {
        setDeleteMethodTemplate("delete(\"{0}.{1}\", {2});");
    }

    protected void configureImplementationImports()
    {
        addImplementationImport(fqjt);
    }

    protected void configureInsertMethodTemplate()
    {
        setInsertMethodTemplate("insert(\"{0}.{1}\", {2});");
    }

    protected void configureQueryForListMethodTemplate()
    {
        setQueryForListMethodTemplate("queryForList(\"{0}.{1}\", {2});");
    }

    protected void configureQueryForObjectMethodTemplate()
    {
        setQueryForObjectMethodTemplate("queryForObject(\"{0}.{1}\", {2});");
    }

    protected void configureSuperClass()
    {
        setSuperClass(new FullyQualifiedJavaType("com.ibatis.dao.client.template.SqlMapDaoTemplate"));
    }

    protected void configureUpdateMethodTemplate()
    {
        setUpdateMethodTemplate("update(\"{0}.{1}\", {2});");
    }

    private FullyQualifiedJavaType fqjt;
}
