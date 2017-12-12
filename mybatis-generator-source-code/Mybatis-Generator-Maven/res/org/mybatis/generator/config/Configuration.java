// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Configuration.java

package org.mybatis.generator.config;

import java.util.*;
import org.mybatis.generator.api.dom.xml.*;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

// Referenced classes of package org.mybatis.generator.config:
//            Context

public class Configuration
{

    public Configuration()
    {
        contexts = new ArrayList();
        classPathEntries = new ArrayList();
    }

    public void addClasspathEntry(String entry)
    {
        classPathEntries.add(entry);
    }

    public List getClassPathEntries()
    {
        return classPathEntries;
    }

    public void validate()
        throws InvalidConfigurationException
    {
        List errors = new ArrayList();
        for(Iterator iterator = classPathEntries.iterator(); iterator.hasNext();)
        {
            String classPathEntry = (String)iterator.next();
            if(!StringUtility.stringHasValue(classPathEntry))
            {
                errors.add(Messages.getString("ValidationError.19"));
                break;
            }
        }

        if(contexts.size() == 0)
        {
            errors.add(Messages.getString("ValidationError.11"));
        } else
        {
            Context context;
            for(Iterator iterator1 = contexts.iterator(); iterator1.hasNext(); context.validate(errors))
                context = (Context)iterator1.next();

        }
        if(errors.size() > 0)
            throw new InvalidConfigurationException(errors);
        else
            return;
    }

    public List getContexts()
    {
        return contexts;
    }

    public void addContext(Context context)
    {
        contexts.add(context);
    }

    public Context getContext(String id)
    {
        for(Iterator iterator = contexts.iterator(); iterator.hasNext();)
        {
            Context context = (Context)iterator.next();
            if(id.equals(context.getId()))
                return context;
        }

        return null;
    }

    public Document toDocument()
    {
        Document document = new Document("-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN", "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd");
        XmlElement rootElement = new XmlElement("generatorConfiguration");
        document.setRootElement(rootElement);
        XmlElement cpeElement;
        for(Iterator iterator = classPathEntries.iterator(); iterator.hasNext(); rootElement.addElement(cpeElement))
        {
            String classPathEntry = (String)iterator.next();
            cpeElement = new XmlElement("classPathEntry");
            cpeElement.addAttribute(new Attribute("location", classPathEntry));
        }

        Context context;
        for(Iterator iterator1 = contexts.iterator(); iterator1.hasNext(); rootElement.addElement(context.toXmlElement()))
            context = (Context)iterator1.next();

        return document;
    }

    private List contexts;
    private List classPathEntries;
}
