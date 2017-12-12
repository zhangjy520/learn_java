// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SelectByExampleWithBLOBsMethodGenerator.java

package org.mybatis.generator.codegen.ibatis2.dao.elements;

import java.util.*;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.ibatis2.dao.templates.AbstractDAOTemplate;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.rules.Rules;

// Referenced classes of package org.mybatis.generator.codegen.ibatis2.dao.elements:
//            AbstractDAOElementGenerator

public class SelectByExampleWithBLOBsMethodGenerator extends AbstractDAOElementGenerator
{

    public SelectByExampleWithBLOBsMethodGenerator(boolean generateForJava5)
    {
        this.generateForJava5 = generateForJava5;
    }

    public void addImplementationElements(TopLevelClass topLevelClass)
    {
        Set importedTypes = new TreeSet();
        Method method = getMethodShell(importedTypes);
        if(generateForJava5)
            method.addSuppressTypeWarningsAnnotation();
        StringBuilder sb = new StringBuilder();
        sb.append(method.getReturnType().getShortName());
        sb.append(" list = ");
        sb.append(daoTemplate.getQueryForListMethod(introspectedTable.getIbatis2SqlMapNamespace(), introspectedTable.getSelectByExampleWithBLOBsStatementId(), "example"));
        method.addBodyLine(sb.toString());
        method.addBodyLine("return list;");
        if(context.getPlugins().clientSelectByExampleWithBLOBsMethodGenerated(method, topLevelClass, introspectedTable))
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
            if(context.getPlugins().clientSelectByExampleWithBLOBsMethodGenerated(method, interfaze, introspectedTable))
            {
                interfaze.addImportedTypes(importedTypes);
                interfaze.addMethod(method);
            }
        }
    }

    private Method getMethodShell(Set importedTypes)
    {
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getExampleType());
        importedTypes.add(type);
        importedTypes.add(FullyQualifiedJavaType.getNewListInstance());
        Method method = new Method();
        method.setVisibility(getExampleMethodVisibility());
        FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getNewListInstance();
        if(generateForJava5)
        {
            FullyQualifiedJavaType fqjt;
            if(introspectedTable.getRules().generateRecordWithBLOBsClass())
                fqjt = new FullyQualifiedJavaType(introspectedTable.getRecordWithBLOBsType());
            else
                fqjt = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
            importedTypes.add(fqjt);
            returnType.addTypeArgument(fqjt);
        }
        method.setReturnType(returnType);
        method.setName(getDAOMethodNameCalculator().getSelectByExampleWithBLOBsMethodName(introspectedTable));
        method.addParameter(new Parameter(type, "example"));
        FullyQualifiedJavaType fqjt;
        for(Iterator iterator = daoTemplate.getCheckedExceptions().iterator(); iterator.hasNext(); importedTypes.add(fqjt))
        {
            fqjt = (FullyQualifiedJavaType)iterator.next();
            method.addException(fqjt);
        }

        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        return method;
    }

    private boolean generateForJava5;
}
