package cn.gukeer.platform.service;

import cn.gukeer.platform.persistence.entity.TeachCycle;

/**
 * Created by LL on 2017/4/5.
 */
public interface TeachCycleSevice {
    TeachCycle findTeachCycleIdByTeachYearAndSemester(String teachYear, String semester);
}
