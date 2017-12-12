// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MergeConstants.java

package org.mybatis.generator.config;


public class MergeConstants
{

    private MergeConstants()
    {
    }

    public static final String OLD_XML_ELEMENT_PREFIXES[] = {
        "ibatorgenerated_", "abatorgenerated_"
    };
    public static final String NEW_ELEMENT_TAG = "@mbggenerated";
    public static final String OLD_ELEMENT_TAGS[] = {
        "@ibatorgenerated", "@abatorgenerated", "@mbggenerated"
    };

}
