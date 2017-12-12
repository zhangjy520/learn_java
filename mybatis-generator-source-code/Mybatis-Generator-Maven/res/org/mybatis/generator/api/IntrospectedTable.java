// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IntrospectedTable.java

package org.mybatis.generator.api;

import java.util.*;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.rules.*;
import org.mybatis.generator.internal.util.StringUtility;

// Referenced classes of package org.mybatis.generator.api:
//            IntrospectedColumn, Plugin, FullyQualifiedTable, ProgressCallback

public abstract class IntrospectedTable
{
    protected static final class InternalAttribute extends Enum
    {

        public static InternalAttribute[] values()
        {
            InternalAttribute ainternalattribute[];
            int i;
            InternalAttribute ainternalattribute1[];
            System.arraycopy(ainternalattribute = ENUM$VALUES, 0, ainternalattribute1 = new InternalAttribute[i = ainternalattribute.length], 0, i);
            return ainternalattribute1;
        }

        public static InternalAttribute valueOf(String s)
        {
            return (InternalAttribute)Enum.valueOf(org/mybatis/generator/api/IntrospectedTable$InternalAttribute, s);
        }

        public static final InternalAttribute ATTR_DAO_IMPLEMENTATION_TYPE;
        public static final InternalAttribute ATTR_DAO_INTERFACE_TYPE;
        public static final InternalAttribute ATTR_PRIMARY_KEY_TYPE;
        public static final InternalAttribute ATTR_BASE_RECORD_TYPE;
        public static final InternalAttribute ATTR_RECORD_WITH_BLOBS_TYPE;
        public static final InternalAttribute ATTR_EXAMPLE_TYPE;
        public static final InternalAttribute ATTR_IBATIS2_SQL_MAP_PACKAGE;
        public static final InternalAttribute ATTR_IBATIS2_SQL_MAP_FILE_NAME;
        public static final InternalAttribute ATTR_IBATIS2_SQL_MAP_NAMESPACE;
        public static final InternalAttribute ATTR_MYBATIS3_XML_MAPPER_PACKAGE;
        public static final InternalAttribute ATTR_MYBATIS3_XML_MAPPER_FILE_NAME;
        public static final InternalAttribute ATTR_MYBATIS3_JAVA_MAPPER_TYPE;
        public static final InternalAttribute ATTR_FULLY_QUALIFIED_TABLE_NAME_AT_RUNTIME;
        public static final InternalAttribute ATTR_ALIASED_FULLY_QUALIFIED_TABLE_NAME_AT_RUNTIME;
        public static final InternalAttribute ATTR_COUNT_BY_EXAMPLE_STATEMENT_ID;
        public static final InternalAttribute ATTR_DELETE_BY_EXAMPLE_STATEMENT_ID;
        public static final InternalAttribute ATTR_DELETE_BY_PRIMARY_KEY_STATEMENT_ID;
        public static final InternalAttribute ATTR_INSERT_STATEMENT_ID;
        public static final InternalAttribute ATTR_INSERT_SELECTIVE_STATEMENT_ID;
        public static final InternalAttribute ATTR_SELECT_BY_EXAMPLE_STATEMENT_ID;
        public static final InternalAttribute ATTR_SELECT_BY_EXAMPLE_WITH_BLOBS_STATEMENT_ID;
        public static final InternalAttribute ATTR_SELECT_BY_PRIMARY_KEY_STATEMENT_ID;
        public static final InternalAttribute ATTR_UPDATE_BY_EXAMPLE_STATEMENT_ID;
        public static final InternalAttribute ATTR_UPDATE_BY_EXAMPLE_SELECTIVE_STATEMENT_ID;
        public static final InternalAttribute ATTR_UPDATE_BY_EXAMPLE_WITH_BLOBS_STATEMENT_ID;
        public static final InternalAttribute ATTR_UPDATE_BY_PRIMARY_KEY_STATEMENT_ID;
        public static final InternalAttribute ATTR_UPDATE_BY_PRIMARY_KEY_SELECTIVE_STATEMENT_ID;
        public static final InternalAttribute ATTR_UPDATE_BY_PRIMARY_KEY_WITH_BLOBS_STATEMENT_ID;
        public static final InternalAttribute ATTR_BASE_RESULT_MAP_ID;
        public static final InternalAttribute ATTR_RESULT_MAP_WITH_BLOBS_ID;
        public static final InternalAttribute ATTR_EXAMPLE_WHERE_CLAUSE_ID;
        public static final InternalAttribute ATTR_BASE_COLUMN_LIST_ID;
        public static final InternalAttribute ATTR_BLOB_COLUMN_LIST_ID;
        public static final InternalAttribute ATTR_MYBATIS3_UPDATE_BY_EXAMPLE_WHERE_CLAUSE_ID;
        private static final InternalAttribute ENUM$VALUES[];

