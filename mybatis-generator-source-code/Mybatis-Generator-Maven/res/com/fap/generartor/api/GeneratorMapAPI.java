// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GeneratorMapAPI.java

package com.fap.generartor.api;

import java.io.IOException;
import java.sql.*;

// Referenced classes of package com.fap.generartor.api:
//            MapperAPI, NameRuler, Java, Method, 
//            Parameter, Field, MergeTemplete

public class GeneratorMapAPI
{

    public GeneratorMapAPI()
    {
    }

    public void generator(DatabaseMetaData databaseMetaData, String packageName, String schema, String javaFilePath)
        throws SQLException, IOException
    {
        MapperAPI mapper = new MapperAPI();
        mapper.setName((new StringBuilder(String.valueOf(NameRuler.classNameRuler(packageName)))).append("Mapper").toString());
        String desprator = "src/main/java";
        String packageDeclaration = javaFilePath.substring(javaFilePath.indexOf(desprator) + desprator.length() + 1, javaFilePath.length() - 1);
        packageDeclaration = packageDeclaration.replace("/", ".");
        mapper.setPackages(packageDeclaration);
        ResultSet rs;
        Java out_class;
        for(rs = databaseMetaData.getProcedures(packageName, schema.toUpperCase(), null); rs.next(); mapper.addOutParameterClass(out_class))
        {
            Java in_class = new Java();
            out_class = new Java();
            String procedure_cat = rs.getString("procedure_cat");
            String procedure_schem = rs.getString("procedure_schem");
            String procedure_name = rs.getString("procedure_name");
            Method method = new Method();
            method.setName(NameRuler.fieldNameRuler(procedure_name));
            method.setReturnType(NameRuler.classNameRuler((new StringBuilder(String.valueOf(procedure_cat))).append("_").append(procedure_name).append("_OUT").toString()));
            method.addParameters(new Parameter("in_params", NameRuler.classNameRuler((new StringBuilder(String.valueOf(procedure_cat))).append("_").append(procedure_name).append("_IN").toString())));
            mapper.addMethod(method);
            in_class.setName(NameRuler.classNameRuler((new StringBuilder(String.valueOf(procedure_cat))).append("_").append(procedure_name).append("_IN").toString()));
            out_class.setName(NameRuler.classNameRuler((new StringBuilder(String.valueOf(procedure_cat))).append("_").append(procedure_name).append("_OUT").toString()));
            ResultSet parameterSet;
            for(parameterSet = databaseMetaData.getProcedureColumns(procedure_cat, procedure_schem, procedure_name, null); parameterSet.next();)
            {
                String fieldName = NameRuler.fieldNameRuler(parameterSet.getString("COLUMN_NAME"));
                if(parameterSet.getString("column_type").equals("1"))
                    in_class.addField(new Field(fieldName, "String"));
                else
                if(parameterSet.getString("column_type").equals("4"))
                    out_class.addField(new Field(fieldName, "String"));
                else
                    parameterSet.getString("column_type").equals("111");
            }

            parameterSet.close();
            mapper.addInParameterClass(in_class);
        }

        rs.close();
        MergeTemplete.mergeMapperAPI("mapperapi.vm", mapper, javaFilePath);
    }

