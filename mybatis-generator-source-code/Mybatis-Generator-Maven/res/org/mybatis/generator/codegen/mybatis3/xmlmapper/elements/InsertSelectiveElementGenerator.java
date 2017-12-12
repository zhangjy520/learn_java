// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InsertSelectiveElementGenerator.java

package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import java.util.Iterator;
import java.util.List;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.xml.*;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.GeneratedKey;
import org.mybatis.generator.internal.rules.Rules;

// Referenced classes of package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements:
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
        answer.addAttribute(new Attribute("parameterType", parameterType.getFullyQualifiedName()));
        context.getCommentGenerator().addComment(answer);
        GeneratedKey gk = introspectedTable.getGeneratedKey();
        String sequenceColumn = null;
        if(gk != null && gk.isBeforeInsert())
        {
            IntrospectedColumn introspectedColumn = introspectedTable.getColumn(gk.getColumn());
            if(introspectedColumn != null)
            {
                answer.addElement(getSelectKey(introspectedColumn, gk));
                sequenceColumn = gk.getColumn();
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("insert into ");
        sb.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
        XmlElement insertTrimElement = new XmlElement("trim");
        insertTrimElement.addAttribute(new Attribute("prefix", "("));
        insertTrimElement.addAttribute(new Attribute("suffix", ")"));
        insertTrimElement.addAttribute(new Attribute("suffixOverrides", ","));
        answer.addElement(insertTrimElement);
        XmlElement valuesTrimElement = new XmlElement("trim");
        valuesTrimElement.addAttribute(new Attribute("prefix", "values ("));
        valuesTrimElement.addAttribute(new Attribute("suffix", ")"));
        valuesTrimElement.addAttribute(new Attribute("suffixOverrides", ","));
        answer.addElement(valuesTrimElement);
        for(Iterator iterator = introspectedTable.getAllColumns().iterator(); iterator.hasNext();)
        {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterator.next();
            if(!introspectedColumn.isIdentity())
            {
                boolean isSequenceColumn = sequenceColumn != null ? sequenceColumn.equals(introspectedColumn.getActualColumnName()) : false;
                if(isSequenceColumn)
                {
                    sb.setLength(0);
                    sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
                    sb.append(',');
                    insertTrimElement.addElement(new TextElement(sb.toString()));
                    sb.setLength(0);
                    sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));
                    sb.append(',');
                    valuesTrimElement.addElement(new TextElement(sb.toString()));
                } else
                {
                    XmlElement insertNotNullElement = new XmlElement("if");
                    sb.setLength(0);
                    sb.append(introspectedColumn.getJavaProperty());
                    sb.append(" != null");
                    insertNotNullElement.addAttribute(new Attribute("test", sb.toString()));
                    sb.setLength(0);
                    sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
                    sb.append(',');
                    insertNotNullElement.addElement(new TextElement(sb.toString()));
                    insertTrimElement.addElement(insertNotNullElement);
                    XmlElement valuesNotNullElement = new XmlElement("if");
                    sb.setLength(0);
                    sb.append(introspectedColumn.getJavaProperty());
                    sb.append(" != null");
                    valuesNotNullElement.addAttribute(new Attribute("test", sb.toString()));
                    sb.setLength(0);
                    sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));
                    sb.append(',');
                    valuesNotNullElement.addElement(new TextElement(sb.toString()));
                    valuesTrimElement.addElement(valuesNotNullElement);
                }
            }
        }

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
