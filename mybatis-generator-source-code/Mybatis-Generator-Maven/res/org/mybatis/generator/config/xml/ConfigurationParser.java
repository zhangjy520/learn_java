// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConfigurationParser.java

package org.mybatis.generator.config.xml;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.util.messages.Messages;
import org.w3c.dom.*;
import org.xml.sax.*;

// Referenced classes of package org.mybatis.generator.config.xml:
//            ParserEntityResolver, ParserErrorHandler, IbatorConfigurationParser, MyBatisGeneratorConfigurationParser

public class ConfigurationParser
{

    public ConfigurationParser(List warnings)
    {
        this(null, warnings);
    }

    public ConfigurationParser(Properties properties, List warnings)
    {
        if(properties == null)
            this.properties = System.getProperties();
        else
            this.properties = properties;
        if(warnings == null)
            this.warnings = new ArrayList();
        else
            this.warnings = warnings;
        parseErrors = new ArrayList();
    }

    public Configuration parseConfiguration(File inputFile)
        throws IOException, XMLParserException
    {
        FileReader fr = new FileReader(inputFile);
        return parseConfiguration(((Reader) (fr)));
    }

    public Configuration parseConfiguration(Reader reader)
        throws IOException, XMLParserException
    {
        InputSource is = new InputSource(reader);
        return parseConfiguration(is);
    }

    public Configuration parseConfiguration(InputStream inputStream)
        throws IOException, XMLParserException
    {
        InputSource is = new InputSource(inputStream);
        return parseConfiguration(is);
    }

    private Configuration parseConfiguration(InputSource inputSource)
        throws IOException, XMLParserException
    {
        DocumentBuilderFactory factory;
        parseErrors.clear();
        factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        Configuration config;
        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setEntityResolver(new ParserEntityResolver());
        ParserErrorHandler handler = new ParserErrorHandler(warnings, parseErrors);
        builder.setErrorHandler(handler);
        Document document = null;
        try
        {
            document = builder.parse(inputSource);
        }
        catch(SAXParseException e)
        {
            throw new XMLParserException(parseErrors);
        }
        catch(SAXException e)
        {
            if(e.getException() == null)
                parseErrors.add(e.getMessage());
            else
                parseErrors.add(e.getException().getMessage());
        }
        if(parseErrors.size() > 0)
            throw new XMLParserException(parseErrors);
        Element rootNode = document.getDocumentElement();
        DocumentType docType = document.getDoctype();
        if(rootNode.getNodeType() == 1 && docType.getPublicId().equals("-//Apache Software Foundation//DTD Apache iBATIS Ibator Configuration 1.0//EN"))
            config = parseIbatorConfiguration(rootNode);
        else
        if(rootNode.getNodeType() == 1 && docType.getPublicId().equals("-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"))
            config = parseMyBatisGeneratorConfiguration(rootNode);
        else
            throw new XMLParserException(Messages.getString("RuntimeError.5"));
        if(parseErrors.size() > 0)
            throw new XMLParserException(parseErrors);
        return config;
        ParserConfigurationException e;
        e;
        parseErrors.add(e.getMessage());
        throw new XMLParserException(parseErrors);
    }

    private Configuration parseIbatorConfiguration(Element rootNode)
        throws XMLParserException
    {
        IbatorConfigurationParser parser = new IbatorConfigurationParser(properties);
        return parser.parseIbatorConfiguration(rootNode);
    }

    private Configuration parseMyBatisGeneratorConfiguration(Element rootNode)
        throws XMLParserException
    {
        MyBatisGeneratorConfigurationParser parser = new MyBatisGeneratorConfigurationParser(properties);
        return parser.parseConfiguration(rootNode);
    }

    private List warnings;
    private List parseErrors;
    private Properties properties;
}
