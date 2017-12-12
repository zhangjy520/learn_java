// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DomWriter.java

package org.mybatis.generator.internal;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.util.messages.Messages;
import org.w3c.dom.*;

public class DomWriter
{

    public DomWriter()
    {
    }

    public synchronized String toString(Document document)
        throws ShellException
    {
        StringWriter sw = new StringWriter();
        printWriter = new PrintWriter(sw);
        write(document);
        String s = sw.toString();
        return s;
    }

    protected Attr[] sortAttributes(NamedNodeMap attrs)
    {
        int len = attrs == null ? 0 : attrs.getLength();
        Attr array[] = new Attr[len];
        for(int i = 0; i < len; i++)
            array[i] = (Attr)attrs.item(i);

        for(int i = 0; i < len - 1; i++)
        {
            String name = array[i].getNodeName();
            int index = i;
            for(int j = i + 1; j < len; j++)
            {
                String curName = array[j].getNodeName();
                if(curName.compareTo(name) < 0)
                {
                    name = curName;
                    index = j;
                }
            }

            if(index != i)
            {
                Attr temp = array[i];
                array[i] = array[index];
                array[index] = temp;
            }
        }

        return array;
    }

    protected void normalizeAndPrint(String s, boolean isAttValue)
    {
        int len = s == null ? 0 : s.length();
        for(int i = 0; i < len; i++)
        {
            char c = s.charAt(i);
            normalizeAndPrint(c, isAttValue);
        }

    }

    protected void normalizeAndPrint(char c, boolean isAttValue)
    {
        switch(c)
        {
        case 60: // '<'
            printWriter.print("&lt;");
            break;

        case 62: // '>'
            printWriter.print("&gt;");
            break;

        case 38: // '&'
            printWriter.print("&amp;");
            break;

        case 34: // '"'
            if(isAttValue)
                printWriter.print("&quot;");
            else
                printWriter.print('"');
            break;

        case 13: // '\r'
            printWriter.print("&#xD;");
            break;

        default:
            if(isXML11 && (c >= '\001' && c <= '\037' && c != '\t' && c != '\n' || c >= '\177' && c <= '\237' || c == '\u2028') || isAttValue && (c == '\t' || c == '\n'))
            {
                printWriter.print("&#x");
                printWriter.print(Integer.toHexString(c).toUpperCase());
                printWriter.print(';');
            } else
            {
                printWriter.print(c);
            }
            break;
        }
    }

    protected String getVersion(Document document)
    {
        if(document == null)
            return null;
        String version = null;
        Method getXMLVersion = null;
        try
        {
            getXMLVersion = document.getClass().getMethod("getXmlVersion", new Class[0]);
            if(getXMLVersion != null)
                version = (String)getXMLVersion.invoke(document, null);
        }
        catch(Exception exception) { }
        return version;
    }

    protected void writeAnyNode(Node node)
        throws ShellException
    {
        if(node == null)
            return;
        short type = node.getNodeType();
        switch(type)
        {
        case 9: // '\t'
            write((Document)node);
            break;

        case 10: // '\n'
            write((DocumentType)node);
            break;

        case 1: // '\001'
            write((Element)node);
            break;

        case 5: // '\005'
            write((EntityReference)node);
            break;

        case 4: // '\004'
            write((CDATASection)node);
            break;

        case 3: // '\003'
            write((Text)node);
            break;

        case 7: // '\007'
            write((ProcessingInstruction)node);
            break;

        case 8: // '\b'
            write((Comment)node);
            break;

        case 2: // '\002'
        case 6: // '\006'
        default:
            throw new ShellException(Messages.getString("RuntimeError.18", Short.toString(type)));
        }
    }

    protected void write(Document node)
        throws ShellException
    {
        isXML11 = "1.1".equals(getVersion(node));
        if(isXML11)
            printWriter.println("<?xml version=\"1.1\" encoding=\"UTF-8\"?>");
        else
            printWriter.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        printWriter.flush();
        write(node.getDoctype());
        write(node.getDocumentElement());
    }

    protected void write(DocumentType node)
        throws ShellException
    {
        printWriter.print("<!DOCTYPE ");
        printWriter.print(node.getName());
        String publicId = node.getPublicId();
        String systemId = node.getSystemId();
        if(publicId != null)
        {
            printWriter.print(" PUBLIC \"");
            printWriter.print(publicId);
            printWriter.print("\" \"");
            printWriter.print(systemId);
            printWriter.print('"');
        } else
        if(systemId != null)
        {
            printWriter.print(" SYSTEM \"");
            printWriter.print(systemId);
            printWriter.print('"');
        }
        String internalSubset = node.getInternalSubset();
        if(internalSubset != null)
        {
            printWriter.println(" [");
            printWriter.print(internalSubset);
            printWriter.print(']');
        }
        printWriter.println('>');
    }

    protected void write(Element node)
        throws ShellException
    {
        printWriter.print('<');
        printWriter.print(node.getNodeName());
        Attr attrs[] = sortAttributes(node.getAttributes());
        Attr aattr[];
        int j = (aattr = attrs).length;
        for(int i = 0; i < j; i++)
        {
            Attr attr = aattr[i];
            printWriter.print(' ');
            printWriter.print(attr.getNodeName());
            printWriter.print("=\"");
            normalizeAndPrint(attr.getNodeValue(), true);
            printWriter.print('"');
        }

        if(node.getChildNodes().getLength() == 0)
        {
            printWriter.print(" />");
            printWriter.flush();
        } else
        {
            printWriter.print('>');
            printWriter.flush();
            for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling())
                writeAnyNode(child);

            printWriter.print("</");
            printWriter.print(node.getNodeName());
            printWriter.print('>');
            printWriter.flush();
        }
    }

    protected void write(EntityReference node)
    {
        printWriter.print('&');
        printWriter.print(node.getNodeName());
        printWriter.print(';');
        printWriter.flush();
    }

    protected void write(CDATASection node)
    {
        printWriter.print("<![CDATA[");
        printWriter.print(node.getNodeValue());
        printWriter.print("]]>");
        printWriter.flush();
    }

    protected void write(Text node)
    {
        normalizeAndPrint(node.getNodeValue(), false);
        printWriter.flush();
    }

    protected void write(ProcessingInstruction node)
    {
        printWriter.print("<?");
        printWriter.print(node.getNodeName());
        String data = node.getNodeValue();
        if(data != null && data.length() > 0)
        {
            printWriter.print(' ');
            printWriter.print(data);
        }
        printWriter.print("?>");
        printWriter.flush();
    }

    protected void write(Comment node)
    {
        printWriter.print("<!--");
        String comment = node.getNodeValue();
        if(comment != null && comment.length() > 0)
            printWriter.print(comment);
        printWriter.print("-->");
        printWriter.flush();
    }

    protected PrintWriter printWriter;
    protected boolean isXML11;
}
