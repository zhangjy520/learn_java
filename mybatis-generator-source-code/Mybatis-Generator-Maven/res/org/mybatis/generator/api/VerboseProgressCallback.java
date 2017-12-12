// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VerboseProgressCallback.java

package org.mybatis.generator.api;

import java.io.PrintStream;
import org.mybatis.generator.internal.NullProgressCallback;

public class VerboseProgressCallback extends NullProgressCallback
{

    public VerboseProgressCallback()
    {
    }

    public void startTask(String taskName)
    {
        System.out.println(taskName);
    }
}
