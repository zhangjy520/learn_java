// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ObjectFactory.java

package org.mybatis.generator.internal;

import java.util.List;
import org.mybatis.generator.api.*;
import org.mybatis.generator.codegen.ibatis2.IntrospectedTableIbatis2Java2Impl;
import org.mybatis.generator.codegen.ibatis2.IntrospectedTableIbatis2Java5Impl;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

// Referenced classes of package org.mybatis.generator.internal:
//            DefaultCommentGenerator

public class ObjectFactory
{

    private ObjectFactory()
    {
    }

    private static ClassLoader getClassLoader()
    {
        if(externalClassLoader != null)
            return externalClassLoader;
        else
            return Thread.currentThread().getContextClassLoader();
    }

    public static synchronized void setExternalClassLoader(ClassLoader classLoader)
    {
        externalClassLoader = classLoader;
    }

    public static Class externalClassForName(String type)
        throws ClassNotFoundException
    {
        Class clazz;
        try
        {
            clazz = getClassLoader().loadClass(type);
        }
        catch(Throwable e)
        {
            clazz = null;
        }
        if(clazz == null)
            clazz = Class.forName(type);
        return clazz;
    }

    public static Object createExternalObject(String type)
    {
        Object answer;
        try
        {
            Class clazz = externalClassForName(type);
            answer = clazz.newInstance();
        }
        catch(Exception e)
        {
            throw new RuntimeException(Messages.getString("RuntimeError.6", type), e);
        }
        return answer;
    }

    public static Class internalClassForName(String type)
        throws ClassNotFoundException
    {
        Class clazz = null;
        try
        {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            clazz = cl.loadClass(type);
        }
        catch(Exception exception) { }
        if(clazz == null)
            clazz = Class.forName(type);
        return clazz;
    }

    public static Object createInternalObject(String type)
    {
        Object answer;
        try
        {
            Class clazz = internalClassForName(type);
            answer = clazz.newInstance();
        }
        catch(Exception e)
        {
            throw new RuntimeException(Messages.getString("RuntimeError.6", type), e);
        }
        return answer;
    }

    public static JavaTypeResolver createJavaTypeResolver(Context context, List warnings)
    {
        JavaTypeResolverConfiguration config = context.getJavaTypeResolverConfiguration();
        String type;
        if(config != null && config.getConfigurationType() != null)
        {
            type = config.getConfigurationType();
            if("DEFAULT".equalsIgnoreCase(type))
                type = org/mybatis/generator/internal/types/JavaTypeResolverDefaultImpl.getName();
        } else
        {
            type = org/mybatis/generator/internal/types/JavaTypeResolverDefaultImpl.getName();
        }
        JavaTypeResolver answer = (JavaTypeResolver)createInternalObject(type);
        answer.setWarnings(warnings);
        if(config != null)
            answer.addConfigurationProperties(config.getProperties());
        answer.setContext(context);
        return answer;
    }

    public static Plugin createPlugin(Context context, PluginConfiguration pluginConfiguration)
    {
        Plugin plugin = (Plugin)createInternalObject(pluginConfiguration.getConfigurationType());
        plugin.setContext(context);
        plugin.setProperties(pluginConfiguration.getProperties());
        return plugin;
    }

    public static CommentGenerator createCommentGenerator(Context context)
    {
        CommentGeneratorConfiguration config = context.getCommentGeneratorConfiguration();
        String type;
        if(config == null || config.getConfigurationType() == null)
            type = org/mybatis/generator/internal/DefaultCommentGenerator.getName();
        else
            type = config.getConfigurationType();
        CommentGenerator answer = (CommentGenerator)createInternalObject(type);
        if(config != null)
            answer.addConfigurationProperties(config.getProperties());
        return answer;
    }

    public static IntrospectedTable createIntrospectedTable(TableConfiguration tableConfiguration, FullyQualifiedTable table, Context context)
    {
        String type = context.getTargetRuntime();
        if(!StringUtility.stringHasValue(type))
            type = org/mybatis/generator/codegen/mybatis3/IntrospectedTableMyBatis3Impl.getName();
        else
        if("Ibatis2Java2".equalsIgnoreCase(type))
            type = org/mybatis/generator/codegen/ibatis2/IntrospectedTableIbatis2Java2Impl.getName();
        else
        if("Ibatis2Java5".equalsIgnoreCase(type))
            type = org/mybatis/generator/codegen/ibatis2/IntrospectedTableIbatis2Java5Impl.getName();
        else
        if("Ibatis3".equalsIgnoreCase(type))
            type = org/mybatis/generator/codegen/mybatis3/IntrospectedTableMyBatis3Impl.getName();
        else
        if("MyBatis3".equalsIgnoreCase(type))
            type = org/mybatis/generator/codegen/mybatis3/IntrospectedTableMyBatis3Impl.getName();
        IntrospectedTable answer = (IntrospectedTable)createInternalObject(type);
        answer.setFullyQualifiedTable(table);
        answer.setContext(context);
        answer.setTableConfiguration(tableConfiguration);
        return answer;
    }

    public static IntrospectedColumn createIntrospectedColumn(Context context)
    {
        String type = context.getIntrospectedColumnImpl();
        if(!StringUtility.stringHasValue(type))
            type = org/mybatis/generator/api/IntrospectedColumn.getName();
        IntrospectedColumn answer = (IntrospectedColumn)createInternalObject(type);
        answer.setContext(context);
        return answer;
    }

    private static ClassLoader externalClassLoader;
}
