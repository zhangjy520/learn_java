// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FlatModelRules.java

package org.mybatis.generator.internal.rules;

import org.mybatis.generator.api.IntrospectedTable;

// Referenced classes of package org.mybatis.generator.internal.rules:
//            BaseRules

public class FlatModelRules extends BaseRules
{

    public FlatModelRules(IntrospectedTable introspectedTable)
    {
        super(introspectedTable);
    }

    public boolean generatePrimaryKeyClass()
    {
        return false;
    }

    public boolean generateBaseRecordClass()
    {
        return true;
    }

    public boolean generateRecordWithBLOBsClass()
    {
        return false;
    }
}
