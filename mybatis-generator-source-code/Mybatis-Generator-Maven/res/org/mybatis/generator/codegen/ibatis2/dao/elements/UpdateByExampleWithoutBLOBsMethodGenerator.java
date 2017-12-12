// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UpdateByExampleWithoutBLOBsMethodGenerator.java

package org.mybatis.generator.codegen.ibatis2.dao.elements;

import java.util.*;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.ibatis2.dao.templates.AbstractDAOTemplate;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.rules.Rules;

// Referenced classes of package org.mybatis.generator.codegen.ibatis2.dao.elements:
//            AbstractDAOElementGenerator

public class UpdateByExampleWithoutBLOBsMethodGenerator extends AbstractDAOElementGenerator
{

    public UpdateByExampleWithoutBLOBsMethodGenerator()
    {
    }

    public void addImplementationElements(TopLevelClass topLevelClass)
    {
        Set importedTypes = new TreeSet();
        Method method = getMethodShell(importedTypes);
        method.addBodyLine("UpdateByExampleParms parms = new UpdateByExampleParms(record, example);");
        StringBuilder sb = new StringBuilder();
        sb.append("int rows = ");
        sb.append(daoTemplate.getUpdateMethod(introspectedTable.getIbatis2SqlMapNamespace(), introspectedTable.getUpdateByExampleStatementId(), "parms"));
        method.addBodyLine(sb.toString());
        method.addBodyLine("return rows;");
        if(context.getPlugins().clientUpdateByExampleWithoutBLOBsMethodGenerated(method, topLevelClass, introspectedTable))
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
            if(context.getPlugins().clientUpdateByExampleWithoutBLOBsMethodGenerated(method, interfaze, introspectedTable))
            {
                interfaze.addImportedTypes(importedTypes);
                interfaze.addMethod(method);
            }
        }
    }

    private Method getMethodShell(Set importedTypes)
    {
        FullyQualifiedJavaType parameterType;
        if(introspectedTable.getRules().generateBaseRecordClass())
            parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        else
            parameterType = new FullyQualifiedJavaType(introspectedTable.getPrimaryKeyType());
        importedTypes.add(parameterType);
        Method method = new Method();
        method.setVisibility(getExampleMethodVisibility());
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setName(getDAOMethodNameCalculator().getUpdateByExampleWithoutBLOBsMethodName(introspectedTable));
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
