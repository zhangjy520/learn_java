// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LogFactory.java

package org.mybatis.generator.logging;


// Referenced classes of package org.mybatis.generator.logging:
//            AbstractLogFactory, JdkLoggingImpl, LogFactory, Log

private static class LogFactory$JdkLoggingLogFactory
    implements AbstractLogFactory
{

    public Log getLog(Class clazz)
    {
        return new JdkLoggingImpl(clazz);
    }

    private LogFactory$JdkLoggingLogFactory()
    {
    }

    LogFactory$JdkLoggingLogFactory(LogFactory$JdkLoggingLogFactory logfactory$jdklogginglogfactory)
    {
        this();
    }
}
