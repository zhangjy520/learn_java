package com;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
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
import java.util.Map;

@SpringBootApplication
@Controller
public class Application {


    @RequestMapping("/hello")
    public String test(){
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

    private static int getPercent2(float h, float w) {
        int p = 0;
        float p2 = 0.0f;
        p2 = 530 / w * 100;
        p = Math.round(p2);
        return p;
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);

    }
}
