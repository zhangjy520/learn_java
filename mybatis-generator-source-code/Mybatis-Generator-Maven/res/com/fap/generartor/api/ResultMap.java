// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ResultMap.java

package com.fap.generartor.api;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.fap.generartor.api:
//            Result

public class ResultMap
{

    public ResultMap()
    {
        results = new ArrayList();
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public List getResults()
    {
        return results;
    }

    public void setResults(List results)
    {
        this.results = results;
    }

    public void addResult(Result result)
    {
        results.add(result);
    }

    String type;
    String id;
    List results;
}
