// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DAOMethodNameCalculator.java

package org.mybatis.generator.api;


// Referenced classes of package org.mybatis.generator.api:
//            IntrospectedTable

public interface DAOMethodNameCalculator
{

    public abstract String getInsertMethodName(IntrospectedTable introspectedtable);

    public abstract String getInsertSelectiveMethodName(IntrospectedTable introspectedtable);

    public abstract String getUpdateByPrimaryKeyWithoutBLOBsMethodName(IntrospectedTable introspectedtable);

    public abstract String getUpdateByPrimaryKeyWithBLOBsMethodName(IntrospectedTable introspectedtable);

    public abstract String getUpdateByPrimaryKeySelectiveMethodName(IntrospectedTable introspectedtable);

    public abstract String getSelectByPrimaryKeyMethodName(IntrospectedTable introspectedtable);

    public abstract String getSelectByExampleWithoutBLOBsMethodName(IntrospectedTable introspectedtable);

    public abstract String getSelectByExampleWithBLOBsMethodName(IntrospectedTable introspectedtable);

    public abstract String getDeleteByPrimaryKeyMethodName(IntrospectedTable introspectedtable);

    public abstract String getDeleteByExampleMethodName(IntrospectedTable introspectedtable);

    public abstract String getCountByExampleMethodName(IntrospectedTable introspectedtable);

    public abstract String getUpdateByExampleSelectiveMethodName(IntrospectedTable introspectedtable);

    public abstract String getUpdateByExampleWithBLOBsMethodName(IntrospectedTable introspectedtable);

    public abstract String getUpdateByExampleWithoutBLOBsMethodName(IntrospectedTable introspectedtable);
}
