// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HierarchicalModelRules.java

package org.mybatis.generator.internal.rules;

import org.mybatis.generator.api.IntrospectedTable;

// Referenced classes of package org.mybatis.generator.internal.rules:
//            BaseRules

public class HierarchicalModelRules extends BaseRules
{

    public HierarchicalModelRules(IntrospectedTable introspectedTable)
    {
        super(introspectedTable);
    }

    public boolean generatePrimaryKeyClass()
    {
        return introspectedTable.hasPrimaryKeyColumns();
    }

    public boolean generateBaseRecordClass()
    {
        return introspectedTable.hasBaseColumns();
    }

    public boolean generateRecordWithBLOBsClass()
    {
        return introspectedTable.hasBLOBColumns();
    }
}
