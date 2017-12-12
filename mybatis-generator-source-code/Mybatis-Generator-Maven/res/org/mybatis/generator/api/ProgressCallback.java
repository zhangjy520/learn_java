// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProgressCallback.java

package org.mybatis.generator.api;


public interface ProgressCallback
{

    public abstract void introspectionStarted(int i);

    public abstract void generationStarted(int i);

    public abstract void saveStarted(int i);

    public abstract void startTask(String s);

    public abstract void done();

    public abstract void checkCancel()
        throws InterruptedException;
}
