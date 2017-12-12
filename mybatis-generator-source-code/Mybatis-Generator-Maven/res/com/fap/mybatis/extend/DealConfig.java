// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DealConfig.java

package com.fap.mybatis.extend;

import java.io.*;
import java.util.ArrayList;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.config.*;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.XMLParserException;

public class DealConfig
{

    public DealConfig()
    {
    }

    public static void main(String args[])
        throws IOException, XMLParserException
    {
        String configfile = "D:\\work\\xpayment3.0\\fap-mybatis-generator-core-1.3.0\\src\\main\\resources\\generatorConfigSomeTable.xml";
        File configurationFile = new File(configfile);
        java.util.List warnings = new ArrayList();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configurationFile);
        config.getContext("DB2Tables").cleanTableConfig();
        TableConfiguration tc = new TableConfiguration(config.getContext("DB2Tables"));
        tc.setTableName("PER_CST_INF");
        tc.setDomainObjectName("perCstInf");
        ColumnOverride columnOverride = new ColumnOverride("PER_CSTNAME");
        columnOverride.setJavaProperty("cstname");
        tc.addColumnOverride(columnOverride);
        config.getContext("DB2Tables").addTableConfiguration(tc);
        Document doc = config.toDocument();
        System.out.println(doc.getFormattedContent());
    }
}
