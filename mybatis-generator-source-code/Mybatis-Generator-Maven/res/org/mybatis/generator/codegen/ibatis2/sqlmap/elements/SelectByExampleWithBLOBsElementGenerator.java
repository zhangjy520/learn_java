// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SelectByExampleWithBLOBsElementGenerator.java

package org.mybatis.generator.codegen.ibatis2.sqlmap.elements;

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.xml.*;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.util.StringUtility;

// Referenced classes of package org.mybatis.generator.codegen.ibatis2.sqlmap.elements:
//            AbstractXmlElementGenerator

public class SelectByExampleWithBLOBsElementGenerator extends AbstractXmlElementGenerator
{

    public SelectByExampleWithBLOBsElementGenerator()
    {
    }

    public void addElements(XmlElement parentElement)
    {
        XmlElement answer = new XmlElement("select");
        answer.addAttribute(new Attribute("id", introspectedTable.getSelectByExampleWithBLOBsStatementId()));
        answer.addAttribute(new Attribute("resultMap", introspectedTable.getResultMapWithBLOBsId()));
        answer.addAttribute(new Attribute("parameterClass", introspectedTable.getExampleType()));
        context.getCommentGenerator().addComment(answer);
        answer.addElement(new TextElement("select"));
        XmlElement isParameterPresent = new XmlElement("isParameterPresent");
        XmlElement isEqualElement = new XmlElement("isEqual");
        isEqualElement.addAttribute(new Attribute("property", "distinct"));
        isEqualElement.addAttribute(new Attribute("compareValue", "true"));
        isEqualElement.addElement(new TextElement("distinct"));
        isParameterPresent.addElement(isEqualElement);
        answer.addElement(isParameterPresent);
        StringBuilder sb = new StringBuilder();
        if(StringUtility.stringHasValue(introspectedTable.getSelectByExampleQueryId()))
        {
            sb.append('\'');
            sb.append(introspectedTable.getSelectByExampleQueryId());
            sb.append("' as QUERYID,");
            answer.addElement(new TextElement(sb.toString()));
        }
        answer.addElement(getBaseColumnListElement());
        answer.addElement(new TextElement(","));
        answer.addElement(getBlobColumnListElement());
        sb.setLength(0);
        sb.append("from ");
        sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
        XmlElement isParameterPresenteElement = new XmlElement("isParameterPresent");
        answer.addElement(isParameterPresenteElement);
        XmlElement includeElement = new XmlElement("include");
        includeElement.addAttribute(new Attribute("refid", (new StringBuilder(String.valueOf(introspectedTable.getIbatis2SqlMapNamespace()))).append(".").append(introspectedTable.getExampleWhereClauseId()).toString()));
        isParameterPresenteElement.addElement(includeElement);
        XmlElement isNotNullElement = new XmlElement("isNotNull");
        isNotNullElement.addAttribute(new Attribute("property", "orderByClause"));
        isNotNullElement.addElement(new TextElement("order by $orderByClause$"));
        isParameterPresenteElement.addElement(isNotNullElement);
        if(context.getPlugins().sqlMapSelectByExampleWithBLOBsElementGenerated(answer, introspectedTable))
            parentElement.addElement(answer);
    }
}
