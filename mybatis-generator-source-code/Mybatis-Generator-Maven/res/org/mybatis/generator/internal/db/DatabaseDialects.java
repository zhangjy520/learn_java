// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DatabaseDialects.java

package org.mybatis.generator.internal.db;


public final class DatabaseDialects extends Enum
{

    private DatabaseDialects(String s, int i, String identityRetrievalStatement)
    {
        super(s, i);
        this.identityRetrievalStatement = identityRetrievalStatement;
    }

    public String getIdentityRetrievalStatement()
    {
        return identityRetrievalStatement;
    }

    public static DatabaseDialects getDatabaseDialect(String database)
    {
        DatabaseDialects returnValue = null;
        if("DB2".equalsIgnoreCase(database))
            returnValue = DB2;
        else
        if("MySQL".equalsIgnoreCase(database))
            returnValue = MYSQL;
        else
        if("SqlServer".equalsIgnoreCase(database))
            returnValue = SQLSERVER;
        else
        if("Cloudscape".equalsIgnoreCase(database))
            returnValue = CLOUDSCAPE;
        else
        if("Derby".equalsIgnoreCase(database))
            returnValue = DERBY;
        else
        if("HSQLDB".equalsIgnoreCase(database))
            returnValue = HSQLDB;
        else
        if("SYBASE".equalsIgnoreCase(database))
            returnValue = SYBASE;
        else
        if("DB2_MF".equalsIgnoreCase(database))
            returnValue = DB2_MF;
        else
        if("Informix".equalsIgnoreCase(database))
            returnValue = INFORMIX;
        return returnValue;
    }

    public static DatabaseDialects[] values()
    {
        DatabaseDialects adatabasedialects[];
        int i;
        DatabaseDialects adatabasedialects1[];
        System.arraycopy(adatabasedialects = ENUM$VALUES, 0, adatabasedialects1 = new DatabaseDialects[i = adatabasedialects.length], 0, i);
        return adatabasedialects1;
    }

    public static DatabaseDialects valueOf(String s)
    {
        return (DatabaseDialects)Enum.valueOf(org/mybatis/generator/internal/db/DatabaseDialects, s);
    }

    public static final DatabaseDialects DB2;
    public static final DatabaseDialects MYSQL;
    public static final DatabaseDialects SQLSERVER;
    public static final DatabaseDialects CLOUDSCAPE;
    public static final DatabaseDialects DERBY;
    public static final DatabaseDialects HSQLDB;
    public static final DatabaseDialects SYBASE;
    public static final DatabaseDialects DB2_MF;
    public static final DatabaseDialects INFORMIX;
    private String identityRetrievalStatement;
    private static final DatabaseDialects ENUM$VALUES[];

    static 
    {
        DB2 = new DatabaseDialects("DB2", 0, "VALUES IDENTITY_VAL_LOCAL()");
        MYSQL = new DatabaseDialects("MYSQL", 1, "SELECT LAST_INSERT_ID()");
        SQLSERVER = new DatabaseDialects("SQLSERVER", 2, "SELECT SCOPE_IDENTITY()");
        CLOUDSCAPE = new DatabaseDialects("CLOUDSCAPE", 3, "VALUES IDENTITY_VAL_LOCAL()");
        DERBY = new DatabaseDialects("DERBY", 4, "VALUES IDENTITY_VAL_LOCAL()");
        HSQLDB = new DatabaseDialects("HSQLDB", 5, "CALL IDENTITY()");
        SYBASE = new DatabaseDialects("SYBASE", 6, "SELECT @@IDENTITY");
        DB2_MF = new DatabaseDialects("DB2_MF", 7, "SELECT IDENTITY_VAL_LOCAL() FROM SYSIBM.SYSDUMMY1");
        INFORMIX = new DatabaseDialects("INFORMIX", 8, "select dbinfo('sqlca.sqlerrd1') from systables where tabid=1");
        ENUM$VALUES = (new DatabaseDialects[] {
            DB2, MYSQL, SQLSERVER, CLOUDSCAPE, DERBY, HSQLDB, SYBASE, DB2_MF, INFORMIX
        });
    }
}
