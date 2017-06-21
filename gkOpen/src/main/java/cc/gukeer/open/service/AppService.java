package cc.gukeer.open.service;

import cc.gukeer.open.modelView.AppAllInfoView;
import cc.gukeer.open.modelView.AppBaseInfoView;
import cc.gukeer.open.persistence.entity.App;
import cc.gukeer.open.persistence.entity.OpenUser;
import com.github.pagehelper.PageInfo;


/**
 * Created by lx on 2016/11/25.
 */
public interface AppService  {
    //保存app
    int save(App app,String arrsrc,String arrindex);
    //获取审核页面的数据
    //根据主键查询app
    App getAppById(String appId);

    PageInfo<App> findAppByDetail(int pageNum, int pageSize, int status,OpenUser openUser);

    PageInfo<AppBaseInfoView> getAppBaseInfoByStatus( Integer stauts, int pageNum, int pageSize,Integer del);

    AppAllInfoView getAppAllInfo(String appId);

    int deleteById(String appId);

    int updateStatus(String appId,int status);

    PageInfo<App> finaAppByOpenUser(int pageNum, int pageSize, OpenUser openUser);

    App findAppByPrimarykeyAndCheckestatus(String appId);

    int selectCount(String client_id);

    int disable(String appId);

    int enable(String appId);

    PageInfo<AppBaseInfoView> findAppBaseInfoContainDel(int appPageNum, int pageSize);

    void updateAppById(App app);
}
