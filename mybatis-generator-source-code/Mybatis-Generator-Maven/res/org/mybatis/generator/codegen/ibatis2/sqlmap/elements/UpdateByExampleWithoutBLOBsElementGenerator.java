// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UpdateByExampleWithoutBLOBsElementGenerator.java

package org.mybatis.generator.codegen.ibatis2.sqlmap.elements;

import java.util.Iterator;
import java.util.List;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.OutputUtilities;
import org.mybatis.generator.api.dom.xml.*;
import org.mybatis.generator.codegen.ibatis2.Ibatis2FormattingUtilities;
import org.mybatis.generator.config.Context;

// Referenced classes of package org.mybatis.generator.codegen.ibatis2.sqlmap.elements:
//            AbstractXmlElementGenerator

public class UpdateByExampleWithoutBLOBsElementGenerator extends AbstractXmlElementGenerator
{

    public UpdateByExampleWithoutBLOBsElementGenerator()
    {
    }

    public void addElements(XmlElement parentElement)
    {
        XmlElement answer = new XmlElement("update");
        answer.addAttribute(new Attribute("id", introspectedTable.getUpdateByExampleStatementId()));
        context.getCommentGenerator().addComment(answer);
        StringBuilder sb = new StringBuilder();
        sb.append("update ");
        sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
        sb.setLength(0);
        sb.append("set ");
        for(Iterator iter = introspectedTable.getNonBLOBColumns().iterator(); iter.hasNext();)
        {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn)iter.next();
            sb.append(Ibatis2FormattingUtilities.getAliasedEscapedColumnName(introspectedColumn));
            sb.append(" = ");
            sb.append(Ibatis2FormattingUtilities.getParameterClause(introspectedColumn, "record."));
            if(iter.hasNext())
                sb.append(',');
            answer.addElement(new TextElement(sb.toString()));
            if(iter.hasNext())
            {
                sb.setLength(0);
                OutputUtilities.xmlIndent(sb, 1);
            }
        }

        XmlElement isParameterPresentElement = new XmlElement("isParameterPresent");
        answer.addElement(isParameterPresentElement);
        XmlElement includeElement = new XmlElement("include");
        includeElement.addAttribute(new Attribute("refid", (new StringBuilder(String.valueOf(introspectedTable.getIbatis2SqlMapNamespace()))).append(".").append(introspectedTable.getExampleWhereClauseId()).toString()));
        isParameterPresentElement.addElement(includeElement);
        if(context.getPlugins().sqlMapUpdateByExampleWithoutBLOBsElementGenerated(answer, introspectedTable))
            parentElement.addElement(answer);
    }
}
