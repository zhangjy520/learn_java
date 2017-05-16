package cc.gukeer.open.service.impl;

import cc.gukeer.common.service.BasicService;
import cc.gukeer.common.utils.PrimaryKey;
import cc.gukeer.open.modelView.UserBaseInfoView;
import cc.gukeer.open.persistence.dao.A_OpenUserExtensionMapper;
import cc.gukeer.open.persistence.dao.OpenMessageMapper;
import cc.gukeer.open.persistence.dao.OpenUserMapper;
import cc.gukeer.open.persistence.dao.PersonalMapper;
import cc.gukeer.open.persistence.entity.OpenMessage;
import cc.gukeer.open.persistence.entity.OpenUser;
import cc.gukeer.open.persistence.entity.OpenUserExample;
import cc.gukeer.open.service.OpenUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by conn on 2016/7/27.
 */
@Service
public class OpenUserServiceImpl extends BasicService implements OpenUserService {

    @Autowired
    OpenUserMapper openUserMapper;

    @Autowired
    PersonalMapper personalMapper;

    
    @Autowired
    A_OpenUserExtensionMapper a_openUserExtensionMapper;

    @Autowired
    OpenMessageMapper openMessageMapper;

    @Override
    public OpenUser saveOpenUser(OpenUser user) {
        OpenUser u = null;
        if (StringUtils.isEmpty(user.getId())) {
            user.setCreateDate(System.currentTimeMillis());

            int count = openUserMapper.insertSelective(user);
            System.out.print("ret num ======: " + count);
            u = user;
        } else {
            openUserMapper.insert(user);
        }
        logger.info("save user username: {} and createTime: {}", user.getUsername(), user.getCreateDate());
        return u;
    }


    @Override
    public OpenUser getByAccountAndPwd(String username, String pwd) {
        OpenUserExample example = new OpenUserExample();
        example.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(pwd);
        List<OpenUser> users = (List<OpenUser>) openUserMapper.selectByExample(example);

        OpenUser user = null;
        if (null != users && users.size() > 0) {
            user = users.get(0);
            logger.info("login with username: {} and createTime: {}", user.getUsername(), user.getCreateDate());
        }
        return user;
    }

    @Override
    public OpenUser getOpenUserById(String userId) {
        OpenUserExample OpenUserExample = new OpenUserExample();
        OpenUserExample.createCriteria().andIdEqualTo(userId);
        List<OpenUser> user = openUserMapper.selectByExample(OpenUserExample);
        if (user.size()!=0 ) {
            return user.get(0);
        }else {
            return null;
        }
    }

    @Override
    public Boolean queryUserByUserName(String userName) {

        OpenUserExample example = new OpenUserExample(); //1
        example.createCriteria().andUsernameEqualTo(userName);

        List<OpenUser> list = openUserMapper.selectByExample(example);
        OpenUser user = new OpenUser();
        if (list.size() > 0) {
            user = list.get(0);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public OpenUser queryUserByUserNameEmail(String userName) {
        OpenUserExample example = new OpenUserExample(); //1
        OpenUserExample.Criteria criteria = example.createCriteria();//2

        criteria.andUsernameEqualTo(userName);//3 创建查询条件

        List<OpenUser> list = openUserMapper.selectByExample(example);
        OpenUser user = null;
        if (list.size() > 0) {
            user = list.get(0);
        }
        return user;
    }

    public Boolean updateOpenUser(OpenUser OpenUser) {
        if (null != OpenUser) {
            OpenUserExample OpenUserExample = new OpenUserExample();
            OpenUserExample.Criteria criteria = OpenUserExample.createCriteria();
            criteria.andUsernameEqualTo(OpenUser.getUsername());
            try {

                int count = openUserMapper.updateByExampleSelective(OpenUser, OpenUserExample);
                if (count > 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    public int updateRandom(OpenUser OpenUser) {
        OpenUserExample OpenUserExample = new OpenUserExample();
        OpenUserExample.createCriteria().andIdEqualTo(OpenUser.getId());
        return openUserMapper.updateByExampleSelective(OpenUser, OpenUserExample);
    }
    
    @Override
    public PageInfo<UserBaseInfoView> getUserBaseInfo(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<UserBaseInfoView> userBaseInfoViewList = a_openUserExtensionMapper.findUserBaseInfo();
        PageInfo<UserBaseInfoView> pageInfo = new PageInfo<>(userBaseInfoViewList);
        return pageInfo;
    }

    @Override
    public int updateCheckState(int checkState,String openUserId) {
        OpenUser openUser = new OpenUser();
        openUser.setStatus(checkState);
        openUser.setId(openUserId);
        return openUserMapper.updateByPrimaryKeySelective(openUser);
    }

    @Override
    public PageInfo<UserBaseInfoView> getUserByCheckState(Integer checkState,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<UserBaseInfoView> userBaseInfoViewList = a_openUserExtensionMapper.findUserBaseInfoByStatus(checkState);
        PageInfo<UserBaseInfoView> pageInfo = new PageInfo<>(userBaseInfoViewList);
        return pageInfo;
    }

    @Override
    public int deleteById(String userId) {
        OpenUserExample openUserExample = new OpenUserExample();
        openUserExample.createCriteria().andIdEqualTo(userId);
        OpenUser openUser = new OpenUser();
        openUser.setId(userId);
        return openUserMapper.deleteByExample(openUserExample);
    }

    @Override
    public int addMessage(String userId, OpenMessage openMessage) {
        openMessage.setCreateDate(new Date().getTime());
        openMessage.setId(PrimaryKey.get());
        openMessage.setUserId(userId);
        return  openMessageMapper.insert(openMessage);
    }

    public int updateOpenUserById(OpenUser openUser,String openUserId) {
        OpenUserExample openUserExample = new OpenUserExample();
        openUserExample.createCriteria().andIdEqualTo(openUserId);
        int result = openUserMapper.updateByExampleSelective(openUser,openUserExample);
        if (result > 0) {
            return 1;
        }else {
            return 0;
        }
    }
}
