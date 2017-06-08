package cc.gukeer.open.service;

import cc.gukeer.open.persistence.entity.Dynamic;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by LL on 2017/2/17.
 */
public interface DynamicService {
    PageInfo<Dynamic> findDynamicBydelFlag(Integer pageNum, Integer pageSize);

    int insertDynamic(Dynamic dynamic);

    int delDynamicByPrimaryId(String id);

    List<Dynamic> findAllDynamic();

    PageInfo<Dynamic> findAllDynamicByPage(Integer pageNum, Integer pageSize);

    Dynamic findDynamicByPrimarykey(String id);
}