        static 
        {
            ATTR_DAO_IMPLEMENTATION_TYPE = new InternalAttribute("ATTR_DAO_IMPLEMENTATION_TYPE", 0);
            ATTR_DAO_INTERFACE_TYPE = new InternalAttribute("ATTR_DAO_INTERFACE_TYPE", 1);
            ATTR_PRIMARY_KEY_TYPE = new InternalAttribute("ATTR_PRIMARY_KEY_TYPE", 2);
            ATTR_BASE_RECORD_TYPE = new InternalAttribute("ATTR_BASE_RECORD_TYPE", 3);
            ATTR_RECORD_WITH_BLOBS_TYPE = new InternalAttribute("ATTR_RECORD_WITH_BLOBS_TYPE", 4);
            ATTR_EXAMPLE_TYPE = new InternalAttribute("ATTR_EXAMPLE_TYPE", 5);
            ATTR_IBATIS2_SQL_MAP_PACKAGE = new InternalAttribute("ATTR_IBATIS2_SQL_MAP_PACKAGE", 6);
            ATTR_IBATIS2_SQL_MAP_FILE_NAME = new InternalAttribute("ATTR_IBATIS2_SQL_MAP_FILE_NAME", 7);
            ATTR_IBATIS2_SQL_MAP_NAMESPACE = new InternalAttribute("ATTR_IBATIS2_SQL_MAP_NAMESPACE", 8);
            ATTR_MYBATIS3_XML_MAPPER_PACKAGE = new InternalAttribute("ATTR_MYBATIS3_XML_MAPPER_PACKAGE", 9);
            ATTR_MYBATIS3_XML_MAPPER_FILE_NAME = new InternalAttribute("ATTR_MYBATIS3_XML_MAPPER_FILE_NAME", 10);
            ATTR_MYBATIS3_JAVA_MAPPER_TYPE = new InternalAttribute("ATTR_MYBATIS3_JAVA_MAPPER_TYPE", 11);
            ATTR_FULLY_QUALIFIED_TABLE_NAME_AT_RUNTIME = new InternalAttribute("ATTR_FULLY_QUALIFIED_TABLE_NAME_AT_RUNTIME", 12);
            ATTR_ALIASED_FULLY_QUALIFIED_TABLE_NAME_AT_RUNTIME = new InternalAttribute("ATTR_ALIASED_FULLY_QUALIFIED_TABLE_NAME_AT_RUNTIME", 13);
            ATTR_COUNT_BY_EXAMPLE_STATEMENT_ID = new InternalAttribute("ATTR_COUNT_BY_EXAMPLE_STATEMENT_ID", 14);
            ATTR_DELETE_BY_EXAMPLE_STATEMENT_ID = new InternalAttribute("ATTR_DELETE_BY_EXAMPLE_STATEMENT_ID", 15);
            ATTR_DELETE_BY_PRIMARY_KEY_STATEMENT_ID = new InternalAttribute("ATTR_DELETE_BY_PRIMARY_KEY_STATEMENT_ID", 16);
            ATTR_INSERT_STATEMENT_ID = new InternalAttribute("ATTR_INSERT_STATEMENT_ID", 17);
            ATTR_INSERT_SELECTIVE_STATEMENT_ID = new InternalAttribute("ATTR_INSERT_SELECTIVE_STATEMENT_ID", 18);
            ATTR_SELECT_BY_EXAMPLE_STATEMENT_ID = new InternalAttribute("ATTR_SELECT_BY_EXAMPLE_STATEMENT_ID", 19);
            ATTR_SELECT_BY_EXAMPLE_WITH_BLOBS_STATEMENT_ID = new InternalAttribute("ATTR_SELECT_BY_EXAMPLE_WITH_BLOBS_STATEMENT_ID", 20);
            ATTR_SELECT_BY_PRIMARY_KEY_STATEMENT_ID = new InternalAttribute("ATTR_SELECT_BY_PRIMARY_KEY_STATEMENT_ID", 21);
            ATTR_UPDATE_BY_EXAMPLE_STATEMENT_ID = new InternalAttribute("ATTR_UPDATE_BY_EXAMPLE_STATEMENT_ID", 22);
            ATTR_UPDATE_BY_EXAMPLE_SELECTIVE_STATEMENT_ID = new InternalAttribute("ATTR_UPDATE_BY_EXAMPLE_SELECTIVE_STATEMENT_ID", 23);
            ATTR_UPDATE_BY_EXAMPLE_WITH_BLOBS_STATEMENT_ID = new InternalAttribute("ATTR_UPDATE_BY_EXAMPLE_WITH_BLOBS_STATEMENT_ID", 24);
            ATTR_UPDATE_BY_PRIMARY_KEY_STATEMENT_ID = new InternalAttribute("ATTR_UPDATE_BY_PRIMARY_KEY_STATEMENT_ID", 25);
            ATTR_UPDATE_BY_PRIMARY_KEY_SELECTIVE_STATEMENT_ID = new InternalAttribute("ATTR_UPDATE_BY_PRIMARY_KEY_SELECTIVE_STATEMENT_ID", 26);
            ATTR_UPDATE_BY_PRIMARY_KEY_WITH_BLOBS_STATEMENT_ID = new InternalAttribute("ATTR_UPDATE_BY_PRIMARY_KEY_WITH_BLOBS_STATEMENT_ID", 27);
            ATTR_BASE_RESULT_MAP_ID = new InternalAttribute("ATTR_BASE_RESULT_MAP_ID", 28);
            ATTR_RESULT_MAP_WITH_BLOBS_ID = new InternalAttribute("ATTR_RESULT_MAP_WITH_BLOBS_ID", 29);
            ATTR_EXAMPLE_WHERE_CLAUSE_ID = new InternalAttribute("ATTR_EXAMPLE_WHERE_CLAUSE_ID", 30);
            ATTR_BASE_COLUMN_LIST_ID = new InternalAttribute("ATTR_BASE_COLUMN_LIST_ID", 31);
            ATTR_BLOB_COLUMN_LIST_ID = new InternalAttribute("ATTR_BLOB_COLUMN_LIST_ID", 32);
            ATTR_MYBATIS3_UPDATE_BY_EXAMPLE_WHERE_CLAUSE_ID = new InternalAttribute("ATTR_MYBATIS3_UPDATE_BY_EXAMPLE_WHERE_CLAUSE_ID", 33);
            ENUM$VALUES = (new InternalAttribute[] {
                ATTR_DAO_IMPLEMENTATION_TYPE, ATTR_DAO_INTERFACE_TYPE, ATTR_PRIMARY_KEY_TYPE, ATTR_BASE_RECORD_TYPE, ATTR_RECORD_WITH_BLOBS_TYPE, ATTR_EXAMPLE_TYPE, ATTR_IBATIS2_SQL_MAP_PACKAGE, ATTR_IBATIS2_SQL_MAP_FILE_NAME, ATTR_IBATIS2_SQL_MAP_NAMESPACE, ATTR_MYBATIS3_XML_MAPPER_PACKAGE, 
                ATTR_MYBATIS3_XML_MAPPER_FILE_NAME, ATTR_MYBATIS3_JAVA_MAPPER_TYPE, ATTR_FULLY_QUALIFIED_TABLE_NAME_AT_RUNTIME, ATTR_ALIASED_FULLY_QUALIFIED_TABLE_NAME_AT_RUNTIME, ATTR_COUNT_BY_EXAMPLE_STATEMENT_ID, ATTR_DELETE_BY_EXAMPLE_STATEMENT_ID, ATTR_DELETE_BY_PRIMARY_KEY_STATEMENT_ID, ATTR_INSERT_STATEMENT_ID, ATTR_INSERT_SELECTIVE_STATEMENT_ID, ATTR_SELECT_BY_EXAMPLE_STATEMENT_ID, 
                ATTR_SELECT_BY_EXAMPLE_WITH_BLOBS_STATEMENT_ID, ATTR_SELECT_BY_PRIMARY_KEY_STATEMENT_ID, ATTR_UPDATE_BY_EXAMPLE_STATEMENT_ID, ATTR_UPDATE_BY_EXAMPLE_SELECTIVE_STATEMENT_ID, ATTR_UPDATE_BY_EXAMPLE_WITH_BLOBS_STATEMENT_ID, ATTR_UPDATE_BY_PRIMARY_KEY_STATEMENT_ID, ATTR_UPDATE_BY_PRIMARY_KEY_SELECTIVE_STATEMENT_ID, ATTR_UPDATE_BY_PRIMARY_KEY_WITH_BLOBS_STATEMENT_ID, ATTR_BASE_RESULT_MAP_ID, ATTR_RESULT_MAP_WITH_BLOBS_ID, 
                ATTR_EXAMPLE_WHERE_CLAUSE_ID, ATTR_BASE_COLUMN_LIST_ID, ATTR_BLOB_COLUMN_LIST_ID, ATTR_MYBATIS3_UPDATE_BY_EXAMPLE_WHERE_CLAUSE_ID
            });
        }

