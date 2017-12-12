// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GeneratedXmlFile.java

package org.mybatis.generator.api;

import org.mybatis.generator.api.dom.xml.Document;

// Referenced classes of package org.mybatis.generator.api:
//            GeneratedFile

public class GeneratedXmlFile extends GeneratedFile
{

    public GeneratedXmlFile(Document document, String fileName, String targetPackage, String targetProject, boolean isMergeable)
    {
        super(targetProject);
        this.document = document;
        this.fileName = fileName;
        this.targetPackage = targetPackage;
        this.isMergeable = isMergeable;
    }

    public String getFormattedContent()
    {
        return document.getFormattedContent();
    }

    public String getFileName()
    {
        return fileName;
    }

    public String getTargetPackage()
    {
        return targetPackage;
    }

    public boolean isMergeable()
    {
        return isMergeable;
    }

    private Document document;
    private String fileName;
    private String targetPackage;
    private boolean isMergeable;
}
