// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DatabaseIntrospector.java

package org.mybatis.generator.internal.db;

import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.ObjectFactory;
import org.mybatis.generator.internal.util.JavaBeansUtil;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;
import org.mybatis.generator.logging.Log;
import org.mybatis.generator.logging.LogFactory;

// Referenced classes of package org.mybatis.generator.internal.db:
//            ActualTableName, SqlReservedWords

public class DatabaseIntrospector
{

    public DatabaseIntrospector(Context context, DatabaseMetaData databaseMetaData, JavaTypeResolver javaTypeResolver, List warnings)
    {
        this.context = context;
        this.databaseMetaData = databaseMetaData;
        this.javaTypeResolver = javaTypeResolver;
        this.warnings = warnings;
        logger = LogFactory.getLog(getClass());
    }

    private void calculatePrimaryKey(FullyQualifiedTable table, IntrospectedTable introspectedTable)
    {
        ResultSet rs;
        rs = null;
        try
        {
            rs = databaseMetaData.getPrimaryKeys(table.getIntrospectedCatalog(), table.getIntrospectedSchema(), table.getIntrospectedTableName());
        }
        catch(SQLException e)
        {
            closeResultSet(rs);
            warnings.add(Messages.getString("Warning.15"));
            return;
        }
        try
        {
            while(rs.next()) 
            {
                String columnName = rs.getString("COLUMN_NAME");
                introspectedTable.addPrimaryKeyColumn(columnName);
            }
        }
        catch(SQLException sqlexception)
        {
            closeResultSet(rs);
            break MISSING_BLOCK_LABEL_103;
        }
        break MISSING_BLOCK_LABEL_98;
        Exception exception;
        exception;
        closeResultSet(rs);
        throw exception;
        closeResultSet(rs);
    }

    private void closeResultSet(ResultSet rs)
    {
        if(rs != null)
            try
            {
                rs.close();
            }
            catch(SQLException sqlexception) { }
    }

    private void reportIntrospectionWarnings(IntrospectedTable introspectedTable, TableConfiguration tableConfiguration, FullyQualifiedTable table)
    {
        for(Iterator iterator = tableConfiguration.getColumnOverrides().iterator(); iterator.hasNext();)
        {
            ColumnOverride columnOverride = (ColumnOverride)iterator.next();
            if(introspectedTable.getColumn(columnOverride.getColumnName()) == null)
                warnings.add(Messages.getString("Warning.3", columnOverride.getColumnName(), table.toString()));
        }

        String string;
        for(Iterator iterator1 = tableConfiguration.getIgnoredColumnsInError().iterator(); iterator1.hasNext(); warnings.add(Messages.getString("Warning.4", string, table.toString())))
            string = (String)iterator1.next();

        GeneratedKey generatedKey = tableConfiguration.getGeneratedKey();
        if(generatedKey != null && introspectedTable.getColumn(generatedKey.getColumn()) == null)
            if(generatedKey.isIdentity())
                warnings.add(Messages.getString("Warning.5", generatedKey.getColumn(), table.toString()));
            else
                warnings.add(Messages.getString("Warning.6", generatedKey.getColumn(), table.toString()));
    }

    public List introspectTables(TableConfiguration tc)
        throws SQLException
    {
        Map columns = getColumns(tc);
        if(columns.isEmpty())
        {
            warnings.add(Messages.getString("Warning.19", tc.getCatalog(), tc.getSchema(), tc.getTableName()));
            return null;
        }
        removeIgnoredColumns(tc, columns);
        calculateExtraColumnInformation(tc, columns);
        applyColumnOverrides(tc, columns);
        calculateIdentityColumns(tc, columns);
        List introspectedTables = calculateIntrospectedTables(tc, columns);
        for(Iterator iter = introspectedTables.iterator(); iter.hasNext();)
        {
            IntrospectedTable introspectedTable = (IntrospectedTable)iter.next();
            if(!introspectedTable.hasAnyColumns())
            {
                String warning = Messages.getString("Warning.1", introspectedTable.getFullyQualifiedTable().toString());
                warnings.add(warning);
                iter.remove();
            } else
            if(!introspectedTable.hasPrimaryKeyColumns() && !introspectedTable.hasBaseColumns())
            {
                String warning = Messages.getString("Warning.18", introspectedTable.getFullyQualifiedTable().toString());
                warnings.add(warning);
                iter.remove();
            } else
            {
                reportIntrospectionWarnings(introspectedTable, tc, introspectedTable.getFullyQualifiedTable());
            }
        }

        return introspectedTables;
    }

