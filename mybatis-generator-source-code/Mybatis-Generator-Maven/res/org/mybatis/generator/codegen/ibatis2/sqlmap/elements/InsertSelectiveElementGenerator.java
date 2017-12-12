// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InsertSelectiveElementGenerator.java

package org.mybatis.generator.codegen.ibatis2.sqlmap.elements;

import java.util.Iterator;
import java.util.List;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.xml.*;
import org.mybatis.generator.codegen.ibatis2.Ibatis2FormattingUtilities;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.GeneratedKey;
import org.mybatis.generator.internal.rules.Rules;

// Referenced classes of package org.mybatis.generator.codegen.ibatis2.sqlmap.elements:
//            AbstractXmlElementGenerator

public class InsertSelectiveElementGenerator extends AbstractXmlElementGenerator
{

    public InsertSelectiveElementGenerator()
    {
    }

    public void addElements(XmlElement parentElement)
    {
        XmlElement answer = new XmlElement("insert");
        answer.addAttribute(new Attribute("id", introspectedTable.getInsertSelectiveStatementId()));
        FullyQualifiedJavaType parameterType = introspectedTable.getRules().calculateAllFieldsClass();
        answer.addAttribute(new Attribute("parameterClass", parameterType.getFullyQualifiedName()));
        context.getCommentGenerator().addComment(answer);
        GeneratedKey gk = introspectedTable.getGeneratedKey();
        if(gk != null && gk.isBeforeInsert())
        {
            IntrospectedColumn introspectedColumn = introspectedTable.getColumn(gk.getColumn());
            if(introspectedColumn != null)
                answer.addElement(getSelectKey(introspectedColumn, gk));
        }
        StringBuilder sb = new StringBuilder();
        sb.append("insert into ");
        sb.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
        XmlElement insertElement = new XmlElement("dynamic");
        insertElement.addAttribute(new Attribute("prepend", "("));
        answer.addElement(insertElement);
        answer.addElement(new TextElement("values"));
        XmlElement valuesElement = new XmlElement("dynamic");
        valuesElement.addAttribute(new Attribute("prepend", "("));
        answer.addElement(valuesElement);
        for(Iterator iterator = introspectedTable.getAllColumns().iterator(); iterator.hasNext();)
        {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterator.next();
            if(!introspectedColumn.isIdentity())
            {
                XmlElement insertNotNullElement = new XmlElement("isNotNull");
                insertNotNullElement.addAttribute(new Attribute("prepend", ","));
                insertNotNullElement.addAttribute(new Attribute("property", introspectedColumn.getJavaProperty()));
                insertNotNullElement.addElement(new TextElement(Ibatis2FormattingUtilities.getEscapedColumnName(introspectedColumn)));
                insertElement.addElement(insertNotNullElement);
                XmlElement valuesNotNullElement = new XmlElement("isNotNull");
                valuesNotNullElement.addAttribute(new Attribute("prepend", ","));
                valuesNotNullElement.addAttribute(new Attribute("property", introspectedColumn.getJavaProperty()));
                valuesNotNullElement.addElement(new TextElement(Ibatis2FormattingUtilities.getParameterClause(introspectedColumn)));
                valuesElement.addElement(valuesNotNullElement);
            }
        }

        insertElement.addElement(new TextElement(")"));
        valuesElement.addElement(new TextElement(")"));
        if(gk != null && !gk.isBeforeInsert())
        {
            IntrospectedColumn introspectedColumn = introspectedTable.getColumn(gk.getColumn());
            if(introspectedColumn != null)
                answer.addElement(getSelectKey(introspectedColumn, gk));
        }
        if(context.getPlugins().sqlMapInsertSelectiveElementGenerated(answer, introspectedTable))
            parentElement.addElement(answer);
    }
}
