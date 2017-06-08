package cc.gukeer.open.modelView;

import cc.gukeer.open.persistence.entity.OpenMessage;

/**
 * Created by LL on 2017/1/18.
 */
public class OpenMessageView  {
    private String dateFormat;

    private OpenMessage openMessage;
    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public OpenMessage getOpenMessage() {
        return openMessage;
    }

    public void setOpenMessage(OpenMessage openMessage) {
        this.openMessage = openMessage;
    }
}
