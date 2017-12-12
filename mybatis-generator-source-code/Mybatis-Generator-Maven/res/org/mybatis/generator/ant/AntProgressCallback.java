// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AntProgressCallback.java

package org.mybatis.generator.ant;

import org.apache.tools.ant.Task;
import org.mybatis.generator.internal.NullProgressCallback;

public class AntProgressCallback extends NullProgressCallback
{

    public AntProgressCallback(Task task, boolean verbose)
    {
        this.task = task;
        this.verbose = verbose;
    }

    public void startTask(String subTaskName)
    {
        if(verbose)
            task.log(subTaskName, 3);
    }

    private Task task;
    private boolean verbose;
}
