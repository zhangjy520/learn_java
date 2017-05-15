
package cn.gukeer.platform.service.impl;

import cn.gukeer.common.utils.syncdata.HttpClientUtil;
import cn.gukeer.common.utils.syncdata.LdapUtils;
import cn.gukeer.common.utils.syncdata.MD5Util;
import cn.gukeer.common.utils.syncdata.TemplateUtil;
import cn.gukeer.platform.modelView.sync.EventData;
import cn.gukeer.platform.modelView.sync.EventType;
import cn.gukeer.platform.persistence.dao.SyncUserMapper;
import cn.gukeer.platform.persistence.entity.sync.SyncUser;
import com.google.gson.Gson;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SyncPushServiceImpl {
	@Autowired
	SyncUserMapper syncUserDao;
	public static Properties prop = LdapUtils.getProperties("/syncData.properties");
	public static final String AGENT = prop.getProperty("agent");
	public static final String ACCESSKEY = prop.getProperty("accesskey");
	public static final String url = prop.getProperty("url");
	
	
	public void pushMain(){
		for (int i = 0; i < 3; i++){
			if(i==0){
				//获取插入数据
				Map<String,Object> parm = makeInsertData();
				String messageContent = (String) parm.get("messageContent");
				if(messageContent!=null){
				   String result = pushdata(messageContent);
				   if(result.equals("success")){
					  Set<Integer> idsTemp=(Set<Integer>) parm.get("ids");
					  List ids = new ArrayList();
					  ids.addAll(idsTemp);
					  syncUserDao.deleteUsered(ids);
				     }
				}
			}
			
			if(i==1){
				//获取修改数据数据
				Map<String,Object> parm = makeModifyData();
				String messageContent = (String) parm.get("messageContent");
				if(messageContent!=null){
				String result = pushdata(messageContent);
				if(result.equals("success")){
					Set<Integer> idsTemp=(Set<Integer>) parm.get("ids");
					List ids = new ArrayList();
					ids.addAll(idsTemp);
					syncUserDao.deleteUsered(ids);
				}
				}
			}
			
			if(i==2){
				//获取删除数据
				Map<String,Object> parm = makeDeleteData();
				String messageContent = (String) parm.get("messageContent");
				if(messageContent!=null){
				String result = pushdata(messageContent);
				if(result.equals("success")){
					Set<Integer> idsTemp=(Set<Integer>) parm.get("ids");
					List ids = new ArrayList();
					ids.addAll(idsTemp);
					syncUserDao.deleteUsered(ids);
				}
				}
			}
			
		}
	}
	//推送数据
	public String pushdata(String  messageContent) {
	    	String URL=url;
	    	//String messageContent = "[{\"@type\":\"com.app.event.EventData\",\"dataList\":[{\"@type\":\"org.springframework.util.LinkedCaseInsensitiveMap\",\"id\":\"9f127ec34f114e65afc7fe5423c977d9\",\"jzgno\":\"07057021\",\"NAME\":\"安宁\",\"sj\":\"13699273296\",\"yxbj\":1,\"school_id\":\"4f2f9e23eedc4a2a8a2f110a4bd00814\"}],\"event\":\"MODIFY\",\"objectKey\":\"user\"}]";
			String property = "userId:1";
			Map<String, Object> generateSignParams = new HashMap<String, Object>();
			generateSignParams.put("agent", AGENT);
			generateSignParams.put("accesskey", ACCESSKEY );
			generateSignParams.put("rand", RandomStringUtils.randomAlphanumeric(8));
			generateSignParams.put("data", messageContent);
			
			//生成签名
			String sign = MD5Util.go(TemplateUtil.freemarker("${agent}${accesskey}${rand}${data}", generateSignParams));
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("agent", generateSignParams.get("agent"));
			params.put("rand", generateSignParams.get("rand"));
			params.put("sign", sign);
			params.put("property", property);
			params.put("objectKey", "test");
			params.put("datas", messageContent);
			String result = null;
			result = HttpClientUtil.postContent(URL, "utf-8","application/x-www-form-urlencoded", params, 3000);
			if(result==null){
				String result1 = "连接失败";
				return result1;
			}
			return result;
	    }
	
	private Map<String,Object> makeInsertData(){
		List<SyncUser> userList=syncUserDao.findInsertSyncUser();
		if(userList.size()!=0){
		Map<String,Object> parm = new HashMap<String,Object>();
		Set<String> ids =new HashSet<String>();
		for (SyncUser syncUser : userList) {
			String id=syncUser.getId();
			ids.add(id);
		}
		EventData<SyncUser> eventdata =new EventData<SyncUser>();
		eventdata.setObjectKey("sys_user");
		eventdata.setEvent(EventType.INSERT);
		eventdata.setDataList(userList);
		Gson gson =new Gson();
		String messageContent=gson.toJson(eventdata, EventData.class);
		parm.put("messageContent", messageContent);
		parm.put("ids", ids);
		return parm;
		}else{
			return new HashMap();
		}
	} 
	
	private Map<String,Object> makeModifyData(){
		List<SyncUser> userList=syncUserDao.findModifySyncUser();
		if(userList.size()!=0){
		Map<String,Object> parm = new HashMap<String,Object>();
		Set<String> ids =new HashSet<String>();
		for (SyncUser syncUser : userList) {
			String id=syncUser.getId();
			ids.add(id);
		}
		EventData<SyncUser> eventdata =new EventData<SyncUser>();
		eventdata.setObjectKey("sys_user");
		eventdata.setEvent(EventType.MODIFY);
		eventdata.setDataList(userList);
		Gson gson =new Gson();
		String messageContent=gson.toJson(eventdata, EventData.class);
		parm.put("messageContent", messageContent);
		parm.put("ids", ids);
		return parm;
		}else {
			return new HashMap();
		}
	}
	
	private Map<String,Object> makeDeleteData(){
		List<SyncUser> userList=syncUserDao.findDeleteSyncUser();
		if(userList.size()!=0){
		Map<String,Object> parm = new HashMap<String,Object>();
		Set<String> ids =new HashSet<String>();
		    for (SyncUser syncUser : userList) {
			    String id=syncUser.getId();
			    ids.add(id);
		    }
		EventData<SyncUser> eventdata =new EventData<SyncUser>();
		eventdata.setObjectKey("sys_user");
		eventdata.setEvent(EventType.DELETE);
		eventdata.setDataList(userList);
		Gson gson =new Gson();
		String messageContent=gson.toJson(eventdata, EventData.class);
		parm.put("messageContent", messageContent);
		parm.put("ids", ids);
		return parm;
		}else{
			return new HashMap();
		}
		}
	}
	
		
