// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Messages.java

package org.mybatis.generator.internal.util.messages;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages
{

    private Messages()
    {
    }

    public static String getString(String key)
    {
        return RESOURCE_BUNDLE.getString(key);
        MissingResourceException e;
        e;
        return (new StringBuilder(String.valueOf('!'))).append(key).append('!').toString();
    }

    public static String getString(String key, String parm1)
    {
        return MessageFormat.format(RESOURCE_BUNDLE.getString(key), new Object[] {
            parm1
        });
        MissingResourceException e;
        e;
        return (new StringBuilder(String.valueOf('!'))).append(key).append('!').toString();
    }

    public static String getString(String key, String parm1, String parm2)
    {
        return MessageFormat.format(RESOURCE_BUNDLE.getString(key), new Object[] {
            parm1, parm2
        });
        MissingResourceException e;
        e;
        return (new StringBuilder(String.valueOf('!'))).append(key).append('!').toString();
    }

    public static String getString(String key, String parm1, String parm2, String parm3)
    {
        return MessageFormat.format(RESOURCE_BUNDLE.getString(key), new Object[] {
            parm1, parm2, parm3
        });
        MissingResourceException e;
        e;
        return (new StringBuilder(String.valueOf('!'))).append(key).append('!').toString();
    }

    private static final String BUNDLE_NAME = "org.mybatis.generator.internal.util.messages.messages";
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("org.mybatis.generator.internal.util.messages.messages");

}
