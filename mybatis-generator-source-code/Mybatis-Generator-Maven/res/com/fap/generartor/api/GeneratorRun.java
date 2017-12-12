// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GeneratorRun.java

package com.fap.generartor.api;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.fap.generartor.api:
//            GeneratorMapAPI, GeneratorMapper, GeneratorTableConfig

public class GeneratorRun
{

    public GeneratorRun()
    {
    }

    public static void main(String args[])
        throws SQLException, IOException
    {
        String uri = "jdbc:oracle:thin:@svnserver:1521:ebank";
        String user = "XPAYMENTV3";
        String pwd = "XPAYMENTV3";
        Connection connection = createConnection(uri, user, pwd);
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        String packages[] = {
            "A_TEST", "APP_FEE_ORDER", "APP_GAMECARD_ORDER", "APP_OIL_ORDER", "APP_PER_CALCULATE_FEE", "COR_PCKG_LOGON", "COR_PCKG_SUBACC_MANAGE", "IM_CHECKLIST", "IM_PCKG_AUTH", "IM_PCKG_LOGON", 
            "IM_PCKG_PUBLIC", "MER_PCKG_LOGON", "PAY_ACCOUNT_STATIC", "PAY_ACCOUT_PAY", "PAY_AUTOSETTLE", "PAY_BANK_PAY", "PAY_CALCULATE_CHARGE_FEE", "PAY_CASH", "PAY_COMP_PAY", "PAY_HANDLE_BIZ", 
            "PAY_LIMIT_CHECK", "PAY_ORDER", "PAY_PAYMENT", "PAY_PRE_CARD_PAY", "PAY_PRE_CHECK", "PAY_PRE_QUICK_PAY", "PAY_REALTIME_PAY", "PAY_RECHARGE", "PAY_RECHARGETOCARD", "PAY_REFUND", 
            "PAY_REGULATE", "PAY_SECURED_PAY", "PAY_SEC_DELAY", "PAY_SEC_JUDGE", "PAY_SEC_REFUND", "PAY_SETTLE", "PAY_TRANSFER", "PER_FIND_PWD_SECURITY", "PER_PCKG_LOGON", "PER_PCKG_ORDER", 
            "PER_PCKG_SECURITY", "PER_SECURITY", "SQL_LOG", "STA_DAY_COUNT"
        };
        for(int i = 0; i < packages.length; i++)
        {
            GeneratorMapAPI generatorMapAPI = new GeneratorMapAPI();
            generatorMapAPI.generator(connection, packages[i], user, javaFilePath);
            GeneratorMapper generatorMapper = new GeneratorMapper();
            generatorMapper.generator(connection, packages[i], user, xmlFilePath);
        }

        GeneratorTableConfig generatorTableConfig = new GeneratorTableConfig();
        List tables = new ArrayList();
        tables.add("PER_MENU_RES");
        tables.add("PER_ROLE_RES");
        tables.add("PER_CST_ROLE");
        tables.add("PER_RESOURCE");
        generatorTableConfig.generator(databaseMetaData, user, "", tables);
    }

    private static Connection createConnection(String dburl, String dbname, String dbpwd)
    {
        Connection connection = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(dburl, dbname, dbpwd);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return connection;
    }

    static String javaFilePath = "D:\\test\\java\\com\\rrtx\\payment\\db\\procedure\\mapper\\";
    static String xmlFilePath = "D:\\test\\resources\\com\\rrtx\\payment\\db\\procedure\\mapper\\";

}
