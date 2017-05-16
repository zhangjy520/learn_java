package cc.gukeer.open.service;


import cc.gukeer.open.persistence.entity.App;

import java.util.List;


public interface ClientService {

    public int createClient(App app);

    public int updateClient(App app);

    public void deleteClient(Long clientId);

    App findOne(Long clientId);

    List<App> findAll();

    App findByClientId(String clientId);

    App findByClientSecret(String clientId,String clientSecret);

}
