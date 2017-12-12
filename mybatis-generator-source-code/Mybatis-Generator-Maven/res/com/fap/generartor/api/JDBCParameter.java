// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JDBCParameter.java

package com.fap.generartor.api;


public class JDBCParameter
{

    public JDBCParameter(String name, String mode, String jdbcType, String resultMap)
    {
        this.name = name;
        this.mode = mode;
        this.jdbcType = jdbcType;
        this.resultMap = resultMap;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getMode()
    {
        return mode;
    }

    public void setMode(String mode)
    {
        this.mode = mode;
    }

    public String getJdbcType()
    {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType)
    {
        this.jdbcType = jdbcType;
    }

    public String getResultMap()
    {
        return resultMap;
    }

    public void setResultMap(String resultMap)
    {
        this.resultMap = resultMap;
    }

    String name;
    String mode;
    String jdbcType;
    String resultMap;
}
