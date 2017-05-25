package cn.gukeer.common.utils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by CC on 2016/8/5 0005.
 */
public class FigureToLetterUtils {
    private static char[] letter = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    /**
     * 单个数字转字母
     *
     * @param a
     * @return
     */
    public static String toLetter(int a) {
        StringBuilder sb = new StringBuilder();
        if (a > 0 && a < 50) {
            if (a >= 26) {
                sb.append(letter[a / 26 - 1]);
                if (a % 26 == 0) {
                    sb.append(letter[a % 26]);
                } else {
                    sb.append(letter[a % 26 - 1]);
                }
            } else {//26以内,直接取a-1的值
                sb.append(letter[a - 1]);
            }
        }
        return sb.toString();
    }

    /**
     * 数字转字母List
     *
     * @param classNum
     * @return
     */
    public static List<String> toLetterList(Integer classNum) {
        List<String> result = new LinkedList<String>();
        for (int i = 1; i <= classNum; i++) {
            result.add(toLetter(i));
        }
        return result;
    }
}
