// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UpdateByPrimaryKeySelectiveMethodGenerator.java

package org.mybatis.generator.codegen.mybatis3.javamapper.elements;

import java.util.Set;
import java.util.TreeSet;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.rules.Rules;

// Referenced classes of package org.mybatis.generator.codegen.mybatis3.javamapper.elements:
//            AbstractJavaMapperMethodGenerator

public class UpdateByPrimaryKeySelectiveMethodGenerator extends AbstractJavaMapperMethodGenerator
{

    public UpdateByPrimaryKeySelectiveMethodGenerator()
    {
    }

    public void addInterfaceElements(Interface interfaze)
    {
        Set importedTypes = new TreeSet();
        FullyQualifiedJavaType parameterType;
        if(introspectedTable.getRules().generateRecordWithBLOBsClass())
            parameterType = new FullyQualifiedJavaType(introspectedTable.getRecordWithBLOBsType());
        else
            parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        importedTypes.add(parameterType);
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setName(introspectedTable.getUpdateByPrimaryKeySelectiveStatementId());
        method.addParameter(new Parameter(parameterType, "record"));
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        if(context.getPlugins().clientUpdateByPrimaryKeySelectiveMethodGenerated(method, interfaze, introspectedTable))
        {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }
}
