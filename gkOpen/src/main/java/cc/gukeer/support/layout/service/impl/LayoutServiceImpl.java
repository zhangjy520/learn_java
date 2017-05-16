package cc.gukeer.support.layout.service.impl;

import cc.gukeer.support.layout.persistence.dao.HeadbarMapper;
import cc.gukeer.support.layout.persistence.entity.Headbar;
import cc.gukeer.support.layout.persistence.entity.HeadbarExample;
import cc.gukeer.support.layout.persistence.entity.Link;
import cc.gukeer.support.layout.service.LayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lx on 2016/12/13.
 */
@Service
public class LayoutServiceImpl implements LayoutService {

    @Autowired
    HeadbarMapper headbarMapper;

    @Override
    public Headbar getHeadbarByAppNameAndIsLogin(String appNmae,int isLogin) {
        HeadbarExample headbarExample =new HeadbarExample();
        headbarExample.createCriteria().andAppNameEqualTo(appNmae).andIsLoginEqualTo(isLogin);
        List<Headbar> headbar = headbarMapper.selectByExample(headbarExample);
        if (headbar!=null||headbar.size()!=0) {
            return headbar.get(0);
        }
        return null;
    }

    @Override
    public String addImgPath(String layoutString,String path,String parm) {
        int i = 0;
        StringBuffer layoutStringbuffer = new StringBuffer(layoutString);
        while (layoutStringbuffer.indexOf(parm,i)!=-1){
            int index = layoutStringbuffer.indexOf(parm,i);
            layoutStringbuffer.insert(index+"url(".toString().length(),path);
            i=index+1;
        }
        System.out.print("######"+layoutStringbuffer.toString());
        return layoutStringbuffer.toString();
    }

    @Override
    public void updateLayout(String flag, Headbar headbar,int isLogin) {
        HeadbarExample headbarExample = new HeadbarExample();
        headbarExample.createCriteria().andAppNameEqualTo(flag).andIsLoginEqualTo(isLogin);
        headbarMapper.updateByExampleSelective(headbar,headbarExample);
    }

    public  String addlink(String html, Link replace, String indexkey) {
        StringBuffer htmlStringbuffer = new StringBuffer(html);
        int add = "href=\"".length();
        int indexCksqStart = htmlStringbuffer.indexOf("href=\"");
        int indexCksqEnd = htmlStringbuffer.indexOf("\">创客社区");
        htmlStringbuffer.replace(indexCksqStart+add, indexCksqEnd,replace.getCksq());

        int indexCkmkStart = htmlStringbuffer.indexOf("href=\"",indexCksqStart+1);
        int indexCkmkEnd = htmlStringbuffer.indexOf("\">创客Mooc");
        htmlStringbuffer.replace(indexCkmkStart+add, indexCkmkEnd,replace.getCkmc());

        int indexZnbcyStart = htmlStringbuffer.indexOf("href=\"",indexCkmkStart+1);
        int indexZnbcyEnd = htmlStringbuffer.indexOf("\">智能编程云");
        htmlStringbuffer.replace(indexZnbcyStart+add, indexZnbcyEnd,replace.getZnbcy());

        int indexCkgwStart = htmlStringbuffer.indexOf("href=\"",indexZnbcyStart+1);
        int indexCkgwEnd = htmlStringbuffer.indexOf("\">创客官网");
        htmlStringbuffer.replace(indexCkgwStart+add, indexCkgwEnd,replace.getCkgw());

        int indexZxxckStart = htmlStringbuffer.indexOf("href=\"",indexCkgwStart+1);
        int indexZxxckEnd = htmlStringbuffer.indexOf("\">中小学创客教育执委会");
        htmlStringbuffer.replace(indexZxxckStart+add, indexZxxckEnd,replace.getZxxck());

        int indexYhmStart = htmlStringbuffer.indexOf("href=\"",indexZxxckStart+1);
        int indexYhmEnd = htmlStringbuffer.indexOf("\">用户名");

        int indexYhzxStart = htmlStringbuffer.indexOf("href=\"",indexYhmStart+1);
        int indexYhzxEnd = htmlStringbuffer.indexOf("\">用户中心");
        if(indexYhzxEnd!=-1) {
            htmlStringbuffer.replace(indexYhzxStart + add, indexYhzxEnd, replace.getYhzx());

            int indexXxzxStart = htmlStringbuffer.indexOf("href=\"", indexYhzxStart + 1);
            int indexXxzxEnd = htmlStringbuffer.indexOf("\">消息中心");
            htmlStringbuffer.replace(indexXxzxStart + add, indexXxzxEnd, replace.getXxzx());

            int indexWdscStart = htmlStringbuffer.indexOf("href=\"", indexXxzxStart + 1);
            int indexWdscEnd = htmlStringbuffer.indexOf("\">我的收藏");
            htmlStringbuffer.replace(indexWdscStart + add, indexWdscEnd, replace.getWdsc());

            int indexWdtxStart = htmlStringbuffer.indexOf("href=\"", indexWdscStart + 1);
            int indexWdtxEnd = htmlStringbuffer.indexOf("\">我的头衔");
            htmlStringbuffer.replace(indexWdtxStart + add, indexWdtxEnd, replace.getWdtx());
            System.out.print("######" + htmlStringbuffer.toString());
        }
        return htmlStringbuffer.toString();
    }


