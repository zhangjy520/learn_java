title: excel各行图片读取
author: zjy
date: 2018-10-16 14:55:02
tags:
---
# 需要注意的是：每张图片必须不能超过该单元格的边界，如果超过，需要将单元格拉大完全包裹住图片，否则会漏数据。

# excel有一表头是图片,需要导入的时候解析成每一行的头像


## 如图

拿到图片
![upload successful](/paste/pasted-6.png)


读取每一行并解析

代码：(读取并保存到本地C:\\Users\\shenyy\\Desktop\\壁纸\\头像  路径下面。看个人需求对图片做其他处理)
     	
      package cc.gukeer.common;
      import org.apache.poi.hssf.usermodel.*;
      import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
      import org.apache.poi.ss.usermodel.PictureData;

      import java.io.FileInputStream;
      import java.io.FileOutputStream;
      import java.io.InputStream;
      import java.util.List;

      public class ReadPicturesFromExcel {

        public static void main(String[] args) throws InvalidFormatException,
                Exception {

            InputStream inp = new FileInputStream(
                    "C:\\Users\\shenyy\\Desktop\\壁纸\\444\\111.xls");
            HSSFWorkbook workbook = new HSSFWorkbook(inp);

            List<HSSFPictureData> pictures = workbook.getAllPictures();
            HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);

            int i = 0;
            for (HSSFShape shape : sheet.getDrawingPatriarch().getChildren()) {
                HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();

                if (shape instanceof HSSFPicture) {
                    HSSFPicture pic = (HSSFPicture) shape;
                    int row = anchor.getRow1();
                    System.out.println(i + "--->" + anchor.getRow1() + ":"
                            + anchor.getCol1());
                    int pictureIndex = pic.getPictureIndex() - 1;
                    HSSFPictureData picData = pictures.get(pictureIndex);

                    System.out.println(i + "--->" + pictureIndex);
                    savePic(sheet, row, picData);
                }
                i++;
            }
        }

        private static void savePic(HSSFSheet sheet, int i, PictureData pic) throws Exception {

            HSSFRow s = sheet.getRow(i);
            HSSFCell d = s.getCell(0);
            String name = d.getStringCellValue();

            String ext = pic.suggestFileExtension();

            byte[] data = pic.getData();

            FileOutputStream out = new FileOutputStream(
                    "C:\\Users\\shenyy\\Desktop\\壁纸\\头像\\" + name + "."+ext);
            out.write(data);
            out.close();
        }

    }
    
    
# 运行效果

![upload successful](/paste/pasted-7.png)


附件：

转换文件夹下所有图片格式
		
    package cc.ligu.common.utils;
    import java.io.ByteArrayOutputStream;
    import java.io.File;
    import java.io.FileInputStream;
    import java.io.FileOutputStream;
    import java.io.FilenameFilter;
    import java.io.InputStream;

    public class YuGiOh
    {
        public static final String SEPARATOR = System.getProperty ("file.separator");
        public static final String LINE = System.getProperty ("line.separator");
        /**
         * 转换之后保存的路径
         */
        private static final String SAVE = "C:\\Users\\shenyy\\Desktop\\壁纸\\头像";

        /**
         * 递归读取文件夹中的特定后缀的文件
         *
         * @param path String 读取的文件夹路径
         * @param suffix String 后缀名，|分隔
         * @param newSuffix String 新的后缀名
         */
        public static void convertSuffix ( String path, final String suffix, final String newSuffix )
        {
            File p = new File (path);
            String name = p.getName (), regex = "(?i)([^\\.]*)\\.(" + suffix + ")";
            if (p.isDirectory ())
            {
                p.list (new FilenameFilter ()
                {
                    @Override
                    public boolean accept ( File dir, String name )
                    {
                        if (dir.isDirectory ())
                        {
                            convertSuffix (dir.getAbsolutePath () + SEPARATOR + name, suffix, newSuffix);
                        }
                        return false;
                    }
                });
            }
            else if (name.matches (regex))
            {
                saveFiles (path, name, newSuffix);
            }
        }

        /**
         * 读取到特定的后缀，修改后缀，保存文件
         *
         * @param path String 读取的文件夹路径
         * @param name String 特定的后缀的文件名
         * @param newSuffix String 新的后缀名
         */
        public static void saveFiles ( String path, String name, String newSuffix )
        {
            try
            {
                File fp = new File (SAVE);
                if (!fp.exists ())
                {
                    fp.mkdir ();
                }
                name = name.replaceAll ("([^\\.]+)(\\..*)?", "$1." + newSuffix);
                InputStream is = new FileInputStream (path);
                ByteArrayOutputStream baos = new ByteArrayOutputStream ();
                byte[] buffer = new byte[1024];
                int len = -1;
                while (( len = is.read (buffer) ) != -1)
                {
                    baos.write (buffer, 0, len);
                }
                baos.flush ();
                baos.close ();
                is.close ();
                byte[] data = baos.toByteArray ();
                FileOutputStream fos = new FileOutputStream (new File (SAVE + SEPARATOR + name));
                fos.write (data);
                fos.flush ();
                fos.close ();
            }
            catch (Exception e)
            {
                e.printStackTrace ();
            }
        }

        public static void main ( String[] args )
        {
            convertSuffix ("C:\\Users\\shenyy\\Desktop\\壁纸\\头像 - 副本", "png|jpg", "png");
        }
    }