    private void removeIgnoredColumns(TableConfiguration tc, Map columns)
    {
        for(Iterator iterator = columns.entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            for(Iterator tableColumns = ((List)entry.getValue()).iterator(); tableColumns.hasNext();)
            {
                IntrospectedColumn introspectedColumn = (IntrospectedColumn)tableColumns.next();
                if(tc.isColumnIgnored(introspectedColumn.getActualColumnName()))
                {
                    tableColumns.remove();
                    if(logger.isDebugEnabled())
                        logger.debug(Messages.getString("Tracing.3", introspectedColumn.getActualColumnName(), ((ActualTableName)entry.getKey()).toString()));
                }
            }

        }

    }

    private void calculateExtraColumnInformation(TableConfiguration tc, Map columns)
    {
        StringBuilder sb = new StringBuilder();
        Pattern pattern = null;
        String replaceString = null;
        if(tc.getColumnRenamingRule() != null)
        {
            pattern = Pattern.compile(tc.getColumnRenamingRule().getSearchString());
            replaceString = tc.getColumnRenamingRule().getReplaceString();
            replaceString = replaceString != null ? replaceString : "";
        }
        for(Iterator iterator = columns.entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            for(Iterator iterator1 = ((List)entry.getValue()).iterator(); iterator1.hasNext();)
            {
                IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterator1.next();
                String calculatedColumnName;
                if(pattern == null)
                {
                    calculatedColumnName = introspectedColumn.getActualColumnName();
                } else
                {
                    Matcher matcher = pattern.matcher(introspectedColumn.getActualColumnName());
                    calculatedColumnName = matcher.replaceAll(replaceString);
                }
                if(StringUtility.isTrue(tc.getProperty("useActualColumnNames")))
                    introspectedColumn.setJavaProperty(JavaBeansUtil.getValidPropertyName(calculatedColumnName));
                else
                if(StringUtility.isTrue(tc.getProperty("useCompoundPropertyNames")))
                {
                    sb.setLength(0);
                    sb.append(calculatedColumnName);
                    sb.append('_');
                    sb.append(JavaBeansUtil.getCamelCaseString(introspectedColumn.getRemarks(), true));
                    introspectedColumn.setJavaProperty(JavaBeansUtil.getValidPropertyName(sb.toString()));
                } else
                {
                    introspectedColumn.setJavaProperty(JavaBeansUtil.getCamelCaseString(calculatedColumnName, false));
                }
                FullyQualifiedJavaType fullyQualifiedJavaType = javaTypeResolver.calculateJavaType(introspectedColumn);
                if(fullyQualifiedJavaType != null)
                {
                    introspectedColumn.setFullyQualifiedJavaType(fullyQualifiedJavaType);
                    introspectedColumn.setJdbcTypeName(javaTypeResolver.calculateJdbcTypeName(introspectedColumn));
                } else
                {
                    boolean warn = true;
                    if(tc.isColumnIgnored(introspectedColumn.getActualColumnName()))
                        warn = false;
                    ColumnOverride co = tc.getColumnOverride(introspectedColumn.getActualColumnName());
                    if(co != null && StringUtility.stringHasValue(co.getJavaType()) && StringUtility.stringHasValue(co.getJavaType()))
                        warn = false;
                    if(warn)
                    {
                        introspectedColumn.setFullyQualifiedJavaType(FullyQualifiedJavaType.getObjectInstance());
                        introspectedColumn.setJdbcTypeName("OTHER");
                        String warning = Messages.getString("Warning.14", ((ActualTableName)entry.getKey()).toString(), introspectedColumn.getActualColumnName());
                        warnings.add(warning);
                    }
                }
                if(context.autoDelimitKeywords() && SqlReservedWords.containsWord(introspectedColumn.getActualColumnName()))
                    introspectedColumn.setColumnNameDelimited(true);
                if(tc.isAllColumnDelimitingEnabled())
                    introspectedColumn.setColumnNameDelimited(true);
            }

        }

    }

