package cc.gukeer.open.service;

import cc.gukeer.open.persistence.entity.Dict;
import com.github.pagehelper.PageInfo;

/**
 * Created by jon on 17-6-7.
 */
public interface DictService {
    public PageInfo<Dict> selectAll(int pageNum, int pageSize, String type);

    public boolean insert(Dict dict);

    public boolean updateBy(Dict dict);

    public boolean deleteById(String id);

    public Dict getDictById(String id);

    public String saveDict(Dict dict);

    public Long delDict(Dict dict);

}
