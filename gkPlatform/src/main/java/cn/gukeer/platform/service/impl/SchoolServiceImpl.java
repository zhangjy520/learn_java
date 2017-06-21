package cn.gukeer.platform.service.impl;

import cn.gukeer.common.service.BasicService;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.platform.modelView.AreaSchoolView;
import cn.gukeer.platform.persistence.dao.*;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.SchoolService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by conn on 2016/8/6.
 */
@Service
public class SchoolServiceImpl extends BasicService implements SchoolService {

    @Autowired
    SchoolMapper schoolMapper;
    @Autowired
    A_SchoolExtensionMapper schoolExtensionMapper;

    @Autowired
    GradeClassMapper classMapper;

    @Autowired
    ClassSectionMapper sectionMapper;

    @Autowired
    SchoolTypeMapper schoolTypeMapper;

    @Autowired
    SchoolAppMapper schoolAppMapper;

    @Autowired
    LogMapper logMapper;

    @Autowired
    ConfigMapper configMapper;

    @Override
    public PageInfo<School> selectAllList(int pageNum, int pageSize) {

        SchoolExample example = new SchoolExample();
        example.createCriteria().andDelFlagEqualTo(0);
        example.setOrderByClause("create_date desc");

        List<School> list = schoolMapper.selectByExample(example);
        PageInfo<School> pageInfo = new PageInfo<School>(list);

        return pageInfo;
    }

    @Override
    public List<School> selectAllList() {

        SchoolExample example = new SchoolExample();
        example.createCriteria().andDelFlagEqualTo(0);
        example.setOrderByClause("create_date desc");

        List<School> list = schoolMapper.selectByExample(example);
        return list;
    }

    @Override
    public School selectSchoolById(String id) {
        School school = schoolMapper.selectByPrimaryKey(id);
        return school;
    }

    @Override
    public int delete(String id) {
        SchoolExample example = new SchoolExample();
        example.createCriteria().andIdEqualTo(id);
        School s = new School();
        s.setDelFlag(1);
        int num = schoolMapper.updateByExampleSelective(s, example);
        return num;
    }

    @Override
    public int saveSchoolSettingInfo(School school) {

        int count = 0;
        if (null == school.getId() || school.getId() == "") {
            count = schoolMapper.insertSelective(school);
        } else {
            SchoolExample example = new SchoolExample();
            example.createCriteria().andIdEqualTo(school.getId());
            count = schoolMapper.updateByExampleSelective(school, example);
        }
        return count;
    }

    @Override
    public int saveSchoolType(SchoolType schoolType) {

        int count = 0;
        if (null == schoolType.getId() || schoolType.getId() == "") {
            schoolType.setId(PrimaryKey.get());
            count = schoolTypeMapper.insertSelective(schoolType);
        } else {
            SchoolTypeExample example = new SchoolTypeExample();
            example.createCriteria().andIdEqualTo(schoolType.getId());
            count = schoolTypeMapper.updateByExampleSelective(schoolType, example);
        }
        return count;
    }

    @Override
    @CacheEvict(value = "schoolInfo", key = "#url")
    public int saveAndClearSchoolcache(School school, String url) {
        if (null == school.getId() || school.getId() == "") {
            return schoolMapper.insertSelective(school);
        } else {
            SchoolExample example = new SchoolExample();
            example.createCriteria().andIdEqualTo(school.getId());
            return schoolMapper.updateByExampleSelective(school, example);
        }
    }

    @Override
    public int saveSchoolBackId(School school) {
        return schoolExtensionMapper.insertSchoolBackId(school);
    }

    @Override
    public SchoolType selectSchoolTypeById(String id) {
        SchoolType schoolType = schoolTypeMapper.selectByPrimaryKey(id);
        return schoolType;
    }

    @Override
    public List<GradeClass> selectGradeClassBySchoolId(String schoolId) {

        GradeClassExample example = new GradeClassExample();
        example.createCriteria().andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0);

        List<GradeClass> list = classMapper.selectByExample(example);

