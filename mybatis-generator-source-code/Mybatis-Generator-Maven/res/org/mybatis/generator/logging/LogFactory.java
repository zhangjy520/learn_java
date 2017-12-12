// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LogFactory.java

package org.mybatis.generator.logging;

import org.mybatis.generator.internal.ObjectFactory;
import org.mybatis.generator.internal.util.messages.Messages;

// Referenced classes of package org.mybatis.generator.logging:
//            AbstractLogFactory, Log, JdkLoggingImpl, Log4jImpl

public class LogFactory
{
    private static class JdkLoggingLogFactory
        implements AbstractLogFactory
    {

        public Log getLog(Class clazz)
        {
            return new JdkLoggingImpl(clazz);
        }

        private JdkLoggingLogFactory()
        {
        }

        JdkLoggingLogFactory(JdkLoggingLogFactory jdklogginglogfactory)
        {
            this();
        }
    }

    private static class Log4jLoggingLogFactory
        implements AbstractLogFactory
    {

        public Log getLog(Class clazz)
        {
            return new Log4jImpl(clazz);
        }

        private Log4jLoggingLogFactory()
        {
        }

        Log4jLoggingLogFactory(Log4jLoggingLogFactory log4jlogginglogfactory)
        {
            this();
        }
    }


    public LogFactory()
    {
    }

    public static Log getLog(Class clazz)
    {
        return logFactory.getLog(clazz);
        Throwable t;
        t;
        throw new RuntimeException(Messages.getString("RuntimeError.21", clazz.getName(), t.getMessage()), t);
    }

    public static synchronized void forceJavaLogging()
    {
        logFactory = new JdkLoggingLogFactory(null);
    }

    public static void setLogFactory(AbstractLogFactory logFactory)
    {
        logFactory = logFactory;
    }

    private static AbstractLogFactory logFactory;

    static 
    {
        try
        {
            ObjectFactory.internalClassForName("org.apache.log4j.Logger");
            logFactory = new Log4jLoggingLogFactory(null);
        }
        catch(Exception e)
        {
            logFactory = new JdkLoggingLogFactory(null);
        }
    }
}