        private InternalAttribute(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class TargetRuntime extends Enum
    {

        public static TargetRuntime[] values()
        {
            TargetRuntime atargetruntime[];
            int i;
            TargetRuntime atargetruntime1[];
            System.arraycopy(atargetruntime = ENUM$VALUES, 0, atargetruntime1 = new TargetRuntime[i = atargetruntime.length], 0, i);
            return atargetruntime1;
        }

        public static TargetRuntime valueOf(String s)
        {
            return (TargetRuntime)Enum.valueOf(org/mybatis/generator/api/IntrospectedTable$TargetRuntime, s);
        }

        public static final TargetRuntime IBATIS2;
        public static final TargetRuntime MYBATIS3;
        private static final TargetRuntime ENUM$VALUES[];

        static 
        {
            IBATIS2 = new TargetRuntime("IBATIS2", 0);
            MYBATIS3 = new TargetRuntime("MYBATIS3", 1);
            ENUM$VALUES = (new TargetRuntime[] {
                IBATIS2, MYBATIS3
            });
        }

        private TargetRuntime(String s, int i)
        {
            super(s, i);
        }
    }


    public IntrospectedTable(TargetRuntime targetRuntime)
    {
        this.targetRuntime = targetRuntime;
        primaryKeyColumns = new ArrayList();
        baseColumns = new ArrayList();
        blobColumns = new ArrayList();
        attributes = new HashMap();
        internalAttributes = new HashMap();
    }

    public FullyQualifiedTable getFullyQualifiedTable()
    {
        return fullyQualifiedTable;
    }

    public String getSelectByExampleQueryId()
    {
        return tableConfiguration.getSelectByExampleQueryId();
    }

    public String getSelectByPrimaryKeyQueryId()
    {
        return tableConfiguration.getSelectByPrimaryKeyQueryId();
    }

    public GeneratedKey getGeneratedKey()
    {
        return tableConfiguration.getGeneratedKey();
    }

    public IntrospectedColumn getColumn(String columnName)
    {
        if(columnName == null)
            return null;
        for(Iterator iterator = primaryKeyColumns.iterator(); iterator.hasNext();)
        {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterator.next();
            if(introspectedColumn.isColumnNameDelimited())
            {
                if(introspectedColumn.getActualColumnName().equals(columnName))
                    return introspectedColumn;
            } else
            if(introspectedColumn.getActualColumnName().equalsIgnoreCase(columnName))
                return introspectedColumn;
        }

        for(Iterator iterator1 = baseColumns.iterator(); iterator1.hasNext();)
        {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterator1.next();
            if(introspectedColumn.isColumnNameDelimited())
            {
                if(introspectedColumn.getActualColumnName().equals(columnName))
                    return introspectedColumn;
            } else
            if(introspectedColumn.getActualColumnName().equalsIgnoreCase(columnName))
                return introspectedColumn;
        }

        for(Iterator iterator2 = blobColumns.iterator(); iterator2.hasNext();)
        {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterator2.next();
            if(introspectedColumn.isColumnNameDelimited())
            {
                if(introspectedColumn.getActualColumnName().equals(columnName))
                    return introspectedColumn;
            } else
            if(introspectedColumn.getActualColumnName().equalsIgnoreCase(columnName))
                return introspectedColumn;
        }

        return null;
    }

    public boolean hasJDBCDateColumns()
    {
        boolean rc = false;
        for(Iterator iterator = primaryKeyColumns.iterator(); iterator.hasNext();)
        {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterator.next();
            if(introspectedColumn.isJDBCDateColumn())
            {
                rc = true;
                break;
            }
        }

        if(!rc)
        {
            for(Iterator iterator1 = baseColumns.iterator(); iterator1.hasNext();)
            {
                IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterator1.next();
                if(introspectedColumn.isJDBCDateColumn())
                {
                    rc = true;
                    break;
                }
            }

        }
        return rc;
    }

    public boolean hasJDBCTimeColumns()
    {
        boolean rc = false;
        for(Iterator iterator = primaryKeyColumns.iterator(); iterator.hasNext();)
        {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterator.next();
            if(introspectedColumn.isJDBCTimeColumn())
            {
                rc = true;
                break;
            }
        }

        if(!rc)
        {
            for(Iterator iterator1 = baseColumns.iterator(); iterator1.hasNext();)
            {
                IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterator1.next();
                if(introspectedColumn.isJDBCTimeColumn())
                {
                    rc = true;
                    break;
                }
            }

        }
        return rc;
    }

    public List getPrimaryKeyColumns()
    {
        return primaryKeyColumns;
    }

    public boolean hasPrimaryKeyColumns()
    {
        return primaryKeyColumns.size() > 0;
    }

    public List getBaseColumns()
    {
        return baseColumns;
    }

    public List getAllColumns()
    {
        List answer = new ArrayList();
        answer.addAll(primaryKeyColumns);
        answer.addAll(baseColumns);
        answer.addAll(blobColumns);
        return answer;
    }

    public List getNonBLOBColumns()
    {
        List answer = new ArrayList();
        answer.addAll(primaryKeyColumns);
        answer.addAll(baseColumns);
        return answer;
    }

    public int getNonBLOBColumnCount()
    {
        return primaryKeyColumns.size() + baseColumns.size();
    }

    public List getNonPrimaryKeyColumns()
    {
        List answer = new ArrayList();
        answer.addAll(baseColumns);
        answer.addAll(blobColumns);
        return answer;
    }

    public List getBLOBColumns()
    {
        return blobColumns;
    }

    public boolean hasBLOBColumns()
    {
        return blobColumns.size() > 0;
    }

    public boolean hasBaseColumns()
    {
        return baseColumns.size() > 0;
    }

    public Rules getRules()
    {
        return rules;
    }

    public String getTableConfigurationProperty(String property)
    {
        return tableConfiguration.getProperty(property);
    }

    public String getPrimaryKeyType()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_PRIMARY_KEY_TYPE);
    }

