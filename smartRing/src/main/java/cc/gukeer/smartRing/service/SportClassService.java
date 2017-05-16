package cc.gukeer.smartRing.service;

import cc.gukeer.smartRing.persistence.entity.SportClass;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by pc-daisike on 2017/3/6.
 */
public interface SportClassService {



    int insertSportClass(SportClass sportClass);

//    List<String> getExtraId();
    //删除ID
    int deleteBatchId(List<String> id);

    Map checkSportClassById(String classId, String schoolId);

    int deleteClass(String id,String schoolId);

    int delete(String classidid,String schoolId);

    int updateClass(String id,String schoolId,SportClass sportClass);

    PageInfo getClassCount(Map map);

    SportClass selectSportClassByName(String name);

    //获得做大的排序
    Map<String,Long> getMaxScort(String schoolId);

}
