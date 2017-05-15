package cc.gukeer.syncdata.common;

import cc.gukeer.common.utils.PrimaryKey;
import cc.gukeer.syncdata.persistence.dao.MarkTimeMapper;
import cc.gukeer.syncdata.persistence.entity.MarkTime;
import cc.gukeer.syncdata.persistence.entity.MarkTimeExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by pc-daisike on 2017/2/17.
 */
public class MarkTimeSelectSAT {
    @Autowired
    MarkTimeMapper markTimeMapper;
    public MarkTime selectTimeAndSource(String objectKey){
        MarkTimeExample markTimeExample = new MarkTimeExample();
        markTimeExample.createCriteria().andTableNameEqualTo(objectKey);
        List<MarkTime> markTimeList = markTimeMapper.selectByExample(markTimeExample);
        MarkTime mark = new MarkTime();
        if (markTimeList.size() == 0) {
            mark.setTableName(objectKey);
            mark.setId(PrimaryKey.get());
            markTimeMapper.insert(mark);
            markTimeList=markTimeMapper.selectByExample(markTimeExample);
            mark = markTimeList.get(0);
        }else{
            mark = markTimeList.get(0);
        }
        return mark;
    }
    public int insertMarkTime(String AGENT,String objectKey){
        MarkTime mark = new MarkTime();
        mark.setMarkMinTime(new Date().getTime());
        mark.setPlatIdentifier(AGENT);
        mark.setTableName(objectKey);
        return markTimeMapper.updateByPrimaryKey(mark);

    }

}