        return list;
    }

    @Override
    public List<SchoolType> selectSchoolTypeBySchoolId(String schoolId) {

        SchoolTypeExample example = new SchoolTypeExample();
        example.setOrderByClause("sort");
        example.createCriteria().andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0);

        List<SchoolType> list = schoolTypeMapper.selectByExample(example);

        return list;
    }

    @Override
    public List<SchoolApp> findSchoolAppById(String schoolId, String _id) {
        SchoolAppExample schoolAppExample = new SchoolAppExample();
        schoolAppExample.createCriteria().andSchoolIdEqualTo(schoolId).andAppIdEqualTo(_id);
        List<SchoolApp> shcoolAppLsit = schoolAppMapper.selectByExample(schoolAppExample);
        return shcoolAppLsit;
    }

    @Override
    public List<Map<String, Object>> selectTeacherByParam(Map<Object, Object> param) {
        return schoolExtensionMapper.selectSchoolViewById(param);
    }

    @Override
    @Cacheable(value = "schoolInfo", key = "#schoolUrl")
    public School selectSchoolByWholeUrl(String schoolUrl) {
        if (schoolUrl != "") {
            SchoolExample schoolExample = new SchoolExample();
            schoolExample.createCriteria().andDeployUrlEqualTo(schoolUrl).andDelFlagEqualTo(0);
            List<School> schoolList = schoolMapper.selectByExample(schoolExample);
            if (schoolList.size() == 1) {
                return schoolList.get(0);
            } else {
                return null;
            }
        } else return null;
    }

    @Override
    public PageInfo<LogWithBLOBs> selectLog(Integer pageNum, Integer pageSize) {
        LogExample example = new LogExample();
        example.createCriteria();
        example.setOrderByClause("create_date Desc");
        PageHelper.startPage(pageNum, pageSize);


        Page<LogWithBLOBs> page = (Page<LogWithBLOBs>) logMapper.selectByExampleWithBLOBs(example);
        PageInfo<LogWithBLOBs> pageInfo = new PageInfo<LogWithBLOBs>(page);

        return pageInfo;
    }

    @Override
    public LogWithBLOBs selectLogById(String id) {
        return logMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Config> selectConfig(Integer pageNum, Integer pageSize) {
        ConfigExample example = new ConfigExample();
        example.createCriteria().andDelFlagEqualTo(0);
        example.setOrderByClause("param_type");
        PageHelper.startPage(pageNum, pageSize);
        Page<Config> page = (Page<Config>) configMapper.selectByExample(example);
        PageInfo<Config> pageInfo = new PageInfo<Config>(page);
        return pageInfo;
    }

    @Override
    public Config selectConfigById(String id) {
        return configMapper.selectByPrimaryKey(id);
    }

    @Override
    public void saveConfig(Config config) {
        if (config.getId() != null && config.getId() != "") {
            configMapper.updateByPrimaryKeySelective(config);
        } else {
            configMapper.insertSelective(config);
        }
    }

    @Override
    public List<Config> selectConfigByType(String type) {
        ConfigExample configExample = new ConfigExample();
        configExample.createCriteria().andDelFlagEqualTo(0).andParamTypeEqualTo(type);
        return configMapper.selectByExample(configExample);
    }

    @Override
    public List<School> getSonSchoolList(String schoolId) {
        return schoolExtensionMapper.getSonSchoolList(schoolId);
    }

    @Override
    public List<AreaSchoolView> getSchoolObjectTreeMenu(String schoolId,Object o) {
        List<School> schoolList = getSonSchoolList(schoolId);

        List<Map> list = new ArrayList<>();
        if (o instanceof Department){
            list = schoolExtensionMapper.getSonSchoolDepartment(schoolList);
        }else if (o instanceof Title){
            list = schoolExtensionMapper.getSonSchoolTitle(schoolList);
        }
        return formatList(list);
    }

    public List<AreaSchoolView> formatList(List<Map> listParam) {
        Map<Object, List> schoolMap = new HashMap<Object, List>();
        for (Map depart : listParam) {
            if (schoolMap.containsKey(depart.get("schoolId"))) {
                schoolMap.get(depart.get("schoolId")).add(depart);
            } else {
                List<Map> list = new ArrayList<>();
                list.add(depart);
                schoolMap.put(depart.get("schoolId"), list);
            }
        }
        Set set = schoolMap.keySet();
        List<AreaSchoolView> res = new ArrayList<>();
        for (Object key : set) {
            List<Map> list = schoolMap.get(key);
            if (list.size() == 0)
                continue;
            AreaSchoolView view = new AreaSchoolView();
            Map list0 = list.get(0);
            view.setId(list0.get("schoolId").toString());
            view.setName(list0.get("schoolName").toString());
            view.setPid(list0.get("schoolPar").toString());
            view.setList(list);
            res.add(view);
        }
        return res;
    }
}
