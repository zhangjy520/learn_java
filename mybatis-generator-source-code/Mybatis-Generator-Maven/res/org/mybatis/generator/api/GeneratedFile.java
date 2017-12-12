// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GeneratedFile.java

package org.mybatis.generator.api;


public abstract class GeneratedFile
{

    public GeneratedFile(String targetProject)
    {
        this.targetProject = targetProject;
    }

    public abstract String getFormattedContent();

    public abstract String getFileName();

    public String getTargetProject()
    {
        return targetProject;
    }

    public abstract String getTargetPackage();

    public String toString()
    {
        return getFormattedContent();
    }

    public abstract boolean isMergeable();

    private String targetProject;
}
