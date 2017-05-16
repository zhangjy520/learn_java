package cc.gukeer.smartRing.persistence.dao;

import cc.gukeer.smartRing.modelView.RoleView;
import cc.gukeer.smartRing.persistence.entity.Role;
import cc.gukeer.smartRing.persistence.entity.extension.ExtensionTeacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by conn on 2016/8/19.
 */
public interface A_RoleExtensionMapper {
    List<RoleView> selectRoleViewByUserId(String userId);

    int insertRoleBackId(Role role);

    List<ExtensionTeacher> findTeacherUser(@Param("schoolId") String schoolId , @Param("search") String search);

    List<cc.gukeer.smartRing.persistence.entity.extension.RoleView> findRoleViewBySchoolId(@Param("schoolId") String schoolId);
}
