package cc.gukeer.smartRing.persistence.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by pc-daisike on 2017/3/6.
 */
public interface A_GradeClassMapper {
    List<Map<String,Object>> checkXdAndNj(String schoolId);

    List<Map> checkXdAndNjAndName(String schoolId);


}
