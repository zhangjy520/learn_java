// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SelectByPrimaryKeyMethodGenerator.java

package org.mybatis.generator.codegen.ibatis2.dao.elements;

import java.util.*;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.ibatis2.dao.templates.AbstractDAOTemplate;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.rules.Rules;
import org.mybatis.generator.internal.util.JavaBeansUtil;

// Referenced classes of package org.mybatis.generator.codegen.ibatis2.dao.elements:
//            AbstractDAOElementGenerator

public class SelectByPrimaryKeyMethodGenerator extends AbstractDAOElementGenerator
{

    public SelectByPrimaryKeyMethodGenerator()
    {
    }

    public void addImplementationElements(TopLevelClass topLevelClass)
    {
        Set importedTypes = new TreeSet();
        Method method = getMethodShell(importedTypes);
        StringBuilder sb = new StringBuilder();
        if(!introspectedTable.getRules().generatePrimaryKeyClass())
        {
            FullyQualifiedJavaType keyType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
            topLevelClass.addImportedType(keyType);
            sb.setLength(0);
            sb.append(keyType.getShortName());
            sb.append(" _key = new ");
            sb.append(keyType.getShortName());
            sb.append("();");
            method.addBodyLine(sb.toString());
            for(Iterator iterator = introspectedTable.getPrimaryKeyColumns().iterator(); iterator.hasNext(); method.addBodyLine(sb.toString()))
            {
                IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterator.next();
                sb.setLength(0);
                sb.append("_key.");
                sb.append(JavaBeansUtil.getSetterMethodName(introspectedColumn.getJavaProperty()));
                sb.append('(');
                sb.append(introspectedColumn.getJavaProperty());
                sb.append(");");
            }

        }
        FullyQualifiedJavaType returnType = method.getReturnType();
        sb.setLength(0);
        sb.append(returnType.getShortName());
        sb.append(" record = (");
        sb.append(returnType.getShortName());
        sb.append(") ");
        sb.append(daoTemplate.getQueryForObjectMethod(introspectedTable.getIbatis2SqlMapNamespace(), introspectedTable.getSelectByPrimaryKeyStatementId(), "_key"));
        method.addBodyLine(sb.toString());
        method.addBodyLine("return record;");
        if(context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(method, topLevelClass, introspectedTable))
        {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    public void addInterfaceElements(Interface interfaze)
    {
        Set importedTypes = new TreeSet();
        Method method = getMethodShell(importedTypes);
        if(context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable))
        {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }

    private Method getMethodShell(Set importedTypes)
    {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType returnType = introspectedTable.getRules().calculateAllFieldsClass();
        method.setReturnType(returnType);
        importedTypes.add(returnType);
        method.setName(getDAOMethodNameCalculator().getSelectByPrimaryKeyMethodName(introspectedTable));
        if(introspectedTable.getRules().generatePrimaryKeyClass())
        {
            FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getPrimaryKeyType());
            importedTypes.add(type);
            method.addParameter(new Parameter(type, "_key"));
        } else
        {
            IntrospectedColumn introspectedColumn;
            FullyQualifiedJavaType type;
            for(Iterator iterator = introspectedTable.getPrimaryKeyColumns().iterator(); iterator.hasNext(); method.addParameter(new Parameter(type, introspectedColumn.getJavaProperty())))
            {
                introspectedColumn = (IntrospectedColumn)iterator.next();
                type = introspectedColumn.getFullyQualifiedJavaType();
                importedTypes.add(type);
            }

        }
        FullyQualifiedJavaType fqjt;
        for(Iterator iterator1 = daoTemplate.getCheckedExceptions().iterator(); iterator1.hasNext(); importedTypes.add(fqjt))
        {
            fqjt = (FullyQualifiedJavaType)iterator1.next();
            method.addException(fqjt);
        }

        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        return method;
    }
}
