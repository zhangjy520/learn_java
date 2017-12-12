// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractGenerator.java

package org.mybatis.generator.codegen;

import java.util.List;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.config.Context;

public abstract class AbstractGenerator
{

    public AbstractGenerator()
    {
    }

    public Context getContext()
    {
        return context;
    }

    public void setContext(Context context)
    {
        this.context = context;
    }

    public IntrospectedTable getIntrospectedTable()
    {
        return introspectedTable;
    }

    public void setIntrospectedTable(IntrospectedTable introspectedTable)
    {
        this.introspectedTable = introspectedTable;
    }

    public List getWarnings()
    {
        return warnings;
    }

    public void setWarnings(List warnings)
    {
        this.warnings = warnings;
    }

    public ProgressCallback getProgressCallback()
    {
        return progressCallback;
    }

    public void setProgressCallback(ProgressCallback progressCallback)
    {
        this.progressCallback = progressCallback;
    }

    protected Context context;
    protected IntrospectedTable introspectedTable;
    protected List warnings;
    protected ProgressCallback progressCallback;
}
