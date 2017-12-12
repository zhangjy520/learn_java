// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Field.java

package org.mybatis.generator.api.dom.java;

import org.mybatis.generator.api.dom.OutputUtilities;

// Referenced classes of package org.mybatis.generator.api.dom.java:
//            JavaElement, JavaVisibility, FullyQualifiedJavaType

public class Field extends JavaElement
{

    public Field()
    {
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public FullyQualifiedJavaType getType()
    {
        return type;
    }

    public void setType(FullyQualifiedJavaType type)
    {
        this.type = type;
    }

    public String getInitializationString()
    {
        return initializationString;
    }

    public void setInitializationString(String initializationString)
    {
        this.initializationString = initializationString;
    }

    public String getFormattedContent(int indentLevel)
    {
        StringBuilder sb = new StringBuilder();
        addFormattedJavadoc(sb, indentLevel);
        addFormattedAnnotations(sb, indentLevel);
        OutputUtilities.javaIndent(sb, indentLevel);
        sb.append(getVisibility().getValue());
        if(isStatic())
            sb.append("static ");
        if(isFinal())
            sb.append("final ");
        sb.append(type.getShortName());
        sb.append(' ');
        sb.append(name);
        if(initializationString != null && initializationString.length() > 0)
        {
            sb.append(" = ");
            sb.append(initializationString);
        }
        sb.append(';');
        return sb.toString();
    }

    private FullyQualifiedJavaType type;
    private String name;
    private String initializationString;
}
