package cc.gukeer.smartRing.syncdata.beanViews;

import cc.gukeer.smartRing.persistence.entity.ClassSection;

/**
 * Created by Administrator on 2017/2/21.
 */
public class ClassSectionView extends ClassSection {
    private String syncId;

    public String getSyncId() {
        return syncId;
    }

    public void setSyncId(String syncId) {
        this.syncId = syncId;
    }
}
