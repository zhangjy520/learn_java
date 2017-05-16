package cc.gukeer.smartRing.service.impl;

import cc.gukeer.common.service.BasicService;
import cc.gukeer.smartRing.persistence.dao.MenuMapper;
import cc.gukeer.smartRing.persistence.entity.Menu;
import cc.gukeer.smartRing.persistence.entity.MenuExample;
import cc.gukeer.smartRing.service.MenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by conn on 2016/8/8.
 */
@Service
public class MenuServiceImpl extends BasicService implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public PageInfo<Menu> findAllList(int pageNum, int pageSize) {

        MenuExample example = new MenuExample();
        example.createCriteria().andDelFlagEqualTo(0);
        example.setOrderByClause("create_date desc");

        PageHelper.startPage(pageNum, pageSize);
        List<Menu> list = menuMapper.selectByExample(example);

        PageInfo<Menu> pageInfo = new PageInfo<Menu>(list);

        return pageInfo;
    }

    @Override
    public Menu findMenuById(String id) {

        Menu menu = menuMapper.selectByPrimaryKey(id);
        return menu;
    }

    @Override
    public int updateMenu(Menu menu) {
        MenuExample example = new MenuExample();
        example.createCriteria().andIdEqualTo(menu.getId());
        int count = menuMapper.updateByExampleSelective(menu, example);
        return count;
    }

    @Override
    public int insertMenu(Menu menu) {
        int count = menuMapper.insertSelective(menu);
        return count;
    }

    @Override
    public List<Menu> findAllMenu() {
        MenuExample example = new MenuExample();
        example.createCriteria().andDelFlagEqualTo(0);
        example.setOrderByClause("create_date desc");
        List<Menu> list = menuMapper.selectByExample(example);
        return list;
    }
}
