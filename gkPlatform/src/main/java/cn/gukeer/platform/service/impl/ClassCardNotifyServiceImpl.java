package cn.gukeer.platform.service.impl;

import cn.gukeer.platform.modelView.ClassCardNotifyView;
import cn.gukeer.platform.persistence.dao.A_ClassCardNotifyMapper;
import cn.gukeer.platform.persistence.dao.ClassCardNotifyMapper;
import cn.gukeer.platform.persistence.entity.ClassCardNotify;
import cn.gukeer.platform.persistence.entity.ClassCardNotifyExample;
import cn.gukeer.platform.service.ClassCardNotifyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by alpha on 17-7-5.
 */
@Service
public class ClassCardNotifyServiceImpl implements ClassCardNotifyService {

    @Autowired
    ClassCardNotifyMapper classCardNotifyMapper;

    @Autowired
    A_ClassCardNotifyMapper a_classCardNotifyMapper;

    @Override
    public int insertClassCardNotify(ClassCardNotify classCardNotify) {
        int count = classCardNotifyMapper.insertSelective(classCardNotify);
        return count;
    }

    @Override
    public PageInfo<ClassCardNotifyView> findAllNotify(String title, int type, int pageNum, int pageSize) {
        if (pageSize != -1) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<ClassCardNotifyView> classCardNotifyViews = a_classCardNotifyMapper.findAllNotifyView(title, type);
        PageInfo<ClassCardNotifyView> pageInfo = new PageInfo<>(classCardNotifyViews);
        return pageInfo;
    }

    @Override
    public List<ClassCardNotifyView> transforNotifyView(List<ClassCardNotifyView> classCardNotifyViews) {
        List<ClassCardNotifyView> resultList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
        if (classCardNotifyViews != null && classCardNotifyViews.size() > 0) {
            for (ClassCardNotifyView cv : classCardNotifyViews) {
                Date date = new Date(cv.getCreateDate());
                cv.setPublishTime(sdf.format(date));
                resultList.add(cv);
            }
        }
        return resultList;
    }

    @Override
    public ClassCardNotify findById(String id) {
        ClassCardNotify classCardNotify = classCardNotifyMapper.selectByPrimaryKey(id);
        return classCardNotify;
    }

    @Override
    public int deleteClassCardNotify(String id[]) {
        ClassCardNotifyExample example =new ClassCardNotifyExample();
        example.createCriteria().andIdIn(Arrays.asList(id));
        int count=classCardNotifyMapper.deleteByExample(example);
        return count;
    }
}
