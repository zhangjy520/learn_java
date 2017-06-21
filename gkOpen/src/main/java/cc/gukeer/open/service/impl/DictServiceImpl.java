package cc.gukeer.open.service.impl;

import cc.gukeer.common.utils.RedisUtil;
import cc.gukeer.open.persistence.dao.DictMapper;
import cc.gukeer.open.persistence.entity.Dict;
import cc.gukeer.open.persistence.entity.DictExample;
import cc.gukeer.open.service.DictService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.qiniu.util.Json;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by jon on 17-6-7.
 */
@Service
public class DictServiceImpl implements DictService {

    @Resource
    private DictMapper dictMapper;
    @Resource
    private JedisPool jedisPool;

    @Override
    public PageInfo<Dict> selectAll(int pageNum, int pageSize, String mark) {
        DictExample example = new DictExample();

        if(mark != null && !"".equals(mark)){
            example.createCriteria().andIdIsNotNull().andMarkEqualTo(mark);
        }else{
            example.createCriteria().andIdIsNotNull();
        }
        example.setOrderByClause("mark");
        PageHelper.startPage(pageNum, pageSize);
        List<Dict> list = dictMapper.selectByExample(example);
        PageInfo<Dict> page = new PageInfo(list);

        return page;
    }

    @Override
    public boolean insert(Dict dict) {
        if(dictMapper.insertSelective(dict) > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateBy(Dict dict) {
        if(dictMapper.updateByPrimaryKeySelective(dict) > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        if(dictMapper.deleteByPrimaryKey(id) > 0){
            return true;
        }
        return false;
    }

    @Override
    public Dict getDictById(String id) {
        return dictMapper.selectByPrimaryKey(id);
    }

    @Override
    public String saveDict(Dict dict) {
        Jedis jedis = jedisPool.getResource();
        Gson gson = new Gson();
        PageInfo<Dict> page = selectAll(1, 0, dict.getMark());
        String set = jedis.set(dict.getMark(), gson.toJson(page.getList()));
        RedisUtil.returnResource(jedis);
        return set;
    }

    @Override
    public Long delDict(Dict dict) {
        DictExample example = new DictExample();
        example.createCriteria().andMarkEqualTo(dict.getMark());
        List<Dict> list = dictMapper.selectByExample(example);

        Jedis jedis = jedisPool.getResource();
        Long del = 0l;
        if(list == null || list.size() < 1){
            del = jedis.del(dict.getMark());
        }else {
            Gson gson = new Gson();
            if(jedis.set(dict.getMark(), gson.toJson(list)).equals("OK")){
                del = 1l;
            }
        }
        RedisUtil.returnResource(jedis);
        return del;
    }
}
