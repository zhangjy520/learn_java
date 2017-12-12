// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UpdateByExampleSelectiveElementGenerator.java

package org.mybatis.generator.codegen.ibatis2.sqlmap.elements;

import java.util.Iterator;
import java.util.List;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.xml.*;
import org.mybatis.generator.codegen.ibatis2.Ibatis2FormattingUtilities;
import org.mybatis.generator.config.Context;

// Referenced classes of package org.mybatis.generator.codegen.ibatis2.sqlmap.elements:
//            AbstractXmlElementGenerator

public class UpdateByExampleSelectiveElementGenerator extends AbstractXmlElementGenerator
{

    public UpdateByExampleSelectiveElementGenerator()
    {
    }

    public void addElements(XmlElement parentElement)
    {
        XmlElement answer = new XmlElement("update");
        answer.addAttribute(new Attribute("id", introspectedTable.getUpdateByExampleSelectiveStatementId()));
        context.getCommentGenerator().addComment(answer);
        StringBuilder sb = new StringBuilder();
        sb.append("update ");
        sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
        XmlElement dynamicElement = new XmlElement("dynamic");
        dynamicElement.addAttribute(new Attribute("prepend", "set"));
        answer.addElement(dynamicElement);
        XmlElement isNotNullElement;
        for(Iterator iterator = introspectedTable.getAllColumns().iterator(); iterator.hasNext(); isNotNullElement.addElement(new TextElement(sb.toString())))
        {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterator.next();
            isNotNullElement = new XmlElement("isNotNull");
            isNotNullElement.addAttribute(new Attribute("prepend", ","));
            isNotNullElement.addAttribute(new Attribute("property", introspectedColumn.getJavaProperty("record.")));
            dynamicElement.addElement(isNotNullElement);
            sb.setLength(0);
            sb.append(Ibatis2FormattingUtilities.getAliasedEscapedColumnName(introspectedColumn));
            sb.append(" = ");
            sb.append(Ibatis2FormattingUtilities.getParameterClause(introspectedColumn, "record."));
        }

        XmlElement isParameterPresentElement = new XmlElement("isParameterPresent");
        answer.addElement(isParameterPresentElement);
        XmlElement includeElement = new XmlElement("include");
        includeElement.addAttribute(new Attribute("refid", (new StringBuilder(String.valueOf(introspectedTable.getIbatis2SqlMapNamespace()))).append(".").append(introspectedTable.getExampleWhereClauseId()).toString()));
        isParameterPresentElement.addElement(includeElement);
        if(context.getPlugins().sqlMapUpdateByExampleSelectiveElementGenerated(answer, introspectedTable))
            parentElement.addElement(answer);
    }
}
