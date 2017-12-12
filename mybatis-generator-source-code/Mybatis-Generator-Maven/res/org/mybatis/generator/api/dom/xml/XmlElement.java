// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XmlElement.java

package org.mybatis.generator.api.dom.xml;

import java.util.*;
import org.mybatis.generator.api.dom.OutputUtilities;

// Referenced classes of package org.mybatis.generator.api.dom.xml:
//            Element, Attribute

public class XmlElement extends Element
{

    public XmlElement(String name)
    {
        attributes = new ArrayList();
        elements = new ArrayList();
        this.name = name;
    }

    public List getAttributes()
    {
        return attributes;
    }

    public void addAttribute(Attribute attribute)
    {
        attributes.add(attribute);
    }

    public List getElements()
    {
        return elements;
    }

    public void addElement(Element element)
    {
        elements.add(element);
    }

    public void addElement(int index, Element element)
    {
        elements.add(index, element);
    }

    public String getName()
    {
        return name;
    }

    public String getFormattedContent(int indentLevel)
    {
        StringBuilder sb = new StringBuilder();
        OutputUtilities.xmlIndent(sb, indentLevel);
        sb.append('<');
        sb.append(name);
        Attribute att;
        for(Iterator iterator = attributes.iterator(); iterator.hasNext(); sb.append(att.getFormattedContent()))
        {
            att = (Attribute)iterator.next();
            sb.append(' ');
        }

        if(elements.size() > 0)
        {
            sb.append(" >");
            Element element;
            for(Iterator iterator1 = elements.iterator(); iterator1.hasNext(); sb.append(element.getFormattedContent(indentLevel + 1)))
            {
                element = (Element)iterator1.next();
                OutputUtilities.newLine(sb);
            }

            OutputUtilities.newLine(sb);
            OutputUtilities.xmlIndent(sb, indentLevel);
            sb.append("</");
            sb.append(name);
            sb.append('>');
        } else
        {
            sb.append(" />");
        }
        return sb.toString();
    }

    public void setName(String name)
    {
        this.name = name;
    }

    private List attributes;
    private List elements;
    private String name;
}
