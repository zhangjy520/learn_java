package cc.gukeer.open.service.impl;

import cc.gukeer.open.persistence.dao.DynamicMapper;
import cc.gukeer.open.persistence.entity.Dynamic;
import cc.gukeer.open.persistence.entity.DynamicExample;
import cc.gukeer.open.service.DynamicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LL on 2017/2/17.
 */
@Service
public class DynamicServiceImpl implements DynamicService {
    @Autowired
    DynamicMapper dynamicMapper;

    public PageInfo<Dynamic> findDynamicBydelFlag(Integer pageNum, Integer pageSize) {
        DynamicExample dynamicExample = new DynamicExample();
        dynamicExample.createCriteria().andDelFlagEqualTo(0);
        dynamicExample.setOrderByClause("release_time desc");
        PageHelper.startPage(pageNum,pageSize);
        List<Dynamic> list = dynamicMapper.selectByExample(dynamicExample);
        PageInfo<Dynamic> pageInfo = new PageInfo<>(list);
        return pageInfo;

    }

    @Override
    public int insertDynamic(Dynamic dynamic) {
       int succ =  dynamicMapper.insertSelective(dynamic);
        if (succ>0){
            return 1;
        }
        return 0;
    }

    @Override
    public int delDynamicByPrimaryId(String id) {
        DynamicExample dynamicExample = new DynamicExample();
        dynamicExample.createCriteria().andIdEqualTo(id);
        Dynamic dynamic = dynamicMapper.selectByPrimaryKey(id);
        dynamic.setDelFlag(1);
        if (dynamic != null){
            int succ = dynamicMapper.updateByPrimaryKeySelective(dynamic);
            if (succ >0){
                return 1;
            }else {
                return 0;
            }
        }
        return 0;
    }

    @Override
    public List<Dynamic> findAllDynamic() {
        DynamicExample dynamicExample = new DynamicExample();
        dynamicExample.createCriteria().andDelFlagEqualTo(0);
        dynamicExample.setOrderByClause("release_time desc");
        List<Dynamic> list = dynamicMapper.selectByExample(dynamicExample);
        if (list != null){
            return list;
        }
       return null;
    }

    @Override
    public PageInfo<Dynamic> findAllDynamicByPage(Integer pageNum, Integer pageSize) {
        DynamicExample dynamicExample = new DynamicExample();
        dynamicExample.createCriteria().andDelFlagEqualTo(0);
        dynamicExample.setOrderByClause("release_time desc");
        PageHelper.startPage(pageNum,pageSize);
        List<Dynamic> list = dynamicMapper.selectByExample(dynamicExample);
        if (list != null ){
            PageInfo<Dynamic> pageInfo = new PageInfo<>(list);
            return pageInfo;
        }
       return null;
    }

    @Override
    public Dynamic findDynamicByPrimarykey(String id) {
        DynamicExample dynamicExample = new DynamicExample();
        dynamicExample.createCriteria().andIdEqualTo(id);
        Dynamic dynamic = dynamicMapper.selectByPrimaryKey(id);
        if (dynamic !=null){
            return dynamic;
        }
        return  null;
    }
}
