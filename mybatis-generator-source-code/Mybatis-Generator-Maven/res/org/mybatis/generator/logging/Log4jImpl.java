// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Log4jImpl.java

package org.mybatis.generator.logging;

import org.apache.log4j.Logger;

// Referenced classes of package org.mybatis.generator.logging:
//            Log

public class Log4jImpl
    implements Log
{

    public Log4jImpl(Class clazz)
    {
        log = Logger.getLogger(clazz);
    }

    public boolean isDebugEnabled()
    {
        return log.isDebugEnabled();
    }

    public void error(String s, Throwable e)
    {
        log.error(s, e);
    }

    public void error(String s)
    {
        log.error(s);
    }

    public void debug(String s)
    {
        log.debug(s);
    }

    public void warn(String s)
    {
        log.warn(s);
    }

    private Logger log;
}
