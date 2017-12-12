// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RenameExampleClassPlugin.java

package org.mybatis.generator.plugins;

import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

public class RenameExampleClassPlugin extends PluginAdapter
{

    public RenameExampleClassPlugin()
    {
    }

    public boolean validate(List warnings)
    {
        searchString = properties.getProperty("searchString");
        replaceString = properties.getProperty("replaceString");
        boolean valid = StringUtility.stringHasValue(searchString) && StringUtility.stringHasValue(replaceString);
        if(valid)
        {
            pattern = Pattern.compile(searchString);
        } else
        {
            if(!StringUtility.stringHasValue(searchString))
                warnings.add(Messages.getString("ValidationError.18", "RenameExampleClassPlugin", "searchString"));
            if(!StringUtility.stringHasValue(replaceString))
                warnings.add(Messages.getString("ValidationError.18", "RenameExampleClassPlugin", "replaceString"));
        }
        return valid;
    }

    public void initialized(IntrospectedTable introspectedTable)
    {
        String oldType = introspectedTable.getExampleType();
        Matcher matcher = pattern.matcher(oldType);
        oldType = matcher.replaceAll(replaceString);
        introspectedTable.setExampleType(oldType);
    }

    private String searchString;
    private String replaceString;
    private Pattern pattern;
}
