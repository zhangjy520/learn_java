// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TableConfig.java

package com.fap.generartor.api;

import java.util.ArrayList;
import java.util.List;

public class TableConfig
{
    public class Column
    {

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getFieldName()
        {
            return fieldName;
        }

        public void setFieldName(String fieldName)
        {
            this.fieldName = fieldName;
        }

        String name;
        String fieldName;
        final TableConfig this$0;

        public Column(String columnName, String fieldName)
        {
            this$0 = TableConfig.this;
            super();
            name = columnName;
            this.fieldName = fieldName;
        }
    }


    public TableConfig()
    {
        columns = new ArrayList();
    }

    public String getTableName()
    {
        return tableName;
    }

    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }

    public List getColumns()
    {
        return columns;
    }

    public void setColumns(List columns)
    {
        this.columns = columns;
    }

    public void addColumn(Column column)
    {
        columns.add(column);
    }

    public String getObjectName()
    {
        return objectName;
    }

    public void setObjectName(String objectName)
    {
        this.objectName = objectName;
    }

    String tableName;
    String objectName;
    List columns;
}
