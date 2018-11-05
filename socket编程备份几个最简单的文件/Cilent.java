package com.jbns.easymaster.web.portal.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cilent {

    private static String info = "2-1002-1022-1538013418686,192.168.3.15,0;2-1002-1022-1538013418686,192.168.3.16,0;1-1002-1022-1538013418686,192.168.3.17,0;1-1002-1022-1538013418686,192.168.3.18,0;1-1002-1022-1538013418686,192.168.3.19,0;1-1002-1022-1538013418686,192.168.3.20,0;1-1002-1022-1538013418686,192.168.3.21,0;1-1002-1022-1538013418686,192.168.3.22,0;1-1002-1022-1538013418686,192.168.3.23,0;1-1002-1022-1538013418686,192.168.3.24,0;1-1002-1022-1538013418686,192.168.3.25,0;1-1002-1022-1538013418686,192.168.3.26,0;1-1002-1022-1538013418686,192.168.3.27,0;1-1002-1022-1538013418686,192.168.3.28,0;1-1002-1022-1538013418686,192.168.3.29,0;1-1002-1022-1538013418686,192.168.3.30,0;1-1002-1022-1538013418686,192.168.3.31,0;1-1002-1022-1538013418686,192.168.3.32,0;1-1002-1022-1538013418686,192.168.3.33,0;1-1002-1022-1538013418686,192.168.3.34,0;1-1002-1022-1538013418686,192.168.3.35,0;1-1002-1022-1538013418686,192.168.3.36,0;1-1002-1022-1538013418686,192.168.3.37,0;1-1002-1022-1538013418686,192.168.3.38,0;1-1002-1022-1538013418686,192.168.3.39,0;1-1002-1022-1538013418686,192.168.3.40,0;1-1002-1022-1538013418686,192.168.3.41,0;1-1002-1022-1538013418686,192.168.3.42,0;1-1002-1022-1538013418686,192.168.3.43,0;1-1002-1022-1538013418686,192.168.3.44,0;1-1002-1022-1538013418686,192.168.3.45,\n" +
            "0;1-1002-1022-1538013418686,192.168.3.46,0;";
    private static String localhost;
    private static byte[] b;
    private static String a;

    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket socket = new Socket(localhost, 4000);
        OutputStream outputStream = socket.getOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        b = "".getBytes();
        ps.write(b);
        ps.flush();
        //千万不能忘记关闭输入输出流!!否则不会出结果!!
        //socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        while((a = bf.readLine()) != null){
            System.out.println("服务器说:"+a);
        }
        socket.shutdownInput();

        //bf.close();
        //inputStream.close();
        //ps.close();
        //outputStream.close();
        //socket.close();
    }
}
