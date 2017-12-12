// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Plugin.java

package org.mybatis.generator.api;

import java.util.List;
import java.util.Properties;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.Context;

// Referenced classes of package org.mybatis.generator.api:
//            IntrospectedTable, IntrospectedColumn, GeneratedXmlFile

public interface Plugin
{
    public static final class ModelClassType extends Enum
    {

        public static ModelClassType[] values()
        {
            ModelClassType amodelclasstype[];
            int i;
            ModelClassType amodelclasstype1[];
            System.arraycopy(amodelclasstype = ENUM$VALUES, 0, amodelclasstype1 = new ModelClassType[i = amodelclasstype.length], 0, i);
            return amodelclasstype1;
        }

        public static ModelClassType valueOf(String s)
        {
            return (ModelClassType)Enum.valueOf(org/mybatis/generator/api/Plugin$ModelClassType, s);
        }

        public static final ModelClassType PRIMARY_KEY;
        public static final ModelClassType BASE_RECORD;
        public static final ModelClassType RECORD_WITH_BLOBS;
        private static final ModelClassType ENUM$VALUES[];

        static 
        {
            PRIMARY_KEY = new ModelClassType("PRIMARY_KEY", 0);
            BASE_RECORD = new ModelClassType("BASE_RECORD", 1);
            RECORD_WITH_BLOBS = new ModelClassType("RECORD_WITH_BLOBS", 2);
            ENUM$VALUES = (new ModelClassType[] {
                PRIMARY_KEY, BASE_RECORD, RECORD_WITH_BLOBS
            });
        }

        private ModelClassType(String s, int i)
        {
            super(s, i);
        }
    }


    public abstract void setContext(Context context);

    public abstract void setProperties(Properties properties);

    public abstract void initialized(IntrospectedTable introspectedtable);

    public abstract boolean validate(List list);

    public abstract List contextGenerateAdditionalJavaFiles();

