// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConnectionFactory.java

package org.mybatis.generator.internal.db;

import java.sql.*;
import java.util.Properties;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.internal.ObjectFactory;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

public class ConnectionFactory
{

    public static ConnectionFactory getInstance()
    {
        return instance;
    }

    private ConnectionFactory()
    {
    }

    public Connection getConnection(JDBCConnectionConfiguration config)
        throws SQLException
    {
        Driver driver = getDriver(config);
        Properties props = new Properties();
        if(StringUtility.stringHasValue(config.getUserId()))
            props.setProperty("user", config.getUserId());
        if(StringUtility.stringHasValue(config.getPassword()))
            props.setProperty("password", config.getPassword());
        props.putAll(config.getProperties());
        Connection conn = driver.connect(config.getConnectionURL(), props);
        if(conn == null)
            throw new SQLException(Messages.getString("RuntimeError.7"));
        else
            return conn;
    }

    private Driver getDriver(JDBCConnectionConfiguration connectionInformation)
    {
        String driverClass = connectionInformation.getDriverClass();
        Driver driver;
        try
        {
            Class clazz = ObjectFactory.externalClassForName(driverClass);
            driver = (Driver)clazz.newInstance();
        }
        catch(Exception e)
        {
            throw new RuntimeException(Messages.getString("RuntimeError.8"), e);
        }
        return driver;
    }

    private static ConnectionFactory instance = new ConnectionFactory();

}