    public void generator(Connection connection, String packageName, String schema, String javaFilePath)
        throws SQLException, IOException
    {
        MapperAPI mapper = new MapperAPI();
        mapper.setName((new StringBuilder(String.valueOf(NameRuler.classNameRuler(packageName)))).append("Mapper").toString());
        String desprator = "src/main/java";
        String packageDeclaration = javaFilePath.substring(javaFilePath.indexOf(desprator) + desprator.length() + 1, javaFilePath.length() - 1);
        packageDeclaration = packageDeclaration.replace("/", ".");
        mapper.setPackages(packageDeclaration);
        String SQL_GET_PROCEDURE = "SELECT                 package_name AS procedure_cat,         owner AS procedure_schem,              object_name AS procedure_name,         NULL,                                  NULL,                                  NULL,                                  'Packaged procedure' AS remarks,       1 AS procedure_type                  FROM all_arguments                     WHERE argument_name IS NOT NULL          AND position = 1                       AND position = sequence                AND package_name LIKE ? ESCAPE '/'     AND owner LIKE ? ESCAPE '/'          ";
        String SQL_GET_PROCEDURE_COLUMNS = "SELECT package_name AS procedure_cat,       owner AS procedure_schem,                                   object_name AS procedure_name,                              argument_name AS column_name,                               DECODE(position, 0, 5,                                                       DECODE(in_out, 'IN', 1,                                                    'OUT', 4,                                                   'IN/OUT', 2,                                                0)) AS column_type,         DECODE (data_type, 'CHAR', 1,                                                  'VARCHAR2', 12,                                             'NUMBER', 3,                                                'LONG', -1,                                                 'DATE', 93,                                                 'RAW', -3,                                                  'LONG RAW', -4,                                             1111) AS data_type,                      data_type AS type_name,                                     DECODE (data_precision, NULL, data_length,                                          data_precision) AS precision,       data_length AS length,                                      data_scale AS scale,                                        10 AS radix,                                                1 AS nullable,                                              NULL AS remarks,                                            sequence,                                                   overload,                                                   type_subname,                                               data_level,                                                 default_value                                         FROM all_arguments                                         WHERE owner LIKE ? ESCAPE '/'                                 AND object_name LIKE ? ESCAPE '/'                           AND package_name LIKE ? ESCAPE '/'                        ORDER BY procedure_schem, procedure_name, overload, sequence";
        String SQL_GET_CURSOR_DEFINE = "SELECT package_name AS procedure_cat,       owner AS procedure_schem,                                   object_name AS procedure_name,                              argument_name AS column_name,                               DECODE(position, 0, 5,                                                       DECODE(in_out, 'IN', 1,                                                    'OUT', 4,                                                   'IN/OUT', 2,                                                0)) AS column_type,         DECODE (data_type, 'CHAR', 1,                                                  'VARCHAR2', 12,                                             'NUMBER', 3,                                                'LONG', -1,                                                 'DATE', 93,                                                 'RAW', -3,                                                  'LONG RAW', -4,                                             1111) AS data_type,                      data_type AS type_name,                                     DECODE (data_precision, NULL, data_length,                                          data_precision) AS precision,       data_length AS length,                                      data_scale AS scale,                                        10 AS radix,                                                1 AS nullable,                                              NULL AS remarks,                                            sequence,                                                   overload,                                                   type_subname,                                               data_level,                                                 default_value                                         FROM all_arguments                                         WHERE data_level > 0\t\t\t\t\t\t\t\t\t\t    AND owner LIKE ? ESCAPE '/'                                 AND package_name LIKE ? ESCAPE '/'                        ORDER BY procedure_schem, procedure_name, overload, sequence";
        PreparedStatement cur_statement = connection.prepareStatement(SQL_GET_CURSOR_DEFINE, 1004, 1007);
        cur_statement.setString(1, schema.toUpperCase());
        cur_statement.setString(2, packageName);
        ResultSet cur_define = cur_statement.executeQuery();
        if(cur_define.next())
        {
            mapper.addImport("java.util.List");
            do
                if(cur_define.getString("data_level").equals("1"))
                {
                    Java cur_class = new Java();
                    cur_class.setName(NameRuler.classNameRuler(cur_define.getString("type_subname")));
                    mapper.addRecordClass(cur_class);
                    while(cur_define.next()) 
                    {
                        if(cur_define.getString("data_level").equals("2"))
                        {
                            cur_class.addField(new Field(NameRuler.fieldNameRuler(cur_define.getString("COLUMN_NAME")), "String"));
                            continue;
                        }
                        if(!cur_define.getString("data_level").equals("1"))
                            continue;
                        cur_define.previous();
                        break;
                    }
                }
            while(cur_define.next());
        }
        cur_define.close();
        cur_statement.close();
        PreparedStatement procedure_statement = connection.prepareStatement(SQL_GET_PROCEDURE);
        procedure_statement.setString(1, packageName);
        procedure_statement.setString(2, schema.toUpperCase());
        ResultSet rs;
        Java out_class;
        for(rs = procedure_statement.executeQuery(); rs.next(); mapper.addOutParameterClass(out_class))
        {
            Java in_class = new Java();
            out_class = new Java();
            String procedure_cat = rs.getString("procedure_cat");
            String procedure_schem = rs.getString("procedure_schem");
            String procedure_name = rs.getString("procedure_name");
            Method method = new Method();
            method.setName(NameRuler.fieldNameRuler(procedure_name));
            method.setReturnType(NameRuler.classNameRuler((new StringBuilder(String.valueOf(procedure_cat))).append("_").append(procedure_name).append("_OUT").toString()));
            method.addParameters(new Parameter("in_params", NameRuler.classNameRuler((new StringBuilder(String.valueOf(procedure_cat))).append("_").append(procedure_name).append("_IN").toString())));
            mapper.addMethod(method);
            in_class.setName(NameRuler.classNameRuler((new StringBuilder(String.valueOf(procedure_cat))).append("_").append(procedure_name).append("_IN").toString()));
            out_class.setName(NameRuler.classNameRuler((new StringBuilder(String.valueOf(procedure_cat))).append("_").append(procedure_name).append("_OUT").toString()));
            PreparedStatement statement = connection.prepareStatement(SQL_GET_PROCEDURE_COLUMNS);
            statement.setString(1, procedure_schem);
            statement.setString(2, procedure_name);
            statement.setString(3, procedure_cat);
            ResultSet parameterSet;
            for(parameterSet = statement.executeQuery(); parameterSet.next();)
            {
                String fieldName = NameRuler.fieldNameRuler(parameterSet.getString("COLUMN_NAME"));
                if(parameterSet.getString("column_type").equals("1") && parameterSet.getString("data_level").equals("0"))
                    in_class.addField(new Field(fieldName, "String"));
                else
                if(parameterSet.getString("column_type").equals("4") && !parameterSet.getString("data_type").equals("1111") && parameterSet.getString("data_level").equals("0"))
                    out_class.addField(new Field(fieldName, "String"));
                else
                if(parameterSet.getString("column_type").equals("4") && parameterSet.getString("data_type").equals("1111") && parameterSet.getString("data_level").equals("0"))
                {
                    parameterSet.next();
                    out_class.addField(new Field((new StringBuilder(String.valueOf(fieldName))).append("s").toString(), (new StringBuilder("List<")).append(NameRuler.classNameRuler(parameterSet.getString("type_subname"))).append(">").toString()));
                }
            }

            parameterSet.close();
            statement.close();
            mapper.addInParameterClass(in_class);
        }

        rs.close();
        procedure_statement.close();
        MergeTemplete.mergeMapperAPI("mapperapi.vm", mapper, javaFilePath);
    }
}
