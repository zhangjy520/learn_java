package cc.gukeer.support.layout.service;

import cc.gukeer.support.layout.persistence.entity.Headbar;
import cc.gukeer.support.layout.persistence.entity.Link;

/**
 * Created by lx on 2016/12/13.
 */
public interface LayoutService {
    Headbar getHeadbarByAppNameAndIsLogin(String appName,int isLogin);
    String addImgPath(String layoutList,String path,String parm);
    void updateLayout(String flag,Headbar Headbar,int isLogin);
    String updateLink(Link link,Headbar headbar);
    Link getLink(String html);
}
