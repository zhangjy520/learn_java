package cc.gukeer.smartRing.persistence.dao;

import cc.gukeer.smartRing.persistence.entity.ScoreMapRule;
import cc.gukeer.smartRing.persistence.entity.ScoreMapRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScoreMapRuleMapper {
    int deleteByExample(ScoreMapRuleExample example);

    int deleteByPrimaryKey(String ruleId);

    int insert(ScoreMapRule record);

    int insertSelective(ScoreMapRule record);

    List<ScoreMapRule> selectByExample(ScoreMapRuleExample example);

    ScoreMapRule selectByPrimaryKey(String ruleId);

    int updateByExampleSelective(@Param("record") ScoreMapRule record, @Param("example") ScoreMapRuleExample example);

    int updateByExample(@Param("record") ScoreMapRule record, @Param("example") ScoreMapRuleExample example);

    int updateByPrimaryKeySelective(ScoreMapRule record);

    int updateByPrimaryKey(ScoreMapRule record);
}