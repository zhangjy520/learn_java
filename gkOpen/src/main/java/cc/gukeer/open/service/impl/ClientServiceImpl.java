package cc.gukeer.open.service.impl;

import cc.gukeer.open.persistence.dao.AppMapper;
import cc.gukeer.open.persistence.dao.ClientMapper;
import cc.gukeer.open.persistence.entity.App;
import cc.gukeer.open.persistence.entity.AppExample;
import cc.gukeer.open.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientMapper clientMapper;

    @Autowired
    AppMapper appMapper;

    public int createClient(App app) {
        app.setClientId(UUID.randomUUID().toString());
        app.setClientSecret(UUID.randomUUID().toString());
        return appMapper.insertSelective(app);
    }

    public int updateClient(App app) {
        return appMapper.updateByPrimaryKey(app);
    }

    public void deleteClient(Long clientId) {
        AppExample example = new AppExample();
        example.createCriteria().andClientIdEqualTo(String.valueOf(clientId));
        appMapper.deleteByExample(example);
    }

    public App findOne(Long clientId) {
        AppExample example = new AppExample();
        example.createCriteria().andClientIdEqualTo(String.valueOf(clientId));
        List<App> clientList = appMapper.selectByExample(example);
        if (clientList.size() > 0) {
            return clientList.get(0);
        } else {
            return null;
        }
    }

    public List<App> findAll() {
        AppExample example = new AppExample();
        example.createCriteria();
        return appMapper.selectByExample(example);
    }

    public App findByClientId(String clientId) {
        AppExample example = new AppExample();
        example.createCriteria().andClientIdEqualTo(clientId);
        List<App> client = appMapper.selectByExample(example);
        return client.size() > 0 ? client.get(0) : null;
    }

    public App findByClientSecret(String clientId, String clientSecret) {
        AppExample clientExample = new AppExample();
        clientExample.createCriteria().andClientSecretEqualTo(clientSecret).andClientIdEqualTo(clientId);
        List<App> client = appMapper.selectByExample(clientExample);
        return client.size() > 0 ? client.get(0) : null;
    }
}