    @Override
    //顺序对应html参数数据
    public String updateLink(Link link,Headbar headbar) {
        String html = headbar.getHtml();
        String newHtml = addlink(html,link,"href=");
        return newHtml;
    }

    @Override
    public  Link getLink(String htmltemp) {
        Link link = new Link();
        StringBuffer html = new StringBuffer(htmltemp);
        int indexCksqStart = html.indexOf("href=\"");
        int indexCksqEnd = html.indexOf("\">创客社区");

        int indexCkmkStart = html.indexOf("href=\"",indexCksqStart+1);
        int indexCkmkEnd = html.indexOf("\">创客Mooc");

        int indexZnbcyStart = html.indexOf("href=\"",indexCkmkStart+1);
        int indexZnbcyEnd = html.indexOf("\">智能编程云");

        int indexCkgwStart = html.indexOf("href=\"",indexZnbcyStart+1);
        int indexCkgwEnd = html.indexOf("\">创客官网");

        int indexZxxckStart = html.indexOf("href=\"",indexCkgwStart+1);
        int indexZxxckEnd = html.indexOf("\">中小学创客教育执委会");

        int indexYhmStart = html.indexOf("href=\"",indexZxxckStart+1);
        int indexYhmEnd = html.indexOf("\">用户名");

        int indexYhzxStart = html.indexOf("href=\"",indexYhmStart+1);
        int indexYhzxEnd = html.indexOf("\">用户中心");

        int indexXxzxStart = html.indexOf("href=\"",indexYhzxStart+1);
        int indexXxzxEnd = html.indexOf("\">消息中心");

        int indexWdscStart = html.indexOf("href=\"",indexXxzxStart+1);
        int indexWdscEnd = html.indexOf("\">我的收藏");

        int indexWdtxStart = html.indexOf("href=\"",indexWdscStart+1);
        int indexWdtxEnd = html.indexOf("\">我的头衔");

        int add = "href=\"".length();
        link.setCksq(html.substring(indexCksqStart+add,indexCksqEnd));
        link.setCkmc(html.substring(indexCkmkStart+add,indexCkmkEnd));
        link.setZnbcy(html.substring(indexZnbcyStart+add,indexZnbcyEnd));
        link.setCkgw(html.substring(indexCkgwStart+add,indexCkgwEnd));
        link.setZxxck(html.substring(indexZxxckStart+add,indexZxxckEnd));
        link.setYhm(html.substring(indexYhmStart+add,indexYhmEnd));
        link.setYhzx(html.substring(indexYhzxStart+add,indexYhzxEnd));
        link.setXxzx(html.substring(indexXxzxStart+add,indexXxzxEnd));
        link.setWdsc(html.substring(indexWdscStart+add,indexWdscEnd));
        link.setWdtx(html.substring(indexWdtxStart+add,indexWdtxEnd));
        return link;
    }
}

