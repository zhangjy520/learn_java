// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SelectElement.java

package com.fap.generartor.api;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.fap.generartor.api:
//            JDBCParameter

public class SelectElement
{

    public SelectElement()
    {
        jdbcParameters = new ArrayList();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getParameterType()
    {
        return parameterType;
    }

    public void setParameterType(String parameterType)
    {
        this.parameterType = parameterType;
    }

    public String getResultType()
    {
        return resultType;
    }

    public void setResultType(String resultType)
    {
        this.resultType = resultType;
    }

    public String getProcedureFullName()
    {
        return procedureFullName;
    }

    public void setProcedureFullName(String procedureFullName)
    {
        this.procedureFullName = procedureFullName;
    }

    public List getJdbcParameters()
    {
        return jdbcParameters;
    }

    public void setJdbcParameters(List jdbcParameters)
    {
        this.jdbcParameters = jdbcParameters;
    }

    public void addJdbcParameter(JDBCParameter jdbcParameter)
    {
        jdbcParameters.add(jdbcParameter);
    }

    String name;
    String parameterType;
    String resultType;
    String procedureFullName;
    List jdbcParameters;
}
