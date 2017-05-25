package cn.gukeer.common.utils;

import org.flywaydb.core.Flyway;

/**
 * Created by conn on 2016/8/2.
 */
public class DBMigrate {

    public static void main(String args[]) {

        String url = "jdbc:mysql://localhost:3306/gk_platform?useUnicode=true&characterEncoding=utf-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
        String username = "root";
        String password = "Jose1123";

        String scriptPath = "filesystem:D:\\projects-codes-tools\\codes\\svn\\gkPlatform\\src\\main\\resources\\db\\migrate";

        Flyway flyway = new Flyway();
        flyway.setDataSource(url, username, password);

        flyway.setSchemas("gk_platform");
        flyway.setTable("schema_version");
        flyway.setLocations(scriptPath);
        flyway.setEncoding("UTF-8");

        flyway.migrate();
    }
}
