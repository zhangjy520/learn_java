// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExampleWhereClauseElementGenerator.java

package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import java.util.Iterator;
import java.util.List;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.xml.*;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.util.StringUtility;

// Referenced classes of package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements:
//            AbstractXmlElementGenerator

public class ExampleWhereClauseElementGenerator extends AbstractXmlElementGenerator
{

    public ExampleWhereClauseElementGenerator(boolean isForUpdateByExample)
    {
        this.isForUpdateByExample = isForUpdateByExample;
    }

    public void addElements(XmlElement parentElement)
    {
        XmlElement answer = new XmlElement("sql");
        if(isForUpdateByExample)
            answer.addAttribute(new Attribute("id", introspectedTable.getMyBatis3UpdateByExampleWhereClauseId()));
        else
            answer.addAttribute(new Attribute("id", introspectedTable.getExampleWhereClauseId()));
        context.getCommentGenerator().addComment(answer);
        XmlElement whereElement = new XmlElement("where");
        answer.addElement(whereElement);
        XmlElement outerForEachElement = new XmlElement("foreach");
        if(isForUpdateByExample)
            outerForEachElement.addAttribute(new Attribute("collection", "example.oredCriteria"));
        else
            outerForEachElement.addAttribute(new Attribute("collection", "oredCriteria"));
        outerForEachElement.addAttribute(new Attribute("item", "criteria"));
        outerForEachElement.addAttribute(new Attribute("separator", "or"));
        whereElement.addElement(outerForEachElement);
        XmlElement ifElement = new XmlElement("if");
        ifElement.addAttribute(new Attribute("test", "criteria.valid"));
        outerForEachElement.addElement(ifElement);
        XmlElement trimElement = new XmlElement("trim");
        trimElement.addAttribute(new Attribute("prefix", "("));
        trimElement.addAttribute(new Attribute("suffix", ")"));
        trimElement.addAttribute(new Attribute("prefixOverrides", "and"));
        ifElement.addElement(trimElement);
        trimElement.addElement(getMiddleForEachElement(null));
        for(Iterator iterator = introspectedTable.getNonBLOBColumns().iterator(); iterator.hasNext();)
        {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterator.next();
            if(StringUtility.stringHasValue(introspectedColumn.getTypeHandler()))
                trimElement.addElement(getMiddleForEachElement(introspectedColumn));
        }

        if(context.getPlugins().sqlMapExampleWhereClauseElementGenerated(answer, introspectedTable))
            parentElement.addElement(answer);
    }

    private XmlElement getMiddleForEachElement(IntrospectedColumn introspectedColumn)
    {
        StringBuilder sb = new StringBuilder();
        String criteriaAttribute;
        boolean typeHandled;
        String typeHandlerString;
        if(introspectedColumn == null)
        {
            criteriaAttribute = "criteria.criteria";
            typeHandled = false;
            typeHandlerString = null;
        } else
        {
            sb.setLength(0);
            sb.append("criteria.");
            sb.append(introspectedColumn.getJavaProperty());
            sb.append("Criteria");
            criteriaAttribute = sb.toString();
            typeHandled = true;
            sb.setLength(0);
            sb.append(",typeHandler=");
            sb.append(introspectedColumn.getTypeHandler());
            typeHandlerString = sb.toString();
        }
        XmlElement middleForEachElement = new XmlElement("foreach");
        middleForEachElement.addAttribute(new Attribute("collection", criteriaAttribute));
        middleForEachElement.addAttribute(new Attribute("item", "criterion"));
        XmlElement chooseElement = new XmlElement("choose");
        middleForEachElement.addElement(chooseElement);
        XmlElement when = new XmlElement("when");
        when.addAttribute(new Attribute("test", "criterion.noValue"));
        when.addElement(new TextElement("and ${criterion.condition}"));
        chooseElement.addElement(when);
        when = new XmlElement("when");
        when.addAttribute(new Attribute("test", "criterion.singleValue"));
        sb.setLength(0);
        sb.append("and ${criterion.condition} #{criterion.value");
        if(typeHandled)
            sb.append(typeHandlerString);
        sb.append('}');
        when.addElement(new TextElement(sb.toString()));
        chooseElement.addElement(when);
        when = new XmlElement("when");
        when.addAttribute(new Attribute("test", "criterion.betweenValue"));
        sb.setLength(0);
        sb.append("and ${criterion.condition} #{criterion.value");
        if(typeHandled)
            sb.append(typeHandlerString);
        sb.append("} and #{criterion.secondValue");
        if(typeHandled)
            sb.append(typeHandlerString);
        sb.append('}');
        when.addElement(new TextElement(sb.toString()));
        chooseElement.addElement(when);
        when = new XmlElement("when");
        when.addAttribute(new Attribute("test", "criterion.listValue"));
        when.addElement(new TextElement("and ${criterion.condition}"));
        XmlElement innerForEach = new XmlElement("foreach");
        innerForEach.addAttribute(new Attribute("collection", "criterion.value"));
        innerForEach.addAttribute(new Attribute("item", "listItem"));
        innerForEach.addAttribute(new Attribute("open", "("));
        innerForEach.addAttribute(new Attribute("close", ")"));
        innerForEach.addAttribute(new Attribute("separator", ","));
        sb.setLength(0);
        sb.append("#{listItem");
        if(typeHandled)
            sb.append(typeHandlerString);
        sb.append('}');
        innerForEach.addElement(new TextElement(sb.toString()));
        when.addElement(innerForEach);
        chooseElement.addElement(when);
        return middleForEachElement;
    }

    private boolean isForUpdateByExample;
}
