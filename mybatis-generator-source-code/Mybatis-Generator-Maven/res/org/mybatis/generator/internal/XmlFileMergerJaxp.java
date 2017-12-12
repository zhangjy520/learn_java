// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XmlFileMergerJaxp.java

package org.mybatis.generator.internal;

import java.io.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.util.messages.Messages;
import org.w3c.dom.*;
import org.xml.sax.*;

// Referenced classes of package org.mybatis.generator.internal:
//            DomWriter

public class XmlFileMergerJaxp
{
    private static class NullEntityResolver
        implements EntityResolver
    {

        public InputSource resolveEntity(String publicId, String systemId)
            throws SAXException, IOException
        {
            StringReader sr = new StringReader("");
            return new InputSource(sr);
        }

        private NullEntityResolver()
        {
        }

        NullEntityResolver(NullEntityResolver nullentityresolver)
        {
            this();
        }
    }


    private XmlFileMergerJaxp()
    {
    }

    public static String getMergedSource(GeneratedXmlFile generatedXmlFile, File existingFile)
        throws ShellException
    {
        Document existingDocument;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setExpandEntityReferences(false);
        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setEntityResolver(new NullEntityResolver(null));
        existingDocument = builder.parse(existingFile);
        StringReader sr = new StringReader(generatedXmlFile.getFormattedContent());
        Document newDocument = builder.parse(new InputSource(sr));
        DocumentType newDocType = newDocument.getDoctype();
        DocumentType existingDocType = existingDocument.getDoctype();
        if(!newDocType.getName().equals(existingDocType.getName()))
            throw new ShellException(Messages.getString("Warning.12", existingFile.getName()));
        Element existingRootElement = existingDocument.getDocumentElement();
        Element newRootElement = newDocument.getDocumentElement();
        NamedNodeMap attributes = existingRootElement.getAttributes();
        int attributeCount = attributes.getLength();
        for(int i = attributeCount - 1; i >= 0; i--)
        {
            Node node = attributes.item(i);
            existingRootElement.removeAttribute(node.getNodeName());
        }

        attributes = newRootElement.getAttributes();
        attributeCount = attributes.getLength();
        for(int i = 0; i < attributeCount; i++)
        {
            Node node = attributes.item(i);
            existingRootElement.setAttribute(node.getNodeName(), node.getNodeValue());
        }

        List nodesToDelete = new ArrayList();
        NodeList children = existingRootElement.getChildNodes();
        int length = children.getLength();
        for(int i = 0; i < length; i++)
        {
            Node node = children.item(i);
            if(isGeneratedNode(node))
                nodesToDelete.add(node);
            else
            if(isWhiteSpace(node) && isGeneratedNode(children.item(i + 1)))
                nodesToDelete.add(node);
        }

        Node node;
        for(Iterator iterator = nodesToDelete.iterator(); iterator.hasNext(); existingRootElement.removeChild(node))
            node = (Node)iterator.next();

        children = newRootElement.getChildNodes();
        length = children.getLength();
        Node firstChild = existingRootElement.getFirstChild();
        for(int i = 0; i < length; i++)
        {
            Node node = children.item(i);
            if(i == length - 1 && isWhiteSpace(node))
                break;
            Node newNode = existingDocument.importNode(node, true);
            if(firstChild == null)
                existingRootElement.appendChild(newNode);
            else
                existingRootElement.insertBefore(newNode, firstChild);
        }

        return prettyPrint(existingDocument);
        Exception e;
        e;
        throw new ShellException(Messages.getString("Warning.13", existingFile.getName()), e);
    }

    private static String prettyPrint(Document document)
        throws ShellException
    {
        DomWriter dw = new DomWriter();
        String s = dw.toString(document);
        return s;
    }

    private static boolean isGeneratedNode(Node node)
    {
        boolean rc = false;
        if(node != null && node.getNodeType() == 1)
        {
            Element element = (Element)node;
            String id = element.getAttribute("id");
            if(id != null)
            {
                String as[];
                int k = (as = MergeConstants.OLD_XML_ELEMENT_PREFIXES).length;
                for(int j = 0; j < k; j++)
                {
                    String prefix = as[j];
                    if(!id.startsWith(prefix))
                        continue;
                    rc = true;
                    break;
                }

            }
            if(!rc)
            {
                NodeList children = node.getChildNodes();
                int length = children.getLength();
                for(int i = 0; i < length; i++)
                {
                    Node childNode = children.item(i);
                    if(isWhiteSpace(childNode))
                        continue;
                    if(childNode.getNodeType() != 8)
                        break;
                    Comment comment = (Comment)childNode;
                    String commentData = comment.getData();
                    String as1[];
                    int i1 = (as1 = MergeConstants.OLD_ELEMENT_TAGS).length;
                    for(int l = 0; l < i1; l++)
                    {
                        String tag = as1[l];
                        if(!commentData.contains(tag))
                            continue;
                        rc = true;
                        break;
                    }

                }

            }
        }
        return rc;
    }

    private static boolean isWhiteSpace(Node node)
    {
        boolean rc = false;
        if(node != null && node.getNodeType() == 3)
        {
            Text tn = (Text)node;
            if(tn.getData().trim().length() == 0)
                rc = true;
        }
        return rc;
    }
}
