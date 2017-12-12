// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConditionalModelRules.java

package org.mybatis.generator.internal.rules;

import java.util.List;
import org.mybatis.generator.api.IntrospectedTable;

// Referenced classes of package org.mybatis.generator.internal.rules:
//            BaseRules

public class ConditionalModelRules extends BaseRules
{

    public ConditionalModelRules(IntrospectedTable introspectedTable)
    {
        super(introspectedTable);
    }

    public boolean generatePrimaryKeyClass()
    {
        return introspectedTable.getPrimaryKeyColumns().size() > 1;
    }

    public boolean generateBaseRecordClass()
    {
        return introspectedTable.getBaseColumns().size() > 0 || introspectedTable.getPrimaryKeyColumns().size() == 1 || introspectedTable.getBLOBColumns().size() > 0 && !generateRecordWithBLOBsClass();
    }

    public boolean generateRecordWithBLOBsClass()
    {
        int otherColumnCount = introspectedTable.getPrimaryKeyColumns().size() + introspectedTable.getBaseColumns().size();
        return otherColumnCount > 1 && introspectedTable.getBLOBColumns().size() > 1;
    }
}
