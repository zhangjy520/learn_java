package cn.gukeer.divideStudent.zsfb.util;

/**
 * Created by conn on 2016/8/16.
 */
public class DividePro {
    //"male", "female", "A" "B" "C" "sqzn" "deformity" "armys" "tchild" "wjchild"
    // 男
    public static final String MALE = "male";
    // 女
    public static final String FEMALE = "female";
    // A
    public static final String A = "A";
    // B
    public static final String B = "B";
    // C
    public static final String C = "C";

    // 以下key必须保证不能存在开始相互包含，如：abc, abcd, 非法：因为abcd整体包含了abc
    // 双胞胎
    public static final String TWINS = "twins";
    // 随迁子女
    public static final String SQZN = "sqzn";
    // 随便就读
    public static final String SBJD = "deformity";
    // 重名
    public static final String CM = "cm";
    // 军属
    public static final String JS = "armys";
    // 教师子女
    public static final String JSZN = "tchild";
    // 外籍子女
    public static final String WJZN = "wjchild";
}
