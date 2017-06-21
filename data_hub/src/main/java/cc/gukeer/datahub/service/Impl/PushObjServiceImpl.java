package cc.gukeer.datahub.service.Impl;

import cc.gukeer.common.utils.GukeerStringUtil;
import cc.gukeer.common.utils.PrimaryKey;
import cc.gukeer.common.utils.PropertiesUtil;
import cc.gukeer.datahub.modeView.QueueObjView;
import cc.gukeer.datahub.persistence.dao.*;
import cc.gukeer.datahub.persistence.entity.*;
import cc.gukeer.datahub.service.PushObjService;
import cc.gukeer.syncdata.persistence.dao.DetailObjColumnMapper;
import cc.gukeer.syncdata.persistence.dao.DetailObjMapper;
import cc.gukeer.syncdata.persistence.entity.DetailObj;
import cc.gukeer.syncdata.persistence.entity.DetailObjColumn;
import cc.gukeer.syncdata.persistence.entity.DetailObjColumnExample;
import cc.gukeer.syncdata.persistence.entity.DetailObjExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by lx on 2017/4/17.
 */
@Service
public class PushObjServiceImpl implements PushObjService {
    @Autowired
    PushObjMapper pushObjMapper;
    @Autowired
    A_PushObjMapper a_pushObjMapper;
    @Autowired
    RefPlatformAppMapper refPlatformAppMapper;
    @Autowired
    RefQueueObjMapper refQueueObjMapper;
    @Autowired
    DetailObjMapper detailObjMapper;
    @Autowired
    DetailObjColumnMapper detailObjColumnMapper;
    @Autowired
    A_DetailObjPushObjMapper a_detailObjPushObjMapper;

    @Override
    public List<PushObj> getAllPushObj() {
        PushObjExample pushObjExample = new PushObjExample();
        pushObjExample.createCriteria();
        pushObjExample.setOrderByClause("obj_table_name");

        List<PushObj> pushObjList = pushObjMapper.selectByExample(pushObjExample);
        return pushObjList;
    }

    @Override
    public PageInfo<PushObj> getAllPushObj(int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);

        PushObjExample pushObjExample = new PushObjExample();
        pushObjExample.createCriteria();
        pushObjExample.setOrderByClause("obj_table_name");

        List<PushObj> pushObjList = pushObjMapper.selectByExample(pushObjExample);
        PageInfo<PushObj> pageInfo = new PageInfo<PushObj>(pushObjList);

