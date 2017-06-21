package cc.gukeer.datahub.service;


import cc.gukeer.datahub.modeView.SyncView;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * Created by lx on 2017/2/24.
 */
public interface SyncService {
    PageInfo<SyncView> getSyncView();

    PageInfo<Map<String, Object>> getInitDatas(String tableName, String source, int pageNum, int pageSize);
}
