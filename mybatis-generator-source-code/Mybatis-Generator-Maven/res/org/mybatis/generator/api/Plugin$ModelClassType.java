// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Plugin.java

package org.mybatis.generator.api;


// Referenced classes of package org.mybatis.generator.api:
//            Plugin

public static final class Plugin$ModelClassType extends Enum
{

    public static Plugin$ModelClassType[] values()
    {
        Plugin$ModelClassType aplugin$modelclasstype[];
        int i;
        Plugin$ModelClassType aplugin$modelclasstype1[];
        System.arraycopy(aplugin$modelclasstype = ENUM$VALUES, 0, aplugin$modelclasstype1 = new Plugin$ModelClassType[i = aplugin$modelclasstype.length], 0, i);
        return aplugin$modelclasstype1;
    }

    public static Plugin$ModelClassType valueOf(String s)
    {
        return (Plugin$ModelClassType)Enum.valueOf(org/mybatis/generator/api/Plugin$ModelClassType, s);
    }

    public static final Plugin$ModelClassType PRIMARY_KEY;
    public static final Plugin$ModelClassType BASE_RECORD;
    public static final Plugin$ModelClassType RECORD_WITH_BLOBS;
    private static final Plugin$ModelClassType ENUM$VALUES[];

    static 
    {
        PRIMARY_KEY = new Plugin$ModelClassType("PRIMARY_KEY", 0);
        BASE_RECORD = new Plugin$ModelClassType("BASE_RECORD", 1);
        RECORD_WITH_BLOBS = new Plugin$ModelClassType("RECORD_WITH_BLOBS", 2);
        ENUM$VALUES = (new Plugin$ModelClassType[] {
            PRIMARY_KEY, BASE_RECORD, RECORD_WITH_BLOBS
        });
    }

    private Plugin$ModelClassType(String s, int i)
    {
        super(s, i);
    }
}
