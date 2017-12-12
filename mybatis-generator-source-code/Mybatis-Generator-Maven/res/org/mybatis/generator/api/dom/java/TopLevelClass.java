// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TopLevelClass.java

package org.mybatis.generator.api.dom.java;

import java.util.*;
import org.mybatis.generator.api.dom.OutputUtilities;
import org.mybatis.generator.internal.util.StringUtility;

// Referenced classes of package org.mybatis.generator.api.dom.java:
//            InnerClass, CompilationUnit, FullyQualifiedJavaType

public class TopLevelClass extends InnerClass
    implements CompilationUnit
{

    public TopLevelClass(FullyQualifiedJavaType type)
    {
        super(type);
        importedTypes = new TreeSet();
        fileCommentLines = new ArrayList();
    }

    public TopLevelClass(String typeName)
    {
        this(new FullyQualifiedJavaType(typeName));
    }

    public Set getImportedTypes()
    {
        return Collections.unmodifiableSet(importedTypes);
    }

    public void addImportedType(FullyQualifiedJavaType importedType)
    {
        if(importedType != null && importedType.isExplicitlyImported() && !importedType.getPackageName().equals(getType().getPackageName()))
            importedTypes.add(importedType);
    }

    public String getFormattedContent()
    {
        StringBuilder sb = new StringBuilder();
        for(Iterator iterator = fileCommentLines.iterator(); iterator.hasNext(); OutputUtilities.newLine(sb))
        {
            String fileCommentLine = (String)iterator.next();
            sb.append(fileCommentLine);
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
        sb.append(super.getFormattedContent(0));
        return sb.toString();
    }

    public boolean isJavaInterface()
    {
        return false;
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
    private List fileCommentLines;
}
