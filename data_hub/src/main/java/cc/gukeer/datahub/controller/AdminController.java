package cc.gukeer.datahub.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.utils.GukeerStringUtil;
import cc.gukeer.common.utils.PrimaryKey;
import cc.gukeer.common.utils.ResultEntity;
import cc.gukeer.datahub.modeView.QueueObjView;
import cc.gukeer.datahub.modeView.SyncView;
import cc.gukeer.datahub.persistence.entity.PushObj;
import cc.gukeer.datahub.service.PushObjService;
import cc.gukeer.datahub.service.RefPlatformService;
import cc.gukeer.datahub.service.SyncService;
import cc.gukeer.syncdata.persistence.entity.DetailObj;
import cc.gukeer.syncdata.persistence.entity.DetailObjColumn;
import cc.gukeer.syncdata.service.push.SyncMian;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lx on 2017/4/14.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController extends BasicController {
    @Autowired
    RefPlatformService refPlatformService;
    @Autowired
    SyncService syncService;
    @Autowired
    PushObjService pushObjService;
    @Autowired
    SyncMian syncMian;


    @RequestMapping(value = "/queue", method = RequestMethod.GET)
    public String queue() {
        return "index";
    }

    @RequestMapping(value = "/bind", method = RequestMethod.GET)
    public String bind(Model model) {
        List<QueueObjView> queueObjViewList = pushObjService.getQueueObj();
        //发送的数据对象
        List<Map<String, String>> result = pushObjService.selectResult();
        //查询的是全部对象
        List<Map<String, String>> queue = pushObjService.selectQueue();

        model.addAttribute("queueObjViewList", queueObjViewList);
        model.addAttribute("queue", queue);
        model.addAttribute("result", result);

        return "bind";
    }

    @Transactional
    @RequestMapping(value = "/refobj", method = RequestMethod.GET)
    public String refobj(HttpServletRequest request) {
        String[] ids = request.getParameterValues("objId");
        String queueId = request.getParameter("queueId");
        pushObjService.deleteQueObj(queueId);//删除队列和对象绑定
        pushObjService.bindPushObj(queueId, ids);//再重新绑定
        return "redirect:/admin/bind";
    }

    @RequestMapping(value = "/pushObj", method = RequestMethod.GET)
    public String pushObj(Model model, HttpServletRequest request) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        PageInfo<PushObj> pushObjList = pushObjService.getAllPushObj(pageSize, pageNum);
        model.addAttribute("pageInfo", pushObjList);
        return "pushobj";
    }

    @RequestMapping(value = "/select/table", method = RequestMethod.POST)
    public String selectTable(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        PushObj pushObj = pushObjService.selectPushObjByKey(id);
        model.addAttribute("pushObj", pushObj);
        return "update";

    }

    @RequestMapping(value = "/delete/table", method = RequestMethod.GET)
    public void deleteTable(HttpServletRequest request) {
        String id = request.getParameter("id");
        pushObjService.deletePushObj(id);
    }

    @Transactional
    @RequestMapping(value = "/update/table", method = RequestMethod.POST)
    public String updateTable(HttpServletRequest request) {
        String id = request.getParameter("id");
        String tableName = request.getParameter("tableName");
        String name = request.getParameter("name");
        String remark = request.getParameter("remark");

        PushObj pushObj = new PushObj();
        pushObj.setObjTableName(tableName);
        pushObj.setObjName(name);
        pushObj.setObjAbstract(remark);
        pushObj.setId(id);

        pushObjService.savePushObj(pushObj);

        return "redirect:/admin/pushObj";

    }

    @RequestMapping(value = "/sync", method = RequestMethod.GET)
    public String sync(Model model) {
        PageInfo<SyncView> syncViews = syncService.getSyncView();
        model.addAttribute("syncViews", syncViews);
        return "sync";
    }

    @ResponseBody
    @RequestMapping(value = "/sync/setName", method = RequestMethod.POST)
    public ResultEntity setName(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        String queueName = request.getParameter("content");
        refPlatformService.setName(id, queueName);
        return ResultEntity.newResultEntity();
    }

    @RequestMapping(value = "/sync/open", method = RequestMethod.GET)
    public String syncOpen(HttpServletRequest request) {
        String id = request.getParameter("id");
        refPlatformService.updateSyncStatus(id, 1);

        return "redirect:/admin/sync";
    }

    @RequestMapping(value = "/sync/close", method = RequestMethod.GET)
    public String syncClose(HttpServletRequest request) {
        String id = request.getParameter("id");
        refPlatformService.updateSyncStatus(id, 0);
        return "redirect:/admin/sync";
    }

    @RequestMapping(value = "/sync/init", method = RequestMethod.GET)
    public String syncInit(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        try {
            syncMian.init(id);
            refPlatformService.updateInitData(id, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/sync";
    }

    @RequestMapping(value = "/push/select", method = RequestMethod.GET)
    public String detailSelect(Model model,HttpServletRequest request) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);

        //List<Map<String, String>> pushObjTableList = pushObjService.getTableName();
        PageInfo<Map<String, String>> pageInfo = pushObjService.getTableName(pageNum,pageSize);
        model.addAttribute("pageInfo", pageInfo);

        return "pushobjObject";
    }

    @Transactional
    @RequestMapping(value = "/detail/getTable", method = RequestMethod.POST)
    public String getTable(Model model, HttpServletRequest request) {
        String id = request.getParameter("id");
        //查出了push_obj里面的id,和表名
        List<PushObj> pushObjTableList = pushObjService.getAllPushObj();
        if (id == null || id == "") {
            String name = pushObjTableList.get(0).getObjTableName();//取出集合里面的第一个对象
            List<Map<String, String>> pushObjFiledList = pushObjService.selectFiled(name);//查询某张表中的所有字段
            model.addAttribute("pushObjTableList", pushObjTableList);//打包返回
            model.addAttribute("pushObjFieldLists", pushObjFiledList);
        } else {
            //得到对象
            DetailObj detailObj = pushObjService.getDetailObjById(id);
            //可以得到push_obj的对象，当前属于那一张表
            PushObj pushObj = pushObjService.selectPushObjByKey(detailObj.getPushObjId());
            //字段列
            //查出当前表的所有字段
            List<Map<String, String>> pushObjFiledList = pushObjService.selectFiled(pushObj.getObjTableName());
            //属性列
            List<DetailObjColumn> detailObjColumnList = pushObjService.getDetailObjColumn(detailObj.getId());
            List<Object> columns = new ArrayList();
            for (int i = 0; i < detailObjColumnList.size(); i++) {
                String column = detailObjColumnList.get(i).getName();
                columns.add(column);
            }
            String columnList = "";
            for (int i = 0; i < detailObjColumnList.size(); i++) {
                columnList += "," + detailObjColumnList.get(i).getName();
            }
            model.addAttribute("columns", new Gson().toJson(columns));
            model.addAttribute("columnList", columnList);
            model.addAttribute("pushObjFieldLists", pushObjFiledList);
            //这个是为了得到table的select列表
            model.addAttribute("pushObjTableList", pushObjTableList);
            model.addAttribute("detailObj", detailObj);
            model.addAttribute("detailObjColumnList", detailObjColumnList);
        }

        return "detailObjuColumnUpdate";
    }

    @ResponseBody
    @RequestMapping(value = "/detail/getDate", method = RequestMethod.GET)
    public String getDate(HttpServletRequest request, Model model) {
        String name = request.getParameter("belong");
        List<Map<String, String>> pushObjTableList = pushObjService.selectFiled(name);

        return new Gson().toJson(pushObjTableList);
    }

    @RequestMapping(value = "/push/objectDelete")
    public void objectDelete(HttpServletRequest request) {
        String id = request.getParameter("id");
        pushObjService.objectDelete(id);
    }

    @ResponseBody
    @RequestMapping(value = "/detail/objInsert", method = RequestMethod.POST)
    public ResultEntity objInsert(HttpServletRequest request) {
        String id = request.getParameter("id");
        String pushObjId = request.getParameter("tableId");
        String name = request.getParameter("objectName");
        String belong = request.getParameter("tableName");
        String sort = request.getParameter("sort");
        String columnName = request.getParameter("filed");

        DetailObj existDetailObj = pushObjService.selectDetailObjByName(name);
        if (GukeerStringUtil.isNullOrEmpty(existDetailObj) || StringUtil.isNotEmpty(id)) {
            DetailObj saveDetail = new DetailObj();
            saveDetail.setId(id);
            saveDetail.setPushObjId(pushObjId);
            saveDetail.setName(name);
            saveDetail.setMark(sort);

            id = pushObjService.detailObjSave(saveDetail);//保持修改并删除列

            //保存对象--列 关系
            DetailObjColumn detailObjColumn = new DetailObjColumn();
            String[] a = columnName.split(",");
            for (int i = 1; i < a.length; i++) {
                String column = a[i];
                detailObjColumn.setName(column);
                detailObjColumn.setBelong(belong);
                detailObjColumn.setDetailObjId(id);
                detailObjColumn.setId(PrimaryKey.get());
                pushObjService.detailObjInsert(detailObjColumn);
            }
            ResultEntity resultEntity = ResultEntity.newResultEntity();
            resultEntity.setMsg("操作完成");
            return resultEntity;
        } else {
            return ResultEntity.newErrEntity("对象名重复");
        }
    }
}
