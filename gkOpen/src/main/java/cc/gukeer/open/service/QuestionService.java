package cc.gukeer.open.service;

import cc.gukeer.open.persistence.entity.Question;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by LL on 2017/1/23.
 */
public interface QuestionService {
    PageInfo queryAllQuestion( int pageNum, int pageSize);

    PageInfo queryQuestionByCategory(int category, int pageNum, int pageSize);

    Question queryQuestionById(String id);
    //PageInfo queryAllQuestion(int category, int pageNum, int pageSize);
}
