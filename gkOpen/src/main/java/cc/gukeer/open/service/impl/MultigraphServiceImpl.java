package cc.gukeer.open.service.impl;

import cc.gukeer.open.service.MultigraphService;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by lx on 2017/1/16.
 */

@Service
public class MultigraphServiceImpl implements MultigraphService {
    @Override
    public List<String> makeImgPath(String multigraph) {
        ArrayList<String> arrayList = new ArrayList<>();
        if ((null != multigraph) && (!"".equals(multigraph.trim()))) {
            String[] resArray = multigraph.split(",");
            if (0 != resArray.length) {
                for (String arrayStr : resArray) {
                    if ((null != arrayStr) && (!"".equals(arrayStr.trim()))) {
                        int index = arrayStr.indexOf("=");
                        if (-1 != index) {
                            arrayList.add(arrayStr.substring(1, index));
                        }
                    }
                }
            }
        }
        return arrayList;
    }
}
