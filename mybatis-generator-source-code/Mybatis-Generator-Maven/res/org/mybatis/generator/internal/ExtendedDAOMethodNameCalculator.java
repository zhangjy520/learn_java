// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExtendedDAOMethodNameCalculator.java

package org.mybatis.generator.internal;

import org.mybatis.generator.api.*;
import org.mybatis.generator.internal.rules.Rules;

public class ExtendedDAOMethodNameCalculator
    implements DAOMethodNameCalculator
{

    public ExtendedDAOMethodNameCalculator()
    {
    }

    public String getInsertMethodName(IntrospectedTable introspectedTable)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("insert");
        sb.append(introspectedTable.getFullyQualifiedTable().getDomainObjectName());
        return sb.toString();
    }

    public String getUpdateByPrimaryKeyWithoutBLOBsMethodName(IntrospectedTable introspectedTable)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("update");
        sb.append(introspectedTable.getFullyQualifiedTable().getDomainObjectName());
        Rules rules = introspectedTable.getRules();
        if(!rules.generateUpdateByPrimaryKeyWithBLOBs())
            sb.append("ByPrimaryKey");
        else
        if(rules.generateRecordWithBLOBsClass())
            sb.append("ByPrimaryKey");
        else
            sb.append("ByPrimaryKeyWithoutBLOBs");
        return sb.toString();
    }

    public String getUpdateByPrimaryKeyWithBLOBsMethodName(IntrospectedTable introspectedTable)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("update");
        sb.append(introspectedTable.getFullyQualifiedTable().getDomainObjectName());
        Rules rules = introspectedTable.getRules();
        if(!rules.generateUpdateByPrimaryKeyWithoutBLOBs())
            sb.append("ByPrimaryKey");
        else
        if(rules.generateRecordWithBLOBsClass())
            sb.append("ByPrimaryKey");
        else
            sb.append("ByPrimaryKeyWithBLOBs");
        return sb.toString();
    }

    public String getDeleteByExampleMethodName(IntrospectedTable introspectedTable)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("delete");
        sb.append(introspectedTable.getFullyQualifiedTable().getDomainObjectName());
        sb.append("ByExample");
        return sb.toString();
    }

    public String getDeleteByPrimaryKeyMethodName(IntrospectedTable introspectedTable)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("delete");
        sb.append(introspectedTable.getFullyQualifiedTable().getDomainObjectName());
        sb.append("ByPrimaryKey");
        return sb.toString();
    }

    public String getSelectByExampleWithoutBLOBsMethodName(IntrospectedTable introspectedTable)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("select");
        sb.append(introspectedTable.getFullyQualifiedTable().getDomainObjectName());
        sb.append("ByExample");
        Rules rules = introspectedTable.getRules();
        if(rules.generateSelectByExampleWithBLOBs())
            sb.append("WithoutBLOBs");
        return sb.toString();
    }

    public String getSelectByExampleWithBLOBsMethodName(IntrospectedTable introspectedTable)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("select");
        sb.append(introspectedTable.getFullyQualifiedTable().getDomainObjectName());
        sb.append("ByExample");
        Rules rules = introspectedTable.getRules();
        if(rules.generateSelectByExampleWithoutBLOBs())
            sb.append("WithBLOBs");
        return sb.toString();
    }

    public String getSelectByPrimaryKeyMethodName(IntrospectedTable introspectedTable)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("select");
        sb.append(introspectedTable.getFullyQualifiedTable().getDomainObjectName());
        sb.append("ByPrimaryKey");
        return sb.toString();
    }

    public String getUpdateByPrimaryKeySelectiveMethodName(IntrospectedTable introspectedTable)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("update");
        sb.append(introspectedTable.getFullyQualifiedTable().getDomainObjectName());
        sb.append("ByPrimaryKeySelective");
        return sb.toString();
    }

    public String getCountByExampleMethodName(IntrospectedTable introspectedTable)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("count");
        sb.append(introspectedTable.getFullyQualifiedTable().getDomainObjectName());
        sb.append("ByExample");
        return sb.toString();
    }

    public String getUpdateByExampleSelectiveMethodName(IntrospectedTable introspectedTable)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("update");
        sb.append(introspectedTable.getFullyQualifiedTable().getDomainObjectName());
        sb.append("ByExampleSelective");
        return sb.toString();
    }

    public String getUpdateByExampleWithBLOBsMethodName(IntrospectedTable introspectedTable)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("update");
        sb.append(introspectedTable.getFullyQualifiedTable().getDomainObjectName());
        Rules rules = introspectedTable.getRules();
        if(!rules.generateUpdateByExampleWithoutBLOBs())
            sb.append("ByExample");
        else
        if(rules.generateRecordWithBLOBsClass())
            sb.append("ByExample");
        else
            sb.append("ByExampleWithBLOBs");
        return sb.toString();
    }

    public String getUpdateByExampleWithoutBLOBsMethodName(IntrospectedTable introspectedTable)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("update");
        sb.append(introspectedTable.getFullyQualifiedTable().getDomainObjectName());
        Rules rules = introspectedTable.getRules();
        if(!rules.generateUpdateByExampleWithBLOBs())
            sb.append("ByExample");
        else
        if(rules.generateRecordWithBLOBsClass())
            sb.append("ByExample");
        else
            sb.append("ByExampleWithoutBLOBs");
        return sb.toString();
    }

    public String getInsertSelectiveMethodName(IntrospectedTable introspectedTable)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("insert");
        sb.append(introspectedTable.getFullyQualifiedTable().getDomainObjectName());
        sb.append("Selective");
        return sb.toString();
    }
}
