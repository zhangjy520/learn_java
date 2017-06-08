
package cc.gukeer.open.persistence.dao;
import cc.gukeer.open.modelView.PersonalAllInfoView;
import cc.gukeer.open.modelView.UserBaseInfoView;

import java.util.List;

/**
 * Created by lx on 2016/12/29.
 */
public interface A_OpenUserExtensionMapper {
    List<UserBaseInfoView> findUserBaseInfo();

    List<UserBaseInfoView> findUserBaseInfoByStatus(Integer status);
}

