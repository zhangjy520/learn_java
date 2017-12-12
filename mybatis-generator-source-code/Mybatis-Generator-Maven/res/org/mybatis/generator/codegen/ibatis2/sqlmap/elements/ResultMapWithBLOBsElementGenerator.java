// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ResultMapWithBLOBsElementGenerator.java

package org.mybatis.generator.codegen.ibatis2.sqlmap.elements;

import java.util.Iterator;
import java.util.List;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.ibatis2.Ibatis2FormattingUtilities;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.rules.Rules;
import org.mybatis.generator.internal.util.StringUtility;

// Referenced classes of package org.mybatis.generator.codegen.ibatis2.sqlmap.elements:
//            AbstractXmlElementGenerator

public class ResultMapWithBLOBsElementGenerator extends AbstractXmlElementGenerator
{

    public ResultMapWithBLOBsElementGenerator()
    {
    }

    public void addElements(XmlElement parentElement)
    {
        boolean useColumnIndex = StringUtility.isTrue(introspectedTable.getTableConfigurationProperty("useColumnIndexes"));
        XmlElement answer = new XmlElement("resultMap");
        answer.addAttribute(new Attribute("id", introspectedTable.getResultMapWithBLOBsId()));
        String returnType;
        if(introspectedTable.getRules().generateRecordWithBLOBsClass())
            returnType = introspectedTable.getRecordWithBLOBsType();
        else
            returnType = introspectedTable.getBaseRecordType();
        answer.addAttribute(new Attribute("class", returnType));
        StringBuilder sb = new StringBuilder();
        sb.append(introspectedTable.getIbatis2SqlMapNamespace());
        sb.append('.');
        sb.append(introspectedTable.getBaseResultMapId());
        answer.addAttribute(new Attribute("extends", sb.toString()));
        context.getCommentGenerator().addComment(answer);
        int i = introspectedTable.getNonBLOBColumnCount() + 1;
        if(StringUtility.stringHasValue(introspectedTable.getSelectByPrimaryKeyQueryId()) || StringUtility.stringHasValue(introspectedTable.getSelectByExampleQueryId()))
            i++;
        XmlElement resultElement;
        for(Iterator iterator = introspectedTable.getBLOBColumns().iterator(); iterator.hasNext(); answer.addElement(resultElement))
        {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterator.next();
            resultElement = new XmlElement("result");
            if(useColumnIndex)
                resultElement.addAttribute(new Attribute("columnIndex", Integer.toString(i++)));
            else
                resultElement.addAttribute(new Attribute("column", Ibatis2FormattingUtilities.getRenamedColumnNameForResultMap(introspectedColumn)));
            resultElement.addAttribute(new Attribute("property", introspectedColumn.getJavaProperty()));
            resultElement.addAttribute(new Attribute("jdbcType", introspectedColumn.getJdbcTypeName()));
            if(StringUtility.stringHasValue(introspectedColumn.getTypeHandler()))
                resultElement.addAttribute(new Attribute("typeHandler", introspectedColumn.getTypeHandler()));
        }

        if(context.getPlugins().sqlMapResultMapWithBLOBsElementGenerated(answer, introspectedTable))
            parentElement.addElement(answer);
    }
}
