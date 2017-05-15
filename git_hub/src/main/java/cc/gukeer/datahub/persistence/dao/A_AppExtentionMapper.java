package cc.gukeer.datahub.persistence.dao;



import cc.gukeer.datahub.modeView.AppBaseInfoView;

import java.util.List;

/**
 * Created by LL on 2017/1/7.
 */
public interface A_AppExtentionMapper {
   List<AppBaseInfoView> findAppBaseInfo(Integer status);

   List<AppBaseInfoView> findAppByPushStatus(Integer pushStatus);

    List<AppBaseInfoView> findAppByOptAndAppStatus();
}
