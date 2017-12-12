// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeleteByPrimaryKeyElementGenerator.java

package org.mybatis.generator.codegen.ibatis2.sqlmap.elements;

import java.util.Iterator;
import java.util.List;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.xml.*;
import org.mybatis.generator.codegen.ibatis2.Ibatis2FormattingUtilities;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.rules.Rules;

// Referenced classes of package org.mybatis.generator.codegen.ibatis2.sqlmap.elements:
//            AbstractXmlElementGenerator

public class DeleteByPrimaryKeyElementGenerator extends AbstractXmlElementGenerator
{

    public DeleteByPrimaryKeyElementGenerator()
    {
    }

    public void addElements(XmlElement parentElement)
    {
        XmlElement answer = new XmlElement("delete");
        answer.addAttribute(new Attribute("id", introspectedTable.getDeleteByPrimaryKeyStatementId()));
        String parameterClass;
        if(introspectedTable.getRules().generatePrimaryKeyClass())
            parameterClass = introspectedTable.getPrimaryKeyType();
        else
            parameterClass = introspectedTable.getBaseRecordType();
        answer.addAttribute(new Attribute("parameterClass", parameterClass));
        context.getCommentGenerator().addComment(answer);
        StringBuilder sb = new StringBuilder();
        sb.append("delete from ");
        sb.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
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
            sb.append(Ibatis2FormattingUtilities.getEscapedColumnName(introspectedColumn));
            sb.append(" = ");
            sb.append(Ibatis2FormattingUtilities.getParameterClause(introspectedColumn));
        }

        if(context.getPlugins().sqlMapDeleteByPrimaryKeyElementGenerated(answer, introspectedTable))
            parentElement.addElement(answer);
    }
}
