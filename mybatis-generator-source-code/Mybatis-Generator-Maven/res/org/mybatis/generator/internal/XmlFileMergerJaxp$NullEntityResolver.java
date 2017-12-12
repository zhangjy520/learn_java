// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XmlFileMergerJaxp.java

package org.mybatis.generator.internal;

import java.io.IOException;
import java.io.StringReader;
import org.xml.sax.*;

// Referenced classes of package org.mybatis.generator.internal:
//            XmlFileMergerJaxp

private static class XmlFileMergerJaxp$NullEntityResolver
    implements EntityResolver
{

    public InputSource resolveEntity(String publicId, String systemId)
        throws SAXException, IOException
    {
        StringReader sr = new StringReader("");
        return new InputSource(sr);
    }

    private XmlFileMergerJaxp$NullEntityResolver()
    {
    }

    XmlFileMergerJaxp$NullEntityResolver(XmlFileMergerJaxp$NullEntityResolver xmlfilemergerjaxp$nullentityresolver)
    {
        this();
    }
}
