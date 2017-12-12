// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Method.java

package com.fap.generartor.api;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.fap.generartor.api:
//            Parameter

public class Method
{

    public Method()
    {
        parameters = new ArrayList();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List getParameters()
    {
        return parameters;
    }

    public void setParameters(List parameters)
    {
        this.parameters = parameters;
    }

    public void addParameters(Parameter parameter)
    {
        parameters.add(parameter);
    }

    public String getReturnType()
    {
        return returnType;
    }

    public void setReturnType(String returnType)
    {
        this.returnType = returnType;
    }

    String name;
    String returnType;
    List parameters;
}
