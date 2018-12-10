package cc.gukeer.common;

import java.io.IOException;

/**
 * RAR格式压缩文件解压工具类
 * 不支持RAR格式压缩
 * 支持中文,支持RAR压缩文件密码
 * 依赖jar包
 * commons-io.jar
 * commons-logging.jar
 * java-unrar-decryption-supported.jar
 * gnu-crypto.jar
 *
 * @author ninemax
 */
public class RarDecompressionUtil {

    public static void main(String[] args) throws IOException, InterruptedException {


        String password = "";




        String cmd = "D:\\winRar\\rar x -hp12345 E:\\服a务器部署用到的软件.rar E:\\ces\\";
        Process proc;

        proc = Runtime.getRuntime().exec(cmd);


        if (proc.waitFor() != 0) {
            System.out.println("解压密码错误，尝试密码:"+password);
        }else{
            System.out.println("破解成功，您的密码是："+password);
        }
    }
}


