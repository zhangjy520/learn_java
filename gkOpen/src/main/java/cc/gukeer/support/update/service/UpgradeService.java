package cc.gukeer.support.update.service;

import cc.gukeer.support.update.persistence.entity.Upgrade;

/**
 * Created by lx on 2016/11/22.
 */

public interface UpgradeService {
    void Save(Upgrade upgrade);
    Upgrade findbyId(Integer id);

}
