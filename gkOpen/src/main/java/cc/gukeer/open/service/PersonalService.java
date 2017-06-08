package cc.gukeer.open.service;

import cc.gukeer.open.persistence.entity.Company;
import cc.gukeer.open.persistence.entity.OpenUser;
import cc.gukeer.open.persistence.entity.Personal;
import com.github.pagehelper.PageInfo;

/**
 * Created by lx on 2016/12/29.
 */
public interface PersonalService {
Personal findPersonalbyloginUser(OpenUser OpenUser);
    int updatePersonalByPrimaryKeySelective(Personal personal);

    PageInfo<Personal> findAllComming(int pageNum, int pageSize);
    int updatePersonalByPrimaryKey(Personal personal);
    int save(Personal personal);
}
