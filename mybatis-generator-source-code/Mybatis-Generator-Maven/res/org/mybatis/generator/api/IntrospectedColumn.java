// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IntrospectedColumn.java

package org.mybatis.generator.api;

import java.util.Properties;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.util.StringUtility;

// Referenced classes of package org.mybatis.generator.api:
//            IntrospectedTable

public class IntrospectedColumn
{

    public IntrospectedColumn()
    {
        properties = new Properties();
    }

    public int getJdbcType()
    {
        return jdbcType;
    }

    public void setJdbcType(int jdbcType)
    {
        this.jdbcType = jdbcType;
    }

    public int getLength()
    {
        return length;
    }

    public void setLength(int length)
    {
        this.length = length;
    }

    public boolean isNullable()
    {
        return nullable;
    }

    public void setNullable(boolean nullable)
    {
        this.nullable = nullable;
    }

    public int getScale()
    {
        return scale;
    }

    public void setScale(int scale)
    {
        this.scale = scale;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Actual Column Name: ");
        sb.append(actualColumnName);
        sb.append(", JDBC Type: ");
        sb.append(jdbcType);
        sb.append(", Nullable: ");
        sb.append(nullable);
        sb.append(", Length: ");
        sb.append(length);
        sb.append(", Scale: ");
        sb.append(scale);
        sb.append(", Identity: ");
        sb.append(identity);
        return sb.toString();
    }

    public void setActualColumnName(String actualColumnName)
    {
        this.actualColumnName = actualColumnName;
        isColumnNameDelimited = StringUtility.stringContainsSpace(actualColumnName);
    }

    public boolean isIdentity()
    {
        return identity;
    }

    public void setIdentity(boolean identity)
    {
        this.identity = identity;
    }

    public boolean isBLOBColumn()
    {
        String typeName = getJdbcTypeName();
        return "BINARY".equals(typeName) || "BLOB".equals(typeName) || "CLOB".equals(typeName) || "LONGVARBINARY".equals(typeName) || "LONGVARCHAR".equals(typeName) || "VARBINARY".equals(typeName);
    }

    public boolean isStringColumn()
    {
        return fullyQualifiedJavaType.equals(FullyQualifiedJavaType.getStringInstance());
    }

    public boolean isJdbcCharacterColumn()
    {
        return jdbcType == 1 || jdbcType == 2005 || jdbcType == -1 || jdbcType == 12;
    }

    public String getJavaProperty()
    {
        return getJavaProperty(null);
    }

    public String getJavaProperty(String prefix)
    {
        if(prefix == null)
        {
            return javaProperty;
        } else
        {
            StringBuilder sb = new StringBuilder();
            sb.append(prefix);
            sb.append(javaProperty);
            return sb.toString();
        }
    }

    public void setJavaProperty(String javaProperty)
    {
        this.javaProperty = javaProperty;
    }

    public boolean isJDBCDateColumn()
    {
        return fullyQualifiedJavaType.equals(FullyQualifiedJavaType.getDateInstance()) && "DATE".equalsIgnoreCase(jdbcTypeName);
    }

    public boolean isJDBCTimeColumn()
    {
        return fullyQualifiedJavaType.equals(FullyQualifiedJavaType.getDateInstance()) && "TIME".equalsIgnoreCase(jdbcTypeName);
    }

    public String getTypeHandler()
    {
        return typeHandler;
    }

    public void setTypeHandler(String typeHandler)
    {
        this.typeHandler = typeHandler;
    }

    public String getActualColumnName()
    {
        return actualColumnName;
    }

    public void setColumnNameDelimited(boolean isColumnNameDelimited)
    {
        this.isColumnNameDelimited = isColumnNameDelimited;
    }

    public boolean isColumnNameDelimited()
    {
        return isColumnNameDelimited;
    }

    public String getJdbcTypeName()
    {
        if(jdbcTypeName == null)
            return "OTHER";
        else
            return jdbcTypeName;
    }

    public void setJdbcTypeName(String jdbcTypeName)
    {
        this.jdbcTypeName = jdbcTypeName;
    }

    public FullyQualifiedJavaType getFullyQualifiedJavaType()
    {
        return fullyQualifiedJavaType;
    }

    public void setFullyQualifiedJavaType(FullyQualifiedJavaType fullyQualifiedJavaType)
    {
        this.fullyQualifiedJavaType = fullyQualifiedJavaType;
    }

    public String getTableAlias()
    {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias)
    {
        this.tableAlias = tableAlias;
    }

    public Context getContext()
    {
        return context;
    }

    public void setContext(Context context)
    {
        this.context = context;
    }

    public IntrospectedTable getIntrospectedTable()
    {
        return introspectedTable;
    }

    public void setIntrospectedTable(IntrospectedTable introspectedTable)
    {
        this.introspectedTable = introspectedTable;
    }

    public Properties getProperties()
    {
        return properties;
    }

    public void setProperties(Properties properties)
    {
        this.properties.putAll(properties);
    }

    public String getRemarks()
    {
        return remarks;
    }

    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }

    public String getDefaultValue()
    {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue)
    {
        this.defaultValue = defaultValue;
    }

    protected String actualColumnName;
    protected int jdbcType;
    protected String jdbcTypeName;
    protected boolean nullable;
    protected int length;
    protected int scale;
    protected boolean identity;
    protected String javaProperty;
    protected FullyQualifiedJavaType fullyQualifiedJavaType;
    protected String tableAlias;
    protected String typeHandler;
    protected Context context;
    protected boolean isColumnNameDelimited;
    protected IntrospectedTable introspectedTable;
    protected Properties properties;
    protected String remarks;
    protected String defaultValue;
}
