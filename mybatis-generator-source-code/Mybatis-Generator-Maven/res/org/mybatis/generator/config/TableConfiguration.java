// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TableConfiguration.java

package org.mybatis.generator.config;

import java.util.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.*;
import org.mybatis.generator.internal.util.messages.Messages;

// Referenced classes of package org.mybatis.generator.config:
//            PropertyHolder, Context, IgnoredColumn, ColumnOverride, 
//            ModelType, GeneratedKey, ColumnRenamingRule

public class TableConfiguration extends PropertyHolder
{

    public TableConfiguration(Context context)
    {
        modelType = context.getDefaultModelType();
        columnOverrides = new ArrayList();
        ignoredColumns = new HashMap();
        insertStatementEnabled = true;
        selectByPrimaryKeyStatementEnabled = true;
        selectByExampleStatementEnabled = true;
        updateByPrimaryKeyStatementEnabled = true;
        deleteByPrimaryKeyStatementEnabled = true;
        deleteByExampleStatementEnabled = true;
        countByExampleStatementEnabled = true;
        updateByExampleStatementEnabled = true;
    }

    public boolean isDeleteByPrimaryKeyStatementEnabled()
    {
        return deleteByPrimaryKeyStatementEnabled;
    }

    public void setDeleteByPrimaryKeyStatementEnabled(boolean deleteByPrimaryKeyStatementEnabled)
    {
        this.deleteByPrimaryKeyStatementEnabled = deleteByPrimaryKeyStatementEnabled;
    }

    public boolean isInsertStatementEnabled()
    {
        return insertStatementEnabled;
    }

    public void setInsertStatementEnabled(boolean insertStatementEnabled)
    {
        this.insertStatementEnabled = insertStatementEnabled;
    }

    public boolean isSelectByPrimaryKeyStatementEnabled()
    {
        return selectByPrimaryKeyStatementEnabled;
    }

    public void setSelectByPrimaryKeyStatementEnabled(boolean selectByPrimaryKeyStatementEnabled)
    {
        this.selectByPrimaryKeyStatementEnabled = selectByPrimaryKeyStatementEnabled;
    }

    public boolean isUpdateByPrimaryKeyStatementEnabled()
    {
        return updateByPrimaryKeyStatementEnabled;
    }

    public void setUpdateByPrimaryKeyStatementEnabled(boolean updateByPrimaryKeyStatementEnabled)
    {
        this.updateByPrimaryKeyStatementEnabled = updateByPrimaryKeyStatementEnabled;
    }

    public boolean isColumnIgnored(String columnName)
    {
        for(Iterator iterator = ignoredColumns.entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            IgnoredColumn ic = (IgnoredColumn)entry.getKey();
            if(ic.isColumnNameDelimited())
            {
                if(columnName.equals(ic.getColumnName()))
                {
                    entry.setValue(Boolean.TRUE);
                    return true;
                }
            } else
            if(columnName.equalsIgnoreCase(ic.getColumnName()))
            {
                entry.setValue(Boolean.TRUE);
                return true;
            }
        }

        return false;
    }

    public void addIgnoredColumn(IgnoredColumn ignoredColumn)
    {
        ignoredColumns.put(ignoredColumn, Boolean.FALSE);
    }

    public void addColumnOverride(ColumnOverride columnOverride)
    {
        columnOverrides.add(columnOverride);
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(!(obj instanceof TableConfiguration))
            return false;
        TableConfiguration other = (TableConfiguration)obj;
        return EqualsUtil.areEqual(catalog, other.catalog) && EqualsUtil.areEqual(schema, other.schema) && EqualsUtil.areEqual(tableName, other.tableName);
    }

    public int hashCode()
    {
        int result = 23;
        result = HashCodeUtil.hash(result, catalog);
        result = HashCodeUtil.hash(result, schema);
        result = HashCodeUtil.hash(result, tableName);
        return result;
    }

    public boolean isSelectByExampleStatementEnabled()
    {
        return selectByExampleStatementEnabled;
    }

    public void setSelectByExampleStatementEnabled(boolean selectByExampleStatementEnabled)
    {
        this.selectByExampleStatementEnabled = selectByExampleStatementEnabled;
    }

    public ColumnOverride getColumnOverride(String columnName)
    {
        for(Iterator iterator = columnOverrides.iterator(); iterator.hasNext();)
        {
            ColumnOverride co = (ColumnOverride)iterator.next();
            if(co.isColumnNameDelimited())
            {
                if(columnName.equals(co.getColumnName()))
                    return co;
            } else
            if(columnName.equalsIgnoreCase(co.getColumnName()))
                return co;
        }

        return null;
    }

    public GeneratedKey getGeneratedKey()
    {
        return generatedKey;
    }

    public String getSelectByExampleQueryId()
    {
        return selectByExampleQueryId;
    }

    public void setSelectByExampleQueryId(String selectByExampleQueryId)
    {
        this.selectByExampleQueryId = selectByExampleQueryId;
    }

    public String getSelectByPrimaryKeyQueryId()
    {
        return selectByPrimaryKeyQueryId;
    }

