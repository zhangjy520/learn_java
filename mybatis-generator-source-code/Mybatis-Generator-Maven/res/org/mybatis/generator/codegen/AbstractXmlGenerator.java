// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractXmlGenerator.java

package org.mybatis.generator.codegen;

import org.mybatis.generator.api.dom.xml.Document;

// Referenced classes of package org.mybatis.generator.codegen:
//            AbstractGenerator

public abstract class AbstractXmlGenerator extends AbstractGenerator
{

    public AbstractXmlGenerator()
    {
    }

    public abstract Document getDocument();
}
