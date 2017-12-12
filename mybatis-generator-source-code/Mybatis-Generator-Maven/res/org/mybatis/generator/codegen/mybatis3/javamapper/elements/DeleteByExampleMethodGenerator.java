// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeleteByExampleMethodGenerator.java

package org.mybatis.generator.codegen.mybatis3.javamapper.elements;

import java.util.Set;
import java.util.TreeSet;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.config.Context;

// Referenced classes of package org.mybatis.generator.codegen.mybatis3.javamapper.elements:
//            AbstractJavaMapperMethodGenerator

public class DeleteByExampleMethodGenerator extends AbstractJavaMapperMethodGenerator
{

    public DeleteByExampleMethodGenerator()
    {
    }

    public void addInterfaceElements(Interface interfaze)
    {
        Set importedTypes = new TreeSet();
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getExampleType());
        importedTypes.add(type);
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setName(introspectedTable.getDeleteByExampleStatementId());
        method.addParameter(new Parameter(type, "example"));
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        if(context.getPlugins().clientDeleteByExampleMethodGenerated(method, interfaze, introspectedTable))
        {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }
}
