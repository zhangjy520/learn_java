package cc.gukeer.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangxin20 on 2017/11/16.
 */
public class WxTEst {

    public static List<String> aa = new ArrayList();

    public static void main(String[] args) {
        // String[] str=new String[]{"q","w","e","r","t","y","u","i","o","p","a","s","d","f","g","h","j","k","l","z","x","c","b","n","m","1","2","3","4","5","6","7","8","9","0","Q","W","E","R","T","Y","U","I","O","P","A","S","D","F","G","H","J","K","L","Z","X","C","V","B","N","M","@"};

        getPassword();

    }

    public static List getPassword() {
//        String[] str=new String[]{"1","2","3"};
//        String[] str = new String[]{"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "b", "n", "m", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M", "@"};
//        String[] str = new String[]{"@","Z","J","Y",".","zjy","Zjy","","z","y","j","1", "2", "3", "4","5","6","7","8","9","0","ZJY"};
        String[] str = new String[]{"1","2","3","4"};

        for (int i = 2; i <= str.length; i++) {
            combinationSelect(str, i);
        }

        while (true) {
            if (aa.size() > 0) {
                for (int i = 0; i < 100; i++) {
                    String pass = aa.get(aa.size() - 1);
                    aa.remove(aa.size() - 1);
                    Passwords p = new Passwords(pass);
                    new Thread(p).start();
                }
            }

        }
    }

    public static void combinationSelect(String[] dataList, int n) {
        combinationSelect(dataList, 0, new String[n], 0);
    }

    private static void combinationSelect(String[] dataList, int dataIndex, String[] resultList, int resultIndex) {
        int resultLen = resultList.length;
        int resultCount = resultIndex + 1;
        if (resultCount > resultLen) { // 全部选择完时，输出组合结果
            print(resultList);
            //System.out.println(Arrays.asList(resultList));
            return;
        }

        // 递归选择下一个
        for (int i = dataIndex; i < dataList.length + resultCount - resultLen; i++) {
            resultList[resultIndex] = dataList[i];
            combinationSelect(dataList, i + 1, resultList, resultIndex + 1);
        }
    }

    public static void print(String[] result) {
        //没获得一个进行一次全排序
        arrangementSelect(result, result.length);
    }

    public static void arrangementSelect(String[] dataList, int n) {
        arrangementSelect(dataList, new String[n], 0);
    }

    private static void arrangementSelect(String[] dataList, String[] resultList, int resultIndex) {
        int resultLen = resultList.length;
        if (resultIndex >= resultLen) { // 全部选择完时，输出排列结果
            StringBuilder bu = new StringBuilder();
            for (int i = 0; i < resultList.length; i++) {
                bu.append(resultList[i]);
            }
            aa.add(bu.toString());
            return;
        }

        // 递归选择下一个
        for (int i = 0; i < dataList.length; i++) {
            // 判断待选项是否存在于排列结果中
            boolean exists = false;
            for (int j = 0; j < resultIndex; j++) {
                if (dataList[i].equals(resultList[j])) {
                    exists = true;
                    break;
                }
            }
            if (!exists) { // 排列结果不存在该项，才可选择
                resultList[resultIndex] = dataList[i];
                arrangementSelect(dataList, resultList, resultIndex + 1);
            }
        }
    }
}
