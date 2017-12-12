// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SpringDAOTemplate.java

package org.mybatis.generator.codegen.ibatis2.dao.templates;

import org.mybatis.generator.api.dom.java.*;

// Referenced classes of package org.mybatis.generator.codegen.ibatis2.dao.templates:
//            AbstractDAOTemplate

public class SpringDAOTemplate extends AbstractDAOTemplate
{

    public SpringDAOTemplate()
    {
    }

    protected void configureConstructorTemplate()
    {
        Method method = new Method();
        method.setConstructor(true);
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addBodyLine("super();");
        setConstructorTemplate(method);
    }

    protected void configureDeleteMethodTemplate()
    {
        setDeleteMethodTemplate("getSqlMapClientTemplate().delete(\"{0}.{1}\", {2});");
    }

    protected void configureInsertMethodTemplate()
    {
        setInsertMethodTemplate("getSqlMapClientTemplate().insert(\"{0}.{1}\", {2});");
    }

    protected void configureQueryForListMethodTemplate()
    {
        setQueryForListMethodTemplate("getSqlMapClientTemplate().queryForList(\"{0}.{1}\", {2});");
    }

    protected void configureQueryForObjectMethodTemplate()
    {
        setQueryForObjectMethodTemplate("getSqlMapClientTemplate().queryForObject(\"{0}.{1}\", {2});");
    }

    protected void configureSuperClass()
    {
        setSuperClass(new FullyQualifiedJavaType("org.springframework.orm.ibatis.support.SqlMapClientDaoSupport"));
    }

    protected void configureUpdateMethodTemplate()
    {
        setUpdateMethodTemplate("getSqlMapClientTemplate().update(\"{0}.{1}\", {2});");
    }
}
