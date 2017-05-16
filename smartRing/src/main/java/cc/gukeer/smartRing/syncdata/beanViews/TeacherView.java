package cc.gukeer.smartRing.syncdata.beanViews;

import cc.gukeer.smartRing.persistence.entity.Teacher;

/**
 * Created by Administrator on 2017/2/21.
 */
public class TeacherView extends Teacher {
    private String syncId;

    public String getSyncId() {
        return syncId;
    }

    public void setSyncId(String syncId) {
        this.syncId = syncId;
    }
}
