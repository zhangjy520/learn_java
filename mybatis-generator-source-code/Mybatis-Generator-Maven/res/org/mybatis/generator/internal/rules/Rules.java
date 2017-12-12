// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Rules.java

package org.mybatis.generator.internal.rules;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

public interface Rules
{

    public abstract boolean generateInsert();

    public abstract boolean generateInsertSelective();

    public abstract FullyQualifiedJavaType calculateAllFieldsClass();

    public abstract boolean generateUpdateByPrimaryKeyWithoutBLOBs();

    public abstract boolean generateUpdateByPrimaryKeyWithBLOBs();

    public abstract boolean generateUpdateByPrimaryKeySelective();

    public abstract boolean generateDeleteByPrimaryKey();

    public abstract boolean generateDeleteByExample();

    public abstract boolean generateBaseResultMap();

    public abstract boolean generateResultMapWithBLOBs();

    public abstract boolean generateSQLExampleWhereClause();

    public abstract boolean generateMyBatis3UpdateByExampleWhereClause();

    public abstract boolean generateBaseColumnList();

    public abstract boolean generateBlobColumnList();

    public abstract boolean generateSelectByPrimaryKey();

    public abstract boolean generateSelectByExampleWithoutBLOBs();

    public abstract boolean generateSelectByExampleWithBLOBs();

    public abstract boolean generateExampleClass();

    public abstract boolean generateCountByExample();

    public abstract boolean generateUpdateByExampleSelective();

    public abstract boolean generateUpdateByExampleWithoutBLOBs();

    public abstract boolean generateUpdateByExampleWithBLOBs();

    public abstract boolean generatePrimaryKeyClass();

    public abstract boolean generateBaseRecordClass();

    public abstract boolean generateRecordWithBLOBsClass();

    public abstract IntrospectedTable getIntrospectedTable();
}
