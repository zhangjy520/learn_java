package cc.gukeer.common.service;

import cc.gukeer.common.utils.RedisUtil;
import cc.gukeer.open.persistence.dao.OpenUserMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.ServletContext;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/8.
 */
@Service
public class DictionaryService implements InitializingBean, ServletContextAware {

    @Autowired
    JedisPool jedisPool;
    @Autowired
    OpenUserMapper mapper;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        Jedis redis = jedisPool.getResource();
        try {
            RedisUtil.addObject("aaa", new ArrayList<>(), redis);
            System.out.println(RedisUtil.getObject("aaa", redis));
        } catch (Exception e) {
            e.printStackTrace();
        }
        redis.close();
    }
}
