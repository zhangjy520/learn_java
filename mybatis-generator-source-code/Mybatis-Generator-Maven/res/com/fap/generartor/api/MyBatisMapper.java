// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MyBatisMapper.java

package com.fap.generartor.api;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.fap.generartor.api:
//            SelectElement, ResultMap

public class MyBatisMapper
{

    public MyBatisMapper()
    {
        selectElements = new ArrayList();
        resultMaps = new ArrayList();
    }

    public String getNamespace()
    {
        return namespace;
    }

    public void setNamespace(String namespace)
    {
        this.namespace = namespace;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List getSelectElements()
    {
        return selectElements;
    }

    public void setSelectElements(List selectElements)
    {
        this.selectElements = selectElements;
    }

    public void addSelectElements(SelectElement selectElement)
    {
        selectElements.add(selectElement);
    }

    public List getResultMaps()
    {
        return resultMaps;
    }

    public void setResultMaps(List resultMaps)
    {
        this.resultMaps = resultMaps;
    }

    public void addResultMaps(ResultMap resultMap)
    {
        resultMaps.add(resultMap);
    }

    String namespace;
    String name;
    List selectElements;
    List resultMaps;
}
