// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ColumnRenamingRule.java

package org.mybatis.generator.config;

import java.util.List;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

public class ColumnRenamingRule
{

    public ColumnRenamingRule()
    {
    }

    public String getReplaceString()
    {
        return replaceString;
    }

    public void setReplaceString(String replaceString)
    {
        this.replaceString = replaceString;
    }

    public String getSearchString()
    {
        return searchString;
    }

    public void setSearchString(String searchString)
    {
        this.searchString = searchString;
    }

    public void validate(List errors, String tableName)
    {
        if(!StringUtility.stringHasValue(searchString))
            errors.add(Messages.getString("ValidationError.14", tableName));
    }

    public XmlElement toXmlElement()
    {
        XmlElement xmlElement = new XmlElement("columnRenamingRule");
        xmlElement.addAttribute(new Attribute("searchString", searchString));
        if(replaceString != null)
            xmlElement.addAttribute(new Attribute("replaceString", replaceString));
        return xmlElement;
    }

    private String searchString;
    private String replaceString;
}
