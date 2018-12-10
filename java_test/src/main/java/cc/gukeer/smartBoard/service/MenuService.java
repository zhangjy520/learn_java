package cc.gukeer.smartBoard.service;

import cc.gukeer.smartBoard.persistence.entity.Menu;
import com.github.pagehelper.PageInfo;

/**
 * Created by conn on 2016/8/8.
 */
public interface MenuService {

    PageInfo<Menu> findAllList(int pageNum, int pageSize);

    Menu findMenuById(int id);

    int updateMenu(Menu menu);

    int insertMenu(Menu menu);
}
