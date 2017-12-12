// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseRules.java

package org.mybatis.generator.internal.rules;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.config.TableConfiguration;

// Referenced classes of package org.mybatis.generator.internal.rules:
//            Rules

public abstract class BaseRules
    implements Rules
{

    public BaseRules(IntrospectedTable introspectedTable)
    {
        this.introspectedTable = introspectedTable;
        tableConfiguration = introspectedTable.getTableConfiguration();
    }

    public boolean generateInsert()
    {
        return tableConfiguration.isInsertStatementEnabled();
    }

    public boolean generateInsertSelective()
    {
        return tableConfiguration.isInsertStatementEnabled();
    }

    public FullyQualifiedJavaType calculateAllFieldsClass()
    {
        String answer;
        if(generateRecordWithBLOBsClass())
            answer = introspectedTable.getRecordWithBLOBsType();
        else
        if(generateBaseRecordClass())
            answer = introspectedTable.getBaseRecordType();
        else
            answer = introspectedTable.getPrimaryKeyType();
        return new FullyQualifiedJavaType(answer);
    }

    public boolean generateUpdateByPrimaryKeyWithoutBLOBs()
    {
        boolean rc = tableConfiguration.isUpdateByPrimaryKeyStatementEnabled() && introspectedTable.hasPrimaryKeyColumns() && introspectedTable.hasBaseColumns();
        return rc;
    }

    public boolean generateUpdateByPrimaryKeyWithBLOBs()
    {
        boolean rc = tableConfiguration.isUpdateByPrimaryKeyStatementEnabled() && introspectedTable.hasPrimaryKeyColumns() && introspectedTable.hasBLOBColumns();
        return rc;
    }

    public boolean generateUpdateByPrimaryKeySelective()
    {
        boolean rc = tableConfiguration.isUpdateByPrimaryKeyStatementEnabled() && introspectedTable.hasPrimaryKeyColumns() && (introspectedTable.hasBLOBColumns() || introspectedTable.hasBaseColumns());
        return rc;
    }

    public boolean generateDeleteByPrimaryKey()
    {
        boolean rc = tableConfiguration.isDeleteByPrimaryKeyStatementEnabled() && introspectedTable.hasPrimaryKeyColumns();
        return rc;
    }

    public boolean generateDeleteByExample()
    {
        boolean rc = tableConfiguration.isDeleteByExampleStatementEnabled();
        return rc;
    }

    public boolean generateBaseResultMap()
    {
        boolean rc = tableConfiguration.isSelectByExampleStatementEnabled() || tableConfiguration.isSelectByPrimaryKeyStatementEnabled();
        return rc;
    }

    public boolean generateResultMapWithBLOBs()
    {
        boolean rc = (tableConfiguration.isSelectByExampleStatementEnabled() || tableConfiguration.isSelectByPrimaryKeyStatementEnabled()) && introspectedTable.hasBLOBColumns();
        return rc;
    }

    public boolean generateSQLExampleWhereClause()
    {
        boolean rc = tableConfiguration.isSelectByExampleStatementEnabled() || tableConfiguration.isDeleteByExampleStatementEnabled() || tableConfiguration.isCountByExampleStatementEnabled();
        if(introspectedTable.getTargetRuntime() == org.mybatis.generator.api.IntrospectedTable.TargetRuntime.IBATIS2)
            rc |= tableConfiguration.isUpdateByExampleStatementEnabled();
        return rc;
    }

    public boolean generateMyBatis3UpdateByExampleWhereClause()
    {
        return introspectedTable.getTargetRuntime() == org.mybatis.generator.api.IntrospectedTable.TargetRuntime.MYBATIS3 && tableConfiguration.isUpdateByExampleStatementEnabled();
    }

    public boolean generateSelectByPrimaryKey()
    {
        boolean rc = tableConfiguration.isSelectByPrimaryKeyStatementEnabled() && introspectedTable.hasPrimaryKeyColumns() && (introspectedTable.hasBaseColumns() || introspectedTable.hasBLOBColumns());
        return rc;
    }

    public boolean generateSelectByExampleWithoutBLOBs()
    {
        return tableConfiguration.isSelectByExampleStatementEnabled();
    }

    public boolean generateSelectByExampleWithBLOBs()
    {
        boolean rc = tableConfiguration.isSelectByExampleStatementEnabled() && introspectedTable.hasBLOBColumns();
        return rc;
    }

    public boolean generateExampleClass()
    {
        boolean rc = tableConfiguration.isSelectByExampleStatementEnabled() || tableConfiguration.isDeleteByExampleStatementEnabled() || tableConfiguration.isCountByExampleStatementEnabled() || tableConfiguration.isUpdateByExampleStatementEnabled();
        return rc;
    }

    public boolean generateCountByExample()
    {
        boolean rc = tableConfiguration.isCountByExampleStatementEnabled();
        return rc;
    }

    public boolean generateUpdateByExampleSelective()
    {
        boolean rc = tableConfiguration.isUpdateByExampleStatementEnabled();
        return rc;
    }

    public boolean generateUpdateByExampleWithoutBLOBs()
    {
        boolean rc = tableConfiguration.isUpdateByExampleStatementEnabled() && (introspectedTable.hasPrimaryKeyColumns() || introspectedTable.hasBaseColumns());
        return rc;
    }

    public boolean generateUpdateByExampleWithBLOBs()
    {
        boolean rc = tableConfiguration.isUpdateByExampleStatementEnabled() && introspectedTable.hasBLOBColumns();
        return rc;
    }

    public IntrospectedTable getIntrospectedTable()
    {
        return introspectedTable;
    }

    public boolean generateBaseColumnList()
    {
        return generateSelectByPrimaryKey() || generateSelectByExampleWithoutBLOBs();
    }

    public boolean generateBlobColumnList()
    {
        return introspectedTable.hasBLOBColumns() && (tableConfiguration.isSelectByExampleStatementEnabled() || tableConfiguration.isSelectByPrimaryKeyStatementEnabled());
    }

    protected TableConfiguration tableConfiguration;
    protected IntrospectedTable introspectedTable;
}
