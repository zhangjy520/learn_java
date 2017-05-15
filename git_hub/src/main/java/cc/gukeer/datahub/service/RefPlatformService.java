package cc.gukeer.datahub.service;


/**
 * Created by LL on 2017/2/23.
 */
public interface RefPlatformService {

    void setName(String id, String queuesName);

    void updateSyncStatus(String id, int status);

    void updateInitData(String id, int status);
}
