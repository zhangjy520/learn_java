// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SelectByExampleWithoutBLOBsMethodGenerator.java

package org.mybatis.generator.codegen.mybatis3.javamapper.elements;

import java.util.Set;
import java.util.TreeSet;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.rules.Rules;
import org.mybatis.generator.internal.util.messages.Messages;

// Referenced classes of package org.mybatis.generator.codegen.mybatis3.javamapper.elements:
//            AbstractJavaMapperMethodGenerator

public class SelectByExampleWithoutBLOBsMethodGenerator extends AbstractJavaMapperMethodGenerator
{

    public SelectByExampleWithoutBLOBsMethodGenerator()
    {
    }

    public void addInterfaceElements(Interface interfaze)
    {
        Set importedTypes = new TreeSet();
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getExampleType());
        importedTypes.add(type);
        importedTypes.add(FullyQualifiedJavaType.getNewListInstance());
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getNewListInstance();
        FullyQualifiedJavaType listType;
        if(introspectedTable.getRules().generateBaseRecordClass())
            listType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        else
        if(introspectedTable.getRules().generatePrimaryKeyClass())
            listType = new FullyQualifiedJavaType(introspectedTable.getPrimaryKeyType());
        else
            throw new RuntimeException(Messages.getString("RuntimeError.12"));
        importedTypes.add(listType);
        returnType.addTypeArgument(listType);
        method.setReturnType(returnType);
        method.setName(introspectedTable.getSelectByExampleStatementId());
        method.addParameter(new Parameter(type, "example"));
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        if(context.getPlugins().clientSelectByExampleWithoutBLOBsMethodGenerated(method, interfaze, introspectedTable))
        {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }
}