    public void setSelectByPrimaryKeyQueryId(String selectByPrimaryKeyQueryId)
    {
        this.selectByPrimaryKeyQueryId = selectByPrimaryKeyQueryId;
    }

    public boolean isDeleteByExampleStatementEnabled()
    {
        return deleteByExampleStatementEnabled;
    }

    public void setDeleteByExampleStatementEnabled(boolean deleteByExampleStatementEnabled)
    {
        this.deleteByExampleStatementEnabled = deleteByExampleStatementEnabled;
    }

    public boolean areAnyStatementsEnabled()
    {
        return selectByExampleStatementEnabled || selectByPrimaryKeyStatementEnabled || insertStatementEnabled || updateByPrimaryKeyStatementEnabled || deleteByExampleStatementEnabled || deleteByPrimaryKeyStatementEnabled || countByExampleStatementEnabled || updateByExampleStatementEnabled;
    }

    public void setGeneratedKey(GeneratedKey generatedKey)
    {
        this.generatedKey = generatedKey;
    }

    public String getAlias()
    {
        return alias;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    public String getCatalog()
    {
        return catalog;
    }

    public void setCatalog(String catalog)
    {
        this.catalog = catalog;
    }

    public String getDomainObjectName()
    {
        return domainObjectName;
    }

    public void setDomainObjectName(String domainObjectName)
    {
        this.domainObjectName = domainObjectName;
    }

    public String getSchema()
    {
        return schema;
    }

    public void setSchema(String schema)
    {
        this.schema = schema;
    }

    public String getTableName()
    {
        return tableName;
    }

    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }

    public List getColumnOverrides()
    {
        return columnOverrides;
    }

    public List getIgnoredColumnsInError()
    {
        List answer = new ArrayList();
        for(Iterator iterator = ignoredColumns.entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            if(Boolean.FALSE.equals(entry.getValue()))
                answer.add(((IgnoredColumn)entry.getKey()).getColumnName());
        }

        return answer;
    }

    public ModelType getModelType()
    {
        return modelType;
    }

    public void setConfiguredModelType(String configuredModelType)
    {
        this.configuredModelType = configuredModelType;
        modelType = ModelType.getModelType(configuredModelType);
    }

    public boolean isWildcardEscapingEnabled()
    {
        return wildcardEscapingEnabled;
    }

    public void setWildcardEscapingEnabled(boolean wildcardEscapingEnabled)
    {
        this.wildcardEscapingEnabled = wildcardEscapingEnabled;
    }

    public XmlElement toXmlElement()
    {
        XmlElement xmlElement = new XmlElement("table");
        xmlElement.addAttribute(new Attribute("tableName", tableName));
        if(StringUtility.stringHasValue(catalog))
            xmlElement.addAttribute(new Attribute("catalog", catalog));
        if(StringUtility.stringHasValue(schema))
            xmlElement.addAttribute(new Attribute("schema", schema));
        if(StringUtility.stringHasValue(alias))
            xmlElement.addAttribute(new Attribute("alias", alias));
        if(StringUtility.stringHasValue(domainObjectName))
            xmlElement.addAttribute(new Attribute("domainObjectName", domainObjectName));
        if(!insertStatementEnabled)
            xmlElement.addAttribute(new Attribute("enableInsert", "false"));
        if(!selectByPrimaryKeyStatementEnabled)
            xmlElement.addAttribute(new Attribute("enableSelectByPrimaryKey", "false"));
        if(!selectByExampleStatementEnabled)
            xmlElement.addAttribute(new Attribute("enableSelectByExample", "false"));
        if(!updateByPrimaryKeyStatementEnabled)
            xmlElement.addAttribute(new Attribute("enableUpdateByPrimaryKey", "false"));
        if(!deleteByPrimaryKeyStatementEnabled)
            xmlElement.addAttribute(new Attribute("enableDeleteByPrimaryKey", "false"));
        if(!deleteByExampleStatementEnabled)
            xmlElement.addAttribute(new Attribute("enableDeleteByExample", "false"));
        if(!countByExampleStatementEnabled)
            xmlElement.addAttribute(new Attribute("enableCountByExample", "false"));
        if(!updateByExampleStatementEnabled)
            xmlElement.addAttribute(new Attribute("enableUpdateByExample", "false"));
        if(StringUtility.stringHasValue(selectByPrimaryKeyQueryId))
            xmlElement.addAttribute(new Attribute("selectByPrimaryKeyQueryId", selectByPrimaryKeyQueryId));
        if(StringUtility.stringHasValue(selectByExampleQueryId))
            xmlElement.addAttribute(new Attribute("selectByExampleQueryId", selectByExampleQueryId));
        if(configuredModelType != null)
            xmlElement.addAttribute(new Attribute("modelType", configuredModelType));
        if(wildcardEscapingEnabled)
            xmlElement.addAttribute(new Attribute("escapeWildcards", "true"));
        if(isAllColumnDelimitingEnabled)
            xmlElement.addAttribute(new Attribute("delimitAllColumns", "true"));
        if(delimitIdentifiers)
            xmlElement.addAttribute(new Attribute("delimitIdentifiers", "true"));
        addPropertyXmlElements(xmlElement);
        if(generatedKey != null)
            xmlElement.addElement(generatedKey.toXmlElement());
        if(columnRenamingRule != null)
            xmlElement.addElement(columnRenamingRule.toXmlElement());
        if(ignoredColumns.size() > 0)
        {
            IgnoredColumn ignoredColumn;
            for(Iterator iterator = ignoredColumns.keySet().iterator(); iterator.hasNext(); xmlElement.addElement(ignoredColumn.toXmlElement()))
                ignoredColumn = (IgnoredColumn)iterator.next();

        }
        if(columnOverrides.size() > 0)
        {
            ColumnOverride columnOverride;
            for(Iterator iterator1 = columnOverrides.iterator(); iterator1.hasNext(); xmlElement.addElement(columnOverride.toXmlElement()))
                columnOverride = (ColumnOverride)iterator1.next();

        }
        return xmlElement;
    }

