package cc.gukeer.support.analysis.service;

import cc.gukeer.support.analysis.entity.LeleInfo;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lx on 2016/12/6.
 */
public class AnalysisXml {

    public List<LeleInfo> getPojo(File file)  {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        List<LeleInfo> leleList = new ArrayList<>();
        Element root = document.getRootElement();
        List<Element> node = root.elements();
        for (Element element : node) {
            LeleInfo lele = new LeleInfo();
            Element id = element.element("id");
            Element name = element.element("name");
            Element grade = element.element("grade");
            Element course = element.element("course");
            Element category = element.element("category");
            Element medium_link = element.element("medium_link");
            Element poster = element.element("poster");
            Element duration = element.element("duration");
            Element shortdesc = element.element("shortdesc");

            lele.setId(id.getText());
            lele.setName(name.getText());
            lele.setGrade(grade.getText());
            lele.setCourse(course.getText());
            lele.setCategory(category.getText());
            lele.setMedium_link(medium_link.getText());
            lele.setPoster(poster.getText());
            lele.setDuration(duration.getText());
            if (shortdesc!=null){
                lele.setShortdesc(shortdesc.getText());
            }
            /**/

            leleList.add(lele);
        }
        return leleList;
    }
}
