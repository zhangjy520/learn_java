// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RootClassInfo.java

package org.mybatis.generator.codegen;

import java.beans.*;
import java.util.*;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.ObjectFactory;
import org.mybatis.generator.internal.util.messages.Messages;

public class RootClassInfo
{

    public static RootClassInfo getInstance(String className, List warnings)
    {
        RootClassInfo classInfo = (RootClassInfo)rootClassInfoMap.get(className);
        if(classInfo == null)
        {
            classInfo = new RootClassInfo(className, warnings);
            rootClassInfoMap.put(className, classInfo);
        }
        return classInfo;
    }

    private RootClassInfo(String className, List warnings)
    {
        this.className = className;
        this.warnings = warnings;
        if(className == null)
            return;
        try
        {
            Class clazz = ObjectFactory.externalClassForName(className);
            BeanInfo bi = Introspector.getBeanInfo(clazz);
            propertyDescriptors = bi.getPropertyDescriptors();
        }
        catch(Exception e)
        {
            propertyDescriptors = null;
            warnings.add(Messages.getString("Warning.20", className));
        }
    }

    public boolean containsProperty(IntrospectedColumn introspectedColumn)
    {
        if(propertyDescriptors == null)
            return false;
        boolean found = false;
        String propertyName = introspectedColumn.getJavaProperty();
        String propertyType = introspectedColumn.getFullyQualifiedJavaType().getFullyQualifiedName();
        for(int i = 0; i < propertyDescriptors.length; i++)
        {
            PropertyDescriptor propertyDescriptor = propertyDescriptors[i];
            if(!propertyDescriptor.getName().equals(propertyName))
                continue;
            if(!propertyDescriptor.getPropertyType().getName().equals(propertyType))
                warnings.add(Messages.getString("Warning.21", propertyName, className, propertyType));
            else
            if(propertyDescriptor.getReadMethod() == null)
                warnings.add(Messages.getString("Warning.22", propertyName, className));
            else
            if(propertyDescriptor.getWriteMethod() == null)
                warnings.add(Messages.getString("Warning.23", propertyName, className));
            else
                found = true;
            break;
        }

        return found;
    }

    private static Map rootClassInfoMap = Collections.synchronizedMap(new HashMap());
    private PropertyDescriptor propertyDescriptors[];
    private String className;
    private List warnings;

}
