// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CountByExampleElementGenerator.java

package org.mybatis.generator.codegen.ibatis2.sqlmap.elements;

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.xml.*;
import org.mybatis.generator.config.Context;

// Referenced classes of package org.mybatis.generator.codegen.ibatis2.sqlmap.elements:
//            AbstractXmlElementGenerator

public class CountByExampleElementGenerator extends AbstractXmlElementGenerator
{

    public CountByExampleElementGenerator()
    {
    }

    public void addElements(XmlElement parentElement)
    {
        XmlElement answer = new XmlElement("select");
        answer.addAttribute(new Attribute("id", introspectedTable.getCountByExampleStatementId()));
        answer.addAttribute(new Attribute("parameterClass", introspectedTable.getExampleType()));
        answer.addAttribute(new Attribute("resultClass", "java.lang.Integer"));
        context.getCommentGenerator().addComment(answer);
        StringBuilder sb = new StringBuilder();
        sb.append("select count(*) from ");
        sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
        XmlElement includeElement = new XmlElement("include");
        sb.setLength(0);
        sb.append(introspectedTable.getIbatis2SqlMapNamespace());
        sb.append('.');
        sb.append(introspectedTable.getExampleWhereClauseId());
        includeElement.addAttribute(new Attribute("refid", sb.toString()));
        answer.addElement(includeElement);
        if(context.getPlugins().sqlMapCountByExampleElementGenerated(answer, introspectedTable))
            parentElement.addElement(answer);
    }
}
