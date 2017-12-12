// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Interface.java

package org.mybatis.generator.api.dom.java;

import java.util.*;
import org.mybatis.generator.api.dom.OutputUtilities;
import org.mybatis.generator.internal.util.StringUtility;

// Referenced classes of package org.mybatis.generator.api.dom.java:
//            JavaElement, CompilationUnit, FullyQualifiedJavaType, JavaVisibility, 
//            Method

public class Interface extends JavaElement
    implements CompilationUnit
{

    public Interface(FullyQualifiedJavaType type)
    {
        this.type = type;
        superInterfaceTypes = new LinkedHashSet();
        methods = new ArrayList();
        importedTypes = new TreeSet();
        fileCommentLines = new ArrayList();
    }

    public Interface(String type)
    {
        this(new FullyQualifiedJavaType(type));
    }

    public Set getImportedTypes()
    {
        return Collections.unmodifiableSet(importedTypes);
    }

    public void addImportedType(FullyQualifiedJavaType importedType)
    {
        if(importedType.isExplicitlyImported() && !importedType.getPackageName().equals(type.getPackageName()))
            importedTypes.add(importedType);
    }

    public String getFormattedContent()
    {
        StringBuilder sb = new StringBuilder();
        for(Iterator iterator = fileCommentLines.iterator(); iterator.hasNext(); OutputUtilities.newLine(sb))
        {
            String commentLine = (String)iterator.next();
            sb.append(commentLine);
        }

        if(StringUtility.stringHasValue(getType().getPackageName()))
        {
            sb.append("package ");
            sb.append(getType().getPackageName());
            sb.append(';');
            OutputUtilities.newLine(sb);
            OutputUtilities.newLine(sb);
        }
        Set importStrings = OutputUtilities.calculateImports(importedTypes);
        for(Iterator iterator1 = importStrings.iterator(); iterator1.hasNext(); OutputUtilities.newLine(sb))
        {
            String importString = (String)iterator1.next();
            sb.append(importString);
        }

        if(importStrings.size() > 0)
            OutputUtilities.newLine(sb);
        int indentLevel = 0;
        addFormattedJavadoc(sb, indentLevel);
        addFormattedAnnotations(sb, indentLevel);
        sb.append(getVisibility().getValue());
        if(isStatic())
            sb.append("static ");
        if(isFinal())
            sb.append("final ");
        sb.append("interface ");
        sb.append(getType().getShortName());
        if(getSuperInterfaceTypes().size() > 0)
        {
            sb.append(" extends ");
            boolean comma = false;
            FullyQualifiedJavaType fqjt;
            for(Iterator iterator2 = getSuperInterfaceTypes().iterator(); iterator2.hasNext(); sb.append(fqjt.getShortName()))
            {
                fqjt = (FullyQualifiedJavaType)iterator2.next();
                if(comma)
                    sb.append(", ");
                else
                    comma = true;
            }

        }
        sb.append(" {");
        indentLevel++;
        for(Iterator mtdIter = getMethods().iterator(); mtdIter.hasNext();)
        {
            OutputUtilities.newLine(sb);
            Method method = (Method)mtdIter.next();
            sb.append(method.getFormattedContent(indentLevel, true));
            if(mtdIter.hasNext())
                OutputUtilities.newLine(sb);
        }

        indentLevel--;
        OutputUtilities.newLine(sb);
        OutputUtilities.javaIndent(sb, indentLevel);
        sb.append('}');
        return sb.toString();
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

    public FullyQualifiedJavaType getSuperClass()
    {
        return null;
    }

    public Set getSuperInterfaceTypes()
    {
        return superInterfaceTypes;
    }

    public boolean isJavaInterface()
    {
        return true;
    }

    public boolean isJavaEnumeration()
    {
        return false;
    }

    public void addFileCommentLine(String commentLine)
    {
        fileCommentLines.add(commentLine);
    }

    public List getFileCommentLines()
    {
        return fileCommentLines;
    }

    public void addImportedTypes(Set importedTypes)
    {
        this.importedTypes.addAll(importedTypes);
    }

    private Set importedTypes;
    private FullyQualifiedJavaType type;
    private Set superInterfaceTypes;
    private List methods;
    private List fileCommentLines;
}
