// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Java.java

package com.fap.generartor.api;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.fap.generartor.api:
//            PlugInAPI, Method, Field

public class Java
{

    public Java()
    {
        imports = new ArrayList();
        fields = new ArrayList();
        methods = new ArrayList();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List getFields()
    {
        return fields;
    }

    public void setFields(List fields)
    {
        this.fields = fields;
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

    public void addField(Field field)
    {
        fields.add(field);
    }

    public void addImport(String imp)
    {
        imports.add(imp);
    }

    public static void main(String args[])
    {
        PlugInAPI aa = new PlugInAPI("D:\\bjrrtx\\workspace\\trunk\\mysql-5.1.9.jar", "jdbc:mysql://127.0.0.1:3306/project_manage", "sa", "123", true, "", "", "", "", "", "");
        System.out.println(aa.getTableNames().toString());
    }

    String name;
    String packages;
    List imports;
    List fields;
    List methods;
}
