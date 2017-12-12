// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeleteByPrimaryKeyMethodGenerator.java

package org.mybatis.generator.codegen.mybatis3.javamapper.elements;

import java.util.*;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.rules.Rules;

// Referenced classes of package org.mybatis.generator.codegen.mybatis3.javamapper.elements:
//            AbstractJavaMapperMethodGenerator

public class DeleteByPrimaryKeyMethodGenerator extends AbstractJavaMapperMethodGenerator
{

    public DeleteByPrimaryKeyMethodGenerator()
    {
    }

    public void addInterfaceElements(Interface interfaze)
    {
        Set importedTypes = new TreeSet();
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setName(introspectedTable.getDeleteByPrimaryKeyStatementId());
        if(introspectedTable.getRules().generatePrimaryKeyClass())
        {
            FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getPrimaryKeyType());
            importedTypes.add(type);
            method.addParameter(new Parameter(type, "key"));
        } else
        {
            List introspectedColumns = introspectedTable.getPrimaryKeyColumns();
            boolean annotate = introspectedColumns.size() > 1;
            if(annotate)
                importedTypes.add(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param"));
            StringBuilder sb = new StringBuilder();
            Parameter parameter;
            for(Iterator iterator = introspectedColumns.iterator(); iterator.hasNext(); method.addParameter(parameter))
            {
                IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterator.next();
                FullyQualifiedJavaType type = introspectedColumn.getFullyQualifiedJavaType();
                importedTypes.add(type);
                parameter = new Parameter(type, introspectedColumn.getJavaProperty());
                if(annotate)
                {
                    sb.setLength(0);
                    sb.append("@Param(\"");
                    sb.append(introspectedColumn.getJavaProperty());
                    sb.append("\")");
                    parameter.addAnnotation(sb.toString());
                }
            }

        }
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        if(context.getPlugins().clientDeleteByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable))
        {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }
}
