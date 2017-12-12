// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ShellCallback.java

package org.mybatis.generator.api;

import java.io.File;
import org.mybatis.generator.exception.ShellException;

public interface ShellCallback
{

    public abstract File getDirectory(String s, String s1)
        throws ShellException;

    public abstract String mergeJavaFile(String s, String s1, String as[])
        throws ShellException;

    public abstract void refreshProject(String s);

    public abstract boolean isMergeSupported();

    public abstract boolean isOverwriteEnabled();
}
