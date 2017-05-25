/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.gukeer.divideStudent.zsfb.persistence.dao;

import cn.gukeer.common.persistence.CrudDao;
import cn.gukeer.common.persistence.annotation.MyBatisDao;
import cn.gukeer.divideStudent.zsfb.persistence.entity.ZsAnalysis;
import cn.gukeer.divideStudent.zsfb.persistence.entity.ZsStudent;

import java.util.List;

/**
 * 分班管理-学生DAO接口
 *
 * @author xiangfusheng
 * @version 2016-07-11
 */
@MyBatisDao
public interface ZsStudentDao extends CrudDao<ZsStudent> {
    public void deleteInfoByTask(ZsStudent zsStudent);

    public List<ZsAnalysis> AnalysisResult(ZsStudent zsStudent);

    public List<ZsStudent> findSameSbtxh(ZsStudent zsStudent);

    public List<ZsStudent> findSbtByBj(ZsStudent zsStudent);

    public int queryClassCount(String taskId);

    public List<ZsStudent> findSameName(ZsStudent zsStudent);

    public List<ZsStudent> findNoneName(ZsStudent zsStudent);

    public List<Integer> findCurrentBj(Integer maxNum, String taskId);

    public List<ZsStudent> findSchoolList(ZsStudent zsStudent);

    public List<ZsStudent> findAddressList(ZsStudent zsStudent);

    public List<ZsAnalysis> findSchoolCount(ZsStudent zsStudent);

    public List<ZsStudent> findStudentBySchool(ZsStudent zsStudent);

    public List<ZsStudent> findStudentByAddress(ZsStudent zsStudent);

    public List<ZsStudent> findNormalStudent(ZsStudent zsStudent);

    public List<ZsStudent> findSpacialStudent(ZsStudent zsStudent);

    public List<ZsStudent> findAlreadyDivideClass(ZsStudent zsStudent);

}