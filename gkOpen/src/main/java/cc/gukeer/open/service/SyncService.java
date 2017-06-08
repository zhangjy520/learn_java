package cc.gukeer.open.service;

import cc.gukeer.open.modelView.SyncView;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by lx on 2017/2/24.
 */
public interface SyncService {
    PageInfo<SyncView> getSyncView();
}
