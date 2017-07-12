package cc.gukeer.sync.service.pull;

import cc.gukeer.sync.dataDefinition.EventData;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.jms.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
public class QueueMessageListener implements SessionAwareMessageListener, Serializable {

    @Override
    public void onMessage(Message msg, Session session) throws JMSException {
        try {
            TextMessage message = (TextMessage)msg;
            if(message == null){
                return;
            }
            String content = null;
            try{
                content = message.getText();
            }
            catch (JMSException e){
                return;
            }
            if (StringUtils.isEmpty(content)){
                System.out.println("消息为空，忽略！");
                return;
            }
            List<EventData<Map<String,Object>>> eventDatas = JSONObject.parseObject(content, new TypeReference<List<EventData<Map<String,Object>>>>(){});
            for(EventData<Map<String,Object>> eventData :eventDatas){
                System.out.println("数据标识："+eventData.getObjectKey());
                System.out.println("操作类型："+eventData.getEvent());
                System.out.println("数据列表："+eventData.getDataList());
                //TODO 此处实现具体的业务代码
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

