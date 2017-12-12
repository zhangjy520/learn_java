// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HashCodeUtil.java

package org.mybatis.generator.internal.util;

import java.lang.reflect.Array;

public final class HashCodeUtil
{

    public HashCodeUtil()
    {
    }

    public static int hash(int aSeed, boolean aBoolean)
    {
        return firstTerm(aSeed) + (aBoolean ? 1 : 0);
    }

    public static int hash(int aSeed, char aChar)
    {
        return firstTerm(aSeed) + aChar;
    }

    public static int hash(int aSeed, int aInt)
    {
        return firstTerm(aSeed) + aInt;
    }

    public static int hash(int aSeed, long aLong)
    {
        return firstTerm(aSeed) + (int)(aLong ^ aLong >>> 32);
    }

    public static int hash(int aSeed, float aFloat)
    {
        return hash(aSeed, Float.floatToIntBits(aFloat));
    }

    public static int hash(int aSeed, double aDouble)
    {
        return hash(aSeed, Double.doubleToLongBits(aDouble));
    }

    public static int hash(int aSeed, Object aObject)
    {
        int result = aSeed;
        if(aObject == null)
            result = hash(result, 0);
        else
        if(!isArray(aObject))
        {
            result = hash(result, aObject.hashCode());
        } else
        {
            int length = Array.getLength(aObject);
            for(int idx = 0; idx < length; idx++)
            {
                Object item = Array.get(aObject, idx);
                result = hash(result, item);
            }

        }
        return result;
    }

    private static int firstTerm(int aSeed)
    {
        return 37 * aSeed;
    }

    private static boolean isArray(Object aObject)
    {
        return aObject.getClass().isArray();
    }

    public static final int SEED = 23;
    private static final int fODD_PRIME_NUMBER = 37;
}
