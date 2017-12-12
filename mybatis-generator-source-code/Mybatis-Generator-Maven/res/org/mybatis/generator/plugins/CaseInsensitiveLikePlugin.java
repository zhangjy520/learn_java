// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CaseInsensitiveLikePlugin.java

package org.mybatis.generator.plugins;

import java.util.Iterator;
import java.util.List;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.ibatis2.Ibatis2FormattingUtilities;

public class CaseInsensitiveLikePlugin extends PluginAdapter
{

    public CaseInsensitiveLikePlugin()
    {
    }

    public boolean validate(List warnings)
    {
        return true;
    }

    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable)
    {
        InnerClass criteria = null;
        for(Iterator iterator = topLevelClass.getInnerClasses().iterator(); iterator.hasNext();)
        {
            InnerClass innerClass = (InnerClass)iterator.next();
            if("Criteria".equals(innerClass.getType().getShortName()))
            {
                criteria = innerClass;
                break;
            }
        }

        if(criteria == null)
            return true;
        for(Iterator iterator1 = introspectedTable.getNonBLOBColumns().iterator(); iterator1.hasNext();)
        {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterator1.next();
            if(introspectedColumn.isJdbcCharacterColumn() && introspectedColumn.isStringColumn())
            {
                Method method = new Method();
                method.setVisibility(JavaVisibility.PUBLIC);
                method.addParameter(new Parameter(introspectedColumn.getFullyQualifiedJavaType(), "value"));
                StringBuilder sb = new StringBuilder();
                sb.append(introspectedColumn.getJavaProperty());
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
                sb.insert(0, "and");
                sb.append("LikeInsensitive");
                method.setName(sb.toString());
                method.setReturnType(FullyQualifiedJavaType.getCriteriaInstance());
                sb.setLength(0);
                sb.append("addCriterion(\"upper(");
                sb.append(Ibatis2FormattingUtilities.getAliasedActualColumnName(introspectedColumn));
                sb.append(") like\", value.toUpperCase(), \"");
                sb.append(introspectedColumn.getJavaProperty());
                sb.append("\");");
                method.addBodyLine(sb.toString());
                method.addBodyLine("return this;");
                criteria.addMethod(method);
            }
        }

        return true;
    }
}
