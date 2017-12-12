// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MapperAPI.java

package com.fap.generartor.api;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.fap.generartor.api:
//            Method, Java

public class MapperAPI
{

    public MapperAPI()
    {
        imports = new ArrayList();
        methods = new ArrayList();
        inParameterClasses = new ArrayList();
        outParameterClasses = new ArrayList();
        recordClasses = new ArrayList();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List getMethods()
    {
        return methods;
    }

    public void setMethods(List methods)
    {
        this.methods = methods;
    }

    public String getPackages()
    {
        return packages;
    }

    public void setPackages(String packages)
    {
        this.packages = packages;
    }

    public List getImports()
    {
        return imports;
    }

    public void setImports(List imports)
    {
        this.imports = imports;
    }

    public void addMethod(Method method)
    {
        methods.add(method);
    }

    public List getInParameterClasses()
    {
        return inParameterClasses;
    }

    public void setInParameterClasss(List inParameterClasses)
    {
        this.inParameterClasses = inParameterClasses;
    }

    public List getOutParameterClasses()
    {
        return outParameterClasses;
    }

    public void setOutParameterClasses(List outParameterClasses)
    {
        this.outParameterClasses = outParameterClasses;
    }

    public void addInParameterClass(Java inParameterClass)
    {
        inParameterClasses.add(inParameterClass);
    }

    public void addOutParameterClass(Java outParameterClass)
    {
        outParameterClasses.add(outParameterClass);
    }

    public List getRecordClasses()
    {
        return recordClasses;
    }

    public void setRecordClasses(List recordClasses)
    {
        this.recordClasses = recordClasses;
    }

    public void addRecordClass(Java recordClass)
    {
        recordClasses.add(recordClass);
    }

    public void addImport(String imp)
    {
        imports.add(imp);
    }

    String name;
    String packages;
    List imports;
    List inParameterClasses;
    List outParameterClasses;
    List recordClasses;
    List methods;
}
