// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SelectByPrimaryKeyElementGenerator.java

package org.mybatis.generator.codegen.ibatis2.sqlmap.elements;

import java.util.Iterator;
import java.util.List;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.xml.*;
import org.mybatis.generator.codegen.ibatis2.Ibatis2FormattingUtilities;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.rules.Rules;
import org.mybatis.generator.internal.util.StringUtility;

// Referenced classes of package org.mybatis.generator.codegen.ibatis2.sqlmap.elements:
//            AbstractXmlElementGenerator

public class SelectByPrimaryKeyElementGenerator extends AbstractXmlElementGenerator
{

    public SelectByPrimaryKeyElementGenerator()
    {
    }

    public void addElements(XmlElement parentElement)
    {
        XmlElement answer = new XmlElement("select");
        answer.addAttribute(new Attribute("id", introspectedTable.getSelectByPrimaryKeyStatementId()));
        if(introspectedTable.getRules().generateResultMapWithBLOBs())
            answer.addAttribute(new Attribute("resultMap", introspectedTable.getResultMapWithBLOBsId()));
        else
            answer.addAttribute(new Attribute("resultMap", introspectedTable.getBaseResultMapId()));
        String parameterType;
        if(introspectedTable.getRules().generatePrimaryKeyClass())
            parameterType = introspectedTable.getPrimaryKeyType();
        else
            parameterType = introspectedTable.getBaseRecordType();
        answer.addAttribute(new Attribute("parameterClass", parameterType));
        context.getCommentGenerator().addComment(answer);
        StringBuilder sb = new StringBuilder();
        sb.append("select ");
        if(StringUtility.stringHasValue(introspectedTable.getSelectByPrimaryKeyQueryId()))
        {
            sb.append('\'');
            sb.append(introspectedTable.getSelectByPrimaryKeyQueryId());
            sb.append("' as QUERYID,");
        }
        answer.addElement(new TextElement(sb.toString()));
        answer.addElement(getBaseColumnListElement());
        if(introspectedTable.hasBLOBColumns())
        {
            answer.addElement(new TextElement(","));
            answer.addElement(getBlobColumnListElement());
        }
        sb.setLength(0);
        sb.append("from ");
        sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
        boolean and = false;
        for(Iterator iterator = introspectedTable.getPrimaryKeyColumns().iterator(); iterator.hasNext(); answer.addElement(new TextElement(sb.toString())))
        {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterator.next();
            sb.setLength(0);
            if(and)
            {
                sb.append("  and ");
            } else
            {
                sb.append("where ");
                and = true;
            }
            sb.append(Ibatis2FormattingUtilities.getAliasedEscapedColumnName(introspectedColumn));
            sb.append(" = ");
            sb.append(Ibatis2FormattingUtilities.getParameterClause(introspectedColumn));
        }

        if(context.getPlugins().sqlMapSelectByPrimaryKeyElementGenerated(answer, introspectedTable))
            parentElement.addElement(answer);
    }
}
