// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MergeTemplete.java

package com.fap.generartor.api;

import java.io.*;
import java.util.List;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

// Referenced classes of package com.fap.generartor.api:
//            Java, MapperAPI, MyBatisMapper

public class MergeTemplete
{

    public MergeTemplete()
    {
    }

    public static void merge(String templateName, Java classDefine, String javaFilePath)
    {
        Velocity.init();
        VelocityContext context = new VelocityContext();
        context.put("classDefine", classDefine);
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File((new StringBuilder(String.valueOf(javaFilePath))).append(classDefine.getName()).append(".java").toString())));
            org.apache.velocity.Template template = Velocity.getTemplate(templateName);
            Velocity.evaluate(context, writer, "", getStringFromStream(com/fap/generartor/api/MergeTemplete.getClassLoader().getResourceAsStream(templateName)));
            writer.flush();
            writer.close();
        }
        catch(IOException e1)
        {
            e1.printStackTrace();
        }
    }

    public static void mergeMapperAPI(String templateName, MapperAPI classDefine, String javaFilePath)
    {
        Velocity.init();
        VelocityContext context = new VelocityContext();
        context.put("mapperAPI", classDefine);
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File((new StringBuilder(String.valueOf(javaFilePath))).append(classDefine.getName()).append(".java").toString())));
            Velocity.evaluate(context, writer, "", getStringFromStream(com/fap/generartor/api/MergeTemplete.getClassLoader().getResourceAsStream(templateName)));
            writer.flush();
            writer.close();
        }
        catch(IOException e1)
        {
            e1.printStackTrace();
        }
    }

    public static void mergeXML(String templateName, MyBatisMapper mapperDefine, String xmlFilePath)
    {
        Velocity.init();
        VelocityContext context = new VelocityContext();
        context.put("mapper", mapperDefine);
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File((new StringBuilder(String.valueOf(xmlFilePath))).append(mapperDefine.getName()).append(".xml").toString())));
            Velocity.evaluate(context, writer, "", getStringFromStream(com/fap/generartor/api/MergeTemplete.getClassLoader().getResourceAsStream(templateName)));
            writer.flush();
            writer.close();
        }
        catch(IOException e1)
        {
            e1.printStackTrace();
        }
    }

    public static void mergeTableConfig(String templateName, List tableConfigs)
    {
        Velocity.init();
        VelocityContext context = new VelocityContext();
        context.put("tableConfigs", tableConfigs);
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("D:\\test\\tableConfig.xml")));
            Velocity.evaluate(context, writer, "", getStringFromStream(com/fap/generartor/api/MergeTemplete.getClassLoader().getResourceAsStream("tableConfig.vm")));
            writer.flush();
            writer.close();
        }
        catch(IOException e1)
        {
            e1.printStackTrace();
        }
    }

    public static String getStringFromStream(InputStream is)
    {
        if(is == null)
            return null;
        String s;
        InputStreamReader reader = new InputStreamReader(is);
        char buffer[] = new char[1024];
        StringWriter writer = new StringWriter();
        int bytes_read;
        while((bytes_read = reader.read(buffer)) != -1) 
            writer.write(buffer, 0, bytes_read);
        s = writer.toString();
        try
        {
            if(is != null)
                is.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return s;
        Exception e;
        e;
        e.printStackTrace();
        try
        {
            if(is != null)
                is.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        break MISSING_BLOCK_LABEL_128;
        Exception exception;
        exception;
        try
        {
            if(is != null)
                is.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        throw exception;
        return null;
    }
}
