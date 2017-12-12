// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PrimaryKeyGenerator.java

package org.mybatis.generator.codegen.mybatis3.model;

import java.util.*;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.RootClassInfo;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.util.messages.Messages;

public class PrimaryKeyGenerator extends AbstractJavaGenerator
{

    public PrimaryKeyGenerator()
    {
    }

    public List getCompilationUnits()
    {
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        progressCallback.startTask(Messages.getString("Progress.7", table.toString()));
        Plugin plugins = context.getPlugins();
        CommentGenerator commentGenerator = context.getCommentGenerator();
        TopLevelClass topLevelClass = new TopLevelClass(introspectedTable.getPrimaryKeyType());
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(topLevelClass);
        String rootClass = getRootClass();
        if(rootClass != null)
        {
            topLevelClass.setSuperClass(new FullyQualifiedJavaType(rootClass));
            topLevelClass.addImportedType(topLevelClass.getSuperClass());
        }
        for(Iterator iterator = introspectedTable.getPrimaryKeyColumns().iterator(); iterator.hasNext();)
        {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterator.next();
            if(!RootClassInfo.getInstance(rootClass, warnings).containsProperty(introspectedColumn))
            {
                Field field = getJavaBeansField(introspectedColumn);
                if(plugins.modelFieldGenerated(field, topLevelClass, introspectedColumn, introspectedTable, org.mybatis.generator.api.Plugin.ModelClassType.PRIMARY_KEY))
                {
                    topLevelClass.addField(field);
                    topLevelClass.addImportedType(field.getType());
                }
                Method method = getJavaBeansGetter(introspectedColumn);
                if(plugins.modelGetterMethodGenerated(method, topLevelClass, introspectedColumn, introspectedTable, org.mybatis.generator.api.Plugin.ModelClassType.PRIMARY_KEY))
                    topLevelClass.addMethod(method);
                method = getJavaBeansSetter(introspectedColumn);
                if(plugins.modelSetterMethodGenerated(method, topLevelClass, introspectedColumn, introspectedTable, org.mybatis.generator.api.Plugin.ModelClassType.PRIMARY_KEY))
                    topLevelClass.addMethod(method);
            }
        }

        List answer = new ArrayList();
        if(context.getPlugins().modelPrimaryKeyClassGenerated(topLevelClass, introspectedTable))
            answer.add(topLevelClass);
        return answer;
    }
}
