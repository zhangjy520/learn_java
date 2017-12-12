// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JavaClientGeneratorConfiguration.java

package org.mybatis.generator.config;

import java.util.List;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

// Referenced classes of package org.mybatis.generator.config:
//            TypedPropertyHolder

public class JavaClientGeneratorConfiguration extends TypedPropertyHolder
{

    public JavaClientGeneratorConfiguration()
    {
    }

    public String getTargetProject()
    {
        return targetProject;
    }

    public void setTargetProject(String targetProject)
    {
        this.targetProject = targetProject;
    }

    public String getTargetPackage()
    {
        return targetPackage;
    }

    public void setTargetPackage(String targetPackage)
    {
        this.targetPackage = targetPackage;
    }

    public XmlElement toXmlElement()
    {
        XmlElement answer = new XmlElement("javaClientGenerator");
        if(getConfigurationType() != null)
            answer.addAttribute(new Attribute("type", getConfigurationType()));
        if(targetPackage != null)
            answer.addAttribute(new Attribute("targetPackage", targetPackage));
        if(targetProject != null)
            answer.addAttribute(new Attribute("targetProject", targetProject));
        if(implementationPackage != null)
            answer.addAttribute(new Attribute("implementationPackage", targetProject));
        addPropertyXmlElements(answer);
        return answer;
    }

    public String getImplementationPackage()
    {
        return implementationPackage;
    }

    public void setImplementationPackage(String implementationPackage)
    {
        this.implementationPackage = implementationPackage;
    }

    public void validate(List errors, String contextId)
    {
        if(!StringUtility.stringHasValue(targetProject))
            errors.add(Messages.getString("ValidationError.2", contextId));
        if(!StringUtility.stringHasValue(targetPackage))
            errors.add(Messages.getString("ValidationError.12", "javaClientGenerator", contextId));
        if(!StringUtility.stringHasValue(getConfigurationType()))
            errors.add(Messages.getString("ValidationError.20", contextId));
    }

    private String targetPackage;
    private String implementationPackage;
    private String targetProject;
}
