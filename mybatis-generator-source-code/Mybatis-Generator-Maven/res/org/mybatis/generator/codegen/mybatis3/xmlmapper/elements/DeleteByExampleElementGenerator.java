// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeleteByExampleElementGenerator.java

package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.xml.*;
import org.mybatis.generator.config.Context;

// Referenced classes of package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements:
//            AbstractXmlElementGenerator

public class DeleteByExampleElementGenerator extends AbstractXmlElementGenerator
{

    public DeleteByExampleElementGenerator()
    {
    }

    public void addElements(XmlElement parentElement)
    {
        XmlElement answer = new XmlElement("delete");
        String fqjt = introspectedTable.getExampleType();
        answer.addAttribute(new Attribute("id", introspectedTable.getDeleteByExampleStatementId()));
        answer.addAttribute(new Attribute("parameterType", fqjt));
        context.getCommentGenerator().addComment(answer);
        StringBuilder sb = new StringBuilder();
        sb.append("delete from ");
        sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
        answer.addElement(getExampleIncludeElement());
        if(context.getPlugins().sqlMapDeleteByExampleElementGenerated(answer, introspectedTable))
            parentElement.addElement(answer);
    }
}
