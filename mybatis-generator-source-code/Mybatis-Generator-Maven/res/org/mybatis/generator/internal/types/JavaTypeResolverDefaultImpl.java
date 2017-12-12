// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JavaTypeResolverDefaultImpl.java

package org.mybatis.generator.internal.types;

import java.math.BigDecimal;
import java.util.*;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.JavaTypeResolver;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.util.StringUtility;

public class JavaTypeResolverDefaultImpl
    implements JavaTypeResolver
{
    private static class JdbcTypeInformation
    {

        public String getJdbcTypeName()
        {
            return jdbcTypeName;
        }

        public FullyQualifiedJavaType getFullyQualifiedJavaType()
        {
            return fullyQualifiedJavaType;
        }

        private String jdbcTypeName;
        private FullyQualifiedJavaType fullyQualifiedJavaType;

        public JdbcTypeInformation(String jdbcTypeName, FullyQualifiedJavaType fullyQualifiedJavaType)
        {
            this.jdbcTypeName = jdbcTypeName;
            this.fullyQualifiedJavaType = fullyQualifiedJavaType;
        }
    }


    public JavaTypeResolverDefaultImpl()
    {
        properties = new Properties();
        typeMap = new HashMap();
        typeMap.put(Integer.valueOf(2003), new JdbcTypeInformation("ARRAY", new FullyQualifiedJavaType(java/lang/Object.getName())));
        typeMap.put(Integer.valueOf(-5), new JdbcTypeInformation("BIGINT", new FullyQualifiedJavaType(java/lang/Long.getName())));
        typeMap.put(Integer.valueOf(-2), new JdbcTypeInformation("BINARY", new FullyQualifiedJavaType("byte[]")));
        typeMap.put(Integer.valueOf(-7), new JdbcTypeInformation("BIT", new FullyQualifiedJavaType(java/lang/Boolean.getName())));
        typeMap.put(Integer.valueOf(2004), new JdbcTypeInformation("BLOB", new FullyQualifiedJavaType("byte[]")));
        typeMap.put(Integer.valueOf(16), new JdbcTypeInformation("BOOLEAN", new FullyQualifiedJavaType(java/lang/Boolean.getName())));
        typeMap.put(Integer.valueOf(1), new JdbcTypeInformation("CHAR", new FullyQualifiedJavaType(java/lang/String.getName())));
        typeMap.put(Integer.valueOf(2005), new JdbcTypeInformation("CLOB", new FullyQualifiedJavaType(java/lang/String.getName())));
        typeMap.put(Integer.valueOf(70), new JdbcTypeInformation("DATALINK", new FullyQualifiedJavaType(java/lang/Object.getName())));
        typeMap.put(Integer.valueOf(91), new JdbcTypeInformation("DATE", new FullyQualifiedJavaType(java/util/Date.getName())));
        typeMap.put(Integer.valueOf(2001), new JdbcTypeInformation("DISTINCT", new FullyQualifiedJavaType(java/lang/Object.getName())));
        typeMap.put(Integer.valueOf(8), new JdbcTypeInformation("DOUBLE", new FullyQualifiedJavaType(java/lang/Double.getName())));
        typeMap.put(Integer.valueOf(6), new JdbcTypeInformation("FLOAT", new FullyQualifiedJavaType(java/lang/Double.getName())));
        typeMap.put(Integer.valueOf(4), new JdbcTypeInformation("INTEGER", new FullyQualifiedJavaType(java/lang/Integer.getName())));
        typeMap.put(Integer.valueOf(2000), new JdbcTypeInformation("JAVA_OBJECT", new FullyQualifiedJavaType(java/lang/Object.getName())));
        typeMap.put(Integer.valueOf(-4), new JdbcTypeInformation("LONGVARBINARY", new FullyQualifiedJavaType("byte[]")));
        typeMap.put(Integer.valueOf(-1), new JdbcTypeInformation("LONGVARCHAR", new FullyQualifiedJavaType(java/lang/String.getName())));
        typeMap.put(Integer.valueOf(0), new JdbcTypeInformation("NULL", new FullyQualifiedJavaType(java/lang/Object.getName())));
        typeMap.put(Integer.valueOf(1111), new JdbcTypeInformation("OTHER", new FullyQualifiedJavaType(java/lang/Object.getName())));
        typeMap.put(Integer.valueOf(7), new JdbcTypeInformation("REAL", new FullyQualifiedJavaType(java/lang/Float.getName())));
        typeMap.put(Integer.valueOf(2006), new JdbcTypeInformation("REF", new FullyQualifiedJavaType(java/lang/Object.getName())));
        typeMap.put(Integer.valueOf(5), new JdbcTypeInformation("SMALLINT", new FullyQualifiedJavaType(java/lang/Short.getName())));
        typeMap.put(Integer.valueOf(2002), new JdbcTypeInformation("STRUCT", new FullyQualifiedJavaType(java/lang/Object.getName())));
        typeMap.put(Integer.valueOf(92), new JdbcTypeInformation("TIME", new FullyQualifiedJavaType(java/util/Date.getName())));
        typeMap.put(Integer.valueOf(93), new JdbcTypeInformation("TIMESTAMP", new FullyQualifiedJavaType(java/util/Date.getName())));
        typeMap.put(Integer.valueOf(-6), new JdbcTypeInformation("TINYINT", new FullyQualifiedJavaType(java/lang/Byte.getName())));
        typeMap.put(Integer.valueOf(-3), new JdbcTypeInformation("VARBINARY", new FullyQualifiedJavaType("byte[]")));
        typeMap.put(Integer.valueOf(12), new JdbcTypeInformation("VARCHAR", new FullyQualifiedJavaType(java/lang/String.getName())));
    }

    public void addConfigurationProperties(Properties properties)
    {
        this.properties.putAll(properties);
        forceBigDecimals = StringUtility.isTrue(properties.getProperty("forceBigDecimals"));
    }

    public FullyQualifiedJavaType calculateJavaType(IntrospectedColumn introspectedColumn)
    {
        JdbcTypeInformation jdbcTypeInformation = (JdbcTypeInformation)typeMap.get(Integer.valueOf(introspectedColumn.getJdbcType()));
        FullyQualifiedJavaType answer;
        if(jdbcTypeInformation == null)
            switch(introspectedColumn.getJdbcType())
            {
            case 2: // '\002'
            case 3: // '\003'
                if(introspectedColumn.getScale() > 0 || introspectedColumn.getLength() > 18 || forceBigDecimals)
                    answer = new FullyQualifiedJavaType(java/math/BigDecimal.getName());
                else
                if(introspectedColumn.getLength() > 9)
                    answer = new FullyQualifiedJavaType(java/lang/Long.getName());
                else
                if(introspectedColumn.getLength() > 4)
                    answer = new FullyQualifiedJavaType(java/lang/Integer.getName());
                else
                    answer = new FullyQualifiedJavaType(java/lang/Short.getName());
                break;

            default:
                answer = null;
                break;
            }
        else
            answer = jdbcTypeInformation.getFullyQualifiedJavaType();
        return answer;
    }

    public String calculateJdbcTypeName(IntrospectedColumn introspectedColumn)
    {
        JdbcTypeInformation jdbcTypeInformation = (JdbcTypeInformation)typeMap.get(Integer.valueOf(introspectedColumn.getJdbcType()));
        String answer;
        if(jdbcTypeInformation == null)
            switch(introspectedColumn.getJdbcType())
            {
            case 3: // '\003'
                answer = "DECIMAL";
                break;

            case 2: // '\002'
                answer = "NUMERIC";
                break;

            default:
                answer = null;
                break;
            }
        else
            answer = jdbcTypeInformation.getJdbcTypeName();
        return answer;
    }

    public void setWarnings(List warnings)
    {
        this.warnings = warnings;
    }

    public void setContext(Context context)
    {
        this.context = context;
    }

    protected List warnings;
    protected Properties properties;
    protected Context context;
    protected boolean forceBigDecimals;
    protected Map typeMap;
}
