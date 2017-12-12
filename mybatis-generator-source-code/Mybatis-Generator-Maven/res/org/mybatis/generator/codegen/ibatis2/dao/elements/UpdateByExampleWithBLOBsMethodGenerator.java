// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UpdateByExampleWithBLOBsMethodGenerator.java

package org.mybatis.generator.codegen.ibatis2.dao.elements;

import java.util.*;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.ibatis2.dao.templates.AbstractDAOTemplate;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.rules.Rules;

// Referenced classes of package org.mybatis.generator.codegen.ibatis2.dao.elements:
//            AbstractDAOElementGenerator

public class UpdateByExampleWithBLOBsMethodGenerator extends AbstractDAOElementGenerator
{

    public UpdateByExampleWithBLOBsMethodGenerator()
    {
    }

    public void addImplementationElements(TopLevelClass topLevelClass)
    {
        Set importedTypes = new TreeSet();
        Method method = getMethodShell(importedTypes);
        method.addBodyLine("UpdateByExampleParms parms = new UpdateByExampleParms(record, example);");
        StringBuilder sb = new StringBuilder();
        sb.append("int rows = ");
        sb.append(daoTemplate.getUpdateMethod(introspectedTable.getIbatis2SqlMapNamespace(), introspectedTable.getUpdateByExampleWithBLOBsStatementId(), "parms"));
        method.addBodyLine(sb.toString());
        method.addBodyLine("return rows;");
        if(context.getPlugins().clientUpdateByExampleWithBLOBsMethodGenerated(method, topLevelClass, introspectedTable))
        {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    public void addInterfaceElements(Interface interfaze)
    {
        if(getExampleMethodVisibility() == JavaVisibility.PUBLIC)
        {
            Set importedTypes = new TreeSet();
            Method method = getMethodShell(importedTypes);
            if(context.getPlugins().clientUpdateByExampleWithBLOBsMethodGenerated(method, interfaze, introspectedTable))
            {
                interfaze.addImportedTypes(importedTypes);
                interfaze.addMethod(method);
            }
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
        method.setVisibility(getExampleMethodVisibility());
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setName(getDAOMethodNameCalculator().getUpdateByExampleWithBLOBsMethodName(introspectedTable));
        method.addParameter(new Parameter(parameterType, "record"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getExampleType()), "example"));
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
