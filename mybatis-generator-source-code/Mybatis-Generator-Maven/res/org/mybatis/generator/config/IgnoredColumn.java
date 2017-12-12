// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IgnoredColumn.java

package org.mybatis.generator.config;

import java.util.List;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

public class IgnoredColumn
{

    public IgnoredColumn(String columnName)
    {
        this.columnName = columnName;
        isColumnNameDelimited = StringUtility.stringContainsSpace(columnName);
    }

    public String getColumnName()
    {
        return columnName;
    }

    public boolean isColumnNameDelimited()
    {
        return isColumnNameDelimited;
    }

    public void setColumnNameDelimited(boolean isColumnNameDelimited)
    {
        this.isColumnNameDelimited = isColumnNameDelimited;
        configuredDelimitedColumnName = isColumnNameDelimited ? "true" : "false";
    }

    public boolean equals(Object obj)
    {
        if(obj == null || !(obj instanceof IgnoredColumn))
            return false;
        else
            return columnName.equals(((IgnoredColumn)obj).getColumnName());
    }

    public int hashCode()
    {
        return columnName.hashCode();
    }

    public XmlElement toXmlElement()
    {
        XmlElement xmlElement = new XmlElement("ignoreColumn");
        xmlElement.addAttribute(new Attribute("column", columnName));
        if(StringUtility.stringHasValue(configuredDelimitedColumnName))
            xmlElement.addAttribute(new Attribute("delimitedColumnName", configuredDelimitedColumnName));
        return xmlElement;
    }

    public void validate(List errors, String tableName)
    {
        if(!StringUtility.stringHasValue(columnName))
            errors.add(Messages.getString("ValidationError.21", tableName));
    }

    private String columnName;
    private boolean isColumnNameDelimited;
    private String configuredDelimitedColumnName;
}
