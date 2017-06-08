package cc.gukeer.common.utils;

import java.io.*;
import java.util.List;

import java.util.ArrayList;
        import java.util.LinkedList;
        import java.util.List;

/***
 *
 * @author Jacky
 *
 */
public class FileUtil {

    public static final String COMPANY_LICENCE_SCAN = "image/completedata/company/"; //企业营业执照
    public static final String CORPORATE_IDCARD = "image/completedata/company/";     //法人身份证

    public static final String PERSONAL_CERTIFICATE = "image/completedata/personal/"; //个人从业资格证书
    public static final String PERSONAL_IDCARD = "image/completedata/personal/";        //个人身份证
    public static final String PERSONAL_WORKS = "image/completedata/personal/";         //个人作品


    public static void write(String path, String content, boolean append) {
        FileWriter writer = null;
        try {
// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            writer = new FileWriter(path, append);
            writer.write(content);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static String readFile(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sb = new StringBuffer();

        try {
            // System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            //int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                //System.out.println("line " + line + ": " + tempString);
                // result.add(tempString);
                //line++;

                sb.append(tempString);
                sb.append("\r\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        return sb.toString();
    }

    public static void write(String path, String content) {
        String s = new String();
        String s1 = new String();
        try {
            File f = new File(path);
            if (f.exists()) {
                System.out.println("文件存在");
            } else {
                System.out.println("文件不存在，正在创建...");
                if (f.createNewFile()) {
                    System.out.println("文件创建成功！");
                } else {
                    System.out.println("文件创建失败！");
                }

            }
            BufferedReader input = new BufferedReader(new FileReader(f));

            while ((s = input.readLine()) != null) {
                s1 += s + "\n";
            }

            input.close();
            s1 += content;

            BufferedWriter output = new BufferedWriter(new FileWriter(f));
            output.write(s1);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 合并文件
     * @param toFile 目标文件
     * @param files  要合并的文件数组
     */
    public static void hebingFiles(File toFile, File[] files) {
        try {
            FileOutputStream fout = new FileOutputStream(toFile);
            FileInputStream fis = null;
            for (File file : files) {
                fis = new FileInputStream(file);
                byte data[] = new byte[1024];
                int read = 0;
                while ((read = fis.read(data)) != -1) {
                    fout.write(data, 0, read);
                }
                fis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 文件分割
     * @param file 要分割的文件
     * @param dir  保存分割数据的文件夹
     * @param comminuteSize 分割块大小
     */
    public static void fileComminute(File file, File dir, int comminuteSize) {
        long fileSize = file.length();
        System.out.println("fileSize==>" + fileSize);
        long bit = (fileSize - 1) / comminuteSize + 1;
        System.out.println("bit==>" + bit);
        if (fileSize < 2) {
            System.out.println("文件太小,不需要分割");
            return;
        }
        int fileNumber = 0;
        try {
            FileInputStream fis = new FileInputStream(file);
            String name = file.getName();
            FileOutputStream fout = null;
            int tempSize = comminuteSize;
            int read = 0;
            byte[] datas = new byte[1024];
            while ((read = fis.read(datas)) != -1) {
                if (tempSize >= comminuteSize) {
                    if (fout != null) {
                        fout.close();
                    }
                    File nextFile = new File(dir, name + ".sp" + (fileNumber++));
                    fout = new FileOutputStream(nextFile);
                    tempSize = 0;

                }
                tempSize += read;
                fout.write(datas, 0, read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean deleteRecursive(File path) {
        if (!path.exists()) {
            throw new RuntimeException("can not find");
        }
        boolean ret = true;
        if (path.isDirectory()) {
            for (File f : path.listFiles()) {
                ret = ret && deleteRecursive(f);
            }
        }
        return ret && path.delete();
    }

    //find out file 所在的folder名 如c:/temp/aa.txt,则返回c:/temp
    public static String getFolderPath(String file) {
        String result = "";
        if (null == file || "".equals(file)) {

        } else {
            String[] ss = file.split("\\/");
            for (int i = 0; i < ss.length - 1; i++) {
                result += ss[i];
                result += "/";
            }
        }

        return result;
    }


    public static boolean createFolder(String folder) {
        File dir = new File(folder);
        return dir.mkdirs();
    }

    public static boolean Move(File srcFile, String destPath) {
// Destination directory
        File dir = new File(destPath);

// Move file to new directory
        boolean success = srcFile.renameTo(new File(dir, srcFile.getName()));

        return success;
    }

    public static boolean Move(String srcFile, String destPath) {
// File (or directory) to be moved
        File file = new File(srcFile);

// Destination directory
        File dir = new File(destPath);

// Move file to new directory
        boolean success = file.renameTo(new File(dir, file.getName()));

        return success;
    }

    public static void Copy(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) {
                InputStream inStream = new FileInputStream(oldPath);
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1024];

                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("error  ");
            e.printStackTrace();
        }
    }

    public static void Copy(File oldfile, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
// File oldfile = new File(oldPath);
            if (oldfile.exists()) {
                InputStream inStream = new FileInputStream(oldfile);
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1024];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("error  ");
            e.printStackTrace();
        }
    }


    /**
     */
    public static Boolean isSameFile(String fileName1, String fileName2) {

        Boolean isSame = false;
        FileInputStream fis1 = null;
        FileInputStream fis2 = null;
        try {
            fis1 = new FileInputStream(fileName1);
            fis2 = new FileInputStream(fileName2);
            int len1 = fis1.available();
            int len2 = fis2.available();
            if (len1 == len2) {// 长度相同，则比较具体内容//建立两个字节缓冲区
                byte[] data1 = new byte[len1];
                byte[] data2 = new byte[len2];
// 分别将两个文件的内容读入缓冲区
                fis1.read(data1);
                fis2.read(data2);
// 依次比较文件中的每一个字节
                for (int i = 0; i < len1; i++) {
// 只要有一个字节不同，两个文件就不一样
                    if (data1[i] != data2[i]) {
/*System.out.println("文件内容不一样");
return;*/
                        isSame = false;
                        break;
                    }
                }
                isSame = true;
            } else {// 长度不一样，文件肯定不同
//System.out.println("两个文件长度不同");
                isSame = false;
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fis1) {
                    fis1.close();
                }
                if (null != fis2) {
                    fis2.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return isSame;

    }

    public static ArrayList<String> searchFolderFileNames(String folder) {
        ArrayList<String> results = new ArrayList<String>();
        ArrayList<File> files = searchFolder(folder);
        for (File file : files) {
            results.add(file.getAbsolutePath());
        }

        return results;
    }


    /***
     * 查找folder下的所有文件
     * @param folder 文件夹下的所有文件
     */
    public static ArrayList<File> searchFolder(String folder) {
        ArrayList<File> result = new ArrayList<File>();

        LinkedList<File> list = new LinkedList<File>();
        File dir = new File(folder);
        File file[] = dir.listFiles();


        for (int i = 0; i < file.length; i++) {
            if (file[i].isDirectory())
                list.add(file[i]);
            else {
                //System.out.println(file[i].getAbsolutePath());
            }
        }


        File tmp = null;
        while (!list.isEmpty()) {
            tmp = list.removeFirst();
            if (tmp.isDirectory()) {
                file = tmp.listFiles();
                if (file == null)
                    continue;
                for (int i = 0; i < file.length; i++) {
                    if (file[i].isDirectory())
                        list.add(file[i]);
                    else {
                        //System.out.println(file[i].getAbsolutePath());
                        result.add(file[i]);
                    }

                }
            } else {
                // System.out.println(tmp.getAbsolutePath());
                result.add(tmp);
            }
        }


        return result;
    }


    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static List<String> readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        List<String> result = new ArrayList<String>();
        try {
            // System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            //int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                //System.out.println("line " + line + ": " + tempString);
                result.add(tempString);
                //line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return result;
    }
}