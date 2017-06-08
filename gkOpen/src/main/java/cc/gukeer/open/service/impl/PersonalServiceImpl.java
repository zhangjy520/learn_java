package cc.gukeer.open.service.impl;

import cc.gukeer.open.persistence.dao.PersonalMapper;
import cc.gukeer.open.persistence.entity.OpenUser;
import cc.gukeer.open.persistence.entity.Personal;
import cc.gukeer.open.service.PersonalService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lx on 2016/12/29.
 */
@Service
public class PersonalServiceImpl implements PersonalService {
    @Autowired
    PersonalMapper personalMapper;

    @Override
    public Personal findPersonalbyloginUser(OpenUser OpenUser) {
        String id = OpenUser.getPersonalId();
        Personal personal = personalMapper.selectByPrimaryKey(id);
        return personal;
    }

    @Override
    public int updatePersonalByPrimaryKeySelective(Personal personal) {
        int  result = personalMapper.updateByPrimaryKeySelective(personal);
        if (result > 0) {
            return result;
        }else {
            return  0;
        }
    }

   @Override
    public PageInfo<Personal> findAllComming(int pageNum, int pageSize) {
      /*  PersonalExample appExample = new PersonalExample();
        appExample.createCriteria().andDelFlagEqualTo(0);
        appExample.setOrderByClause("id desc");
        List<App> appList = appMapper.selectByExample(appExample);
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<App> pageInfo = new PageInfo<App>(appList);*/
        return null;
    }

    public int updatePersonalByPrimaryKey(Personal personal) {
        int result = personalMapper.updateByPrimaryKey(personal);
        if (result > 0) {
            return result;
        } else {
            return 0;
        }
    }

    @Override
    public int save(Personal personal) {
        return personalMapper.insert(personal);

    }


}
