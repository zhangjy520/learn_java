// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ClassloaderUtility.java

package org.mybatis.generator.internal.util;

import java.io.File;
import java.net.*;
import java.util.*;
import org.mybatis.generator.internal.util.messages.Messages;

public class ClassloaderUtility
{

    private ClassloaderUtility()
    {
    }

    public static ClassLoader getCustomClassloader(List entries)
    {
        List urls = new ArrayList();
        if(entries != null)
        {
            for(Iterator iterator = entries.iterator(); iterator.hasNext();)
            {
                String classPathEntry = (String)iterator.next();
                File file = new File(classPathEntry);
                if(!file.exists())
                    throw new RuntimeException(Messages.getString("RuntimeError.9", classPathEntry));
                try
                {
                    urls.add(file.toURI().toURL());
                }
                catch(MalformedURLException e)
                {
                    throw new RuntimeException(Messages.getString("RuntimeError.9", classPathEntry));
                }
            }

        }
        ClassLoader parent = Thread.currentThread().getContextClassLoader();
        URLClassLoader ucl = new URLClassLoader((URL[])urls.toArray(new URL[urls.size()]), parent);
        return ucl;
    }
}
