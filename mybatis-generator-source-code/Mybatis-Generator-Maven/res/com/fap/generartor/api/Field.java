// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Field.java

package com.fap.generartor.api;

import java.io.PrintStream;

// Referenced classes of package com.fap.generartor.api:
//            PlugInAPI

public class Field
{

    public Field(String name, String type)
    {
        this.name = name;
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public static void main(String args[])
    {
        PlugInAPI aa = new PlugInAPI("D:\\bjrrtx\\workspace\\trunk\\mysql-5.1.9.jar", "jdbc:mysql://127.0.0.1:3306/project_manage", "sa", "123", true, "", "", "", "", "", "");
        System.out.println(aa.getTableNames().toString());
    }

    String name;
    String type;
}
