package cn.gukeer.divideStudent.zsfb.service;

import cn.gukeer.common.persistence.Page;
import cn.gukeer.common.service.CrudService;
import cn.gukeer.divideStudent.zsfb.persistence.dao.ZsStudentDao;
import cn.gukeer.divideStudent.zsfb.persistence.entity.ZsAnalysis;
import cn.gukeer.divideStudent.zsfb.persistence.entity.ZsStudent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 分班管理业务层
 *
 * @author CC
 */
@Service
@Transactional(readOnly = true)
public class ZsStudentService extends CrudService<ZsStudentDao, ZsStudent> {

    public ZsStudent get(String id) {
        return super.get(id);
    }

    @Transactional(readOnly = false)
    public void save(ZsStudent zsStudent) {
        super.save(zsStudent);
    }

    @Transactional(readOnly = false)
    public void delete(ZsStudent zsStudent) {
        super.delete(zsStudent);
    }

    @Transactional(readOnly = false)
    public void update(ZsStudent zsStudent) {
        dao.update(zsStudent);
    }

    @Transactional(readOnly = false)
    public void deleteInfoByTask(ZsStudent zsStudent) {
        dao.deleteInfoByTask(zsStudent);
    }

    public List<ZsStudent> findList(ZsStudent zsStudent) {
        return super.findList(zsStudent);
    }

    public Page<ZsStudent> findPage(Page<ZsStudent> page, ZsStudent zsStudent) {
        return super.findPage(page, zsStudent);
    }

    public List<ZsStudent> findAllList(ZsStudent zsStudent) {
        return dao.findAllList(zsStudent);
    }

    public List<ZsAnalysis> AnalysisResult(ZsStudent zsStudent) {
        return dao.AnalysisResult(zsStudent);
    }

    public int queryClassCount(String taskId) {
        return dao.queryClassCount(taskId);
    }

    public List<ZsStudent> findSchoolList(ZsStudent zsStudent) {
        return dao.findSchoolList(zsStudent);
    }

    public List<ZsStudent> findAddressList(ZsStudent zsStudent) {
        return dao.findAddressList(zsStudent);
    }

    public List<ZsAnalysis> findSchoolCount(ZsStudent zsStudent) {
        return dao.findSchoolCount(zsStudent);
    }

    public List<ZsStudent> findStudentBySchool(ZsStudent zsStudent) {
        return dao.findStudentBySchool(zsStudent);
    }

    public List<ZsStudent> findStudentByAddress(ZsStudent zsStudent) {
        return dao.findStudentByAddress(zsStudent);
    }

    public List<ZsStudent> findNormalStudent(ZsStudent zsStudent) {
        return dao.findNormalStudent(zsStudent);
    }

    public List<ZsStudent> findSpacialStudent(ZsStudent zsStudent) {
        return dao.findSpacialStudent(zsStudent);
    }

    public List<ZsStudent> findAlreadyDivideClass(ZsStudent zsStudent) {
        return dao.findAlreadyDivideClass(zsStudent);
    }

}