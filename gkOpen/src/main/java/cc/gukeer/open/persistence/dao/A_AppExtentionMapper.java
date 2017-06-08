package cc.gukeer.open.persistence.dao;

import cc.gukeer.open.modelView.AppBaseInfoView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by LL on 2017/1/7.
 */
public interface A_AppExtentionMapper {
   List<AppBaseInfoView> findAppBaseInfo(@Param("status") Integer status,@Param("del") Integer del);

   List<AppBaseInfoView> findAppByPushStatus(Integer pushStatus);

   List<AppBaseInfoView> findAppBaseInfoContainDel();
}
