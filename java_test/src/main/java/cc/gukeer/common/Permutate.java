package cc.gukeer.common;

import java.util.ArrayList;
import java.util.List;

public class Permutate {
    public static int total = 0;

    private static List<String> list = new ArrayList<>();

    public static void swap(String[] str, int i, int j) {
        String temp = new String();
        temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

    public static void arrange(String[] str, int st, int len) {
        if (st == len - 1) {
            for (int i = 0; i < len; i++) {
                System.out.print(str[i]);
            }
            System.out.println();
            total++;
        } else {
            for (int i = st; i < len; i++) {
                swap(str, st, i);
                arrange(str, st + 1, len);  //交换后，再进行全排列算法
                swap(str, st, i);
            }
        }
    }


    //private static int count = 1;
    public static List add(List list, Object o) {
        if (list.contains(o)) {
            return list;
        } else {
            list.add(o);
            return list;
        }
    }

    public static void main(String[] args) {
        String str[] = {"a", "b","c","d"};
        arrange(str, 0, str.length);
    }


    public static void generatePass(String[] arg, int cen, String param) {

    /*    if (cen!= 4) {
            for (int h = 0; h < arg.length; h++) {
                 param += arg[h];
                add(list, param);
            }
            cen++;
            generatePass(arg,cen,param);
        }else {
            return;
        }*/


        if (cen != 1) {
            for (int a = 0; a < arg.length; a++) {
                add(list, arg[a]);
                if (cen != 2) {
                    for (int b = 0; b < arg.length; b++) {
                        add(list, arg[a] + arg[b]);
                        if (cen != 3) {
                            for (int c = 0; c < arg.length; c++) {
                                add(list, arg[a] + arg[b] + arg[c]);
                                if (cen != 4) {
                                    for (int d = 0; d < arg.length; d++) {
                                        add(list, arg[a] + arg[b] + arg[c] + arg[d]);
                                        if (cen != 5) {
                                            for (int e = 0; e < arg.length; e++) {
                                                add(list, arg[a] + arg[b] + arg[c] + arg[d] + arg[e]);
                                                if (cen != 6) {
                                                    for (int f = 0; f < arg.length; f++) {
                                                        add(list, arg[a] + arg[b] + arg[c] + arg[d] + arg[e] + arg[f]);
                                                        if (cen != 7) {
                                                            for (int g = 0; g < arg.length; g++) {
                                                                add(list, arg[a] + arg[b] + arg[c] + arg[d] + arg[e] + arg[f] + arg[g]);
                                                                if (cen != 8) {
                                                                    for (int h = 0; h < arg.length; h++) {
                                                                        add(list, arg[a] + arg[b] + arg[c] + arg[d] + arg[e] + arg[f] + arg[g] + arg[h]);
                                                                        if (cen != 8) {
                                                                            for (int i = 0; i < arg.length; i++) {
                                                                                add(list, arg[a] + arg[b] + arg[c] + arg[d] + arg[e] + arg[f] + arg[g] + arg[h] + arg[i]);
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        /*list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        });*/

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println(list.size());
    }


}