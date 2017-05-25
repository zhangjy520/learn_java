/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.gukeer.divideStudent.zsfb.service;

import cn.gukeer.common.persistence.Page;
import cn.gukeer.common.service.CrudService;
import cn.gukeer.divideStudent.zsfb.persistence.dao.ZsStudentDao;
import cn.gukeer.divideStudent.zsfb.persistence.dao.ZsTaskDao;
import cn.gukeer.divideStudent.zsfb.persistence.entity.ZsStudent;
import cn.gukeer.divideStudent.zsfb.persistence.entity.ZsTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 分班任务Service
 *
 * @author xiangfusheng
 * @version 2016-07-11
 */
@Service
@Transactional(readOnly = true)
public class ZsTaskService extends CrudService<ZsTaskDao, ZsTask> {
    @Autowired
    private ZsStudentDao studentDao;

    public ZsTask get(String id) {
        return super.get(id);
    }

    public List<ZsTask> findList(ZsTask zsTask) {
        return super.findList(zsTask);
    }

    public Page<ZsTask> findPage(Page<ZsTask> page, ZsTask zsTask) {
        return super.findPage(page, zsTask);
    }

    @Transactional(readOnly = false)
    public void save(ZsTask zsTask) {
        super.save(zsTask);
    }

    @Transactional(readOnly = false)
    public void update(ZsTask zsTask) {
        super.dao.update(zsTask);
    }

    @Transactional(readOnly = false)
    public void delete(ZsTask zsTask) {
        ZsStudent object = new ZsStudent();
        object.setTaskId(zsTask.getId());
        studentDao.deleteInfoByTask(object);
        dao.delete(zsTask);
    }
}