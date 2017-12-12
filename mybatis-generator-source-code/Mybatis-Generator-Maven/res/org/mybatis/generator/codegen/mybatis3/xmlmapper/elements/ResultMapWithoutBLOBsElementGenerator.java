// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ResultMapWithoutBLOBsElementGenerator.java

package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import java.util.Iterator;
import java.util.List;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.rules.Rules;
import org.mybatis.generator.internal.util.StringUtility;

// Referenced classes of package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements:
//            AbstractXmlElementGenerator

public class ResultMapWithoutBLOBsElementGenerator extends AbstractXmlElementGenerator
{

    public ResultMapWithoutBLOBsElementGenerator()
    {
    }

    public void addElements(XmlElement parentElement)
    {
        XmlElement answer = new XmlElement("resultMap");
        answer.addAttribute(new Attribute("id", introspectedTable.getBaseResultMapId()));
        String returnType;
        if(introspectedTable.getRules().generateBaseRecordClass())
            returnType = introspectedTable.getBaseRecordType();
        else
            returnType = introspectedTable.getPrimaryKeyType();
        answer.addAttribute(new Attribute("type", returnType));
        context.getCommentGenerator().addComment(answer);
        int i = 1;
        if(StringUtility.stringHasValue(introspectedTable.getSelectByPrimaryKeyQueryId()) || StringUtility.stringHasValue(introspectedTable.getSelectByExampleQueryId()))
            i++;
        XmlElement resultElement;
        for(Iterator iterator = introspectedTable.getPrimaryKeyColumns().iterator(); iterator.hasNext(); answer.addElement(resultElement))
        {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterator.next();
            resultElement = new XmlElement("id");
            resultElement.addAttribute(new Attribute("column", MyBatis3FormattingUtilities.getRenamedColumnNameForResultMap(introspectedColumn)));
            resultElement.addAttribute(new Attribute("property", introspectedColumn.getJavaProperty()));
            resultElement.addAttribute(new Attribute("jdbcType", introspectedColumn.getJdbcTypeName()));
            if(StringUtility.stringHasValue(introspectedColumn.getTypeHandler()))
                resultElement.addAttribute(new Attribute("typeHandler", introspectedColumn.getTypeHandler()));
        }

        XmlElement resultElement;
        for(Iterator iterator1 = introspectedTable.getBaseColumns().iterator(); iterator1.hasNext(); answer.addElement(resultElement))
        {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterator1.next();
            resultElement = new XmlElement("result");
            resultElement.addAttribute(new Attribute("column", MyBatis3FormattingUtilities.getRenamedColumnNameForResultMap(introspectedColumn)));
            resultElement.addAttribute(new Attribute("property", introspectedColumn.getJavaProperty()));
            resultElement.addAttribute(new Attribute("jdbcType", introspectedColumn.getJdbcTypeName()));
            if(StringUtility.stringHasValue(introspectedColumn.getTypeHandler()))
                resultElement.addAttribute(new Attribute("typeHandler", introspectedColumn.getTypeHandler()));
        }

        if(context.getPlugins().sqlMapResultMapWithoutBLOBsElementGenerated(answer, introspectedTable))
            parentElement.addElement(answer);
    }
}
