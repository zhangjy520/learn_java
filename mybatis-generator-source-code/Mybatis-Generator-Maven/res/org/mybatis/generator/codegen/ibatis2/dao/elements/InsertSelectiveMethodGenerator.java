// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InsertSelectiveMethodGenerator.java

package org.mybatis.generator.codegen.ibatis2.dao.elements;

import java.util.*;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.ibatis2.dao.templates.AbstractDAOTemplate;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.GeneratedKey;
import org.mybatis.generator.internal.rules.Rules;

// Referenced classes of package org.mybatis.generator.codegen.ibatis2.dao.elements:
//            AbstractDAOElementGenerator

public class InsertSelectiveMethodGenerator extends AbstractDAOElementGenerator
{

    public InsertSelectiveMethodGenerator()
    {
    }

    public void addImplementationElements(TopLevelClass topLevelClass)
    {
        Set importedTypes = new TreeSet();
        Method method = getMethodShell(importedTypes);
        FullyQualifiedJavaType returnType = method.getReturnType();
        StringBuilder sb = new StringBuilder();
        if(returnType != null)
            sb.append("Object newKey = ");
        sb.append(daoTemplate.getInsertMethod(introspectedTable.getIbatis2SqlMapNamespace(), introspectedTable.getInsertSelectiveStatementId(), "record"));
        method.addBodyLine(sb.toString());
        if(returnType != null)
            if("Object".equals(returnType.getShortName()))
            {
                method.addBodyLine("return newKey;");
            } else
            {
                sb.setLength(0);
                if(returnType.isPrimitive())
                {
                    PrimitiveTypeWrapper ptw = returnType.getPrimitiveTypeWrapper();
                    sb.append("return ((");
                    sb.append(ptw.getShortName());
                    sb.append(") newKey");
                    sb.append(").");
                    sb.append(ptw.getToPrimitiveMethod());
                    sb.append(';');
                } else
                {
                    sb.append("return (");
                    sb.append(returnType.getShortName());
                    sb.append(") newKey;");
                }
                method.addBodyLine(sb.toString());
            }
        if(context.getPlugins().clientInsertSelectiveMethodGenerated(method, topLevelClass, introspectedTable))
        {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    public void addInterfaceElements(Interface interfaze)
    {
        Set importedTypes = new TreeSet();
        Method method = getMethodShell(importedTypes);
        if(context.getPlugins().clientInsertSelectiveMethodGenerated(method, interfaze, introspectedTable))
        {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }

    private Method getMethodShell(Set importedTypes)
    {
        Method method = new Method();
        FullyQualifiedJavaType returnType;
        if(introspectedTable.getGeneratedKey() != null)
        {
            IntrospectedColumn introspectedColumn = introspectedTable.getColumn(introspectedTable.getGeneratedKey().getColumn());
            if(introspectedColumn == null)
            {
                returnType = null;
            } else
            {
                returnType = introspectedColumn.getFullyQualifiedJavaType();
                importedTypes.add(returnType);
            }
        } else
        {
            returnType = null;
        }
        method.setReturnType(returnType);
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName(getDAOMethodNameCalculator().getInsertSelectiveMethodName(introspectedTable));
        FullyQualifiedJavaType parameterType = introspectedTable.getRules().calculateAllFieldsClass();
        importedTypes.add(parameterType);
        method.addParameter(new Parameter(parameterType, "record"));
        FullyQualifiedJavaType fqjt;
        for(Iterator iterator = daoTemplate.getCheckedExceptions().iterator(); iterator.hasNext(); importedTypes.add(fqjt))
        {
            fqjt = (FullyQualifiedJavaType)iterator.next();
            method.addException(fqjt);
        }

        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        return method;
    }
}
