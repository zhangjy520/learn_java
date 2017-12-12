// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GeneratorMapper.java

package com.fap.generartor.api;

import java.io.PrintStream;
import java.sql.*;

// Referenced classes of package com.fap.generartor.api:
//            MyBatisMapper, NameRuler, SelectElement, JDBCParameter, 
//            MergeTemplete, ResultMap, Result

public class GeneratorMapper
{

    public GeneratorMapper()
    {
    }

    public void generator(DatabaseMetaData databaseMetaData, String packageName, String schema, String javaFilePath)
        throws SQLException
    {
        MyBatisMapper mapper = new MyBatisMapper();
        mapper.setName((new StringBuilder(String.valueOf(NameRuler.classNameRuler(packageName)))).append("Mapper").toString());
        String desprator = "src/main/java";
        String packageDeclaration = javaFilePath.substring(javaFilePath.indexOf(desprator) + desprator.length() + 1, javaFilePath.length() - 1);
        packageDeclaration = packageDeclaration.replace("/", ".");
        mapper.setNamespace((new StringBuilder(String.valueOf(packageDeclaration))).append(".").append(NameRuler.classNameRuler(packageName)).append("Mapper").toString());
        ResultSet rs;
        SelectElement selectElement;
        for(rs = databaseMetaData.getProcedures(packageName, schema.toUpperCase(), null); rs.next(); mapper.addSelectElements(selectElement))
        {
            String procedure_cat = rs.getString("procedure_cat");
            String procedure_schem = rs.getString("procedure_schem");
            String procedure_name = rs.getString("procedure_name");
            System.out.println((new StringBuilder(String.valueOf(procedure_cat))).append("_").append(procedure_schem).append("_").append(procedure_name).toString());
            selectElement = new SelectElement();
            selectElement.setName(NameRuler.fieldNameRuler(procedure_name));
            selectElement.setParameterType((new StringBuilder(String.valueOf(procedure_cat))).append("_").append(procedure_name).append("_IN").toString());
            selectElement.setResultType((new StringBuilder(String.valueOf(procedure_cat))).append("_").append(procedure_name).append("_OUT").toString());
            selectElement.setProcedureFullName((new StringBuilder(String.valueOf(procedure_cat))).append(".").append(procedure_name).toString());
            ResultSet parameterSet;
            for(parameterSet = databaseMetaData.getProcedureColumns(procedure_cat, procedure_schem, procedure_name, null); parameterSet.next();)
                if(parameterSet.getString("column_type").equals("1"))
                    selectElement.addJdbcParameter(new JDBCParameter(parameterSet.getString("COLUMN_NAME"), "IN", "VARCHAR", null));
                else
                if(parameterSet.getString("column_type").equals("4"))
                    selectElement.addJdbcParameter(new JDBCParameter(parameterSet.getString("COLUMN_NAME"), "OUT", "VARCHAR", null));

            parameterSet.close();
        }

        rs.close();
        MergeTemplete.mergeXML("mapper.vm", mapper, javaFilePath);
    }

