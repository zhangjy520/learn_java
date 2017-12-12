// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JavaTypeResolverDefaultImpl.java

package org.mybatis.generator.internal.types;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

// Referenced classes of package org.mybatis.generator.internal.types:
//            JavaTypeResolverDefaultImpl

private static class JavaTypeResolverDefaultImpl$JdbcTypeInformation
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

    public JavaTypeResolverDefaultImpl$JdbcTypeInformation(String jdbcTypeName, FullyQualifiedJavaType fullyQualifiedJavaType)
    {
        this.jdbcTypeName = jdbcTypeName;
        this.fullyQualifiedJavaType = fullyQualifiedJavaType;
    }
}
