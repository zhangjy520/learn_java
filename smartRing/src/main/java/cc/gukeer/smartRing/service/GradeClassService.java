package cc.gukeer.smartRing.service;

import cc.gukeer.smartRing.persistence.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by pc-daisike on 2017/3/6.
 */
public interface GradeClassService {


    //查询课程和年级表得到name，nj，xd
    List<Map> getGradeClassName(User user);

    List<Map> getAllClass(User user);

}
