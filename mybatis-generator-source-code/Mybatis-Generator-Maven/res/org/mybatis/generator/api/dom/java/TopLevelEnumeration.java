// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TopLevelEnumeration.java

package org.mybatis.generator.api.dom.java;

import java.util.*;
import org.mybatis.generator.api.dom.OutputUtilities;
import org.mybatis.generator.internal.util.messages.Messages;

// Referenced classes of package org.mybatis.generator.api.dom.java:
//            InnerEnum, CompilationUnit, FullyQualifiedJavaType

public class TopLevelEnumeration extends InnerEnum
    implements CompilationUnit
{

    public TopLevelEnumeration(FullyQualifiedJavaType type)
    {
        super(type);
        importedTypes = new TreeSet();
        fileCommentLines = new ArrayList();
    }

    public String getFormattedContent()
    {
        StringBuilder sb = new StringBuilder();
        for(Iterator iterator = fileCommentLines.iterator(); iterator.hasNext(); OutputUtilities.newLine(sb))
        {
            String fileCommentLine = (String)iterator.next();
            sb.append(fileCommentLine);
        }

        if(getType().getPackageName() != null && getType().getPackageName().length() > 0)
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

    public Set getImportedTypes()
    {
        return Collections.unmodifiableSet(importedTypes);
    }

    public FullyQualifiedJavaType getSuperClass()
    {
        throw new UnsupportedOperationException(Messages.getString("RuntimeError.11"));
    }

    public boolean isJavaInterface()
    {
        return false;
    }

    public boolean isJavaEnumeration()
    {
        return true;
    }

    public void addImportedType(FullyQualifiedJavaType importedType)
    {
        if(importedType.isExplicitlyImported() && !importedType.getPackageName().equals(getType().getPackageName()))
            importedTypes.add(importedType);
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
