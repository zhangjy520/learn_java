// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TypedPropertyHolder.java

package org.mybatis.generator.config;


// Referenced classes of package org.mybatis.generator.config:
//            PropertyHolder

public abstract class TypedPropertyHolder extends PropertyHolder
{

    public TypedPropertyHolder()
    {
    }

    public String getConfigurationType()
    {
        return configurationType;
    }

    public void setConfigurationType(String configurationType)
    {
        if(!"DEFAULT".equalsIgnoreCase(configurationType))
            this.configurationType = configurationType;
    }

    private String configurationType;
}
