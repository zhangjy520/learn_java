package cc.gukeer.datahub.service;


import cc.gukeer.datahub.modeView.SyncView;
import com.github.pagehelper.PageInfo;

/**
 * Created by lx on 2017/2/24.
 */
public interface SyncService {
    PageInfo<SyncView> getSyncView();
}
