// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CompilationUnit.java

package org.mybatis.generator.api.dom.java;

import java.util.List;
import java.util.Set;

// Referenced classes of package org.mybatis.generator.api.dom.java:
//            FullyQualifiedJavaType

public interface CompilationUnit
{

    public abstract String getFormattedContent();

    public abstract Set getImportedTypes();

    public abstract FullyQualifiedJavaType getSuperClass();

    public abstract boolean isJavaInterface();

    public abstract boolean isJavaEnumeration();

    public abstract Set getSuperInterfaceTypes();

    public abstract FullyQualifiedJavaType getType();

    public abstract void addImportedType(FullyQualifiedJavaType fullyqualifiedjavatype);

    public abstract void addImportedTypes(Set set);

    public abstract void addFileCommentLine(String s);

    public abstract List getFileCommentLines();
}
