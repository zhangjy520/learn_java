package cc.gukeer.common.utils;




import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * Created by connli on 16-12-15.
 */
public class PrimaryKey {

    public static String get() {

        String uuid = UUID.randomUUID().toString();
        // StringUtils 引用 org.apache.commons.lang3.StringUtils
        String key = StringUtils.replace(uuid, "-", "");
        return key;
    }

}
