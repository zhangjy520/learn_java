package com.servlet;

import com.bean.Course;
import com.bean.GradeClass;
import com.bean.Teacher;
import com.bean.Time;
import com.itextpdf.text.Document;
    import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.util.JDBCUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@Controller
public class Application {

    @RequestMapping("/hello")
    public String test(){
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        return "test.html";
    }

    @RequestMapping(value = "/pdf",method = RequestMethod.POST)
    public void test(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException {
        String filePath = "D:\\blog\\exportPdf2.pdf";
        String imagePath = "D:\\blog\\exportImg2.png";
        Document document = new Document();
        try{
            Map getMap = request.getFileMap();
            MultipartFile mfile = (MultipartFile) getMap.get("imgData"); //获取数据
            InputStream file = mfile.getInputStream();
            byte[] fileByte = FileCopyUtils.copyToByteArray(file);

            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(imagePath));//打开输入流
            imageOutput.write(fileByte, 0, fileByte.length);//生成本地图片文件
            imageOutput.close();

            PdfWriter.getInstance(document, new FileOutputStream(filePath)); //itextpdf文件
            document.open();
            document.add(new Paragraph("JUST TEST ..."));
            Image image = Image.getInstance(imagePath); //itext-pdf-image
            float heigth = image.getHeight();
            float width = image.getWidth();
            int percent = getPercent2(heigth, width);  //按比例缩小图片
            image.setAlignment(Image.MIDDLE);
            image.scalePercent(percent+3);
            document.add(image);
            document.close();

        }catch (DocumentException de) {
            System.err.println(de.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();

        }
    }

    @RequestMapping(value = "/sql/test" ,method = RequestMethod.GET)
    public void sqlTest(){
        String sql = "select time_id from time where week = ?";
        PreparedStatement ps = null;
        try {
            ps = JDBCUtil.getConnection().prepareStatement(sql);
            ps.setString(1,"1");
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                System.out.println(result.getString(1));
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.returnResource(ps);
        }
    }

    public void paiKe(){

    }

    /*3个班级*/
    public static List<GradeClass> dataClass(){
        GradeClass gradeClass1 = new GradeClass("1","1班");
        GradeClass gradeClass2 = new GradeClass("2","2班");
        GradeClass gradeClass3 = new GradeClass("3","3班");
        List<GradeClass> list = new ArrayList();
        list.add(gradeClass1);
        list.add(gradeClass2);
        list.add(gradeClass3);
        return list;
    }

    /*课程教师*/
    public static List<Teacher> dataTeacher(){
        Teacher teacher1 = new Teacher("1","英语老师1");
        Teacher teacher2 = new Teacher("2","英语老师2");
        Teacher teacher3 = new Teacher("3","历史老师");
        Teacher teacher4 = new Teacher("4","数学老师1");
        Teacher teacher5 = new Teacher("5","数学老师2");
        Teacher teacher6 = new Teacher("6","化学老师");
        Teacher teacher7 = new Teacher("7","音乐老师");
        Teacher teacher8 = new Teacher("8","语文老师1");
        Teacher teacher9 = new Teacher("9","语文老师2");
        Teacher teacher10 = new Teacher("10","物理老师");
        Teacher teacher11 = new Teacher("11","体育老师");
        Teacher teacher12 = new Teacher("12","生物老师");
        Teacher teacher13 = new Teacher("13","技术综合老师");
        Teacher teacher14 = new Teacher("14","家政老师");
        Teacher teacher15 = new Teacher("15","地理老师");
        Teacher teacher16 = new Teacher("16","美术老师");
        Teacher teacher17 = new Teacher("17","义工老师");
        Teacher teacher18 = new Teacher("18","班主任");

        List<Teacher> list = new ArrayList<>();
        list.add(teacher1);
        list.add(teacher2);
        list.add(teacher3);
        list.add(teacher4);
        list.add(teacher5);
        list.add(teacher6);
        list.add(teacher7);
        list.add(teacher8);
        list.add(teacher9);
        list.add(teacher10);
        list.add(teacher11);
        list.add(teacher12);
        list.add(teacher13);
        list.add(teacher14);
        list.add(teacher15);
        list.add(teacher16);
        list.add(teacher17);
        list.add(teacher18);

        return list;
    }


    //课程表，英语3，历史2，数学4，化学2，音乐1，政治2，语文3，物理2，体育2，生物2，技术综合1，家政1，地理2，美术1，义工1，班会1
    public static List<Course> dataCourse(){
        Course course1 = new Course("1","英语");
        Course course2 = new Course("2","历史");
        Course course3 = new Course("3","数学");
        Course course4 = new Course("4","化学");
        Course course5 = new Course("5","音乐");
        Course course6 = new Course("6","政治");
        Course course7 = new Course("7","语文");
        Course course8 = new Course("8","物理");
        Course course9 = new Course("9","体育");
        Course course10 = new Course("10","生物");
        Course course11 = new Course("11","技术综合");
        Course course12 = new Course("12","家政");
        Course course13 = new Course("13","地理");
        Course course14 = new Course("14","美术");
        Course course15 = new Course("15","义工");
        Course course16 = new Course("16","班会");

        List<Course> list = new ArrayList<>();
        list.add(course1);
        list.add(course2);
        list.add(course3);
        list.add(course4);
        list.add(course5);
        list.add(course6);
        list.add(course7);
        list.add(course8);
        list.add(course9);
        list.add(course10);
        list.add(course11);
        list.add(course12);
        list.add(course13);
        list.add(course14);
        list.add(course15);
        list.add(course16);

        return list;
    }

    /*一周五天每天6节课假数据*/
    public static List<Time> dataTime(){
        List<Time> list = new ArrayList<>();
        int count =0;
        for (int i=1;i<=5;i++){
            for (int j = 1; j <=6 ; j++) {
                count++;
                Time time = new Time(String.valueOf(count),""+i+","+j+"");
                list.add(time);
            }
        }
        return list;
    }


    private static int getPercent2(float h, float w) {
        int p = 0;
        float p2 = 0.0f;
        p2 = 530 / w * 100;
        p = Math.round(p2);
        return p;
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
       // System.out.println(dataTime());
        /*int aaa = 123321;
        String ccc = String.valueOf(aaa);
        boolean flag = false;
        for (int i = 0; i < ccc.length(); i++) {
            if (ccc.charAt(i) == ccc.charAt(ccc.length()-i-1)){
                flag = true;
            }else {
                flag = false;
                break;
            }

        }
        if (flag)
            System.out.println("是");
        else
            System.out.println("否");*/

    }

}
