package org.smart4j.framework.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.Collection;
import java.util.Map;

public final class CollectionUtil {  
    /** 
     * �ж�Collection�Ƿ�Ϊ�� 
     */  
    public static boolean isEmpty(Collection<?> collection){  
        return CollectionUtils.isEmpty(collection);  
    }  
    /** 
     *�ж�Collection�Ƿ�ǿ� 
     */  
    public static boolean isNotEmpty(Collection<?> collection){  
        return !isEmpty(collection);  
    }  
    /** 
     * �ж�Map�Ƿ�Ϊ�� 
     */  
    public static boolean isEmpty(Map<?,?> map){  
        return MapUtils.isEmpty(map);  
    }  
    /** 
     * �ж�Map�Ƿ�ǿ� 
     */  
    public static boolean isNotEmpty(Map<?,?> map){  
        return !isEmpty(map);  
    }  
} 