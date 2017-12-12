// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CommentGenerator.java

package org.mybatis.generator.api;

import java.util.Properties;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.xml.XmlElement;

// Referenced classes of package org.mybatis.generator.api:
//            IntrospectedTable, IntrospectedColumn

public interface CommentGenerator
{

    public abstract void addConfigurationProperties(Properties properties);

    public abstract void addFieldComment(Field field, IntrospectedTable introspectedtable, IntrospectedColumn introspectedcolumn);

    public abstract void addFieldComment(Field field, IntrospectedTable introspectedtable);

    public abstract void addClassComment(InnerClass innerclass, IntrospectedTable introspectedtable);

    public abstract void addClassComment(InnerClass innerclass, IntrospectedTable introspectedtable, boolean flag);

    public abstract void addEnumComment(InnerEnum innerenum, IntrospectedTable introspectedtable);

    public abstract void addGetterComment(Method method, IntrospectedTable introspectedtable, IntrospectedColumn introspectedcolumn);

    public abstract void addSetterComment(Method method, IntrospectedTable introspectedtable, IntrospectedColumn introspectedcolumn);

    public abstract void addGeneralMethodComment(Method method, IntrospectedTable introspectedtable);

    public abstract void addJavaFileComment(CompilationUnit compilationunit);

    public abstract void addComment(XmlElement xmlelement);

    public abstract void addRootComment(XmlElement xmlelement);
}
