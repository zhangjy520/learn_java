package cc.gukeer.smartRing.common;

import java.util.Comparator;

/**
 * Created by Administrator on 2016/12/19.
 */
public class MapKeyComparator implements Comparator<String> {

    @Override
    public int compare(String str1, String str2) {
        String add = str1 + str2;
        int index3 = add.indexOf("三");
        int index2 = add.indexOf("二");
        if (index3 >= 0 && index2 >= 0) {
            return str2.compareTo(str1);
        }
        return str1.compareTo(str2);
    }

    public static void main(String[] args) {
        System.out.println(new MapKeyComparator().compare(String.valueOf("初二"), String.valueOf("初三")));
        System.out.println(new MapKeyComparator().compare(String.valueOf("二"), String.valueOf("三")));
        System.out.println(new MapKeyComparator().compare(String.valueOf("五"), String.valueOf("三")));
        System.out.println(new MapKeyComparator().compare(String.valueOf("四"), String.valueOf("三")));
        System.out.println(new MapKeyComparator().compare("2", "3"));
    }
}