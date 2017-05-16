package cc.gukeer.open.service.impl;

import cc.gukeer.open.persistence.dao.QuestionMapper;
import cc.gukeer.open.persistence.entity.Question;
import cc.gukeer.open.persistence.entity.QuestionExample;
import cc.gukeer.open.service.QuestionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LL on 2017/1/23.
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionMapper questionMapper;

    public PageInfo queryAllQuestion(int pageNum,int pageSize) {
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andDelFlagEqualTo(0);
        PageHelper.startPage(pageNum,pageSize);
        List<Question> list = questionMapper.selectByExample(questionExample);
        PageInfo<Question> pageInfo = new PageInfo<Question>(list);
        return  pageInfo;

    }

    @Override
    public PageInfo queryQuestionByCategory(int category, int pageNum, int pageSize) {
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andDelFlagEqualTo(0).andCategoryEqualTo(category);
        PageHelper.startPage(pageNum,pageSize);
        List<Question> list = questionMapper.selectByExample(questionExample);
        PageInfo<Question> pageInfo = new PageInfo<>(list);
        return  pageInfo;
    }

    public Question queryQuestionById(String id) {
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andIdEqualTo(id);
        List<Question> list = questionMapper.selectByExample(questionExample);
        if(null != list) {
            return list.get(0);
        }
        return null;
    }
}
