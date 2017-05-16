package cc.gukeer.common.utils;
import java.util.Properties;

/**
 * Created by conn on 10/25/16.
 */
public class ConstantsConfig extends LoggerWrapper {

    public static String SECURITY_SALT = null;
    public static String SERVER_API_URL = null;


    public static String BBB_RETURN_CODE_SUCCESS = "SUCCESS";
    public static String BBB_RETURN_CODE_FAIL = "FAILED";


    static {
        Properties prop = LoadPropertyUtil.getInstance().getProperties("/server.properties");

        SERVER_API_URL = prop.getProperty("server_url");
        SECURITY_SALT = prop.getProperty("security_salt");

    }

}
