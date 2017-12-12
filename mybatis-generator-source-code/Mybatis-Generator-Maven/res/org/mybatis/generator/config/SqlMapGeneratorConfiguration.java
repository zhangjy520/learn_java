// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SqlMapGeneratorConfiguration.java

package org.mybatis.generator.config;

import java.util.List;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

// Referenced classes of package org.mybatis.generator.config:
//            PropertyHolder

public class SqlMapGeneratorConfiguration extends PropertyHolder
{

    public SqlMapGeneratorConfiguration()
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
        XmlElement answer = new XmlElement("sqlMapGenerator");
        if(targetPackage != null)
            answer.addAttribute(new Attribute("targetPackage", targetPackage));
        if(targetProject != null)
            answer.addAttribute(new Attribute("targetProject", targetProject));
        addPropertyXmlElements(answer);
        return answer;
    }

    public void validate(List errors, String contextId)
    {
        if(!StringUtility.stringHasValue(targetProject))
            errors.add(Messages.getString("ValidationError.1", contextId));
        if(!StringUtility.stringHasValue(targetPackage))
            errors.add(Messages.getString("ValidationError.12", "SQLMapGenerator", contextId));
    }

    private String targetPackage;
    private String targetProject;
}
