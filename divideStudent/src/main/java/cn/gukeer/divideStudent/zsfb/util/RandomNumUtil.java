package cn.gukeer.divideStudent.zsfb.util;

import org.apache.commons.lang.math.RandomUtils;

import java.util.Random;

public class RandomNumUtil {

    /**
     * 取范围1-n之间的不重复随机数
     *
     * @param n
     * @return
     */
    public static int[] randomArray(int n) {
        // 初始化给定范围的待选数组
        int[] source = new int[n];
        for (int i = 1; i < n + 1; i++) {
            source[i - 1] = i;
        }
        int[] result = new int[n];
        Random rd = new Random();
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            index = Math.abs(rd.nextInt() % n--);
            result[i] = source[index];
            source[index] = source[n];
        }
        return result;
    }

    /**
     * 生成1-n的随机数
     *
     * @param n
     * @return
     */
    public static int randomInt(int n) {
        int result = RandomUtils.nextInt(n) + 1;
        return result > n ? n : result;
    }

    /**
     * 生成0-n的随机数
     *
     * @param n
     * @return
     */
    public static int randomIntZeroToN(int n) {
        return RandomUtils.nextInt(n);
    }
}