    public void generator(Connection connection, String packageName, String schema, String xmlFilePath)
        throws SQLException
    {
        MyBatisMapper mapper = new MyBatisMapper();
        mapper.setName((new StringBuilder(String.valueOf(NameRuler.classNameRuler(packageName)))).append("Mapper").toString());
        mapper.setNamespace((new StringBuilder("com.rrtx.payment.db.procedure.mapper.")).append(NameRuler.classNameRuler(packageName)).append("Mapper").toString());
        String SQL_GET_PROCEDURE = "SELECT                 package_name AS procedure_cat,         owner AS procedure_schem,              object_name AS procedure_name,         NULL,                                  NULL,                                  NULL,                                  'Packaged procedure' AS remarks,       1 AS procedure_type                  FROM all_arguments                     WHERE argument_name IS NOT NULL          AND position = 1                       AND position = sequence                AND package_name LIKE ? ESCAPE '/'     AND owner LIKE ? ESCAPE '/'          ";
        String SQL_GET_PROCEDURE_COLUMNS = "SELECT package_name AS procedure_cat,             owner AS procedure_schem,                                   object_name AS procedure_name,                              argument_name AS column_name,                               DECODE(position, 0, 5,                                                       DECODE(in_out, 'IN', 1,                                                    'OUT', 4,                                                   'IN/OUT', 2,                                                0)) AS column_type,         DECODE (data_type, 'CHAR', 1,                                                  'VARCHAR2', 12,                                             'NUMBER', 3,                                                'LONG', -1,                                                 'DATE', 93,                                                 'RAW', -3,                                                  'LONG RAW', -4,                                             1111) AS data_type,                      data_type AS type_name,                                     DECODE (data_precision, NULL, data_length,                                          data_precision) AS precision,       data_length AS length,                                      data_scale AS scale,                                        10 AS radix,                                                1 AS nullable,                                              NULL AS remarks,                                            sequence,                                                   overload,                                                   type_subname,                                               data_level,                                                 default_value                                         FROM all_arguments                                         WHERE owner LIKE ? ESCAPE '/'                                 AND object_name LIKE ? ESCAPE '/'                           AND package_name LIKE ? ESCAPE '/'                        ORDER BY procedure_schem, procedure_name, overload, sequence";
        String SQL_GET_CURSOR_DEFINE = "SELECT package_name AS procedure_cat,       owner AS procedure_schem,                                   object_name AS procedure_name,                              argument_name AS column_name,                               DECODE(position, 0, 5,                                                       DECODE(in_out, 'IN', 1,                                                    'OUT', 4,                                                   'IN/OUT', 2,                                                0)) AS column_type,         DECODE (data_type, 'CHAR', 1,                                                  'VARCHAR2', 12,                                             'NUMBER', 3,                                                'LONG', -1,                                                 'DATE', 93,                                                 'RAW', -3,                                                  'LONG RAW', -4,                                             1111) AS data_type,                      data_type AS type_name,                                     DECODE (data_precision, NULL, data_length,                                          data_precision) AS precision,       data_length AS length,                                      data_scale AS scale,                                        10 AS radix,                                                1 AS nullable,                                              NULL AS remarks,                                            sequence,                                                   overload,                                                   type_subname,                                               data_level,                                                 default_value                                         FROM all_arguments                                         WHERE data_level > 0\t\t\t\t\t\t\t\t\t\t    AND owner LIKE ? ESCAPE '/'                                 AND package_name LIKE ? ESCAPE '/'                        ORDER BY procedure_schem, procedure_name, overload, sequence";
        PreparedStatement cur_statement = connection.prepareStatement(SQL_GET_CURSOR_DEFINE, 1004, 1007);
        cur_statement.setString(1, schema.toUpperCase());
        cur_statement.setString(2, packageName);
        ResultSet cur_define;
        for(cur_define = cur_statement.executeQuery(); cur_define.next();)
            if(cur_define.getString("data_level").equals("1"))
            {
                ResultMap resultMap = new ResultMap();
                resultMap.setId(NameRuler.fieldNameRuler(cur_define.getString("type_subname")));
                resultMap.setType((new StringBuilder(String.valueOf(mapper.getNamespace()))).append("$").append(NameRuler.classNameRuler(cur_define.getString("type_subname"))).toString());
                mapper.addResultMaps(resultMap);
                while(cur_define.next()) 
                {
                    if(cur_define.getString("data_level").equals("2"))
                    {
                        resultMap.addResult(new Result(cur_define.getString("COLUMN_NAME"), NameRuler.fieldNameRuler(cur_define.getString("COLUMN_NAME")), "VARCHAR", "String"));
                        continue;
                    }
                    if(!cur_define.getString("data_level").equals("1"))
                        continue;
                    cur_define.previous();
                    break;
                }
            }

        cur_define.close();
        cur_statement.close();
        PreparedStatement procedure_statement = connection.prepareStatement(SQL_GET_PROCEDURE);
        procedure_statement.setString(1, packageName);
        procedure_statement.setString(2, schema.toUpperCase());
        ResultSet rs;
        SelectElement selectElement;
        for(rs = procedure_statement.executeQuery(); rs.next(); mapper.addSelectElements(selectElement))
        {
            String procedure_cat = rs.getString("procedure_cat");
            String procedure_schem = rs.getString("procedure_schem");
            String procedure_name = rs.getString("procedure_name");
            System.out.println((new StringBuilder(String.valueOf(procedure_cat))).append("_").append(procedure_schem).append("_").append(procedure_name).toString());
            selectElement = new SelectElement();
            selectElement.setName(NameRuler.fieldNameRuler(procedure_name));
            selectElement.setParameterType((new StringBuilder(String.valueOf(procedure_cat))).append("_").append(procedure_name).append("_IN").toString());
            selectElement.setResultType((new StringBuilder(String.valueOf(procedure_cat))).append("_").append(procedure_name).append("_OUT").toString());
            selectElement.setProcedureFullName((new StringBuilder(String.valueOf(procedure_cat))).append(".").append(procedure_name).toString());
            PreparedStatement statement = connection.prepareStatement(SQL_GET_PROCEDURE_COLUMNS);
            statement.setString(1, procedure_schem);
            statement.setString(2, procedure_name);
            statement.setString(3, procedure_cat);
            ResultSet parameterSet;
            for(parameterSet = statement.executeQuery(); parameterSet.next();)
            {
                String columnName = NameRuler.fieldNameRuler(parameterSet.getString("COLUMN_NAME"));
                if(parameterSet.getString("column_type").equals("1") && parameterSet.getString("data_level").equals("0"))
                    selectElement.addJdbcParameter(new JDBCParameter(columnName, "IN", "VARCHAR", null));
                else
                if(parameterSet.getString("column_type").equals("4") && !parameterSet.getString("data_type").equals("1111") && parameterSet.getString("data_level").equals("0"))
                    selectElement.addJdbcParameter(new JDBCParameter(columnName, "OUT", "VARCHAR", null));
                else
                if(parameterSet.getString("column_type").equals("4") && parameterSet.getString("data_type").equals("1111") && parameterSet.getString("data_level").equals("0"))
                {
                    parameterSet.next();
                    selectElement.addJdbcParameter(new JDBCParameter(columnName, "OUT", "CURSOR", NameRuler.fieldNameRuler(parameterSet.getString("type_subname"))));
                }
            }

            parameterSet.close();
            statement.close();
        }

        rs.close();
        procedure_statement.close();
        MergeTemplete.mergeXML("mapper.vm", mapper, xmlFilePath);
    }
}
