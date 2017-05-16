package cc.gukeer.common.tld;

import cc.gukeer.smartRing.persistence.entity.User;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by conn on 2016/8/12.
 */
public class GukeerStringUtil {

    public static Boolean isEmpty(String str) {

        return StringUtils.isEmpty(str);

    }

    public static Boolean notEmpty(String str) {

        return !isEmpty(str);

    }

    public static String formatStr(String str){
        if (StringUtils.isEmpty(str)){
            return "0";
        }
        else {
            return str;
        }
    }

    /**
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
     *
     * @param obj
     * @return
     */
    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null)
            return true;

        if (obj instanceof CharSequence)
            return ((CharSequence) obj).length() == 0;

        if (obj instanceof Collection)
            return ((Collection) obj).isEmpty();

        if (obj instanceof Map)
            return ((Map) obj).isEmpty();

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isNullOrEmpty(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }

    public static void main(String[] args) {
        List list = new ArrayList();
        Map map = new HashMap();
        User user =new User();
//        System.out.println(isNullOrEmpty(""));
//        System.out.println(isNullOrEmpty(null));
//        System.out.println(isNullOrEmpty(list));
//        System.out.println(isNullOrEmpty(map));
        System.out.println(isNullOrEmpty(user));

    }
}
