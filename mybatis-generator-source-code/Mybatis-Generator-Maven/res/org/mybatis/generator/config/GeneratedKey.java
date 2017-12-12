// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GeneratedKey.java

package org.mybatis.generator.config;

import java.util.List;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.db.DatabaseDialects;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

public class GeneratedKey
{

    public GeneratedKey(String column, String configuredSqlStatement, boolean isIdentity, String type)
    {
        this.column = column;
        this.type = type;
        this.isIdentity = isIdentity;
        this.configuredSqlStatement = configuredSqlStatement;
        DatabaseDialects dialect = DatabaseDialects.getDatabaseDialect(configuredSqlStatement);
        if(dialect == null)
            runtimeSqlStatement = configuredSqlStatement;
        else
            runtimeSqlStatement = dialect.getIdentityRetrievalStatement();
    }

    public String getColumn()
    {
        return column;
    }

    public boolean isIdentity()
    {
        return isIdentity;
    }

    public String getRuntimeSqlStatement()
    {
        return runtimeSqlStatement;
    }

    public String getType()
    {
        return type;
    }

    public boolean isBeforeInsert()
    {
        boolean rc;
        if(StringUtility.stringHasValue(type))
            rc = true;
        else
        if(isIdentity)
            rc = false;
        else
            rc = true;
        return rc;
    }

    public XmlElement toXmlElement()
    {
        XmlElement xmlElement = new XmlElement("generatedKey");
        xmlElement.addAttribute(new Attribute("column", column));
        xmlElement.addAttribute(new Attribute("sqlStatement", configuredSqlStatement));
        xmlElement.addAttribute(new Attribute("type", type));
        xmlElement.addAttribute(new Attribute("identity", isIdentity ? "true" : "false"));
        return xmlElement;
    }

    public void validate(List errors, String tableName)
    {
        if(!StringUtility.stringHasValue(runtimeSqlStatement))
            errors.add(Messages.getString("ValidationError.7", tableName));
        if(StringUtility.stringHasValue(type) && !"pre".equals(type) && !"post".equals(type))
            errors.add(Messages.getString("ValidationError.15", tableName));
    }

    private String column;
    private String configuredSqlStatement;
    private String runtimeSqlStatement;
    private boolean isIdentity;
    private String type;
}
