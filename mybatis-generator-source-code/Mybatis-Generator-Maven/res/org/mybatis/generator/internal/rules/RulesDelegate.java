// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RulesDelegate.java

package org.mybatis.generator.internal.rules;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

// Referenced classes of package org.mybatis.generator.internal.rules:
//            Rules

public class RulesDelegate
    implements Rules
{

    public RulesDelegate(Rules rules)
    {
        this.rules = rules;
    }

    public FullyQualifiedJavaType calculateAllFieldsClass()
    {
        return rules.calculateAllFieldsClass();
    }

    public boolean generateBaseRecordClass()
    {
        return rules.generateBaseRecordClass();
    }

    public boolean generateBaseResultMap()
    {
        return rules.generateBaseResultMap();
    }

    public boolean generateCountByExample()
    {
        return rules.generateCountByExample();
    }

    public boolean generateDeleteByExample()
    {
        return rules.generateDeleteByExample();
    }

    public boolean generateDeleteByPrimaryKey()
    {
        return rules.generateDeleteByPrimaryKey();
    }

    public boolean generateExampleClass()
    {
        return rules.generateExampleClass();
    }

    public boolean generateInsert()
    {
        return rules.generateInsert();
    }

    public boolean generateInsertSelective()
    {
        return rules.generateInsertSelective();
    }

    public boolean generatePrimaryKeyClass()
    {
        return rules.generatePrimaryKeyClass();
    }

    public boolean generateRecordWithBLOBsClass()
    {
        return rules.generateRecordWithBLOBsClass();
    }

    public boolean generateResultMapWithBLOBs()
    {
        return rules.generateResultMapWithBLOBs();
    }

    public boolean generateSelectByExampleWithBLOBs()
    {
        return rules.generateSelectByExampleWithBLOBs();
    }

    public boolean generateSelectByExampleWithoutBLOBs()
    {
        return rules.generateSelectByExampleWithoutBLOBs();
    }

    public boolean generateSelectByPrimaryKey()
    {
        return rules.generateSelectByPrimaryKey();
    }

    public boolean generateSQLExampleWhereClause()
    {
        return rules.generateSQLExampleWhereClause();
    }

    public boolean generateMyBatis3UpdateByExampleWhereClause()
    {
        return rules.generateMyBatis3UpdateByExampleWhereClause();
    }

    public boolean generateUpdateByExampleSelective()
    {
        return rules.generateUpdateByExampleSelective();
    }

    public boolean generateUpdateByExampleWithBLOBs()
    {
        return rules.generateUpdateByExampleWithBLOBs();
    }

    public boolean generateUpdateByExampleWithoutBLOBs()
    {
        return rules.generateUpdateByExampleWithoutBLOBs();
    }

    public boolean generateUpdateByPrimaryKeySelective()
    {
        return rules.generateUpdateByPrimaryKeySelective();
    }

    public boolean generateUpdateByPrimaryKeyWithBLOBs()
    {
        return rules.generateUpdateByPrimaryKeyWithBLOBs();
    }

    public boolean generateUpdateByPrimaryKeyWithoutBLOBs()
    {
        return rules.generateUpdateByPrimaryKeyWithoutBLOBs();
    }

    public IntrospectedTable getIntrospectedTable()
    {
        return rules.getIntrospectedTable();
    }

    public boolean generateBaseColumnList()
    {
        return rules.generateBaseColumnList();
    }

    public boolean generateBlobColumnList()
    {
        return rules.generateBlobColumnList();
    }

    protected Rules rules;
}
