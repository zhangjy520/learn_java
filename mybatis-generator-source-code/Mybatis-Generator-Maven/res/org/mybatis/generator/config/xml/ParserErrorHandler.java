// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ParserErrorHandler.java

package org.mybatis.generator.config.xml;

import java.util.List;
import org.mybatis.generator.internal.util.messages.Messages;
import org.xml.sax.*;

public class ParserErrorHandler
    implements ErrorHandler
{

    public ParserErrorHandler(List warnings, List errors)
    {
        this.warnings = warnings;
        this.errors = errors;
    }

    public void warning(SAXParseException exception)
        throws SAXException
    {
        warnings.add(Messages.getString("Warning.7", Integer.toString(exception.getLineNumber()), exception.getMessage()));
    }

    public void error(SAXParseException exception)
        throws SAXException
    {
        errors.add(Messages.getString("RuntimeError.4", Integer.toString(exception.getLineNumber()), exception.getMessage()));
    }

    public void fatalError(SAXParseException exception)
        throws SAXException
    {
        errors.add(Messages.getString("RuntimeError.4", Integer.toString(exception.getLineNumber()), exception.getMessage()));
    }

    private List warnings;
    private List errors;
}
