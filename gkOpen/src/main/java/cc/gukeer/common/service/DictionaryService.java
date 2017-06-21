package cc.gukeer.common.service;

import cc.gukeer.common.utils.FileUtils;
import cc.gukeer.common.utils.RedisUtil;
import cc.gukeer.open.persistence.dao.A_DictExtentionMapper;
import cc.gukeer.open.persistence.dao.DictMapper;
import cc.gukeer.open.persistence.dao.OpenUserMapper;
import cc.gukeer.open.persistence.entity.Dict;
import cc.gukeer.open.persistence.entity.DictExample;
import com.google.gson.Gson;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Properties;

/**
 * Created by Administrator on 2017/6/8.
 */
public class DictionaryService implements InitializingBean, ServletContextAware {

    @Autowired
    JedisPool jedisPool;
    @Autowired
    OpenUserMapper mapper;

    @Resource
    DictMapper dictMapper;
    @Resource
    A_DictExtentionMapper dictExtentionMapper;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        Properties properties = FileUtils.getProperties("/db.properties");
        if ("false".equals(properties.getProperty("redis.status")))
            return;

        Jedis redis = jedisPool.getResource();
        List<Dict> marks = dictExtentionMapper.selectMarks();
        Gson gson = new Gson();
        if(marks == null && marks.size() < 1){
            return;
        }

        DictExample example;
        for (Dict d : marks){
            example = new DictExample();
            example.createCriteria().andMarkEqualTo(d.getMark());
            try {
                RedisUtil.add(d.getMark(), gson.toJson(dictMapper.selectByExample(example)), redis);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        RedisUtil.returnResource(redis);

/*        Jedis redis = jedisPool.getResource();
        try {
            RedisUtil.addObject("aaa", new ArrayList<>(), redis);
            System.out.println(RedisUtil.getObject("aaa", redis));
        } catch (Exception e) {
            e.printStackTrace();
        }
        redis.close();*/
    }
}
