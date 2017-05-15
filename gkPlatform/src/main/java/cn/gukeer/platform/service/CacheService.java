package cn.gukeer.platform.service;

import cn.gukeer.platform.persistence.entity.User;

public interface CacheService {

    public void addCache(String key, Object value);

    public Object getCacheByKey(String key);

    public  void removeCache(String key);


}
