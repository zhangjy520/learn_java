// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JavaTypeResolver.java

package org.mybatis.generator.api;

import java.util.List;
import java.util.Properties;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.config.Context;

// Referenced classes of package org.mybatis.generator.api:
//            IntrospectedColumn

public interface JavaTypeResolver
{

    public abstract void addConfigurationProperties(Properties properties);

    public abstract void setContext(Context context);

    public abstract void setWarnings(List list);

    public abstract FullyQualifiedJavaType calculateJavaType(IntrospectedColumn introspectedcolumn);

    public abstract String calculateJdbcTypeName(IntrospectedColumn introspectedcolumn);
}
