package cc.gukeer.common.utils;

import java.nio.charset.Charset;

public class Constants {

    // 验证码有效期 10 分钟
    public static final int CAPTCHA_EXPIRE_MINUTES = 10;

    // 七牛
    public static final String QINIU_ACCESS_KEY = "GjOjwlgjG4qYsrlVF3gpFNehgXjvztftAW_pAKh2";
    public static final String QINIU_SECRET_KEY = "KISZ2wRY4Mz-lpWd4iTUk0KajgyUt-_J8Hyy2nLm";

    public static final String DEFAULT_PASSWORD = "123456789";

    public static final int PAGE_COUNT = 10;

    public static final String TYPE_PHONE = "p";
    public static final String TYPE_SCHOOL_IDENTITY = "s";

    public static String RESOURCE_SERVER_NAME = "cc.gukeer";

    //oauth2
    public static final String INVALID_CLIENT_DESCRIPTION = "客户端验证失败，如错误的client_id/client_secret";
    public static final String OAUTH_USER_CACHE_KEY ="AG23ab_dhSA+psAjwQ_mlgQKNKLD";


    /**
     * 版本号
     */
    public static final String VERSION = "7.2.4";
    /**
     * 块大小，不能改变
     */
    public static final int BLOCK_SIZE = 4 * 1024 * 1024;
    /**
     * 所有都是UTF-8编码
     */
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    /**
     * 连接超时时间 单位秒(默认10s)
     */
    public static final int CONNECT_TIMEOUT = 10;
    /**
     * 写超时时间 单位秒(默认 0 , 不超时)
     */
    public static final int WRITE_TIMEOUT = 0;
    /**
     * 回复超时时间 单位秒(默认30s)
     */
    public static final int READ_TIMEOUT = 30;

    private Constants() {
    }
}
