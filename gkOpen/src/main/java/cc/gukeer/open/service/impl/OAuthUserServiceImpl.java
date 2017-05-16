package cc.gukeer.open.service.impl;

import cc.gukeer.common.service.BasicService;
import cc.gukeer.open.persistence.dao.ChangeStateUserMapper;
import cc.gukeer.open.persistence.entity.ChangeStateUser;
import cc.gukeer.open.persistence.entity.ChangeStateUserExample;
import cc.gukeer.open.service.OAuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class OAuthUserServiceImpl extends BasicService implements OAuthUserService {

    @Autowired
    ChangeStateUserMapper sysUserMapper;

    @Override
    public ChangeStateUser getByAccountAndPwd(String account, String pwd) {
        ChangeStateUserExample example = new ChangeStateUserExample();
        example.createCriteria().andUsernameEqualTo(account).andPasswordEqualTo(pwd);
        List<ChangeStateUser> users = sysUserMapper.selectByExample(example);

        ChangeStateUser user = null;
        if (null != users && users.size() > 0) {
            user = users.get(0);
            logger.info("login with username: {} and createTime: {}", user.getUsername(),System.currentTimeMillis());
        }
        return user;
    }
}
