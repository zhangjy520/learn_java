---
title: html转pdf,截图保存
date: 2016-05-03 13:51:49
tags: java
---
 
#  使用技术
```
itext.jar  : 将byte文件输入流转换为图片，pdf等
html2canvas.js ：将html页面区域截图为base64编码的图片资源 
java+js
```


# 1. 准备资源
1.  itext.jar

```
 www.baidu.com
```

2.  html2canvas.js
```
www.baidu.com
```

# 2.前端代码：
```

    //进行截图操作，document.querySelector("body") 为要截图的区域
     function test() {
            html2canvas(document.querySelector("body"), {
                onrendered: function (canvas) {
                    var dataUrl = canvas.toDataURL('image/png');
                    var formData = new FormData(); //模拟表单对象
                    formData.append("imgData", convertBase64UrlToBlob(dataUrl)); //写入数据
                    var xhr = new XMLHttpRequest(); //数据传输方法
                    xhr.open("POST", "http://localhost:8080/pdf"); //配置传输方式及地址
                    xhr.send(formData);
                    xhr.onreadystatechange = function () { //回调函数
                    };
                }
            });
        }

        //格式化图片base64编码转换为byte文件流
        function convertBase64UrlToBlob(urlData){
            //去掉url的头，并转换为byte
            var bytes=window.atob(urlData.split(',')[1]);
            //处理异常,将ascii码小于0的转换为大于0
            var ab = new ArrayBuffer(bytes.length);
            var ia = new Uint8Array(ab);
            for (var s = 0;s<bytes.length;s++){
                ia[s] = bytes.charCodeAt(s);
            }
            return new Blob( [ab] , {type : 'image/png'});
        }
        
        <body onclick="test()">//调用截图方法即可
```

# 3.后端代码：   

```

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
```


## 4 包名
     
```
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
```


# 4 前端截图，访问后端接口，保存截图文件到本地为pdf或者其他格式的文件。
     有兴趣的同学可以把后端改为下载文件到本地
	 
# 5 项目源码地址	
 ```
https://github.com/zhangjy520/learn_java/tree/master/boot	
```