    public abstract List contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedtable);

    public abstract List contextGenerateAdditionalXmlFiles();

    public abstract List contextGenerateAdditionalXmlFiles(IntrospectedTable introspectedtable);

    public abstract boolean clientGenerated(Interface interface1, TopLevelClass toplevelclass, IntrospectedTable introspectedtable);

    public abstract boolean clientCountByExampleMethodGenerated(Method method, TopLevelClass toplevelclass, IntrospectedTable introspectedtable);

    public abstract boolean clientDeleteByExampleMethodGenerated(Method method, TopLevelClass toplevelclass, IntrospectedTable introspectedtable);

    public abstract boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, TopLevelClass toplevelclass, IntrospectedTable introspectedtable);

    public abstract boolean clientInsertMethodGenerated(Method method, TopLevelClass toplevelclass, IntrospectedTable introspectedtable);

    public abstract boolean clientInsertSelectiveMethodGenerated(Method method, TopLevelClass toplevelclass, IntrospectedTable introspectedtable);

    public abstract boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method, TopLevelClass toplevelclass, IntrospectedTable introspectedtable);

    public abstract boolean clientSelectByExampleWithoutBLOBsMethodGenerated(Method method, TopLevelClass toplevelclass, IntrospectedTable introspectedtable);

    public abstract boolean clientSelectByPrimaryKeyMethodGenerated(Method method, TopLevelClass toplevelclass, IntrospectedTable introspectedtable);

    public abstract boolean clientUpdateByExampleSelectiveMethodGenerated(Method method, TopLevelClass toplevelclass, IntrospectedTable introspectedtable);

    public abstract boolean clientUpdateByExampleWithBLOBsMethodGenerated(Method method, TopLevelClass toplevelclass, IntrospectedTable introspectedtable);

    public abstract boolean clientUpdateByExampleWithoutBLOBsMethodGenerated(Method method, TopLevelClass toplevelclass, IntrospectedTable introspectedtable);

    public abstract boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method, TopLevelClass toplevelclass, IntrospectedTable introspectedtable);

    public abstract boolean clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(Method method, TopLevelClass toplevelclass, IntrospectedTable introspectedtable);

    public abstract boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method, TopLevelClass toplevelclass, IntrospectedTable introspectedtable);

    public abstract boolean clientCountByExampleMethodGenerated(Method method, Interface interface1, IntrospectedTable introspectedtable);

    public abstract boolean clientDeleteByExampleMethodGenerated(Method method, Interface interface1, IntrospectedTable introspectedtable);

    public abstract boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, Interface interface1, IntrospectedTable introspectedtable);

    public abstract boolean clientInsertMethodGenerated(Method method, Interface interface1, IntrospectedTable introspectedtable);

    public abstract boolean clientInsertSelectiveMethodGenerated(Method method, Interface interface1, IntrospectedTable introspectedtable);

    public abstract boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method, Interface interface1, IntrospectedTable introspectedtable);

    public abstract boolean clientSelectByExampleWithoutBLOBsMethodGenerated(Method method, Interface interface1, IntrospectedTable introspectedtable);

    public abstract boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interface1, IntrospectedTable introspectedtable);

    public abstract boolean clientUpdateByExampleSelectiveMethodGenerated(Method method, Interface interface1, IntrospectedTable introspectedtable);

    public abstract boolean clientUpdateByExampleWithBLOBsMethodGenerated(Method method, Interface interface1, IntrospectedTable introspectedtable);

    public abstract boolean clientUpdateByExampleWithoutBLOBsMethodGenerated(Method method, Interface interface1, IntrospectedTable introspectedtable);

    public abstract boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method, Interface interface1, IntrospectedTable introspectedtable);

    public abstract boolean clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(Method method, Interface interface1, IntrospectedTable introspectedtable);

    public abstract boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method, Interface interface1, IntrospectedTable introspectedtable);

    public abstract boolean modelFieldGenerated(Field field, TopLevelClass toplevelclass, IntrospectedColumn introspectedcolumn, IntrospectedTable introspectedtable, ModelClassType modelclasstype);

    public abstract boolean modelGetterMethodGenerated(Method method, TopLevelClass toplevelclass, IntrospectedColumn introspectedcolumn, IntrospectedTable introspectedtable, ModelClassType modelclasstype);

    public abstract boolean modelSetterMethodGenerated(Method method, TopLevelClass toplevelclass, IntrospectedColumn introspectedcolumn, IntrospectedTable introspectedtable, ModelClassType modelclasstype);

    public abstract boolean modelPrimaryKeyClassGenerated(TopLevelClass toplevelclass, IntrospectedTable introspectedtable);

    public abstract boolean modelBaseRecordClassGenerated(TopLevelClass toplevelclass, IntrospectedTable introspectedtable);

    public abstract boolean modelRecordWithBLOBsClassGenerated(TopLevelClass toplevelclass, IntrospectedTable introspectedtable);

    public abstract boolean modelExampleClassGenerated(TopLevelClass toplevelclass, IntrospectedTable introspectedtable);

    public abstract boolean sqlMapGenerated(GeneratedXmlFile generatedxmlfile, IntrospectedTable introspectedtable);

    public abstract boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedtable);

    public abstract boolean sqlMapResultMapWithoutBLOBsElementGenerated(XmlElement xmlelement, IntrospectedTable introspectedtable);

    public abstract boolean sqlMapCountByExampleElementGenerated(XmlElement xmlelement, IntrospectedTable introspectedtable);

    public abstract boolean sqlMapDeleteByExampleElementGenerated(XmlElement xmlelement, IntrospectedTable introspectedtable);

    public abstract boolean sqlMapDeleteByPrimaryKeyElementGenerated(XmlElement xmlelement, IntrospectedTable introspectedtable);

    public abstract boolean sqlMapExampleWhereClauseElementGenerated(XmlElement xmlelement, IntrospectedTable introspectedtable);

    public abstract boolean sqlMapBaseColumnListElementGenerated(XmlElement xmlelement, IntrospectedTable introspectedtable);

    public abstract boolean sqlMapBlobColumnListElementGenerated(XmlElement xmlelement, IntrospectedTable introspectedtable);

    public abstract boolean sqlMapInsertElementGenerated(XmlElement xmlelement, IntrospectedTable introspectedtable);

    public abstract boolean sqlMapInsertSelectiveElementGenerated(XmlElement xmlelement, IntrospectedTable introspectedtable);

    public abstract boolean sqlMapResultMapWithBLOBsElementGenerated(XmlElement xmlelement, IntrospectedTable introspectedtable);

    public abstract boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement xmlelement, IntrospectedTable introspectedtable);

    public abstract boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement xmlelement, IntrospectedTable introspectedtable);

    public abstract boolean sqlMapSelectByExampleWithBLOBsElementGenerated(XmlElement xmlelement, IntrospectedTable introspectedtable);

    public abstract boolean sqlMapUpdateByExampleSelectiveElementGenerated(XmlElement xmlelement, IntrospectedTable introspectedtable);

    public abstract boolean sqlMapUpdateByExampleWithBLOBsElementGenerated(XmlElement xmlelement, IntrospectedTable introspectedtable);

    public abstract boolean sqlMapUpdateByExampleWithoutBLOBsElementGenerated(XmlElement xmlelement, IntrospectedTable introspectedtable);

    public abstract boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(XmlElement xmlelement, IntrospectedTable introspectedtable);

    public abstract boolean sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(XmlElement xmlelement, IntrospectedTable introspectedtable);

    public abstract boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement xmlelement, IntrospectedTable introspectedtable);
}
