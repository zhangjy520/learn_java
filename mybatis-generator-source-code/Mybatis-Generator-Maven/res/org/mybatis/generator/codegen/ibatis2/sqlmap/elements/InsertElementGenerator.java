// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InsertElementGenerator.java

package org.mybatis.generator.codegen.ibatis2.sqlmap.elements;

import java.util.*;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.OutputUtilities;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.xml.*;
import org.mybatis.generator.codegen.ibatis2.Ibatis2FormattingUtilities;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.GeneratedKey;
import org.mybatis.generator.internal.rules.Rules;

// Referenced classes of package org.mybatis.generator.codegen.ibatis2.sqlmap.elements:
//            AbstractXmlElementGenerator

public class InsertElementGenerator extends AbstractXmlElementGenerator
{

    public InsertElementGenerator()
    {
    }

    public void addElements(XmlElement parentElement)
    {
        XmlElement answer = new XmlElement("insert");
        answer.addAttribute(new Attribute("id", introspectedTable.getInsertStatementId()));
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
        StringBuilder insertClause = new StringBuilder();
        StringBuilder valuesClause = new StringBuilder();
        insertClause.append("insert into ");
        insertClause.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
        insertClause.append(" (");
        valuesClause.append("values (");
        List valuesClauses = new ArrayList();
        for(Iterator iter = introspectedTable.getAllColumns().iterator(); iter.hasNext();)
        {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn)iter.next();
            if(!introspectedColumn.isIdentity())
            {
                insertClause.append(Ibatis2FormattingUtilities.getEscapedColumnName(introspectedColumn));
                valuesClause.append(Ibatis2FormattingUtilities.getParameterClause(introspectedColumn));
                if(iter.hasNext())
                {
                    insertClause.append(", ");
                    valuesClause.append(", ");
                }
                if(valuesClause.length() > 80)
                {
                    answer.addElement(new TextElement(insertClause.toString()));
                    insertClause.setLength(0);
                    OutputUtilities.xmlIndent(insertClause, 1);
                    valuesClauses.add(valuesClause.toString());
                    valuesClause.setLength(0);
                    OutputUtilities.xmlIndent(valuesClause, 1);
                }
            }
        }

        insertClause.append(')');
        answer.addElement(new TextElement(insertClause.toString()));
        valuesClause.append(')');
        valuesClauses.add(valuesClause.toString());
        String clause;
        for(Iterator iterator = valuesClauses.iterator(); iterator.hasNext(); answer.addElement(new TextElement(clause)))
            clause = (String)iterator.next();

        if(gk != null && !gk.isBeforeInsert())
        {
            IntrospectedColumn introspectedColumn = introspectedTable.getColumn(gk.getColumn());
            if(introspectedColumn != null)
                answer.addElement(getSelectKey(introspectedColumn, gk));
        }
        if(context.getPlugins().sqlMapInsertElementGenerated(answer, introspectedTable))
            parentElement.addElement(answer);
    }
}