    private void calculateIdentityColumns(TableConfiguration tc, Map columns)
    {
        for(Iterator iterator = columns.entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            for(Iterator iterator1 = ((List)entry.getValue()).iterator(); iterator1.hasNext();)
            {
                IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterator1.next();
                if(tc.getGeneratedKey() != null && tc.getGeneratedKey().isIdentity())
                {
                    if(introspectedColumn.isColumnNameDelimited())
                    {
                        if(introspectedColumn.getActualColumnName().equals(tc.getGeneratedKey().getColumn()))
                            introspectedColumn.setIdentity(true);
                    } else
                    if(introspectedColumn.getActualColumnName().equalsIgnoreCase(tc.getGeneratedKey().getColumn()))
                        introspectedColumn.setIdentity(true);
                } else
                {
                    introspectedColumn.setIdentity(false);
                }
            }

        }

    }

    private void applyColumnOverrides(TableConfiguration tc, Map columns)
    {
        for(Iterator iterator = columns.entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            for(Iterator iterator1 = ((List)entry.getValue()).iterator(); iterator1.hasNext();)
            {
                IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterator1.next();
                ColumnOverride columnOverride = tc.getColumnOverride(introspectedColumn.getActualColumnName());
                if(columnOverride != null)
                {
                    if(logger.isDebugEnabled())
                        logger.debug(Messages.getString("Tracing.4", introspectedColumn.getActualColumnName(), ((ActualTableName)entry.getKey()).toString()));
                    if(StringUtility.stringHasValue(columnOverride.getJavaProperty()))
                        introspectedColumn.setJavaProperty(columnOverride.getJavaProperty());
                    if(StringUtility.stringHasValue(columnOverride.getJavaType()))
                        introspectedColumn.setFullyQualifiedJavaType(new FullyQualifiedJavaType(columnOverride.getJavaType()));
                    if(StringUtility.stringHasValue(columnOverride.getJdbcType()))
                        introspectedColumn.setJdbcTypeName(columnOverride.getJdbcType());
                    if(StringUtility.stringHasValue(columnOverride.getTypeHandler()))
                        introspectedColumn.setTypeHandler(columnOverride.getTypeHandler());
                    if(columnOverride.isColumnNameDelimited())
                        introspectedColumn.setColumnNameDelimited(true);
                    introspectedColumn.setProperties(columnOverride.getProperties());
                }
            }

        }

    }

