package cc.gukeer.smartRing.persistence.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by pc-daisike on 2017/3/7.
 */
public interface A_SportClassMapper {
    List<Map> selectByClassName(String schoolId);
    List<Map> selectAddClassName(String schoolId);
    List<Map<String,String>> selectMinId();
    int deleteBatch(@Param("listId") List<String> id);
    Map selectByClassId(@Param("sportClassId") String classId,@Param("schoolId") String schoolId);
    Map<String,Long> getMaxScort(String schoolId);
}
