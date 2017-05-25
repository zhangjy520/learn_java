/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.gukeer.divideStudent.school.web;

import com.google.common.collect.Lists;
import cn.gukeer.common.config.Global;
import cn.gukeer.common.persistence.Page;
import cn.gukeer.common.utils.*;
import cn.gukeer.common.utils.excel.ExportExcel;
import cn.gukeer.common.utils.excel.ImportExcel;
import cn.gukeer.common.web.BaseController;
import cn.gukeer.divideStudent.school.persistence.entity.School;
import cn.gukeer.divideStudent.school.service.SchoolService;
import cn.gukeer.divideStudent.sys.persistence.entity.Area;
import cn.gukeer.divideStudent.sys.service.AreaService;
import net.sf.json.JSONArray;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * 学校Controller
 * 
 * @author xiangfusheng
 * @version 2016-06-14
 */
@Controller
@RequestMapping(value = "/sys/school")
public class SchoolController extends BaseController {

	@Autowired
	private SchoolService schoolService;
	@Autowired
	private AreaService areaService;
	@ModelAttribute
	public School get(@RequestParam(required = false) String id) {
		School entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = schoolService.get(id);
		}
		if (entity == null) {
			entity = new School();
		}
		return entity;
	}

	/**
	 * 学校列表页面
	 */
	@RequiresPermissions("sys:school:list")
	@RequestMapping(value = { "list", "" })
	public String list(School school, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<School> page = schoolService.findPage(new Page<School>(request, response), school);
		model.addAttribute("page", page);
		return "school/schoolList";
	}

	/**
	 * 查看，增加，编辑学校表单页面
	 */
	@RequiresPermissions(value={"sys:school:view","sys:school:add","sys:school:edit"},logical= Logical.OR)
	@RequestMapping(value = "form")
	public String form(School school, Model model) {
		model.addAttribute("school", school);
		//通过type获取所有的省份
		Area province = new Area();
		province.setType("1");
		List<Area> provinces = areaService.getAreasByEntity(province);
		model.addAttribute("provinces", provinces);
		if(school.getId() != null && school.getSsdq() != "" &&  school.getSsdq() != null && school.getId() !=""){
			String area = school.getSsdq();
			String areas[] = area.split(",");
			if(areas.length == 3){
				String provinceId = areas[0];
				String cityId = areas[1];
				String countyId = areas[2];
				model.addAttribute("provinceId", provinceId);
				model.addAttribute("cityId", cityId);
				model.addAttribute("countyId", countyId);
				
				Area entity = new Area();
				entity.setParentIds(provinceId);
				List<Area> cities = areaService.getAreasByEntity(entity);
				entity.setParentIds(cityId);
				List<Area> countys = areaService.getAreasByEntity(entity);
				
				model.addAttribute("cities", cities);
				model.addAttribute("countys", countys);
				
			}
		}
		return "school/schoolForm";
	}

	/**
	 * 保存学校
	 */
	@RequiresPermissions(value={"sys:school:add","sys:school:edit"},logical= Logical.OR)
	@RequestMapping(value = "save")
	public String save(School school, Model model, RedirectAttributes redirectAttributes){
		if (!beanValidator(model, school)) {
			return form(school, model);
		}
		if (!school.getIsNewRecord()) {// 编辑表单保存
			School t = schoolService.get(school.getId());// 从数据库取出记录的值
			try {
				MyBeanUtils.copyBeanNotNull2Bean(school, t);
			} catch (Exception e) {
				logger.error("SchoolController.save"+e.getMessage());
			}// 将编辑表单中的非NULL值覆盖数据库记录中的值
			schoolService.save(t);// 保存
		} else {// 新增表单保存
			schoolService.save(school);// 保存
		}
		addMessage(redirectAttributes, "保存学校成功");
		return "redirect:" + "/sys/school/?repage";
	}

	/**
	 * 删除学校
	 */
	@RequiresPermissions("sys:school:del")
	@RequestMapping(value = "delete")
	public String delete(School school, RedirectAttributes redirectAttributes) {
		schoolService.delete(school);
		addMessage(redirectAttributes, "删除学校成功");
		return "redirect:" + "/sys/school/?repage";
	}

	/**
	 * 批量删除学校
	 */
	@RequiresPermissions("sys:school:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] = ids.split(",");
		for (String id : idArray) {
			schoolService.delete(schoolService.get(id));
		}
		addMessage(redirectAttributes, "删除学校成功");
		return "redirect:" + "/sys/school/?repage";
	}

	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("sys:school:export")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(School school, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "学校" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			Page<School> page = schoolService.findPage(new Page<School>(request, response, -1), school);
			new ExportExcel("学校", School.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出学校记录失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + "/sys/school/?repage";
	}

	/**
	 * 导入Excel数据
	 * 
	 */
    @RequiresPermissions("sys:school:import")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<School> list = ei.getDataList(School.class);
			for (School school : list) {
				try {
					schoolService.save(school);
					successNum++;
				} catch (ConstraintViolationException ex) {
					failureNum++;
				} catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条学校记录。");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条学校记录" + failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入学校失败！失败信息：" + e.getMessage());
		}
		return "redirect:"  + "/sys/school/?repage";
	}

	/**
	 * 下载导入学校数据模板
	 */
	@RequiresPermissions("sys:school:import")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "学校数据导入模板.xlsx";
			List<School> list = Lists.newArrayList();
			new ExportExcel("学校数据", School.class, 1).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + "/sys/school/?repage";
	}

	/**
	 * 上传图片
	 * @param request
	 * @param response
	 * @param file
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "/logoUpload")
	public void imageUpload(HttpServletRequest request, HttpServletResponse response, MultipartFile file)
			throws IllegalStateException, IOException {
		// 判断文件是否为空
		if (!file.isEmpty()) {
			// 文件保存路径
			String realPath = VFSUtil.getVFSRootPath() + "/school/" + "/files/logo/";
			// 转存文件
			FileUtils.createDirectory(realPath);
			file.transferTo(new File(realPath + file.getOriginalFilename()));
		}

	}

	/**
	 * 显示图片
	 * @param response
	 * @param picPath图片路径
	 * @throws Exception
	 */
	@RequestMapping(value = "/showPicture")
    public void showPicture(HttpServletResponse response, String picPath){
    	File file = new File(VFSUtil.getVFSRootPath() + picPath);
    	if (!file.exists()) {
	    	logger.error("找不到文件[" + VFSUtil.getVFSRootPath() + picPath + "]");
	    	return;
    	}
    	response.setContentType("multipart/form-data");
    	InputStream reader = null;
    	try {
	    	reader = VFSUtil.getInputStream(file, true);
	    	byte[] buf = new byte[1024];
	    	int len = 0;
	    	OutputStream out = response.getOutputStream();
	    	while ((len = reader.read(buf)) != -1) {
	    		out.write(buf, 0, len);
	    	}
	    	out.flush();
    	} catch (Exception ex) {
    	logger.error("显示图片时发生错误:" + ex.getMessage(), ex);
    	} finally {
	    	if (reader != null) {
		    	try {
		    		reader.close();
		    	} catch (Exception ex) {
		    		logger.error("关闭文件流出错", ex);
		    	}
	    	}
    	}
    }
	/**
	 * 通过父节点获取子节点
	 * @param parentId,父节点的id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getAreasByParentId")
	public String getAreasByParentId(String parentId){
		Area area = new Area();
		area.setParentIds(parentId);
		List<Area> areas = areaService.getAreasByEntity(area);
		HashMap<String, String> areaMap = null;
		List<HashMap<String, String>> maps = new ArrayList<HashMap<String, String>>();
		for(Area a: areas){
			areaMap = new HashMap<String, String>();
			areaMap.put("id", a.getId());
			areaMap.put("name", a.getName());
			maps.add(areaMap);
		}
		JSONArray fromObject = JSONArray.fromObject(maps);
		return fromObject.toString();
	}

}