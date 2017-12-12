// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TableConfig.java

package com.fap.generartor.api;


// Referenced classes of package com.fap.generartor.api:
//            TableConfig

public class TableConfig$Column
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

    public TableConfig$Column(String columnName, String fieldName)
    {
        this$0 = TableConfig.this;
        super();
        name = columnName;
        this.fieldName = fieldName;
    }
}
