package cc.gukeer.common.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by conn on 10/25/16.
 */
public class LoadPropertyUtil extends LoggerWrapper {

    private static LoadPropertyUtil instance = new LoadPropertyUtil();

    private LoadPropertyUtil(){};

    public static LoadPropertyUtil getInstance() {
        return instance;
    }

    public Properties getProperties(String path) {
        Properties prop = null;
        InputStream inStream = null;
        try {
            prop = new Properties();
            inStream = getClass().getResourceAsStream(path);
            prop.load(inStream);
            inStream.close();
        } catch (Exception e) {
            logger.info("获取properties文件失败", e);
        }
        return prop;
    }

}
