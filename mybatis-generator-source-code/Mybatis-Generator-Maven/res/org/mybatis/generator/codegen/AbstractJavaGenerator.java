// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractJavaGenerator.java

package org.mybatis.generator.codegen;

import com.fap.mybatis.extend.FAPProperties;
import java.util.List;
import java.util.Properties;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.internal.util.JavaBeansUtil;
import org.mybatis.generator.internal.util.StringUtility;

// Referenced classes of package org.mybatis.generator.codegen:
//            AbstractGenerator

public abstract class AbstractJavaGenerator extends AbstractGenerator
{

    public AbstractJavaGenerator()
    {
    }

    public abstract List getCompilationUnits();

    public static Method getGetter(Field field)
    {
        Method method = new Method();
        method.setName(JavaBeansUtil.getGetterMethodName(field.getName(), field.getType()));
        method.setReturnType(field.getType());
        method.setVisibility(JavaVisibility.PUBLIC);
        StringBuilder sb = new StringBuilder();
        sb.append("return ");
        sb.append(field.getName());
        sb.append(';');
        method.addBodyLine(sb.toString());
        return method;
    }

    public Method getJavaBeansGetter(IntrospectedColumn introspectedColumn)
    {
        FullyQualifiedJavaType fqjt = introspectedColumn.getFullyQualifiedJavaType();
        String property = introspectedColumn.getJavaProperty();
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(fqjt);
        method.setName(JavaBeansUtil.getGetterMethodName(property, fqjt));
        context.getCommentGenerator().addGetterComment(method, introspectedTable, introspectedColumn);
        StringBuilder sb = new StringBuilder();
        sb.append("return ");
        sb.append(property);
        sb.append(';');
        method.addBodyLine(sb.toString());
        return method;
    }

    public Field getJavaBeansField(IntrospectedColumn introspectedColumn)
    {
        FullyQualifiedJavaType fqjt = introspectedColumn.getFullyQualifiedJavaType();
        String property = introspectedColumn.getJavaProperty();
        Field field = new Field();
        field.setVisibility(JavaVisibility.PRIVATE);
        field.setType(fqjt);
        field.setName(property);
        context.getCommentGenerator().addFieldComment(field, introspectedTable, introspectedColumn);
        return field;
    }

    public Method getJavaBeansSetter(IntrospectedColumn introspectedColumn)
    {
        FullyQualifiedJavaType fqjt = introspectedColumn.getFullyQualifiedJavaType();
        String property = introspectedColumn.getJavaProperty();
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName(JavaBeansUtil.getSetterMethodName(property));
        method.addParameter(new Parameter(fqjt, property));
        context.getCommentGenerator().addSetterComment(method, introspectedTable, introspectedColumn);
        StringBuilder sb = new StringBuilder();
        if(isTrimStringsEnabled() && introspectedColumn.isStringColumn())
        {
            sb.append("this.");
            sb.append(property);
            sb.append(" = ");
            sb.append(property);
            sb.append(" == null ? null : ");
            sb.append(property);
            sb.append(".trim();");
            method.addBodyLine(sb.toString());
        } else
        {
            sb.append("this.");
            sb.append(property);
            sb.append(" = ");
            sb.append(property);
            sb.append(';');
            method.addBodyLine(sb.toString());
        }
        return method;
    }

    public boolean isTrimStringsEnabled()
    {
        Properties properties = context.getJavaModelGeneratorConfiguration().getProperties();
        boolean rc = StringUtility.isTrue(properties.getProperty("trimStrings"));
        return rc;
    }

    public String getRootClass()
    {
        String rootClass = introspectedTable.getTableConfigurationProperty("rootClass");
        if(rootClass == null)
        {
            Properties properties = context.getJavaModelGeneratorConfiguration().getProperties();
            rootClass = properties.getProperty("rootClass");
        }
        if(rootClass == null && FAPProperties.MODEL_ROOT_CLASS != null)
            rootClass = FAPProperties.MODEL_ROOT_CLASS;
        return rootClass;
    }
}
