// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PropertyHolder.java

package org.mybatis.generator.config;

import java.util.Enumeration;
import java.util.Properties;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;

public abstract class PropertyHolder
{

    public PropertyHolder()
    {
        properties = new Properties();
    }

    public void addProperty(String name, String value)
    {
        properties.setProperty(name, value);
    }

    public String getProperty(String name)
    {
        return properties.getProperty(name);
    }

    public Properties getProperties()
    {
        return properties;
    }

    protected void addPropertyXmlElements(XmlElement xmlElement)
    {
        XmlElement propertyElement;
        for(Enumeration enumeration = properties.propertyNames(); enumeration.hasMoreElements(); xmlElement.addElement(propertyElement))
        {
            String propertyName = (String)enumeration.nextElement();
            propertyElement = new XmlElement("property");
            propertyElement.addAttribute(new Attribute("name", propertyName));
            propertyElement.addAttribute(new Attribute("value", properties.getProperty(propertyName)));
        }

    }

    private Properties properties;
}
