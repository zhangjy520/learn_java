package cc.gukeer.open.service.impl;

import cc.gukeer.common.utils.PrimaryKey;
import cc.gukeer.open.persistence.dao.DynamicMapper;
import cc.gukeer.open.persistence.entity.Dynamic;
import cc.gukeer.open.persistence.entity.DynamicExample;
import cc.gukeer.open.service.DynamicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public int save(Dynamic dynamic) {
       int succ =  0;
        if (StringUtils.isNotEmpty(dynamic.getId())){
            dynamic.setDelFlag(1);
            succ = dynamicMapper.updateByPrimaryKeySelective(dynamic);
        }else {
            dynamic.setId(PrimaryKey.get());
            dynamic.setDelFlag(0);
            dynamic.setReleaseTime(new Date().getTime());
            succ = dynamicMapper.insertSelective(dynamic);
        }
       return succ;
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
        return dynamic;
    }
}
