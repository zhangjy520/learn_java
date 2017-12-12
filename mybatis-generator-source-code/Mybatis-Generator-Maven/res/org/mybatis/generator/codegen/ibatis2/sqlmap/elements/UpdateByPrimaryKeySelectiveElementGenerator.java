// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UpdateByPrimaryKeySelectiveElementGenerator.java

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

public class UpdateByPrimaryKeySelectiveElementGenerator extends AbstractXmlElementGenerator
{

    public UpdateByPrimaryKeySelectiveElementGenerator()
    {
    }

    public void addElements(XmlElement parentElement)
    {
        XmlElement answer = new XmlElement("update");
        answer.addAttribute(new Attribute("id", introspectedTable.getUpdateByPrimaryKeySelectiveStatementId()));
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
        XmlElement dynamicElement = new XmlElement("dynamic");
        dynamicElement.addAttribute(new Attribute("prepend", "set"));
        answer.addElement(dynamicElement);
        XmlElement isNotNullElement;
        for(Iterator iterator = introspectedTable.getNonPrimaryKeyColumns().iterator(); iterator.hasNext(); isNotNullElement.addElement(new TextElement(sb.toString())))
        {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterator.next();
            isNotNullElement = new XmlElement("isNotNull");
            isNotNullElement.addAttribute(new Attribute("prepend", ","));
            isNotNullElement.addAttribute(new Attribute("property", introspectedColumn.getJavaProperty()));
            dynamicElement.addElement(isNotNullElement);
            sb.setLength(0);
            sb.append(Ibatis2FormattingUtilities.getEscapedColumnName(introspectedColumn));
            sb.append(" = ");
            sb.append(Ibatis2FormattingUtilities.getParameterClause(introspectedColumn));
        }

        boolean and = false;
        for(Iterator iterator1 = introspectedTable.getPrimaryKeyColumns().iterator(); iterator1.hasNext(); answer.addElement(new TextElement(sb.toString())))
        {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterator1.next();
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

        if(context.getPlugins().sqlMapUpdateByPrimaryKeySelectiveElementGenerated(answer, introspectedTable))
            parentElement.addElement(answer);
    }
}
