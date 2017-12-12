// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GeneratedJavaFile.java

package org.mybatis.generator.api;

import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

// Referenced classes of package org.mybatis.generator.api:
//            GeneratedFile

public class GeneratedJavaFile extends GeneratedFile
{

    public GeneratedJavaFile(CompilationUnit compilationUnit, String targetProject)
    {
        super(targetProject);
        this.compilationUnit = compilationUnit;
    }

    public String getFormattedContent()
    {
        return compilationUnit.getFormattedContent();
    }

    public String getFileName()
    {
        return (new StringBuilder(String.valueOf(compilationUnit.getType().getShortName()))).append(".java").toString();
    }

    public String getTargetPackage()
    {
        return compilationUnit.getType().getPackageName();
    }

    public CompilationUnit getCompilationUnit()
    {
        return compilationUnit;
    }

    public boolean isMergeable()
    {
        return true;
    }

    private CompilationUnit compilationUnit;
}
