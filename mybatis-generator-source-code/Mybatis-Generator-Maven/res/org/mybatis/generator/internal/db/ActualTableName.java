// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ActualTableName.java

package org.mybatis.generator.internal.db;

import org.mybatis.generator.internal.util.StringUtility;

public class ActualTableName
{

    public ActualTableName(String catalog, String schema, String tableName)
    {
        this.catalog = catalog;
        this.schema = schema;
        this.tableName = tableName;
        fullName = StringUtility.composeFullyQualifiedTableName(catalog, schema, tableName, '.');
    }

    public String getCatalog()
    {
        return catalog;
    }

    public String getSchema()
    {
        return schema;
    }

    public String getTableName()
    {
        return tableName;
    }

    public boolean equals(Object obj)
    {
        if(obj == null || !(obj instanceof ActualTableName))
            return false;
        else
            return obj.toString().equals(toString());
    }

    public int hashCode()
    {
        return fullName.hashCode();
    }

    public String toString()
    {
        return fullName;
    }

    private String tableName;
    private String catalog;
    private String schema;
    private String fullName;
}
