package cc.gukeer.smartRing.persistence.dao;

import cc.gukeer.smartRing.persistence.entity.StuScoreRef;
import cc.gukeer.smartRing.persistence.entity.StuScoreRefExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StuScoreRefMapper {
    int deleteByExample(StuScoreRefExample example);

    int deleteByPrimaryKey(String testSeq);

    int insert(StuScoreRef record);

    int insertSelective(StuScoreRef record);

    List<StuScoreRef> selectByExample(StuScoreRefExample example);

    StuScoreRef selectByPrimaryKey(String testSeq);

    int updateByExampleSelective(@Param("record") StuScoreRef record, @Param("example") StuScoreRefExample example);

    int updateByExample(@Param("record") StuScoreRef record, @Param("example") StuScoreRefExample example);

    int updateByPrimaryKeySelective(StuScoreRef record);

    int updateByPrimaryKey(StuScoreRef record);
}