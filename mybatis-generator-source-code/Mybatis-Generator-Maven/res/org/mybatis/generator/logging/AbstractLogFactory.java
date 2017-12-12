// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractLogFactory.java

package org.mybatis.generator.logging;


// Referenced classes of package org.mybatis.generator.logging:
//            Log

public interface AbstractLogFactory
{

    public abstract Log getLog(Class class1);
}
