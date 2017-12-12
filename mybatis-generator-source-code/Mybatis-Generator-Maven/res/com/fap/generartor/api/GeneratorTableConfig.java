// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GeneratorTableConfig.java

package com.fap.generartor.api;

import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.fap.generartor.api:
//            TableConfig, NameRuler, MergeTemplete

public class GeneratorTableConfig
{

    public GeneratorTableConfig()
    {
    }

    public void generator(DatabaseMetaData databaseMetaData, String user, String templatePath)
        throws SQLException
    {
        ArrayList tableConfigs = new ArrayList();
        for(ResultSet rs = databaseMetaData.getTables(null, user, null, null); rs.next();)
        {
            TableConfig tableConfig = new TableConfig();
            String tableName = rs.getString("table_name");
            System.out.println(tableName);
            if(!tableName.contains("BIN"))
            {
                tableConfig.setTableName(tableName);
                tableConfig.setObjectName(NameRuler.classNameRuler(tableName));
                String columnName;
                for(ResultSet rsc = databaseMetaData.getColumns(null, user, tableName, null); rsc.next(); tableConfig.addColumn(new TableConfig.Column(tableConfig, columnName, NameRuler.columnNameRuler(columnName))))
                    columnName = rsc.getString("column_name");

                tableConfigs.add(tableConfig);
            }
        }

        MergeTemplete.mergeTableConfig(templatePath, tableConfigs);
    }

    public void generator(DatabaseMetaData databaseMetaData, String user, String templatePath, List tables)
        throws SQLException
    {
        ArrayList tableConfigs = new ArrayList();
        for(ResultSet rs = databaseMetaData.getTables(null, user, null, null); rs.next();)
        {
            TableConfig tableConfig = new TableConfig();
            String tableName = rs.getString("table_name");
            if(!tableName.contains("BIN") && tables.contains(tableName))
            {
                System.out.println(tableName);
                tableConfig.setTableName(tableName);
                tableConfig.setObjectName(NameRuler.classNameRuler(tableName));
                String columnName;
                for(ResultSet rsc = databaseMetaData.getColumns(null, user, tableName, null); rsc.next(); tableConfig.addColumn(new TableConfig.Column(tableConfig, columnName, NameRuler.columnNameRuler(columnName))))
                    columnName = rsc.getString("column_name");

                tableConfigs.add(tableConfig);
            }
        }

        MergeTemplete.mergeTableConfig(templatePath, tableConfigs);
    }

    public boolean contain(String value, String values[])
    {
        boolean b = false;
        for(int i = 0; i < values.length; i++)
            if(value.equals(values[i]))
                return true;

        return b;
    }
}
