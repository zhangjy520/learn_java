package com.servlet;

import com.spreada.utils.chinese.ZHConverter;

import java.io.*;
import java.net.MalformedURLException;

public class Test {

    public static String converter(String obj, Integer type) {
        String res = null;
        ZHConverter converter = null;
        if (type == 1) {
            //简体，繁体转为简体
            converter = ZHConverter.getInstance(ZHConverter.SIMPLIFIED);
            res = converter.convert(obj);
        } else if (type == 0) {
            //繁体，简体转为繁体
            converter = ZHConverter.getInstance(ZHConverter.TRADITIONAL);
            res = converter.convert(obj);
        }
        return res;
    }


    public static void readTxtFile(String filePath,String outPath){
        try {
            OutputStream out = new FileOutputStream(outPath, true);
            String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    String reg = "[^\u4e00-\u9fa5]";
                    lineTxt = lineTxt.replaceAll(reg, "");


                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        String reg = "[^\u4e00-\u9fa5]";
        readTxtFile("D:/1.txt","D:/2.txt");


        //输入流
        InputStream in = new FileInputStream("D:/1.txt");
        //输出流
        OutputStream out = new FileOutputStream("D:/2.txt", true);

        try {
            byte[] buffer = new byte[1024];
            while (true) {
                int byteRead = in.read(buffer);
                if (byteRead == -1) break;
                out.write(buffer, 0, byteRead);
            }
        } catch (MalformedURLException ex) {
            System.err.println(args[0] + " is not a URL Java understands.");
        } finally {
            if (in != null) in.close();
            if (out != null) {
                out.close();
            }

        }
    }

}
