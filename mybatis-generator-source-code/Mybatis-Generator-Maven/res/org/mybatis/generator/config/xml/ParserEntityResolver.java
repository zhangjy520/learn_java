// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ParserEntityResolver.java

package org.mybatis.generator.config.xml;

import java.io.IOException;
import org.xml.sax.*;

public class ParserEntityResolver
    implements EntityResolver
{

    public ParserEntityResolver()
    {
    }

    public InputSource resolveEntity(String publicId, String systemId)
        throws SAXException, IOException
    {
        if("-//Apache Software Foundation//DTD Apache iBATIS Ibator Configuration 1.0//EN".equalsIgnoreCase(publicId))
        {
            java.io.InputStream is = getClass().getClassLoader().getResourceAsStream("org/mybatis/generator/config/xml/ibator-config_1_0.dtd");
            InputSource ins = new InputSource(is);
            return ins;
        }
        if("-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN".equalsIgnoreCase(publicId))
        {
            java.io.InputStream is = getClass().getClassLoader().getResourceAsStream("org/mybatis/generator/config/xml/mybatis-generator-config_1_0.dtd");
            InputSource ins = new InputSource(is);
            return ins;
        } else
        {
            return null;
        }
    }
}
