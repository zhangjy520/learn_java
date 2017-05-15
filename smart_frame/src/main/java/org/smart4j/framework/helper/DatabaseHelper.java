package org.smart4j.framework.helper;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.util.PropsUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Administrator on 2017/4/12.
 */
public class DatabaseHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);
    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;
    private static final ThreadLocal<Connection> CONNECTION_THREAD_LOCAL = new ThreadLocal<>();
    private static final QueryRunner QUERY_RUNNER;
    private static final BasicDataSource DATA_SOURCE;

    static {
        QUERY_RUNNER = new QueryRunner();
        DATA_SOURCE = new BasicDataSource();

        Properties conf = PropsUtil.loadProps("smart.properties");
        DRIVER = conf.getProperty("jdbc.driver");
        URL = conf.getProperty("jdbc.url");
        USERNAME = conf.getProperty("jdbc.username");
        PASSWORD = conf.getProperty("jdbc.password");

        DATA_SOURCE.setDriverClassName(DRIVER);
        DATA_SOURCE.setUrl(URL);
        DATA_SOURCE.setUsername(USERNAME);
        DATA_SOURCE.setPassword(PASSWORD);

        try {
            Class.forName(DRIVER);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
       /* Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;*/
       Connection connection = CONNECTION_THREAD_LOCAL.get();
        if (connection == null)
            try {
//                connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
                connection = DATA_SOURCE.getConnection();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                CONNECTION_THREAD_LOCAL.set(connection);
            }
            return connection;
    }

    public static void closeConnection(Connection connection){
        if (connection !=null)
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }



}
