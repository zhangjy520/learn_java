package cc.gukeer.smartRing.service.impl;

import cc.gukeer.common.service.BasicService;
import cc.gukeer.smartRing.modelView.MenuView;
import cc.gukeer.smartRing.modelView.RoleView;
import cc.gukeer.smartRing.persistence.dao.A_MenuExtensionMapper;
import cc.gukeer.smartRing.persistence.dao.A_RoleExtensionMapper;
import cc.gukeer.smartRing.persistence.dao.UserMapper;
import cc.gukeer.smartRing.persistence.entity.User;
import cc.gukeer.smartRing.persistence.entity.UserExample;
import cc.gukeer.smartRing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by conn on 2016/7/27.
 */
@Service
public class UserServiceImpl extends BasicService implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    A_RoleExtensionMapper roleExtensionMapper;

    @Autowired
    A_MenuExtensionMapper menuExtensionMapper;

    @Override
    public User saveUser(User user) {
        User u = null;
        if (StringUtils.isEmpty(user.getId())) {
            user.setCreateDate(System.currentTimeMillis());
            int count = userMapper.insertSelective(user);
            System.out.print("ret num ======: " + count);
            u = user;
        } else {
            userMapper.updateByPrimaryKey(user);
        }
        logger.info("save user username: {} and createTime: {}", user.getUsername(), user.getCreateDate());
        return u;
    }

    @Override
    public User getByAccountAndPwd(String username, String pwd) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(pwd);
        List<User> users = (List<User>) userMapper.selectByExample(example);

        User user = null;
        if (null != users && users.size() > 0) {
            user = users.get(0);
            logger.info("login with username: {} and createTime: {}", user.getUsername(), user.getCreateDate());
        }
        return user;
    }

    @Override
    public User getUserById(String userId) {

        User user = userMapper.selectByPrimaryKey(userId);
        return user;
    }

    @Override
    public List<RoleView> selectRoleViewByUserId(String userId) {

        List<RoleView> roleViews = roleExtensionMapper.selectRoleViewByUserId(userId);

        return roleViews;
    }

    @Override
    public List<MenuView> selectMenusByRoleId(String roleId) {

        List<MenuView> menuViews = menuExtensionMapper.selectMenusByRoleId(roleId);

        return menuViews;
    }

    @Override
    public List<User> getUsersByIds(List<String> ids) {
        UserExample example = new UserExample();
        example.createCriteria().andDelFlagEqualTo(0).andIdIn(ids);
        return userMapper.selectByExample(example);
    }

    @Override
    public User getUserByTypeAndRefId(int type, String refId) {
        UserExample example = new UserExample();
        example.createCriteria().andDelFlagEqualTo(0).andUserTypeEqualTo(type).andRefIdEqualTo(refId);
        List<User> users = userMapper.selectByExample(example);
        if (users.size() > 0) {
            return users.get(0);
        } else return null;
    }

    @Override
    public Map getUserInfo(String userId) {

        Map map = menuExtensionMapper.getUserInfo(userId);

        return map;
    }
}
