// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UpdateByPrimaryKeyWithBLOBsMethodGenerator.java

package org.mybatis.generator.codegen.ibatis2.dao.elements;

import java.util.*;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.ibatis2.dao.templates.AbstractDAOTemplate;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.rules.Rules;

// Referenced classes of package org.mybatis.generator.codegen.ibatis2.dao.elements:
//            AbstractDAOElementGenerator

public class UpdateByPrimaryKeyWithBLOBsMethodGenerator extends AbstractDAOElementGenerator
{

    public UpdateByPrimaryKeyWithBLOBsMethodGenerator()
    {
    }

    public void addImplementationElements(TopLevelClass topLevelClass)
    {
        Set importedTypes = new TreeSet();
        Method method = getMethodShell(importedTypes);
        StringBuilder sb = new StringBuilder();
        sb.append("int rows = ");
        sb.append(daoTemplate.getUpdateMethod(introspectedTable.getIbatis2SqlMapNamespace(), introspectedTable.getUpdateByPrimaryKeyWithBLOBsStatementId(), "record"));
        method.addBodyLine(sb.toString());
        method.addBodyLine("return rows;");
        if(context.getPlugins().clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(method, topLevelClass, introspectedTable))
        {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    public void addInterfaceElements(Interface interfaze)
    {
        Set importedTypes = new TreeSet();
        Method method = getMethodShell(importedTypes);
        if(context.getPlugins().clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(method, interfaze, introspectedTable))
        {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }

    private Method getMethodShell(Set importedTypes)
    {
        FullyQualifiedJavaType parameterType;
        if(introspectedTable.getRules().generateRecordWithBLOBsClass())
            parameterType = new FullyQualifiedJavaType(introspectedTable.getRecordWithBLOBsType());
        else
            parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        importedTypes.add(parameterType);
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setName(getDAOMethodNameCalculator().getUpdateByPrimaryKeyWithBLOBsMethodName(introspectedTable));
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
