package cc.gukeer.datahub.modeView;

import cc.gukeer.syncdata.persistence.entity.DetailObj;

import java.util.List;

/**
 * Created by lx on 2017/4/17.
 */
public class QueueObjView {
    private String id;
    private String appName;
    private String platName;
    private String queueName;
    private String pushObjId;
    private String queueId;
    private List<String> detailObjIdList;

    public List getDetailObjIdList() {
        return detailObjIdList;
    }

    public void setDetailObjIdList(List<String> detailObjIdList) {
        this.detailObjIdList = detailObjIdList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPushObjId() {
        return pushObjId;
    }

    public void setPushObjId(String pushObjId) {
        this.pushObjId = pushObjId;
    }

    public String getQueueId() {
        return queueId;
    }

    public void setQueueId(String queueId) {
        this.queueId = queueId;
    }

    private List<DetailObj> detailObj;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPlatName() {
        return platName;
    }

    public void setPlatName(String platName) {
        this.platName = platName;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public List<DetailObj> getDetailObj() {
        return detailObj;
    }

    public void setDetailObj(List<DetailObj> detailObj) {
        this.detailObj = detailObj;
    }
}