    public String getBaseRecordType()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_BASE_RECORD_TYPE);
    }

    public String getExampleType()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_EXAMPLE_TYPE);
    }

    public String getRecordWithBLOBsType()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_RECORD_WITH_BLOBS_TYPE);
    }

    public String getIbatis2SqlMapFileName()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_IBATIS2_SQL_MAP_FILE_NAME);
    }

    public String getIbatis2SqlMapNamespace()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_IBATIS2_SQL_MAP_NAMESPACE);
    }

    public String getIbatis2SqlMapPackage()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_IBATIS2_SQL_MAP_PACKAGE);
    }

    public String getDAOImplementationType()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_DAO_IMPLEMENTATION_TYPE);
    }

    public String getDAOInterfaceType()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_DAO_INTERFACE_TYPE);
    }

    public boolean hasAnyColumns()
    {
        return primaryKeyColumns.size() > 0 || baseColumns.size() > 0 || blobColumns.size() > 0;
    }

    public void setTableConfiguration(TableConfiguration tableConfiguration)
    {
        this.tableConfiguration = tableConfiguration;
    }

    public void setFullyQualifiedTable(FullyQualifiedTable fullyQualifiedTable)
    {
        this.fullyQualifiedTable = fullyQualifiedTable;
    }

    public void setContext(Context context)
    {
        this.context = context;
    }

    public void addColumn(IntrospectedColumn introspectedColumn)
    {
        if(introspectedColumn.isBLOBColumn())
            blobColumns.add(introspectedColumn);
        else
            baseColumns.add(introspectedColumn);
        introspectedColumn.setIntrospectedTable(this);
    }

    public void addPrimaryKeyColumn(String columnName)
    {
        boolean found = false;
        for(Iterator iter = baseColumns.iterator(); iter.hasNext();)
        {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn)iter.next();
            if(introspectedColumn.getActualColumnName().equals(columnName))
            {
                primaryKeyColumns.add(introspectedColumn);
                iter.remove();
                found = true;
                break;
            }
        }

        if(!found)
        {
            for(Iterator iter = blobColumns.iterator(); iter.hasNext();)
            {
                IntrospectedColumn introspectedColumn = (IntrospectedColumn)iter.next();
                if(introspectedColumn.getActualColumnName().equals(columnName))
                {
                    primaryKeyColumns.add(introspectedColumn);
                    iter.remove();
                    found = true;
                    break;
                }
            }

        }
    }

    public Object getAttribute(String name)
    {
        return attributes.get(name);
    }

    public void removeAttribute(String name)
    {
        attributes.remove(name);
    }

    public void setAttribute(String name, Object value)
    {
        attributes.put(name, value);
    }

    public void initialize()
    {
        calculateJavaClientAttributes();
        calculateModelAttributes();
        calculateXmlAttributes();
        if(tableConfiguration.getModelType() == ModelType.HIERARCHICAL)
            rules = new HierarchicalModelRules(this);
        else
        if(tableConfiguration.getModelType() == ModelType.FLAT)
            rules = new FlatModelRules(this);
        else
            rules = new ConditionalModelRules(this);
        context.getPlugins().initialized(this);
    }

    protected void calculateXmlAttributes()
    {
        setIbatis2SqlMapPackage(calculateIbatis2SqlMapPackage());
        setIbatis2SqlMapFileName(calculateIbatis2SqlMapFileName());
        setMyBatis3XmlMapperFileName(calculateMyBatis3XmlMapperFileName());
        setMyBatis3XmlMapperPackage(getIbatis2SqlMapPackage());
        setIbatis2SqlMapNamespace(calculateIbatis2SqlMapNamespace());
        setSqlMapFullyQualifiedRuntimeTableName(calculateSqlMapFullyQualifiedRuntimeTableName());
        setSqlMapAliasedFullyQualifiedRuntimeTableName(calculateSqlMapAliasedFullyQualifiedRuntimeTableName());
        setCountByExampleStatementId("countByExample");
        setDeleteByExampleStatementId("deleteByExample");
        setDeleteByPrimaryKeyStatementId("deleteByPrimaryKey");
        setInsertStatementId("insert");
        setInsertSelectiveStatementId("insertSelective");
        setSelectByExampleStatementId("selectByExample");
        setSelectByExampleWithBLOBsStatementId("selectByExampleWithBLOBs");
        setSelectByPrimaryKeyStatementId("selectByPrimaryKey");
        setUpdateByExampleStatementId("updateByExample");
        setUpdateByExampleSelectiveStatementId("updateByExampleSelective");
        setUpdateByExampleWithBLOBsStatementId("updateByExampleWithBLOBs");
        setUpdateByPrimaryKeyStatementId("updateByPrimaryKey");
        setUpdateByPrimaryKeySelectiveStatementId("updateByPrimaryKeySelective");
        setUpdateByPrimaryKeyWithBLOBsStatementId("updateByPrimaryKeyWithBLOBs");
        setBaseResultMapId("BaseResultMap");
        setResultMapWithBLOBsId("ResultMapWithBLOBs");
        setExampleWhereClauseId("Example_Where_Clause");
        setBaseColumnListId("Base_Column_List");
        setBlobColumnListId("Blob_Column_List");
        setMyBatis3UpdateByExampleWhereClauseId("Update_By_Example_Where_Clause");
    }

    public void setBlobColumnListId(String s)
    {
        internalAttributes.put(InternalAttribute.ATTR_BLOB_COLUMN_LIST_ID, s);
    }

    public void setBaseColumnListId(String s)
    {
        internalAttributes.put(InternalAttribute.ATTR_BASE_COLUMN_LIST_ID, s);
    }

    public void setExampleWhereClauseId(String s)
    {
        internalAttributes.put(InternalAttribute.ATTR_EXAMPLE_WHERE_CLAUSE_ID, s);
    }

    public void setMyBatis3UpdateByExampleWhereClauseId(String s)
    {
        internalAttributes.put(InternalAttribute.ATTR_MYBATIS3_UPDATE_BY_EXAMPLE_WHERE_CLAUSE_ID, s);
    }

    public void setResultMapWithBLOBsId(String s)
    {
        internalAttributes.put(InternalAttribute.ATTR_RESULT_MAP_WITH_BLOBS_ID, s);
    }

    public void setBaseResultMapId(String s)
    {
        internalAttributes.put(InternalAttribute.ATTR_BASE_RESULT_MAP_ID, s);
    }

    public void setUpdateByPrimaryKeyWithBLOBsStatementId(String s)
    {
        internalAttributes.put(InternalAttribute.ATTR_UPDATE_BY_PRIMARY_KEY_WITH_BLOBS_STATEMENT_ID, s);
    }

    public void setUpdateByPrimaryKeySelectiveStatementId(String s)
    {
        internalAttributes.put(InternalAttribute.ATTR_UPDATE_BY_PRIMARY_KEY_SELECTIVE_STATEMENT_ID, s);
    }

    public void setUpdateByPrimaryKeyStatementId(String s)
    {
        internalAttributes.put(InternalAttribute.ATTR_UPDATE_BY_PRIMARY_KEY_STATEMENT_ID, s);
    }

    public void setUpdateByExampleWithBLOBsStatementId(String s)
    {
        internalAttributes.put(InternalAttribute.ATTR_UPDATE_BY_EXAMPLE_WITH_BLOBS_STATEMENT_ID, s);
    }

    public void setUpdateByExampleSelectiveStatementId(String s)
    {
        internalAttributes.put(InternalAttribute.ATTR_UPDATE_BY_EXAMPLE_SELECTIVE_STATEMENT_ID, s);
    }

    public void setUpdateByExampleStatementId(String s)
    {
        internalAttributes.put(InternalAttribute.ATTR_UPDATE_BY_EXAMPLE_STATEMENT_ID, s);
    }

    public void setSelectByPrimaryKeyStatementId(String s)
    {
        internalAttributes.put(InternalAttribute.ATTR_SELECT_BY_PRIMARY_KEY_STATEMENT_ID, s);
    }

    public void setSelectByExampleWithBLOBsStatementId(String s)
    {
        internalAttributes.put(InternalAttribute.ATTR_SELECT_BY_EXAMPLE_WITH_BLOBS_STATEMENT_ID, s);
    }

    public void setSelectByExampleStatementId(String s)
    {
        internalAttributes.put(InternalAttribute.ATTR_SELECT_BY_EXAMPLE_STATEMENT_ID, s);
    }

    public void setInsertSelectiveStatementId(String s)
    {
        internalAttributes.put(InternalAttribute.ATTR_INSERT_SELECTIVE_STATEMENT_ID, s);
    }

    public void setInsertStatementId(String s)
    {
        internalAttributes.put(InternalAttribute.ATTR_INSERT_STATEMENT_ID, s);
    }

    public void setDeleteByPrimaryKeyStatementId(String s)
    {
        internalAttributes.put(InternalAttribute.ATTR_DELETE_BY_PRIMARY_KEY_STATEMENT_ID, s);
    }

    public void setDeleteByExampleStatementId(String s)
    {
        internalAttributes.put(InternalAttribute.ATTR_DELETE_BY_EXAMPLE_STATEMENT_ID, s);
    }

    public void setCountByExampleStatementId(String s)
    {
        internalAttributes.put(InternalAttribute.ATTR_COUNT_BY_EXAMPLE_STATEMENT_ID, s);
    }

    public String getBlobColumnListId()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_BLOB_COLUMN_LIST_ID);
    }

    public String getBaseColumnListId()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_BASE_COLUMN_LIST_ID);
    }

    public String getExampleWhereClauseId()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_EXAMPLE_WHERE_CLAUSE_ID);
    }

    public String getMyBatis3UpdateByExampleWhereClauseId()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_MYBATIS3_UPDATE_BY_EXAMPLE_WHERE_CLAUSE_ID);
    }

    public String getResultMapWithBLOBsId()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_RESULT_MAP_WITH_BLOBS_ID);
    }

    public String getBaseResultMapId()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_BASE_RESULT_MAP_ID);
    }

    public String getUpdateByPrimaryKeyWithBLOBsStatementId()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_UPDATE_BY_PRIMARY_KEY_WITH_BLOBS_STATEMENT_ID);
    }

    public String getUpdateByPrimaryKeySelectiveStatementId()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_UPDATE_BY_PRIMARY_KEY_SELECTIVE_STATEMENT_ID);
    }

    public String getUpdateByPrimaryKeyStatementId()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_UPDATE_BY_PRIMARY_KEY_STATEMENT_ID);
    }

    public String getUpdateByExampleWithBLOBsStatementId()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_UPDATE_BY_EXAMPLE_WITH_BLOBS_STATEMENT_ID);
    }

    public String getUpdateByExampleSelectiveStatementId()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_UPDATE_BY_EXAMPLE_SELECTIVE_STATEMENT_ID);
    }

    public String getUpdateByExampleStatementId()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_UPDATE_BY_EXAMPLE_STATEMENT_ID);
    }

    public String getSelectByPrimaryKeyStatementId()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_SELECT_BY_PRIMARY_KEY_STATEMENT_ID);
    }

    public String getSelectByExampleWithBLOBsStatementId()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_SELECT_BY_EXAMPLE_WITH_BLOBS_STATEMENT_ID);
    }

    public String getSelectByExampleStatementId()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_SELECT_BY_EXAMPLE_STATEMENT_ID);
    }

    public String getInsertSelectiveStatementId()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_INSERT_SELECTIVE_STATEMENT_ID);
    }

    public String getInsertStatementId()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_INSERT_STATEMENT_ID);
    }

    public String getDeleteByPrimaryKeyStatementId()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_DELETE_BY_PRIMARY_KEY_STATEMENT_ID);
    }

    public String getDeleteByExampleStatementId()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_DELETE_BY_EXAMPLE_STATEMENT_ID);
    }

    public String getCountByExampleStatementId()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_COUNT_BY_EXAMPLE_STATEMENT_ID);
    }

    protected String calculateJavaClientImplementationPackage()
    {
        JavaClientGeneratorConfiguration config = context.getJavaClientGeneratorConfiguration();
        if(config == null)
            return null;
        StringBuilder sb = new StringBuilder();
        if(StringUtility.stringHasValue(config.getImplementationPackage()))
            sb.append(config.getImplementationPackage());
        else
            sb.append(config.getTargetPackage());
        if(StringUtility.isTrue(config.getProperty("enableSubPackages")))
            sb.append(fullyQualifiedTable.getSubPackage());
        return sb.toString();
    }

    protected String calculateJavaClientInterfacePackage()
    {
        JavaClientGeneratorConfiguration config = context.getJavaClientGeneratorConfiguration();
        if(config == null)
            return null;
        StringBuilder sb = new StringBuilder();
        sb.append(config.getTargetPackage());
        if(StringUtility.isTrue(config.getProperty("enableSubPackages")))
            sb.append(fullyQualifiedTable.getSubPackage());
        return sb.toString();
    }

    protected void calculateJavaClientAttributes()
    {
        if(context.getJavaClientGeneratorConfiguration() == null)
        {
            return;
        } else
        {
            StringBuilder sb = new StringBuilder();
            sb.append(calculateJavaClientImplementationPackage());
            sb.append('.');
            sb.append(fullyQualifiedTable.getDomainObjectName());
            sb.append("DAOImpl");
            setDAOImplementationType(sb.toString());
            sb.setLength(0);
            sb.append(calculateJavaClientInterfacePackage());
            sb.append('.');
            sb.append(fullyQualifiedTable.getDomainObjectName());
            sb.append("DAO");
            setDAOInterfaceType(sb.toString());
            sb.setLength(0);
            sb.append(calculateJavaClientInterfacePackage());
            sb.append('.');
            sb.append(fullyQualifiedTable.getDomainObjectName());
            sb.append("Mapper");
            setMyBatis3JavaMapperType(sb.toString());
            return;
        }
    }

    protected String calculateJavaModelPackage()
    {
        JavaModelGeneratorConfiguration config = context.getJavaModelGeneratorConfiguration();
        StringBuilder sb = new StringBuilder();
        sb.append(config.getTargetPackage());
        if(StringUtility.isTrue(config.getProperty("enableSubPackages")))
            sb.append(fullyQualifiedTable.getSubPackage());
        return sb.toString();
    }

    protected void calculateModelAttributes()
    {
        String pakkage = calculateJavaModelPackage();
        StringBuilder sb = new StringBuilder();
        sb.append(pakkage);
        sb.append('.');
        sb.append(fullyQualifiedTable.getDomainObjectName());
        sb.append("Key");
        setPrimaryKeyType(sb.toString());
        sb.setLength(0);
        sb.append(pakkage);
        sb.append('.');
        sb.append(fullyQualifiedTable.getDomainObjectName());
        setBaseRecordType(sb.toString());
        sb.setLength(0);
        sb.append(pakkage);
        sb.append('.');
        sb.append(fullyQualifiedTable.getDomainObjectName());
        sb.append("WithBLOBs");
        setRecordWithBLOBsType(sb.toString());
        sb.setLength(0);
        sb.append(pakkage);
        sb.append('.');
        sb.append(fullyQualifiedTable.getDomainObjectName());
        sb.append("Example");
        setExampleType(sb.toString());
    }

    protected String calculateIbatis2SqlMapPackage()
    {
        SqlMapGeneratorConfiguration config = context.getSqlMapGeneratorConfiguration();
        StringBuilder sb = new StringBuilder(config.getTargetPackage());
        if(StringUtility.isTrue(config.getProperty("enableSubPackages")))
            sb.append(fullyQualifiedTable.getSubPackage());
        return sb.toString();
    }

    protected String calculateIbatis2SqlMapFileName()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(fullyQualifiedTable.getIbatis2SqlMapNamespace());
        sb.append("_SqlMap.xml");
        return sb.toString();
    }

    protected String calculateMyBatis3XmlMapperFileName()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(fullyQualifiedTable.getDomainObjectName());
        sb.append("Mapper.xml");
        return sb.toString();
    }

    protected String calculateIbatis2SqlMapNamespace()
    {
        return fullyQualifiedTable.getIbatis2SqlMapNamespace();
    }

    protected String calculateSqlMapFullyQualifiedRuntimeTableName()
    {
        return fullyQualifiedTable.getFullyQualifiedTableNameAtRuntime();
    }

    protected String calculateSqlMapAliasedFullyQualifiedRuntimeTableName()
    {
        return fullyQualifiedTable.getAliasedFullyQualifiedTableNameAtRuntime();
    }

    public String getFullyQualifiedTableNameAtRuntime()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_FULLY_QUALIFIED_TABLE_NAME_AT_RUNTIME);
    }

    public String getAliasedFullyQualifiedTableNameAtRuntime()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_ALIASED_FULLY_QUALIFIED_TABLE_NAME_AT_RUNTIME);
    }

    public abstract void calculateGenerators(List list, ProgressCallback progresscallback);

    public abstract List getGeneratedJavaFiles();

    public abstract List getGeneratedXmlFiles();

    public abstract boolean isJava5Targeted();

    public abstract int getGenerationSteps();

    public void setRules(Rules rules)
    {
        this.rules = rules;
    }

    public TableConfiguration getTableConfiguration()
    {
        return tableConfiguration;
    }

    public void setDAOImplementationType(String DAOImplementationType)
    {
        internalAttributes.put(InternalAttribute.ATTR_DAO_IMPLEMENTATION_TYPE, DAOImplementationType);
    }

    public void setDAOInterfaceType(String DAOInterfaceType)
    {
        internalAttributes.put(InternalAttribute.ATTR_DAO_INTERFACE_TYPE, DAOInterfaceType);
    }

    public void setPrimaryKeyType(String primaryKeyType)
    {
        internalAttributes.put(InternalAttribute.ATTR_PRIMARY_KEY_TYPE, primaryKeyType);
    }

    public void setBaseRecordType(String baseRecordType)
    {
        internalAttributes.put(InternalAttribute.ATTR_BASE_RECORD_TYPE, baseRecordType);
    }

    public void setRecordWithBLOBsType(String recordWithBLOBsType)
    {
        internalAttributes.put(InternalAttribute.ATTR_RECORD_WITH_BLOBS_TYPE, recordWithBLOBsType);
    }

    public void setExampleType(String exampleType)
    {
        internalAttributes.put(InternalAttribute.ATTR_EXAMPLE_TYPE, exampleType);
    }

    public void setIbatis2SqlMapPackage(String sqlMapPackage)
    {
        internalAttributes.put(InternalAttribute.ATTR_IBATIS2_SQL_MAP_PACKAGE, sqlMapPackage);
    }

    public void setIbatis2SqlMapFileName(String sqlMapFileName)
    {
        internalAttributes.put(InternalAttribute.ATTR_IBATIS2_SQL_MAP_FILE_NAME, sqlMapFileName);
    }

    public void setIbatis2SqlMapNamespace(String sqlMapNamespace)
    {
        internalAttributes.put(InternalAttribute.ATTR_IBATIS2_SQL_MAP_NAMESPACE, sqlMapNamespace);
    }

    public void setSqlMapFullyQualifiedRuntimeTableName(String fullyQualifiedRuntimeTableName)
    {
        internalAttributes.put(InternalAttribute.ATTR_FULLY_QUALIFIED_TABLE_NAME_AT_RUNTIME, fullyQualifiedRuntimeTableName);
    }

    public void setSqlMapAliasedFullyQualifiedRuntimeTableName(String aliasedFullyQualifiedRuntimeTableName)
    {
        internalAttributes.put(InternalAttribute.ATTR_ALIASED_FULLY_QUALIFIED_TABLE_NAME_AT_RUNTIME, aliasedFullyQualifiedRuntimeTableName);
    }

    public String getMyBatis3XmlMapperPackage()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_MYBATIS3_XML_MAPPER_PACKAGE);
    }

    public void setMyBatis3XmlMapperPackage(String mybatis3XmlMapperPackage)
    {
        internalAttributes.put(InternalAttribute.ATTR_MYBATIS3_XML_MAPPER_PACKAGE, mybatis3XmlMapperPackage);
    }

    public String getMyBatis3XmlMapperFileName()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_MYBATIS3_XML_MAPPER_FILE_NAME);
    }

    public void setMyBatis3XmlMapperFileName(String mybatis3XmlMapperFileName)
    {
        internalAttributes.put(InternalAttribute.ATTR_MYBATIS3_XML_MAPPER_FILE_NAME, mybatis3XmlMapperFileName);
    }

    public String getMyBatis3JavaMapperType()
    {
        return (String)internalAttributes.get(InternalAttribute.ATTR_MYBATIS3_JAVA_MAPPER_TYPE);
    }

    public void setMyBatis3JavaMapperType(String mybatis3JavaMapperType)
    {
        internalAttributes.put(InternalAttribute.ATTR_MYBATIS3_JAVA_MAPPER_TYPE, mybatis3JavaMapperType);
    }

    public TargetRuntime getTargetRuntime()
    {
        return targetRuntime;
    }

    protected TableConfiguration tableConfiguration;
    protected FullyQualifiedTable fullyQualifiedTable;
    protected Context context;
    protected Rules rules;
    protected List primaryKeyColumns;
    protected List baseColumns;
    protected List blobColumns;
    protected TargetRuntime targetRuntime;
    protected Map attributes;
    protected Map internalAttributes;
}
