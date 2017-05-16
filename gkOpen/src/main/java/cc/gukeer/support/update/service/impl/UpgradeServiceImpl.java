package cc.gukeer.support.update.service.impl;


import cc.gukeer.common.service.BasicService;
import cc.gukeer.support.update.persistence.dao.UpgradeMapper;

import cc.gukeer.support.update.persistence.entity.Upgrade;
import cc.gukeer.support.update.service.UpgradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lx on 2016/11/22.
 */
@Service
public class UpgradeServiceImpl extends BasicService implements UpgradeService {
    @Autowired
    UpgradeMapper upgradeMapper;

    @Override
    public void Save(Upgrade upgrade) {
        upgradeMapper.insert(upgrade);
    }

    @Override
    public Upgrade findbyId(Integer id) {
        Upgrade upgrade = upgradeMapper.selectByPrimaryKey(id);
        return upgrade;
    }
}
