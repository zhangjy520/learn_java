package cc.gukeer.open.persistence.dao;

import cc.gukeer.open.modelView.AppBaseInfoView;

import java.util.List;

/**
 * Created by LL on 2017/1/7.
 */
public interface A_AppExtentionMapper {
   List<AppBaseInfoView> findAppBaseInfo(Integer status);

   List<AppBaseInfoView> findAppByPushStatus(Integer pushStatus);

    List<AppBaseInfoView> findAppByOptAndAppStatus();
}
