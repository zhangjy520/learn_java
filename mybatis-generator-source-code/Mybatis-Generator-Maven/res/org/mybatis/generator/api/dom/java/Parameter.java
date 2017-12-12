// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Parameter.java

package org.mybatis.generator.api.dom.java;

import java.util.*;

// Referenced classes of package org.mybatis.generator.api.dom.java:
//            FullyQualifiedJavaType

public class Parameter
{

    public Parameter(FullyQualifiedJavaType type, String name)
    {
        this.name = name;
        this.type = type;
        annotations = new ArrayList();
    }

    public Parameter(FullyQualifiedJavaType type, String name, String annotation)
    {
        this(type, name);
        addAnnotation(annotation);
    }

    public String getName()
    {
        return name;
    }

    public FullyQualifiedJavaType getType()
    {
        return type;
    }

    public List getAnnotations()
    {
        return annotations;
    }

    public void addAnnotation(String annotation)
    {
        annotations.add(annotation);
    }

    public String getFormattedContent()
    {
        StringBuilder sb = new StringBuilder();
        for(Iterator iterator = annotations.iterator(); iterator.hasNext(); sb.append(' '))
        {
            String annotation = (String)iterator.next();
            sb.append(annotation);
        }

        sb.append(type.getShortName());
        sb.append(' ');
        sb.append(name);
        return sb.toString();
    }

    public String toString()
    {
        return getFormattedContent();
    }

    private String name;
    private FullyQualifiedJavaType type;
    private List annotations;
}