    private Map getColumns(TableConfiguration tc)
        throws SQLException
    {
        boolean delimitIdentifiers = tc.isDelimitIdentifiers() || StringUtility.stringContainsSpace(tc.getCatalog()) || StringUtility.stringContainsSpace(tc.getSchema()) || StringUtility.stringContainsSpace(tc.getTableName());
        String localCatalog;
        String localSchema;
        String localTableName;
        if(delimitIdentifiers)
        {
            localCatalog = tc.getCatalog();
            localSchema = tc.getSchema();
            localTableName = tc.getTableName();
        } else
        if(databaseMetaData.storesLowerCaseIdentifiers())
        {
            localCatalog = tc.getCatalog() != null ? tc.getCatalog().toLowerCase() : null;
            localSchema = tc.getSchema() != null ? tc.getSchema().toLowerCase() : null;
            localTableName = tc.getTableName() != null ? tc.getTableName().toLowerCase() : null;
        } else
        if(databaseMetaData.storesUpperCaseIdentifiers())
        {
            localCatalog = tc.getCatalog() != null ? tc.getCatalog().toUpperCase() : null;
            localSchema = tc.getSchema() != null ? tc.getSchema().toUpperCase() : null;
            localTableName = tc.getTableName() != null ? tc.getTableName().toUpperCase() : null;
        } else
        {
            localCatalog = tc.getCatalog();
            localSchema = tc.getSchema();
            localTableName = tc.getTableName();
        }
        if(tc.isWildcardEscapingEnabled())
        {
            String escapeString = databaseMetaData.getSearchStringEscape();
            StringBuilder sb = new StringBuilder();
            if(localSchema != null)
            {
                String token;
                for(StringTokenizer st = new StringTokenizer(localSchema, "_%", true); st.hasMoreTokens(); sb.append(token))
                {
                    token = st.nextToken();
                    if(token.equals("_") || token.equals("%"))
                        sb.append(escapeString);
                }

                localSchema = sb.toString();
            }
            sb.setLength(0);
            String token;
            for(StringTokenizer st = new StringTokenizer(localTableName, "_%", true); st.hasMoreTokens(); sb.append(token))
            {
                token = st.nextToken();
                if(token.equals("_") || token.equals("%"))
                    sb.append(escapeString);
            }

            localTableName = sb.toString();
        }
        Map answer = new HashMap();
        if(logger.isDebugEnabled())
        {
            String fullTableName = StringUtility.composeFullyQualifiedTableName(localCatalog, localSchema, localTableName, '.');
            logger.debug(Messages.getString("Tracing.1", fullTableName));
        }
        ResultSet rs;
        for(rs = databaseMetaData.getColumns(localCatalog, localSchema, localTableName, null); rs.next();)
        {
            IntrospectedColumn introspectedColumn = ObjectFactory.createIntrospectedColumn(context);
            introspectedColumn.setTableAlias(tc.getAlias());
            introspectedColumn.setJdbcType(rs.getInt("DATA_TYPE"));
            introspectedColumn.setLength(rs.getInt("COLUMN_SIZE"));
            introspectedColumn.setActualColumnName(rs.getString("COLUMN_NAME"));
            introspectedColumn.setNullable(rs.getInt("NULLABLE") == 1);
            introspectedColumn.setScale(rs.getInt("DECIMAL_DIGITS"));
            introspectedColumn.setRemarks(rs.getString("REMARKS"));
            introspectedColumn.setDefaultValue(rs.getString("COLUMN_DEF"));
            ActualTableName atn = new ActualTableName(rs.getString("TABLE_CAT"), rs.getString("TABLE_SCHEM"), rs.getString("TABLE_NAME"));
            List columns = (List)answer.get(atn);
            if(columns == null)
            {
                columns = new ArrayList();
                answer.put(atn, columns);
            }
            columns.add(introspectedColumn);
            if(logger.isDebugEnabled())
                logger.debug(Messages.getString("Tracing.2", introspectedColumn.getActualColumnName(), Integer.toString(introspectedColumn.getJdbcType()), atn.toString()));
        }

        closeResultSet(rs);
        if(answer.size() > 1 && !StringUtility.stringContainsSQLWildcard(localSchema) && !StringUtility.stringContainsSQLWildcard(localTableName))
        {
            ActualTableName inputAtn = new ActualTableName(tc.getCatalog(), tc.getSchema(), tc.getTableName());
            StringBuilder sb = new StringBuilder();
            boolean comma = false;
            ActualTableName atn;
            for(Iterator iterator = answer.keySet().iterator(); iterator.hasNext(); sb.append(atn.toString()))
            {
                atn = (ActualTableName)iterator.next();
                if(comma)
                    sb.append(',');
                else
                    comma = true;
            }

            warnings.add(Messages.getString("Warning.25", inputAtn.toString(), sb.toString()));
        }
        return answer;
    }

    private List calculateIntrospectedTables(TableConfiguration tc, Map columns)
    {
        boolean delimitIdentifiers = tc.isDelimitIdentifiers() || StringUtility.stringContainsSpace(tc.getCatalog()) || StringUtility.stringContainsSpace(tc.getSchema()) || StringUtility.stringContainsSpace(tc.getTableName());
        List answer = new ArrayList();
        IntrospectedTable introspectedTable;
        for(Iterator iterator = columns.entrySet().iterator(); iterator.hasNext(); answer.add(introspectedTable))
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            ActualTableName atn = (ActualTableName)entry.getKey();
            FullyQualifiedTable table = new FullyQualifiedTable(StringUtility.stringHasValue(tc.getCatalog()) ? atn.getCatalog() : null, StringUtility.stringHasValue(tc.getSchema()) ? atn.getSchema() : null, atn.getTableName(), tc.getDomainObjectName(), tc.getAlias(), StringUtility.isTrue(tc.getProperty("ignoreQualifiersAtRuntime")), tc.getProperty("runtimeCatalog"), tc.getProperty("runtimeSchema"), tc.getProperty("runtimeTableName"), delimitIdentifiers, context);
            introspectedTable = ObjectFactory.createIntrospectedTable(tc, table, context);
            IntrospectedColumn introspectedColumn;
            for(Iterator iterator1 = ((List)entry.getValue()).iterator(); iterator1.hasNext(); introspectedTable.addColumn(introspectedColumn))
                introspectedColumn = (IntrospectedColumn)iterator1.next();

            calculatePrimaryKey(table, introspectedTable);
        }

        return answer;
    }

    private DatabaseMetaData databaseMetaData;
    private JavaTypeResolver javaTypeResolver;
    private List warnings;
    private Context context;
    private Log logger;
}
