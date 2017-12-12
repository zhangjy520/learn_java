// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TextElement.java

package org.mybatis.generator.api.dom.xml;

import org.mybatis.generator.api.dom.OutputUtilities;

// Referenced classes of package org.mybatis.generator.api.dom.xml:
//            Element

public class TextElement extends Element
{

    public TextElement(String content)
    {
        this.content = content;
    }

    public String getFormattedContent(int indentLevel)
    {
        StringBuilder sb = new StringBuilder();
        OutputUtilities.xmlIndent(sb, indentLevel);
        sb.append(content);
        return sb.toString();
    }

    private String content;
}
