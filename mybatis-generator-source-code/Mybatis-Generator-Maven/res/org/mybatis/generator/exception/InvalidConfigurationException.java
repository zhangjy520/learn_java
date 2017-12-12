// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InvalidConfigurationException.java

package org.mybatis.generator.exception;

import java.util.List;

public class InvalidConfigurationException extends Exception
{

    public InvalidConfigurationException(List errors)
    {
        this.errors = errors;
    }

    public List getErrors()
    {
        return errors;
    }

    static final long serialVersionUID = 0x44087ecc42991bb3L;
    private List errors;
}
