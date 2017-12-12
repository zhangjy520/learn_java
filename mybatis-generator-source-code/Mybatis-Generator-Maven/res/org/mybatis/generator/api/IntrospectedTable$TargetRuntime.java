// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IntrospectedTable.java

package org.mybatis.generator.api;


// Referenced classes of package org.mybatis.generator.api:
//            IntrospectedTable

public static final class IntrospectedTable$TargetRuntime extends Enum
{

    public static IntrospectedTable$TargetRuntime[] values()
    {
        IntrospectedTable$TargetRuntime aintrospectedtable$targetruntime[];
        int i;
        IntrospectedTable$TargetRuntime aintrospectedtable$targetruntime1[];
        System.arraycopy(aintrospectedtable$targetruntime = ENUM$VALUES, 0, aintrospectedtable$targetruntime1 = new IntrospectedTable$TargetRuntime[i = aintrospectedtable$targetruntime.length], 0, i);
        return aintrospectedtable$targetruntime1;
    }

    public static IntrospectedTable$TargetRuntime valueOf(String s)
    {
        return (IntrospectedTable$TargetRuntime)Enum.valueOf(org/mybatis/generator/api/IntrospectedTable$TargetRuntime, s);
    }

    public static final IntrospectedTable$TargetRuntime IBATIS2;
    public static final IntrospectedTable$TargetRuntime MYBATIS3;
    private static final IntrospectedTable$TargetRuntime ENUM$VALUES[];

    static 
    {
        IBATIS2 = new IntrospectedTable$TargetRuntime("IBATIS2", 0);
        MYBATIS3 = new IntrospectedTable$TargetRuntime("MYBATIS3", 1);
        ENUM$VALUES = (new IntrospectedTable$TargetRuntime[] {
            IBATIS2, MYBATIS3
        });
    }

    private IntrospectedTable$TargetRuntime(String s, int i)
    {
        super(s, i);
    }
}
