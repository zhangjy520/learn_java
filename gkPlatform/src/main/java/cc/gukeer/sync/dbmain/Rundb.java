package cc.gukeer.sync.dbmain;

import cn.gukeer.platform.persistence.entity.TeachCycle;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by lx on 2016/12/1.
 */
public class Rundb {
    private static String DBDRIVER = "";
    private static String DBURL = "";
    private static String USERNAME = "";
    private static String PASSWORD = "";
    private static String entityList = "";

    static {
        Properties prop_db = new Properties();
        Properties prop_entity = new Properties();
        InputStream db = Object.class.getResourceAsStream("/db.properties");
        InputStream syncentity = Object.class.getResourceAsStream("/syncentity.properties");
        try {
            prop_db.load(db);
            DBDRIVER = prop_db.getProperty("jdbc.driver");
            DBURL = prop_db.getProperty("jdbc.url");
            USERNAME = prop_db.getProperty("jdbc.username");
            PASSWORD = prop_db.getProperty("jdbc.password");
            entityList = prop_entity.getProperty("entityList");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void run(Map<String, String> map) throws SQLException {

        //数据库连接对象
        Connection conn = null;
        //数据库操作对象
        Statement stmt = null;
        //1、加载驱动程序
        try {
            Class.forName(DBDRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //2、连接数据库
        //通过连接管理器连接数据库
        try {
            //在连接的时候直接输入用户名和密码才可以连接
            conn = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //3、向数据库中插入一条数据

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //4、执行语句
        try {
            stmt.execute(map.get("isExistsTable"));
            stmt.execute(map.get("createSql"));
            stmt.execute(map.get("istiggerSql_i"));
            stmt.execute(map.get("tiggerSql_i"));
            stmt.execute(map.get("istiggerSql_m"));
            stmt.execute(map.get("tiggerSql_m"));
            stmt.execute(map.get("istiggerSql_d"));
            stmt.execute(map.get("tiggerSql_d"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //5、关闭操作
        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //获取需要同步数据的中间表
    public static List<String> getSyncTable(String schema, String mark) throws SQLException {

        //数据库连接对象
        Connection conn = null;
        //数据库操作对象
        Statement stmt = null;
        //1、加载驱动程序
        try {
            Class.forName(DBDRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //2、连接数据库
        //通过连接管理器连接数据库
        try {
            //在连接的时候直接输入用户名和密码才可以连接
            conn = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //3、向数据库中插入一条数据
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //4、执行语句
        try {
            String sql = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA ='gk_azure' AND TABLE_NAME LIKE 'sync_%'";
            ResultSet rs = stmt.executeQuery(sql);
            List<String> table_name = new ArrayList<>();
            while (rs.next()) {
                //有数据
                table_name.add(rs.getString("TABLE_NAME"));
            }
            return table_name;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //5、关闭操作
        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {




       Map<String,String>  classRoom = MakeTigger.make(TeachCycle.class);run(classRoom);
      // Map<String,String>  classRoom1 = MakeTigger.make(CourseNode.class);run(classRoom1);


    }

}




