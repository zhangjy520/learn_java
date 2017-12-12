// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JDBCConnectionConfiguration.java

package org.mybatis.generator.config;

import java.util.List;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

// Referenced classes of package org.mybatis.generator.config:
//            PropertyHolder

public class JDBCConnectionConfiguration extends PropertyHolder
{

    public JDBCConnectionConfiguration()
    {
    }

    public String getConnectionURL()
    {
        return connectionURL;
    }

    public void setConnectionURL(String connectionURL)
    {
        this.connectionURL = connectionURL;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getDriverClass()
    {
        return driverClass;
    }

    public void setDriverClass(String driverClass)
    {
        this.driverClass = driverClass;
    }

    public XmlElement toXmlElement()
    {
        XmlElement xmlElement = new XmlElement("jdbcConnection");
        xmlElement.addAttribute(new Attribute("driverClass", driverClass));
        xmlElement.addAttribute(new Attribute("connectionURL", connectionURL));
        if(StringUtility.stringHasValue(userId))
            xmlElement.addAttribute(new Attribute("userId", userId));
        if(StringUtility.stringHasValue(password))
            xmlElement.addAttribute(new Attribute("password", password));
        addPropertyXmlElements(xmlElement);
        return xmlElement;
    }

    public void validate(List errors)
    {
        if(!StringUtility.stringHasValue(driverClass))
            errors.add(Messages.getString("ValidationError.4"));
        if(!StringUtility.stringHasValue(connectionURL))
            errors.add(Messages.getString("ValidationError.5"));
    }

    private String driverClass;
    private String connectionURL;
    private String userId;
    private String password;
}
