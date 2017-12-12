// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractXmlElementGenerator.java

package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.xml.*;
import org.mybatis.generator.codegen.AbstractGenerator;
import org.mybatis.generator.config.GeneratedKey;
import org.mybatis.generator.internal.util.StringUtility;

public abstract class AbstractXmlElementGenerator extends AbstractGenerator
{

    public abstract void addElements(XmlElement xmlelement);

    public AbstractXmlElementGenerator()
    {
    }

    protected XmlElement getSelectKey(IntrospectedColumn introspectedColumn, GeneratedKey generatedKey)
    {
        String identityColumnType = introspectedColumn.getFullyQualifiedJavaType().getFullyQualifiedName();
        XmlElement answer = new XmlElement("selectKey");
        answer.addAttribute(new Attribute("resultType", identityColumnType));
        answer.addAttribute(new Attribute("keyProperty", introspectedColumn.getJavaProperty()));
        if(StringUtility.stringHasValue(generatedKey.getType()))
            if("pre".equalsIgnoreCase(generatedKey.getType()))
                answer.addAttribute(new Attribute("order", "BEFORE"));
            else
            if("post".equalsIgnoreCase(generatedKey.getType()))
                answer.addAttribute(new Attribute("order", "AFTER"));
            else
                answer.addAttribute(new Attribute("order", generatedKey.getType()));
        answer.addElement(new TextElement(generatedKey.getRuntimeSqlStatement()));
        return answer;
    }

    protected XmlElement getBaseColumnListElement()
    {
        XmlElement answer = new XmlElement("include");
        answer.addAttribute(new Attribute("refid", introspectedTable.getBaseColumnListId()));
        return answer;
    }

    protected XmlElement getBlobColumnListElement()
    {
        XmlElement answer = new XmlElement("include");
        answer.addAttribute(new Attribute("refid", introspectedTable.getBlobColumnListId()));
        return answer;
    }

    protected XmlElement getExampleIncludeElement()
    {
        XmlElement ifElement = new XmlElement("if");
        ifElement.addAttribute(new Attribute("test", "_parameter != null"));
        XmlElement includeElement = new XmlElement("include");
        includeElement.addAttribute(new Attribute("refid", introspectedTable.getExampleWhereClauseId()));
        ifElement.addElement(includeElement);
        return ifElement;
    }

    protected XmlElement getUpdateByExampleIncludeElement()
    {
        XmlElement ifElement = new XmlElement("if");
        ifElement.addAttribute(new Attribute("test", "_parameter != null"));
        XmlElement includeElement = new XmlElement("include");
        includeElement.addAttribute(new Attribute("refid", introspectedTable.getMyBatis3UpdateByExampleWhereClauseId()));
        ifElement.addElement(includeElement);
        return ifElement;
    }
}
