package cc.gukeer.smartRing.syncdata.beanViews;

import cc.gukeer.smartRing.persistence.entity.TeacherClass;

/**
 * Created by Administrator on 2017/2/21.
 */
public class TeacherClassView extends TeacherClass {
    private String syncTeacherId;
    private String syncClassId;

    public String getSyncTeacherId() {
        return syncTeacherId;
    }

    public void setSyncTeacherId(String syncTeacherId) {
        this.syncTeacherId = syncTeacherId;
    }

    public String getSyncClassId() {
        return syncClassId;
    }

    public void setSyncClassId(String syncClassId) {
        this.syncClassId = syncClassId;
    }
}
