// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UpdateByExampleParmsInnerclassGenerator.java

package org.mybatis.generator.codegen.ibatis2.dao.elements;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.config.Context;

// Referenced classes of package org.mybatis.generator.codegen.ibatis2.dao.elements:
//            AbstractDAOElementGenerator

public class UpdateByExampleParmsInnerclassGenerator extends AbstractDAOElementGenerator
{

    public UpdateByExampleParmsInnerclassGenerator()
    {
    }

    public void addImplementationElements(TopLevelClass topLevelClass)
    {
        topLevelClass.addImportedType(new FullyQualifiedJavaType(introspectedTable.getExampleType()));
        InnerClass innerClass = new InnerClass(new FullyQualifiedJavaType("UpdateByExampleParms"));
        innerClass.setVisibility(JavaVisibility.PROTECTED);
        innerClass.setStatic(true);
        innerClass.setSuperClass(introspectedTable.getExampleType());
        context.getCommentGenerator().addClassComment(innerClass, introspectedTable);
        Method method = new Method();
        method.setConstructor(true);
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName(innerClass.getType().getShortName());
        method.addParameter(new Parameter(FullyQualifiedJavaType.getObjectInstance(), "record"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getExampleType()), "example"));
        method.addBodyLine("super(example);");
        method.addBodyLine("this.record = record;");
        innerClass.addMethod(method);
        Field field = new Field();
        field.setVisibility(JavaVisibility.PRIVATE);
        field.setType(FullyQualifiedJavaType.getObjectInstance());
        field.setName("record");
        innerClass.addField(field);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getObjectInstance());
        method.setName("getRecord");
        method.addBodyLine("return record;");
        innerClass.addMethod(method);
        topLevelClass.addInnerClass(innerClass);
    }

    public void addInterfaceElements(Interface interface1)
    {
    }
}
