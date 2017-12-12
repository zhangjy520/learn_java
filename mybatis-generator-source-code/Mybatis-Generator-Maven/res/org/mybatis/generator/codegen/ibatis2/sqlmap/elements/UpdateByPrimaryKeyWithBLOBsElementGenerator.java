// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UpdateByPrimaryKeyWithBLOBsElementGenerator.java

package org.mybatis.generator.codegen.ibatis2.sqlmap.elements;

import java.util.Iterator;
import java.util.List;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.OutputUtilities;
import org.mybatis.generator.api.dom.xml.*;
import org.mybatis.generator.codegen.ibatis2.Ibatis2FormattingUtilities;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.rules.Rules;

// Referenced classes of package org.mybatis.generator.codegen.ibatis2.sqlmap.elements:
//            AbstractXmlElementGenerator

public class UpdateByPrimaryKeyWithBLOBsElementGenerator extends AbstractXmlElementGenerator
{

    public UpdateByPrimaryKeyWithBLOBsElementGenerator()
    {
    }

    public void addElements(XmlElement parentElement)
    {
        XmlElement answer = new XmlElement("update");
        answer.addAttribute(new Attribute("id", introspectedTable.getUpdateByPrimaryKeyWithBLOBsStatementId()));
        String parameterType;
        if(introspectedTable.getRules().generateRecordWithBLOBsClass())
            parameterType = introspectedTable.getRecordWithBLOBsType();
        else
            parameterType = introspectedTable.getBaseRecordType();
        answer.addAttribute(new Attribute("parameterClass", parameterType));
        context.getCommentGenerator().addComment(answer);
        StringBuilder sb = new StringBuilder();
        sb.append("update ");
        sb.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
        sb.setLength(0);
        sb.append("set ");
        for(Iterator iter = introspectedTable.getNonPrimaryKeyColumns().iterator(); iter.hasNext();)
        {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn)iter.next();
            sb.append(Ibatis2FormattingUtilities.getEscapedColumnName(introspectedColumn));
            sb.append(" = ");
            sb.append(Ibatis2FormattingUtilities.getParameterClause(introspectedColumn));
            if(iter.hasNext())
                sb.append(',');
            answer.addElement(new TextElement(sb.toString()));
            if(iter.hasNext())
            {
                sb.setLength(0);
                OutputUtilities.xmlIndent(sb, 1);
            }
        }

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

        if(context.getPlugins().sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(answer, introspectedTable))
            parentElement.addElement(answer);
    }
}
