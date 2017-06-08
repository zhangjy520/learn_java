package cc.gukeer.support.analysis.service;

import cc.gukeer.support.analysis.entity.LeleInfoPart;
import cc.gukeer.support.analysis.entity.LeleInfo;
import com.google.gson.Gson;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.dom4j.DocumentException;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by lx on 2016/12/6.
 */
public class main {

    public static List<LeleInfo> merge (File excel1, File excel2) throws DocumentException, IOException, InvalidFormatException {
        AnalysisExcel analysisExcel = new AnalysisExcel();
        AnalysisXml analysisXml = new AnalysisXml();
        List<LeleInfoPart> excelList = analysisExcel.GetPojo(excel1);
        List<LeleInfo> xmlList = analysisXml.getPojo(excel2);
        for (int i = 0; i < xmlList.size(); i++) {
            for (int j = 0; j < excelList.size(); j++) {
                if (xmlList.get(i).getName().equals(excelList.get(j).getChip())){
                    xmlList.get(i).setGrade(excelList.get(j).getTerm());
                    xmlList.get(i).setChapter(excelList.get(j).getSection());
                }
            }
        }
        return xmlList;
    }

    public  static String toJson (List<LeleInfo> leleinfo){
        for (LeleInfo leleInfo : leleinfo) {
           if (leleInfo.getChapter()==null){
               leleInfo.setChapter("");
           }
        }
            Gson gson = new Gson();
            String json = gson.toJson(leleinfo);

        return json;
    }

    public static void  main(String[] args) throws DocumentException, IOException, InvalidFormatException {
        File excel1 = new File("E:\\excel\\乐乐课堂资源说明－初中化学.xlsx");
        File excel2 = new File("E:\\excel\\乐乐课堂资源说明－初中数学.xlsx");
        File excel3 = new File("E:\\excel\\乐乐课堂资源说明－初中物理.xlsx");
        File excel4 = new File("E:\\excel\\乐乐课堂资源说明－高中化学.xlsx");
        File excel5 = new File("E:\\excel\\乐乐课堂资源说明－高中数学.xlsx");
        File excel6 = new File("E:\\excel\\乐乐课堂资源说明－高中物理.xlsx");
        File excel7 = new File("E:\\excel\\乐乐课堂资源说明－小学奥数.xlsx");
        File excel8 = new File("E:\\excel\\乐乐课堂资源说明－小学数学.xlsx");
        File excel9 = new File("E:\\excel\\乐乐课堂资源说明－小学语文.xlsx");

        File xml1 = new File("E:\\xml\\初中化学.xml");
        File xml2 = new File("E:\\xml\\初中数学.xml");
        File xml3 = new File("E:\\xml\\初中物理.xml");
        File xml4 = new File("E:\\xml\\高中化学.xml");
        File xml5 = new File("E:\\xml\\高中数学.xml");
        File xml6 = new File("E:\\xml\\高中物理.xml");
        File xml7 = new File("E:\\xml\\小学奥数.xml");
        File xml8 = new File("E:\\xml\\小学数学.xml");
        File xml9 = new File("E:\\xml\\小学语文.xml");


       /*String a = toJson( merge(excel1,xml1));
        /*String b = toJson( merge(excel2,xml2));
        String c = toJson( merge(excel3,xml3));*/
  /*      String d = toJson( merge(excel4,xml4));
        String e = toJson( merge(excel5,xml5));
        String f = toJson( merge(excel6,xml6));*/
       String g = toJson( merge(excel7,xml7));
        String h = toJson( merge(excel8,xml8));
       /* String i = toJson( merge(excel9,xml9));*/

      /*  System.out.print(a);
        System.out.print("\n");
        System.out.print(b);
        System.out.print("\n");
        System.out.print(c);*/
       /* System.out.print("\n");
        System.out.print(d);
        System.out.print("\n");
        System.out.print(e);
        System.out.print("\n");
        System.out.print(f);
        System.out.print("\n");*/

        System.out.print(g);
        System.out.print("\n");
        System.out.print(h);
        System.out.print("\n");
      /*  System.out.print(i);
        System.out.print("\n");*/

    }

}
