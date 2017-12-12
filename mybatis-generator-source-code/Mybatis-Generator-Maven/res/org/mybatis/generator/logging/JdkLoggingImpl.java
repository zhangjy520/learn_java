// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JdkLoggingImpl.java

package org.mybatis.generator.logging;

import java.util.logging.*;

// Referenced classes of package org.mybatis.generator.logging:
//            Log

public class JdkLoggingImpl
    implements Log
{

    public JdkLoggingImpl(Class clazz)
    {
        log = Logger.getLogger(clazz.getName());
    }

    public boolean isDebugEnabled()
    {
        return log.isLoggable(Level.FINE);
    }

    public void error(String s, Throwable e)
    {
        LogRecord lr = new LogRecord(Level.SEVERE, s);
        lr.setSourceClassName(log.getName());
        lr.setThrown(e);
        log.log(lr);
    }

    public void error(String s)
    {
        LogRecord lr = new LogRecord(Level.SEVERE, s);
        lr.setSourceClassName(log.getName());
        log.log(lr);
    }

    public void debug(String s)
    {
        LogRecord lr = new LogRecord(Level.FINE, s);
        lr.setSourceClassName(log.getName());
        log.log(lr);
    }

    public void warn(String s)
    {
        LogRecord lr = new LogRecord(Level.WARNING, s);
        lr.setSourceClassName(log.getName());
        log.log(lr);
    }

    private Logger log;
}
