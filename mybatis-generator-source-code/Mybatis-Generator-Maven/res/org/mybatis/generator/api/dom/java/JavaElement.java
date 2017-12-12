// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JavaElement.java

package org.mybatis.generator.api.dom.java;

import java.util.*;
import org.mybatis.generator.api.dom.OutputUtilities;

// Referenced classes of package org.mybatis.generator.api.dom.java:
//            JavaVisibility

public abstract class JavaElement
{

    public JavaElement()
    {
        javaDocLines = new ArrayList();
        annotations = new ArrayList();
    }

    public List getJavaDocLines()
    {
        return javaDocLines;
    }

    public void addJavaDocLine(String javaDocLine)
    {
        javaDocLines.add(javaDocLine);
    }

    public List getAnnotations()
    {
        return annotations;
    }

    public void addAnnotation(String annotation)
    {
        annotations.add(annotation);
    }

    public JavaVisibility getVisibility()
    {
        return visibility;
    }

    public void setVisibility(JavaVisibility visibility)
    {
        this.visibility = visibility;
    }

    public void addSuppressTypeWarningsAnnotation()
    {
        addAnnotation("@SuppressWarnings(\"unchecked\")");
    }

    public void addFormattedJavadoc(StringBuilder sb, int indentLevel)
    {
        for(Iterator iterator = javaDocLines.iterator(); iterator.hasNext(); OutputUtilities.newLine(sb))
        {
            String javaDocLine = (String)iterator.next();
            OutputUtilities.javaIndent(sb, indentLevel);
            sb.append(javaDocLine);
        }

    }

    public void addFormattedAnnotations(StringBuilder sb, int indentLevel)
    {
        for(Iterator iterator = annotations.iterator(); iterator.hasNext(); OutputUtilities.newLine(sb))
        {
            String annotation = (String)iterator.next();
            OutputUtilities.javaIndent(sb, indentLevel);
            sb.append(annotation);
        }

    }

    public boolean isFinal()
    {
        return isFinal;
    }

    public void setFinal(boolean isFinal)
    {
        this.isFinal = isFinal;
    }

    public boolean isStatic()
    {
        return isStatic;
    }

    public void setStatic(boolean isStatic)
    {
        this.isStatic = isStatic;
    }

    private List javaDocLines;
    private JavaVisibility visibility;
    private boolean isStatic;
    private boolean isFinal;
    private List annotations;
}
