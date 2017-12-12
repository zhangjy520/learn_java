// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqualsUtil.java

package org.mybatis.generator.internal.util;


public final class EqualsUtil
{

    public EqualsUtil()
    {
    }

    public static boolean areEqual(boolean aThis, boolean aThat)
    {
        return aThis == aThat;
    }

    public static boolean areEqual(char aThis, char aThat)
    {
        return aThis == aThat;
    }

    public static boolean areEqual(long aThis, long aThat)
    {
        return aThis == aThat;
    }

    public static boolean areEqual(float aThis, float aThat)
    {
        return Float.floatToIntBits(aThis) == Float.floatToIntBits(aThat);
    }

    public static boolean areEqual(double aThis, double aThat)
    {
        return Double.doubleToLongBits(aThis) == Double.doubleToLongBits(aThat);
    }

    public static boolean areEqual(Object aThis, Object aThat)
    {
        return aThis != null ? aThis.equals(aThat) : aThat == null;
    }
}
