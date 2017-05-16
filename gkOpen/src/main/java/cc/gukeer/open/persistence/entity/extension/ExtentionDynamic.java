package cc.gukeer.open.persistence.entity.extension;

import cc.gukeer.open.persistence.entity.Dynamic;

import java.util.Date;

/**
 * Created by LL on 2017/2/17.
 */
public class ExtentionDynamic extends Dynamic {
    private Dynamic dynamic;

    public Dynamic getDynamic() {
        return dynamic;
    }

    public void setDynamic(Dynamic dynamic) {
        this.dynamic = dynamic;
    }

    private String releaseTimeExt;

    public String getReleaseTimeExt() {
        return releaseTimeExt;
    }

    public void setReleaseTimeExt(String releaseTimeExt) {
        this.releaseTimeExt = releaseTimeExt;
    }
}



