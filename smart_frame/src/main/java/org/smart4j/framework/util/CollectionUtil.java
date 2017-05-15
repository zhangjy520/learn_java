package org.smart4j.framework.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.Collection;
import java.util.Map;

public final class CollectionUtil {  
    /** 
     * ÅÐ¶ÏCollectionÊÇ·ñÎª¿Õ 
     */  
    public static boolean isEmpty(Collection<?> collection){  
        return CollectionUtils.isEmpty(collection);  
    }  
    /** 
     *ÅÐ¶ÏCollectionÊÇ·ñ·Ç¿Õ 
     */  
    public static boolean isNotEmpty(Collection<?> collection){  
        return !isEmpty(collection);  
    }  
    /** 
     * ÅÐ¶ÏMapÊÇ·ñÎª¿Õ 
     */  
    public static boolean isEmpty(Map<?,?> map){  
        return MapUtils.isEmpty(map);  
    }  
    /** 
     * ÅÐ¶ÏMapÊÇ·ñ·Ç¿Õ 
     */  
    public static boolean isNotEmpty(Map<?,?> map){  
        return !isEmpty(map);  
    }  
} 