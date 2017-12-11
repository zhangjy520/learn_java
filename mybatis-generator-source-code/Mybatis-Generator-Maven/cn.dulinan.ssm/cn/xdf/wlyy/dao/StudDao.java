/**
 * 
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2016年09月06日 13:54:24
 * <p>2016-3-21
 * Company: 北京新东方学校
 * <p>
 * Author: dulinan@xdf.cn
 * <p>
 * Version: 1.0
 * <p>
 */
package cn.xdf.wlyy.dao;

import cn.xdf.wlyy.domain.Stud;
import cn.xdf.wlyy.domain.StudExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudDao {
    int countByExample(StudExample example);

    int deleteByExample(StudExample example);

    int deleteByPrimaryKey(String id);

    int insert(Stud record);

    int insertSelective(Stud record);

    List<Stud> selectByExample(StudExample example);

    Stud selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Stud record, @Param("example") StudExample example);

    int updateByExample(@Param("record") Stud record, @Param("example") StudExample example);

    int updateByPrimaryKeySelective(Stud record);

    int updateByPrimaryKey(Stud record);
}