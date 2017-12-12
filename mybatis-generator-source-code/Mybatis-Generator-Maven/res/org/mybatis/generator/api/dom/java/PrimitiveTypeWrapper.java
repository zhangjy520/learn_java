// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PrimitiveTypeWrapper.java

package org.mybatis.generator.api.dom.java;


// Referenced classes of package org.mybatis.generator.api.dom.java:
//            FullyQualifiedJavaType

public class PrimitiveTypeWrapper extends FullyQualifiedJavaType
{

    private PrimitiveTypeWrapper(String fullyQualifiedName, String toPrimitiveMethod)
    {
        super(fullyQualifiedName);
        this.toPrimitiveMethod = toPrimitiveMethod;
    }

    public String getToPrimitiveMethod()
    {
        return toPrimitiveMethod;
    }

    public static PrimitiveTypeWrapper getBooleanInstance()
    {
        if(booleanInstance == null)
            booleanInstance = new PrimitiveTypeWrapper("java.lang.Boolean", "booleanValue()");
        return booleanInstance;
    }

    public static PrimitiveTypeWrapper getByteInstance()
    {
        if(byteInstance == null)
            byteInstance = new PrimitiveTypeWrapper("java.lang.Byte", "byteValue()");
        return byteInstance;
    }

    public static PrimitiveTypeWrapper getCharacterInstance()
    {
        if(characterInstance == null)
            characterInstance = new PrimitiveTypeWrapper("java.lang.Character", "charValue()");
        return characterInstance;
    }

    public static PrimitiveTypeWrapper getDoubleInstance()
    {
        if(doubleInstance == null)
            doubleInstance = new PrimitiveTypeWrapper("java.lang.Double", "doubleValue()");
        return doubleInstance;
    }

    public static PrimitiveTypeWrapper getFloatInstance()
    {
        if(floatInstance == null)
            floatInstance = new PrimitiveTypeWrapper("java.lang.Float", "floatValue()");
        return floatInstance;
    }

    public static PrimitiveTypeWrapper getIntegerInstance()
    {
        if(integerInstance == null)
            integerInstance = new PrimitiveTypeWrapper("java.lang.Integer", "intValue()");
        return integerInstance;
    }

    public static PrimitiveTypeWrapper getLongInstance()
    {
        if(longInstance == null)
            longInstance = new PrimitiveTypeWrapper("java.lang.Long", "longValue()");
        return longInstance;
    }

    public static PrimitiveTypeWrapper getShortInstance()
    {
        if(shortInstance == null)
            shortInstance = new PrimitiveTypeWrapper("java.lang.Short", "shortValue()");
        return shortInstance;
    }

    private static PrimitiveTypeWrapper booleanInstance;
    private static PrimitiveTypeWrapper byteInstance;
    private static PrimitiveTypeWrapper characterInstance;
    private static PrimitiveTypeWrapper doubleInstance;
    private static PrimitiveTypeWrapper floatInstance;
    private static PrimitiveTypeWrapper integerInstance;
    private static PrimitiveTypeWrapper longInstance;
    private static PrimitiveTypeWrapper shortInstance;
    private String toPrimitiveMethod;
}
