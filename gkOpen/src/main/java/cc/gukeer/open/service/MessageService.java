package cc.gukeer.open.service;

import cc.gukeer.open.persistence.entity.OpenMessage;
import cc.gukeer.open.persistence.entity.OpenUser;
import com.github.pagehelper.PageInfo;

/**
 * Created by LL on 2017/1/12.
 */
public interface MessageService {

    PageInfo<OpenMessage> findInfoByOpenUser(int pageNum, int pageSize, OpenUser openUser);

    OpenMessage findByMessageId(String id);

    OpenMessage findByRefId(String id,Integer message_type);

    int updateByPrimarykey(OpenMessage openMessage);

    int deleteMessage(String userid);

    OpenMessage findByMessageByUserIdAndAppName(String userId, String appName);
}
