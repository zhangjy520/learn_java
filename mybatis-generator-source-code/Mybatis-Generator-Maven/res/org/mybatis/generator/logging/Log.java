// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Log.java

package org.mybatis.generator.logging;


public interface Log
{

    public abstract boolean isDebugEnabled();

    public abstract void error(String s, Throwable throwable);

    public abstract void error(String s);

    public abstract void debug(String s);

    public abstract void warn(String s);
}
