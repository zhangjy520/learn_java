// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseRecordGenerator.java

package org.mybatis.generator.codegen.mybatis3.model;

import java.util.*;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.RootClassInfo;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.rules.Rules;
import org.mybatis.generator.internal.util.messages.Messages;

public class BaseRecordGenerator extends AbstractJavaGenerator
{

    public BaseRecordGenerator()
    {
    }

    public List getCompilationUnits()
    {
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        progressCallback.startTask(Messages.getString("Progress.8", table.toString()));
        Plugin plugins = context.getPlugins();
        CommentGenerator commentGenerator = context.getCommentGenerator();
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        TopLevelClass topLevelClass = new TopLevelClass(type);
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(topLevelClass);
        FullyQualifiedJavaType superClass = getSuperClass();
        if(superClass != null)
        {
            topLevelClass.setSuperClass(superClass);
            topLevelClass.addImportedType(superClass);
        }
        List introspectedColumns;
        if(includePrimaryKeyColumns())
        {
            if(includeBLOBColumns())
                introspectedColumns = introspectedTable.getAllColumns();
            else
                introspectedColumns = introspectedTable.getNonBLOBColumns();
        } else
        if(includeBLOBColumns())
            introspectedColumns = introspectedTable.getNonPrimaryKeyColumns();
        else
            introspectedColumns = introspectedTable.getBaseColumns();
        String rootClass = getRootClass();
        for(Iterator iterator = introspectedColumns.iterator(); iterator.hasNext();)
        {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterator.next();
            if(!RootClassInfo.getInstance(rootClass, warnings).containsProperty(introspectedColumn))
            {
                Field field = getJavaBeansField(introspectedColumn);
                if(plugins.modelFieldGenerated(field, topLevelClass, introspectedColumn, introspectedTable, org.mybatis.generator.api.Plugin.ModelClassType.BASE_RECORD))
                {
                    topLevelClass.addField(field);
                    topLevelClass.addImportedType(field.getType());
                }
                Method method = getJavaBeansGetter(introspectedColumn);
                if(plugins.modelGetterMethodGenerated(method, topLevelClass, introspectedColumn, introspectedTable, org.mybatis.generator.api.Plugin.ModelClassType.BASE_RECORD))
                    topLevelClass.addMethod(method);
                method = getJavaBeansSetter(introspectedColumn);
                if(plugins.modelSetterMethodGenerated(method, topLevelClass, introspectedColumn, introspectedTable, org.mybatis.generator.api.Plugin.ModelClassType.BASE_RECORD))
                    topLevelClass.addMethod(method);
            }
        }

        List answer = new ArrayList();
        if(context.getPlugins().modelBaseRecordClassGenerated(topLevelClass, introspectedTable))
            answer.add(topLevelClass);
        return answer;
    }

    private FullyQualifiedJavaType getSuperClass()
    {
        FullyQualifiedJavaType superClass;
        if(introspectedTable.getRules().generatePrimaryKeyClass())
        {
            superClass = new FullyQualifiedJavaType(introspectedTable.getPrimaryKeyType());
        } else
        {
            String rootClass = getRootClass();
            if(rootClass != null)
                superClass = new FullyQualifiedJavaType(rootClass);
            else
                superClass = null;
        }
        return superClass;
    }

    private boolean includePrimaryKeyColumns()
    {
        return !introspectedTable.getRules().generatePrimaryKeyClass() && introspectedTable.hasPrimaryKeyColumns();
    }

    private boolean includeBLOBColumns()
    {
        return !introspectedTable.getRules().generateRecordWithBLOBsClass() && introspectedTable.hasBLOBColumns();
    }
}
