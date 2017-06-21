package cc.gukeer.open.service;

import cc.gukeer.open.modelView.*;
import cc.gukeer.open.persistence.entity.OpenMessage;
import cc.gukeer.open.persistence.entity.OpenUser;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OpenUserService {

	OpenUser saveOpenUser(OpenUser OpenUser);

	OpenUser getByAccountAndPwd(String account, String pwd);

	OpenUser getOpenUserById(String loginUserId);

	Boolean queryUserByUserName(String userName);

	OpenUser queryUserByUserNameEmail(String userName);

    Boolean updateOpenUser(OpenUser OpenUser);

	int updateRandom(OpenUser OpenUser);

	PageInfo<UserBaseInfoView> getUserBaseInfo(int pageNum, int pageSize);

	int updateCheckState(int checkState,String openUserId);

	PageInfo<UserBaseInfoView> getUserByCheckState(Integer checkState, int pageNum, int pageSize);

	int deleteById(String userId);

	int addMessage(String userId, OpenMessage openMessage);

	int updateOpenUserById(OpenUser openUser,String openUserId);

	OpenUser queryUserByToken(String token);
}
