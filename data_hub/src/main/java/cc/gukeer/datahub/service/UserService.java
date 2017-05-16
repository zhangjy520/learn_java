package cc.gukeer.datahub.service;

import cc.gukeer.datahub.persistence.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lx on 2017/4/12.
 */
public interface UserService {
    boolean dologin(String username, String password, HttpServletRequest request);
}