    public String toString()
    {
        return StringUtility.composeFullyQualifiedTableName(catalog, schema, tableName, '.');
    }

    public boolean isDelimitIdentifiers()
    {
        return delimitIdentifiers;
    }

    public void setDelimitIdentifiers(boolean delimitIdentifiers)
    {
        this.delimitIdentifiers = delimitIdentifiers;
    }

    public boolean isCountByExampleStatementEnabled()
    {
        return countByExampleStatementEnabled;
    }

    public void setCountByExampleStatementEnabled(boolean countByExampleStatementEnabled)
    {
        this.countByExampleStatementEnabled = countByExampleStatementEnabled;
    }

    public boolean isUpdateByExampleStatementEnabled()
    {
        return updateByExampleStatementEnabled;
    }

    public void setUpdateByExampleStatementEnabled(boolean updateByExampleStatementEnabled)
    {
        this.updateByExampleStatementEnabled = updateByExampleStatementEnabled;
    }

    public void validate(List errors, int listPosition)
    {
        if(!StringUtility.stringHasValue(tableName))
            errors.add(Messages.getString("ValidationError.6", Integer.toString(listPosition)));
        String fqTableName = StringUtility.composeFullyQualifiedTableName(catalog, schema, tableName, '.');
        if(generatedKey != null)
            generatedKey.validate(errors, fqTableName);
        if(StringUtility.isTrue(getProperty("useColumnIndexes")) && selectByExampleStatementEnabled && selectByPrimaryKeyStatementEnabled)
        {
            boolean queryId1Set = StringUtility.stringHasValue(selectByExampleQueryId);
            boolean queryId2Set = StringUtility.stringHasValue(selectByPrimaryKeyQueryId);
            if(queryId1Set != queryId2Set)
                errors.add(Messages.getString("ValidationError.13", fqTableName));
        }
        if(columnRenamingRule != null)
            columnRenamingRule.validate(errors, fqTableName);
        ColumnOverride columnOverride;
        for(Iterator iterator = columnOverrides.iterator(); iterator.hasNext(); columnOverride.validate(errors, fqTableName))
            columnOverride = (ColumnOverride)iterator.next();

        IgnoredColumn ignoredColumn;
        for(Iterator iterator1 = ignoredColumns.keySet().iterator(); iterator1.hasNext(); ignoredColumn.validate(errors, fqTableName))
            ignoredColumn = (IgnoredColumn)iterator1.next();

    }

    public ColumnRenamingRule getColumnRenamingRule()
    {
        return columnRenamingRule;
    }

    public void setColumnRenamingRule(ColumnRenamingRule columnRenamingRule)
    {
        this.columnRenamingRule = columnRenamingRule;
    }

    public boolean isAllColumnDelimitingEnabled()
    {
        return isAllColumnDelimitingEnabled;
    }

    public void setAllColumnDelimitingEnabled(boolean isAllColumnDelimitingEnabled)
    {
        this.isAllColumnDelimitingEnabled = isAllColumnDelimitingEnabled;
    }

    private boolean insertStatementEnabled;
    private boolean selectByPrimaryKeyStatementEnabled;
    private boolean selectByExampleStatementEnabled;
    private boolean updateByPrimaryKeyStatementEnabled;
    private boolean deleteByPrimaryKeyStatementEnabled;
    private boolean deleteByExampleStatementEnabled;
    private boolean countByExampleStatementEnabled;
    private boolean updateByExampleStatementEnabled;
    private List columnOverrides;
    private Map ignoredColumns;
    private GeneratedKey generatedKey;
    private String selectByPrimaryKeyQueryId;
    private String selectByExampleQueryId;
    private String catalog;
    private String schema;
    private String tableName;
    private String domainObjectName;
    private String alias;
    private ModelType modelType;
    private boolean wildcardEscapingEnabled;
    private String configuredModelType;
    private boolean delimitIdentifiers;
    private ColumnRenamingRule columnRenamingRule;
    private boolean isAllColumnDelimitingEnabled;
}