        return pageInfo;
    }

    @Override
    public int bindPushObj(String queueId, String[] ObjIds) {
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(ObjIds));
        for (String objId : arrayList) {
            RefQueueObj refQueueObj = new RefQueueObj();
            refQueueObj.setId(PrimaryKey.get());
            refQueueObj.setPlatAppId(queueId);
            refQueueObj.setObjId(objId);
            refQueueObjMapper.insert(refQueueObj);
        }
        return arrayList.size();
    }

    @Override
    public List<QueueObjView> getQueueObj() {
        List<QueueObjView> queueObjViews = new ArrayList<>();

        List<Map> refPlatAppList = a_detailObjPushObjMapper.selectPlatApp();
        for (Map refPlatApp : refPlatAppList) {
            QueueObjView queueObjView = new QueueObjView();
            queueObjView.setId(PrimaryKey.get());
            queueObjView.setQueueId(trans(refPlatApp.get("id")));
            queueObjView.setAppName(trans(refPlatApp.get("appName")));
            queueObjView.setPlatName(trans(refPlatApp.get("platName")));
            queueObjView.setQueueName(trans(refPlatApp.get("queues")));

            RefQueueObjExample refQueueObjExample = new RefQueueObjExample();
            refQueueObjExample.createCriteria().andPlatAppIdEqualTo(trans(refPlatApp.get("id")));
            List<RefQueueObj> refQueueObjList = refQueueObjMapper.selectByExample(refQueueObjExample);


            List<DetailObj> detailObjList = new ArrayList<>();

            List<String> objIdList = new ArrayList<>();
            objIdList.add("");

            for (RefQueueObj refQueueObj : refQueueObjList) {
                objIdList.add(refQueueObj.getObjId());
            }

            DetailObjExample example = new DetailObjExample();
            example.createCriteria().andIdIn(objIdList);
            detailObjList = detailObjMapper.selectByExample(example);

            queueObjView.setDetailObjIdList(objIdList);
            queueObjView.setDetailObj(detailObjList);

            queueObjViews.add(queueObjView);
        }
        return queueObjViews;
    }

    @Override
    public int deletePushObj(String id) {

        return pushObjMapper.deleteByPrimaryKey(id);

    }

    @Override
    public PushObj selectPushObjByKey(String id) {
        return pushObjMapper.selectByPrimaryKey(id);
    }

    @Override
    public int savePushObj(PushObj pushObj) {

        int flag = 0;
        if (StringUtil.isEmpty(pushObj.getId())) {
            pushObj.setId(PrimaryKey.get());
            flag = pushObjMapper.insertSelective(pushObj);
        } else {
            flag = pushObjMapper.updateByPrimaryKeySelective(pushObj);
        }
        return flag;
    }

    @Override
    public List<Map<String, String>> getTableName() {
        return a_pushObjMapper.getTableName();
    }

    @Override
    public PageInfo<Map<String, String>> getTableName(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, String>> list = a_pushObjMapper.getTableName();
        PageInfo<Map<String,String>> pageInfo = new PageInfo<Map<String,String>>(list);
        return pageInfo;
    }


    @Override
    public List<Map<String, String>> selectFiled(String name) {

        Properties props = PropertiesUtil.getProperties("/db.properties");
        String schema = props.getProperty("jdbc.schema");

        return a_pushObjMapper.selectFiled(name, schema);
    }

    @Override
    public void objectDelete(String id) {
        int x = detailObjMapper.deleteByPrimaryKey(id);
        DetailObjColumnExample detailObjColumnExample = new DetailObjColumnExample();
        detailObjColumnExample.createCriteria().andDetailObjIdEqualTo(id);
        int y = detailObjColumnMapper.deleteByExample(detailObjColumnExample);

    }

    @Transactional
    @Override
    public String detailObjSave(DetailObj detailObj) {

        if (StringUtil.isEmpty(detailObj.getId())) {

            String id = PrimaryKey.get();

            detailObj.setId(id);
            detailObj.setCreateDate(System.currentTimeMillis());
            detailObjMapper.insertSelective(detailObj);

            return id;
        } else {

            detailObjMapper.updateByPrimaryKeySelective(detailObj);//修改对象

            this.deleteDetailObjColumn(detailObj.getId());//对象修改后，对应的列无效，得删除

            return detailObj.getId();
        }
    }

    @Override
    public void detailObjInsert(DetailObjColumn detailObjColumn) {
        detailObjColumnMapper.insert(detailObjColumn);

    }


    @Override
    public DetailObj getDetailObjById(String id) {

        return detailObjMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<DetailObjColumn> getDetailObjColumn(String id) {
        DetailObjColumnExample detailObjColumnExample = new DetailObjColumnExample();
        detailObjColumnExample.createCriteria().andDetailObjIdEqualTo(id);
        return detailObjColumnMapper.selectByExample(detailObjColumnExample);
    }


    @Override
    public void deleteDetailObjColumn(String id) {
        DetailObjColumnExample detailObjColumnExample = new DetailObjColumnExample();
        detailObjColumnExample.createCriteria().andDetailObjIdEqualTo(id);
        detailObjColumnMapper.deleteByExample(detailObjColumnExample);
    }

    @Override
    public DetailObj selectDetailObjByName(String name) {
        DetailObjExample example = new DetailObjExample();
        example.createCriteria().andNameEqualTo(name);
        List<DetailObj> res = detailObjMapper.selectByExample(example);
        if (res.size() > 0)
            return res.get(0);
        return null;
    }

    @Override
    public List<Map<String, String>> selectResult() {
        List<Map<String, String>> result = a_detailObjPushObjMapper.selectResult();
        return result;
    }

    @Override
    public void deleteQueObj(String platAppId) {
        RefQueueObjExample refQueueObjExample = new RefQueueObjExample();
        refQueueObjExample.createCriteria().andPlatAppIdEqualTo(platAppId);
        refQueueObjMapper.deleteByExample(refQueueObjExample);
    }

    @Override
    public List<Map<String, String>> selectQueue() {
        return a_detailObjPushObjMapper.selectQueue();
    }

    public String trans(Object obj) {
        if (GukeerStringUtil.isNullOrEmpty(obj))
            return null;
        return obj.toString();
    }

}
