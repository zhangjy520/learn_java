package cc.gukeer.open.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.entity.ResultEntity;
import cc.gukeer.common.utils.PrimaryKey;
import cc.gukeer.open.persistence.entity.Dict;
import cc.gukeer.open.persistence.entity.OpenUser;
import cc.gukeer.open.service.DictService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by jon on 17-6-7.
 */

@Controller
@RequestMapping("/dict")
public class DictController extends BasicController {

    @Resource
    private DictService dictService;

/*    @Resource
    private JedisPool jedisPool;*/

    @RequestMapping("/index")
    public String selectAll(HttpServletRequest request, Model model){
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        String mark = request.getParameter("mark");

        PageInfo<Dict> pageInfo = dictService.selectAll(pageNum, pageSize, mark);
        model.addAttribute("pageInfo",pageInfo);

/*        Jedis jedis = jedisPool.getResource();
        String sex = jedis.get("xd");
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Dict>>(){}.getType();
        List<Dict> list = gson.fromJson(sex, listType);*/

        return "dict/index";
    }

    @RequestMapping("/to/{edit}")
    public String toEdit(HttpServletRequest request, @PathVariable String edit, Model model){
        String url = null;
        if("add".equals(edit)){
            url = "dict/add";
        }else if ("update".equals(edit)){
            String id = request.getParameter("id");
            Dict dict = dictService.getDictById(id);
            model.addAttribute("dict", dict);
            url = "dict/update";
        }
        return url;
    }

    @RequestMapping("/do/{edit}")
    @ResponseBody
    public ResultEntity edit(HttpServletRequest request, @PathVariable String edit, Dict dict){
        OpenUser openUser = getLoginUser();

        if("insert".equals(edit)){
            dict.setId(PrimaryKey.get());
            dict.setCreateBy(openUser.getId());
            dict.setCreateDate(System.currentTimeMillis());
            if(dictService.insert(dict)){
                if(!dictService.saveDict(dict).equals("OK")){
                    logger.error("缓存失败");
                }
                return ResultEntity.newResultEntity("新增成功", null);
            }
        }else if("update".equals(edit)){
            if(dictService.updateBy(dict)){
                if(!dictService.saveDict(dict).equals("OK")){
                    logger.error("缓存失败");
                }
                return ResultEntity.newResultEntity("更新成功", null);
            }
        }else if ("del".equals(edit)){
            if(dictService.deleteById(dict.getId())){
                if(dictService.delDict(dict) < 1){
                    logger.error("缓存失败");
                }
                return ResultEntity.newResultEntity(ResultEntity.OK_CODE,"删除成功", null);
            }
        }

        return ResultEntity.newErrEntity("操作失败");
    }

}
