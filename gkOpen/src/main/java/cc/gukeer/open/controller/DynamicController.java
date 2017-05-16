package cc.gukeer.open.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.entity.ResultEntity;
import cc.gukeer.common.tld.DateTimeUtil;
import cc.gukeer.open.persistence.entity.Dynamic;
import cc.gukeer.open.persistence.entity.extension.ExtentionDynamic;
import cc.gukeer.open.service.DynamicService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LL on 2017/2/15.
 */
@Controller
@RequestMapping(value = "/dynamic")
public class DynamicController extends BasicController {

    @Autowired
    DynamicService dynamicService;

//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    public String index(HttpServletRequest request) {
//        return "info/index";
//    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String dynamic(HttpServletRequest request, Model model) {
        Integer pageNum = getPageNum(request);
        Integer pageSize = getPageSize(request);
        PageInfo<Dynamic> pageInfo = dynamicService.findDynamicBydelFlag(pageNum, pageSize);
        List<ExtentionDynamic> listView = new ArrayList();

        for (Dynamic dynamic : pageInfo.getList()) {
            ExtentionDynamic extentionDynamic = new ExtentionDynamic();
            long releaseTime = dynamic.getReleaseTime();
            String dateFormat = DateTimeUtil.formatDateInterval(releaseTime);
            extentionDynamic.setReleaseTimeExt(dateFormat);
            extentionDynamic.setDynamic(dynamic);
            listView.add(extentionDynamic);
        }
        model.addAttribute("listView", listView);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("adminDynamic", "adminDynamic");
        return "admin/dynamic";
    }

    @RequestMapping(value = "/more", method = RequestMethod.GET)
    public String more(HttpServletRequest request, Model model) {
        Integer pageNum = getPageNum(request);
        Integer pageSize = getPageSize(request);
        PageInfo<Dynamic> pageInfo = dynamicService.findAllDynamicByPage(pageNum, pageSize);
        List<ExtentionDynamic> listView = new ArrayList();
        for (Dynamic dynamic : pageInfo.getList()) {
            ExtentionDynamic extentionDynamic = new ExtentionDynamic();
            long releaseTime = dynamic.getReleaseTime();
            String dateFormat = DateTimeUtil.formatDateInterval(releaseTime);
            extentionDynamic.setReleaseTimeExt(dateFormat);
            extentionDynamic.setDynamic(dynamic);
            listView.add(extentionDynamic);
        }
        model.addAttribute("listView", listView);
        model.addAttribute("pageInfo", pageInfo);
        return "indexInfo/more";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, Model model) {

        String id = request.getParameter("id");
        if (null != id) {
            Dynamic dynamic = dynamicService.findDynamicByPrimarykey(id);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ExtentionDynamic extentionDynamic = new ExtentionDynamic();
            long releaseTime = dynamic.getReleaseTime();
            String dateFormat = DateTimeUtil.formatDateInterval(releaseTime);
            extentionDynamic.setReleaseTimeExt(dateFormat);
            extentionDynamic.setDynamic(dynamic);
            model.addAttribute("extentionDynamic", extentionDynamic);
        }
//        dynamicService.findDynamicById();
        return "indexInfo/detail";
    }


    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.GET)
    public ResultEntity dynamicDel(HttpServletRequest request, Model model) {
        String ids = request.getParameter("ids");
        if (ids != null) {
            String[] idArray = ids.split(",");
            System.out.println(idArray.length);
            if (idArray.length == 1) {
                return ResultEntity.newErrEntity("您当前没有选中任何删除条目");
            }
            int succ = 0;
            for (int i = 1; i < idArray.length; i++) {
                succ = dynamicService.delDynamicByPrimaryId(idArray[i]);
            }
            if (succ == 1) {
                return ResultEntity.newResultEntity("删除成功", "dynamic/index");
            } else {
                return ResultEntity.newErrEntity("删除失败");
            }
        } else {
            return ResultEntity.newErrEntity("您当前没有选中任何删除条目");
        }
    }

    @RequestMapping(value = "/del/one", method = RequestMethod.GET)
    public String dynamicDelOne(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
            int succ = 0;
            succ = dynamicService.delDynamicByPrimaryId(id);
//            if (succ == 1) {
//                return ResultEntity.newResultEntity("删除成功", "dynamic/index");
//            } else {
//                return ResultEntity.newErrEntity("删除失败");
//            }
        return "redirect:/dynamic/index";
        }

}
