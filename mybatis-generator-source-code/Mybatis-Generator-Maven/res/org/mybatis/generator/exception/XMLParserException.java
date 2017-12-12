// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XMLParserException.java

package org.mybatis.generator.exception;

import java.util.ArrayList;
import java.util.List;

public class XMLParserException extends Exception
{

    public XMLParserException(List errors)
    {
        this.errors = errors;
    }

    public XMLParserException(String error)
    {
        super(error);
        errors = new ArrayList();
        errors.add(error);
    }

    public List getErrors()
    {
        return errors;
    }

    private static final long serialVersionUID = 0x47c8807946bf149dL;
    private List errors;
}
