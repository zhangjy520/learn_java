// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NullProgressCallback.java

package org.mybatis.generator.internal;

import org.mybatis.generator.api.ProgressCallback;

public class NullProgressCallback
    implements ProgressCallback
{

    public NullProgressCallback()
    {
    }

    public void generationStarted(int i)
    {
    }

    public void introspectionStarted(int i)
    {
    }

    public void saveStarted(int i)
    {
    }

    public void startTask(String s)
    {
    }

    public void checkCancel()
        throws InterruptedException
    {
    }

    public void done()
    {
    }
}
