/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.gukeer.divideStudent.zsfb.web;

import cn.gukeer.common.persistence.Page;
import cn.gukeer.common.utils.DateUtils;
import cn.gukeer.common.utils.MyBeanUtils;
import cn.gukeer.common.utils.StringUtils;
import cn.gukeer.common.utils.excel.ExportExcel;
import cn.gukeer.common.utils.excel.ImportExcel;
import cn.gukeer.common.web.BaseController;
import cn.gukeer.divideStudent.sys.utils.UserUtils;
import cn.gukeer.divideStudent.zsfb.persistence.entity.ZsTask;
import cn.gukeer.divideStudent.zsfb.persistence.entity.ZsTaskConstant;
import cn.gukeer.divideStudent.zsfb.service.ZsTaskService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * 分班任务Controller
 *
 * @author xiangfusheng
 * @version 2016-07-11
 */
@Controller
@RequestMapping(value = "/zsfb/zsTask")
public class ZsTaskController extends BaseController {

    @Autowired
    private ZsTaskService zsTaskService;

    @ModelAttribute
    public ZsTask get(@RequestParam(required = false) String id) {
        ZsTask entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = zsTaskService.get(id);
        }
        if (entity == null) {
            entity = new ZsTask();
            entity.setScsjqr(ZsTaskConstant.NO);
            entity.setFbcg(ZsTaskConstant.NO);
        }
        return entity;
    }



    /**
     * 保存分班任务
     *
     * @param zsTask
     * @param model
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequiresPermissions(value = {"zsfb:zsTask:add", "zsfb:zsTask:edit"}, logical = Logical.OR)
    @RequestMapping(value = "save")
    public String save(ZsTask zsTask, Model model, RedirectAttributes redirectAttributes)
            throws Exception {
        if (!beanValidator(model, zsTask)) {
            return form(zsTask, model);
        }
        if (!zsTask.getIsNewRecord()) {// 编辑表单保存
            ZsTask t = zsTaskService.get(zsTask.getId());// 从数据库取出记录的值
            MyBeanUtils.copyBeanNotNull2Bean(zsTask, t);// 将编辑表单中的非NULL值覆盖数据库记录中的值
            zsTaskService.save(t);// 保存
        } else {// 新增表单保存
            zsTaskService.save(zsTask);// 保存
        }
        addMessage(redirectAttributes, "保存分班任务成功");
        return "redirect:" +"/zsfb/zsTask/first?id=" + zsTask.getId();
    }

    /**
     * 查看，增加，编辑分班任务表单页面
     *
     * @param zsTask
     * @param model
     * @return
     */
    @RequiresPermissions(value = {"zsfb:zsTask:view", "zsfb:zsTask:add", "zsfb:zsTask:edit"}, logical = Logical.OR)
    @RequestMapping(value = "form")
    public String form(ZsTask zsTask, Model model) {
        model.addAttribute("zsTask", zsTask);
        return "zsfb/zsTaskForm";
    }



    /**
     * 分班第一步页面
     *
     * @param zsTask
     * @param request
     * @param model
     * @return
     */

    @RequiresPermissions("zsfb:zsTask:list")
    @RequestMapping(value = {"first"})
    public String first(ZsTask zsTask, HttpServletRequest request, Model model) {
        model.addAttribute("zsTask", zsTask);
        model.addAttribute("importFlag", request.getParameter("importFlag"));

        return "zsfb/first";
    }



    /**
     * 分班第二步页面
     *
     * @param zsTask
     * @param model
     * @return
     */
    @RequiresPermissions("zsfb:zsTask:list")
    @RequestMapping(value = {"second"})
    public String second(ZsTask zsTask, Model model) {
        model.addAttribute("zsTask", zsTask);
        return "zsfb/second";
    }



    /**
     * 分班任务初始化页面
     *
     * @param zsTask
     * @param model
     * @return
     */
    @RequiresPermissions("zsfb:zsTask:list")
    @RequestMapping(value = {"list", ""})
    public String list(ZsTask zsTask, Model model) {
        zsTask.setCreateBy(UserUtils.getUser());
        List<ZsTask> taskList = zsTaskService.findList(zsTask);
        model.addAttribute("taskList", taskList);
        return "zsfb/init";
    }


    /**
     * 预览确认
     *
     * @param zsTask
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequiresPermissions(value = {"zsfb:zsTask:add", "zsfb:zsTask:edit"}, logical = Logical.OR)
    @RequestMapping(value = "savePreview")
    public String savePreview(ZsTask zsTask, RedirectAttributes redirectAttributes) throws Exception {
        zsTaskService.save(zsTask);// 保存
        redirectAttributes.addAttribute("id", zsTask.getId());
        redirectAttributes.addAttribute("importFlag", "true");
        return "redirect:" + "/zsfb/zsTask/first?repage";
    }

    /**
     * 删除分班任务
     *
     * @param zsTask
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("zsfb:zsTask:del")
    @RequestMapping(value = "delete")
    public String delete(ZsTask zsTask, RedirectAttributes redirectAttributes) {
        zsTaskService.delete(zsTask);
        addMessage(redirectAttributes, "删除分班任务成功");
        return "redirect:"  + "/zsfb/zsTask/?repage";
    }

    /**
     * 批量删除分班任务
     *
     * @param ids
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("zsfb:zsTask:del")
    @RequestMapping(value = "deleteAll")
    public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
        String idArray[] = ids.split(",");
        for (String id : idArray) {
            zsTaskService.delete(zsTaskService.get(id));
        }
        addMessage(redirectAttributes, "删除分班任务成功");
        return "redirect:"  + "/zsfb/zsTask/?repage";
    }

    /**
     * 导出excel文件
     *
     * @param zsTask
     * @param request
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("zsfb:zsTask:export")
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportFile(ZsTask zsTask, HttpServletRequest request, HttpServletResponse response,
                             RedirectAttributes redirectAttributes) {
        try {

            String fileName = "分班任务" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            Page<ZsTask> page = zsTaskService.findPage(new Page<ZsTask>(request, response, -1), zsTask);
            new ExportExcel("分班任务", ZsTask.class, 1, 3).setDataList(page.getList()).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出分班任务记录失败！失败信息：" + e.getMessage());
        }
        return "redirect:"  + "/zsfb/zsTask/?repage";
    }

    /**
     * 导入Excel数据
     *
     * @param file
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("zsfb:zsTask:import")
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            int successNum = 0;
            int failureNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ImportExcel ei = new ImportExcel(file, 1, 0);
            List<ZsTask> list = ei.getDataList(ZsTask.class);
            for (ZsTask zsTask : list) {
                try {
                    zsTaskService.save(zsTask);
                    successNum++;
                } catch (ConstraintViolationException ex) {
                    failureNum++;
                } catch (Exception ex) {
                    failureNum++;
                }
            }
            if (failureNum > 0) {
                failureMsg.insert(0, "，失败 " + failureNum + " 条分班任务记录。");
            }
            addMessage(redirectAttributes, "已成功导入 " + successNum + " 条分班任务记录" + failureMsg);
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入分班任务失败！失败信息：" + e.getMessage());
        }
        return "redirect:"  + "/zsfb/zsTask/?repage";
    }

}