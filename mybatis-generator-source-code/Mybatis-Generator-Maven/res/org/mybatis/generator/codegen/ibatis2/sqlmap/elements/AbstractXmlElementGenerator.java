// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractXmlElementGenerator.java

package org.mybatis.generator.codegen.ibatis2.sqlmap.elements;

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
        answer.addAttribute(new Attribute("resultClass", identityColumnType));
        answer.addAttribute(new Attribute("keyProperty", introspectedColumn.getJavaProperty()));
        if(StringUtility.stringHasValue(generatedKey.getType()))
            answer.addAttribute(new Attribute("type", generatedKey.getType()));
        answer.addElement(new TextElement(generatedKey.getRuntimeSqlStatement()));
        return answer;
    }

    protected XmlElement getBaseColumnListElement()
    {
        XmlElement answer = new XmlElement("include");
        answer.addAttribute(new Attribute("refid", (new StringBuilder(String.valueOf(introspectedTable.getIbatis2SqlMapNamespace()))).append(".").append(introspectedTable.getBaseColumnListId()).toString()));
        return answer;
    }

    protected XmlElement getBlobColumnListElement()
    {
        XmlElement answer = new XmlElement("include");
        answer.addAttribute(new Attribute("refid", (new StringBuilder(String.valueOf(introspectedTable.getIbatis2SqlMapNamespace()))).append(".").append(introspectedTable.getBlobColumnListId()).toString()));
        return answer;
    }
}
