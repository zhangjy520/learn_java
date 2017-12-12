// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JavaVisibility.java

package org.mybatis.generator.api.dom.java;


public final class JavaVisibility extends Enum
{

    private JavaVisibility(String s, int i, String value)
    {
        super(s, i);
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    public static JavaVisibility[] values()
    {
        JavaVisibility ajavavisibility[];
        int i;
        JavaVisibility ajavavisibility1[];
        System.arraycopy(ajavavisibility = ENUM$VALUES, 0, ajavavisibility1 = new JavaVisibility[i = ajavavisibility.length], 0, i);
        return ajavavisibility1;
    }

    public static JavaVisibility valueOf(String s)
    {
        return (JavaVisibility)Enum.valueOf(org/mybatis/generator/api/dom/java/JavaVisibility, s);
    }

    public static final JavaVisibility PUBLIC;
    public static final JavaVisibility PRIVATE;
    public static final JavaVisibility PROTECTED;
    public static final JavaVisibility DEFAULT;
    private String value;
    private static final JavaVisibility ENUM$VALUES[];

    static 
    {
        PUBLIC = new JavaVisibility("PUBLIC", 0, "public ");
        PRIVATE = new JavaVisibility("PRIVATE", 1, "private ");
        PROTECTED = new JavaVisibility("PROTECTED", 2, "protected ");
        DEFAULT = new JavaVisibility("DEFAULT", 3, "");
        ENUM$VALUES = (new JavaVisibility[] {
            PUBLIC, PRIVATE, PROTECTED, DEFAULT
        });
    }
}
