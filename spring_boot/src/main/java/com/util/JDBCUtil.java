package com.util;

import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by jon on 17-6-19.
 */
public class JDBCUtil {

    //数据库连接池
    private BoneCPDataSource boneCPDataSource;

    public void setBoneCPDataSource(BoneCPDataSource boneCPDataSource) {
        this.boneCPDataSource = boneCPDataSource;
    }

    //使用连接池获得链接
    public Connection getPoolConnection(){
        Connection connection = null;
        try {
            connection = boneCPDataSource.getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  connection;
    }

    //手动获取连接
    public static Connection getConnection(){
        Properties properties = new Properties();
        InputStream in = null;
        String driver = null;
        String url = null;
        String username = null;
        String password = null;
        Connection connection = null;

        try {
            in = new ClassPathResource("/db.properties").getInputStream();
            properties.load(in);
            driver = properties.getProperty("jdbc.driver");
            url = properties.getProperty("jdbc.url");
            username = properties.getProperty("jdbc.username");
            password = properties.getProperty("jdbc.password");
            Class.forName(driver);
            connection = (Connection) DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void returnResource(Connection conn){
        try {
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void returnResource(PreparedStatement ps){
        try {
            if(ps != null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
