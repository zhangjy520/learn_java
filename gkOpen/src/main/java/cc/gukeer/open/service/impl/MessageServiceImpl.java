package cc.gukeer.open.service.impl;

import cc.gukeer.open.persistence.dao.OpenMessageMapper;
import cc.gukeer.open.persistence.entity.OpenMessage;
import cc.gukeer.open.persistence.entity.OpenMessageExample;
import cc.gukeer.open.persistence.entity.OpenUser;
import cc.gukeer.open.service.MessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LL on 2017/1/12.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    OpenMessageMapper openMessageMapper;

    public PageInfo<OpenMessage> findInfoByOpenUser(int pageNum, int pageSize, OpenUser openUser) {
        String openUserId = openUser.getId();
        OpenMessageExample openMessageExample = new OpenMessageExample();
        openMessageExample.setOrderByClause("create_date desc");
            PageHelper.startPage(pageNum,pageSize);
            openMessageExample.createCriteria().andUserIdEqualTo(openUserId);
            List<OpenMessage> list = openMessageMapper.selectByExample(openMessageExample);
        PageInfo<OpenMessage> messagePageInfo = new PageInfo<>(list);
        return messagePageInfo;
    }
    public OpenMessage findByMessageId(String id) {
        OpenMessageExample openMessageExample = new OpenMessageExample();
        openMessageExample.createCriteria().andIdEqualTo(id);
        List<OpenMessage> openMessageList = openMessageMapper.selectByExample(openMessageExample);
        if (openMessageList != null) {
            return openMessageList.get(0);
        }
        return null;
    }

    @Override
    public OpenMessage findByRefId(String id, Integer message_type) {
        OpenMessageExample openMessageExample = new OpenMessageExample();
        OpenMessageExample.Criteria criteria = openMessageExample.createCriteria();
        if (message_type == 0){
            criteria.andUserIdEqualTo(id);
        }else {
            criteria.andAppIdEqualTo(id);
        }
        criteria.andMessageTypeEqualTo(message_type);
        List<OpenMessage> openMessageList = openMessageMapper.selectByExample(openMessageExample);
        if (!openMessageList.isEmpty()) {
            OpenMessage openMessage = openMessageList.get(0);
            return openMessage;
        } else {
            return null;
        }
    }

    @Override
    public int updateByPrimarykey(OpenMessage openMessage) {
        OpenMessageExample openMessageExample = new OpenMessageExample();
        openMessageExample.createCriteria().andIdEqualTo(openMessage.getId());
        int readed = openMessageMapper.updateByExampleSelective(openMessage,openMessageExample);
        if (readed>0){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public int deleteMessage(String userid) {
        OpenMessageExample openMessageExample = new OpenMessageExample();
        openMessageExample.createCriteria().andUserIdEqualTo(userid).andMessageTypeEqualTo(0);
        return openMessageMapper.deleteByExample(openMessageExample);
    }

    @Override
    public OpenMessage findByMessageByUserIdAndAppName(String userId, String appName) {
        OpenMessageExample openMessageExample = new OpenMessageExample();
        openMessageExample.createCriteria().andUserIdEqualTo(userId).andAppNameEqualTo(appName);
         List<OpenMessage> list =  openMessageMapper.selectByExample(openMessageExample);
        if (list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
