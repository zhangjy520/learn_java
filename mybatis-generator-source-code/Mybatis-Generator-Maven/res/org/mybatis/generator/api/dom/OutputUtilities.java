// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OutputUtilities.java

package org.mybatis.generator.api.dom;

import java.util.*;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

public class OutputUtilities
{

    private OutputUtilities()
    {
    }

    public static void javaIndent(StringBuilder sb, int indentLevel)
    {
        for(int i = 0; i < indentLevel; i++)
            sb.append("    ");

    }

    public static void xmlIndent(StringBuilder sb, int indentLevel)
    {
        for(int i = 0; i < indentLevel; i++)
            sb.append("  ");

    }

    public static void newLine(StringBuilder sb)
    {
        sb.append(lineSeparator);
    }

    public static Set calculateImports(Set importedTypes)
    {
        StringBuilder sb = new StringBuilder();
        Set importStrings = new TreeSet();
        for(Iterator iterator = importedTypes.iterator(); iterator.hasNext();)
        {
            FullyQualifiedJavaType fqjt = (FullyQualifiedJavaType)iterator.next();
            for(Iterator iterator1 = fqjt.getImportList().iterator(); iterator1.hasNext(); importStrings.add(sb.toString()))
            {
                String importString = (String)iterator1.next();
                sb.setLength(0);
                sb.append("import ");
                sb.append(importString);
                sb.append(';');
            }

        }

        return importStrings;
    }

    private static final String lineSeparator;

    static 
    {
        String ls = System.getProperty("line.separator");
        if(ls == null)
            ls = "\n";
        lineSeparator = ls;
    }
}
