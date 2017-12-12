// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PluginAggregator.java

package org.mybatis.generator.internal;

import java.util.*;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.Context;

public final class PluginAggregator
    implements Plugin
{

    public PluginAggregator()
    {
        plugins = new ArrayList();
    }

    public void addPlugin(Plugin plugin)
    {
        plugins.add(plugin);
    }

    public void setContext(Context context)
    {
        throw new UnsupportedOperationException();
    }

    public void setProperties(Properties properties)
    {
        throw new UnsupportedOperationException();
    }

    public boolean validate(List warnings)
    {
        throw new UnsupportedOperationException();
    }

    public boolean modelBaseRecordClassGenerated(TopLevelClass tlc, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.modelBaseRecordClassGenerated(tlc, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass tlc, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.modelRecordWithBLOBsClassGenerated(tlc, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapCountByExampleElementGenerated(XmlElement element, IntrospectedTable table)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.sqlMapCountByExampleElementGenerated(element, table))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapDeleteByExampleElementGenerated(XmlElement element, IntrospectedTable table)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.sqlMapDeleteByExampleElementGenerated(element, table))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapDeleteByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable table)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.sqlMapDeleteByPrimaryKeyElementGenerated(element, table))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean modelExampleClassGenerated(TopLevelClass tlc, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.modelExampleClassGenerated(tlc, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public List contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable)
    {
        List answer = new ArrayList();
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            List temp = plugin.contextGenerateAdditionalJavaFiles(introspectedTable);
            if(temp != null)
                answer.addAll(temp);
        }

        return answer;
    }

    public List contextGenerateAdditionalXmlFiles(IntrospectedTable introspectedTable)
    {
        List answer = new ArrayList();
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            List temp = plugin.contextGenerateAdditionalXmlFiles(introspectedTable);
            if(temp != null)
                answer.addAll(temp);
        }

        return answer;
    }

    public boolean modelPrimaryKeyClassGenerated(TopLevelClass tlc, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.modelPrimaryKeyClassGenerated(tlc, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapResultMapWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.sqlMapResultMapWithoutBLOBsElementGenerated(element, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapExampleWhereClauseElementGenerated(XmlElement element, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.sqlMapExampleWhereClauseElementGenerated(element, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.sqlMapInsertElementGenerated(element, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapResultMapWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.sqlMapResultMapWithBLOBsElementGenerated(element, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.sqlMapSelectByExampleWithoutBLOBsElementGenerated(element, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.sqlMapSelectByExampleWithBLOBsElementGenerated(element, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.sqlMapSelectByPrimaryKeyElementGenerated(element, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.sqlMapGenerated(sqlMap, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapUpdateByExampleSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.sqlMapUpdateByExampleSelectiveElementGenerated(element, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapUpdateByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.sqlMapUpdateByExampleWithBLOBsElementGenerated(element, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapUpdateByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.sqlMapUpdateByPrimaryKeySelectiveElementGenerated(element, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(element, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(element, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientCountByExampleMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientCountByExampleMethodGenerated(method, interfaze, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientCountByExampleMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientCountByExampleMethodGenerated(method, topLevelClass, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientDeleteByExampleMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientDeleteByExampleMethodGenerated(method, interfaze, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientDeleteByExampleMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientDeleteByExampleMethodGenerated(method, topLevelClass, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientDeleteByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientDeleteByPrimaryKeyMethodGenerated(method, topLevelClass, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientInsertMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientInsertMethodGenerated(method, interfaze, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientInsertMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientInsertMethodGenerated(method, topLevelClass, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientGenerated(interfaze, topLevelClass, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientSelectByExampleWithBLOBsMethodGenerated(method, interfaze, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientSelectByExampleWithBLOBsMethodGenerated(method, topLevelClass, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientSelectByExampleWithoutBLOBsMethodGenerated(method, interfaze, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientSelectByExampleWithoutBLOBsMethodGenerated(method, topLevelClass, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientSelectByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientSelectByPrimaryKeyMethodGenerated(method, topLevelClass, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientUpdateByExampleSelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientUpdateByExampleSelectiveMethodGenerated(method, interfaze, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientUpdateByExampleSelectiveMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientUpdateByExampleSelectiveMethodGenerated(method, topLevelClass, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientUpdateByExampleWithBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientUpdateByExampleWithBLOBsMethodGenerated(method, interfaze, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientUpdateByExampleWithBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientUpdateByExampleWithBLOBsMethodGenerated(method, topLevelClass, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientUpdateByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientUpdateByExampleWithoutBLOBsMethodGenerated(method, interfaze, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientUpdateByExampleWithoutBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientUpdateByExampleWithoutBLOBsMethodGenerated(method, topLevelClass, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientUpdateByPrimaryKeySelectiveMethodGenerated(method, interfaze, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientUpdateByPrimaryKeySelectiveMethodGenerated(method, topLevelClass, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(method, interfaze, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(method, topLevelClass, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(method, interfaze, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(method, topLevelClass, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public List contextGenerateAdditionalJavaFiles()
    {
        List answer = new ArrayList();
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            List temp = plugin.contextGenerateAdditionalJavaFiles();
            if(temp != null)
                answer.addAll(temp);
        }

        return answer;
    }

    public List contextGenerateAdditionalXmlFiles()
    {
        List answer = new ArrayList();
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            List temp = plugin.contextGenerateAdditionalXmlFiles();
            if(temp != null)
                answer.addAll(temp);
        }

        return answer;
    }

    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.sqlMapDocumentGenerated(document, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, org.mybatis.generator.api.Plugin.ModelClassType modelClassType)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.modelFieldGenerated(field, topLevelClass, introspectedColumn, introspectedTable, modelClassType))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, org.mybatis.generator.api.Plugin.ModelClassType modelClassType)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.modelGetterMethodGenerated(method, topLevelClass, introspectedColumn, introspectedTable, modelClassType))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, org.mybatis.generator.api.Plugin.ModelClassType modelClassType)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.modelSetterMethodGenerated(method, topLevelClass, introspectedColumn, introspectedTable, modelClassType))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.sqlMapInsertSelectiveElementGenerated(element, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientInsertSelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientInsertSelectiveMethodGenerated(method, interfaze, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientInsertSelectiveMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.clientInsertSelectiveMethodGenerated(method, topLevelClass, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public void initialized(IntrospectedTable introspectedTable)
    {
        Plugin plugin;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext(); plugin.initialized(introspectedTable))
            plugin = (Plugin)iterator.next();

    }

    public boolean sqlMapBaseColumnListElementGenerated(XmlElement element, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.sqlMapBaseColumnListElementGenerated(element, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapBlobColumnListElementGenerated(XmlElement element, IntrospectedTable introspectedTable)
    {
        boolean rc = true;
        for(Iterator iterator = plugins.iterator(); iterator.hasNext();)
        {
            Plugin plugin = (Plugin)iterator.next();
            if(!plugin.sqlMapBlobColumnListElementGenerated(element, introspectedTable))
            {
                rc = false;
                break;
            }
        }

        return rc;
    }

    private List plugins;
}
