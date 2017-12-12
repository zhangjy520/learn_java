// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PluginConfiguration.java

package org.mybatis.generator.config;

import java.util.List;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

// Referenced classes of package org.mybatis.generator.config:
//            TypedPropertyHolder

public class PluginConfiguration extends TypedPropertyHolder
{

    public PluginConfiguration()
    {
    }

    public XmlElement toXmlElement()
    {
        XmlElement answer = new XmlElement("plugin");
        if(getConfigurationType() != null)
            answer.addAttribute(new Attribute("type", getConfigurationType()));
        addPropertyXmlElements(answer);
        return answer;
    }

    public void validate(List errors, String contextId)
    {
        if(!StringUtility.stringHasValue(getConfigurationType()))
            errors.add(Messages.getString("ValidationError.17", contextId));
    }
}
