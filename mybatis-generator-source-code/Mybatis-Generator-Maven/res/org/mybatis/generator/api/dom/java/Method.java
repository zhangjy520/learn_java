// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Method.java

package org.mybatis.generator.api.dom.java;

import java.util.*;
import org.mybatis.generator.api.dom.OutputUtilities;

// Referenced classes of package org.mybatis.generator.api.dom.java:
//            JavaElement, JavaVisibility, FullyQualifiedJavaType, Parameter

public class Method extends JavaElement
{

    public Method()
    {
        bodyLines = new ArrayList();
        parameters = new ArrayList();
        exceptions = new ArrayList();
    }

    public List getBodyLines()
    {
        return bodyLines;
    }

    public void addBodyLine(String line)
    {
        bodyLines.add(line);
    }

    public void addBodyLine(int index, String line)
    {
        bodyLines.add(index, line);
    }

    public void addBodyLines(Collection lines)
    {
        bodyLines.addAll(lines);
    }

    public void addBodyLines(int index, Collection lines)
    {
        bodyLines.addAll(index, lines);
    }

    public String getFormattedContent(int indentLevel, boolean interfaceMethod)
    {
        StringBuilder sb = new StringBuilder();
        addFormattedJavadoc(sb, indentLevel);
        addFormattedAnnotations(sb, indentLevel);
        OutputUtilities.javaIndent(sb, indentLevel);
        if(!interfaceMethod)
        {
            sb.append(getVisibility().getValue());
            if(isStatic())
                sb.append("static ");
            if(isFinal())
                sb.append("final ");
            if(bodyLines.size() == 0)
                sb.append("abstract ");
        }
        if(!constructor)
        {
            if(getReturnType() == null)
                sb.append("void");
            else
                sb.append(getReturnType().getShortName());
            sb.append(' ');
        }
        sb.append(getName());
        sb.append('(');
        boolean comma = false;
        Parameter parameter;
        for(Iterator iterator = getParameters().iterator(); iterator.hasNext(); sb.append(parameter.getFormattedContent()))
        {
            parameter = (Parameter)iterator.next();
            if(comma)
                sb.append(", ");
            else
                comma = true;
        }

        sb.append(')');
        if(getExceptions().size() > 0)
        {
            sb.append(" throws ");
            comma = false;
            FullyQualifiedJavaType fqjt;
            for(Iterator iterator1 = getExceptions().iterator(); iterator1.hasNext(); sb.append(fqjt.getShortName()))
            {
                fqjt = (FullyQualifiedJavaType)iterator1.next();
                if(comma)
                    sb.append(", ");
                else
                    comma = true;
            }

        }
        if(bodyLines.size() == 0)
        {
            sb.append(';');
        } else
        {
            sb.append(" {");
            indentLevel++;
            for(ListIterator listIter = bodyLines.listIterator(); listIter.hasNext();)
            {
                String line = (String)listIter.next();
                if(line.startsWith("}"))
                    indentLevel--;
                OutputUtilities.newLine(sb);
                OutputUtilities.javaIndent(sb, indentLevel);
                sb.append(line);
                if(line.endsWith("{") && !line.startsWith("switch") || line.endsWith(":"))
                    indentLevel++;
                if(line.startsWith("break"))
                {
                    if(listIter.hasNext())
                    {
                        String nextLine = (String)listIter.next();
                        if(nextLine.startsWith("}"))
                            indentLevel++;
                        listIter.previous();
                    }
                    indentLevel--;
                }
            }

            indentLevel--;
            OutputUtilities.newLine(sb);
            OutputUtilities.javaIndent(sb, indentLevel);
            sb.append('}');
        }
        return sb.toString();
    }

    public boolean isConstructor()
    {
        return constructor;
    }

    public void setConstructor(boolean constructor)
    {
        this.constructor = constructor;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List getParameters()
    {
        return parameters;
    }

    public void addParameter(Parameter parameter)
    {
        parameters.add(parameter);
    }

    public void addParameter(int index, Parameter parameter)
    {
        parameters.add(index, parameter);
    }

    public FullyQualifiedJavaType getReturnType()
    {
        return returnType;
    }

    public void setReturnType(FullyQualifiedJavaType returnType)
    {
        this.returnType = returnType;
    }

    public List getExceptions()
    {
        return exceptions;
    }

    public void addException(FullyQualifiedJavaType exception)
    {
        exceptions.add(exception);
    }

    private List bodyLines;
    private boolean constructor;
    private FullyQualifiedJavaType returnType;
    private String name;
    private List parameters;
    private List exceptions;
}
