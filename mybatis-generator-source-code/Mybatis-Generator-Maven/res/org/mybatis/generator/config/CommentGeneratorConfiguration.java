// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CommentGeneratorConfiguration.java

package org.mybatis.generator.config;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;

// Referenced classes of package org.mybatis.generator.config:
//            TypedPropertyHolder

public class CommentGeneratorConfiguration extends TypedPropertyHolder
{

    public CommentGeneratorConfiguration()
    {
    }

    public XmlElement toXmlElement()
    {
        XmlElement answer = new XmlElement("commentGenerator");
        if(getConfigurationType() != null)
            answer.addAttribute(new Attribute("type", getConfigurationType()));
        addPropertyXmlElements(answer);
        return answer;
    }
}
