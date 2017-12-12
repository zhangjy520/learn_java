// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ColumnOverride.java

package org.mybatis.generator.config;

import java.util.List;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

// Referenced classes of package org.mybatis.generator.config:
//            PropertyHolder

public class ColumnOverride extends PropertyHolder
{

    public ColumnOverride(String columnName)
    {
        this.columnName = columnName;
        isColumnNameDelimited = StringUtility.stringContainsSpace(columnName);
    }

    public String getColumnName()
    {
        return columnName;
    }

    public String getJavaProperty()
    {
        return javaProperty;
    }

    public void setJavaProperty(String javaProperty)
    {
        this.javaProperty = javaProperty;
    }

    public String getJavaType()
    {
        return javaType;
    }

    public void setJavaType(String javaType)
    {
        this.javaType = javaType;
    }

    public String getJdbcType()
    {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType)
    {
        this.jdbcType = jdbcType;
    }

    public String getTypeHandler()
    {
        return typeHandler;
    }

    public void setTypeHandler(String typeHandler)
    {
        this.typeHandler = typeHandler;
    }

    public XmlElement toXmlElement()
    {
        XmlElement xmlElement = new XmlElement("columnOverride");
        xmlElement.addAttribute(new Attribute("column", columnName));
        if(StringUtility.stringHasValue(javaProperty))
            xmlElement.addAttribute(new Attribute("property", javaProperty));
        if(StringUtility.stringHasValue(javaType))
            xmlElement.addAttribute(new Attribute("javaType", javaType));
        if(StringUtility.stringHasValue(jdbcType))
            xmlElement.addAttribute(new Attribute("jdbcType", jdbcType));
        if(StringUtility.stringHasValue(typeHandler))
            xmlElement.addAttribute(new Attribute("typeHandler", typeHandler));
        if(StringUtility.stringHasValue(configuredDelimitedColumnName))
            xmlElement.addAttribute(new Attribute("delimitedColumnName", configuredDelimitedColumnName));
        addPropertyXmlElements(xmlElement);
        return xmlElement;
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

    public void validate(List errors, String tableName)
    {
        if(!StringUtility.stringHasValue(columnName))
            errors.add(Messages.getString("ValidationError.22", tableName));
    }

    private String columnName;
    private String javaProperty;
    private String jdbcType;
    private String javaType;
    private String typeHandler;
    private boolean isColumnNameDelimited;
    private String configuredDelimitedColumnName;
}
