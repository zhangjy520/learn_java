// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultDAOMethodNameCalculator.java

package org.mybatis.generator.internal;

import org.mybatis.generator.api.DAOMethodNameCalculator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.internal.rules.Rules;

public class DefaultDAOMethodNameCalculator
    implements DAOMethodNameCalculator
{

    public DefaultDAOMethodNameCalculator()
    {
    }

    public String getInsertMethodName(IntrospectedTable introspectedTable)
    {
        return "insert";
    }

    public String getUpdateByPrimaryKeyWithoutBLOBsMethodName(IntrospectedTable introspectedTable)
    {
        Rules rules = introspectedTable.getRules();
        if(!rules.generateUpdateByPrimaryKeyWithBLOBs())
            return "updateByPrimaryKey";
        if(rules.generateRecordWithBLOBsClass())
            return "updateByPrimaryKey";
        else
            return "updateByPrimaryKeyWithoutBLOBs";
    }

    public String getUpdateByPrimaryKeyWithBLOBsMethodName(IntrospectedTable introspectedTable)
    {
        Rules rules = introspectedTable.getRules();
        if(!rules.generateUpdateByPrimaryKeyWithoutBLOBs())
            return "updateByPrimaryKey";
        if(rules.generateRecordWithBLOBsClass())
            return "updateByPrimaryKey";
        else
            return "updateByPrimaryKeyWithBLOBs";
    }

    public String getDeleteByExampleMethodName(IntrospectedTable introspectedTable)
    {
        return "deleteByExample";
    }

    public String getDeleteByPrimaryKeyMethodName(IntrospectedTable introspectedTable)
    {
        return "deleteByPrimaryKey";
    }

    public String getSelectByExampleWithoutBLOBsMethodName(IntrospectedTable introspectedTable)
    {
        Rules rules = introspectedTable.getRules();
        if(!rules.generateSelectByExampleWithBLOBs())
            return "selectByExample";
        else
            return "selectByExampleWithoutBLOBs";
    }

    public String getSelectByExampleWithBLOBsMethodName(IntrospectedTable introspectedTable)
    {
        Rules rules = introspectedTable.getRules();
        if(!rules.generateSelectByExampleWithoutBLOBs())
            return "selectByExample";
        else
            return "selectByExampleWithBLOBs";
    }

    public String getSelectByPrimaryKeyMethodName(IntrospectedTable introspectedTable)
    {
        return "selectByPrimaryKey";
    }

    public String getUpdateByPrimaryKeySelectiveMethodName(IntrospectedTable introspectedTable)
    {
        return "updateByPrimaryKeySelective";
    }

    public String getCountByExampleMethodName(IntrospectedTable introspectedTable)
    {
        return "countByExample";
    }

    public String getUpdateByExampleSelectiveMethodName(IntrospectedTable introspectedTable)
    {
        return "updateByExampleSelective";
    }

    public String getUpdateByExampleWithBLOBsMethodName(IntrospectedTable introspectedTable)
    {
        Rules rules = introspectedTable.getRules();
        if(!rules.generateUpdateByExampleWithoutBLOBs())
            return "updateByExample";
        if(rules.generateRecordWithBLOBsClass())
            return "updateByExample";
        else
            return "updateByExampleWithBLOBs";
    }

    public String getUpdateByExampleWithoutBLOBsMethodName(IntrospectedTable introspectedTable)
    {
        Rules rules = introspectedTable.getRules();
        if(!rules.generateUpdateByExampleWithBLOBs())
            return "updateByExample";
        if(rules.generateRecordWithBLOBsClass())
            return "updateByExample";
        else
            return "updateByExampleWithoutBLOBs";
    }

    public String getInsertSelectiveMethodName(IntrospectedTable introspectedTable)
    {
        return "insertSelective";
    }
}
