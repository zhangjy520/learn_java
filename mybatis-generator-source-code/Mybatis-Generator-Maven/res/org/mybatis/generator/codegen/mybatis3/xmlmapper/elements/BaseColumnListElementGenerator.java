// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseColumnListElementGenerator.java

package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import java.util.Iterator;
import java.util.List;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.xml.*;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.config.Context;

// Referenced classes of package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements:
//            AbstractXmlElementGenerator

public class BaseColumnListElementGenerator extends AbstractXmlElementGenerator
{

    public BaseColumnListElementGenerator()
    {
    }

    public void addElements(XmlElement parentElement)
    {
        XmlElement answer = new XmlElement("sql");
        answer.addAttribute(new Attribute("id", introspectedTable.getBaseColumnListId()));
        context.getCommentGenerator().addComment(answer);
        StringBuilder sb = new StringBuilder();
        for(Iterator iter = introspectedTable.getNonBLOBColumns().iterator(); iter.hasNext();)
        {
            sb.append(MyBatis3FormattingUtilities.getSelectListPhrase((IntrospectedColumn)iter.next()));
            if(iter.hasNext())
                sb.append(", ");
            if(sb.length() > 80)
            {
                answer.addElement(new TextElement(sb.toString()));
                sb.setLength(0);
            }
        }

        if(sb.length() > 0)
            answer.addElement(new TextElement(sb.toString()));
        if(context.getPlugins().sqlMapBaseColumnListElementGenerated(answer, introspectedTable))
            parentElement.addElement(answer);
    }
}
