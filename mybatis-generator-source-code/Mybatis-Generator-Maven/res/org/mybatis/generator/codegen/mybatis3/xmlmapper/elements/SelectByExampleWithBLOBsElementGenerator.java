// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SelectByExampleWithBLOBsElementGenerator.java

package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.xml.*;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.util.StringUtility;

// Referenced classes of package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements:
//            AbstractXmlElementGenerator

public class SelectByExampleWithBLOBsElementGenerator extends AbstractXmlElementGenerator
{

    public SelectByExampleWithBLOBsElementGenerator()
    {
    }

    public void addElements(XmlElement parentElement)
    {
        String fqjt = introspectedTable.getExampleType();
        XmlElement answer = new XmlElement("select");
        answer.addAttribute(new Attribute("id", introspectedTable.getSelectByExampleWithBLOBsStatementId()));
        answer.addAttribute(new Attribute("resultMap", introspectedTable.getResultMapWithBLOBsId()));
        answer.addAttribute(new Attribute("parameterType", fqjt));
        context.getCommentGenerator().addComment(answer);
        answer.addElement(new TextElement("select"));
        XmlElement ifElement = new XmlElement("if");
        ifElement.addAttribute(new Attribute("test", "distinct"));
        ifElement.addElement(new TextElement("distinct"));
        answer.addElement(ifElement);
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
        answer.addElement(getExampleIncludeElement());
        ifElement = new XmlElement("if");
        ifElement.addAttribute(new Attribute("test", "orderByClause != null"));
        ifElement.addElement(new TextElement("order by $orderByClause$"));
        answer.addElement(ifElement);
        if(context.getPlugins().sqlMapSelectByExampleWithBLOBsElementGenerated(answer, introspectedTable))
            parentElement.addElement(answer);
    }
}
