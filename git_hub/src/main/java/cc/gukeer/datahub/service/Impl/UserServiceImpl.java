package cc.gukeer.datahub.service.Impl;

import cc.gukeer.common.security.AESencryptor;
import cc.gukeer.datahub.persistence.dao.UserMapper;
import cc.gukeer.datahub.persistence.entity.User;
import cc.gukeer.datahub.persistence.entity.UserExample;
import cc.gukeer.datahub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lx on 2017/4/12.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean dologin(String username, String _password, HttpServletRequest request) {
        String password = AESencryptor.encryptCBCPKCS5Padding(_password);
        //   String password = "VAah64ZhruW/UCQQY3QJOg==";
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUsernameEqualTo(username).andPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(userExample);
        if (users == null || users.size() == 0) {
            return false;
        } else {
            request.getSession().setAttribute("loginUser", users.get(0));
            return true;
        }
    }
}
