package cc.gukeer.smartRing.persistence.dao;

import cc.gukeer.smartRing.persistence.entity.ScanLog;

import java.util.List;

/**
 * Created by conn on 17-01-22.
 */
public interface A_ScanLogExtensionMapper {

    List<ScanLog> selectScanLogOrderByDate(ScanLog scanLog);

}
