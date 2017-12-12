// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LogFactory.java

package org.mybatis.generator.logging;


// Referenced classes of package org.mybatis.generator.logging:
//            AbstractLogFactory, Log4jImpl, LogFactory, Log

private static class LogFactory$Log4jLoggingLogFactory
    implements AbstractLogFactory
{

    public Log getLog(Class clazz)
    {
        return new Log4jImpl(clazz);
    }

    private LogFactory$Log4jLoggingLogFactory()
    {
    }

    LogFactory$Log4jLoggingLogFactory(LogFactory$Log4jLoggingLogFactory logfactory$log4jlogginglogfactory)
    {
        this();
    }
}
