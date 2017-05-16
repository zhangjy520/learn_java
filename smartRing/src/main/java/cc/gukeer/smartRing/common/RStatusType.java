package cc.gukeer.smartRing.common;

/**
 * 手环 类型、状态
 * Created by connli on 2017/1/22.
 */
public class RStatusType {

    // 手环类型 [0 个人手环， 1 临时手环 ，2未使用]
    public static final int TYPE_PERSONAL = 0;
    public static final int TYPE_TEMP = 1;
    public static final int TYPE_FREE = 2;

    // 手环状态 [0 空闲， 1 使用中， 2 已挂失， 3 已丢失]
    public static final int STATUS_UNUSED = 0;
    public static final int STATUS_USED = 1;
    public static final int STATUS_LOSING = 2;
    public static final int STATUS_LOST = 3;

}
