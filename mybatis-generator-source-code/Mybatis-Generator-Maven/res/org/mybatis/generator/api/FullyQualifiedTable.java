// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FullyQualifiedTable.java

package org.mybatis.generator.api;

import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.util.*;

public class FullyQualifiedTable
{

    public FullyQualifiedTable(String introspectedCatalog, String introspectedSchema, String introspectedTableName, String domainObjectName, String alias, boolean ignoreQualifiersAtRuntime, String runtimeCatalog, 
            String runtimeSchema, String runtimeTableName, boolean delimitIdentifiers, Context context)
    {
        this.introspectedCatalog = introspectedCatalog;
        this.introspectedSchema = introspectedSchema;
        this.introspectedTableName = introspectedTableName;
        this.domainObjectName = domainObjectName;
        this.ignoreQualifiersAtRuntime = ignoreQualifiersAtRuntime;
        this.runtimeCatalog = runtimeCatalog;
        this.runtimeSchema = runtimeSchema;
        this.runtimeTableName = runtimeTableName;
        if(alias == null)
            this.alias = null;
        else
            this.alias = alias.trim();
        beginningDelimiter = delimitIdentifiers ? context.getBeginningDelimiter() : "";
        endingDelimiter = delimitIdentifiers ? context.getEndingDelimiter() : "";
    }

    public String getIntrospectedCatalog()
    {
        return introspectedCatalog;
    }

    public String getIntrospectedSchema()
    {
        return introspectedSchema;
    }

    public String getIntrospectedTableName()
    {
        return introspectedTableName;
    }

    public String getFullyQualifiedTableNameAtRuntime()
    {
        StringBuilder localCatalog = new StringBuilder();
        if(!ignoreQualifiersAtRuntime)
            if(StringUtility.stringHasValue(runtimeCatalog))
                localCatalog.append(runtimeCatalog);
            else
            if(StringUtility.stringHasValue(introspectedCatalog))
                localCatalog.append(introspectedCatalog);
        if(localCatalog.length() > 0)
            addDelimiters(localCatalog);
        StringBuilder localSchema = new StringBuilder();
        if(!ignoreQualifiersAtRuntime)
            if(StringUtility.stringHasValue(runtimeSchema))
                localSchema.append(runtimeSchema);
            else
            if(StringUtility.stringHasValue(introspectedSchema))
                localSchema.append(introspectedSchema);
        if(localSchema.length() > 0)
            addDelimiters(localSchema);
        StringBuilder localTableName = new StringBuilder();
        if(StringUtility.stringHasValue(runtimeTableName))
            localTableName.append(runtimeTableName);
        else
            localTableName.append(introspectedTableName);
        addDelimiters(localTableName);
        return StringUtility.composeFullyQualifiedTableName(localCatalog.toString(), localSchema.toString(), localTableName.toString(), '.');
    }

    public String getAliasedFullyQualifiedTableNameAtRuntime()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getFullyQualifiedTableNameAtRuntime());
        if(StringUtility.stringHasValue(alias))
        {
            sb.append(' ');
            sb.append(alias);
        }
        return sb.toString();
    }

    public String getIbatis2SqlMapNamespace()
    {
        String localCatalog = StringUtility.stringHasValue(runtimeCatalog) ? runtimeCatalog : introspectedCatalog;
        String localSchema = StringUtility.stringHasValue(runtimeSchema) ? runtimeSchema : introspectedSchema;
        String localTable = StringUtility.stringHasValue(runtimeTableName) ? runtimeTableName : introspectedTableName;
        return StringUtility.composeFullyQualifiedTableName(ignoreQualifiersAtRuntime ? null : localCatalog, ignoreQualifiersAtRuntime ? null : localSchema, localTable, '_');
    }

    public String getDomainObjectName()
    {
        if(StringUtility.stringHasValue(domainObjectName))
            return domainObjectName;
        if(StringUtility.stringHasValue(runtimeTableName))
            return JavaBeansUtil.getCamelCaseString(runtimeTableName, true);
        else
            return JavaBeansUtil.getCamelCaseString(introspectedTableName, true);
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(!(obj instanceof FullyQualifiedTable))
            return false;
        FullyQualifiedTable other = (FullyQualifiedTable)obj;
        return EqualsUtil.areEqual(introspectedTableName, other.introspectedTableName) && EqualsUtil.areEqual(introspectedCatalog, other.introspectedCatalog) && EqualsUtil.areEqual(introspectedSchema, other.introspectedSchema);
    }

    public int hashCode()
    {
        int result = 23;
        result = HashCodeUtil.hash(result, introspectedTableName);
        result = HashCodeUtil.hash(result, introspectedCatalog);
        result = HashCodeUtil.hash(result, introspectedSchema);
        return result;
    }

    public String toString()
    {
        return StringUtility.composeFullyQualifiedTableName(introspectedCatalog, introspectedSchema, introspectedTableName, '.');
    }

    public String getAlias()
    {
        return alias;
    }

    public String getSubPackage()
    {
        StringBuilder sb = new StringBuilder();
        if(!ignoreQualifiersAtRuntime)
        {
            if(StringUtility.stringHasValue(runtimeCatalog))
            {
                sb.append('.');
                sb.append(runtimeCatalog.toLowerCase());
            } else
            if(StringUtility.stringHasValue(introspectedCatalog))
            {
                sb.append('.');
                sb.append(introspectedCatalog.toLowerCase());
            }
            if(StringUtility.stringHasValue(runtimeSchema))
            {
                sb.append('.');
                sb.append(runtimeSchema.toLowerCase());
            } else
            if(StringUtility.stringHasValue(introspectedSchema))
            {
                sb.append('.');
                sb.append(introspectedSchema.toLowerCase());
            }
        }
        return sb.toString();
    }

    private void addDelimiters(StringBuilder sb)
    {
        if(StringUtility.stringHasValue(beginningDelimiter))
            sb.insert(0, beginningDelimiter);
        if(StringUtility.stringHasValue(endingDelimiter))
            sb.append(endingDelimiter);
    }

    private String introspectedCatalog;
    private String introspectedSchema;
    private String introspectedTableName;
    private String runtimeCatalog;
    private String runtimeSchema;
    private String runtimeTableName;
    private String domainObjectName;
    private String alias;
    private boolean ignoreQualifiersAtRuntime;
    private String beginningDelimiter;
    private String endingDelimiter;
}
