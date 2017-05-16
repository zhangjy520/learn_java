package cc.gukeer.common.utils;

import cc.gukeer.smartRing.persistence.entity.StuScoreRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 通用对象copy工具类
 *
 * @author
 * @date 2015年1月9日 上午10:50:32
 * @version 1.0
 */
public class ObjectCopyUtil {

    private static final Logger logger = LoggerFactory.getLogger(ObjectCopyUtil.class);

    /**
     * 拷贝对象方法（适合同一类型的对象复制，但结果需强制转换）
     *
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static Object copy(Object objSource) throws InstantiationException, IllegalAccessException{

        if(null == objSource) return null;//如果源对象为空，则直接返回null

        // 获取源对象类型
        Class<?> clazz = objSource.getClass();
        // 获取源对象构造函数
//      Constructor<?> construtctor = clazz.getConstructor();
        // 实例化出目标对象
//      Object objDes = construtctor.newInstance();
        Object objDes = clazz.newInstance();

        // 获得源对象所有属性
        Field[] fields = clazz.getDeclaredFields();

        // 循环遍历字段，获取字段对应的属性值
        for ( Field field : fields )
        {
            // 如果不为空，设置可见性，然后返回
            field.setAccessible( true );

            try
            {
                if (field.toGenericString().contains("final")){
                    continue;
                }
                // 设置字段可见，即可用get方法获取属性值。
                field.set(objDes, field.get(objSource));
            }
            catch ( Exception e )
            {
                logger.error("执行{}类的{}属性的set方法时出错。{}",clazz.getSimpleName(),field.getName(),e);
            }
        }
        return objDes;
    }


    /**
     * 拷贝对象方法（适合同一类型的对象复制）
     *
     * @param objSource 源对象
     * @param clazz 目标类
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T> T copy(Object objSource,Class<T> clazz) throws InstantiationException, IllegalAccessException{

        if(null == objSource) return null;//如果源对象为空，则直接返回null

        T objDes = clazz.newInstance();

        // 获得源对象所有属性
        Field[] fields = clazz.getDeclaredFields();

        // 循环遍历字段，获取字段对应的属性值
        for ( Field field : fields )
        {
            // 如果不为空，设置可见性，然后返回
            field.setAccessible( true );

            try
            {
                // 设置字段可见，即可用get方法获取属性值。
                field.set(objDes, field.get(objSource));

            }
            catch ( Exception e )
            {
                logger.error("执行{}类的{}属性的set方法时出错。{}",clazz.getSimpleName(),field.getName(),e);
            }
        }
        return objDes;
    }

    /**
     * 拷贝对象方法（适合不同类型的转换）<br/>
     * 前提是，源类中的所有属性在目标类中都存在
     *
     * @param objSource 源对象
     * @param clazzSrc 源对象所属class
     * @param clazzDes 目标class
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T, K> T copy(K objSource,Class<K> clazzSrc,Class<T> clazzDes) throws InstantiationException, IllegalAccessException{

        if(null == objSource) return null;//如果源对象为空，则直接返回null

        T objDes = clazzDes.newInstance();

        // 获得源对象所有属性
        Field[] fields = clazzSrc.getDeclaredFields();

        // 循环遍历字段，获取字段对应的属性值
        for ( Field field : fields )
        {
            // 如果不为空，设置可见性，然后返回
            field.setAccessible( true );

            try
            {
                String fieldName = field.getName();// 属性名
                String firstLetter = fieldName.substring(0, 1).toUpperCase();// 获取属性首字母

                // 拼接set方法名
                String setMethodName = "set" + firstLetter + fieldName.substring(1);
                // 获取set方法对象
                Method setMethod = clazzDes.getMethod(setMethodName,new Class[]{field.getType()});
                // 对目标对象调用set方法装入属性值
                setMethod.invoke(objDes, field.get(objSource));
            }
            catch ( Exception e )
            {
                logger.error("执行{}类的{}属性的set方法时出错。{}",clazzDes.getSimpleName(),field.getName(),e);
            }
        }
        return objDes;
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        StuScoreRef ref = new StuScoreRef();
        ref.setTestSeq("12");
        //StuScoreRef ref1 = (StuScoreRef) copy(ref);
        StuScoreRef ref1 = ref;
        ref1.setTestSeq("123");
        System.out.println(ref.getTestSeq());
        System.out.println(ref1.getTestSeq());
    }
}
