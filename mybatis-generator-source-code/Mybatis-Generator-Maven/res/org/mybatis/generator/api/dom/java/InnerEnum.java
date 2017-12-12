// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InnerEnum.java

package org.mybatis.generator.api.dom.java;

import java.util.*;
import org.mybatis.generator.api.dom.OutputUtilities;

// Referenced classes of package org.mybatis.generator.api.dom.java:
//            JavaElement, JavaVisibility, FullyQualifiedJavaType, Field, 
//            Method, InnerClass

public class InnerEnum extends JavaElement
{

    public InnerEnum(FullyQualifiedJavaType type)
    {
        this.type = type;
        fields = new ArrayList();
        innerClasses = new ArrayList();
        innerEnums = new ArrayList();
        superInterfaceTypes = new HashSet();
        methods = new ArrayList();
        enumConstants = new ArrayList();
    }

    public List getFields()
    {
        return fields;
    }

    public void addField(Field field)
    {
        fields.add(field);
    }

    public List getInnerClasses()
    {
        return innerClasses;
    }

    public void addInnerClass(InnerClass innerClass)
    {
        innerClasses.add(innerClass);
    }

    public List getInnerEnums()
    {
        return innerEnums;
    }

    public void addInnerEnum(InnerEnum innerEnum)
    {
        innerEnums.add(innerEnum);
    }

    public List getEnumConstants()
    {
        return enumConstants;
    }

    public void addEnumConstant(String enumConstant)
    {
        enumConstants.add(enumConstant);
    }

    public String getFormattedContent(int indentLevel)
    {
        StringBuilder sb = new StringBuilder();
        addFormattedJavadoc(sb, indentLevel);
        addFormattedAnnotations(sb, indentLevel);
        OutputUtilities.javaIndent(sb, indentLevel);
        if(getVisibility() == JavaVisibility.PUBLIC)
            sb.append(getVisibility().getValue());
        sb.append("enum ");
        sb.append(getType().getShortName());
        if(superInterfaceTypes.size() > 0)
        {
            sb.append(" implements ");
            boolean comma = false;
            FullyQualifiedJavaType fqjt;
            for(Iterator iterator = superInterfaceTypes.iterator(); iterator.hasNext(); sb.append(fqjt.getShortName()))
            {
                fqjt = (FullyQualifiedJavaType)iterator.next();
                if(comma)
                    sb.append(", ");
                else
                    comma = true;
            }

        }
        sb.append(" {");
        indentLevel++;
        for(Iterator strIter = enumConstants.iterator(); strIter.hasNext();)
        {
            OutputUtilities.newLine(sb);
            OutputUtilities.javaIndent(sb, indentLevel);
            String enumConstant = (String)strIter.next();
            sb.append(enumConstant);
            if(strIter.hasNext())
                sb.append(',');
            else
                sb.append(';');
        }

        if(fields.size() > 0)
            OutputUtilities.newLine(sb);
        for(Iterator fldIter = fields.iterator(); fldIter.hasNext();)
        {
            OutputUtilities.newLine(sb);
            Field field = (Field)fldIter.next();
            sb.append(field.getFormattedContent(indentLevel));
            if(fldIter.hasNext())
                OutputUtilities.newLine(sb);
        }

        if(methods.size() > 0)
            OutputUtilities.newLine(sb);
        for(Iterator mtdIter = methods.iterator(); mtdIter.hasNext();)
        {
            OutputUtilities.newLine(sb);
            Method method = (Method)mtdIter.next();
            sb.append(method.getFormattedContent(indentLevel, false));
            if(mtdIter.hasNext())
                OutputUtilities.newLine(sb);
        }

        if(innerClasses.size() > 0)
            OutputUtilities.newLine(sb);
        for(Iterator icIter = innerClasses.iterator(); icIter.hasNext();)
        {
            OutputUtilities.newLine(sb);
            InnerClass innerClass = (InnerClass)icIter.next();
            sb.append(innerClass.getFormattedContent(indentLevel));
            if(icIter.hasNext())
                OutputUtilities.newLine(sb);
        }

        if(innerEnums.size() > 0)
            OutputUtilities.newLine(sb);
        for(Iterator ieIter = innerEnums.iterator(); ieIter.hasNext();)
        {
            OutputUtilities.newLine(sb);
            InnerEnum innerEnum = (InnerEnum)ieIter.next();
            sb.append(innerEnum.getFormattedContent(indentLevel));
            if(ieIter.hasNext())
                OutputUtilities.newLine(sb);
        }

        indentLevel--;
        OutputUtilities.newLine(sb);
        OutputUtilities.javaIndent(sb, indentLevel);
        sb.append('}');
        return sb.toString();
    }

    public Set getSuperInterfaceTypes()
    {
        return superInterfaceTypes;
    }

    public void addSuperInterface(FullyQualifiedJavaType superInterface)
    {
        superInterfaceTypes.add(superInterface);
    }

    public List getMethods()
    {
        return methods;
    }

    public void addMethod(Method method)
    {
        methods.add(method);
    }

    public FullyQualifiedJavaType getType()
    {
        return type;
    }

    private List fields;
    private List innerClasses;
    private List innerEnums;
    private FullyQualifiedJavaType type;
    private Set superInterfaceTypes;
    private List methods;
    private List enumConstants;
}
